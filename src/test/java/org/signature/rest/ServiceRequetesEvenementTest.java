package org.signature.rest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import org.signature.mqttRest.objetsPartages.etatEtPilotage.IMessageAffichageEquipementMqttRest;
import org.signature.mqttRest.objetsPartages.etatEtPilotage.IMessageAffichageEquipementMqttRest.TypeEquipement;
import org.signature.mqttRest.objetsPartages.evenement.MessageAlarmeMqttRest;
import org.signature.mqttRest.objetsPartages.etatEtPilotage.MessageModuleMqttRest;
import org.signature.mqttRest.objetsPartages.etatEtPilotage.MessagePmvMqttRest;
import org.signature.mqttRest.objetsPartages.etatEtPilotage.MessagePpadMqttRest;
import org.signature.mqttRest.objetsPartages.etatEtPilotage.MessagePrismeMqttRest;
import org.signature.mqttRest.services.rest.client.InterrogationServeurHttpRest;
import org.signature.mqttRest.services.rest.serveur.ServeurHttpRest;
import org.signature.mqttRest.services.rest.serveur.TraitementRequetesRestAdapteur;

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

		IMessageAffichageEquipementMqttRest msg = InterrogationServeurHttpRest
				.requeteDemandeEtatAffichageEquipementPourDate(HOST, PORT, "ab", "cd", "1111", Date.from(ist1));

		assertNotNull("Message null", msg);
		assertEquals("Type incorrect", TypeEquipement.PMV, msg.getTypeEquipement());
		
		assertEquals("Taille incorrect", 3, ((MessagePmvMqttRest)msg).getMessagesLignes().size());
	}
	
	/**
	 * Test de demande d'état d'un équipement entre deux dates
	 */
	@Test
	public void testEtatEquipementEntreDeuxDates() {
		LocalDateTime ldt1 = LocalDateTime.of(2016, Month.NOVEMBER, 8, 16, 50, 10);
		Instant ist1 = ldt1.toInstant(ZoneOffset.UTC);
		
		LocalDateTime ldt2 = LocalDateTime.of(2016, Month.NOVEMBER, 8, 16, 55, 20);
		Instant ist2 = ldt2.toInstant(ZoneOffset.UTC);

		List<IMessageAffichageEquipementMqttRest> msgs = InterrogationServeurHttpRest
				.requeteDemandeEtatAffichageEquipementEntreDeuxDates(HOST, PORT, "ab", "cd", "1111", Date.from(ist1), Date.from(ist2));

		assertEquals("Taille incorrecte", 3, msgs.size());
		assertEquals("Type msg 1 incorrect", TypeEquipement.PMV, msgs.get(0).getTypeEquipement());
		assertEquals("Type msg 2 incorrect", TypeEquipement.PPAD, msgs.get(1).getTypeEquipement());
		assertEquals("Type msg 3 incorrect", TypeEquipement.PRISME, msgs.get(2).getTypeEquipement());
		
		assertEquals("Taille lignes incorrect", 3, ((MessagePmvMqttRest)msgs.get(0)).getMessagesLignes().size());
		assertEquals("Msg PPAD incorrect", "ppad 1", ((MessagePpadMqttRest)msgs.get(1)).getMessagesModuleUnique().getMessagesParPage().get(0));
		assertEquals("Msg Prisme incorrect", "prisme 1", ((MessagePrismeMqttRest)msgs.get(2)).getMessagesModuleUnique().getMessagesParPage().get(0));
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
	public IMessageAffichageEquipementMqttRest traiteDemandeEtatAffichageEquipementPourDate(String pIdentifiantExpediteur,
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
	
	@Override
	public List<IMessageAffichageEquipementMqttRest> traiteDemandeEtatAffichageEquipementEntreDeuxDates(String pIdentifiantExpediteur,
			String pReferenceCommande, String pIdEquipement, Date pHorodateDebut, Date pHorodateFin) {
		assertEquals("Id expediteur incorrect", "ab", pIdentifiantExpediteur);
		assertEquals("Id commande incorrect", "cd", pReferenceCommande);
		assertEquals("Id équipement incorrect", "1111", pIdEquipement);

		LocalDateTime ldt = LocalDateTime.of(2016, Month.NOVEMBER, 8, 16, 50, 10);
		Instant ist = ldt.toInstant(ZoneOffset.UTC);
		assertEquals("Horodate début incorrect", Date.from(ist), pHorodateDebut);
		
		ldt = LocalDateTime.of(2016, Month.NOVEMBER, 8, 16, 55, 20);
		ist = ldt.toInstant(ZoneOffset.UTC);
		assertEquals("Horodate fin incorrect", Date.from(ist), pHorodateFin);
		
		List<IMessageAffichageEquipementMqttRest> retour = new ArrayList<>();

		MessagePmvMqttRest pmv = new MessagePmvMqttRest();

		List<MessageModuleMqttRest> lignes = new ArrayList<>();
		lignes.add(new MessageModuleMqttRest());
		lignes.add(new MessageModuleMqttRest());
		lignes.add(new MessageModuleMqttRest());
		pmv.setMessagesLignes(lignes);
		retour.add(pmv);
		
		MessagePpadMqttRest ppad = new MessagePpadMqttRest();
		MessageModuleMqttRest mod = new MessageModuleMqttRest();
		mod.setMessagesParPage(Arrays.asList("ppad 1"));
		ppad.setMessagesModuleUnique(mod);
		retour.add(ppad);
		
		MessagePrismeMqttRest prisme = new MessagePrismeMqttRest();
		mod = new MessageModuleMqttRest();
		mod.setMessagesParPage(Arrays.asList("prisme 1"));
		prisme.setMessagesModuleUnique(mod);
		retour.add(prisme);
		
		return retour;
	}

}
