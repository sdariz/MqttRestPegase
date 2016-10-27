package signature.mqttRest.services.rest;

/**
 * Traitement des requêtes REST d'état des équipements
 * @author SDARIZCUREN
 *
 */
public interface ITraitementRequetesEtatEquipements {
	
	/**
	 * Traite une demande d'état d'affichage d'un équipement
	 * @param pId l'id de l'équipement
	 * @return l'état d'affichage au format JSON
	 */
	public String demandeEtatAffichageEquipement(String pId);
	
	/**
	 * Traite une demande d'état d'affichage pour tous les équipements
	 * @return l'état d'affichage des équipements au format JSON
	 */
	public String demandeEtatAffichageEquipements();
	
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
