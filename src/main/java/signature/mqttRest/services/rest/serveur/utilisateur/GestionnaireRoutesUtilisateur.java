package signature.mqttRest.services.rest.serveur.utilisateur;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import signature.mqttRest.util.Util;

/**
 * Routes utilisées pour gérer un utilisateur. Traitement des requêtes GET et
 * POST reçues par le serveur REST.
 * 
 * @author SDARIZCUREN
 *
 */
public class GestionnaireRoutesUtilisateur {
	public final static String LISTE_UTILISATEURS = "/utilisateurs";
	public final static String UTILISATEUR_CONNECTE = "/utilisateurConnecte";
	public final static String IDENTIFIANTS_VALIDE = "/identifiantsValide";

	/**
	 * Donne la liste des routes de type GET
	 * 
	 * @return la liste des routes
	 */
	public static List<String> getGETRoutes() {
		return Arrays.asList(LISTE_UTILISATEURS, UTILISATEUR_CONNECTE, IDENTIFIANTS_VALIDE);
	}

	/**
	 * Donne la liste des routes de type POST
	 * 
	 * @return la liste des routes
	 */
	public static List<String> getPOSTRoutes() {
		return new ArrayList<>();
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
			ITraitementRequetesUtilisateur pTraiteRequetesRest) {
		if (LISTE_UTILISATEURS.equals(pUri)) {
			return Util.toJsonString(pTraiteRequetesRest.demandeListeUtilisateurs(pParametres.get("idExpediteur")[0],
					pParametres.get("idCommande")[0]));
		}

		if (UTILISATEUR_CONNECTE.equals(pUri)) {
			return Util.toJsonString(pTraiteRequetesRest.demandeUtilisateurConnecte(pParametres.get("idExpediteur")[0],
					pParametres.get("idCommande")[0]));
		}

		if (IDENTIFIANTS_VALIDE.equals(pUri)) {
			return Util.toJsonString(
					pTraiteRequetesRest.estValide(pParametres.get("login")[0], pParametres.get("password")[0],
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
			ITraitementRequetesUtilisateur pTraiteRequetesRest) {
		return "";
	}

}
