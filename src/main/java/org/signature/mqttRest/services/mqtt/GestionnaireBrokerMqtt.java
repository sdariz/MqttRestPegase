package org.signature.mqttRest.services.mqtt;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.signature.mqttRest.services.mqtt.moquette.BrokerMqttMoquette;
import org.signature.mqttRest.services.mqtt.vertx.BrokerMqttVertx;

/**
 * Classe d�marrant le broker mqtt
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
	// (100 thread qui postent vers 10 abonn�s => perte de messages !!!))
	private final static int QOS_MOQUETTE = 1;

	private final static int QOS_VERTX = 2;

	/**
	 * Construction de l'instance unique
	 */
	private GestionnaireBrokerMqtt() {
	}

	/**
	 * Acc�s � l'instance unique de la classe
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
	 * Initialise le broker � utiliser
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
	 * Retourne le broker utilis�
	 * 
	 * @return le broker MQTT
	 */
	public BrokersMqtt getDefautBroker() {
		return _defautBroker;
	}

	/**
	 * Indique la qualit� de service � utiliser selon son support correct par le
	 * broker
	 * 
	 * @param pBrokerMqtt
	 *            le broker concern�
	 * @return la qualit� de service � utiliser
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
	 * D�marrage du broker par d�faut sur le port par d�faut : 1883, avec
	 * sauvegarde des donn�es sur disque
	 */
	public synchronized void startBroker() {
		this.startBroker(PORT_DEFAUT, false);
	}

	/**
	 * D�marrage du serveur sur le port par d�faut : 1883
	 * 
	 * @param pInMemoryDb
	 *            true pour ne pas sauvegarder les donn�es de fonctionnement du
	 *            serveur : reprise, ...
	 */
	public synchronized void startBroker(boolean pInMemoryDb) {
		this.startBroker(PORT_DEFAUT, pInMemoryDb);
	}

	/**
	 * D�marrage du serveur
	 * 
	 * @param pPort
	 *            le port � utiliser
	 * @param pInMemoryDb
	 *            true pour ne pas sauvegarder les donn�es de fonctionnement du
	 *            serveur : reprise, ...
	 */
	public synchronized void startBroker(int pPort, boolean pInMemoryDb) {
		// Rien � faire si le serveur est d�j� d�marr�
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

		// Hook pour arr�ter le serveur sur arr�t application
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
	 * Arr�t du serveur
	 */
	public synchronized void stopBroker() {
		if (_brokerMqtt != null) {
			_brokerMqtt.stopBroker();
		}
	}
}
