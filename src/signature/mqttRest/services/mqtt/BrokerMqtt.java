package signature.mqttRest.services.mqtt;

import java.io.IOException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.moquette.server.Server;

/**
 * Classe démarrant le broker mqtt
 * 
 * @author SDARIZCUREN
 *
 */
public class BrokerMqtt {
	private final static Logger LOG = LoggerFactory.getLogger(BrokerMqtt.class);

	private int _port;
	private Server _serveur;

	/**
	 * Démarre le broker sur le port par défaut 1883
	 */
	public BrokerMqtt() {
		this(1883);
	}

	/**
	 * Démarre le broker
	 * 
	 * @param pPort
	 *            le port d'écoute
	 */
	public BrokerMqtt(int pPort) {
		_port = pPort;

		// Hook pour arrêter le serveur sur arrêt application
		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {
				LOG.info("Arret du broker mqtt");
				
				stopBroker();
			}
		});

	}

	/**
	 * Démarrage du serveur
	 */
	public synchronized void startBroker() {
		// Rien à faire si le serveur est déjà démarré
		if (_serveur != null) {
			return;
		}

		_serveur = new Server();

		Properties props = new Properties();
		props.setProperty("port", Integer.toString(_port));

		// Propriété par défaut
		// props.setProperty("allow_anonymous", "true");

		try {
			_serveur.startServer(props);
		} catch (IOException e) {
			LOG.error("Problème pour démarrer le broker mqtt", e);
		}
	}
	
	/**
	 * Arrêt du serveur
	 */
	public synchronized void stopBroker() {
		if (_serveur != null) {
			_serveur.stopServer();
			_serveur = null;
		}
	}
}
