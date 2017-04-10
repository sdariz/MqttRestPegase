package org.signature.mqtt;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
		// Démarrage du broker de message
		GestionnaireBrokerMqtt.getInstance().startBroker(PORT, true);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		// Arrêt du broker
		GestionnaireBrokerMqtt.getInstance().stopBroker();
	}

	// @Test
	public void testPublicationMessagesParalleles() {
		int nombre = 100;
		AtomicInteger atomicInt = new AtomicInteger(0);

		IListenerMessageMqtt listener = (messages, topic) -> {
			atomicInt.incrementAndGet();
		};

		AbonnementMqtt abo = new AbonnementMqtt(listener, Arrays.asList(Topic.ETAT_AFFICHAGE_EQUIPEMENT), HOST, PORT);

		for (int i = 0; i < nombre; i++) {
			new Thread(() -> {
				// Pose 100 millisecondes, pour laisser tous les threads se
				// créer avant de publier
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				// Message état d'affichage
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

		abo.deconnexionListener();

		assertEquals("Perte de messages", nombre, atomicInt.get());
	}

	// @Test
	public void testReceptionMessagesParalleles() {
		int nombre = 100;
		AtomicInteger atomicInt = new AtomicInteger(0);

		List<AbonnementMqtt> abos = new ArrayList<>();

		for (int i = 0; i < nombre; i++) {
			IListenerMessageMqtt listener = (messages, topic) -> {
				atomicInt.incrementAndGet();
			};

			abos.add(new AbonnementMqtt(listener, Arrays.asList(Topic.ETAT_AFFICHAGE_EQUIPEMENT), HOST, PORT));
		}

		// Message état d'affichage
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

		abos.forEach(a -> a.deconnexionListener());

		assertEquals("Perte de messages", nombre, atomicInt.get());
	}

	@Test
	public void testEmissionReception() {
		int nbSuscribers = 50;
		int nbPublications = 100;
		AtomicInteger atomicInt = new AtomicInteger(0);

		List<AbonnementMqtt> abos = new ArrayList<>();

		for (int i = 0; i < nbSuscribers; i++) {
			IListenerMessageMqtt listener = (messages, topic) -> {
				System.out.println("reception");
				atomicInt.incrementAndGet();
			};

			abos.add(new AbonnementMqtt(listener, Arrays.asList(Topic.ETAT_AFFICHAGE_EQUIPEMENT), HOST, PORT));
		}

		for (int i = 0; i < nbPublications; i++) {
			for (int j = 0; j < nbSuscribers; j++) {
				new Thread(() -> {
					MessageEtatAffichageMqttRest msg = new MessageEtatAffichageMqttRest("ab", "cd");
					msg.setMessageEquipement(new MessagePmvMqttRest("1111"));
					try {
						PublicationMqtt.publicationMessage(msg, HOST, PORT, Topic.ETAT_AFFICHAGE_EQUIPEMENT);
					} catch (Exception e2) {
						fail("Erreur publication");
					}
				}).start();
			}
			
			System.out.println("SLEEP.....................;");
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			System.out.println(" FIN SLEEP.....................;");
		}
		
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(atomicInt.get());

		assertEquals("Perte de messages", nbSuscribers * nbSuscribers  * nbPublications, atomicInt.get());
	}

}
