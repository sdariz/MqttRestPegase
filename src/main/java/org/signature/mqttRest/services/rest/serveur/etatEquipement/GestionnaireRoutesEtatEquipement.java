package org.signature.mqttRest.services.rest.serveur.etatEquipement;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.signature.mqttRest.services.rest.serveur.ITraitementRequetesRest;
import org.signature.mqttRest.util.Util;

/**
 * Routes utilis?es pour g?rer l'?tat d'?quipements. Traitement des
 * requ?tes GET et POST re?ues par le serveur REST.
 * 
 * @author SDARIZCUREN
 *
 */
public class GestionnaireRoutesEtatEquipement {

	public final static String ETAT_AFFICHAGE_EQUIPEMENT = "/etatEquipement/etatAffichage";
	public final static String ETAT_TECHNIQUE_EQUIPEMENT = "/etatEquipement/etatTechnique";
	public final static String ACTUALISATION_ETAT_EQUIPEMENT = "/etatEquipement/actualisationEtatEquipement";

	/**
	 * Donne la liste des routes de type GET
	 * 
	 * @return la liste des routes
	 */
	public static List<String> getGETRoutes() {
		return Arrays.asList(ETAT_AFFICHAGE_EQUIPEMENT, ETAT_TECHNIQUE_EQUIPEMENT);
	}

	/**
	 * Donne la liste des routes de type POST
	 * 
	 * @return la liste des routes
	 */
	public static List<String> getPOSTRoutes() {
		return Arrays.asList(ACTUALISATION_ETAT_EQUIPEMENT);
	}

	/**
	 * Traite une demande de type GET
	 * 
	 * @param pUri
	 *            la route ? traiter
	 * @param pParametres
	 *            les param?tres de la requ?te
	 * @param pTraiteRequetesRest
	 *            l'objet qui va traiter les requ?tes re?ues
	 * @return la r?ponse ? retourner, au format JSON. Cha?ne vide si pas de
	 *         r?ponse
	 */
	public static String traiteDemandeGET(String pUri, Map<String, String[]> pParametres,
			ITraitementRequetesRest pTraiteRequetesRest) {
		if (ETAT_AFFICHAGE_EQUIPEMENT.equals(pUri)) {
			// Soit demande d'un ?quipement en particulier, soit demande pour
			// tous les ?quipements
			if (pParametres.get("idEquipement") == null || pParametres.get("idEquipement").length == 0) {
				return Util.listObjectToJsonString(pTraiteRequetesRest.demandeEtatAffichageEquipements(
						pParametres.get("idExpediteur")[0], pParametres.get("idCommande")[0]));
			}

			// D?codage de l'id de l'?quipement
			return Util.ObjectToJsonString(pTraiteRequetesRest.demandeEtatAffichageEquipement(
					pParametres.get("idExpediteur")[0], pParametres.get("idCommande")[0], pParametres.get("idEquipement")[0]));
		}

		if (ETAT_TECHNIQUE_EQUIPEMENT.equals(pUri)) {
			// Soit demande d'un ?quipement en particulier, soit demande pour
			// tous les ?quipements
			if (pParametres.get("idEquipement") == null || pParametres.get("idEquipement").length == 0) {
				return Util.listObjectToJsonString(pTraiteRequetesRest.demandeEtatTechniqueEquipements(
						pParametres.get("idExpediteur")[0], pParametres.get("idCommande")[0]));
			}

			// D?codage de l'id de l'?quipement
			return Util.ObjectToJsonString(pTraiteRequetesRest.demandeEtatTechniqueEquipement(
					pParametres.get("idExpediteur")[0], pParametres.get("idCommande")[0], pParametres.get("idEquipement")[0]));
		}

		return "";
	}

	/**
	 * Traite une demande de type POST
	 * 
	 * @param pUri
	 *            la route ? traiter
	 * @param pParametres
	 *            les param?tres de la requ?te
	 * @param pTraiteRequetesRest
	 *            l'objet qui va traiter les requ?tes re?ues
	 * @return la r?ponse ? retourner, au format JSON. Cha?ne vide si pas de
	 *         r?ponse
	 */
	public static String traiteDemandePOST(String pUri, Map<String, String[]> pParametres,
			ITraitementRequetesRest pTraiteRequetesRest) {
		if (ACTUALISATION_ETAT_EQUIPEMENT.equals(pUri)) {
			pTraiteRequetesRest.demandeActualisationEtatEquipement(pParametres.get("idExpediteur")[0],
					pParametres.get("idCommande")[0], pParametres.get("idEquipement")[0]);
			return "";
		}
		return "";
	}
}
