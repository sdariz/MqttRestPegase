package signature.mqttRest.objetsPartages.etatEtPilotage;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

import signature.mqttRest.objetsPartages.etatEtPilotage.MessageModuleMqttRest.Luminosite;


/**
 * Caract�risation d'un message d'�tat d'affichage d'un �quipement
 * 
 * @author SDARIZCUREN
 *
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "@class")
public interface IMessageAffichageEquipement {

	public enum TypeEquipement {
		PMV, PPLMV, PPAD, PICTOGRAMME, R24, PRISME, BARRIERE, BRA, RADT, METEO
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
}
