package org.signature.rest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import org.signature.mqttRest.services.rest.client.InterrogationServeurHttpRest;
import org.signature.mqttRest.services.rest.serveur.ServeurHttpRest;
import org.signature.mqttRest.services.rest.serveur.TraitementRequetesRestAdapteur;

/**
 * Test des requ?tes d'administration de Pegase
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

		// D?marrage du serveur HTTP REST
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

		new InterrogationServeurHttpRest().requeteInterdictionPilotages(HOST, PORT, "ab", "cd", true);

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

		new InterrogationServeurHttpRest().requeteInterdictionPilotages(HOST, PORT, "ab", "cd", false);

		// Sortie en erreur
		assertEquals("ERREUR TEST", 1, traitementsRequetesRest.traitement);
	}

	/**
	 * Force une interdiction des pilotages sur certains ?quipements
	 */
	@Test
	public void testRequeteInterdictionPilotagesSurEquipements() {
		traitementsRequetesRest.traitement = 0;

		// Flag pilotage interdit pour valider le test
		traitementsRequetesRest.interdit = true;

		new InterrogationServeurHttpRest().requeteInterdictionPilotages(HOST, PORT, "ab", "cd",
				Arrays.asList("1111", "2222", "3333"), true);

		// Sortie en erreur
		assertEquals("ERREUR TEST", 1, traitementsRequetesRest.traitement);

	}

	/**
	 * Test de pr?sence du serveur
	 */
	@Test
	public void testPresenceServeur() {
		assertTrue("Serveur absent", new InterrogationServeurHttpRest().requeteTestPresence(HOST, PORT, "ab", "cd"));
	}

	/**
	 * Test activation d'un bouton
	 */
	@Test
	public void testActivationBouton() {
		traitementsRequetesRest.traitement = 0;

		traitementsRequetesRest.activation = true;
		new InterrogationServeurHttpRest().requeteActivationBouton(HOST, PORT, "ab", "cd", "1111", true);

		// Sortie en erreur
		assertEquals("ERREUR TEST", 1, traitementsRequetesRest.traitement);
	}

	/**
	 * Test d?sactivation d'un bouton
	 */
	@Test
	public void testDesactivationBouton() {
		traitementsRequetesRest.traitement = 0;

		traitementsRequetesRest.activation = false;
		new InterrogationServeurHttpRest().requeteActivationBouton(HOST, PORT, "ab", "cd", "1111", false);

		// Sortie en erreur
		assertEquals("ERREUR TEST", 1, traitementsRequetesRest.traitement);
	}

	/**
	 * Test lancement action rattach? ? un bouton
	 */
	@Test
	public void testLancementActionBouton() {
		traitementsRequetesRest.traitement = 0;

		new InterrogationServeurHttpRest().requeteLancementActionBouton(HOST, PORT, "ab", "cd", "1111");

		// Sortie en erreur
		assertEquals("ERREUR TEST", 1, traitementsRequetesRest.traitement);
	}
	
	/**
	 * Force un arr?t de Pegase
	 */
	@Test
	public void testForcageArretPegase() {
		traitementsRequetesRest.traitement = 0;

		new InterrogationServeurHttpRest().requeteForcageArretApplication(HOST, PORT, "ab", "cd");

		// Sortie en erreur
		assertEquals("ERREUR TEST", 1, traitementsRequetesRest.traitement);
	}
	
	/**
	 * DEmande les donn?es sauvegardes au format JSON
	 */
	@Test
	public void testDemandeDecriptionDonnesTravailSauvegardes() {
		traitementsRequetesRest.traitement = 0;

		String Descjson = new InterrogationServeurHttpRest().requeteDemandeDecriptionDonnesTravailSauvegardes(HOST, PORT, "ab", "cd");

		// Sortie en erreur
		assertEquals("ERREUR TEST", 1, traitementsRequetesRest.traitement);
		assertEquals("ERREUR JSON", "{\"JSON\":\"OK\"}", Descjson);
	}

}

class TraitementRequetesAdministration extends TraitementRequetesRestAdapteur {
	protected int traitement = 0;
	protected boolean interdit = true;
	protected boolean activation = false;

	@Override
	public void traiteDemandeInterdictionPilotages(String pIdentifiantExpediteur, String pReferenceCommande,
			boolean pInterdit) {
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
	public void traiteDemandeInterdictionPilotages(String pIdentifiantExpediteur, String pReferenceCommande,
			List<String> pIdsEquipements, boolean pInterdit) {
		try {
			assertEquals("Interdiction non valide", interdit, pInterdit);
			assertEquals("Id expediteur non valide", "ab", pIdentifiantExpediteur);
			assertEquals("Id commande non valide", "cd", pReferenceCommande);
			assertEquals("Nombre ?quipements incorrect", 3, pIdsEquipements.size());
			assertEquals("Ids ?quipements incorrect", Arrays.asList("1111", "2222", "3333"), pIdsEquipements);
		} catch (Throwable t) {
			t.printStackTrace();
			traitement = 2;
			return;
		}

		traitement = 1;
	}

	@Override
	public void traiteDemandeActivationBouton(String pIdentifiantExpediteur, String pReferenceCommande,
			String pIdBouton, boolean pActif) {
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
	public void traiteDemandeLancementActionBouton(String pIdentifiantExpediteur, String pReferenceCommande,
			String pIdBouton) {
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
	
	/**
	 * For?age de l'arret de l'application h?bergeant le serveur REST
	 * 
	 * @param pIdentifiantExpediteur l'identifiant unique de l'exp?diteur : peut
	 *                               ?tre vide
	 * @param pReferenceCommande     la r?f?rence unique de la demande : peut ?tre
	 *                               vide
	 */
	@Override
	public void traiteForcageArretApplication(String pIdentifiantExpediteur, String pReferenceCommande) {
		try {
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
	public String traiteDemandeDecriptionDonnesTravailSauvegardes(String pIdentifiantExpediteur,
			String pReferenceCommande) {
		try {
			assertEquals("Id expediteur incorrect", "ab", pIdentifiantExpediteur);
			assertEquals("Id commande incorrect", "cd", pReferenceCommande);
		} catch (Throwable t) {
			t.printStackTrace();
			traitement = 2;
			return "";
		}

		traitement = 1;
		
		return "{\"JSON\":\"OK\"}";
	}
}
