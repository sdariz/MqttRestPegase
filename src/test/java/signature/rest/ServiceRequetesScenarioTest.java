package signature.rest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import signature.mqttRest.objetsPartages.etatEtPilotage.MessagePmvMqttRest;
import signature.mqttRest.objetsPartages.etatEtPilotage.MessagePpadMqttRest;
import signature.mqttRest.objetsPartages.etatEtPilotage.MessagePplmvMqttRest;
import signature.mqttRest.objetsPartages.scenario.MessageScenarioMqttRest;
import signature.mqttRest.objetsPartages.scenario.OrdonnanceurMessagesScenarioMqttRest;
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
		assertEquals("Identiifants incorrect", Arrays.asList("1111", "2222", "3333"), ids);
	}

	/**
	 * Test de récupération d'un scénario en particulier
	 */
	@Test
	public void testDemandeScenario() {
		MessageScenarioMqttRest msg = InterrogationServeurHttpRest.requeteDemandeScenario(HOST, PORT, "ab", "cd",
				"1111");

		assertNotNull("Scénario null", msg);
		assertEquals("Identifiant incorrect", "1111", msg.getIdentifiant());
		assertEquals("Nom incorrect", "nom1", msg.getNom());
		assertEquals("Categorie incorrect", "cat1", msg.getCategorie());
		assertEquals("Nombre ordonnanceurs incorrect", 3, msg.getOrdonnanceurs().size());

		assertEquals("Numero ordre 1 incorrect", 30, msg.getOrdonnanceurs().get(0).getNumeroOrdre());
		assertEquals("Numero ordre 2 incorrect", 31, msg.getOrdonnanceurs().get(1).getNumeroOrdre());
		assertEquals("Numero ordre 3 incorrect", 32, msg.getOrdonnanceurs().get(2).getNumeroOrdre());

		assertEquals("Temps pilotage 1 incorrect", 10, msg.getOrdonnanceurs().get(0).getTempsAvantPilotage());
		assertEquals("Temps pilotage 2 incorrect", 11, msg.getOrdonnanceurs().get(1).getTempsAvantPilotage());
		assertEquals("Temps pilotage 3 incorrect", 12, msg.getOrdonnanceurs().get(2).getTempsAvantPilotage());

		assertEquals("Nombre messages 1 incorrect", 3, msg.getOrdonnanceurs().get(0).getListeMessages().size());
		assertEquals("Nombre messages 2 incorrect", 2, msg.getOrdonnanceurs().get(1).getListeMessages().size());
		assertEquals("Nombre messages 3 incorrect", 1, msg.getOrdonnanceurs().get(2).getListeMessages().size());
	}

	/**
	 * Test le nombre de messages PMV, PPAD, ..., dans un scénario
	 */
	@Test
	public void testScenarioNombreMessageParType() {
		MessageScenarioMqttRest msg = InterrogationServeurHttpRest.requeteDemandeScenario(HOST, PORT, "ab", "cd",
				"1111");

		assertNotNull("Scénario null", msg);
		assertEquals("Nombre messages incorrect", 6, msg.getTousMessages().size());
		assertEquals("Nombre messages PMV incorrect", 1, msg.getTousMessagesPmv().size());
		assertEquals("Nombre messages incorrect", 2, msg.getTousMessagesPplmv().size());
		assertEquals("Nombre messages incorrect", 3, msg.getTousMessagesPpad().size());
	}

	/**
	 * Test de récupération des scénarios
	 */
	@Test
	public void testDemandeScenarios() {
		List<MessageScenarioMqttRest> msgs = InterrogationServeurHttpRest.requeteDemandeScenarios(HOST, PORT, "ab", "cd");

		assertEquals("Taille incorrect", 3, msgs.size());
		
		assertEquals("Identifiant 1 incorrect", "1111", msgs.get(0).getIdentifiant());
		assertEquals("Nom 1 incorrect", "nom1", msgs.get(0).getNom());
		assertEquals("Categorie 1 incorrect", "cat1", msgs.get(0).getCategorie());

		assertEquals("Identifiant 2 incorrect", "2222", msgs.get(1).getIdentifiant());
		assertEquals("Nom 2 incorrect", "nom2", msgs.get(1).getNom());
		assertEquals("Categorie 2 incorrect", "cat2", msgs.get(1).getCategorie());
		
		assertEquals("Identifiant 3 incorrect", "3333", msgs.get(2).getIdentifiant());
		assertEquals("Nom 3 incorrect", "nom3", msgs.get(2).getNom());
		assertEquals("Categorie 3 incorrect", "cat3", msgs.get(2).getCategorie());
	}

}

class TraitementRequetesScenario extends TraitementRequetesRestAdapteur {

	@Override
	public List<String> traiteDemandeIdentifiantsScenarios(String pIdentifiantExpediteur, String pReferenceCommande) {
		assertEquals("Id expediteur incorrect", "ab", pIdentifiantExpediteur);
		assertEquals("Id commande incorrect", "cd", pReferenceCommande);

		return Arrays.asList("1111", "2222", "3333");
	}

	@Override
	public MessageScenarioMqttRest traiteDemandeScenario(String pIdentifiantExpediteur, String pReferenceCommande,
			String pIdScenario) {
		assertEquals("Id expediteur incorrect", "ab", pIdentifiantExpediteur);
		assertEquals("Id commande incorrect", "cd", pReferenceCommande);
		assertEquals("Id scénario incorrect", "1111", pIdScenario);

		MessageScenarioMqttRest retour = new MessageScenarioMqttRest("1111", "nom1", "cat1");
		List<OrdonnanceurMessagesScenarioMqttRest> liste = new ArrayList<>();
		retour.setOrdonnanceurs(liste);

		liste.add(new OrdonnanceurMessagesScenarioMqttRest(30, 10,
				Arrays.asList(new MessagePmvMqttRest(), new MessagePplmvMqttRest(), new MessagePpadMqttRest())));
		liste.add(new OrdonnanceurMessagesScenarioMqttRest(31, 11,
				Arrays.asList(new MessagePplmvMqttRest(), new MessagePpadMqttRest())));
		liste.add(new OrdonnanceurMessagesScenarioMqttRest(32, 12, Arrays.asList(new MessagePpadMqttRest())));

		return retour;
	}

	@Override
	public List<MessageScenarioMqttRest> traiteDemandeScenarios(String pIdentifiantExpediteur,
			String pReferenceCommande) {
		assertEquals("Id expediteur incorrect", "ab", pIdentifiantExpediteur);
		assertEquals("Id commande incorrect", "cd", pReferenceCommande);

		List<MessageScenarioMqttRest> retour = new ArrayList<>();
		retour.add(new MessageScenarioMqttRest("1111", "nom1", "cat1"));
		retour.add(new MessageScenarioMqttRest("2222", "nom2", "cat2"));
		retour.add(new MessageScenarioMqttRest("3333", "nom3", "cat3"));

		return retour;
	}

}
