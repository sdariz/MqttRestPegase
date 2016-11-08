package signature.mqttRest.services.rest.serveur.utilisateur;

import java.util.List;

import signature.mqttRest.objetsPartages.utilisateur.MessageUtilisateurMqttRest;

/**
 * Traitement des requêtes de gestion des utilisateurs
 * 
 * @author SDARIZCUREN
 *
 */
public interface ITraitementRequetesUtilisateur {

	/**
	 * Traite une demande d'obtention de la liste des utilisateurs
	 * 
	 * @return la liste des utilisateurs
	 */
	public List<MessageUtilisateurMqttRest> demandeListeUtilisateurs();

	/**
	 * Traite une demande d'obtention de l'utilisateur connecté
	 * 
	 * @return l'utilisateur connecté, null si pas d'utilisateur connecté
	 */
	public MessageUtilisateurMqttRest demandeUtilisateurConnecte();

	/**
	 * Indique si le login et le mot de passe associé sont valides
	 * 
	 * @param pLogin
	 *            le login de l'utilisateur
	 * @param pMotPasse
	 *            le mot de passe associé au login
	 * @return true ou false
	 */
	public boolean estValide(String pLogin, String pMotPasse);

}
