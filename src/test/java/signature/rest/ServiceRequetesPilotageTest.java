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

import signature.mqttRest.objetsPartages.etatEtPilotage.IMessageAffichageEquipementMqttRest;
import signature.mqttRest.objetsPartages.etatEtPilotage.MessageBarriereMqttRest;
import signature.mqttRest.objetsPartages.etatEtPilotage.MessageModuleMqttRest;
import signature.mqttRest.objetsPartages.etatEtPilotage.MessagePmvMqttRest;
import signature.mqttRest.objetsPartages.etatEtPilotage.MessagePpadMqttRest;
import signature.mqttRest.objetsPartages.etatEtPilotage.IMessageAffichageEquipementMqttRest.TypeEquipement;
import signature.mqttRest.objetsPartages.etatEtPilotage.IMessageAffichageEquipementMqttRest.TypeMessage;
import signature.mqttRest.objetsPartages.scenario.MessageScenarioMqttRest;
import signature.mqttRest.objetsPartages.scenario.OrdonnanceurMessagesScenarioMqttRest;
import signature.mqttRest.services.rest.client.InterrogationServeurHttpRest;
import signature.mqttRest.services.rest.serveur.ServeurHttpRest;
import signature.mqttRest.services.rest.serveur.TraitementRequetesRestAdapteur;

public class ServiceRequetesPilotageTest {

	private static ServeurHttpRest serveurHttpRest;
	private static TraitementRequetesPilotageScenario traitementsRequetesRest;

	private final static String HOST = "localhost";
	private final static int PORT = 1122;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		traitementsRequetesRest = new TraitementRequetesPilotageScenario();

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
	 * Test de pilotage d'un scénario, à partir de son identifiant
	 */
	@Test
	public void testPilotageScenarioParId() {
		traitementsRequetesRest.traitement = 0;

		InterrogationServeurHttpRest.requetePilotageScenario(HOST, PORT, "ab", "cd", "1111");

		// Sortie en erreur
		assertEquals("ERREUR TEST", 1, traitementsRequetesRest.traitement);
	}

	/**
	 * Test de pilotage d'un scénario temporaire
	 */
	@Test
	public void testPilotageScenarioTemporaire() {
		traitementsRequetesRest.traitement = 0;

		MessageScenarioMqttRest scenario = new MessageScenarioMqttRest("", "nom1", "cat1");
		scenario.setOrdonnanceurs(Arrays.asList(new OrdonnanceurMessagesScenarioMqttRest(1, 10),
				new OrdonnanceurMessagesScenarioMqttRest(2, 20)));

		InterrogationServeurHttpRest.requetePilotageScenario(HOST, PORT, "ab", "cd", scenario);

		// Sortie en erreur
		assertEquals("ERREUR TEST", 1, traitementsRequetesRest.traitement);
	}

	/**
	 * Test de pilotage de messages dans un scénario
	 */
	@Test
	public void testPilotageMessagesScenario() {
		traitementsRequetesRest.traitement = 0;

		List<IMessageAffichageEquipementMqttRest> messages = new ArrayList<>();
		messages.add(new MessagePmvMqttRest());
		messages.add(new MessagePpadMqttRest());
		messages.add(new MessageBarriereMqttRest());

		InterrogationServeurHttpRest.requetePilotageScenario(HOST, PORT, "ab", "cd", "1111", messages);

		// Sortie en erreur
		assertEquals("ERREUR TEST", 1, traitementsRequetesRest.traitement);
	}

	/**
	 * Test de pilotage d'un PMV
	 */
	@Test
	public void testPilotagePmv() {
		traitementsRequetesRest.traitement = 0;

		MessagePmvMqttRest message = new MessagePmvMqttRest();
		message.setTypeMessage(TypeMessage.NEUTRE);
		message.setMessagesLignes(Arrays.asList(new MessageModuleMqttRest(), new MessageModuleMqttRest()));

		InterrogationServeurHttpRest.requetePilotagePmv(HOST, PORT, "ab", "cd", "1111", message);

		// Sortie en erreur
		assertEquals("ERREUR TEST", 1, traitementsRequetesRest.traitement);
	}

}

