package org.signature.mqttRest.objetsPartages.etatEtPilotage;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import org.signature.mqttRest.objetsPartages.etatEtPilotage.MessageModuleMqttRest.Luminosite;

/**
 * Caractérisation d'un message d'état d'affichage d'un équipement
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
		@Type(value = MessageBraMqttRest.class, name = "BRA"),
		@Type(value = MessageFeuRegulationMqttRest.class, name = "FEU_REGULATION") })
public interface IMessageAffichageEquipementMqttRest {

	public enum TypeEquipement {
		PMV, PPLMV, PPAD, PICTOGRAMME, R24, PRISME, BARRIERE, BRA, FEU_REGULATION
		// , RADT, METEO
	};

	public enum TypeMessage {
		EXPLOITATION, ETEINT, NEUTRE
	};

	public final static String ETEINT = "eteint";
	public final static String ALLUME = "allume";
	public final static String OUVERT = "ouvert";
	public final static String FERME = "ferme";
	public final static String DEPLOYE = "deploye";
	public final static String REPLIE = "replie";
	public final static String INCONNU = "inconnu";

	/**
	 * Initialise une luminosité commune pour l'ensemble des modules de l'équipement
	 * 
	 * @param pLuminosite
	 *            la luminosité commune
	 */
	public default void setLuminositeCommune(Luminosite pLuminosite) {
		getMessagesModules().forEach(m -> m.setLuminosite(pLuminosite));
	}

	/**
	 * Initialise une durée de validité commune pour l'ensemble des modules
	 * 
	 * @param pDuree
	 *            la durée commune en secondes
	 */
	public default void setDureeValiditeCommune(int pDuree) {
		getMessagesModules().forEach(m -> m.setDureeValidite(pDuree));
	}

	/**
	 * Initialise une durée de validité restante commune pour l'ensemble des modules
	 * 
	 * @param pDuree
	 *            la durée restante commune en secondes
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
	 * Donne l'identifant unique associé à l'équipement
	 * 
	 * @return l'identifant unique de l'équipement
	 */
	public String getIdentifiantEquipement();

	/**
	 * Initialise l'identifant unique associé à l'équipement
	 * 
	 * @param pId
	 *            l'identifant unique de l'équipement
	 */
	public void setIdentifiantEquipement(String pId);

	/**
	 * Indique le type de l'équipement concerné par le message d'affichage
	 * 
	 * @return le type de l'équipement
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
	 * @param pType
	 *            le type de message
	 */
	public void setTypeMessage(TypeMessage pType);

	/**
	 * Copie de l'objet courant
	 * 
	 * @return une copie de l'instance courante
	 */
	public IMessageAffichageEquipementMqttRest duplique();
}
