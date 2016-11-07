package signature.mqttRest.services.rest.etatEtPilotage;

import java.util.List;

import signature.mqttRest.objetsPartages.etatEtPilotage.MessageEtatAffichageMqttRest;

/**
 * Traitement des requ�tes d'�tat des �quipements
 * @author SDARIZCUREN
 *
 */
public interface ITraitementRequetesEtatEquipements {
	
	/**
	 * Traite une demande d'�tat d'affichage d'un �quipement
	 * @param pId l'id de l'�quipement
	 * @return l'�tat d'affichage de l'�quipement
	 */
	public MessageEtatAffichageMqttRest demandeEtatAffichageEquipement(String pId);
	
	/**
	 * Traite une demande d'�tat d'affichage pour tous les �quipements
	 * @return l'�tat d'affichage des �quipements
	 */
	public List<MessageEtatAffichageMqttRest> demandeEtatAffichageEquipements();
	
	/**
	 * Traite une demande d'�tat technique d'un �quipement
	 * @param pId l'id de l'�quipement
	 * @return l'�tat technique au format JSON
	 */
	public String demandeEtatTechniqueEquipement(String pId);
	
	/**
	 * Traite une demande d'�tat technique pour tous les �quipements
	 * @return l'�tat technique des �quipements au format JSON
	 */
	public String demandeEtatTechniqueEquipements();
}
