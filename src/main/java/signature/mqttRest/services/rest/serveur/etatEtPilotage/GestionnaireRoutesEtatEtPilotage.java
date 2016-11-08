package signature.mqttRest.services.rest.serveur.etatEtPilotage;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import signature.mqttRest.util.Util;

/**
 * Routes utilis�es pour piloter ou g�rer l'�tat d'�quipements. Traitement des
 * requ�tes GET et POST re�ues par le serveur REST.
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
	 *            la route � traiter
	 * @param pParametres
	 *            les param�tres de la requ�te
	 * @param pTraiteRequetesRest
	 *            l'objet qui va traiter les requ�tes re�ues
	 * @return la r�ponse � retourner, au format JSON. Cha�ne vide si pas de
	 *         r�ponse
	 */
	public static String traiteDemandeGET(String pUri, Map<String, String[]> pParametres,
			ITraitementRequetesEtatEquipements pTraiteRequetesRest) {
		if (ETAT_AFFICHAGE_EQUIPEMENT.equals(pUri)) {
			// Soit demande d'un �quipement en particulier, soit demande pour
			// tous les �quipements
			if (pParametres.get("id") == null || pParametres.get("id").length == 0) {
				return Util.toJsonString(pTraiteRequetesRest.demandeEtatAffichageEquipements(
						pParametres.get("idExpediteur")[0], pParametres.get("idCommande")[0]));
			}

			// D�codage de l'id de l'�quipement
			return Util.toJsonString(pTraiteRequetesRest.demandeEtatAffichageEquipement(pParametres.get("id")[0],
					pParametres.get("idExpediteur")[0], pParametres.get("idCommande")[0]));
		}

		if (ETAT_TECHNIQUE_EQUIPEMENT.equals(pUri)) {
			// Soit demande d'un �quipement en particulier, soit demande pour
			// tous les �quipements
			if (pParametres.get("id") == null || pParametres.get("id").length == 0) {
				return pTraiteRequetesRest.demandeEtatTechniqueEquipements(pParametres.get("idExpediteur")[0],
						pParametres.get("idCommande")[0]);
			}

			// D�codage de l'id de l'�quipement
			return pTraiteRequetesRest.demandeEtatTechniqueEquipement(pParametres.get("id")[0],
					pParametres.get("idExpediteur")[0], pParametres.get("idCommande")[0]);
		}

		return "";
	}

	/**
	 * Traite une demande de type POST
	 * 
	 * @param pUri
	 *            la route � traiter
	 * @param pParametres
	 *            les param�tres de la requ�te
	 * @param pTraiteRequetesRest
	 *            l'objet qui va traiter les requ�tes re�ues
	 * @return la r�ponse � retourner, au format JSON. Cha�ne vide si pas de
	 *         r�ponse
	 */
	public static String traiteDemandePOST(String pUri, Map<String, String[]> pParametres,
			ITraitementRequetesEtatEquipements pTraiteRequetesRest) {
		if (ACTUALISATION_ETAT_EQUIPEMENT.equals(pUri)) {
			pTraiteRequetesRest.demandeActualisationEtatEquipement(pParametres.get("idEquipement")[0],
					pParametres.get("idExpediteur")[0], pParametres.get("idCommande")[0]);
			return "";
		}
		return "";
	}
}
