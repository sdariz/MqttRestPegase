package signature.mqttRest.services.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Classe servant uniquement à lister les routes à initialiser
 * 
 * @author SDARIZCUREN
 *
 */
public class FactoryRoutes {
	private static List<String> getRoutes;
	// private static List<String> postRoutes;

	private final static String ETAT_AFFICHAGE_EQUIPEMENT = "/etatAffichage";
	private final static String ETAT_TECHNIQUE_EQUIPEMENT = "/etatTechnique";
	
	/**
	 * Demande la route pour une demande d'état d'affichage 
	 * @return la route à utiliser
	 */
	public static String getRouteDemandeEtatAffichageEquipement() {
		return ETAT_AFFICHAGE_EQUIPEMENT;
	}
	
	/**
	 * Demande la route pour une demande d'état technique 
	 * @return la route à utiliser
	 */
	public static String getRouteDemandeEtatTechniqueEquipement() {
		return ETAT_TECHNIQUE_EQUIPEMENT;
	}

	/**
	 * Retourne la liste des routes de type GET
	 * 
	 * @return
	 */
	protected static List<String> GETRoutes() {
		getRoutes = new ArrayList<>();

		getRoutes.add(ETAT_AFFICHAGE_EQUIPEMENT);
		getRoutes.add(ETAT_TECHNIQUE_EQUIPEMENT);

		return getRoutes;
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
		switch (pUri) {
		case ETAT_AFFICHAGE_EQUIPEMENT:
			return traiteDemandeEtatAffichage(pParametres, pTraiteRequetesRest);
		case ETAT_TECHNIQUE_EQUIPEMENT:
			return traiteDemandeEtatTechnique(pParametres, pTraiteRequetesRest);
		}

		return "";
	}

	// Traite une demande d'état d'affichage
	private static String traiteDemandeEtatAffichage(Map<String, String[]> pParametres,
			ITraitementRequetesRest pTraiteRequetesRest) {
		// Soit demande d'un équipement en particulier, soit demande pour tous
		// les équipements
		if (pParametres.keySet().size() == 0) {
			return pTraiteRequetesRest.demandeEtatAffichageEquipements();
		}

		// Décodage de l'id de l'équipement
		String id = pParametres.get("id")[0];

		return pTraiteRequetesRest.demandeEtatAffichageEquipement(id);
	}

	// Traite une demande d'état technique
	private static String traiteDemandeEtatTechnique(Map<String, String[]> pParametres,
			ITraitementRequetesRest pTraiteRequetesRest) {
		// Soit demande d'un équipement en particulier, soit demande pour tous
		// les équipements
		if (pParametres.keySet().size() == 0) {
			return pTraiteRequetesRest.demandeEtatTechniqueEquipements();
		}

		// Décodage de l'id de l'équipement
		String id = pParametres.get("id")[0];

		return pTraiteRequetesRest.demandeEtatTechniqueEquipement(id);
	}

}
