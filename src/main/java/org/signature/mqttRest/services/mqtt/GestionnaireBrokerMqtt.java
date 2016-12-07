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
	 * D�marrage du serveur sur le port par d�faut : 1883
	 */
	public synchronized void startBroker() {
		this.startBroker(PORT_DEFAUT);
	}

	/**
	 * D�marrage du serveur
	 * 
	 * @param pPort
	 *            le port � utiliser
	 */
	public synchronized void startBroker(int pPort) {
		// Rien � faire si le serveur est d�j� d�marr�
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

		// Propri�t� par d�faut
		// props.setProperty("allow_anonymous", "true");

		try {
			_serveur.startServer(props);
		} catch (IOException e) {
			LOG.error("Probl�me pour d�marrer le broker mqtt", e);
		}
	}

	// Suppression et recr�ation du dossier du broker � chaque d�marrage, pour
	// �viter d'exploser la taille
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
	 * Arr�t du serveur
	 */
	public synchronized void stopBroker() {
		if (_serveur != null) {
			_serveur.stopServer();
			_serveur = null;
		}
	}
}
