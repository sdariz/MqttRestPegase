package signature.rest;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import signature.mqttRest.services.rest.client.InterrogationServeurHttpRest;
import signature.mqttRest.services.rest.serveur.ServeurHttpRest;
import signature.mqttRest.services.rest.serveur.TraitementRequetesRestAdapteur;

public class ServiceRequetesAdministrationTest {
	private static ServeurHttpRest serveurHttpRest;
	private static TraitementRequetesAdministration traitementsRequetesRest;

	private final static int PORT = 1122;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		traitementsRequetesRest = new TraitementRequetesAdministration();

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
	 * Force une interdiction des pilotages
	 */
	@Test
	public void testRequeteInterdictionPilotages() {
		// Flag pilotage interdit pour valider le test
		traitementsRequetesRest.setInterdit(true);

		InterrogationServeurHttpRest.requeteInterdictionPilotages("localhost", PORT, true);
	}

	/**
	 * Force une autorisation des pilotages
	 */
	@Test
	public void testRequeteAutorisationPilotages() {
		// Flag pilotage interdit pour valider le test
		traitementsRequetesRest.setInterdit(false);

		InterrogationServeurHttpRest.requeteInterdictionPilotages("localhost", PORT, false);
	}

}

class TraitementRequetesAdministration extends TraitementRequetesRestAdapteur {
	private boolean interdit = true;

	// Initialisation du flag interdit
	protected void setInterdit(boolean pInterdit) {
		interdit = pInterdit;
	}

	@Override
	public void traiteDemandeInterdictionPilotages(boolean pInterdit) {
		assertEquals("Interdiction non valide", interdit, pInterdit);
	}
}
