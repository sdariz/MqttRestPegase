package signature.mqttRest.services.rest.client.etatEtPilotage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import signature.mqttRest.objetsPartages.etatEtPilotage.MessageEtatAffichageMqttRest;
import signature.mqttRest.objetsPartages.etatEtPilotage.MessageEtatTechniqueMqttRest;
import signature.mqttRest.services.rest.client.ClientHttpRest;
import signature.mqttRest.services.rest.serveur.etatEtPilotage.GestionnaireRoutesEtatEtPilotage;
import signature.mqttRest.util.Util;

/**
 * Services d'envoi de requ�tes vers le serveur REST, et retour de la r�ponse.
 * 
 * @author SDARIZCUREN
 *
 */
public class ServiceRequetesEtatEtPilotage {

	/**
	 * Demande d'�tat d'affichage d'un �quipement
	 * 
	 * @param pHost
	 *            l'adresse IP du serveur REST
	 * @param pPort
	 *            le port TCP utilis� par le serveur
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'exp�diteur : peut �tre vide
	 * @param pReferenceCommande
	 *            la r�f�rence unique de la demande : peut �tre vide
	 * @param pId
	 *            l'id de l'�quipement � interroger
	 * @return l'�tat d'affichage de l'�quipement, ou null si probl�me
	 */
	public static MessageEtatAffichageMqttRest requeteDemandeEtatAffichageEquipement(String pHost, int pPort,
			String pIdentifiantExpediteur, String pReferenceCommande, String pId) {
		if (pIdentifiantExpediteur == null) {
			pIdentifiantExpediteur = "";
		}

		if (pReferenceCommande == null) {
			pReferenceCommande = "";
		}

		Map<String, String> params = new HashMap<>();
		params.put("id", pId);
		params.put("idExpediteur", pIdentifiantExpediteur);
		params.put("idCommande", pReferenceCommande);

		String json = ClientHttpRest.envoiRequeteGET(pHost, pPort,
				GestionnaireRoutesEtatEtPilotage.ETAT_AFFICHAGE_EQUIPEMENT, params);
		if (json.length() == 0) {
			return null;
		}

		return (MessageEtatAffichageMqttRest) Util.jsonToObjet(json, MessageEtatAffichageMqttRest.class);
	}

	/**
	 * Demande d'�tat d'affichage de tous les �quipements
	 * 
	 * @param pHost
	 *            l'adresse IP du serveur REST
	 * @param pPort
	 *            le port TCP utilis� par le serveur
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'exp�diteur : peut �tre vide
	 * @param pReferenceCommande
	 *            la r�f�rence unique de la demande : peut �tre vide
	 * @return l'�tat d'affichage des �quipements, liste vide si probl�me
	 */
	public static List<MessageEtatAffichageMqttRest> requeteDemandeEtatAffichageEquipements(String pHost, int pPort,
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
				GestionnaireRoutesEtatEtPilotage.ETAT_AFFICHAGE_EQUIPEMENT, params);
		if (json.length() == 0) {
			return new ArrayList<>();
		}

		// Formatage du retour vers le bon format
		List<MessageEtatAffichageMqttRest> retour = Util.jsonToListeObjet(json, MessageEtatAffichageMqttRest.class)
				.stream().map(MessageEtatAffichageMqttRest.class::cast).collect(Collectors.toList());

		return retour;
	}

	/**
	 * Demande d'�tat technique d'un �quipement
	 * 
	 * @param pHost
	 *            l'adresse IP du serveur REST
	 * @param pPort
	 *            le port TCP utilis� par le serveur
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'exp�diteur : peut �tre vide
	 * @param pReferenceCommande
	 *            la r�f�rence unique de la demande : peut �tre vide
	 * @param pId
	 *            l'id de l'�quipement � interroger
	 * @return l'�tat technique de l'�quipement, ou null si probl�me
	 */
	public static MessageEtatTechniqueMqttRest requeteDemandeEtatTechniqueEquipement(String pHost, int pPort,
			String pIdentifiantExpediteur, String pReferenceCommande, String pId) {
		if (pIdentifiantExpediteur == null) {
			pIdentifiantExpediteur = "";
		}

		if (pReferenceCommande == null) {
			pReferenceCommande = "";
		}

		Map<String, String> params = new HashMap<>();
		params.put("id", pId);
		params.put("idExpediteur", pIdentifiantExpediteur);
		params.put("idCommande", pReferenceCommande);

		String json = ClientHttpRest.envoiRequeteGET(pHost, pPort,
				GestionnaireRoutesEtatEtPilotage.ETAT_TECHNIQUE_EQUIPEMENT, params);
		if (json.length() == 0) {
			return null;
		}

		return (MessageEtatTechniqueMqttRest) Util.jsonToObjet(json, MessageEtatTechniqueMqttRest.class);
	}

	/**
	 * Demande d'�tat technique de tous les �quipements
	 * 
	 * @param pHost
	 *            l'adresse IP du serveur REST
	 * @param pPort
	 *            le port TCP utilis� par le serveur
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'exp�diteur : peut �tre vide
	 * @param pReferenceCommande
	 *            la r�f�rence unique de la demande : peut �tre vide
	 * @return l'�tat technique des �quipements, liste vide si probl�me
	 */
	public static List<MessageEtatTechniqueMqttRest> requeteDemandeEtatTechniqueEquipements(String pHost, int pPort,
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
				GestionnaireRoutesEtatEtPilotage.ETAT_TECHNIQUE_EQUIPEMENT, params);
		if (json.length() == 0) {
			return new ArrayList<>();
		}

		// Formatage du retour vers le bon format
		List<MessageEtatTechniqueMqttRest> retour = Util.jsonToListeObjet(json, MessageEtatTechniqueMqttRest.class)
				.stream().map(MessageEtatTechniqueMqttRest.class::cast).collect(Collectors.toList());

		return retour;
	}

	/**
	 * Demande � actualiser l'�tat d'un �quipement, en for�ant une interrogation
	 * de l'�quipement sur le terrain
	 * 
	 * @param pHost
	 *            l'adresse IP du serveur REST
	 * @param pPort
	 *            le port TCP utilis� par le serveur
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'exp�diteur : peut �tre vide
	 * @param pReferenceCommande
	 *            la r�f�rence unique de la demande : peut �tre vide
	 * @param pId
	 *            l'id de l'�quipement � interroger
	 */
	public static void requeteActualisationEtatEquipement(String pHost, int pPort, String pIdentifiantExpediteur,
			String pReferenceCommande, String pId) {
		if (pIdentifiantExpediteur == null) {
			pIdentifiantExpediteur = "";
		}

		if (pReferenceCommande == null) {
			pReferenceCommande = "";
		}

		// Param�tre de la requette
		Map<String, String> params = new HashMap<>();
		params.put("idEquipement", pId);
		params.put("idExpediteur", pIdentifiantExpediteur);
		params.put("idCommande", pReferenceCommande);

		ClientHttpRest.envoiRequetePOST(pHost, pPort, GestionnaireRoutesEtatEtPilotage.ACTUALISATION_ETAT_EQUIPEMENT,
				params);
	}

}
