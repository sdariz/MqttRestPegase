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
import org.signature.mqttRest.objetsPartages.informationPegase.MessageModificationPegaseMqttRest;
import org.signature.mqttRest.objetsPartages.informationPegase.MessageModificationPegaseMqttRest.TypeModification;
import org.signature.mqttRest.services.mqtt.AbonnementMqtt;
import org.signature.mqttRest.services.mqtt.GestionnaireBrokerMqtt;
import org.signature.mqttRest.services.mqtt.IListenerMessageMqtt;
import org.signature.mqttRest.services.mqtt.ITopicMqtt.Topic;
import org.signature.mqttRest.services.mqtt.PublicationMqtt;

public class AbonnementModificationPegaseTest {

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
	public void testModificationPegaseArretPegase() {
		testModification(TypeModification.ARRET_APPLICATION_PEGASE);
	}
	
	@Test
	public void testModificationPegaseModificationBibliothequePmv() {
		testModification(TypeModification.MODIFICATION_LISTE_MESSAGES_BIBLIOTHEQUE_PMV);
	}
	
	@Test
	public void testModificationPegaseListeScenarios() {
		testModification(TypeModification.MODIFICATION_LISTE_SCENARIOS);
	}
	
	@Test
	public void testModificationPegaseListeUtilisateurs() {
		testModification(TypeModification.MODIFICATION_LISTE_UTILISATEURS);
	}
	@Test
	public void testModificationPegaseProprietesEquipements() {
		testModification(TypeModification.MODIFICATION_PROPRIETES_EQUIPEMENTS);
	}
	
	private void testModification(TypeModification pType) {
		AtomicInteger fin = new AtomicInteger(0);

		IListenerMessageMqtt listener = (messages, topic) -> {
			try {
				assertEquals("Taille incorrecte", 1, messages.size());
				assertEquals("Topic incorrect", Topic.MODIFICATION_PEGASE, topic);
				assertEquals("Id expediteur incorrect", "ab",
						((MessageModificationPegaseMqttRest) messages.get(0)).getIdentifiantExpediteur());
				assertEquals("Reference message incorrect", "cd",
						((MessageModificationPegaseMqttRest) messages.get(0)).getReferenceMessage());
				assertEquals("Type modification incorrect", pType,
						((MessageModificationPegaseMqttRest) messages.get(0)).getTypeModification());
			} catch (Throwable t) {
				fin.set(1);
				t.printStackTrace();
				return;
			}

			fin.set(2);
		};

		// Abonnement topic modification de Pegase
		AbonnementMqtt abo = new AbonnementMqtt(listener, Topic.MODIFICATION_PEGASE, HOST, PORT);

		List<MessageModificationPegaseMqttRest> liste = new ArrayList<>();
		
		MessageModificationPegaseMqttRest msg = new MessageModificationPegaseMqttRest("ab", "cd", pType);
		liste.add(msg);

		PublicationMqtt.publicationMessages(liste, HOST, PORT, Topic.MODIFICATION_PEGASE);

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
