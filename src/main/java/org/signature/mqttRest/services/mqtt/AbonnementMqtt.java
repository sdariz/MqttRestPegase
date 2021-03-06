package org.signature.mqttRest.services.mqtt;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MqttDefaultFilePersistence;
import org.signature.mqttRest.objetsPartages.IMessageMqttRest;
import org.signature.mqttRest.services.mqtt.ITopicMqtt.Topic;
import org.signature.mqttRest.util.Util;

/**
 * Abonnement ? un topic mqtt
 * 
 * @author SDARIZCUREN
 *
 */
public class AbonnementMqtt implements MqttCallback {
	private static Logger LOG = LogManager.getLogger(AbonnementMqtt.class);
	
	private List<Topic> _topics;
	private IListenerMessageMqtt _listener;
	private MqttClient _clientMqtt;
	private String _host;
	private int _port;

	/**
	 * Abonnement ? un topic
	 * 
	 * @param pListener
	 *            le listener ? pr?venir sur r?ception d'un message
	 * @param pTopic
	 *            le topic auxques on veut s'abonner
	 * @param pHost
	 *            l'adresse IP du broker mqtt
	 * @param pPort
	 *            le port utilis? par le broker
	 */
	public AbonnementMqtt(IListenerMessageMqtt pListener, Topic pTopic, String pHost, int pPort) {
		this(pListener, pTopic == null ? new ArrayList<>() : Arrays.asList(pTopic), pHost, pPort);
	}

	/**
	 * Abonnement ? une liste de topics
	 * 
	 * @param pListener
	 *            le listener ? pr?venir sur r?ception d'un message
	 * @param pTopics
	 *            les topics auxquels on veut s'abonner
	 * @param pHost
	 *            l'adresse IP du broker mqtt
	 * @param pPort
	 *            le port utilis? par le broker
	 */
	public AbonnementMqtt(IListenerMessageMqtt pListener, List<Topic> pTopics, String pHost, int pPort) {
		if (pTopics == null) {
			pTopics = new ArrayList<>();
		}
		
		// Effacement des fichiers de travail au premier chargement de l'application
		GestionnaireRepertoiresTravail.getInstance().purgeDossierPersistanceClientsUneFoisAuDemarrage();

		_listener = pListener;
		_topics = pTopics;
		_host = pHost;
		_port = pPort;

		// Lancement d'une connexion au broker mqtt
		if (_topics.size() > 0 && _listener != null) {
			connexionAuBroker();
		}
	}

	// Connexion au broker
	private void connexionAuBroker() {
		String uri = String.format("tcp://%s:%d", _host, _port);

		// G?n?ration d'un id unique pour le client
		// Limit? entre 1 et 23 dans mqtt
		String clientId = UUID.randomUUID().toString().substring(0, 16);

		try {
			// _clientMqtt = new MqttClient(uri, clientId, new
			// MemoryPersistence());
			_clientMqtt = new MqttClient(uri, clientId, new MqttDefaultFilePersistence(
					GestionnaireRepertoiresTravail.DOSSIER_PERSISTANCES_MESSAGES_CLIENTS));
		} catch (MqttException e) {
			LOG.error("Probl?me de cr?ation de la connection au broker mqtt", e);
			return;
		}

		MqttConnectOptions connOpts = new MqttConnectOptions();
		connOpts.setCleanSession(true);
		connOpts.setAutomaticReconnect(true);
		connOpts.setMaxInflight(100);

		// Initialisation de l'instance en tant que callback des ?v?nements mqtt
		_clientMqtt.setCallback(this);

		try {
			_clientMqtt.connect(connOpts);
		} catch (MqttException e) {
			LOG.error("Probl?me de connexion au broker", e);
			deconnexion();
			return;
		}

		int qosToUse = GestionnaireBrokerMqtt.getInstance()
				.getQosBroker(GestionnaireBrokerMqtt.getInstance().getDefautBroker());

		// Liste des topics sur lesquels s'abonner.
		String[] topics = new String[_topics.size()];
		int[] qos = new int[_topics.size()];
		for (int i = 0; i < _topics.size(); i++) {
			topics[i] = _topics.get(i).toString();
			qos[i] = qosToUse;
		}

		// Abonnement aux topics
		try {
			_clientMqtt.subscribe(topics, qos);
		} catch (MqttException e) {
			LOG.error("Probl?me d'abonnement aux topics", e);
			deconnexion();
			return;
		}
	}

