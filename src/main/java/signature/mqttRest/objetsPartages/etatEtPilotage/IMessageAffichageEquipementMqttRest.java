package signature.mqttRest.objetsPartages.etatEtPilotage;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import signature.mqttRest.objetsPartages.etatEtPilotage.MessageModuleMqttRest.Luminosite;

/**
 * Caract�risation d'un message d'�tat d'affichage d'un �quipement
 * 
 * @author SDARIZCUREN
 *
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "typeEquipement")
@JsonSubTypes({ @Type(value = MessagePmvMqttRest.class, name = "PMV"),
		@Type(value = MessagePplmvMqttRest.class, name = "PPLMV"),
		@Type(value = MessagePpadMqttRest.class, name = "PPAD"),
		@Type(value = MessagePictogrammeMqttRest.class, name = "PICTOGRAMME"),
		@Type(value = MessageR24MqttRest.class, name = "R24"),
		@Type(value = MessagePrismeMqttRest.class, name = "PRISME"),
		@Type(value = MessageBarriereMqttRest.class, name = "BARRIERE"),
		@Type(value = MessageBraMqttRest.class, name = "BRA") })
public interface IMessageAffichageEquipementMqttRest {

	public enum TypeEquipement {
		PMV, PPLMV, PPAD, PICTOGRAMME, R24, PRISME, BARRIERE, BRA
		// , RADT, METEO
	};

	public enum TypeMessage {
		EXPLOITATION, ETEINT, NEUTRE
	};

	/**
	 * Initialise une luminosit� commune pour l'ensemble des modules de
	 * l'�quipement
	 * 
	 * @param pLuminosite
	 *            la luminosit� commune
	 */
	public default void setLuminositeCommune(Luminosite pLuminosite) {
		getMessagesModules().forEach(m -> m.setLuminosite(pLuminosite));
	}

	/**
	 * Initialise une dur�e de validit� commune pour l'ensemble des modules
	 * 
	 * @param pDuree
	 *            la dur�e commune
	 */
	public default void setDureeValiditeCommune(int pDuree) {
		getMessagesModules().forEach(m -> m.setDureeValidite(pDuree));
	}

	/**
	 * Initialise une dur�e de validit� restante commune pour l'ensemble des
	 * modules
	 * 
	 * @param pDuree
	 *            la dur�e restante commune
	 */
	public default void setDureeValiditeRestanteCommune(int pDuree) {
		getMessagesModules().forEach(m -> m.setDureeValiditeRestante(pDuree));
	}

	/**
	 * Retourne la liste des messages pour chaque module
	 * 
	 * @return l'affichage des modules
	 */
	public List<MessageModuleMqttRest> getMessagesModules();

	/**
	 * Indique le type de l'�quipement concern� par le message d'affichage
	 * 
	 * @return le type de l'�quipement
	 */
	public TypeEquipement getTypeEquipement();

	/**
	 * Indique le type du message : message d'exploitation, de mise au neutre
	 * 
	 * @return le type de message
	 */
	public TypeMessage getTypeMessage();
	
	/**
	 * Initialise le type du message : message d'exploitation, de mise au neutre
	 * 
	 * @param pType le type de message
	 */
	public void setTypeMessage(TypeMessage pType);
}
