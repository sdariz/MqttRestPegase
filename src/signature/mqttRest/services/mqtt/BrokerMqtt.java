package signature.mqttRest.services.mqtt;

import java.io.IOException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.moquette.server.Server;

/**
 * Classe d�marrant le broker mqtt
 * 
 * @author SDARIZCUREN
 *
 */
public class BrokerMqtt {
	private final static Logger LOG = LoggerFactory.getLogger(BrokerMqtt.class);

	private int _port;
	private Server _serveur;

	/**
	 * D�marre le broker sur le port par d�faut 1883
	 */
	public BrokerMqtt() {
		this(1883);
	}

	/**
	 * D�marre le broker
	 * 
	 * @param pPort
	 *            le port d'�coute
	 */
	public BrokerMqtt(int pPort) {
		_port = pPort;

		// Hook pour arr�ter le serveur sur arr�t application
		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {
				LOG.info("Arret du broker mqtt");
				
				stopBroker();
			}
		});

	}

	/**
	 * D�marrage du serveur
	 */
	public synchronized void startBroker() {
		// Rien � faire si le serveur est d�j� d�marr�
		if (_serveur != null) {
			return;
		}

		_serveur = new Server();

		Properties props = new Properties();
		props.setProperty("port", Integer.toString(_port));

		// Propri�t� par d�faut
		// props.setProperty("allow_anonymous", "true");

		try {
			_serveur.startServer(props);
		} catch (IOException e) {
			LOG.error("Probl�me pour d�marrer le broker mqtt", e);
		}
	}
	
	/**
	 * Arr�t du serveur
	 */
	public synchronized void stopBroker() {
		if (_serveur != null) {
			_serveur.stopServer();
			_serveur = null;
		}
	}
}
