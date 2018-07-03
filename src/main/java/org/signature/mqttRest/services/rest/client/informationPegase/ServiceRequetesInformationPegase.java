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
 * Requêtes vers le serveur REST pour récupérer des informations sur Pegase
 * 
 * @author SDARIZCUREN
 *
 */
public class ServiceRequetesInformationPegase {

	/**
	 * Envoi au serveur REST une demande de propriétés d'un équipement
	 * 
	 * @param pHost
	 *            l'adresse IP du serveur REST
	 * @param pPort
	 *            le port TCP utilisé par le serveur
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'expéditeur : peut être vide
	 * @param pReferenceCommande
	 *            la référence unique de la demande : peut être vide
	 * @param pIdEquipement
	 *            l'id de l'équipement concerné
	 * @return les propriétés d'un équipement, ou null si problème
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
	 * Envoi au serveur REST une demande des propriétés des équipements
	 * 
	 * @param pHost
	 *            l'adresse IP du serveur REST
	 * @param pPort
	 *            le port TCP utilisé par le serveur
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'expéditeur : peut être vide
	 * @param pReferenceCommande
	 *            la référence unique de la demande : peut être vide
	 * @return les propriétés des équipements
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
	 * Envoi au serveur REST une demande de propriétés d'une armoire
	 * 
	 * @param pHost
	 *            l'adresse IP du serveur REST
	 * @param pPort
	 *            le port TCP utilisé par le serveur
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'expéditeur : peut être vide
	 * @param pReferenceCommande
	 *            la référence unique de la demande : peut être vide
	 * @param pIdEquipement
	 *            l'identifiant d'un équipement de l'armoire à interroger
	 * @return les propriétés d'une armoire, ou null si problème
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
	 * Envoi au serveur REST une demande des propriétés des armoires
	 * 
	 * @param pHost
	 *            l'adresse IP du serveur REST
	 * @param pPort
	 *            le port TCP utilisé par le serveur
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'expéditeur : peut être vide
	 * @param pReferenceCommande
	 *            la référence unique de la demande : peut être vide
	 * @return les propriétés des armoires
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
	 * Envoi au serveur REST une demande de propriétés d'un équipement sur les synoptiques web
	 * 
	 * @param pHost
	 *            l'adresse IP du serveur REST
	 * @param pPort
	 *            le port TCP utilisé par le serveur
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'expéditeur : peut être vide
	 * @param pReferenceCommande
	 *            la référence unique de la demande : peut être vide
	 * @param pIdEquipement
	 *            l'id de l'équipement concerné
	 * @return les propriétés web d'un équipement, ou null si problème
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
	 * Envoi au serveur REST une demande des propriétés des équipements sur les synoptiques web
	 * 
	 * @param pHost
	 *            l'adresse IP du serveur REST
	 * @param pPort
	 *            le port TCP utilisé par le serveur
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'expéditeur : peut être vide
	 * @param pReferenceCommande
	 *            la référence unique de la demande : peut être vide
	 * @return les propriétés des équipements web
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
