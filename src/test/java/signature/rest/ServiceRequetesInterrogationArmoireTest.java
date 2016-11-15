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
	 * Requête de test des équipements de l'armoire
	 */
	@Test
	public void testLancementTestEquipements() {
		traitementsRequetesRest.traitement = 0;

		InterrogationServeurHttpRest.requeteLancementTestEquipementsArmoire(HOST, PORT, "ab", "cd", "1111");

		// Sortie en erreur
		assertEquals("ERREUR TEST", 1, traitementsRequetesRest.traitement);
	}
	
	/**
	 * Requête de remise à l'heure d'une armoire
	 */
	@Test
	public void testRemiseHeureArmoire() {
		traitementsRequetesRest.traitement = 0;

		InterrogationServeurHttpRest.requeteRemiseHeureArmoire(HOST, PORT, "ab", "cd", "2222");

		// Sortie en erreur
		assertEquals("ERREUR TEST", 1, traitementsRequetesRest.traitement);
	}
	
	/**
	 * Requête d'envoi de commande
	 */
	@Test
	public void testDemandeArmoire() {
		String reponse = InterrogationServeurHttpRest.requeteDemandeArmoire(HOST, PORT, "ab", "cd", "3333", "Une commande");

		// Sortie en erreur
		assertEquals("Réponse incorrrecte", "Une réponse", reponse);
	}
}

class TraitementRequetesRest extends TraitementRequetesRestAdapteur {
	protected int traitement = 0;
	
	@Override
	public void traiteDemandeLancementTestEquipements(String pIdentifiantExpediteur,
			String pReferenceCommande, String pIdArmoire) {
		try {
			assertEquals("Id armoire incorrect", "1111", pIdArmoire);
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
	public void traiteDemandeRemiseHeureArmoire(String pIdentifiantExpediteur,
			String pReferenceCommande, String pIdArmoire) {
		try {
			assertEquals("Id armoire incorrect", "2222", pIdArmoire);
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
	public String traiteDemandeArmoire(String pIdentifiantExpediteur,
			String pReferenceCommande, String pIdArmoire, String pTrame) {
		try {
			assertEquals("Id armoire incorrect", "3333", pIdArmoire);
			assertEquals("Id expediteur incorrect", "ab", pIdentifiantExpediteur);
			assertEquals("Id commande incorrect", "cd", pReferenceCommande);
			assertEquals("Trame incorrect", "Une commande", pTrame);
		} catch (Throwable t) {
			t.printStackTrace();
			return "";
		}
		
		return "Une réponse";
	}

}
