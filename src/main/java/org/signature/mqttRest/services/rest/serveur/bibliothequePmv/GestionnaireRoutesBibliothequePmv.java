package org.signature.mqttRest.services.rest.serveur.bibliothequePmv;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.signature.mqttRest.services.rest.serveur.ITraitementRequetesRest;
import org.signature.mqttRest.util.Util;

/**
 * Traitement des requ�tes GET et POST re�ues par le serveur REST, qui concerne
 * la biblioth�que PMV
 * 
 * @author SDARIZCUREN
 *
 */
public class GestionnaireRoutesBibliothequePmv {
	public final static String LISTE_CATEGORIES = "/bibliothequePmv/categories";
	public final static String LISTE_MESSAGES_DANS_CATEGORIE = "/bibliothequePmv/messagesDansCategorie";
	public final static String MESSAGE = "/bibliothequePmv/message";

	/**
	 * Donne la liste des routes de type GET
	 * 
	 * @return la liste des routes
	 */
	public static List<String> getGETRoutes() {
		return Arrays.asList(LISTE_CATEGORIES, LISTE_MESSAGES_DANS_CATEGORIE, MESSAGE);
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
		if (LISTE_CATEGORIES.equals(pUri)) {
			return Util.toJsonString(pTraiteRequetesRest.traiteDemandeCategoriesBibliothequePmv(
					pParametres.get("idExpediteur")[0], pParametres.get("idCommande")[0]));
		}

		if (LISTE_MESSAGES_DANS_CATEGORIE.equals(pUri)) {
			return Util.toJsonString(pTraiteRequetesRest.traiteDemandeMessagesDansCategorieBibliothequePmv(
					pParametres.get("idExpediteur")[0], pParametres.get("idCommande")[0],
					pParametres.get("categorie")[0]));
		}
		
		if (MESSAGE.equals(pUri)) {
			return Util.toJsonString(pTraiteRequetesRest.traiteDemandeMessageBibliothequePmv(
					pParametres.get("idExpediteur")[0], pParametres.get("idCommande")[0],
					pParametres.get("categorie")[0], pParametres.get("nom")[0]));
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
