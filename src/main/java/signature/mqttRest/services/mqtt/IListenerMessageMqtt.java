package signature.mqttRest.services.mqtt;

import java.util.List;

import signature.mqttRest.objetsPartages.IMessageMqttRest;
import signature.mqttRest.services.mqtt.ITopicMqtt.Topic;

/**
 * Interface pour être prévenu de l'arrivé d'un message mqtt
 * @author SDARIZCUREN
 *
 */
public interface IListenerMessageMqtt {
	
	/**
	 * Prévient de l'arrivé de messages
	 * @param pMessages les messages reçus
	 * @param pTopic le topic du message
	 */
	void messagesRecu(List<IMessageMqttRest> pMessages, Topic pTopic);
}
