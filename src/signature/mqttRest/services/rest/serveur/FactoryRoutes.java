package signature.mqttRest.services.rest.serveur;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import signature.mqttRest.services.rest.etatEtPilotage.FactoryRequetesEtatEtPilotage;
import signature.mqttRest.services.rest.utilisateur.FactoryRequetesUtilisateur;

/**
 * Classe servant uniquement � lister les routes � initialiser
 * 
 * @author SDARIZCUREN
 *
 */
class FactoryRoutes {
	private static List<String> getRoutes;
	// private static List<String> postRoutes;

	/**
	 * Retourne la liste des routes de type GET
	 * 
	 * @return
	 */
	protected static List<String> GETRoutes() {
		getRoutes = new ArrayList<>();

		getRoutes.add(FactoryRequetesEtatEtPilotage.ETAT_AFFICHAGE_EQUIPEMENT);
		getRoutes.add(FactoryRequetesEtatEtPilotage.ETAT_TECHNIQUE_EQUIPEMENT);
		getRoutes.add(FactoryRequetesUtilisateur.LISTE_UTILISATEURS);

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
		case FactoryRequetesEtatEtPilotage.ETAT_AFFICHAGE_EQUIPEMENT:
			return traiteDemandeEtatAffichage(pParametres, pTraiteRequetesRest);
		case FactoryRequetesEtatEtPilotage.ETAT_TECHNIQUE_EQUIPEMENT:
			return traiteDemandeEtatTechnique(pParametres, pTraiteRequetesRest);
		case FactoryRequetesUtilisateur.LISTE_UTILISATEURS:
			return traiteDemandeListeUtilisateurs(pParametres, pTraiteRequetesRest);
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

	// Traite une demande d'obtention de la liste des utilisateurs
	private static String traiteDemandeListeUtilisateurs(Map<String, String[]> pParametres,
			ITraitementRequetesRest pTraiteRequetesRest) {
		return pTraiteRequetesRest.traiteDemandeListeUtilisateurs();
	}

}
