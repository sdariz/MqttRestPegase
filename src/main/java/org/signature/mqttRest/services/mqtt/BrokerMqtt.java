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
 * Classe démarrant le broker mqtt
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
				LOG.warn("Arret du broker mqtt");

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
		
		// Suppression et recreation du dossier db
		creationDossierBroker();

		_serveur = new Server();

		Properties props = new Properties();
		props.setProperty(BrokerConstants.PORT_PROPERTY_NAME, Integer.toString(_port));
		props.setProperty(BrokerConstants.PERSISTENT_STORE_PROPERTY_NAME,
				System.getProperty("user.dir") + File.separator + DOSSIER_MQTT_BROKER + File.separator
						+ BrokerConstants.DEFAULT_MOQUETTE_STORE_MAP_DB_FILENAME);

		// Propriété par défaut
		// props.setProperty("allow_anonymous", "true");

		try {
			_serveur.startServer(props);
		} catch (IOException e) {
			LOG.error("Problème pour démarrer le broker mqtt", e);
		}
	}
	
	// Suppression et recréation du dossier du broker à chaque démarrage, pour éviter d'exploser la taille
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
	 * Arrêt du serveur
	 */
	public synchronized void stopBroker() {
		if (_serveur != null) {
			_serveur.stopServer();
			_serveur = null;
		}
	}
}
