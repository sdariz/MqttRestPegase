package signature.mqttRest.services.rest.serveur.etatEtPilotage;

import java.util.List;

import signature.mqttRest.objetsPartages.etatEtPilotage.MessageEtatAffichageMqttRest;

/**
 * Traitement des requêtes d'état des équipements
 * @author SDARIZCUREN
 *
 */
public interface ITraitementRequetesEtatEquipements {
	
	/**
	 * Traite une demande d'état d'affichage d'un équipement
	 * @param pId l'id de l'équipement
	 * @return l'état d'affichage de l'équipement
	 */
	public MessageEtatAffichageMqttRest demandeEtatAffichageEquipement(String pId);
	
	/**
	 * Traite une demande d'état d'affichage pour tous les équipements
	 * @return l'état d'affichage des équipements
	 */
	public List<MessageEtatAffichageMqttRest> demandeEtatAffichageEquipements();
	
	/**
	 * Traite une demande d'état technique d'un équipement
	 * @param pId l'id de l'équipement
	 * @return l'état technique au format JSON
	 */
	public String demandeEtatTechniqueEquipement(String pId);
	
	/**
	 * Traite une demande d'état technique pour tous les équipements
	 * @return l'état technique des équipements au format JSON
	 */
	public String demandeEtatTechniqueEquipements();
}
