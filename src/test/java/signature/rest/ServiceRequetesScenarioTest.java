package signature.rest;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import signature.mqttRest.services.rest.client.InterrogationServeurHttpRest;
import signature.mqttRest.services.rest.serveur.ServeurHttpRest;
import signature.mqttRest.services.rest.serveur.TraitementRequetesRestAdapteur;

public class ServiceRequetesScenarioTest {

	private static ServeurHttpRest serveurHttpRest;
	private static TraitementRequetesScenario traitementsRequetesRest;

	private final static String HOST = "localhost";
	private final static int PORT = 1122;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		traitementsRequetesRest = new TraitementRequetesScenario();

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
	 * Test de récupération des identifiants des scénarios
	 */
	@Test
	public void testListeIdentifiantsScenarios() {
		List<String> ids = InterrogationServeurHttpRest.requeteDemandeIdentifiantsScenarios(HOST, PORT, "ab", "cd");

		assertEquals("Taille incorrect", 3, ids.size());
		assertEquals("Identiifants incorrect",  Arrays.asList("1111", "2222", "3333"), ids);
	}

}

class TraitementRequetesScenario extends TraitementRequetesRestAdapteur {
	
	@Override
	public List<String> traiteDemandeIdentifiantsScenarios(String pIdentifiantExpediteur, String pReferenceCommande) {
		assertEquals("Id expediteur incorrect", "ab", pIdentifiantExpediteur);
		assertEquals("Id commande incorrect", "cd", pReferenceCommande);
		
		return Arrays.asList("1111", "2222", "3333");
	}
	
}
