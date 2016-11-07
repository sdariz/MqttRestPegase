package signature.mqttRest.objetsPartages;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

import signature.mqttRest.objetsPartages.MessageModuleMqttRest.Luminosite;

/**
 * Caractérisation d'un message d'état d'affichage d'un équipement
 * 
 * @author SDARIZCUREN
 *
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "@class")
public interface IMessageAffichageEquipement {

	public enum TYPE_EQUIPEMENT {
		PMV, PPLMV, PPAD, PICTOGRAMME, R24, PRISME, BARRIERE, BRA, RADT, METEO
	};

	/**
	 * Initialise une luminosité commune pour l'ensemble des modules de
	 * l'équipement
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
	 *            la durée commune
	 */
	public default void setDureeValiditeCommune(int pDuree) {
		getMessagesModules().forEach(m -> m.setDureeValidite(pDuree));
	}

	/**
	 * Initialise une durée de validité restante commune pour l'ensemble des
	 * modules
	 * 
	 * @param pDuree
	 *            la durée restante commune
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
	 * Indique le type de l'équipement concerné par le message d'affichage
	 * 
	 * @return le type de l'équipement
	 */
	public TYPE_EQUIPEMENT getTypeEquipement();
}
