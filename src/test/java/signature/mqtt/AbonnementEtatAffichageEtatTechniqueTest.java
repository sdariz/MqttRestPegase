package signature.mqtt;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import signature.mqttRest.objetsPartages.etatEtPilotage.MessageEtatAffichageMqttRest;
import signature.mqttRest.objetsPartages.etatEtPilotage.MessageEtatTechniqueMqttRest;
import signature.mqttRest.services.mqtt.AbonnementMqtt;
import signature.mqttRest.services.mqtt.BrokerMqtt;
import signature.mqttRest.services.mqtt.IListenerMessageMqtt;
import signature.mqttRest.services.mqtt.ITopicMqtt.Topic;
import signature.mqttRest.services.mqtt.PublicationMqtt;

/**
 * Tests sur la publication et la reception de messages d'état d'affichage et
 * d'état techniqueO
 * 
 * @author SDARIZCUREN
 *
 */
public class AbonnementEtatAffichageEtatTechniqueTest {
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
	 * Test reception état affichage + état technique
	 */
	@Test
	public void testReceptionEtatAffichageEtTechnique() {
		AtomicInteger fin = new AtomicInteger(0);

		IListenerMessageMqtt listener = (messages, topic) -> {
			try {
				assertEquals("Taille incorrecte", 1, messages.size());
				if(Topic.ETAT_AFFICHAGE_EQUIPEMENT.equals(topic)) {
					assertEquals("Id equipment incorrect", "1111",
							((MessageEtatAffichageMqttRest) messages.get(0)).getIdentifiantEquipement());
					fin.set(2);
				}
				else if(Topic.ETAT_TECHNIQUE_EQUIPEMENT.equals(topic)) {
					assertEquals("Id equipment incorrect", "2222",
							((MessageEtatTechniqueMqttRest) messages.get(0)).getIdentifiantEquipement());
					fin.set(3);
				}
				else {
					fin.set(4);
				}
				
			} catch (Throwable t) {
				fin.set(1);
				t.printStackTrace();
				return;
			}
		};

		// Abonnement topic état affichage + état technique
		AbonnementMqtt abo = new AbonnementMqtt(listener,
				Arrays.asList(Topic.ETAT_AFFICHAGE_EQUIPEMENT, Topic.ETAT_TECHNIQUE_EQUIPEMENT), HOST, PORT);

		// Message état d'affichage
		MessageEtatAffichageMqttRest msg1 = new MessageEtatAffichageMqttRest("1111", "ab", "cd");
		PublicationMqtt.publicationMessage(msg1, HOST, PORT, Topic.ETAT_AFFICHAGE_EQUIPEMENT);

		// Attente fin de traitement
		while (fin.get() == 0) {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		// Test erreur
		assertEquals("ERREUR TEST", 2, fin.get());

		// Message état technique
		fin.set(0);
		MessageEtatTechniqueMqttRest msg2 = new MessageEtatTechniqueMqttRest("2222", "ab", "cd");
		
		PublicationMqtt.publicationMessage(msg2, HOST, PORT, Topic.ETAT_TECHNIQUE_EQUIPEMENT);

		// Attente fin de traitement
		while (fin.get() == 0) {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		// Déconnexion
		abo.deconnexionListener();

		// Test erreur
		assertEquals("ERREUR TEST", 3, fin.get());
	}
}
