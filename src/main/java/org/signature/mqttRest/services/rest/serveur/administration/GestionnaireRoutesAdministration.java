package org.signature.mqttRest.services.rest.serveur.administration;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.signature.mqttRest.services.rest.serveur.ITraitementRequetesRest;
import org.signature.mqttRest.util.Util;

/**
 * Routes utilis�es pour g�rer les commandes d'administration. Traitement des
 * requ�tes GET et POST re�ues par le serveur REST.
 * 
 * @author SDARIZCUREN
 *
 */
public class GestionnaireRoutesAdministration {
	public final static String INTERDICTION_PILOTAGES = "/administration/interdictionPilotages";
	public final static String TEST_PRESENCE = "/administration/testPresence";
	public final static String ACTIVATION_BOUTON = "/administration/activationBouton";
	public final static String LANCEMENT_ACTION_BOUTON = "/administration/lancementActionBouton";
	public final static String FORCAGE_ARRET_APPLICATION = "/administration/forcageArretApplication";

	/**
	 * Donne la liste des routes de type GET
	 * 
	 * @return la liste des routes
	 */
	public static List<String> getGETRoutes() {
		return Arrays.asList(TEST_PRESENCE);
	}

	/**
	 * Donne la liste des routes de type POST
	 * 
	 * @return la liste des routes
	 */
	public static List<String> getPOSTRoutes() {
		return Arrays.asList(INTERDICTION_PILOTAGES, ACTIVATION_BOUTON, LANCEMENT_ACTION_BOUTON,
				FORCAGE_ARRET_APPLICATION);
	}

	/**
	 * Traite une demande de type GET, re�ue par le serveur REST
	 * 
	 * @param pUri                la route � traiter
	 * @param pParametres         les param�tres de la requ�te
	 * @param pTraiteRequetesRest l'objet qui va traiter les requ�tes re�ues
	 * @return la r�ponse � retourner, au format JSON. Cha�ne vide si pas de r�ponse
	 */
	public static String traiteDemandeGET(String pUri, Map<String, String[]> pParametres,
			ITraitementRequetesRest pTraiteRequetesRest) {
		if (TEST_PRESENCE.equals(pUri)) {
			// Pas de traitement, r�ponse directe OK
			return Util.booleanToJsonString(true);
		}

		return "";
	}

	/**
	 * Traite une demande de type POST, re�ue par le serveur REST
	 * 
	 * @param pUri                la route � traiter
	 * @param pParametres         les param�tres de la requ�te
	 * @param pTraiteRequetesRest l'objet qui va traiter les requ�tes re�ues
	 * @return la r�ponse � retourner, au format JSON. Cha�ne vide si pas de r�ponse
	 */
	public static String traiteDemandePOST(String pUri, Map<String, String[]> pParametres,
			ITraitementRequetesRest pTraiteRequetesRest) {
		if (INTERDICTION_PILOTAGES.equals(pUri)) {
			// Soit demande pour certains �quipements en particulier, soit
			// demande pour
			// tous les �quipements
			if (pParametres.get("equipements") == null || pParametres.get("equipements").length == 0) {
				pTraiteRequetesRest.traiteDemandeInterdictionPilotages(pParametres.get("idExpediteur")[0],
						pParametres.get("idCommande")[0], Boolean.parseBoolean(pParametres.get("interdiction")[0]));

				return "";
			}

			// D�s�rialisation des �quipements concern�s
			List<String> eqpts = Util.jsonToListeObjet(pParametres.get("equipements")[0], String.class).stream()
					.map(String.class::cast).collect(Collectors.toList());

			pTraiteRequetesRest.traiteDemandeInterdictionPilotages(pParametres.get("idExpediteur")[0],
					pParametres.get("idCommande")[0], eqpts, Boolean.parseBoolean(pParametres.get("interdiction")[0]));
			return "";
		}

		if (ACTIVATION_BOUTON.equals(pUri)) {
			pTraiteRequetesRest.traiteDemandeActivationBouton(pParametres.get("idExpediteur")[0],
					pParametres.get("idCommande")[0], pParametres.get("idBouton")[0],
					Boolean.parseBoolean(pParametres.get("actif")[0]));
			return "";
		}

		if (LANCEMENT_ACTION_BOUTON.equals(pUri)) {
			pTraiteRequetesRest.traiteDemandeLancementActionBouton(pParametres.get("idExpediteur")[0],
					pParametres.get("idCommande")[0], pParametres.get("idBouton")[0]);
			return "";
		}

		if (FORCAGE_ARRET_APPLICATION.equals(pUri)) {
			pTraiteRequetesRest.traiteForcageArretApplication(pParametres.get("idExpediteur")[0],
					pParametres.get("idCommande")[0]);
			return "";
		}

		return "";
	}
}
