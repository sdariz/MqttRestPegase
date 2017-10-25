package org.signature.rest;

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

import org.signature.mqttRest.objetsPartages.etatEtPilotage.IMessageAffichageEquipementMqttRest;
import org.signature.mqttRest.objetsPartages.etatEtPilotage.MessageBarriereMqttRest;
import org.signature.mqttRest.objetsPartages.etatEtPilotage.MessageBraMqttRest;
import org.signature.mqttRest.objetsPartages.etatEtPilotage.MessageModuleMqttRest;
import org.signature.mqttRest.objetsPartages.etatEtPilotage.MessagePictogrammeMqttRest;
import org.signature.mqttRest.objetsPartages.etatEtPilotage.MessagePmvMqttRest;
import org.signature.mqttRest.objetsPartages.etatEtPilotage.MessagePpadMqttRest;
import org.signature.mqttRest.objetsPartages.etatEtPilotage.MessagePplmvMqttRest;
import org.signature.mqttRest.objetsPartages.etatEtPilotage.MessagePrismeMqttRest;
import org.signature.mqttRest.objetsPartages.etatEtPilotage.MessageR24MqttRest;
import org.signature.mqttRest.objetsPartages.etatEtPilotage.IMessageAffichageEquipementMqttRest.TypeEquipement;
import org.signature.mqttRest.objetsPartages.etatEtPilotage.IMessageAffichageEquipementMqttRest.TypeMessage;
import org.signature.mqttRest.objetsPartages.scenario.MessageScenarioMqttRest;
import org.signature.mqttRest.objetsPartages.scenario.OrdonnanceurMessagesScenarioMqttRest;
import org.signature.mqttRest.services.rest.client.InterrogationServeurHttpRest;
import org.signature.mqttRest.services.rest.serveur.ServeurHttpRest;
import org.signature.mqttRest.services.rest.serveur.TraitementRequetesRestAdapteur;

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

		new InterrogationServeurHttpRest().requetePilotageScenario(HOST, PORT, "ab", "cd", "1111");

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

		new InterrogationServeurHttpRest().requetePilotageScenario(HOST, PORT, "ab", "cd", scenario);

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

		new InterrogationServeurHttpRest().requetePilotageScenario(HOST, PORT, "ab", "cd", "1111", messages);

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

		new InterrogationServeurHttpRest().requetePilotagePmv(HOST, PORT, "ab", "cd", "1111", message);

		// Sortie en erreur
		assertEquals("ERREUR TEST", 1, traitementsRequetesRest.traitement);
	}

	/**
	 * Test de pilotage d'un PPLMV
	 */
	@Test
	public void testPilotagePplmv() {
		traitementsRequetesRest.traitement = 0;

		MessagePplmvMqttRest message = new MessagePplmvMqttRest();
		message.setTypeMessage(TypeMessage.NEUTRE);
		MessageModuleMqttRest mod = new MessageModuleMqttRest();
		mod.setMessagesParPage(Arrays.asList("msg 1"));
		message.setMessagePanonceauHaut(mod);

		new InterrogationServeurHttpRest().requetePilotagePplmv(HOST, PORT, "ab", "cd", "1111", message);

		// Sortie en erreur
		assertEquals("ERREUR TEST", 1, traitementsRequetesRest.traitement);
	}

	/**
	 * Test de pilotage d'un PPAD
	 */
	@Test
	public void testPilotagePpad() {
		traitementsRequetesRest.traitement = 0;

		MessagePpadMqttRest message = new MessagePpadMqttRest();
		message.setTypeMessage(TypeMessage.ETEINT);
		MessageModuleMqttRest mod = new MessageModuleMqttRest();
		mod.setMessagesParPage(Arrays.asList("msg 1"));
		message.setMessagesModuleUnique(mod);

		new InterrogationServeurHttpRest().requetePilotagePpad(HOST, PORT, "ab", "cd", "1111", message);

		// Sortie en erreur
		assertEquals("ERREUR TEST", 1, traitementsRequetesRest.traitement);
	}

	/**
	 * Test de pilotage d'un Pictogramme
	 */
	@Test
	public void testPilotagePictogramme() {
		traitementsRequetesRest.traitement = 0;

		MessagePictogrammeMqttRest message = new MessagePictogrammeMqttRest();
		message.setTypeMessage(TypeMessage.ETEINT);
		MessageModuleMqttRest mod = new MessageModuleMqttRest();
		mod.setMessagesParPage(Arrays.asList("msg 1"));
		message.setMessagesModuleUnique(mod);

		new InterrogationServeurHttpRest().requetePilotagePictogramme(HOST, PORT, "ab", "cd", "1111", message);

		// Sortie en erreur
		assertEquals("ERREUR TEST", 1, traitementsRequetesRest.traitement);
	}

	/**
	 * Test de pilotage d'un Feu R24
	 */
	@Test
	public void testPilotageR24() {
		traitementsRequetesRest.traitement = 0;

		MessageR24MqttRest message = new MessageR24MqttRest();
		message.setTypeMessage(TypeMessage.EXPLOITATION);
		MessageModuleMqttRest mod = new MessageModuleMqttRest();
		mod.setMessagesParPage(Arrays.asList("msg 1"));
		message.setMessagesModuleUnique(mod);

		new InterrogationServeurHttpRest().requetePilotageR24(HOST, PORT, "ab", "cd", "1111", message);

		// Sortie en erreur
		assertEquals("ERREUR TEST", 1, traitementsRequetesRest.traitement);
	}

	/**
	 * Test de pilotage d'un Prisme
	 */
	@Test
	public void testPilotagePrisme() {
		traitementsRequetesRest.traitement = 0;

		MessagePrismeMqttRest message = new MessagePrismeMqttRest();
		message.setTypeMessage(TypeMessage.EXPLOITATION);
		MessageModuleMqttRest mod = new MessageModuleMqttRest();
		mod.setMessagesParPage(Arrays.asList("msg 1"));
		message.setMessagesModuleUnique(mod);

		new InterrogationServeurHttpRest().requetePilotagePrisme(HOST, PORT, "ab", "cd", "1111", message);

		// Sortie en erreur
		assertEquals("ERREUR TEST", 1, traitementsRequetesRest.traitement);
	}

	/**
	 * Test de pilotage d'une Barrière
	 */
	@Test
	public void testPilotageBarriere() {
		traitementsRequetesRest.traitement = 0;

		MessageBarriereMqttRest message = new MessageBarriereMqttRest();
		message.setTypeMessage(TypeMessage.EXPLOITATION);
		MessageModuleMqttRest mod = new MessageModuleMqttRest();
		mod.setMessagesParPage(Arrays.asList("msg 1"));
		message.setMessagesModuleUnique(mod);

		new InterrogationServeurHttpRest().requetePilotageBarriere(HOST, PORT, "ab", "cd", "1111", message);

		// Sortie en erreur
		assertEquals("ERREUR TEST", 1, traitementsRequetesRest.traitement);
	}

	/**
	 * Test de pilotage d'un Bra
	 */
	@Test
	public void testPilotageBra() {
		traitementsRequetesRest.traitement = 0;

		MessageBraMqttRest message = new MessageBraMqttRest();
		message.setTypeMessage(TypeMessage.EXPLOITATION);
		MessageModuleMqttRest mod = new MessageModuleMqttRest();
		mod.setMessagesParPage(Arrays.asList("msg 1"));
		message.setMessagesModuleUnique(mod);

		new InterrogationServeurHttpRest().requetePilotageBra(HOST, PORT, "ab", "cd", "1111", message);

		// Sortie en erreur
		assertEquals("ERREUR TEST", 1, traitementsRequetesRest.traitement);
	}

	/**
	 * Test de pilotage d'un ensemble de messages
	 */
	@Test
	public void testPilotageMessages() {
		traitementsRequetesRest.traitement = 0;

		List<IMessageAffichageEquipementMqttRest> messages = new ArrayList<>();
		messages.add(new MessagePmvMqttRest("1111"));
		messages.add(new MessagePpadMqttRest("2222"));
		messages.add(new MessageBarriereMqttRest("3333"));

		new InterrogationServeurHttpRest().requetePilotageMessages(HOST, PORT, "ab", "cd", messages);

		// Sortie en erreur
		assertEquals("ERREUR TEST", 1, traitementsRequetesRest.traitement);
	}
	
	/**
	 * Test pilotage en cours sur un équipement
	 */
	@Test
	public void testPilotageEnCoursSurEquipement() {
		traitementsRequetesRest.flagPilotageCours = true;
		
		boolean enCours = new InterrogationServeurHttpRest().requetePilotageEnCours(HOST, PORT, "ab", "cd", "1111");

		assertEquals("Pilotage en cours attendu", true, enCours);
	}
	
	/**
	 * Test pas de pilotage en cours sur un équipement
	 */
	@Test
	public void testPasPilotageEnCoursSurEquipement() {
		traitementsRequetesRest.flagPilotageCours = false;
		
		boolean enCours = new InterrogationServeurHttpRest().requetePilotageEnCours(HOST, PORT, "ab", "cd", "1111");

		assertEquals("Pas de pilotage en cours attendu", false, enCours);
	}
	
	/**
	 * Test pilotage en cours sur un équipement quelconque
	 */
	@Test
	public void testPilotageEnCoursSurEquipementQuelconque() {
		traitementsRequetesRest.flagPilotageCours = true;
		
		boolean enCours = new InterrogationServeurHttpRest().requetePilotageEnCours(HOST, PORT, "ab", "cd");

		assertEquals("Pilotage en cours attendu", true, enCours);
	}
	
	/**
	 * Test pas de pilotage en cours sur un équipement quelconque
	 */
	@Test
	public void testPasPilotageEnCoursSurEquipementQuelconque() {
		traitementsRequetesRest.flagPilotageCours = false;
		
		boolean enCours = new InterrogationServeurHttpRest().requetePilotageEnCours(HOST, PORT, "ab", "cd");

		assertEquals("Pas de pilotage en cours attendu", false, enCours);
	}

}

