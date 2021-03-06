package org.signature.mqttRest.services.mqtt;

import java.util.List;

import org.signature.mqttRest.objetsPartages.IMessageMqttRest;
import org.signature.mqttRest.services.mqtt.ITopicMqtt.Topic;

/**
 * Interface pour ?tre pr?venu de l'arriv? d'un message mqtt
 * 
 * @author SDARIZCUREN
 *
 */
@FunctionalInterface
public interface IListenerMessageMqtt {

	/**
	 * Pr?vient de l'arriv? de messages
	 * 
	 * @param pMessages
	 *            les messages re?us
	 * @param pTopic
	 *            le topic du message
	 */
	void messagesRecu(List<IMessageMqttRest> pMessages, Topic pTopic);
}
