package org.signature.mqttRest.services.rest.serveur.informationPegase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.signature.mqttRest.services.rest.serveur.ITraitementRequetesRest;
import org.signature.mqttRest.util.Util;

/**
 * Traitement des requ�tes GET et POST re�ues par le serveur REST, de
 * r�cup�ration d'information sur Pegase
 * 
 * @author SDARIZCUREN
 *
 */
public class GestionnaireRoutesInformationPegase {
	public final static String PROPRIETES_EQUIPEMENT = "/informationPegase/proprietesEquipement";
	public final static String PROPRIETES_ARMOIRE = "/informationPegase/proprietesArmoire";

	/**
	 * Donne la liste des routes de type GET
	 * 
	 * @return la liste des routes
	 */
	public static List<String> getGETRoutes() {
		return Arrays.asList(PROPRIETES_EQUIPEMENT, PROPRIETES_ARMOIRE);
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
		if (PROPRIETES_EQUIPEMENT.equals(pUri)) {
			// Cas demande pour un �quipement ou pour l'ensemble des �quipements
			if (pParametres.get("idEquipement") == null || pParametres.get("idEquipement").length == 0) {
				return Util.toJsonString(
						pTraiteRequetesRest.traiteDemandeProprietesEquipements(pParametres.get("idExpediteur")[0],
								pParametres.get("idCommande")[0]));
			}
			
			return Util.toJsonString(
					pTraiteRequetesRest.traiteDemandeProprietesEquipement(pParametres.get("idExpediteur")[0],
							pParametres.get("idCommande")[0], pParametres.get("idEquipement")[0]));
		}
		
		if (PROPRIETES_ARMOIRE.equals(pUri)) {
			// Cas demande pour une armoire ou pour l'ensemble des armoires
			if (pParametres.get("idArmoire") == null || pParametres.get("idArmoire").length == 0) {
				return Util.toJsonString(
						pTraiteRequetesRest.traiteDemandeProprietesArmoires(pParametres.get("idExpediteur")[0],
								pParametres.get("idCommande")[0]));
			}
			
			return Util.toJsonString(
					pTraiteRequetesRest.traiteDemandeProprietesArmoire(pParametres.get("idExpediteur")[0],
							pParametres.get("idCommande")[0], pParametres.get("idArmoire")[0]));
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
