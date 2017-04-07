package org.signature.mqttRest.services.mqtt;

/**
 * Interface spécialisant les fonctionnalité d'un broker MQTT
 * 
 * @author SDARIZCUREN
 *
 */
public interface IBrokerMqtt {
	
	/**
	 * Démarrage du broker MQTT
	 * 
	 * @param pPort
	 *            le port à utiliser
	 * @param pInMemoryDb
	 *            true pour ne pas sauvegarder les données de fonctionnement du
	 *            serveur : reprise, ...
	 */
	public void startBroker(int pPort, boolean pInMemoryDb);
	
	/**
	 * Arrêt du broker MQTT
	 */
	public void stopBroker();

}
