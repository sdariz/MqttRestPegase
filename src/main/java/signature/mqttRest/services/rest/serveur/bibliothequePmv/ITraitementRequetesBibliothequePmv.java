package signature.mqttRest.services.rest.serveur.bibliothequePmv;

import java.util.List;

import signature.mqttRest.objetsPartages.etatEtPilotage.MessagePmvMqttRest;

/**
 * Traitement des requêtes concernant la bibliothèque Pmv
 * 
 * @author SDARIZCUREN
 *
 */
public interface ITraitementRequetesBibliothequePmv {

	/**
	 * Traite une demande d'obtention de la liste des catégories de la
	 * bibliothèque
	 * 
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'expéditeur : peut être vide
	 * @param pReferenceCommande
	 *            la référence unique de la demande : peut être vide
	 * @return la liste des catégories
	 */
	public List<String> traiteDemandeCategoriesBibliothequePmv(String pIdentifiantExpediteur,
			String pReferenceCommande);

	/**
	 * Traite une demande d'obtention de la liste des noms des messages dans une
	 * catégorie de la bibliothèque
	 * 
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'expéditeur : peut être vide
	 * @param pReferenceCommande
	 *            la référence unique de la demande : peut être vide
	 * @param pCategorie
	 *            la catégorie concernée
	 * @return la liste des noms des messages de la catégorie
	 */
	public List<String> traiteDemandeMessagesDansCategorieBibliothequePmv(String pIdentifiantExpediteur,
			String pReferenceCommande, String pCategorie);

	/**
	 * Traite une demande d'obtention d'un message de la bibliothèque
	 * 
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'expéditeur : peut être vide
	 * @param pReferenceCommande
	 *            la référence unique de la demande : peut être vide
	 * @param pCategorie
	 *            la catégorie concernée
	 * @param pNom
	 *            le nom du message
	 * @return le message recherché ou null si problème
	 */
	public MessagePmvMqttRest traiteDemandeMessageBibliothequePmv(String pIdentifiantExpediteur,
			String pReferenceCommande, String pCategorie, String pNom);
}