class TraitementRequetesPilotageScenario extends TraitementRequetesRestAdapteur {
	protected int traitement = 0;

	@Override
	public void traiteDemandePilotageScenario(String pIdentifiantExpediteur, String pReferenceCommande,
			String pIdScenario) {
		try {
			assertEquals("Id expediteur incorrect", "ab", pIdentifiantExpediteur);
			assertEquals("Id commande incorrect", "cd", pReferenceCommande);
			assertEquals("Id scénario incorrect", "1111", pIdScenario);
		} catch (Throwable t) {
			t.printStackTrace();
			traitement = 2;
			return;
		}

		traitement = 1;
	}

	@Override
	public void traiteDemandePilotageScenario(String pIdentifiantExpediteur, String pReferenceCommande,
			MessageScenarioMqttRest pScenarioTemporaire) {
		try {
			assertEquals("Id expediteur incorrect", "ab", pIdentifiantExpediteur);
			assertEquals("Id commande incorrect", "cd", pReferenceCommande);
			assertNotNull("Scénario null", pScenarioTemporaire);

			assertEquals("Id scénario non vide", "", pScenarioTemporaire.getIdentifiant());
			assertEquals("Nom incorrect", "nom1", pScenarioTemporaire.getNom());
			assertEquals("Catégorie incorrect", "cat1", pScenarioTemporaire.getCategorie());
			assertEquals("Nombre ordonnanceurs incorrect", 2, pScenarioTemporaire.getOrdonnanceurs().size());

			assertEquals("Ordonnanceur 1 ordre incorrect", 1,
					pScenarioTemporaire.getOrdonnanceurs().get(0).getNumeroOrdre());
			assertEquals("Ordonnanceur 1 temps incorrect", 10,
					pScenarioTemporaire.getOrdonnanceurs().get(0).getTempsAvantPilotage());
			assertEquals("Ordonnanceur 2 ordre incorrect", 2,
					pScenarioTemporaire.getOrdonnanceurs().get(1).getNumeroOrdre());
			assertEquals("Ordonnanceur 2 temps incorrect", 20,
					pScenarioTemporaire.getOrdonnanceurs().get(1).getTempsAvantPilotage());
		} catch (Throwable t) {
			t.printStackTrace();
			traitement = 2;
			return;
		}

		traitement = 1;
	}

	@Override
	public void traiteDemandePilotageScenario(String pIdentifiantExpediteur, String pReferenceCommande,
			String pIdScenario, List<IMessageAffichageEquipementMqttRest> pMessages) {
		try {
			assertEquals("Id expediteur incorrect", "ab", pIdentifiantExpediteur);
			assertEquals("Id commande incorrect", "cd", pReferenceCommande);
			assertEquals("Id scénario incorrect", "1111", pIdScenario);
			assertEquals("Nombre messages incorrect", 3, pMessages.size());
			assertEquals("Type PMV incorrect", TypeEquipement.PMV, pMessages.get(0).getTypeEquipement());
			assertEquals("Type PPAD incorrect", TypeEquipement.PPAD, pMessages.get(1).getTypeEquipement());
			assertEquals("Type PPAD incorrect", TypeEquipement.BARRIERE, pMessages.get(2).getTypeEquipement());

		} catch (Throwable t) {
			t.printStackTrace();
			traitement = 2;
			return;
		}

		traitement = 1;
	}
	
	@Override
	public void traiteDemandePilotagePmv(String pIdentifiantExpediteur, String pReferenceCommande,
			String pIdEquipement, MessagePmvMqttRest pMessageAPiloter) {
		try {
			assertEquals("Id expediteur incorrect", "ab", pIdentifiantExpediteur);
			assertEquals("Id commande incorrect", "cd", pReferenceCommande);
			assertNotNull("Message null", pMessageAPiloter);
			
			assertEquals("Nombre lignes PMV incorrect", 2, pMessageAPiloter.getNombreLignes());
		} catch (Throwable t) {
			t.printStackTrace();
			traitement = 2;
			return;
		}

		traitement = 1;
	}

}
