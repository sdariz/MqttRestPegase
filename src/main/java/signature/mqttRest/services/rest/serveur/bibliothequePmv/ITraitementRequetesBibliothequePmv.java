package signature.mqttRest.services.rest.serveur.bibliothequePmv;

import java.util.List;

import signature.mqttRest.objetsPartages.etatEtPilotage.MessagePmvMqttRest;

/**
 * Traitement des requ�tes concernant la biblioth�que Pmv
 * 
 * @author SDARIZCUREN
 *
 */
public interface ITraitementRequetesBibliothequePmv {

	/**
	 * Traite une demande d'obtention de la liste des cat�gories de la
	 * biblioth�que
	 * 
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'exp�diteur : peut �tre vide
	 * @param pReferenceCommande
	 *            la r�f�rence unique de la demande : peut �tre vide
	 * @return la liste des cat�gories
	 */
	public List<String> traiteDemandeCategoriesBibliothequePmv(String pIdentifiantExpediteur,
			String pReferenceCommande);

	/**
	 * Traite une demande d'obtention de la liste des noms des messages dans une
	 * cat�gorie de la biblioth�que
	 * 
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'exp�diteur : peut �tre vide
	 * @param pReferenceCommande
	 *            la r�f�rence unique de la demande : peut �tre vide
	 * @param pCategorie
	 *            la cat�gorie concern�e
	 * @return la liste des noms des messages de la cat�gorie
	 */
	public List<String> traiteDemandeMessagesDansCategorieBibliothequePmv(String pIdentifiantExpediteur,
			String pReferenceCommande, String pCategorie);

	/**
	 * Traite une demande d'obtention d'un message de la biblioth�que
	 * 
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'exp�diteur : peut �tre vide
	 * @param pReferenceCommande
	 *            la r�f�rence unique de la demande : peut �tre vide
	 * @param pCategorie
	 *            la cat�gorie concern�e
	 * @param pNom
	 *            le nom du message
	 * @return le message recherch� ou null si probl�me
	 */
	public MessagePmvMqttRest traiteDemandeMessageBibliothequePmv(String pIdentifiantExpediteur,
			String pReferenceCommande, String pCategorie, String pNom);
}
