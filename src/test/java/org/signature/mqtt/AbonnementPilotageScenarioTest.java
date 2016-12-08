package org.signature.mqtt;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.signature.mqttRest.objetsPartages.scenario.MessageActivationScenarioMqttRest;
import org.signature.mqttRest.objetsPartages.scenario.MessageScenarioMqttRest;
import org.signature.mqttRest.services.mqtt.AbonnementMqtt;
import org.signature.mqttRest.services.mqtt.GestionnaireBrokerMqtt;
import org.signature.mqttRest.services.mqtt.IListenerMessageMqtt;
import org.signature.mqttRest.services.mqtt.ITopicMqtt.Topic;
import org.signature.mqttRest.services.mqtt.PublicationMqtt;

public class AbonnementPilotageScenarioTest {

	private final static String HOST = "localhost";
	private final static int PORT = 8866;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		// Démarrage du broker de message
		GestionnaireBrokerMqtt.getInstance().startBroker(PORT);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		// Arrêt du broker
		GestionnaireBrokerMqtt.getInstance().stopBroker();
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testPilotageScenario() {
		AtomicInteger fin = new AtomicInteger(0);

		IListenerMessageMqtt listener = (messages, topic) -> {
			try {
				assertEquals("Taille incorrecte", 1, messages.size());
				assertEquals("Topic incorrect", Topic.PILOTAGE_SCENARIO, topic);
				assertEquals("Id expediteur incorrect", "ab",
						((MessageActivationScenarioMqttRest) messages.get(0)).getIdentifiantExpediteur());
				assertEquals("Reference message incorrect", "cd",
						((MessageActivationScenarioMqttRest) messages.get(0)).getReferenceMessage());
				assertEquals("Id scénario incorrect", "12",
						((MessageActivationScenarioMqttRest) messages.get(0)).getScenario().getIdentifiant());
				assertEquals("Nom scénario incorrect", "scen1",
						((MessageActivationScenarioMqttRest) messages.get(0)).getScenario().getNom());
				assertEquals("Catégorie scénario incorrect", "cat1",
						((MessageActivationScenarioMqttRest) messages.get(0)).getScenario().getCategorie());
			} catch (Throwable t) {
				fin.set(1);
				t.printStackTrace();
				return;
			}

			fin.set(2);
		};

		// Abonnement topic pilotage d'un scénario
		AbonnementMqtt abo = new AbonnementMqtt(listener, Topic.PILOTAGE_SCENARIO, HOST, PORT);

		List<MessageActivationScenarioMqttRest> liste = new ArrayList<>();
		
		MessageScenarioMqttRest msgScen = new MessageScenarioMqttRest("12", "scen1", "cat1");
		
		MessageActivationScenarioMqttRest msg = new MessageActivationScenarioMqttRest("ab", "cd", msgScen);
		liste.add(msg);

		PublicationMqtt.publicationMessages(liste, HOST, PORT, Topic.PILOTAGE_SCENARIO);

		// Attente fin de traitement
		while (fin.get() == 0) {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		// Déconnexion
		abo.deconnexionListener();

		// Test erreur
		assertEquals("ERREUR TEST", 2, fin.get());
	}

}
