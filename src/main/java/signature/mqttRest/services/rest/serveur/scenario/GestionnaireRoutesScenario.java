package signature.mqttRest.services.rest.serveur.scenario;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import signature.mqttRest.services.rest.serveur.ITraitementRequetesRest;
import signature.mqttRest.util.Util;

/**
 * Traitement des requêtes GET et POST reçues par le serveur REST, qui concerne
 * les scénarios
 * 
 * @author SDARIZCUREN
 *
 */
public class GestionnaireRoutesScenario {
	public final static String LISTE_IDENTIFIANTS = "/scenario/identifiants";

	/**
	 * Donne la liste des routes de type GET
	 * 
	 * @return la liste des routes
	 */
	public static List<String> getGETRoutes() {
		return Arrays.asList(LISTE_IDENTIFIANTS);
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
		if (LISTE_IDENTIFIANTS.equals(pUri)) {
			return Util.toJsonString(pTraiteRequetesRest.traiteDemandeIdentifiantsScenarios(
					pParametres.get("idExpediteur")[0], pParametres.get("idCommande")[0]));
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
