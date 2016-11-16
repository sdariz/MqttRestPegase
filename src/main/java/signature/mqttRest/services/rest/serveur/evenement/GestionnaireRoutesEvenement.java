package signature.mqttRest.services.rest.serveur.evenement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import signature.mqttRest.services.rest.serveur.ITraitementRequetesRest;
import signature.mqttRest.util.Util;

/**
 * Traitement des requ�tes GET et POST re�ues par le serveur REST, qui concerne
 * des �v�nements sur Pegase
 * 
 * @author SDARIZCUREN
 *
 */
public class GestionnaireRoutesEvenement {
	public final static String LISTE_ALARMES = "/evenement/listeAlarmes";
	public final static String ETAT_AFFICHAGE_EQUIPEMENT_POUR_DATE = "/evenement/etatAffichageEquipementPourDate";
	public final static String ETAT_AFFICHAGE_EQUIPEMENT_ENTRE_DEUX_DATES = "/evenement/etatAffichageEquipementEntreDeuxDates";

	/**
	 * Donne la liste des routes de type GET
	 * 
	 * @return la liste des routes
	 */
	public static List<String> getGETRoutes() {
		return Arrays.asList(LISTE_ALARMES, ETAT_AFFICHAGE_EQUIPEMENT_POUR_DATE,
				ETAT_AFFICHAGE_EQUIPEMENT_ENTRE_DEUX_DATES);
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
		if (LISTE_ALARMES.equals(pUri)) {
			Date horodateDebut = new Date(new Long(pParametres.get("horodateDebut")[0]));
			Date horodateFin = new Date(new Long(pParametres.get("horodateFin")[0]));
			boolean active = Boolean.parseBoolean(pParametres.get("active")[0]);

			return Util.toJsonString(pTraiteRequetesRest.traiteDemandeListeAlarmes(pParametres.get("idExpediteur")[0],
					pParametres.get("idCommande")[0], pParametres.get("idEquipement")[0], horodateDebut, horodateFin,
					active));
		}

		if (ETAT_AFFICHAGE_EQUIPEMENT_POUR_DATE.equals(pUri)) {
			Date horodate = new Date(new Long(pParametres.get("horodate")[0]));

			return Util.toJsonString(
					pTraiteRequetesRest.traiteDemandeEtatAffichageEquipementPourDate(pParametres.get("idExpediteur")[0],
							pParametres.get("idCommande")[0], pParametres.get("idEquipement")[0], horodate));
		}

		if (ETAT_AFFICHAGE_EQUIPEMENT_ENTRE_DEUX_DATES.equals(pUri)) {
			Date horodateDebut = new Date(new Long(pParametres.get("horodateDebut")[0]));
			Date horodateFin = new Date(new Long(pParametres.get("horodateFin")[0]));

			return Util.toJsonString(pTraiteRequetesRest.traiteDemandeEtatAffichageEquipementEntreDeuxDates(
					pParametres.get("idExpediteur")[0], pParametres.get("idCommande")[0],
					pParametres.get("idEquipement")[0], horodateDebut, horodateFin));
		}

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

		return "";
	}

}
