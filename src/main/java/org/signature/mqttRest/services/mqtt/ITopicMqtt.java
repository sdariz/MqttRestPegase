package org.signature.mqttRest.services.mqtt;

import org.signature.mqttRest.objetsPartages.etatEtPilotage.MessageEtatAffichageMqttRest;
import org.signature.mqttRest.objetsPartages.etatEtPilotage.MessageEtatTechniqueMqttRest;
import org.signature.mqttRest.objetsPartages.informationPegase.MessageModificationPegaseMqttRest;

/**
 * Interface de base des clients mqtt
 * 
 * @author SDARIZCUREN
 *
 */
public interface ITopicMqtt {
	enum Topic {
		ETAT_AFFICHAGE_EQUIPEMENT, ETAT_TECHNIQUE_EQUIPEMENT, MODIFICATION_PEGASE
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
		case ETAT_TECHNIQUE_EQUIPEMENT:
			return MessageEtatTechniqueMqttRest.class;
		case MODIFICATION_PEGASE:
			return MessageModificationPegaseMqttRest.class;
		}
		
		return null;
	}
}
