package signature.mqttRest.services.mqtt;

import signature.mqttRest.objetsPartages.MessageEtatAffichageMqttRest;

/**
 * Interface de base des clients mqtt
 * 
 * @author SDARIZCUREN
 *
 */
public interface ITopicMqtt {
	enum Topic {
		ETAT_AFFICHAGE_EQUIPEMENT, ETAT_TECHNIQUE_EQUIPEMENT
	};

	/**
	 * Donne la classe du message associé au topic
	 * 
	 * @param pTopic
	 *            le topic mqtt
	 * @return la classe correspondante
	 */
	static Class<?> getClassMessagePourTopic(Topic pTopic) {
		switch (pTopic) {
		case ETAT_AFFICHAGE_EQUIPEMENT:
			return MessageEtatAffichageMqttRest.class;
		default:
			return null;
		}
	}
}