class TraitementRequetesPilotageScenario extends TraitementRequetesRestAdapteur {
	protected int traitement = 0;
	protected boolean flagPilotageCours = false;

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
	public void traiteDemandePilotagePmv(String pIdentifiantExpediteur, String pReferenceCommande, String pIdEquipement,
			MessagePmvMqttRest pMessageAPiloter) {
		try {
			assertEquals("Id expediteur incorrect", "ab", pIdentifiantExpediteur);
			assertEquals("Id commande incorrect", "cd", pReferenceCommande);
			assertNotNull("Message null", pMessageAPiloter);
			assertEquals("Type message incorrect", TypeMessage.NEUTRE, pMessageAPiloter.getTypeMessage());

			assertEquals("Nombre lignes PMV incorrect", 2, pMessageAPiloter.getNombreLignes());
		} catch (Throwable t) {
			t.printStackTrace();
			traitement = 2;
			return;
		}

		traitement = 1;
	}

	@Override
	public void traiteDemandePilotagePplmv(String pIdentifiantExpediteur, String pReferenceCommande,
			String pIdEquipement, MessagePplmvMqttRest pMessageAPiloter) {
		try {
			assertEquals("Id expediteur incorrect", "ab", pIdentifiantExpediteur);
			assertEquals("Id commande incorrect", "cd", pReferenceCommande);
			assertNotNull("Message null", pMessageAPiloter);
			assertEquals("Type message incorrect", TypeMessage.NEUTRE, pMessageAPiloter.getTypeMessage());

			assertEquals("Nombre messages incorrect", 1,
					pMessageAPiloter.getMessagePanonceauHaut().getMessagesParPage().size());
			assertEquals("Message incorrect", "msg 1",
					pMessageAPiloter.getMessagePanonceauHaut().getMessagesParPage().get(0));
		} catch (Throwable t) {
			t.printStackTrace();
			traitement = 2;
			return;
		}

		traitement = 1;
	}

