package org.signature.mqttRest.services.mqtt;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.signature.mqttRest.services.mqtt.moquette.BrokerMqttMoquette;
import org.signature.mqttRest.services.mqtt.vertx.BrokerMqttVertx;

/**
 * Classe démarrant le broker mqtt
 * 
 * @author SDARIZCUREN
 *
 */
public class GestionnaireBrokerMqtt {
	private static GestionnaireBrokerMqtt _instance = null;
	
	private static Logger LOG = LogManager.getLogger(GestionnaireBrokerMqtt.class);

	private IBrokerMqtt _brokerMqtt = null;

	public enum BrokersMqtt {
		MOQUETTE_BROKER, VERTX_BROKER
	};

	private BrokersMqtt _defautBroker = BrokersMqtt.VERTX_BROKER;
	private final static int PORT_DEFAUT = 1883;

	// Acquittement de bonne reception du message (qos=2 ne marche pas toujours
	// (100 thread qui postent vers 10 abonnés => perte de messages !!!))
	private final static int QOS_MOQUETTE = 1;

	private final static int QOS_VERTX = 2;

	/**
	 * Construction de l'instance unique
	 */
	private GestionnaireBrokerMqtt() {
	}

	/**
	 * Accès à l'instance unique de la classe
	 * 
	 * @return l'instance unique
	 */
	public final static synchronized GestionnaireBrokerMqtt getInstance() {
		if (_instance == null) {
			_instance = new GestionnaireBrokerMqtt();
		}

		return _instance;
	}

	/**
	 * Initialise le broker à utiliser
	 * 
	 * @param pBrokerMqtt
	 *            le broker MQTT
	 * @return l'instance pour filer facilement les demandes
	 */
	public GestionnaireBrokerMqtt setDefautBroker(BrokersMqtt pBrokerMqtt) {
		if (pBrokerMqtt != null) {
			_defautBroker = pBrokerMqtt;
		}

		return _instance;
	}

	/**
	 * Retourne le broker utilisé
	 * 
	 * @return le broker MQTT
	 */
	public BrokersMqtt getDefautBroker() {
		return _defautBroker;
	}

	/**
	 * Indique la qualité de service à utiliser selon son support correct par le
	 * broker
	 * 
	 * @param pBrokerMqtt
	 *            le broker concerné
	 * @return la qualité de service à utiliser
	 */
	protected int getQosBroker(BrokersMqtt pBrokerMqtt) {
		switch (_defautBroker) {
		case MOQUETTE_BROKER:
			return QOS_MOQUETTE;
		case VERTX_BROKER:
			return QOS_VERTX;
		}

		return QOS_VERTX;
	}

	/**
	 * Démarrage du broker par défaut sur le port par défaut : 1883, avec
	 * sauvegarde des données sur disque
	 */
	public synchronized void startBroker() {
		this.startBroker(PORT_DEFAUT, false);
	}

	/**
	 * Démarrage du serveur sur le port par défaut : 1883
	 * 
	 * @param pInMemoryDb
	 *            true pour ne pas sauvegarder les données de fonctionnement du
	 *            serveur : reprise, ...
	 */
	public synchronized void startBroker(boolean pInMemoryDb) {
		this.startBroker(PORT_DEFAUT, pInMemoryDb);
	}

	/**
	 * Démarrage du serveur
	 * 
	 * @param pPort
	 *            le port à utiliser
	 * @param pInMemoryDb
	 *            true pour ne pas sauvegarder les données de fonctionnement du
	 *            serveur : reprise, ...
	 */
	public synchronized void startBroker(int pPort, boolean pInMemoryDb) {
		// Rien à faire si le serveur est déjà démarré
		if (_brokerMqtt != null) {
			return;
		}

		switch (_defautBroker) {
		case MOQUETTE_BROKER:
			_brokerMqtt = new BrokerMqttMoquette();
			break;
		case VERTX_BROKER:
			_brokerMqtt = new BrokerMqttVertx();
			break;
		}

		_brokerMqtt.startBroker(pPort, pInMemoryDb);

		// Hook pour arrêter le serveur sur arrêt application
		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {
				LOG.warn("Arret du broker mqtt");

				if (_brokerMqtt != null) {
					_brokerMqtt.stopBroker();
				}
			}
		});
	}

	/**
	 * Arrêt du serveur
	 */
	public synchronized void stopBroker() {
		if (_brokerMqtt != null) {
			_brokerMqtt.stopBroker();
		}
	}
}
