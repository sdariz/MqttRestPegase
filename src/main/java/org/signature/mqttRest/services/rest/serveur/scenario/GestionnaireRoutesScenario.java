package org.signature.mqttRest.services.rest.serveur.scenario;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.signature.mqttRest.services.rest.serveur.ITraitementRequetesRest;
import org.signature.mqttRest.util.Util;

/**
 * Traitement des requ�tes GET et POST re�ues par le serveur REST, qui concerne
 * les sc�narios
 * 
 * @author SDARIZCUREN
 *
 */
public class GestionnaireRoutesScenario {
	public final static String LISTE_IDENTIFIANTS = "/scenario/identifiants";
	public final static String SCENARIO = "/scenario/scenario";

	/**
	 * Donne la liste des routes de type GET
	 * 
	 * @return la liste des routes
	 */
	public static List<String> getGETRoutes() {
		return Arrays.asList(LISTE_IDENTIFIANTS, SCENARIO);
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
	 * Traite une demande de type GET, re�ue par le serveur REST
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
			ITraitementRequetesRest pTraiteRequetesRest) {
		if (LISTE_IDENTIFIANTS.equals(pUri)) {
			return Util.toJsonString(pTraiteRequetesRest.traiteDemandeIdentifiantsScenarios(
					pParametres.get("idExpediteur")[0], pParametres.get("idCommande")[0]));
		}

		if (SCENARIO.equals(pUri)) {
			// Soit demande d'un sc�nario en particulier, soit demande pour
			// tous les sc�narios
			if (pParametres.get("idScenario") == null || pParametres.get("idScenario").length == 0) {
				return Util.toJsonString(pTraiteRequetesRest.traiteDemandeScenarios(pParametres.get("idExpediteur")[0],
						pParametres.get("idCommande")[0]));
			}

			// D�codage de l'id du sc�nario
			return Util.toJsonString(pTraiteRequetesRest.traiteDemandeScenario(pParametres.get("idExpediteur")[0],
					pParametres.get("idCommande")[0], pParametres.get("idScenario")[0]));
		}

		return "";
	}

	/**
	 * Traite une demande de type POST, re�ue par le serveur REST
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
			ITraitementRequetesRest pTraiteRequetesRest) {

		return "";
	}
}
