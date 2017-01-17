package org.signature.mqttRest.services.rest.client.interrogationArmoire;

import java.util.HashMap;
import java.util.Map;

import org.signature.mqttRest.services.rest.client.ClientHttpRest;
import org.signature.mqttRest.services.rest.serveur.interrogationArmoire.GestionnaireRoutesInterrogationArmoire;

/**
 * Requêtes REST pour interroger une armoire : envoi de commande, mise à
 * l'heure, ...
 * 
 * @author SDARIZCUREN
 *
 */
public class ServiceRequetesInterrogationArmoire {

	/**
	 * Envoi au serveur REST une demande de lancement de test sur les
	 * équipements d'une armoire
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
	 *            l'identifiant d'un équipement de l'armoire à tester
	 */
	public static void requeteLancementTestEquipementsArmoire(String pHost, int pPort, String pIdentifiantExpediteur,
			String pReferenceCommande, String pIdEquipement) {
		if (pIdentifiantExpediteur == null) {
			pIdentifiantExpediteur = "";
		}

		if (pReferenceCommande == null) {
			pReferenceCommande = "";
		}

		// Paramètre de la requette
		Map<String, String> params = new HashMap<>();
		params.put("idEquipement", pIdEquipement);
		params.put("idExpediteur", pIdentifiantExpediteur);
		params.put("idCommande", pReferenceCommande);

		ClientHttpRest.envoiRequetePOST(pHost, pPort, GestionnaireRoutesInterrogationArmoire.LANCEMENT_TEST_EQUIPEMENTS,
				params);
	}

	/**
	 * Envoi au serveur REST une demande de remise à l'heure d'une armoire
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
	 *            l'identifiant d'un équipement de l'armoire à remettre à l'heure
	 */
	public static void requeteRemiseHeureArmoire(String pHost, int pPort, String pIdentifiantExpediteur,
			String pReferenceCommande, String pIdEquipement) {
		if (pIdentifiantExpediteur == null) {
			pIdentifiantExpediteur = "";
		}

		if (pReferenceCommande == null) {
			pReferenceCommande = "";
		}

		// Paramètre de la requette
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
	 *            le port TCP utilisé par le serveur
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'expéditeur : peut être vide
	 * @param pReferenceCommande
	 *            la référence unique de la demande : peut être vide
	 * @param pIdEquipement
	 *            l'identifiant d'un équipement de l'armoire à interroger
	 * @param pTrame
	 *            la trame à envoyer à l'armoire
	 * @return la réponse de l'armoire
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
