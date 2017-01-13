package org.signature.mqtt;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import org.signature.mqttRest.objetsPartages.etatEtPilotage.MessageEtatAffichageMqttRest;
import org.signature.mqttRest.objetsPartages.etatEtPilotage.MessagePmvMqttRest;
import org.signature.mqttRest.services.mqtt.AbonnementMqtt;
import org.signature.mqttRest.services.mqtt.GestionnaireBrokerMqtt;
import org.signature.mqttRest.services.mqtt.IListenerMessageMqtt;
import org.signature.mqttRest.services.mqtt.ITopicMqtt.Topic;
import org.signature.mqttRest.services.mqtt.PublicationMqtt;

/**
 * Tests sur la publication et la reception de messages d'état d'affichage
 * 
 * @author SDARIZCUREN
 *
 */
public class AbonnementEtatAffichageTest {
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

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test reception 1 message
	 */
	@Test
	public void testReceptionEtatAffichageUnEquipement() {
		AtomicInteger fin = new AtomicInteger(0);

		IListenerMessageMqtt listener = (messages, topic) -> {
			try {
				assertEquals("Taille incorrecte", 1, messages.size());
				assertEquals("Topic incorrect", Topic.ETAT_AFFICHAGE_EQUIPEMENT, topic);
				assertEquals("Id equipment incorrect", "1122",
						((MessageEtatAffichageMqttRest) messages.get(0)).getIdentifiantEquipement());
				LocalDateTime ldt = LocalDateTime.of(2016, Month.NOVEMBER, 8, 16, 50, 10);
				Instant ist = ldt.toInstant(ZoneOffset.UTC);
				assertEquals("Horodate incorrect", Date.from(ist),
						((MessageEtatAffichageMqttRest) messages.get(0)).getHorodateGeneration());
				assertNotNull("Message PMV null",
						((MessageEtatAffichageMqttRest) messages.get(0)).getMessageEquipement());
			} catch (Throwable t) {
				fin.set(1);
				t.printStackTrace();
				return;
			}

			fin.set(2);
		};

		// Abonnement topic état affichage
		AbonnementMqtt abo = new AbonnementMqtt(listener, Topic.ETAT_AFFICHAGE_EQUIPEMENT, HOST, PORT);

		// Post d'un nouvel état d'affichage
		MessageEtatAffichageMqttRest msg = new MessageEtatAffichageMqttRest("ab", "cd");
		LocalDateTime ldt = LocalDateTime.of(2016, Month.NOVEMBER, 8, 16, 50, 10);
		Instant ist = ldt.toInstant(ZoneOffset.UTC);
		msg.setHorodateGeneration(Date.from(ist));
		MessagePmvMqttRest msgPmv = new MessagePmvMqttRest("1122");
		msg.setMessageEquipement(msgPmv);

		PublicationMqtt.publicationMessage(msg, HOST, PORT, Topic.ETAT_AFFICHAGE_EQUIPEMENT);

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

	/**
	 * Test reception 2 messages
	 */
	@Test
	public void testReceptionEtatAffichageDeuxEquipement() {
		AtomicInteger fin = new AtomicInteger(0);

		IListenerMessageMqtt listener = (messages, topic) -> {
			try {
				assertEquals("Taille incorrecte", 2, messages.size());
				assertEquals("Topic incorrect", Topic.ETAT_AFFICHAGE_EQUIPEMENT, topic);
				assertEquals("Id equipment incorrect", "1111",
						((MessageEtatAffichageMqttRest) messages.get(0)).getIdentifiantEquipement());
				assertEquals("Id equipment incorrect", "2222",
						((MessageEtatAffichageMqttRest) messages.get(1)).getIdentifiantEquipement());
				LocalDateTime ldt = LocalDateTime.of(2016, Month.NOVEMBER, 8, 16, 50, 10);
				Instant ist = ldt.toInstant(ZoneOffset.UTC);
				assertEquals("Horodate incorrect", Date.from(ist),
						((MessageEtatAffichageMqttRest) messages.get(0)).getHorodateGeneration());
				ldt = LocalDateTime.of(2016, Month.NOVEMBER, 8, 16, 55, 20);
				ist = ldt.toInstant(ZoneOffset.UTC);
				assertEquals("Horodate incorrect", Date.from(ist),
						((MessageEtatAffichageMqttRest) messages.get(1)).getHorodateGeneration());

				assertNotNull("Message PMV null",
						((MessageEtatAffichageMqttRest) messages.get(0)).getMessageEquipement());
				assertNotNull("Message PMV null",
						((MessageEtatAffichageMqttRest) messages.get(1)).getMessageEquipement());
			} catch (Throwable t) {
				fin.set(1);
				t.printStackTrace();
				return;
			}

			fin.set(2);
		};

		// Abonnement topic état affichage
		AbonnementMqtt abo = new AbonnementMqtt(listener, Topic.ETAT_AFFICHAGE_EQUIPEMENT, HOST, PORT);

		List<MessageEtatAffichageMqttRest> liste = new ArrayList<>();

		// Post de l'état de deux équipements
		MessageEtatAffichageMqttRest msg = new MessageEtatAffichageMqttRest("ab", "cd");
		liste.add(msg);

		LocalDateTime ldt = LocalDateTime.of(2016, Month.NOVEMBER, 8, 16, 50, 10);
		Instant ist = ldt.toInstant(ZoneOffset.UTC);
		msg.setHorodateGeneration(Date.from(ist));
		MessagePmvMqttRest msgPmv = new MessagePmvMqttRest("1111");
		msg.setMessageEquipement(msgPmv);

		msg = new MessageEtatAffichageMqttRest("ab", "cd");
		liste.add(msg);

		ldt = LocalDateTime.of(2016, Month.NOVEMBER, 8, 16, 55, 20);
		ist = ldt.toInstant(ZoneOffset.UTC);
		msg.setHorodateGeneration(Date.from(ist));
		msgPmv = new MessagePmvMqttRest("2222");
		msg.setMessageEquipement(msgPmv);

		PublicationMqtt.publicationMessages(liste, HOST, PORT, Topic.ETAT_AFFICHAGE_EQUIPEMENT);

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
