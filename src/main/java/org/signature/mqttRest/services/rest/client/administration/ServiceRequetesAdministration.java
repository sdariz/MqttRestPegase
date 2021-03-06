package org.signature.mqttRest.services.rest.client.administration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.signature.mqttRest.services.rest.client.ClientHttpRest;
import org.signature.mqttRest.services.rest.serveur.administration.GestionnaireRoutesAdministration;
import org.signature.mqttRest.util.Util;

/**
 * Services d'envoi de requ?tes vers le serveur REST, et retour de la r?ponse.
 * Requ?tes permettant d'administrer le syst?me : interdiction des pilotages,
 * test de pr?sence, ...
 * 
 * @author SDARIZCUREN
 *
 */
public class ServiceRequetesAdministration {

	/**
	 * Envoi au serveur REST une interdiction ou autorisation de pilotage
	 * 
	 * @param pHost                  l'adresse IP du serveur REST
	 * @param pPort                  le port TCP utilis? par le serveur
	 * @param pIdentifiantExpediteur l'identifiant unique de l'exp?diteur : peut
	 *                               ?tre vide
	 * @param pReferenceCommande     la r?f?rence unique de la demande : peut ?tre
	 *                               vide
	 * @param pInterdit              true pour interdire les pilotages sur Pegase
	 */
	public static void requeteInterdictionPilotages(String pHost, int pPort, String pIdentifiantExpediteur,
			String pReferenceCommande, boolean pInterdit) {
		if (pIdentifiantExpediteur == null) {
			pIdentifiantExpediteur = "";
		}

		if (pReferenceCommande == null) {
			pReferenceCommande = "";
		}

		// Param?tre de la requette
		Map<String, String> params = new HashMap<>();
		params.put("interdiction", Boolean.toString(pInterdit));
		params.put("idExpediteur", pIdentifiantExpediteur);
		params.put("idCommande", pReferenceCommande);

		ClientHttpRest.envoiRequetePOST(pHost, pPort, GestionnaireRoutesAdministration.INTERDICTION_PILOTAGES, params);
	}

	/**
	 * Envoi au serveur REST une interdiction ou autorisation de pilotage sur des
	 * ?quipements
	 * 
	 * @param pHost                  l'adresse IP du serveur REST
	 * @param pPort                  le port TCP utilis? par le serveur
	 * @param pIdentifiantExpediteur l'identifiant unique de l'exp?diteur : peut
	 *                               ?tre vide
	 * @param pReferenceCommande     la r?f?rence unique de la demande : peut ?tre
	 *                               vide
	 * @param pIdsEquipements        les identifiants des ?quipements concern?s
	 * @param pInterdit              true pour interdire les pilotages sur Pegase
	 */
	public static void requeteInterdictionPilotages(String pHost, int pPort, String pIdentifiantExpediteur,
			String pReferenceCommande, List<String> pIdsEquipements, boolean pInterdit) {
		if (pIdentifiantExpediteur == null) {
			pIdentifiantExpediteur = "";
		}

		if (pReferenceCommande == null) {
			pReferenceCommande = "";
		}

		// Param?tre de la requette
		Map<String, String> params = new HashMap<>();
		params.put("idExpediteur", pIdentifiantExpediteur);
		params.put("idCommande", pReferenceCommande);
		params.put("equipements", Util.listObjectToJsonString(pIdsEquipements));

		params.put("interdiction", Boolean.toString(pInterdit));

		ClientHttpRest.envoiRequetePOST(pHost, pPort, GestionnaireRoutesAdministration.INTERDICTION_PILOTAGES, params);
	}

	/**
	 * Envoi au serveur REST un test de pr?sence
	 * 
	 * @param pHost                  l'adresse IP du serveur REST
	 * @param pPort                  le port TCP utilis? par le serveur
	 * @param pIdentifiantExpediteur l'identifiant unique de l'exp?diteur : peut
	 *                               ?tre vide
	 * @param pReferenceCommande     la r?f?rence unique de la demande : peut ?tre
	 *                               vide
	 * @return true si r?ponse OK du serveur, false si probl?me
	 */
	public static boolean requeteTestPresence(String pHost, int pPort, String pIdentifiantExpediteur,
			String pReferenceCommande) {
		if (pIdentifiantExpediteur == null) {
			pIdentifiantExpediteur = "";
		}

		if (pReferenceCommande == null) {
			pReferenceCommande = "";
		}

		// Param?tre de la requette
		Map<String, String> params = new HashMap<>();
		params.put("idExpediteur", pIdentifiantExpediteur);
		params.put("idCommande", pReferenceCommande);

		String json = ClientHttpRest.envoiRequeteGET(pHost, pPort, GestionnaireRoutesAdministration.TEST_PRESENCE,
				params);
		if (json.length() == 0) {
			return false;
		}

		return Util.jsonToBoolean(json);
	}

