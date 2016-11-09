package signature.rest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import signature.mqttRest.services.rest.client.InterrogationServeurHttpRest;
import signature.mqttRest.services.rest.serveur.ServeurHttpRest;
import signature.mqttRest.services.rest.serveur.TraitementRequetesRestAdapteur;

/**
 * Test des requêtes d'administration de Pegase
 * 
 * @author SDARIZCUREN
 *
 */
public class ServiceRequetesAdministrationTest {
	private static ServeurHttpRest serveurHttpRest;
	private static TraitementRequetesAdministration traitementsRequetesRest;

	private final static String HOST = "localhost";
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
		traitementsRequetesRest.traitement = 0;

		// Flag pilotage interdit pour valider le test
		traitementsRequetesRest.interdit = true;

		InterrogationServeurHttpRest.requeteInterdictionPilotages(HOST, PORT, true, "ab", "cd");

		// Sortie en erreur
		assertEquals("ERREUR TEST", 1, traitementsRequetesRest.traitement);

	}

	/**
	 * Force une autorisation des pilotages
	 */
	@Test
	public void testRequeteAutorisationPilotages() {
		traitementsRequetesRest.traitement = 0;

		// Flag pilotage interdit pour valider le test
		traitementsRequetesRest.interdit = false;

		InterrogationServeurHttpRest.requeteInterdictionPilotages(HOST, PORT, false, "ab", "cd");

		// Sortie en erreur
		assertEquals("ERREUR TEST", 1, traitementsRequetesRest.traitement);
	}

	/**
	 * Test de présence du serveur
	 */
	@Test
	public void testPresenceServeur() {
		assertTrue("Serveur absent", InterrogationServeurHttpRest.requeteTestPresence(HOST, PORT, "ab", "cd"));
	}

	/**
	 * Test activation d'un bouton
	 */
	@Test
	public void testActivationBouton() {
		traitementsRequetesRest.traitement = 0;

		traitementsRequetesRest.activation = true;
		InterrogationServeurHttpRest.requeteActivationBouton(HOST, PORT, "ab", "cd", "1111", true);

		// Sortie en erreur
		assertEquals("ERREUR TEST", 1, traitementsRequetesRest.traitement);
	}

	/**
	 * Test désactivation d'un bouton
	 */
	@Test
	public void testDesactivationBouton() {
		traitementsRequetesRest.traitement = 0;

		traitementsRequetesRest.activation = false;
		InterrogationServeurHttpRest.requeteActivationBouton(HOST, PORT, "ab", "cd", "1111", false);

		// Sortie en erreur
		assertEquals("ERREUR TEST", 1, traitementsRequetesRest.traitement);
	}
	
	/**
	 * Test lancement action rattaché à un bouton
	 */
	@Test
	public void testLancementActionBouton() {
		traitementsRequetesRest.traitement = 0;

		InterrogationServeurHttpRest.requeteLancementActionBouton(HOST, PORT, "ab", "cd", "1111");

		// Sortie en erreur
		assertEquals("ERREUR TEST", 1, traitementsRequetesRest.traitement);
	}

}

class TraitementRequetesAdministration extends TraitementRequetesRestAdapteur {
	protected int traitement = 0;
	protected boolean interdit = true;
	protected boolean activation = false;

	@Override
	public void traiteDemandeInterdictionPilotages(boolean pInterdit, String pIdentifiantExpediteur,
			String pReferenceCommande) {
		try {
			assertEquals("Interdiction non valide", interdit, pInterdit);
			assertEquals("Id expediteur non valide", "ab", pIdentifiantExpediteur);
			assertEquals("Id commande non valide", "cd", pReferenceCommande);
		} catch (Throwable t) {
			t.printStackTrace();
			traitement = 2;
			return;
		}
		
		traitement = 1;
	}

	@Override
	public void traiteDemandeActivationBouton(String pIdBouton, boolean pActif, String pIdentifiantExpediteur,
			String pReferenceCommande) {
		try {
			assertEquals("Id bouton incorrect", "1111", pIdBouton);
			assertEquals("Activation incorrect", activation, pActif);
			assertEquals("Id expediteur incorrect", "ab", pIdentifiantExpediteur);
			assertEquals("Id commande incorrect", "cd", pReferenceCommande);
		} catch (Throwable t) {
			t.printStackTrace();
			traitement = 2;
			return;
		}
		
		traitement = 1;
	}
	
	@Override
	public void traiteDemandeLancementActionBouton(String pIdBouton, String pIdentifiantExpediteur,
			String pReferenceCommande) {
		try {
			assertEquals("Id bouton incorrect", "1111", pIdBouton);
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
