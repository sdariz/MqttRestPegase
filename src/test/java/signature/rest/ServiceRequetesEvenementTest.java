package signature.rest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import signature.mqttRest.objetsPartages.etatEtPilotage.IMessageAffichageEquipement;
import signature.mqttRest.objetsPartages.etatEtPilotage.MessageAlarmeMqttRest;
import signature.mqttRest.objetsPartages.etatEtPilotage.IMessageAffichageEquipement.TypeEquipement;
import signature.mqttRest.objetsPartages.etatEtPilotage.MessageModuleMqttRest;
import signature.mqttRest.objetsPartages.etatEtPilotage.MessagePmvMqttRest;
import signature.mqttRest.services.rest.client.InterrogationServeurHttpRest;
import signature.mqttRest.services.rest.serveur.ServeurHttpRest;
import signature.mqttRest.services.rest.serveur.TraitementRequetesRestAdapteur;

public class ServiceRequetesEvenementTest {

	private static ServeurHttpRest serveurHttpRest;
	private static TraitementRequetesEvenement traitementsRequetesRest;

	private final static String HOST = "localhost";
	private final static int PORT = 1122;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		traitementsRequetesRest = new TraitementRequetesEvenement();

		// Démarrage du serveur HTTP REST
		serveurHttpRest = new ServeurHttpRest(PORT, traitementsRequetesRest);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		serveurHttpRest.arretServeur();
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test de demande de la liste des alarmes actives
	 */
	@Test
	public void testListeAlarmesActives() {
		LocalDateTime ldt1 = LocalDateTime.of(2016, Month.NOVEMBER, 8, 16, 50, 10);
		Instant ist1 = ldt1.toInstant(ZoneOffset.UTC);

		LocalDateTime ldt2 = LocalDateTime.of(2016, Month.NOVEMBER, 8, 16, 55, 20);
		Instant ist2 = ldt2.toInstant(ZoneOffset.UTC);

		// Flag alarmes actives
		traitementsRequetesRest.active = true;

		List<MessageAlarmeMqttRest> alarmes = InterrogationServeurHttpRest.requeteDemandeListeAlarmes(HOST, PORT, "ab",
				"cd", "1111", Date.from(ist1), Date.from(ist2), true);

		assertEquals("Taille incorrect", 3, alarmes.size());
	}

	/**
	 * Test de demande de la liste de toutes les alarmes
	 */
	@Test
	public void testListeToutesAlarmes() {
		LocalDateTime ldt1 = LocalDateTime.of(2016, Month.NOVEMBER, 8, 16, 50, 10);
		Instant ist1 = ldt1.toInstant(ZoneOffset.UTC);

		LocalDateTime ldt2 = LocalDateTime.of(2016, Month.NOVEMBER, 8, 16, 55, 20);
		Instant ist2 = ldt2.toInstant(ZoneOffset.UTC);

		// Flag alarmes actives
		traitementsRequetesRest.active = false;

		List<MessageAlarmeMqttRest> alarmes = InterrogationServeurHttpRest.requeteDemandeListeAlarmes(HOST, PORT, "ab",
				"cd", "1111", Date.from(ist1), Date.from(ist2), false);

		assertEquals("Taille incorrect", 3, alarmes.size());
	}

	/**
	 * Test de demande d'état d'un équipement à une date
	 */
	@Test
	public void testEtatEquipementPourDate() {
		LocalDateTime ldt1 = LocalDateTime.of(2016, Month.NOVEMBER, 8, 16, 50, 10);
		Instant ist1 = ldt1.toInstant(ZoneOffset.UTC);

		IMessageAffichageEquipement msg = InterrogationServeurHttpRest
				.requeteDemandeEtatAffichageEquipementPourDate(HOST, PORT, "ab", "cd", "1111", Date.from(ist1));

		assertNotNull("Message null", msg);
		assertEquals("Type incorrect", TypeEquipement.PMV, msg.getTypeEquipement());
		
		assertEquals("Taille incorrect", 3, ((MessagePmvMqttRest)msg).getMessagesLignes().size());
	}

}

class TraitementRequetesEvenement extends TraitementRequetesRestAdapteur {
	protected boolean active = false;

	@Override
	public List<MessageAlarmeMqttRest> traiteDemandeListeAlarmes(String pIdentifiantExpediteur,
			String pReferenceCommande, String pIdEquipement, Date pHorodateDebut, Date pHorodateFin, boolean pActive) {
		assertEquals("Id expediteur incorrect", "ab", pIdentifiantExpediteur);
		assertEquals("Id commande incorrect", "cd", pReferenceCommande);
		assertEquals("Id équipement incorrect", "1111", pIdEquipement);

		LocalDateTime ldt = LocalDateTime.of(2016, Month.NOVEMBER, 8, 16, 50, 10);
		Instant ist = ldt.toInstant(ZoneOffset.UTC);
		assertEquals("Horodate début incorrect", Date.from(ist), pHorodateDebut);

		ldt = LocalDateTime.of(2016, Month.NOVEMBER, 8, 16, 55, 20);
		ist = ldt.toInstant(ZoneOffset.UTC);
		assertEquals("Horodate fin incorrect", Date.from(ist), pHorodateFin);

		assertEquals("Active incorrect", active, pActive);

		List<MessageAlarmeMqttRest> retour = new ArrayList<MessageAlarmeMqttRest>();
		retour.add(new MessageAlarmeMqttRest());
		retour.add(new MessageAlarmeMqttRest());
		retour.add(new MessageAlarmeMqttRest());

		return retour;
	}

	@Override
	public IMessageAffichageEquipement traiteDemandeEtatAffichageEquipementPourDate(String pIdentifiantExpediteur,
			String pReferenceCommande, String pIdEquipement, Date pHorodate) {
		assertEquals("Id expediteur incorrect", "ab", pIdentifiantExpediteur);
		assertEquals("Id commande incorrect", "cd", pReferenceCommande);
		assertEquals("Id équipement incorrect", "1111", pIdEquipement);

		LocalDateTime ldt = LocalDateTime.of(2016, Month.NOVEMBER, 8, 16, 50, 10);
		Instant ist = ldt.toInstant(ZoneOffset.UTC);
		assertEquals("Horodate début incorrect", Date.from(ist), pHorodate);

		MessagePmvMqttRest retour = new MessagePmvMqttRest();

		List<MessageModuleMqttRest> lignes = new ArrayList<>();
		lignes.add(new MessageModuleMqttRest());
		lignes.add(new MessageModuleMqttRest());
		lignes.add(new MessageModuleMqttRest());
		retour.setMessagesLignes(lignes);

		return retour;
	}

}
