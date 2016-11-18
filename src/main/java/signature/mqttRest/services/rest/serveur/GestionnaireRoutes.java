package signature.mqttRest.services.rest.serveur;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import signature.mqttRest.services.rest.serveur.administration.GestionnaireRoutesAdministration;
import signature.mqttRest.services.rest.serveur.bibliothequePmv.GestionnaireRoutesBibliothequePmv;
import signature.mqttRest.services.rest.serveur.etatEquipement.GestionnaireRoutesEtatEquipement;
import signature.mqttRest.services.rest.serveur.evenement.GestionnaireRoutesEvenement;
import signature.mqttRest.services.rest.serveur.informationPegase.GestionnaireRoutesInformationPegase;
import signature.mqttRest.services.rest.serveur.interrogationArmoire.GestionnaireRoutesInterrogationArmoire;
import signature.mqttRest.services.rest.serveur.pilotage.GestionnaireRoutesPilotage;
import signature.mqttRest.services.rest.serveur.scenario.GestionnaireRoutesScenario;
import signature.mqttRest.services.rest.serveur.utilisateur.GestionnaireRoutesUtilisateur;

/**
 * Gestionnaire des routes: liste des routes à gérer par le serveur, et
 * traitement des appels REST
 * 
 * @author SDARIZCUREN
 *
 */
class GestionnaireRoutes {
	private static Map<Class<?>, List<String>> _routesGet;
	private static Map<Class<?>, List<String>> _routesPost;
	private final static Logger LOG = LoggerFactory.getLogger(GestionnaireRoutes.class);

	// Liste des routes GET et POST
	static {
		_routesGet = new HashMap<>();
		_routesGet.put(GestionnaireRoutesEtatEquipement.class, GestionnaireRoutesEtatEquipement.getGETRoutes());
		_routesGet.put(GestionnaireRoutesUtilisateur.class, GestionnaireRoutesUtilisateur.getGETRoutes());
		_routesGet.put(GestionnaireRoutesAdministration.class, GestionnaireRoutesAdministration.getGETRoutes());
		_routesGet.put(GestionnaireRoutesInterrogationArmoire.class,
				GestionnaireRoutesInterrogationArmoire.getGETRoutes());
		_routesGet.put(GestionnaireRoutesBibliothequePmv.class, GestionnaireRoutesBibliothequePmv.getGETRoutes());
		_routesGet.put(GestionnaireRoutesEvenement.class, GestionnaireRoutesEvenement.getGETRoutes());
		_routesGet.put(GestionnaireRoutesInformationPegase.class, GestionnaireRoutesInformationPegase.getGETRoutes());
		_routesGet.put(GestionnaireRoutesScenario.class, GestionnaireRoutesScenario.getGETRoutes());
		_routesGet.put(GestionnaireRoutesPilotage.class, GestionnaireRoutesPilotage.getGETRoutes());		

		_routesPost = new HashMap<>();
		_routesPost.put(GestionnaireRoutesEtatEquipement.class, GestionnaireRoutesEtatEquipement.getPOSTRoutes());
		_routesPost.put(GestionnaireRoutesUtilisateur.class, GestionnaireRoutesUtilisateur.getPOSTRoutes());
		_routesPost.put(GestionnaireRoutesAdministration.class, GestionnaireRoutesAdministration.getPOSTRoutes());
		_routesPost.put(GestionnaireRoutesInterrogationArmoire.class,
				GestionnaireRoutesInterrogationArmoire.getPOSTRoutes());
		_routesPost.put(GestionnaireRoutesBibliothequePmv.class, GestionnaireRoutesBibliothequePmv.getPOSTRoutes());
		_routesPost.put(GestionnaireRoutesEvenement.class, GestionnaireRoutesEvenement.getPOSTRoutes());
		_routesPost.put(GestionnaireRoutesInformationPegase.class, GestionnaireRoutesInformationPegase.getPOSTRoutes());
		_routesPost.put(GestionnaireRoutesScenario.class, GestionnaireRoutesScenario.getPOSTRoutes());
		_routesPost.put(GestionnaireRoutesPilotage.class, GestionnaireRoutesPilotage.getPOSTRoutes());
	}

	/**
	 * Retourne la liste des routes de type GET
	 * 
	 * @return les routes GET
	 */
	protected static List<String> GETRoutes() {
		List<String> routes = new ArrayList<>();

		_routesGet.values().forEach(r -> {
			routes.addAll(r);
		});

		return routes;
	}

	/**
	 * Retourne la liste des routes de type POST
	 * 
	 * @return les routes POST
	 */
	protected static List<String> POSTRoutes() {
		List<String> routes = new ArrayList<>();

		_routesPost.values().forEach(r -> {
			routes.addAll(r);
		});

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

		// Détermination de la méthode à appeler par réflexion
		Object[] classes = _routesGet.keySet().toArray();
		for (int i = 0; i < classes.length; i++) {
			if (_routesGet.get(classes[i]).contains(pUri)) {
				try {
					Method method = ((Class<?>) classes[i]).getDeclaredMethod("traiteDemandeGET", String.class,
							Map.class, ITraitementRequetesRest.class);
					return (String) method.invoke(null, pUri, pParametres, pTraiteRequetesRest);
				} catch (Exception e) {
					LOG.error("Erreur reflexion GET", e);
					return "";
				}
			}
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
		// Détermination de la méthode à appeler par réflexion
		Object[] classes = _routesPost.keySet().toArray();
		for (int i = 0; i < classes.length; i++) {
			if (_routesPost.get(classes[i]).contains(pUri)) {
				try {
					Method method = ((Class<?>) classes[i]).getDeclaredMethod("traiteDemandePOST", String.class,
							Map.class, ITraitementRequetesRest.class);
					return (String) method.invoke(null, pUri, pParametres, pTraiteRequetesRest);
				} catch (Exception e) {
					LOG.error("Erreur reflexion POST", e);
					return "";
				}
			}
		}

		return "";
	}
}
