package signature.mqttRest.services.rest.serveur;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import signature.mqttRest.services.rest.etatEtPilotage.GestionnaireRoutesEtatEtPilotage;
import signature.mqttRest.services.rest.utilisateur.GestionnaireRoutesUtilisateur;

/**
 * Gestionnaire des routes: liste des routes à gérer par le serveur, et traitement des appels REST
 * 
 * @author SDARIZCUREN
 *
 */
class GestionnaireRoutes {
	
	/**
	 * Retourne la liste des routes de type GET
	 * 
	 * @return les routes GET
	 */
	protected static List<String> GETRoutes() {
		List<String> routes = new ArrayList<>();

		routes.addAll(GestionnaireRoutesEtatEtPilotage.getGETRoutes());
		routes.addAll(GestionnaireRoutesUtilisateur.getGETRoutes());

		return routes;
	}
	
	/**
	 * Retourne la liste des routes de type POST
	 * 
	 * @return les routes POST
	 */
	protected static List<String> POSTRoutes() {
		List<String> routes = new ArrayList<>();

		routes.addAll(GestionnaireRoutesEtatEtPilotage.getPOSTRoutes());
		routes.addAll(GestionnaireRoutesUtilisateur.getPOSTRoutes());

		return routes;
	}

	/**
	 * Traite une route GET
	 * 
	 * @param pUri
	 *            la route à traiter
	 * @param pParametres
	 *            les paramètres de la requête
	 * @param pTraiteRequetesRest
	 *            l'objet qui va traiter les requêtes reçues
	 * @return la réponse à retourner. Chaîne vide si pas de réponse
	 */
	protected static String traiteGET(String pUri, Map<String, String[]> pParametres,
			ITraitementRequetesRest pTraiteRequetesRest) {
		// Traitement selon la requête reçue
		if(GestionnaireRoutesEtatEtPilotage.getGETRoutes().contains(pUri)) {
			return GestionnaireRoutesEtatEtPilotage.traiteDemandeGET(pUri, pParametres, pTraiteRequetesRest);
		}
		
		if(GestionnaireRoutesUtilisateur.getGETRoutes().contains(pUri)) {
			return GestionnaireRoutesUtilisateur.traiteDemandeGET(pUri, pParametres, pTraiteRequetesRest);
		}
		
		return "";
	}
	
	/**
	 * Traite une route POST
	 * 
	 * @param pUri
	 *            la route à traiter
	 * @param pParametres
	 *            les paramètres de la requête
	 * @param pTraiteRequetesRest
	 *            l'objet qui va traiter les requêtes reçues
	 * @return la réponse à retourner. Chaîne vide si pas de réponse
	 */
	protected static String traitePOST(String pUri, Map<String, String[]> pParametres,
			ITraitementRequetesRest pTraiteRequetesRest) {
		// Traitement selon la requête reçue
		if(GestionnaireRoutesEtatEtPilotage.getPOSTRoutes().contains(pUri)) {
			return GestionnaireRoutesEtatEtPilotage.traiteDemandePOST(pUri, pParametres, pTraiteRequetesRest);
		}
		
		if(GestionnaireRoutesUtilisateur.getPOSTRoutes().contains(pUri)) {
			return GestionnaireRoutesUtilisateur.traiteDemandePOST(pUri, pParametres, pTraiteRequetesRest);
		}
		
		return "";
	}
}
