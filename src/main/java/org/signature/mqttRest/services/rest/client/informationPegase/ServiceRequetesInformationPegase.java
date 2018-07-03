package org.signature.mqttRest.services.rest.client.informationPegase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.signature.mqttRest.objetsPartages.informationPegase.MessageProprietesArmoireMqttRest;
import org.signature.mqttRest.objetsPartages.informationPegase.MessageProprietesEquipementMqttRest;
import org.signature.mqttRest.objetsPartages.informationPegase.MessageProprietesEquipementWebMqttRest;
import org.signature.mqttRest.services.rest.client.ClientHttpRest;
import org.signature.mqttRest.services.rest.serveur.informationPegase.GestionnaireRoutesInformationPegase;
import org.signature.mqttRest.util.Util;

/**
 * Requ�tes vers le serveur REST pour r�cup�rer des informations sur Pegase
 * 
 * @author SDARIZCUREN
 *
 */
public class ServiceRequetesInformationPegase {

	/**
	 * Envoi au serveur REST une demande de propri�t�s d'un �quipement
	 * 
	 * @param pHost
	 *            l'adresse IP du serveur REST
	 * @param pPort
	 *            le port TCP utilis� par le serveur
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'exp�diteur : peut �tre vide
	 * @param pReferenceCommande
	 *            la r�f�rence unique de la demande : peut �tre vide
	 * @param pIdEquipement
	 *            l'id de l'�quipement concern�
	 * @return les propri�t�s d'un �quipement, ou null si probl�me
	 */
	public static MessageProprietesEquipementMqttRest requeteDemandeProprietesEquipement(String pHost, int pPort,
			String pIdentifiantExpediteur, String pReferenceCommande, String pIdEquipement) {
		if (pIdentifiantExpediteur == null) {
			pIdentifiantExpediteur = "";
		}

		if (pReferenceCommande == null) {
			pReferenceCommande = "";
		}

		Map<String, String> params = new HashMap<>();
		params.put("idExpediteur", pIdentifiantExpediteur);
		params.put("idCommande", pReferenceCommande);
		params.put("idEquipement", pIdEquipement);

		String json = ClientHttpRest.envoiRequeteGET(pHost, pPort,
				GestionnaireRoutesInformationPegase.PROPRIETES_EQUIPEMENT, params);
		if (json.length() == 0) {
			return null;
		}

		// Formatage du retour vers le bon format
		return (MessageProprietesEquipementMqttRest) Util.jsonToObjet(json, MessageProprietesEquipementMqttRest.class);
	}

	/**
	 * Envoi au serveur REST une demande des propri�t�s des �quipements
	 * 
	 * @param pHost
	 *            l'adresse IP du serveur REST
	 * @param pPort
	 *            le port TCP utilis� par le serveur
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'exp�diteur : peut �tre vide
	 * @param pReferenceCommande
	 *            la r�f�rence unique de la demande : peut �tre vide
	 * @return les propri�t�s des �quipements
	 */
	public static List<MessageProprietesEquipementMqttRest> requeteDemandeProprietesEquipements(String pHost, int pPort,
			String pIdentifiantExpediteur, String pReferenceCommande) {
		if (pIdentifiantExpediteur == null) {
			pIdentifiantExpediteur = "";
		}

		if (pReferenceCommande == null) {
			pReferenceCommande = "";
		}

		Map<String, String> params = new HashMap<>();
		params.put("idExpediteur", pIdentifiantExpediteur);
		params.put("idCommande", pReferenceCommande);

		String json = ClientHttpRest.envoiRequeteGET(pHost, pPort,
				GestionnaireRoutesInformationPegase.PROPRIETES_EQUIPEMENT, params);
		if (json.length() == 0) {
			return new ArrayList<>();
		}

		// Formatage du retour vers le bon format
		return Util.jsonToListeObjet(json, MessageProprietesEquipementMqttRest.class).stream()
				.map(MessageProprietesEquipementMqttRest.class::cast).collect(Collectors.toList());
	}

	/**
	 * Envoi au serveur REST une demande de propri�t�s d'une armoire
	 * 
	 * @param pHost
	 *            l'adresse IP du serveur REST
	 * @param pPort
	 *            le port TCP utilis� par le serveur
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'exp�diteur : peut �tre vide
	 * @param pReferenceCommande
	 *            la r�f�rence unique de la demande : peut �tre vide
	 * @param pIdEquipement
	 *            l'identifiant d'un �quipement de l'armoire � interroger
	 * @return les propri�t�s d'une armoire, ou null si probl�me
	 */
	public static MessageProprietesArmoireMqttRest requeteDemandeProprietesArmoire(String pHost, int pPort,
			String pIdentifiantExpediteur, String pReferenceCommande, String pIdEquipement) {
		if (pIdentifiantExpediteur == null) {
			pIdentifiantExpediteur = "";
		}

		if (pReferenceCommande == null) {
			pReferenceCommande = "";
		}

		Map<String, String> params = new HashMap<>();
		params.put("idExpediteur", pIdentifiantExpediteur);
		params.put("idCommande", pReferenceCommande);
		params.put("idEquipement", pIdEquipement);

		String json = ClientHttpRest.envoiRequeteGET(pHost, pPort,
				GestionnaireRoutesInformationPegase.PROPRIETES_ARMOIRE, params);
		if (json.length() == 0) {
			return null;
		}

		// Formatage du retour vers le bon format
		return (MessageProprietesArmoireMqttRest) Util.jsonToObjet(json, MessageProprietesArmoireMqttRest.class);
	}

