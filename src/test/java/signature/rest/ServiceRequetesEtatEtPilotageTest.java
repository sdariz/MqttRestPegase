package signature.rest;

import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import signature.mqttRest.objetsPartages.etatEtPilotage.MessageEtatAffichageMqttRest;
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
		fail("Not yet implemented");
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
	public MessageEtatAffichageMqttRest demandeEtatAffichageEquipement(String pId) {
		MessageEtatAffichageMqttRest retour = new MessageEtatAffichageMqttRest();
		
		
		return retour;
	}
}
