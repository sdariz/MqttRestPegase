package signature.mqttRest.services.rest.serveur.etatEtPilotage;

import java.util.List;

import signature.mqttRest.objetsPartages.etatEtPilotage.MessageEtatAffichageMqttRest;

/**
 * Traitement des requêtes d'état des équipements
 * 
 * @author SDARIZCUREN
 *
 */
public interface ITraitementRequetesEtatEquipements {

	/**
	 * Traite une demande d'état d'affichage d'un équipement
	 * 
	 * @param pId
	 *            l'id de l'équipement
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'expéditeur : peut être vide
	 * @param pReferenceCommande
	 *            la référence unique de la demande : peut être vide
	 * @return l'état d'affichage de l'équipement
	 */
	public MessageEtatAffichageMqttRest demandeEtatAffichageEquipement(String pId, String pIdentifiantExpediteur,
			String pReferenceCommande);

	/**
	 * Traite une demande d'état d'affichage pour tous les équipements
	 * 
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'expéditeur : peut être vide
	 * @param pReferenceCommande
	 *            la référence unique de la demande : peut être vide
	 * @return l'état d'affichage des équipements
	 */
	public List<MessageEtatAffichageMqttRest> demandeEtatAffichageEquipements(String pIdentifiantExpediteur,
			String pReferenceCommande);

	/**
	 * Traite une demande d'état technique d'un équipement
	 * 
	 * @param pId
	 *            l'id de l'équipement
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'expéditeur : peut être vide
	 * @param pReferenceCommande
	 *            la référence unique de la demande : peut être vide
	 * @return l'état technique au format JSON
	 */
	public String demandeEtatTechniqueEquipement(String pId, String pIdentifiantExpediteur, String pReferenceCommande);

	/**
	 * Traite une demande d'état technique pour tous les équipements
	 * 
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'expéditeur : peut être vide
	 * @param pReferenceCommande
	 *            la référence unique de la demande : peut être vide
	 * @return l'état technique des équipements au format JSON
	 */
	public String demandeEtatTechniqueEquipements(String pIdentifiantExpediteur, String pReferenceCommande);

	/**
	 * Traite une demande d'actualisation de l'état d'un équipement, en forçant
	 * une interrogation de l'équipement sur le terrain
	 * 
	 * @param pId
	 *            l'id de l'équipement à rafraîchir
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'expéditeur : peut être vide
	 * @param pReferenceCommande
	 *            la référence unique de la demande : peut être vide
	 */
	public void demandeActualisationEtatEquipement(String pId, String pIdentifiantExpediteur,
			String pReferenceCommande);
}
