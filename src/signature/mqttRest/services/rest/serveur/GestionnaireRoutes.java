package signature.mqttRest.services.rest.serveur;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import signature.mqttRest.services.rest.etatEtPilotage.FactoryRequetesEtatEtPilotage;
import signature.mqttRest.services.rest.utilisateur.FactoryRequetesUtilisateur;

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
	 * @return
	 */
	protected static List<String> GETRoutes() {
		List<String> routes = new ArrayList<>();

		routes.addAll(FactoryRequetesEtatEtPilotage.getGETRoutes());
		routes.addAll(FactoryRequetesUtilisateur.getGETRoutes());

		return routes;
	}

	/**
	 * Traite une route
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
		if(FactoryRequetesEtatEtPilotage.getGETRoutes().contains(pUri)) {
			return FactoryRequetesEtatEtPilotage.traiteDemandeGET(pUri, pParametres, pTraiteRequetesRest);
		}
		
		if(FactoryRequetesUtilisateur.getGETRoutes().contains(pUri)) {
			return FactoryRequetesUtilisateur.traiteDemandeGET(pUri, pParametres, pTraiteRequetesRest);
		}
		
		return "";
	}
}
