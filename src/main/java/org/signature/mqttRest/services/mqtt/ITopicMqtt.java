package org.signature.mqttRest.services.mqtt;

import org.signature.mqttRest.objetsPartages.etatEtPilotage.MessageEtatAffichageMqttRest;
import org.signature.mqttRest.objetsPartages.etatEtPilotage.MessageEtatTechniqueMqttRest;
import org.signature.mqttRest.objetsPartages.informationPegase.MessageModificationPegaseMqttRest;
import org.signature.mqttRest.objetsPartages.scenario.MessageActivationScenarioMqttRest;

/**
 * Interface de base des clients MQTT
 * 
 * @author SDARIZCUREN
 *
 */
public interface ITopicMqtt {
	enum Topic {
		ETAT_AFFICHAGE_EQUIPEMENT, ETAT_TECHNIQUE_EQUIPEMENT, MODIFICATION_PEGASE, PILOTAGE_SCENARIO
	};

	/**
	 * Donne la classe du message associé au topic
	 * 
	 * @param pTopic
	 *            le topic MQTT
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
		case PILOTAGE_SCENARIO:
			return MessageActivationScenarioMqttRest.class;
		}

		return null;
	}
}