	/**
	 * Envoi au serveur REST une demande d'activation ou d?sactivation d'un bouton
	 * 
	 * @param pHost                  l'adresse IP du serveur REST
	 * @param pPort                  le port TCP utilis? par le serveur
	 * @param pIdentifiantExpediteur l'identifiant unique de l'exp?diteur : peut
	 *                               ?tre vide
	 * @param pReferenceCommande     la r?f?rence unique de la demande : peut ?tre
	 *                               vide
	 * @param pIdBouton              l'identifiant du bouton
	 * @param pActif                 true pour activer, false pour d?sactiver
	 */
	public static void requeteActivationBouton(String pHost, int pPort, String pIdentifiantExpediteur,
			String pReferenceCommande, String pIdBouton, boolean pActif) {
		if (pIdentifiantExpediteur == null) {
			pIdentifiantExpediteur = "";
		}

		if (pReferenceCommande == null) {
			pReferenceCommande = "";
		}

		// Param?tre de la requette
		Map<String, String> params = new HashMap<>();
		params.put("idBouton", pIdBouton);
		params.put("actif", Boolean.toString(pActif));
		params.put("idExpediteur", pIdentifiantExpediteur);
		params.put("idCommande", pReferenceCommande);

		ClientHttpRest.envoiRequetePOST(pHost, pPort, GestionnaireRoutesAdministration.ACTIVATION_BOUTON, params);
	}

	/**
	 * Envoi au serveur REST une demande de lancement de l'action rattach?e ? un
	 * bouton
	 * 
	 * @param pHost                  l'adresse IP du serveur REST
	 * @param pPort                  le port TCP utilis? par le serveur
	 * @param pIdentifiantExpediteur l'identifiant unique de l'exp?diteur : peut
	 *                               ?tre vide
	 * @param pReferenceCommande     la r?f?rence unique de la demande : peut ?tre
	 *                               vide
	 * @param pIdBouton              l'identifiant du bouton
	 */
	public static void requeteLancementActionBouton(String pHost, int pPort, String pIdentifiantExpediteur,
			String pReferenceCommande, String pIdBouton) {
		if (pIdentifiantExpediteur == null) {
			pIdentifiantExpediteur = "";
		}

		if (pReferenceCommande == null) {
			pReferenceCommande = "";
		}

		// Param?tre de la requette
		Map<String, String> params = new HashMap<>();
		params.put("idBouton", pIdBouton);
		params.put("idExpediteur", pIdentifiantExpediteur);
		params.put("idCommande", pReferenceCommande);

		ClientHttpRest.envoiRequetePOST(pHost, pPort, GestionnaireRoutesAdministration.LANCEMENT_ACTION_BOUTON, params);
	}

	/**
	 * For?age de l'arret de l'application h?bergeant le serveur REST
	 * 
	 * @param pHost                  l'adresse IP du serveur REST
	 * @param pPort                  le port TCP utilis? par le serveur
	 * @param pIdentifiantExpediteur l'identifiant unique de l'exp?diteur : peut
	 *                               ?tre vide
	 * @param pReferenceCommande     la r?f?rence unique de la demande : peut ?tre
	 *                               vide
	 */
	public static void requeteForcageArretApplication(String pHost, int pPort, String pIdentifiantExpediteur,
			String pReferenceCommande) {
		if (pIdentifiantExpediteur == null) {
			pIdentifiantExpediteur = "";
		}

		if (pReferenceCommande == null) {
			pReferenceCommande = "";
		}

		// Param?tre de la requette
		Map<String, String> params = new HashMap<>();
		params.put("idExpediteur", pIdentifiantExpediteur);
		params.put("idCommande", pReferenceCommande);

		ClientHttpRest.envoiRequetePOST(pHost, pPort, GestionnaireRoutesAdministration.FORCAGE_ARRET_APPLICATION,
				params);
	}

	/**
	 * Demande la description au format JSON des donn?es de travail sauvegard?s. Ce
	 * sont toutes les donn?es except?s les alarmes et ?v?nements. mAis avec les
	 * images utilis?s (synoptiques, pictogrammes, PPAD, ...) au format base64
	 * 
	 * @param pHost                  l'adresse IP du serveur REST
	 * @param pPort                  le port TCP utilis? par le serveur
	 * @param pIdentifiantExpediteur l'identifiant unique de l'exp?diteur : peut
	 *                               ?tre vide
	 * @param pReferenceCommande     la r?f?rence unique de la demande : peut ?tre
	 *                               vide
	 * @return la description au format JSON
	 */
	public static String requeteDemandeDecriptionDonnesTravailSauvegardes(String pHost, int pPort,
			String pIdentifiantExpediteur, String pReferenceCommande) {
		if (pIdentifiantExpediteur == null) {
			pIdentifiantExpediteur = "";
		}

		if (pReferenceCommande == null) {
			pReferenceCommande = "";
		}

		// Param?tre de la requette
		Map<String, String> params = new HashMap<>();
		params.put("idExpediteur", pIdentifiantExpediteur);
		params.put("idCommande", pReferenceCommande);

		return ClientHttpRest.envoiRequeteGET(pHost, pPort,
				GestionnaireRoutesAdministration.DESCRIPTION_JSON_DONNES_TRAVAIL, params);
	}

}
