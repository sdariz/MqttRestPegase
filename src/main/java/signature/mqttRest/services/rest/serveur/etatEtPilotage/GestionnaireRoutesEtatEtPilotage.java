package signature.mqttRest.services.rest.serveur.etatEtPilotage;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import signature.mqttRest.services.rest.serveur.ITraitementRequetesRest;
import signature.mqttRest.util.Util;

/**
 * Routes utilisées pour piloter ou gérer l'état d'équipements. Traitement des
 * requêtes GET et POST reçues par le serveur REST.
 * 
 * @author SDARIZCUREN
 *
 */
public class GestionnaireRoutesEtatEtPilotage {

	public final static String ETAT_AFFICHAGE_EQUIPEMENT = "/etatAffichage";
	public final static String ETAT_TECHNIQUE_EQUIPEMENT = "/etatTechnique";
	public final static String ACTUALISATION_ETAT_EQUIPEMENT = "/actualisationEtatEquipement";

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
		if (ETAT_AFFICHAGE_EQUIPEMENT.equals(pUri)) {
			// Soit demande d'un équipement en particulier, soit demande pour
			// tous les équipements
			if (pParametres.get("id") == null || pParametres.get("id").length == 0) {
				return Util.toJsonString(pTraiteRequetesRest.demandeEtatAffichageEquipements(
						pParametres.get("idExpediteur")[0], pParametres.get("idCommande")[0]));
			}

			// Décodage de l'id de l'équipement
			return Util.toJsonString(pTraiteRequetesRest.demandeEtatAffichageEquipement(
					pParametres.get("idExpediteur")[0], pParametres.get("idCommande")[0], pParametres.get("id")[0]));
		}

		if (ETAT_TECHNIQUE_EQUIPEMENT.equals(pUri)) {
			// Soit demande d'un équipement en particulier, soit demande pour
			// tous les équipements
			if (pParametres.get("id") == null || pParametres.get("id").length == 0) {
				return Util.toJsonString(pTraiteRequetesRest.demandeEtatTechniqueEquipements(
						pParametres.get("idExpediteur")[0], pParametres.get("idCommande")[0]));
			}

			// Décodage de l'id de l'équipement
			return Util.toJsonString(pTraiteRequetesRest.demandeEtatTechniqueEquipement(
					pParametres.get("idExpediteur")[0], pParametres.get("idCommande")[0], pParametres.get("id")[0]));
		}

		return "";
	}

	/**
	 * Traite une demande de type POST
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
		if (ACTUALISATION_ETAT_EQUIPEMENT.equals(pUri)) {
			pTraiteRequetesRest.demandeActualisationEtatEquipement(pParametres.get("idExpediteur")[0],
					pParametres.get("idCommande")[0], pParametres.get("idEquipement")[0]);
			return "";
		}
		return "";
	}
}
