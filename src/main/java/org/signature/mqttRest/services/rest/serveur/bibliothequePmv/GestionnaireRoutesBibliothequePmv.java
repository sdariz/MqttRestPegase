package org.signature.mqttRest.services.rest.serveur.bibliothequePmv;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.signature.mqttRest.services.rest.serveur.ITraitementRequetesRest;
import org.signature.mqttRest.util.Util;

/**
 * Traitement des requêtes GET et POST reçues par le serveur REST, qui concerne
 * la bibliothèque PMV
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
