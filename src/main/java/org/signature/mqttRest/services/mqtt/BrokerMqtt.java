package org.signature.mqttRest.services.mqtt;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.moquette.BrokerConstants;
import io.moquette.server.Server;

/**
 * Classe d�marrant le broker mqtt
 * 
 * @author SDARIZCUREN
 *
 */
public class BrokerMqtt {
	private final static Logger LOG = LoggerFactory.getLogger(BrokerMqtt.class);
	
	private final static String DOSSIER_MQTT_BROKER = "mqtt-broker";

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
				LOG.warn("Arret du broker mqtt");

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
		
		// Suppression et recreation du dossier db
		creationDossierBroker();

		_serveur = new Server();

		Properties props = new Properties();
		props.setProperty(BrokerConstants.PORT_PROPERTY_NAME, Integer.toString(_port));
		props.setProperty(BrokerConstants.PERSISTENT_STORE_PROPERTY_NAME,
				System.getProperty("user.dir") + File.separator + DOSSIER_MQTT_BROKER + File.separator
						+ BrokerConstants.DEFAULT_MOQUETTE_STORE_MAP_DB_FILENAME);

		// Propri�t� par d�faut
		// props.setProperty("allow_anonymous", "true");

		try {
			_serveur.startServer(props);
		} catch (IOException e) {
			LOG.error("Probl�me pour d�marrer le broker mqtt", e);
		}
	}
	
	// Suppression et recr�ation du dossier du broker � chaque d�marrage, pour �viter d'exploser la taille
	private void creationDossierBroker() {
		File dir = new File(DOSSIER_MQTT_BROKER);
		if(dir.exists()) {
			File[] files = dir.listFiles();
			Arrays.asList(files).forEach(f -> f.delete());
			
			dir.delete();
		}
		
		dir.mkdir();
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
