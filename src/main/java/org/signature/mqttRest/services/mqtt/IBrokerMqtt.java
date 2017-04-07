package org.signature.mqttRest.services.mqtt;

/**
 * Interface sp�cialisant les fonctionnalit� d'un broker MQTT
 * 
 * @author SDARIZCUREN
 *
 */
public interface IBrokerMqtt {
	
	/**
	 * D�marrage du broker MQTT
	 * 
	 * @param pPort
	 *            le port � utiliser
	 * @param pInMemoryDb
	 *            true pour ne pas sauvegarder les donn�es de fonctionnement du
	 *            serveur : reprise, ...
	 */
	public void startBroker(int pPort, boolean pInMemoryDb);
	
	/**
	 * Arr�t du broker MQTT
	 */
	public void stopBroker();

}
