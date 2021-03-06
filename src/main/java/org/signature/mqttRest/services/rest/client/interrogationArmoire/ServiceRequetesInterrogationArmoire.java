package org.signature.mqttRest.services.rest.client.interrogationArmoire;

import java.util.HashMap;
import java.util.Map;

import org.signature.mqttRest.services.rest.client.ClientHttpRest;
import org.signature.mqttRest.services.rest.serveur.interrogationArmoire.GestionnaireRoutesInterrogationArmoire;

/**
 * Requ?tes REST pour interroger une armoire : envoi de commande, mise ?
 * l'heure, ...
 * 
 * @author SDARIZCUREN
 *
 */
public class ServiceRequetesInterrogationArmoire {

	/**
	 * Envoi au serveur REST une demande de lancement de test sur les
	 * ?quipements d'une armoire
	 * 
	 * @param pHost
	 *            l'adresse IP du serveur REST
	 * @param pPort
	 *            le port TCP utilis? par le serveur
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'exp?diteur : peut ?tre vide
	 * @param pReferenceCommande
	 *            la r?f?rence unique de la demande : peut ?tre vide
	 * @param pIdEquipement
	 *            l'identifiant d'un ?quipement de l'armoire ? tester
	 */
	public static void requeteLancementTestEquipementsArmoire(String pHost, int pPort, String pIdentifiantExpediteur,
			String pReferenceCommande, String pIdEquipement) {
		if (pIdentifiantExpediteur == null) {
			pIdentifiantExpediteur = "";
		}

		if (pReferenceCommande == null) {
			pReferenceCommande = "";
		}

		// Param?tre de la requette
		Map<String, String> params = new HashMap<>();
		params.put("idEquipement", pIdEquipement);
		params.put("idExpediteur", pIdentifiantExpediteur);
		params.put("idCommande", pReferenceCommande);

		ClientHttpRest.envoiRequetePOST(pHost, pPort, GestionnaireRoutesInterrogationArmoire.LANCEMENT_TEST_EQUIPEMENTS,
				params);
	}

	/**
	 * Envoi au serveur REST une demande de remise ? l'heure d'une armoire
	 * 
	 * @param pHost
	 *            l'adresse IP du serveur REST
	 * @param pPort
	 *            le port TCP utilis? par le serveur
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'exp?diteur : peut ?tre vide
	 * @param pReferenceCommande
	 *            la r?f?rence unique de la demande : peut ?tre vide
	 * @param pIdEquipement
	 *            l'identifiant d'un ?quipement de l'armoire ? remettre ? l'heure
	 */
	public static void requeteRemiseHeureArmoire(String pHost, int pPort, String pIdentifiantExpediteur,
			String pReferenceCommande, String pIdEquipement) {
		if (pIdentifiantExpediteur == null) {
			pIdentifiantExpediteur = "";
		}

		if (pReferenceCommande == null) {
			pReferenceCommande = "";
		}

		// Param?tre de la requette
		Map<String, String> params = new HashMap<>();
		params.put("idEquipement", pIdEquipement);
		params.put("idExpediteur", pIdentifiantExpediteur);
		params.put("idCommande", pReferenceCommande);

		ClientHttpRest.envoiRequetePOST(pHost, pPort, GestionnaireRoutesInterrogationArmoire.REMISE_HEURE, params);
	}

	/**
	 * Envoi au serveur REST une interrogation pour une armoire
	 * 
	 * @param pHost
	 *            l'adresse IP du serveur REST
	 * @param pPort
	 *            le port TCP utilis? par le serveur
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'exp?diteur : peut ?tre vide
	 * @param pReferenceCommande
	 *            la r?f?rence unique de la demande : peut ?tre vide
	 * @param pIdEquipement
	 *            l'identifiant d'un ?quipement de l'armoire ? interroger
	 * @param pTrame
	 *            la trame ? envoyer ? l'armoire
	 * @return la r?ponse de l'armoire
	 */
	public static String requeteDemandeArmoire(String pHost, int pPort, String pIdentifiantExpediteur,
			String pReferenceCommande, String pIdEquipement, String pTrame) {
		if (pIdentifiantExpediteur == null) {
			pIdentifiantExpediteur = "";
		}

		if (pReferenceCommande == null) {
			pReferenceCommande = "";
		}

		Map<String, String> params = new HashMap<>();
		params.put("idEquipement", pIdEquipement);
		params.put("idExpediteur", pIdentifiantExpediteur);
		params.put("idCommande", pReferenceCommande);
		params.put("trame", pTrame);

		return ClientHttpRest.envoiRequetePOST(pHost, pPort, GestionnaireRoutesInterrogationArmoire.COMMANDE, params);
	}

}
