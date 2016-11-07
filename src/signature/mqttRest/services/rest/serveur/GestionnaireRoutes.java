package signature.mqttRest.services.rest.serveur;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import signature.mqttRest.services.rest.serveur.administration.GestionnaireRoutesAdministration;
import signature.mqttRest.services.rest.serveur.etatEtPilotage.GestionnaireRoutesEtatEtPilotage;
import signature.mqttRest.services.rest.serveur.utilisateur.GestionnaireRoutesUtilisateur;

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

		routes.addAll(GestionnaireRoutesEtatEtPilotage.getGETRoutes());
		routes.addAll(GestionnaireRoutesUtilisateur.getGETRoutes());
		routes.addAll(GestionnaireRoutesAdministration.getGETRoutes());

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
		routes.addAll(GestionnaireRoutesAdministration.getPOSTRoutes());

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
		if(GestionnaireRoutesEtatEtPilotage.getGETRoutes().contains(pUri)) {
			return GestionnaireRoutesEtatEtPilotage.traiteDemandeGET(pUri, pParametres, pTraiteRequetesRest);
		}
		
		if(GestionnaireRoutesUtilisateur.getGETRoutes().contains(pUri)) {
			return GestionnaireRoutesUtilisateur.traiteDemandeGET(pUri, pParametres, pTraiteRequetesRest);
		}
		
		if(GestionnaireRoutesAdministration.getGETRoutes().contains(pUri)) {
			return GestionnaireRoutesAdministration.traiteDemandeGET(pUri, pParametres, pTraiteRequetesRest);
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
		if(GestionnaireRoutesEtatEtPilotage.getPOSTRoutes().contains(pUri)) {
			return GestionnaireRoutesEtatEtPilotage.traiteDemandePOST(pUri, pParametres, pTraiteRequetesRest);
		}
		
		if(GestionnaireRoutesUtilisateur.getPOSTRoutes().contains(pUri)) {
			return GestionnaireRoutesUtilisateur.traiteDemandePOST(pUri, pParametres, pTraiteRequetesRest);
		}
		
		if(GestionnaireRoutesAdministration.getPOSTRoutes().contains(pUri)) {
			return GestionnaireRoutesAdministration.traiteDemandePOST(pUri, pParametres, pTraiteRequetesRest);
		}
		
		return "";
	}
}
