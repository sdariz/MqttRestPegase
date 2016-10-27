package signature.mqttRest.services.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Classe servant uniquement � lister les routes � initialiser
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
	 * Demande la route pour une demande d'�tat d'affichage 
	 * @return la route � utiliser
	 */
	public static String getRouteDemandeEtatAffichageEquipement() {
		return ETAT_AFFICHAGE_EQUIPEMENT;
	}
	
	/**
	 * Demande la route pour une demande d'�tat technique 
	 * @return la route � utiliser
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
		switch (pUri) {
		case ETAT_AFFICHAGE_EQUIPEMENT:
			return traiteDemandeEtatAffichage(pParametres, pTraiteRequetesRest);
		case ETAT_TECHNIQUE_EQUIPEMENT:
			return traiteDemandeEtatTechnique(pParametres, pTraiteRequetesRest);
		}

		return "";
	}

	// Traite une demande d'�tat d'affichage
	private static String traiteDemandeEtatAffichage(Map<String, String[]> pParametres,
			ITraitementRequetesRest pTraiteRequetesRest) {
		// Soit demande d'un �quipement en particulier, soit demande pour tous
		// les �quipements
		if (pParametres.keySet().size() == 0) {
			return pTraiteRequetesRest.demandeEtatAffichageEquipements();
		}

		// D�codage de l'id de l'�quipement
		String id = pParametres.get("id")[0];

		return pTraiteRequetesRest.demandeEtatAffichageEquipement(id);
	}

	// Traite une demande d'�tat technique
	private static String traiteDemandeEtatTechnique(Map<String, String[]> pParametres,
			ITraitementRequetesRest pTraiteRequetesRest) {
		// Soit demande d'un �quipement en particulier, soit demande pour tous
		// les �quipements
		if (pParametres.keySet().size() == 0) {
			return pTraiteRequetesRest.demandeEtatTechniqueEquipements();
		}

		// D�codage de l'id de l'�quipement
		String id = pParametres.get("id")[0];

		return pTraiteRequetesRest.demandeEtatTechniqueEquipement(id);
	}

}
