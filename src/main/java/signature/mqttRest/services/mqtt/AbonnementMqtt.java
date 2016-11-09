package signature.mqttRest.services.mqtt;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
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
 * Abonnement à un topic mqtt
 * 
 * @author SDARIZCUREN
 *
 */
public class AbonnementMqtt implements MqttCallback {
	private final static Logger LOG = LoggerFactory.getLogger(AbonnementMqtt.class);
	private List<Topic> _topics;
	private IListenerMessageMqtt _listener;
	private MqttClient _clientMqtt;
	private String _host;
	private int _port;

	// Acquittement de bonne reception du message (qos=2 ne marche pas toujours
	// (100 thread qui postent vers 10 abonnés => perte de messages !!!))
	private final static int QOS = 1;

	/**
	 * Abonnement à un topic
	 * 
	 * @param pListener
	 *            le listener à prévenir sur réception d'un message
	 * @param pTopic
	 *            le topic auxques on veut s'abonner
	 * @param pHost
	 *            l'adresse IP du broker mqtt
	 * @param pPort
	 *            le port utilisé par le broker
	 */
	public AbonnementMqtt(IListenerMessageMqtt pListener, Topic pTopic, String pHost, int pPort) {
		_topics = new ArrayList<>();
		if (pTopic != null) {
			_topics.add(pTopic);
		}

		_listener = pListener;
		_host = pHost;
		_port = pPort;

		// Lancement d'une connexion au broker mqtt
		if (_topics.size() > 0 && _listener != null) {
			connexionAuBroker();
		}
	}

	/**
	 * Abonnement à une liste de topics
	 * 
	 * @param pListener
	 *            le listener à prévenir sur réception d'un message
	 * @param pTopics
	 *            les topics auxquels on veut s'abonner
	 * @param pHost
	 *            l'adresse IP du broker mqtt
	 * @param pPort
	 *            le port utilisé par le broker
	 */
	public AbonnementMqtt(IListenerMessageMqtt pListener, List<Topic> pTopics, String pHost, int pPort) {
		if (pTopics == null) {
			pTopics = new ArrayList<>();
		}

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
		String uri = "tcp://" + _host + ":" + _port;

		// Génération d'un id unique pour le client
		String clientId = UUID.randomUUID().toString();

		try {
			_clientMqtt = new MqttClient(uri, clientId, new MemoryPersistence());
		} catch (MqttException e) {
			LOG.error("Problème de création de la connection au broker mqtt", e);
			return;
		}

		MqttConnectOptions connOpts = new MqttConnectOptions();
		connOpts.setCleanSession(true);
		connOpts.setAutomaticReconnect(true);

		// Initialisation de l'instance en tant que callback des évènements mqtt
		_clientMqtt.setCallback(this);

		try {
			_clientMqtt.connect(connOpts);
		} catch (MqttException e) {
			LOG.error("Problème de connexion au broker", e);
			deconnexion();
			return;
		}

		// Liste des topics sur lesquels s'abonner.
		String[] topics = new String[_topics.size()];
		int[] qos = new int[_topics.size()];
		for (int i = 0; i < _topics.size(); i++) {
			topics[i] = _topics.get(i).toString();
			qos[i] = QOS;
		}

		// Abonnement aux topics
		try {
			_clientMqtt.subscribe(topics, qos);
		} catch (MqttException e) {
			LOG.error("Problème d'abonnement aux topics", e);
			deconnexion();
			return;
		}
	}

	// Déconnexion du client
	private void deconnexion() {
		if (_clientMqtt == null) {
			return;
		}

		try {
			_clientMqtt.disconnect();
		} catch (MqttException e) {
			LOG.error("Problème de déconnection du client mqtt", e);
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
	 * Déconnexion du client à l'écoute des messages reçus
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

		// Un thread pour attendre le retour du serveur et se réabonner
		// autoReconnect est à true, mais il faut se réabonner (?)
		new Thread(() -> {
			while (true) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					LOG.error("Erreur thread", e);
					return;
				}

				// Si retour du serveur, je close la connexion courante pour me
				// réabonner
				if (_clientMqtt.isConnected()) {
					deconnexion();

					connexionAuBroker();

					return;
				}
			}
		}).start();
	}

	/**
	 * Callback mqtt pour prévenir que les acquittement ont été reçus. Service
	 * interne, ne pas utiliser.
	 */
	@Override
	public void deliveryComplete(IMqttDeliveryToken pToken) {
		// Rien à faire
	}

	/**
	 * Callback pour avertir de l'arrivé d'un nouveau message. Service interne,
	 * ne pas utiliser.
	 */
	@Override
	public void messageArrived(String pTopic, MqttMessage pMessage) throws Exception {
		// Vérification que l'on est abonné à ce topic
		Topic topic = getTopicCorrespondant(pTopic);
		if (topic == null) {
			return;
		}

		byte[] bytes = pMessage.getPayload();
		if (bytes == null || bytes.length == 0) {
			return;
		}

		// La classe des objets associés au topic
		Class<?> classObj = ITopicMqtt.getClassMessagePourTopic(topic);

		String content = new String(bytes);
		// Désérialisation des objets (toujours transfert d'une liste d'objets)
		// et conversion au bon format
		List<Object> objs = Util.jsonToListeObjet(content, classObj);
		List<IMessageMqttRest> reponse = objs.stream().map(IMessageMqttRest.class::cast).collect(Collectors.toList());

		_listener.messagesRecu(reponse, topic);
	}

	// Recherche le topic correspondant au nom demandé. Retourne null si pas
	// trouvé
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
