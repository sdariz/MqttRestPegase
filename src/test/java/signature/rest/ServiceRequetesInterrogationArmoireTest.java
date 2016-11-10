package signature.rest;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import signature.mqttRest.services.rest.client.InterrogationServeurHttpRest;
import signature.mqttRest.services.rest.serveur.ServeurHttpRest;
import signature.mqttRest.services.rest.serveur.TraitementRequetesRestAdapteur;

public class ServiceRequetesInterrogationArmoireTest {

	private static ServeurHttpRest serveurHttpRest;
	private static TraitementRequetesRest traitementsRequetesRest;

	private final static String HOST = "localhost";
	private final static int PORT = 1122;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		traitementsRequetesRest = new TraitementRequetesRest();

		// D�marrage du serveur HTTP REST
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
	 * Requ�te de test des �quipements de l'armoire
	 */
	@Test
	public void testLancementTestEquipements() {
		traitementsRequetesRest.traitement = 0;

		InterrogationServeurHttpRest.requeteLancementTestEquipementsArmoire(HOST, PORT, "ab", "cd", "1111");

		// Sortie en erreur
		assertEquals("ERREUR TEST", 1, traitementsRequetesRest.traitement);
	}
}

class TraitementRequetesRest extends TraitementRequetesRestAdapteur {
	protected int traitement = 0;
	
	@Override
	public void traiteDemandeLancementTestEquipements(String pIdentifiantExpediteur,
			String pReferenceCommande, String pId) {
		try {
			assertEquals("Id armoire incorrect", "1111", pId);
			assertEquals("Id expediteur incorrect", "ab", pIdentifiantExpediteur);
			assertEquals("Id commande incorrect", "cd", pReferenceCommande);
		} catch (Throwable t) {
			t.printStackTrace();
			traitement = 2;
			return;
		}
		
		traitement = 1;
	}

}
