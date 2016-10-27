package signature.mqttRest.services.mqtt;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import signature.mqttRest.objetsPartages.IMessageMqttRest;
import signature.mqttRest.services.mqtt.ITopicMqtt.Topic;
import signature.mqttRest.util.Util;

/**
 * Un client mqtt pour publier en mode asynchrone des messages vers le broker
 * MQTT
 * 
 * @author SDARIZCUREN
 *
 */
public class PublicationMqtt {
	private final static Logger LOG = LoggerFactory.getLogger(PublicationMqtt.class);
	
	/**
	 * Publication d'un message
	 * 
	 * @param pMsg
	 *            le message à publier
	 * @param pHost
	 *            l'adresse IP du broker mqtt
	 * @param pPort
	 *            le port utilisé par le broker
	 * @param pTopic
	 *            le topic vers lequel publier le message
	 */
	public static synchronized void publicationMessage(IMessageMqttRest pMsg, String pHost, int pPort, Topic pTopic) {
		List<IMessageMqttRest> l = new ArrayList<>();
		l.add(pMsg);
		publicationMessages(l, pHost, pPort, pTopic);
	}

	/**
	 * Publication d'une liste de messages
	 * 
	 * @param pMsgs
	 *            les messages à publier
	 * @param pHost
	 *            l'adresse IP du broker mqtt
	 * @param pPort
	 *            le port utilisé par le broker
	 * @param pTopic
	 *            le topic vers lequel publier le message
	 */
	public static synchronized void publicationMessages(List<IMessageMqttRest> pMsgs, String pHost, int pPort, Topic pTopic) {
		String content = Util. toJsonString(pMsgs);
		String uri = "tcp://" + pHost + ":" + pPort;

		// Génération d'un id unique pour le client
		String clientId = UUID.randomUUID().toString();

		MqttClient clientMqtt;

		try {
			clientMqtt = new MqttClient(uri, clientId, new MemoryPersistence());
		} catch (MqttException e) {
			LOG.error("Problème de création de la connection au broker mqtt", e);
			return;
		}

		MqttConnectOptions connOpts = new MqttConnectOptions();
		connOpts.setCleanSession(true);

		// Connexion au broker et publication dans la callback
		try {
			clientMqtt.connect(connOpts);
		} catch (MqttException e) {
			LOG.error("Problème de connexion au broker", e);
			deconnexion(clientMqtt);
			return;
		}

		MqttMessage message = new MqttMessage(content.getBytes());
		message.setQos(1); // Acquittement de bonne reception du
							// message (qos=2 ne marche pas toujours !!!)

		try {
			clientMqtt.publish(pTopic.toString(), message);
		} catch (MqttException e) {
			LOG.error("Problème de publication du message", e);
			deconnexion(clientMqtt);
			return;
		}
		
		// Déconnexion en fin de traitement
		deconnexion(clientMqtt);
	}

	// Déconnexion du client
	private static void deconnexion(MqttClient pClient) {
		try {
			pClient.disconnect();
		} catch (MqttException e) {
			LOG.error("Problème deconnexion du client mqtt", e);
			return;
		}
		
		try {
			pClient.close();
		} catch (MqttException e) {
			LOG.error("Problème fermeture du client mqtt", e);
		}
	}

}
