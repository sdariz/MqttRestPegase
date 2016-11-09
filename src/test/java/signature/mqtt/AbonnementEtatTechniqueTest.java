package signature.mqtt;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import signature.mqttRest.objetsPartages.etatEtPilotage.MessageAlarmeMqttRest;
import signature.mqttRest.objetsPartages.etatEtPilotage.MessageEtatTechniqueMqttRest;
import signature.mqttRest.services.mqtt.AbonnementMqtt;
import signature.mqttRest.services.mqtt.BrokerMqtt;
import signature.mqttRest.services.mqtt.IListenerMessageMqtt;
import signature.mqttRest.services.mqtt.ITopicMqtt.Topic;
import signature.mqttRest.services.mqtt.PublicationMqtt;

public class AbonnementEtatTechniqueTest {
	private static BrokerMqtt broker;
	private final static String HOST = "localhost";
	private final static int PORT = 8866;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		// Démarrage du broker de message
		broker = new BrokerMqtt(PORT);
		broker.startBroker();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		// Arrêt du broker
		broker.stopBroker();
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
	public void testReceptionEtatTechniqueUnEquipement() {
		AtomicInteger fin = new AtomicInteger(0);

		IListenerMessageMqtt listener = (messages, topic) -> {
			try {
				assertEquals("Taille incorrecte", 1, messages.size());
				assertEquals("Topic incorrect", Topic.ETAT_TECHNIQUE_EQUIPEMENT, topic);
				assertEquals("Id equipment incorrect", "1122",
						((MessageEtatTechniqueMqttRest) messages.get(0)).getIdentifiantEquipement());
				assertEquals("Nombre alarmes incorrect", 2,
						((MessageEtatTechniqueMqttRest) messages.get(0)).getAlarmes().size());
				assertEquals("Libellé incorrect", "Alarme 1",
						((MessageEtatTechniqueMqttRest) messages.get(0)).getAlarmes().get(0).getLibelle());
				assertEquals("Libellé incorrect", "Alarme 2",
						((MessageEtatTechniqueMqttRest) messages.get(0)).getAlarmes().get(1).getLibelle());

			} catch (Throwable t) {
				fin.set(1);
				t.printStackTrace();
				return;
			}

			fin.set(2);
		};

		// Abonnement topic état technique
		AbonnementMqtt abo = new AbonnementMqtt(listener, Topic.ETAT_TECHNIQUE_EQUIPEMENT, HOST, PORT);

		// Post d'un nouvel état technique
		MessageEtatTechniqueMqttRest msg = new MessageEtatTechniqueMqttRest("1122", "ab", "cd");
		List<MessageAlarmeMqttRest> alarmes = new ArrayList<>();
		msg.setAlarmes(alarmes);

		MessageAlarmeMqttRest al = new MessageAlarmeMqttRest();
		al.setLibelle("Alarme 1");
		alarmes.add(al);

		al = new MessageAlarmeMqttRest();
		al.setLibelle("Alarme 2");
		alarmes.add(al);

		PublicationMqtt.publicationMessage(msg, HOST, PORT, Topic.ETAT_TECHNIQUE_EQUIPEMENT);

		// Attente fin de traitement
		while (fin.get() == 0) {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		// Déconnection
		abo.deconnexionListener();

		// Test errreur
		assertEquals("ERREUR TEST", 2, fin.get());
	}

	/**
	 * Test reception 2 messages
	 */
	@Test
	public void testReceptionEtatTechniqueDeuxEquipement() {
		AtomicInteger fin = new AtomicInteger(0);

		IListenerMessageMqtt listener = (messages, topic) -> {
			try {
				assertEquals("Taille incorrecte", 2, messages.size());
				assertEquals("Topic incorrect", Topic.ETAT_TECHNIQUE_EQUIPEMENT, topic);
				assertEquals("Id equipment incorrect", "1111",
						((MessageEtatTechniqueMqttRest) messages.get(0)).getIdentifiantEquipement());
				assertEquals("Id equipment incorrect", "2222",
						((MessageEtatTechniqueMqttRest) messages.get(1)).getIdentifiantEquipement());
			} catch (Throwable t) {
				fin.set(1);
				t.printStackTrace();
				return;
			}

			fin.set(2);
		};

		// Abonnement topic état affichage
		AbonnementMqtt abo = new AbonnementMqtt(listener, Topic.ETAT_TECHNIQUE_EQUIPEMENT, HOST, PORT);

		List<MessageEtatTechniqueMqttRest> liste = new ArrayList<>();

		// Post de l'état de deux équipements
		MessageEtatTechniqueMqttRest msg = new MessageEtatTechniqueMqttRest("1111", "ab", "cd");
		liste.add(msg);

		msg = new MessageEtatTechniqueMqttRest("2222", "ab", "cd");
		liste.add(msg);

		PublicationMqtt.publicationMessages(liste, HOST, PORT, Topic.ETAT_TECHNIQUE_EQUIPEMENT);

		// Attente fin de traitement
		while (fin.get() == 0) {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		// Déconnection
		abo.deconnexionListener();

		// Test errreur
		assertEquals("ERREUR TEST", 2, fin.get());
	}

}
