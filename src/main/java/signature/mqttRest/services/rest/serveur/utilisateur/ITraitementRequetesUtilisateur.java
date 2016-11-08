package signature.mqttRest.services.rest.serveur.utilisateur;

import java.util.List;

import signature.mqttRest.objetsPartages.utilisateur.MessageUtilisateurMqttRest;

/**
 * Traitement des requ�tes de gestion des utilisateurs
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
	 * Traite une demande d'obtention de l'utilisateur connect�
	 * 
	 * @return l'utilisateur connect�, null si pas d'utilisateur connect�
	 */
	public MessageUtilisateurMqttRest demandeUtilisateurConnecte();

	/**
	 * Indique si le login et le mot de passe associ� sont valides
	 * 
	 * @param pLogin
	 *            le login de l'utilisateur
	 * @param pMotPasse
	 *            le mot de passe associ� au login
	 * @return true ou false
	 */
	public boolean estValide(String pLogin, String pMotPasse);

}