	/**
	 * Envoi au serveur REST une demande des propri�t�s des armoires
	 * 
	 * @param pHost
	 *            l'adresse IP du serveur REST
	 * @param pPort
	 *            le port TCP utilis� par le serveur
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'exp�diteur : peut �tre vide
	 * @param pReferenceCommande
	 *            la r�f�rence unique de la demande : peut �tre vide
	 * @return les propri�t�s des armoires
	 */
	public static List<MessageProprietesArmoireMqttRest> requeteDemandeProprietesArmoires(String pHost, int pPort,
			String pIdentifiantExpediteur, String pReferenceCommande) {
		if (pIdentifiantExpediteur == null) {
			pIdentifiantExpediteur = "";
		}

		if (pReferenceCommande == null) {
			pReferenceCommande = "";
		}

		Map<String, String> params = new HashMap<>();
		params.put("idExpediteur", pIdentifiantExpediteur);
		params.put("idCommande", pReferenceCommande);

		String json = ClientHttpRest.envoiRequeteGET(pHost, pPort,
				GestionnaireRoutesInformationPegase.PROPRIETES_ARMOIRE, params);
		if (json.length() == 0) {
			return new ArrayList<>();
		}

		// Formatage du retour vers le bon format
		return Util.jsonToListeObjet(json, MessageProprietesArmoireMqttRest.class).stream()
				.map(MessageProprietesArmoireMqttRest.class::cast).collect(Collectors.toList());
	}
	
	/**
	 * Envoi au serveur REST une demande de propri�t�s d'un �quipement sur les synoptiques web
	 * 
	 * @param pHost
	 *            l'adresse IP du serveur REST
	 * @param pPort
	 *            le port TCP utilis� par le serveur
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'exp�diteur : peut �tre vide
	 * @param pReferenceCommande
	 *            la r�f�rence unique de la demande : peut �tre vide
	 * @param pIdEquipement
	 *            l'id de l'�quipement concern�
	 * @return les propri�t�s web d'un �quipement, ou null si probl�me
	 */
	public static MessageProprietesEquipementWebMqttRest requeteDemandeProprietesEquipementWeb(String pHost, int pPort,
			String pIdentifiantExpediteur, String pReferenceCommande, String pIdEquipement) {
		if (pIdentifiantExpediteur == null) {
			pIdentifiantExpediteur = "";
		}

		if (pReferenceCommande == null) {
			pReferenceCommande = "";
		}

		Map<String, String> params = new HashMap<>();
		params.put("idExpediteur", pIdentifiantExpediteur);
		params.put("idCommande", pReferenceCommande);
		params.put("idEquipement", pIdEquipement);

		String json = ClientHttpRest.envoiRequeteGET(pHost, pPort,
				GestionnaireRoutesInformationPegase.PROPRIETES_EQUIPEMENT_WEB, params);
		if (json.length() == 0) {
			return null;
		}

		// Formatage du retour vers le bon format
		return (MessageProprietesEquipementWebMqttRest) Util.jsonToObjet(json, MessageProprietesEquipementWebMqttRest.class);
	}
	
	/**
	 * Envoi au serveur REST une demande des propri�t�s des �quipements sur les synoptiques web
	 * 
	 * @param pHost
	 *            l'adresse IP du serveur REST
	 * @param pPort
	 *            le port TCP utilis� par le serveur
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'exp�diteur : peut �tre vide
	 * @param pReferenceCommande
	 *            la r�f�rence unique de la demande : peut �tre vide
	 * @return les propri�t�s des �quipements web
	 */
	public static List<MessageProprietesEquipementWebMqttRest> requeteDemandeProprietesEquipementsWeb(String pHost, int pPort,
			String pIdentifiantExpediteur, String pReferenceCommande) {
		if (pIdentifiantExpediteur == null) {
			pIdentifiantExpediteur = "";
		}

		if (pReferenceCommande == null) {
			pReferenceCommande = "";
		}

		Map<String, String> params = new HashMap<>();
		params.put("idExpediteur", pIdentifiantExpediteur);
		params.put("idCommande", pReferenceCommande);

		String json = ClientHttpRest.envoiRequeteGET(pHost, pPort,
				GestionnaireRoutesInformationPegase.PROPRIETES_EQUIPEMENT_WEB, params);
		if (json.length() == 0) {
			return new ArrayList<>();
		}

		// Formatage du retour vers le bon format
		return Util.jsonToListeObjet(json, MessageProprietesEquipementWebMqttRest.class).stream()
				.map(MessageProprietesEquipementWebMqttRest.class::cast).collect(Collectors.toList());
	}

}
