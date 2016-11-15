package signature.mqttRest.services.rest.serveur.interrogationArmoire;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import signature.mqttRest.services.rest.serveur.ITraitementRequetesRest;

/**
 * Routes utilis�es pour g�rer les commandes d'administration. Traitement des
 * requ�tes GET et POST re�ues par le serveur REST.
 * 
 * @author SDARIZCUREN
 *
 */
public class GestionnaireRoutesInterrogationArmoire {
	public final static String LANCEMENT_TEST_EQUIPEMENTS = "/lancementTestEquipements";
	public final static String REMISE_HEURE = "/remiseHeure";

	/**
	 * Donne la liste des routes de type GET
	 * 
	 * @return la liste des routes
	 */
	public static List<String> getGETRoutes() {
		return new ArrayList<String>();
	}

	/**
	 * Donne la liste des routes de type POST
	 * 
	 * @return la liste des routes
	 */
	public static List<String> getPOSTRoutes() {
		return Arrays.asList(LANCEMENT_TEST_EQUIPEMENTS, REMISE_HEURE);
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
		if (LANCEMENT_TEST_EQUIPEMENTS.equals(pUri)) {
			pTraiteRequetesRest.traiteDemandeLancementTestEquipements(pParametres.get("idExpediteur")[0],
					pParametres.get("idCommande")[0], pParametres.get("idArmoire")[0]);
			return "";
		}
		
		if (REMISE_HEURE.equals(pUri)) {
			pTraiteRequetesRest.traiteDemandeRemiseHeureArmoire(pParametres.get("idExpediteur")[0],
					pParametres.get("idCommande")[0], pParametres.get("idArmoire")[0]);
			return "";
		}

		return "";
	}
}
