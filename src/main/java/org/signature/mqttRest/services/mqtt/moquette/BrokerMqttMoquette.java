package org.signature.mqttRest.services.mqtt.moquette;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.signature.mqttRest.services.mqtt.IBrokerMqtt;

import io.moquette.BrokerConstants;
import io.moquette.server.Server;

/**
 * Le broker MQTT moquette
 * 
 * @author SDARIZCUREN
 *
 */
public class BrokerMqttMoquette implements IBrokerMqtt {
	private static Logger LOG = LogManager.getLogger(BrokerMqttMoquette.class);

	private final static String DOSSIER_MQTT_BROKER = "mqtt-broker";

	private Server _serveur = null;

	/**
	 * D?marrage du serveur
	 * 
	 * @param pPort
	 *            le port ? utiliser
	 * @param pInMemoryDb
	 *            true pour ne pas sauvegarder les donn?es de fonctionnement du
	 *            serveur : reprise, ...
	 */
	public synchronized void startBroker(int pPort, boolean pInMemoryDb) {
		// Rien ? faire si le serveur est d?j? d?marr?
		if (_serveur != null) {
			return;
		}

		// Suppression et recreation du dossier db
		creationDossierBroker();

		_serveur = new Server();

		Properties props = new Properties();
		props.setProperty(BrokerConstants.PORT_PROPERTY_NAME, Integer.toString(pPort));

		if (pInMemoryDb) {
			props.setProperty(BrokerConstants.PERSISTENT_STORE_PROPERTY_NAME, "");
		} else {
			props.setProperty(BrokerConstants.PERSISTENT_STORE_PROPERTY_NAME,
					System.getProperty("user.dir") + File.separator + DOSSIER_MQTT_BROKER + File.separator
							+ BrokerConstants.DEFAULT_MOQUETTE_STORE_MAP_DB_FILENAME);
		}

		// Propri?t? par d?faut
		// props.setProperty("allow_anonymous", "true");

		try {
			_serveur.startServer(props);
		} catch (IOException e) {
			LOG.error("Probl?me pour d?marrer le broker mqtt", e);
		}
	}

	// Suppression et recr?ation du dossier du broker ? chaque d?marrage, pour
	// ?viter d'exploser la taille
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
	 * Arr?t du serveur
	 */
	public synchronized void stopBroker() {
		if (_serveur != null) {
			_serveur.stopServer();
			_serveur = null;
		}
	}

}
