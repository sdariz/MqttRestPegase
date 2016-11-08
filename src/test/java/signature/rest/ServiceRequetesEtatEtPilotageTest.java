package signature.rest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Date;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import signature.mqttRest.objetsPartages.etatEtPilotage.MessageEtatAffichageMqttRest;
import signature.mqttRest.services.rest.client.InterrogationServeurHttpRest;
import signature.mqttRest.services.rest.serveur.ServeurHttpRest;
import signature.mqttRest.services.rest.serveur.TraitementRequetesRestAdapteur;

public class ServiceRequetesEtatEtPilotageTest {

	private static ServeurHttpRest serveurHttpRest;
	private static TraitementRequetesEtatEtPilotage traitementsRequetesRest;

	private final static int PORT = 1122;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		traitementsRequetesRest = new TraitementRequetesEtatEtPilotage();

		// Démarrage du serveur HTTP REST
		serveurHttpRest = new ServeurHttpRest(PORT, traitementsRequetesRest);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		serveurHttpRest.arretServeur();
		Thread.sleep(100); // Attente arrêt du serveur avant de passer aux tests
							// suivants
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test demande d'état d'affichage d'un PMV
	 */
	@Test
	public void testRequeteDemandeEtatAffichagePMV() {
		MessageEtatAffichageMqttRest msg = InterrogationServeurHttpRest
				.requeteDemandeEtatAffichageEquipement("localhost", PORT, "1578", "ab", "cd");

		assertEquals("Id expéditeur incorrect", msg.getIdentifiantExpediteur(), "ab");
		assertEquals("Id commande incorrect", msg.getReferenceMessage(), "cd");
		assertEquals("Identifiant incorrect", msg.getIdentifiantEquipement(), "1578");
		
		LocalDateTime ldt = LocalDateTime.of(2016, Month.NOVEMBER, 8, 16, 50, 10);
		Instant ist = ldt.toInstant(ZoneOffset.UTC);
		assertEquals("Horodate génération incorrect", msg.getHorodateGeneration(), Date.from(ist));
		
		ldt = LocalDateTime.of(2016, Month.NOVEMBER, 8, 16, 55, 20);
		ist = ldt.toInstant(ZoneOffset.UTC);
		assertEquals("Horodate FIN incorrect", msg.getHorodateFin(), Date.from(ist));
	}

	@Test
	public void testRequeteDemandeEtatAffichageEquipements() {
		fail("Not yet implemented");
	}

	@Test
	public void testRequeteActualisationEtatEquipement() {
		fail("Not yet implemented");
	}
}

class TraitementRequetesEtatEtPilotage extends TraitementRequetesRestAdapteur {

	@Override
	public MessageEtatAffichageMqttRest demandeEtatAffichageEquipement(String pId, String pIdentifiantExpediteur,
			String pReferenceCommande) {
		MessageEtatAffichageMqttRest retour = new MessageEtatAffichageMqttRest(pId, pIdentifiantExpediteur, pReferenceCommande);
		
		LocalDateTime ldt = LocalDateTime.of(2016, Month.NOVEMBER, 8, 16, 50, 10);
		Instant ist = ldt.toInstant(ZoneOffset.UTC);
		retour.setHorodateGeneration(Date.from(ist));
		
		 ldt = LocalDateTime.of(2016, Month.NOVEMBER, 8, 16, 55, 20);
		 ist = ldt.toInstant(ZoneOffset.UTC);
		 retour.setHorodateFin(Date.from(ist));
		 
		 //retour.setMessageEquipement(pMsg);

		return retour;
	}
}
