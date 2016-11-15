package signature.mqttRest.services.rest.serveur.etatEtPilotage;

import java.util.List;

import signature.mqttRest.objetsPartages.etatEtPilotage.MessageEtatAffichageMqttRest;
import signature.mqttRest.objetsPartages.etatEtPilotage.MessageEtatTechniqueMqttRest;

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
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'expéditeur : peut être vide
	 * @param pReferenceCommande
	 *            la référence unique de la demande : peut être vide
	 * @param pIdEquipement
	 *            l'id de l'équipement
	 * @return l'état d'affichage de l'équipement
	 */
	public MessageEtatAffichageMqttRest demandeEtatAffichageEquipement(String pIdentifiantExpediteur,
			String pReferenceCommande, String pIdEquipement);

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
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'expéditeur : peut être vide
	 * @param pReferenceCommande
	 *            la référence unique de la demande : peut être vide
	 * @param pIdEquipement
	 *            l'id de l'équipement
	 * @return l'état technique de l'équipement
	 */
	public MessageEtatTechniqueMqttRest demandeEtatTechniqueEquipement(String pIdentifiantExpediteur,
			String pReferenceCommande, String pIdEquipement);

	/**
	 * Traite une demande d'état technique pour tous les équipements
	 * 
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'expéditeur : peut être vide
	 * @param pReferenceCommande
	 *            la référence unique de la demande : peut être vide
	 * @return l'état technique des équipements
	 */
	public List<MessageEtatTechniqueMqttRest> demandeEtatTechniqueEquipements(String pIdentifiantExpediteur,
			String pReferenceCommande);

	/**
	 * Traite une demande d'actualisation de l'état d'un équipement, en forçant
	 * une interrogation de l'équipement sur le terrain
	 * 
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'expéditeur : peut être vide
	 * @param pReferenceCommande
	 *            la référence unique de la demande : peut être vide
	 * @param pIdEquipement
	 *            l'id de l'équipement à rafraîchir
	 */
	public void demandeActualisationEtatEquipement(String pIdentifiantExpediteur, String pReferenceCommande,
			String pIdEquipement);
}