	@Override
	public void traiteDemandePilotagePpad(String pIdentifiantExpediteur, String pReferenceCommande,
			String pIdEquipement, MessagePpadMqttRest pMessageAPiloter) {
		try {
			assertEquals("Id expediteur incorrect", "ab", pIdentifiantExpediteur);
			assertEquals("Id commande incorrect", "cd", pReferenceCommande);
			assertNotNull("Message null", pMessageAPiloter);
			assertEquals("Type message incorrect", TypeMessage.ETEINT, pMessageAPiloter.getTypeMessage());

			assertEquals("Nombre messages incorrect", 1,
					pMessageAPiloter.getMessagesModuleUnique().getMessagesParPage().size());
			assertEquals("Message incorrect", "msg 1",
					pMessageAPiloter.getMessagesModuleUnique().getMessagesParPage().get(0));
		} catch (Throwable t) {
			t.printStackTrace();
			traitement = 2;
			return;
		}

		traitement = 1;
	}

	@Override
	public void traiteDemandePilotagePictogramme(String pIdentifiantExpediteur, String pReferenceCommande,
			String pIdEquipement, MessagePictogrammeMqttRest pMessageAPiloter) {
		try {
			assertEquals("Id expediteur incorrect", "ab", pIdentifiantExpediteur);
			assertEquals("Id commande incorrect", "cd", pReferenceCommande);
			assertNotNull("Message null", pMessageAPiloter);
			assertEquals("Type message incorrect", TypeMessage.ETEINT, pMessageAPiloter.getTypeMessage());

			assertEquals("Nombre messages incorrect", 1,
					pMessageAPiloter.getMessagesModuleUnique().getMessagesParPage().size());
			assertEquals("Message incorrect", "msg 1",
					pMessageAPiloter.getMessagesModuleUnique().getMessagesParPage().get(0));
		} catch (Throwable t) {
			t.printStackTrace();
			traitement = 2;
			return;
		}

		traitement = 1;
	}

