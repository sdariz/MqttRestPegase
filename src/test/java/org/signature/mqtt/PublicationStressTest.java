package org.signature.mqtt;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.signature.mqttRest.objetsPartages.etatEtPilotage.MessageEtatAffichageMqttRest;
import org.signature.mqttRest.objetsPartages.etatEtPilotage.MessagePmvMqttRest;
import org.signature.mqttRest.services.mqtt.AbonnementMqtt;
import org.signature.mqttRest.services.mqtt.GestionnaireBrokerMqtt;
import org.signature.mqttRest.services.mqtt.IListenerMessageMqtt;
import org.signature.mqttRest.services.mqtt.ITopicMqtt.Topic;
import org.signature.mqttRest.services.mqtt.PublicationMqtt;

public class PublicationStressTest {

	private final static String HOST = "localhost";
	private final static int PORT = 8866;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		// D�marrage du broker de message
		GestionnaireBrokerMqtt.getInstance().startBroker(PORT, true);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		// Arr�t du broker
		GestionnaireBrokerMqtt.getInstance().stopBroker();
	}

	@Test
	public void testPublicationMessagesParalleles() {
		int nombre = 100;
		AtomicInteger atomicInt = new AtomicInteger(0);

		IListenerMessageMqtt listener = (messages, topic) -> {
			atomicInt.incrementAndGet();
		};

		new AbonnementMqtt(listener, Arrays.asList(Topic.ETAT_AFFICHAGE_EQUIPEMENT), HOST, PORT);

		for (int i = 0; i < nombre; i++) {
			new Thread(() -> {
				// Pose 100 millisecondes, pour laisser tous les threads se
				// cr�er avant de publier
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				// Message �tat d'affichage
				MessageEtatAffichageMqttRest msg1 = new MessageEtatAffichageMqttRest("ab", "cd");
				msg1.setMessageEquipement(new MessagePmvMqttRest("1111"));
				try {
					PublicationMqtt.publicationMessage(msg1, HOST, PORT, Topic.ETAT_AFFICHAGE_EQUIPEMENT);
				} catch (Exception e2) {
					fail("Erreur publication");
				}
			}).start();
		}

		// Attente fin des thread
		int cpt = 0;
		while (atomicInt.get() < nombre && (cpt++) < 100) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		assertEquals("Perte de messages", nombre, atomicInt.get());
	}

	@Test
	public void testReceptionMessagesParalleles() {
		int nombre = 100;
		AtomicInteger atomicInt = new AtomicInteger(0);

		for (int i = 0; i < nombre; i++) {
			IListenerMessageMqtt listener = (messages, topic) -> {
				atomicInt.incrementAndGet();
			};

			new AbonnementMqtt(listener, Arrays.asList(Topic.ETAT_AFFICHAGE_EQUIPEMENT), HOST, PORT);
		}

		// Message �tat d'affichage
		MessageEtatAffichageMqttRest msg = new MessageEtatAffichageMqttRest("ab", "cd");
		msg.setMessageEquipement(new MessagePmvMqttRest("1111"));
		try {
			PublicationMqtt.publicationMessage(msg, HOST, PORT, Topic.ETAT_AFFICHAGE_EQUIPEMENT);
		} catch (Exception e2) {
			fail("Erreur publication");
		}
		
		int cpt = 0;
		while (atomicInt.get() < nombre && cpt++ < 100) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		assertEquals("Perte de messages", nombre, atomicInt.get());
	}

}