package signature.mqttRest.services.rest.utilisateur;

/**
 * Traitement des requêtes de gestion des utilisateurs
 * @author SDARIZCUREN
 *
 */
public interface ITraitementRequetesUtilisateur {
	
	/**
	 * Traite une demande d'obtention de la liste des utilisateurs
	 * @return la liste des utilisateurs au format JSON
	 */
	public String traiteDemandeListeUtilisateurs();

}