	// D?connexion du client
	private void deconnexion() {
		if (_clientMqtt == null) {
			return;
		}

		String[] topics = new String[_topics.size()];
		for (int i = 0; i < _topics.size(); i++) {
			topics[i] = _topics.get(i).toString();
		}

		try {
			_clientMqtt.unsubscribe(topics);
		} catch (MqttException e1) {
			LOG.error("Probl?me de d?sabonnement des topics", e1);
		}

		try {
			_clientMqtt.disconnect();
		} catch (MqttException e) {
			LOG.error("Probl?me de d?connection du client mqtt", e);
			_clientMqtt = null;
			return;
		}

		try {
			_clientMqtt.close();
		} catch (MqttException e) {
			LOG.error("Erreur close du client mqtt", e);
		}

		_clientMqtt = null;
	}

	/**
	 * D?connexion du client ? l'?coute des messages re?us
	 */
	public void deconnexionListener() {
		deconnexion();
	}

	/**
	 * Callback mqtt sur perte de connexion avec le serveur. Service interne, ne
	 * pas utiliser.
	 */
	@Override
	public void connectionLost(Throwable pCause) {
		LOG.error("Perte de connexion avec le serveur", pCause);

		// Un thread pour attendre le retour du serveur et se r?abonner
		// autoReconnect est ? true, mais il faut se r?abonner (?)
		new Thread(() -> {
			while (true) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					LOG.error("Erreur thread", e);
					return;
				}

				// Si retour du serveur, je close la connexion courante pour me
				// r?abonner
				if (_clientMqtt.isConnected()) {
					deconnexion();

					connexionAuBroker();

					return;
				}
			}
		}).start();
	}

	/**
	 * Callback mqtt pour pr?venir que les acquittement ont ?t? re?us. Service
	 * interne, ne pas utiliser.
	 */
	@Override
	public void deliveryComplete(IMqttDeliveryToken pToken) {
		// Rien ? faire
	}

	/**
	 * Callback pour avertir de l'arriv? d'un nouveau message. Service interne,
	 * ne pas utiliser.
	 */
	@Override
	public synchronized void messageArrived(String pTopic, MqttMessage pMessage) throws Exception {
		// V?rification que l'on est abonn? ? ce topic
		Topic topic = getTopicCorrespondant(pTopic);
		if (topic == null) {
			return;
		}

		byte[] bytes = pMessage.getPayload();
		if (bytes == null || bytes.length == 0) {
			return;
		}

		// La classe des objets associ?s au topic
		Class<?> classObj = ITopicMqtt.getClassMessagePourTopic(topic);

		String content = new String(bytes);
		// D?s?rialisation des objets (toujours transfert d'une liste d'objets)
		// et conversion au bon format
		List<Object> objs = Util.jsonToListeObjet(content, classObj);
		List<IMessageMqttRest> reponse = objs.stream().map(IMessageMqttRest.class::cast).collect(Collectors.toList());

		_listener.messagesRecu(reponse, topic);
	}

	// Recherche le topic correspondant au nom demand?. Retourne null si pas
	// trouv?
	private Topic getTopicCorrespondant(String pTopic) {
		Topic[] topics = Topic.values();
		for (int i = 0; i < topics.length; i++) {
			if (topics[i].toString().equals(pTopic)) {
				return topics[i];
			}
		}

		return null;
	}

}
