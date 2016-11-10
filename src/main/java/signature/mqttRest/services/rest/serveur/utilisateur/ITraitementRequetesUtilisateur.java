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
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'expéditeur : peut être vide
	 * @param pReferenceCommande
	 *            la référence unique de la demande : peut être vide
	 * 
	 * @return la liste des utilisateurs
	 */
	public List<MessageUtilisateurMqttRest> demandeListeUtilisateurs(String pIdentifiantExpediteur,
			String pReferenceCommande);

	/**
	 * Traite une demande d'obtention de l'utilisateur connecté
	 * 
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'expéditeur : peut être vide
	 * @param pReferenceCommande
	 *            la référence unique de la demande : peut être vide
	 * 
	 * @return l'utilisateur connecté, null si pas d'utilisateur connecté
	 */
	public MessageUtilisateurMqttRest demandeUtilisateurConnecte(String pIdentifiantExpediteur,
			String pReferenceCommande);

	/**
	 * Indique si le login et le mot de passe associé sont valides
	 * 
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'expéditeur : peut être vide
	 * @param pReferenceCommande
	 *            la référence unique de la demande : peut être vide
	 * @param pLogin
	 *            le login de l'utilisateur
	 * @param pMotPasse
	 *            le mot de passe associé au login
	 * @return true ou false
	 */
	public boolean estValide(String pIdentifiantExpediteur, String pReferenceCommande, String pLogin, String pMotPasse);

}
