package signature.mqttRest.services.rest.serveur.pilotage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import signature.mqttRest.objetsPartages.etatEtPilotage.IMessageAffichageEquipementMqttRest;
import signature.mqttRest.objetsPartages.etatEtPilotage.MessagePmvMqttRest;
import signature.mqttRest.objetsPartages.scenario.MessageScenarioMqttRest;
import signature.mqttRest.services.rest.serveur.ITraitementRequetesRest;
import signature.mqttRest.util.Util;

/**
 * Routes utilis�es pour effectuer des pilotages. Traitement des requ�tes GET et
 * POST re�ues par le serveur REST.
 * 
 * @author SDARIZCUREN
 *
 */
public class GestionnaireRoutesPilotage {

	public final static String PILOTAGE_IDENTIFIANT_SCENARIO = "/pilotage/pilotageIdentifiantScenario";
	public final static String PILOTAGE_SCENARIO = "/pilotage/pilotageScenario";
	public final static String PILOTAGE_MESSAGES_SCENARIO = "/pilotage/pilotageMessagesScenario";
	public final static String PILOTAGE_PMV = "/pilotage/pilotagPmv";

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
		return Arrays.asList(PILOTAGE_IDENTIFIANT_SCENARIO, PILOTAGE_SCENARIO, PILOTAGE_MESSAGES_SCENARIO,
				PILOTAGE_PMV);
	}

	/**
	 * Traite une demande de type GET
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
	 * Traite une demande de type POST
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
		if (PILOTAGE_IDENTIFIANT_SCENARIO.equals(pUri)) {
			pTraiteRequetesRest.traiteDemandePilotageScenario(pParametres.get("idExpediteur")[0],
					pParametres.get("idCommande")[0], pParametres.get("idScenario")[0]);
			return "";
		}

		if (PILOTAGE_SCENARIO.equals(pUri)) {
			// D�s�rialisation du message � piloter
			MessageScenarioMqttRest scenario = (MessageScenarioMqttRest) Util
					.jsonToObjet(pParametres.get("scenario")[0], MessageScenarioMqttRest.class);

			if (scenario == null) {
				return "";
			}

			pTraiteRequetesRest.traiteDemandePilotageScenario(pParametres.get("idExpediteur")[0],
					pParametres.get("idCommande")[0], scenario);
			return "";
		}

		if (PILOTAGE_MESSAGES_SCENARIO.equals(pUri)) {
			// D�s�rialisation des messages � piloter
			List<IMessageAffichageEquipementMqttRest> messages = Util
					.jsonToListeObjet(pParametres.get("messages")[0], IMessageAffichageEquipementMqttRest.class)
					.stream().map(IMessageAffichageEquipementMqttRest.class::cast).collect(Collectors.toList());

			if (messages.size() == 0) {
				return "";
			}

			pTraiteRequetesRest.traiteDemandePilotageScenario(pParametres.get("idExpediteur")[0],
					pParametres.get("idCommande")[0], pParametres.get("idScenario")[0], messages);
			return "";
		}

		if (PILOTAGE_PMV.equals(pUri)) {
			// D�s�rialisation du message � piloter
			MessagePmvMqttRest message = (MessagePmvMqttRest) Util.jsonToObjet(pParametres.get("message")[0],
					MessagePmvMqttRest.class);

			if (message == null) {
				return "";
			}

			pTraiteRequetesRest.traiteDemandePilotagePmv(pParametres.get("idExpediteur")[0],
					pParametres.get("idCommande")[0], pParametres.get("idEquipement")[0], message);
			return "";
		}

		return "";
	}

}
