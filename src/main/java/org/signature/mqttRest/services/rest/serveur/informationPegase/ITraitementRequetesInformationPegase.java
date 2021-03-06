package org.signature.mqttRest.services.rest.serveur.informationPegase;

import java.util.List;

import org.signature.mqttRest.objetsPartages.informationPegase.MessageProprietesArmoireMqttRest;
import org.signature.mqttRest.objetsPartages.informationPegase.MessageProprietesEquipementMqttRest;
import org.signature.mqttRest.objetsPartages.informationPegase.MessageProprietesEquipementWebMqttRest;

/**
 * Traitement des requ?tes de r?cup?ration d'information sur Pegase
 * 
 * @author SDARIZCUREN
 *
 */
public interface ITraitementRequetesInformationPegase {

	/**
	 * Traite une demande d'obtention des propri?t?s d'un ?quipement
	 * 
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'exp?diteur : peut ?tre vide
	 * @param pReferenceCommande
	 *            la r?f?rence unique de la demande : peut ?tre vide
	 * @param pIdEquipement
	 *            l'id de l'?quipement concern?
	 * @return les propri?t?s d'un ?quipement, null si probl?me
	 */
	public MessageProprietesEquipementMqttRest traiteDemandeProprietesEquipement(String pIdentifiantExpediteur,
			String pReferenceCommande, String pIdEquipement);

	/**
	 * Traite une demande d'obtention des propri?t?s des ?quipements
	 * 
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'exp?diteur : peut ?tre vide
	 * @param pReferenceCommande
	 *            la r?f?rence unique de la demande : peut ?tre vide
	 * @return les propri?t?s des ?quipements
	 */
	public List<MessageProprietesEquipementMqttRest> traiteDemandeProprietesEquipements(String pIdentifiantExpediteur,
			String pReferenceCommande);

	/**
	 * Traite une demande d'obtention des propri?t?s d'une armoire
	 * 
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'exp?diteur : peut ?tre vide
	 * @param pReferenceCommande
	 *            la r?f?rence unique de la demande : peut ?tre vide
	 * @param pIdEquipement
	 *            l'identifiant d'un ?quipement de l'armoire ? interroger
	 * @return les propri?t?s d'une armoire, null si probl?me
	 */
	public MessageProprietesArmoireMqttRest traiteDemandeProprietesArmoire(String pIdentifiantExpediteur,
			String pReferenceCommande, String pIdEquipement);

	/**
	 * Traite une demande d'obtention des propri?t?s des armoires
	 * 
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'exp?diteur : peut ?tre vide
	 * @param pReferenceCommande
	 *            la r?f?rence unique de la demande : peut ?tre vide
	 * @return les propri?t?s des armoires
	 */
	public List<MessageProprietesArmoireMqttRest> traiteDemandeProprietesArmoires(String pIdentifiantExpediteur,
			String pReferenceCommande);

	/**
	 * Traite une demande d'obtention des propri?t?s d'un ?quipement web :
	 * positionnement sur un synoptique, ...
	 * 
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'exp?diteur : peut ?tre vide
	 * @param pReferenceCommande
	 *            la r?f?rence unique de la demande : peut ?tre vide
	 * @param pIdEquipement
	 *            l'id de l'?quipement concern?
	 * @return les propri?t?s d'un ?quipement web, null si probl?me
	 */
	public MessageProprietesEquipementWebMqttRest traiteDemandeProprietesEquipementWeb(String pIdentifiantExpediteur,
			String pReferenceCommande, String pIdEquipement);

	/**
	 * Traite une demande d'obtention des propri?t?s des ?quipements web :
	 * positionnement sur un synoptique, ...
	 * 
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'exp?diteur : peut ?tre vide
	 * @param pReferenceCommande
	 *            la r?f?rence unique de la demande : peut ?tre vide
	 * @return les propri?t?s des ?quipements
	 */
	public List<MessageProprietesEquipementWebMqttRest> traiteDemandeProprietesEquipementsWeb(String pIdentifiantExpediteur,
			String pReferenceCommande);

}
