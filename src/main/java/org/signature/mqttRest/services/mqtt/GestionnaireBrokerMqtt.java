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
public class GestionnaireBrokerMqtt {
	private final static Logger LOG = LoggerFactory.getLogger(GestionnaireBrokerMqtt.class);
	private static GestionnaireBrokerMqtt _instance = null;

	private final static String DOSSIER_MQTT_BROKER = "mqtt-broker";
	public final static int PORT_DEFAUT = 1883;

	private Server _serveur = null;

	/**
	 * Construction de l'instance unique
	 */
	private GestionnaireBrokerMqtt() {
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
	 * Démarrage du serveur sur le port par défaut : 1883
	 */
	public synchronized void startBroker() {
		this.startBroker(PORT_DEFAUT);
	}

	/**
	 * Démarrage du serveur
	 * 
	 * @param pPort
	 *            le port à utiliser
	 */
	public synchronized void startBroker(int pPort) {
		// Rien à faire si le serveur est déjà démarré
		if (_serveur != null) {
			return;
		}

		// Suppression et recreation du dossier db
		creationDossierBroker();

		_serveur = new Server();

		Properties props = new Properties();
		props.setProperty(BrokerConstants.PORT_PROPERTY_NAME, Integer.toString(pPort));
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

	// Suppression et recréation du dossier du broker à chaque démarrage, pour
	// éviter d'exploser la taille
	private void creationDossierBroker() {
		File dir = new File(DOSSIER_MQTT_BROKER);
		if (dir.exists()) {
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
