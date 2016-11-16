package signature.mqttRest.services.rest.serveur.informationPegase;

import java.util.List;

import signature.mqttRest.objetsPartages.etatEtPilotage.MessageProprietesArmoireMqttRest;
import signature.mqttRest.objetsPartages.etatEtPilotage.MessageProprietesEquipementMqttRest;

/**
 * Traitement des requêtes de récupération d'information sur Pegase
 * 
 * @author SDARIZCUREN
 *
 */
public interface ITraitementRequetesInformationPegase {

	/**
	 * Traite une demande d'obtention des propriétés d'un équipement
	 * 
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'expéditeur : peut être vide
	 * @param pReferenceCommande
	 *            la référence unique de la demande : peut être vide
	 * @param pIdEquipement
	 *            l'id de l'équipement concerné
	 * @return les propriétés d'un équipement, null si problème
	 */
	public MessageProprietesEquipementMqttRest traiteDemandeProprietesEquipement(String pIdentifiantExpediteur,
			String pReferenceCommande, String pIdEquipement);

	/**
	 * Traite une demande d'obtention des propriétés des équipements
	 * 
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'expéditeur : peut être vide
	 * @param pReferenceCommande
	 *            la référence unique de la demande : peut être vide
	 * @return les propriétés des équipements
	 */
	public List<MessageProprietesEquipementMqttRest> traiteDemandeProprietesEquipements(String pIdentifiantExpediteur,
			String pReferenceCommande);

	/**
	 * Traite une demande d'obtention des propriétés d'une armoire
	 * 
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'expéditeur : peut être vide
	 * @param pReferenceCommande
	 *            la référence unique de la demande : peut être vide
	 * @param pIdArmoire
	 *            l'id de l'armoire concernée
	 * @return les propriétés d'une armoire, null si problème
	 */
	public MessageProprietesArmoireMqttRest traiteDemandeProprietesArmoire(String pIdentifiantExpediteur,
			String pReferenceCommande, String pIdArmoire);

	/**
	 * Traite une demande d'obtention des propriétés des armoires
	 * 
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'expéditeur : peut être vide
	 * @param pReferenceCommande
	 *            la référence unique de la demande : peut être vide
	 * @return les propriétés des armoires
	 */
	public List<MessageProprietesArmoireMqttRest> traiteDemandeProprietesArmoires(String pIdentifiantExpediteur,
			String pReferenceCommande);
}
