package signature.rest;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import signature.mqttRest.objetsPartages.utilisateur.MessageUtilisateurMqttRest;
import signature.mqttRest.objetsPartages.utilisateur.MessageUtilisateurMqttRest.Droits;
import signature.mqttRest.services.rest.client.InterrogationServeurHttpRest;
import signature.mqttRest.services.rest.serveur.ServeurHttpRest;
import signature.mqttRest.services.rest.serveur.TraitementRequetesRestAdapteur;

public class ServiceRequetesUtilisateurTest {
	private static ServeurHttpRest serveurHttpRest;
	private static TraitementRequetesUtilisateur traitementsRequetesRest;

	private final static String HOST = "localhost";
	private final static int PORT = 1122;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		traitementsRequetesRest = new TraitementRequetesUtilisateur();

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
	 * Vérification 2 utilisateurs définis
	 */
	@Test
	public void testRequeteDemandeListeUtilisateurs() {
		List<MessageUtilisateurMqttRest> msgs = InterrogationServeurHttpRest
				.requeteDemandeListeUtilisateurs(HOST, PORT, "ab", "cd");

		assertEquals("Taille incorrecte", msgs.size(), 2);
		
		assertEquals("Id expediteur incorrect", "ab", msgs.get(0).getIdentifiantExpediteur());
		assertEquals("Id commande incorrect", "cd", msgs.get(0).getReferenceMessage());
		assertEquals("Nom incorrect", msgs.get(0).getNom(), "Nom1TestUnitaire");
		assertEquals("Prénom incorrect", msgs.get(0).getPrenom(), "Prenom1TestUnitaire");
		assertEquals("Mot de passe incorrect", msgs.get(0).getMotPasse(), "MotPasse1TestUnitaire");
		assertEquals("Droit incorrect", msgs.get(0).getDroits(), Droits.ADMINISTRATEUR);

		assertEquals("Id expediteur incorrect", "ab", msgs.get(1).getIdentifiantExpediteur());
		assertEquals("Id commande incorrect", "cd", msgs.get(1).getReferenceMessage());
		assertEquals("Nom incorrect", msgs.get(1).getNom(), "Nom2TestUnitaire");
		assertEquals("Prénom incorrect", msgs.get(1).getPrenom(), "Prenom2TestUnitaire");
		assertEquals("Mot de passe incorrect", msgs.get(1).getMotPasse(), "MotPasse2TestUnitaire");
		assertEquals("Droit incorrect", msgs.get(1).getDroits(), Droits.GESTIONNAIRE);
	}

	/**
	 * Validation des propriétés de l'utilsiateur connecté
	 */
	@Test
	public void testRequeteDemandeUtilisateurConnecte() {
		MessageUtilisateurMqttRest msg = InterrogationServeurHttpRest.requeteDemandeUtilisateurConnecte(HOST,
				PORT, "aa", "bb");

		assertEquals("Id expediteur incorrect", "aa", msg.getIdentifiantExpediteur());
		assertEquals("Id commande incorrect", "bb", msg.getReferenceMessage());
		assertEquals("Nom incorrect", msg.getNom(), "NomTestUnitaire");
		assertEquals("Prénom incorrect", msg.getPrenom(), "PrenomTestUnitaire");
		assertEquals("Mot de passe incorrect", msg.getMotPasse(), "MotPasseTestUnitaire");
		assertEquals("Droit incorrect", msg.getDroits(), Droits.ADMINISTRATEUR);
	}

	/**
	 * Test identifiant et mot de passe valide
	 */
	@Test
	public void testRequeteDemandeIdentifiantsValide() {
		assertEquals("Identifiant invalide", true, InterrogationServeurHttpRest
				.requeteDemandeIdentifiantsValide(HOST, PORT, "NomValide", "MotPasseValide", "ab", "cd"));
	}

	/**
	 * Rejet car nom invalide
	 */
	@Test
	public void testRequeteDemandeIdentifiantsNomInvalide() {
		assertEquals("Le nom devrait être invalide", false, InterrogationServeurHttpRest
				.requeteDemandeIdentifiantsValide(HOST, PORT, "NomInvalide", "MotPasseValide", "ab", "cd"));
	}

	/**
	 * Rejet car mot de passe invalide
	 */
	@Test
	public void testRequeteDemandeIdentifiantsMotPasseInvalide() {
		assertEquals("Le mot de passe devrait être invalide", false, InterrogationServeurHttpRest
				.requeteDemandeIdentifiantsValide(HOST, PORT, "NomValide", "MotPasseInvalide", "ab", "cd"));
	}

}

class TraitementRequetesUtilisateur extends TraitementRequetesRestAdapteur {

	@Override
	public List<MessageUtilisateurMqttRest> demandeListeUtilisateurs(String pIdentifiantExpediteur,
			String pReferenceCommande) {
		List<MessageUtilisateurMqttRest> retour = new ArrayList<>();

		MessageUtilisateurMqttRest msg = new MessageUtilisateurMqttRest(pIdentifiantExpediteur, pReferenceCommande);
		msg.setNom("Nom1TestUnitaire");
		msg.setPrenom("Prenom1TestUnitaire");
		msg.setMotPasse("MotPasse1TestUnitaire");
		msg.setDroits(Droits.ADMINISTRATEUR);
		retour.add(msg);

		msg = new MessageUtilisateurMqttRest(pIdentifiantExpediteur, pReferenceCommande);
		msg.setNom("Nom2TestUnitaire");
		msg.setPrenom("Prenom2TestUnitaire");
		msg.setMotPasse("MotPasse2TestUnitaire");
		msg.setDroits(Droits.GESTIONNAIRE);
		retour.add(msg);

		return retour;
	}

	@Override
	public MessageUtilisateurMqttRest demandeUtilisateurConnecte(String pIdentifiantExpediteur,
			String pReferenceCommande) {
		MessageUtilisateurMqttRest msg = new MessageUtilisateurMqttRest(pIdentifiantExpediteur, pReferenceCommande);
		msg.setNom("NomTestUnitaire");
		msg.setPrenom("PrenomTestUnitaire");
		msg.setMotPasse("MotPasseTestUnitaire");
		msg.setDroits(Droits.ADMINISTRATEUR);
		return msg;
	}

	@Override
	public boolean estValide(String pLogin, String pMotPasse, String pIdentifiantExpediteur,
			String pReferenceCommande) {
		return pLogin.equals("NomValide") && pMotPasse.equals("MotPasseValide");
	}

}
