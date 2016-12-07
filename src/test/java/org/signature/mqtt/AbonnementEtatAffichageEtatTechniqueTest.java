package org.signature.mqtt;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import org.signature.mqttRest.objetsPartages.etatEtPilotage.MessageEtatAffichageMqttRest;
import org.signature.mqttRest.objetsPartages.etatEtPilotage.MessageEtatTechniqueMqttRest;
import org.signature.mqttRest.objetsPartages.etatEtPilotage.MessagePmvMqttRest;
import org.signature.mqttRest.services.mqtt.AbonnementMqtt;
import org.signature.mqttRest.services.mqtt.GestionnaireBrokerMqtt;
import org.signature.mqttRest.services.mqtt.IListenerMessageMqtt;
import org.signature.mqttRest.services.mqtt.ITopicMqtt.Topic;
import org.signature.mqttRest.services.mqtt.PublicationMqtt;

/**
 * Tests sur la publication et la reception de messages d'�tat d'affichage et
 * d'�tat techniqueO
 * 
 * @author SDARIZCUREN
 *
 */
public class AbonnementEtatAffichageEtatTechniqueTest {
	private final static String HOST = "localhost";
	private final static int PORT = 8866;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		// D�marrage du broker de message
		GestionnaireBrokerMqtt.getInstance().startBroker(PORT);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		// Arr�t du broker
		GestionnaireBrokerMqtt.getInstance().stopBroker();
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test reception �tat affichage + �tat technique
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

		// Abonnement topic �tat affichage + �tat technique
		AbonnementMqtt abo = new AbonnementMqtt(listener,
				Arrays.asList(Topic.ETAT_AFFICHAGE_EQUIPEMENT, Topic.ETAT_TECHNIQUE_EQUIPEMENT), HOST, PORT);

		// Message �tat d'affichage
		MessageEtatAffichageMqttRest msg1 = new MessageEtatAffichageMqttRest("ab", "cd");
		msg1.setMessageEquipement(new MessagePmvMqttRest("1111"));
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

		// Message �tat technique
		fin.set(0);
		MessageEtatTechniqueMqttRest msg2 = new MessageEtatTechniqueMqttRest("ab", "cd", "2222");
		
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

		// D�connexion
		abo.deconnexionListener();

		// Test erreur
		assertEquals("ERREUR TEST", 3, fin.get());
	}
}