	@Override
	public void traiteDemandePilotageR24(String pIdentifiantExpediteur, String pReferenceCommande, String pIdEquipement,
			MessageR24MqttRest pMessageAPiloter) {
		try {
			assertEquals("Id expediteur incorrect", "ab", pIdentifiantExpediteur);
			assertEquals("Id commande incorrect", "cd", pReferenceCommande);
			assertNotNull("Message null", pMessageAPiloter);
			assertEquals("Type message incorrect", TypeMessage.EXPLOITATION, pMessageAPiloter.getTypeMessage());

			assertEquals("Nombre messages incorrect", 1,
					pMessageAPiloter.getMessagesModuleUnique().getMessagesParPage().size());
			assertEquals("Message incorrect", "msg 1",
					pMessageAPiloter.getMessagesModuleUnique().getMessagesParPage().get(0));
		} catch (Throwable t) {
			t.printStackTrace();
			traitement = 2;
			return;
		}

		traitement = 1;
	}

	@Override
	public void traiteDemandePilotagePrisme(String pIdentifiantExpediteur, String pReferenceCommande,
			String pIdEquipement, MessagePrismeMqttRest pMessageAPiloter) {
		try {
			assertEquals("Id expediteur incorrect", "ab", pIdentifiantExpediteur);
			assertEquals("Id commande incorrect", "cd", pReferenceCommande);
			assertNotNull("Message null", pMessageAPiloter);
			assertEquals("Type message incorrect", TypeMessage.EXPLOITATION, pMessageAPiloter.getTypeMessage());

			assertEquals("Nombre messages incorrect", 1,
					pMessageAPiloter.getMessagesModuleUnique().getMessagesParPage().size());
			assertEquals("Message incorrect", "msg 1",
					pMessageAPiloter.getMessagesModuleUnique().getMessagesParPage().get(0));
		} catch (Throwable t) {
			t.printStackTrace();
			traitement = 2;
			return;
		}

		traitement = 1;
	}

