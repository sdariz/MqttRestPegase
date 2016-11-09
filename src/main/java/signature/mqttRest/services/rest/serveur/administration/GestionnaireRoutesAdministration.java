package signature.mqttRest.services.rest.serveur.administration;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import signature.mqttRest.util.Util;

/**
 * Routes utilisées pour gérer les commandes d'administration. Traitement des
 * requêtes GET et POST reçues par le serveur REST.
 * 
 * @author SDARIZCUREN
 *
 */
public class GestionnaireRoutesAdministration {
	public final static String INTERDICTION_PILOTAGES = "/interdictionPilotages";
	public final static String TEST_PRESENCE = "/testPresence";
	public final static String ACTIVATION_BOUTON = "/activationBouton";
	public final static String LANCEMENT_ACTION_BOUTON = "/lancementActionBouton";

	/**
	 * Donne la liste des routes de type GET
	 * 
	 * @return la liste des routes
	 */
	public static List<String> getGETRoutes() {
		return Arrays.asList(TEST_PRESENCE);
	}

	/**
	 * Donne la liste des routes de type POST
	 * 
	 * @return la liste des routes
	 */
	public static List<String> getPOSTRoutes() {
		return Arrays.asList(INTERDICTION_PILOTAGES, ACTIVATION_BOUTON, LANCEMENT_ACTION_BOUTON);
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
			ITraitementRequetesAdministration pTraiteRequetesRest) {
		if (TEST_PRESENCE.equals(pUri)) {
			// Pas de traitement, réponse directe OK
			return Util.toJsonString(true);
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
			ITraitementRequetesAdministration pTraiteRequetesRest) {
		if (INTERDICTION_PILOTAGES.equals(pUri)) {
			pTraiteRequetesRest.traiteDemandeInterdictionPilotages(
					Boolean.parseBoolean(pParametres.get("interdiction")[0]), pParametres.get("idExpediteur")[0],
					pParametres.get("idCommande")[0]);
			return "";
		}

		if (ACTIVATION_BOUTON.equals(pUri)) {
			pTraiteRequetesRest.traiteDemandeActivationBouton(pParametres.get("idBouton")[0],
					Boolean.parseBoolean(pParametres.get("actif")[0]), pParametres.get("idExpediteur")[0],
					pParametres.get("idCommande")[0]);
			return "";
		}

		if (LANCEMENT_ACTION_BOUTON.equals(pUri)) {
			pTraiteRequetesRest.traiteDemandeLancementActionBouton(pParametres.get("idBouton")[0],
					pParametres.get("idExpediteur")[0], pParametres.get("idCommande")[0]);
			return "";
		}

		return "";
	}
}
