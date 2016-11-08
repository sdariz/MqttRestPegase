package signature.mqttRest.services.rest.serveur.utilisateur;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import signature.mqttRest.util.Util;

/**
 * Routes utilis�es pour g�rer un utilisateur. Traitement des requ�tes GET et
 * POST re�ues par le serveur REST.
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
			ITraitementRequetesUtilisateur pTraiteRequetesRest) {
		return "";
	}

}
