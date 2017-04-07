package org.signature.mqttRest.services.mqtt.moquette;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Properties;

import org.signature.mqttRest.services.mqtt.IBrokerMqtt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.moquette.BrokerConstants;
import io.moquette.server.Server;

/**
 * Le broker MQTT moquette
 * 
 * @author SDARIZCUREN
 *
 */
public class BrokerMqttMoquette implements IBrokerMqtt {
	private final static Logger LOG = LoggerFactory.getLogger(BrokerMqttMoquette.class);

	private final static String DOSSIER_MQTT_BROKER = "mqtt-broker";

	private Server _serveur = null;

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
