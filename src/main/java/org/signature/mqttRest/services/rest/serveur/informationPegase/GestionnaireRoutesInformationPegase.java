package org.signature.mqttRest.services.rest.serveur.informationPegase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.signature.mqttRest.services.rest.serveur.ITraitementRequetesRest;
import org.signature.mqttRest.util.Util;

/**
 * Traitement des requêtes GET et POST reçues par le serveur REST, de
 * récupération d'information sur Pegase
 * 
 * @author SDARIZCUREN
 *
 */
public class GestionnaireRoutesInformationPegase {
	public final static String PROPRIETES_EQUIPEMENT = "/informationPegase/proprietesEquipement";
	public final static String PROPRIETES_ARMOIRE = "/informationPegase/proprietesArmoire";

	/**
	 * Donne la liste des routes de type GET
	 * 
	 * @return la liste des routes
	 */
	public static List<String> getGETRoutes() {
		return Arrays.asList(PROPRIETES_EQUIPEMENT, PROPRIETES_ARMOIRE);
	}

	/**
	 * Donne la liste des routes de type POST
	 * 
	 * @return la liste des routes
	 */
	public static List<String> getPOSTRoutes() {
		return new ArrayList<String>();
	}

	/**
	 * Traite une demande de type GET, reçue par le serveur REST
	 * 
	 * @param pUri
	 *            la route à traiter
	 * @param pParametres
	 *            les paramètres de la requête
	 * @param pTraiteRequetesRest
	 *            l'objet qui va traiter les requêtes reçues
	 * @return la réponse à retourner, au format JSON. Chaîne vide si pas de
	 *         réponse
	 */
	public static String traiteDemandeGET(String pUri, Map<String, String[]> pParametres,
			ITraitementRequetesRest pTraiteRequetesRest) {
		if (PROPRIETES_EQUIPEMENT.equals(pUri)) {
			// Cas demande pour un équipement ou pour l'ensemble des équipements
			if (pParametres.get("idEquipement") == null || pParametres.get("idEquipement").length == 0) {
				return Util.toJsonString(
						pTraiteRequetesRest.traiteDemandeProprietesEquipements(pParametres.get("idExpediteur")[0],
								pParametres.get("idCommande")[0]));
			}
			
			return Util.toJsonString(
					pTraiteRequetesRest.traiteDemandeProprietesEquipement(pParametres.get("idExpediteur")[0],
							pParametres.get("idCommande")[0], pParametres.get("idEquipement")[0]));
		}
		
		if (PROPRIETES_ARMOIRE.equals(pUri)) {
			// Cas demande pour une armoire ou pour l'ensemble des armoires
			if (pParametres.get("idArmoire") == null || pParametres.get("idArmoire").length == 0) {
				return Util.toJsonString(
						pTraiteRequetesRest.traiteDemandeProprietesArmoires(pParametres.get("idExpediteur")[0],
								pParametres.get("idCommande")[0]));
			}
			
			return Util.toJsonString(
					pTraiteRequetesRest.traiteDemandeProprietesArmoire(pParametres.get("idExpediteur")[0],
							pParametres.get("idCommande")[0], pParametres.get("idArmoire")[0]));
		}

		return "";
	}

	/**
	 * Traite une demande de type POST, reçue par le serveur REST
	 * 
	 * @param pUri
	 *            la route à traiter
	 * @param pParametres
	 *            les paramètres de la requête
	 * @param pTraiteRequetesRest
	 *            l'objet qui va traiter les requêtes reçues
	 * @return la réponse à retourner, au format JSON. Chaîne vide si pas de
	 *         réponse
	 */
	public static String traiteDemandePOST(String pUri, Map<String, String[]> pParametres,
			ITraitementRequetesRest pTraiteRequetesRest) {

		return "";
	}

}
