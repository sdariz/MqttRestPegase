package signature.mqttRest.services.rest.utilisateur;

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
	 * @return la liste des utilisateurs au format JSON
	 */
	public List<MessageUtilisateurMqttRest> traiteDemandeListeUtilisateurs();

	/**
	 * Traite une demande d'obtention de l'utilisateur connect�
	 * 
	 * @return l'utilisateur connect� au format JSON, cha�ne vide si pas
	 *         d'utilisateur connect�
	 */
	public MessageUtilisateurMqttRest traiteDemandeUtilisateurConnecte();

	/**
	 * Indique si le login et le mot de passe associ� sont valides
	 * 
	 * @param pLogin
	 *            le login de l'utilisateur
	 * @param pMotPasse
	 *            le mot de passe associ� au login
	 * @return true ou false au format JSON
	 */
	public boolean estValide(String pLogin, String pMotPasse);

}
