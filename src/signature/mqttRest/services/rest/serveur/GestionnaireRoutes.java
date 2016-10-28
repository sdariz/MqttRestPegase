package signature.mqttRest.services.rest.serveur;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import signature.mqttRest.services.rest.etatEtPilotage.FactoryRequetesEtatEtPilotage;
import signature.mqttRest.services.rest.utilisateur.FactoryRequetesUtilisateur;

/**
 * Gestionnaire des routes: liste des routes � g�rer par le serveur, et traitement des appels REST
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

		routes.addAll(FactoryRequetesEtatEtPilotage.getGETRoutes());
		routes.addAll(FactoryRequetesUtilisateur.getGETRoutes());

		return routes;
	}
	
	/**
	 * Retourne la liste des routes de type POST
	 * 
	 * @return les routes POST
	 */
	protected static List<String> POSTRoutes() {
		List<String> routes = new ArrayList<>();

		routes.addAll(FactoryRequetesEtatEtPilotage.getPOSTRoutes());
		routes.addAll(FactoryRequetesUtilisateur.getPOSTRoutes());

		return routes;
	}

	/**
	 * Traite une route GET
	 * 
	 * @param pUri
	 *            la route � traiter
	 * @param pParametres
	 *            les param�tres de la requ�te
	 * @param pTraiteRequetesRest
	 *            l'objet qui va traiter les requ�tes re�ues
	 * @return la r�ponse � retourner. Cha�ne vide si pas de r�ponse
	 */
	protected static String traiteGET(String pUri, Map<String, String[]> pParametres,
			ITraitementRequetesRest pTraiteRequetesRest) {
		// Traitement selon la requ�te re�ue
		if(FactoryRequetesEtatEtPilotage.getGETRoutes().contains(pUri)) {
			return FactoryRequetesEtatEtPilotage.traiteDemandeGET(pUri, pParametres, pTraiteRequetesRest);
		}
		
		if(FactoryRequetesUtilisateur.getGETRoutes().contains(pUri)) {
			return FactoryRequetesUtilisateur.traiteDemandeGET(pUri, pParametres, pTraiteRequetesRest);
		}
		
		return "";
	}
	
	/**
	 * Traite une route POST
	 * 
	 * @param pUri
	 *            la route � traiter
	 * @param pParametres
	 *            les param�tres de la requ�te
	 * @param pTraiteRequetesRest
	 *            l'objet qui va traiter les requ�tes re�ues
	 * @return la r�ponse � retourner. Cha�ne vide si pas de r�ponse
	 */
	protected static String traitePOST(String pUri, Map<String, String[]> pParametres,
			ITraitementRequetesRest pTraiteRequetesRest) {
		// Traitement selon la requ�te re�ue
		if(FactoryRequetesEtatEtPilotage.getPOSTRoutes().contains(pUri)) {
			return FactoryRequetesEtatEtPilotage.traiteDemandePOST(pUri, pParametres, pTraiteRequetesRest);
		}
		
		if(FactoryRequetesUtilisateur.getPOSTRoutes().contains(pUri)) {
			return FactoryRequetesUtilisateur.traiteDemandePOST(pUri, pParametres, pTraiteRequetesRest);
		}
		
		return "";
	}
}
