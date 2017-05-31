package org.signature.mqttRest.services.mqtt;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.persist.MqttDefaultFilePersistence;
import org.signature.mqttRest.objetsPartages.IMessageMqttRest;
import org.signature.mqttRest.services.mqtt.ITopicMqtt.Topic;
import org.signature.mqttRest.util.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
	 *            l'adresse IP du broker mqttà
	 * @param pPort
	 *            le port utilisé par le broker
	 * @param pTopic
	 *            le topic vers lequel publier le message
	 * @throws Exception
	 *             si problème non géré
	 */
	public static void publicationMessage(IMessageMqttRest pMsg, String pHost, int pPort, Topic pTopic)
			throws Exception {
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
	 * @throws Exception
	 *             si problème non géré
	 */
	public static void publicationMessages(List<? extends IMessageMqttRest> pMsgs, String pHost, int pPort,
			Topic pTopic) throws Exception {
		String content = Util.listObjectToJsonString(pMsgs);
		String uri = String.format("tcp://%s:%d", pHost, pPort);

		// Génération d'un id unique pour le client
		// Limité entre 1 et 23 dans mqtt
		String clientId = UUID.randomUUID().toString().substring(0, 16);

		MqttClient clientMqtt;

		try {
			// clientMqtt = new MqttClient(uri, clientId, new
			// MemoryPersistence());
			clientMqtt = new MqttClient(uri, clientId, new MqttDefaultFilePersistence(
					GestionnaireRepertoiresTravail.DOSSIER_PERSISTANCES_MESSAGES_CLIENTS));

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

		int qosToUse = GestionnaireBrokerMqtt.getInstance()
				.getQosBroker(GestionnaireBrokerMqtt.getInstance().getDefautBroker());

		try {
			clientMqtt.publish(pTopic.toString(), content.getBytes(), qosToUse, false);
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