	@Override
	public void traiteDemandePilotageBarriere(String pIdentifiantExpediteur, String pReferenceCommande,
			String pIdEquipement, MessageBarriereMqttRest pMessageAPiloter) {
		try {
			assertEquals("Id expediteur incorrect", "ab", pIdentifiantExpediteur);
			assertEquals("Id commande incorrect", "cd", pReferenceCommande);
			assertNotNull("Message null", pMessageAPiloter);
			assertEquals("Type message incorrect", TypeMessage.EXPLOITATION, pMessageAPiloter.getTypeMessage());

			assertEquals("Nombre messages incorrect", 1,
					pMessageAPiloter.getMessagesModuleUnique().getMessagesParPage().size());
			assertEquals("Message incorrect", "msg 1",
					pMessageAPiloter.getMessagesModuleUnique().getMessagesParPage().get(0));
		} catch (Throwable t) {
			t.printStackTrace();
			traitement = 2;
			return;
		}

		traitement = 1;
	}

	@Override
	public void traiteDemandePilotageBra(String pIdentifiantExpediteur, String pReferenceCommande, String pIdEquipement,
			MessageBraMqttRest pMessageAPiloter) {
		try {
			assertEquals("Id expediteur incorrect", "ab", pIdentifiantExpediteur);
			assertEquals("Id commande incorrect", "cd", pReferenceCommande);
			assertNotNull("Message null", pMessageAPiloter);
			assertEquals("Type message incorrect", TypeMessage.EXPLOITATION, pMessageAPiloter.getTypeMessage());

			assertEquals("Nombre messages incorrect", 1,
					pMessageAPiloter.getMessagesModuleUnique().getMessagesParPage().size());
			assertEquals("Message incorrect", "msg 1",
					pMessageAPiloter.getMessagesModuleUnique().getMessagesParPage().get(0));
		} catch (Throwable t) {
			t.printStackTrace();
			traitement = 2;
			return;
		}

		traitement = 1;
	}

	@Override
	public void traiteDemandePilotageMessages(String pIdentifiantExpediteur, String pReferenceCommande,
			List<IMessageAffichageEquipementMqttRest> pMessages) {
		try {
			assertEquals("Id expediteur incorrect", "ab", pIdentifiantExpediteur);
			assertEquals("Id commande incorrect", "cd", pReferenceCommande);
			assertEquals("Nombre Messages incorrect", 3, pMessages.size());
			
			assertEquals("Type eqpt message 1 incorrect", TypeEquipement.PMV, pMessages.get(0).getTypeEquipement());
			assertEquals("Id eqpt message 1 incorrect", "1111", pMessages.get(0).getIdentifiantEquipement());
			assertEquals("Type eqpt message 2 incorrect", TypeEquipement.PPAD, pMessages.get(1).getTypeEquipement());
			assertEquals("Id eqpt message 2 incorrect", "2222", pMessages.get(1).getIdentifiantEquipement());
			assertEquals("Type eqpt message 3 incorrect", TypeEquipement.BARRIERE, pMessages.get(2).getTypeEquipement());
			assertEquals("Id eqpt message 3 incorrect", "3333", pMessages.get(2).getIdentifiantEquipement());
		} catch (Throwable t) {
			t.printStackTrace();
			traitement = 2;
			return;
		}

		traitement = 1;
	}
	
	@Override
	public boolean traiteDemandePilotageEnCours(String pIdentifiantExpediteur, String pReferenceCommande,
			String pIdEquipement) {
		assertEquals("Id expediteur incorrect", "ab", pIdentifiantExpediteur);
		assertEquals("Id commande incorrect", "cd", pReferenceCommande);
		assertEquals("Id équipement incorrect", "1111", pIdEquipement);
		
		return flagPilotageCours;
	}
	
	@Override
	public boolean traiteDemandePilotageEnCours(String pIdentifiantExpediteur, String pReferenceCommande) {
		assertEquals("Id expediteur incorrect", "ab", pIdentifiantExpediteur);
		assertEquals("Id commande incorrect", "cd", pReferenceCommande);
		
		return flagPilotageCours;
	}

}
