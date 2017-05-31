package org.signature.mqtt;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
//import org.junit.Test;
import org.signature.mqttRest.objetsPartages.etatEtPilotage.MessageEtatAffichageMqttRest;
import org.signature.mqttRest.objetsPartages.etatEtPilotage.MessagePmvMqttRest;
import org.signature.mqttRest.services.mqtt.AbonnementMqtt;
import org.signature.mqttRest.services.mqtt.GestionnaireBrokerMqtt;
import org.signature.mqttRest.services.mqtt.GestionnaireRepertoiresTravail;
import org.signature.mqttRest.services.mqtt.IListenerMessageMqtt;
import org.signature.mqttRest.services.mqtt.ITopicMqtt.Topic;
import org.signature.mqttRest.services.mqtt.PublicationMqtt;

public class PublicationStressTest {

	private final static String HOST = "localhost";
	private final static int PORT = 8866;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		// Purge des messages persistes
		GestionnaireRepertoiresTravail.getInstance().purgeDossierPersistanceClientsUneFoisAuDemarrage();
		
		// Démarrage du broker de message
		GestionnaireBrokerMqtt.getInstance().startBroker(PORT, true);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		// Arrêt du broker
		GestionnaireBrokerMqtt.getInstance().stopBroker();
	}

	// DESACTIVE POUR NE PAS RALENTIR LA COMPIl MAVEN
	// SINON, IL FAUT SUPPRIMER LES TEST DANS LA COMPIL

	@Test
	public void testPublicationMessagesParalleles() {
		testEmissioReception(1, 1000);
	}

	@Test
	public void testReceptionMessagesParalleles() {
		testEmissioReception(10, 1);
	}

	@Test
	public void testEmissionReception() {
		testEmissioReception(10, 1000);
	}
	
	//@Test
	public void testEmissionReceptionBoucle() {
		int cpt = 100;
		int nbAbonnes = 5;
		int nbPublications = 500;
		
		for(int i=0; i<cpt; i++) {
			System.out.println("BOUCLE --------------- " + i);
			testEmissioReception(nbAbonnes, nbPublications);
			
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	// Emission et reception pour x abonnes et y publication.
	private void testEmissioReception(int pSuscribers, int pPublications) {
		Map<Integer, AtomicInteger> atoms = new HashMap<>();
		List<AbonnementMqtt> abos = new ArrayList<>();
		
		for (int i = 0; i < pSuscribers; i++) {
			final int cpt = i;
			IListenerMessageMqtt listener = (messages, topic) -> {
				atoms.get(cpt).incrementAndGet();
				System.out.println("reception: " + atoms.get(cpt).get());
			};

			AbonnementMqtt abo = new AbonnementMqtt(listener, Arrays.asList(Topic.ETAT_AFFICHAGE_EQUIPEMENT), HOST,
					PORT);
			abos.add(abo);
			atoms.put(i, new AtomicInteger(0));
		}
		
		for (int i = 0; i < pPublications; i++) {
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

		int cpt = 0;
		int nombre = pPublications;
		boolean termine = false;

		while (!termine && cpt++ < 1000) {
			termine = true;
			for (int i = 0; i < pSuscribers; i++) {
				System.out.println(atoms.get(i).get());
				if (atoms.get(i).get() < nombre) {
					termine = false;
					break;
				}
			}

			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		System.out.println("Desabonnement");
		abos.forEach(a -> a.deconnexionListener());

		assertTrue("Perte de messages", termine);
	}

}
