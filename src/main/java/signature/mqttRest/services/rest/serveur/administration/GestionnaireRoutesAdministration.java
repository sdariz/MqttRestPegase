package signature.mqttRest.services.rest.serveur.administration;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import signature.mqttRest.services.rest.serveur.ITraitementRequetesRest;
import signature.mqttRest.util.Util;

/**
 * Routes utilis�es pour g�rer les commandes d'administration. Traitement des
 * requ�tes GET et POST re�ues par le serveur REST.
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
		if (TEST_PRESENCE.equals(pUri)) {
			// Pas de traitement, r�ponse directe OK
			return Util.toJsonString(true);
		}

		return "";
	}

	public static String toto(String s, ITraitementRequetesRest pTraiteRequetesRest) {

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
		if (INTERDICTION_PILOTAGES.equals(pUri)) {
			pTraiteRequetesRest.traiteDemandeInterdictionPilotages(pParametres.get("idExpediteur")[0],
					pParametres.get("idCommande")[0], Boolean.parseBoolean(pParametres.get("interdiction")[0]));
			return "";
		}

		if (ACTIVATION_BOUTON.equals(pUri)) {
			pTraiteRequetesRest.traiteDemandeActivationBouton(pParametres.get("idExpediteur")[0],
					pParametres.get("idCommande")[0], pParametres.get("idBouton")[0],
					Boolean.parseBoolean(pParametres.get("actif")[0]));
			return "";
		}

		if (LANCEMENT_ACTION_BOUTON.equals(pUri)) {
			pTraiteRequetesRest.traiteDemandeLancementActionBouton(pParametres.get("idExpediteur")[0],
					pParametres.get("idCommande")[0], pParametres.get("idBouton")[0]);
			return "";
		}

		return "";
	}
}
