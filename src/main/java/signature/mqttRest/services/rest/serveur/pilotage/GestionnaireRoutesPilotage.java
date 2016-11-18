package signature.mqttRest.services.rest.serveur.pilotage;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import signature.mqttRest.objetsPartages.etatEtPilotage.IMessageAffichageEquipementMqttRest;
import signature.mqttRest.objetsPartages.etatEtPilotage.MessageBarriereMqttRest;
import signature.mqttRest.objetsPartages.etatEtPilotage.MessageBraMqttRest;
import signature.mqttRest.objetsPartages.etatEtPilotage.MessagePictogrammeMqttRest;
import signature.mqttRest.objetsPartages.etatEtPilotage.MessagePmvMqttRest;
import signature.mqttRest.objetsPartages.etatEtPilotage.MessagePpadMqttRest;
import signature.mqttRest.objetsPartages.etatEtPilotage.MessagePplmvMqttRest;
import signature.mqttRest.objetsPartages.etatEtPilotage.MessagePrismeMqttRest;
import signature.mqttRest.objetsPartages.etatEtPilotage.MessageR24MqttRest;
import signature.mqttRest.objetsPartages.scenario.MessageScenarioMqttRest;
import signature.mqttRest.services.rest.serveur.ITraitementRequetesRest;
import signature.mqttRest.util.Util;

/**
 * Routes utilisées pour effectuer des pilotages. Traitement des requêtes GET et
 * POST reçues par le serveur REST.
 * 
 * @author SDARIZCUREN
 *
 */
public class GestionnaireRoutesPilotage {

	public final static String PILOTAGE_IDENTIFIANT_SCENARIO = "/pilotage/pilotageIdentifiantScenario";
	public final static String PILOTAGE_SCENARIO = "/pilotage/pilotageScenario";
	public final static String PILOTAGE_MESSAGES_SCENARIO = "/pilotage/pilotageMessagesScenario";
	public final static String PILOTAGE_PMV = "/pilotage/pilotagePmv";
	public final static String PILOTAGE_PPLMV = "/pilotage/pilotagePplmv";
	public final static String PILOTAGE_PPAD = "/pilotage/pilotagePpad";
	public final static String PILOTAGE_PICTOGRAMME = "/pilotage/pilotagePictogramme";
	public final static String PILOTAGE_R24 = "/pilotage/pilotageR24";
	public final static String PILOTAGE_PRISME = "/pilotage/pilotagePrisme";
	public final static String PILOTAGE_BARRIERE = "/pilotage/pilotageBarriere";
	public final static String PILOTAGE_BRA = "/pilotage/pilotageBra";
	public final static String PILOTAGE_MESSAGES = "/pilotage/pilotageMessages";
	public final static String PILOTAGE_EN_COURS = "/pilotage/pilotageEnCours";

	/**
	 * Donne la liste des routes de type GET
	 * 
	 * @return la liste des routes
	 */
	public static List<String> getGETRoutes() {
		return Arrays.asList(PILOTAGE_EN_COURS);
	}

	/**
	 * Donne la liste des routes de type POST
	 * 
	 * @return la liste des routes
	 */
	public static List<String> getPOSTRoutes() {
		return Arrays.asList(PILOTAGE_IDENTIFIANT_SCENARIO, PILOTAGE_SCENARIO, PILOTAGE_MESSAGES_SCENARIO, PILOTAGE_PMV,
				PILOTAGE_PPLMV, PILOTAGE_PPAD, PILOTAGE_PICTOGRAMME, PILOTAGE_R24, PILOTAGE_PRISME, PILOTAGE_BARRIERE,
				PILOTAGE_BRA, PILOTAGE_MESSAGES);
	}

	/**
	 * Traite une demande de type GET
	 * 
	 * @param pUri
	 *            la route à traiter
	 * @param pParametres
	 *            les paramètres de la requête
	 * @param pTraiteRequetesRest
	 *            l'objet qui va traiter les requêtes reçues
	 * @return la réponse à retourner, au format JSON. Chaîne vide si pas de
	 *         réponse
	 */
	public static String traiteDemandeGET(String pUri, Map<String, String[]> pParametres,
			ITraitementRequetesRest pTraiteRequetesRest) {
		if (PILOTAGE_EN_COURS.equals(pUri)) {
			// Soit demande d'un équipement en particulier, soit demande pour
			// tous les équipements
			if (pParametres.get("idEquipement") == null || pParametres.get("idEquipement").length == 0) {
				return Util
						.toJsonString(pTraiteRequetesRest.traiteDemandePilotageEnCours(pParametres.get("idExpediteur")[0],
								pParametres.get("idCommande")[0]));
			}
			
			return Util
					.toJsonString(pTraiteRequetesRest.traiteDemandePilotageEnCours(pParametres.get("idExpediteur")[0],
							pParametres.get("idCommande")[0], pParametres.get("idEquipement")[0]));
		}

		return "";
	}

	/**
	 * Traite une demande de type POST
	 * 
	 * @param pUri
	 *            la route à traiter
	 * @param pParametres
	 *            les paramètres de la requête
	 * @param pTraiteRequetesRest
	 *            l'objet qui va traiter les requêtes reçues
	 * @return la réponse à retourner, au format JSON. Chaîne vide si pas de
	 *         réponse
	 */
	public static String traiteDemandePOST(String pUri, Map<String, String[]> pParametres,
			ITraitementRequetesRest pTraiteRequetesRest) {
		if (PILOTAGE_IDENTIFIANT_SCENARIO.equals(pUri)) {
			pTraiteRequetesRest.traiteDemandePilotageScenario(pParametres.get("idExpediteur")[0],
					pParametres.get("idCommande")[0], pParametres.get("idScenario")[0]);
			return "";
		}

		if (PILOTAGE_SCENARIO.equals(pUri)) {
			// Désérialisation du message à piloter
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
			// Désérialisation des messages à piloter
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
			// Désérialisation du message à piloter
			MessagePmvMqttRest message = (MessagePmvMqttRest) Util.jsonToObjet(pParametres.get("message")[0],
					MessagePmvMqttRest.class);

			if (message == null) {
				return "";
			}

			pTraiteRequetesRest.traiteDemandePilotagePmv(pParametres.get("idExpediteur")[0],
					pParametres.get("idCommande")[0], pParametres.get("idEquipement")[0], message);
			return "";
		}

		if (PILOTAGE_PPLMV.equals(pUri)) {
			// Désérialisation du message à piloter
			MessagePplmvMqttRest message = (MessagePplmvMqttRest) Util.jsonToObjet(pParametres.get("message")[0],
					MessagePplmvMqttRest.class);

			if (message == null) {
				return "";
			}

			pTraiteRequetesRest.traiteDemandePilotagePplmv(pParametres.get("idExpediteur")[0],
					pParametres.get("idCommande")[0], pParametres.get("idEquipement")[0], message);
			return "";
		}

		if (PILOTAGE_PPAD.equals(pUri)) {
			// Désérialisation du message à piloter
			MessagePpadMqttRest message = (MessagePpadMqttRest) Util.jsonToObjet(pParametres.get("message")[0],
					MessagePpadMqttRest.class);

			if (message == null) {
				return "";
			}

			pTraiteRequetesRest.traiteDemandePilotagePpad(pParametres.get("idExpediteur")[0],
					pParametres.get("idCommande")[0], pParametres.get("idEquipement")[0], message);
			return "";
		}

		if (PILOTAGE_PICTOGRAMME.equals(pUri)) {
			// Désérialisation du message à piloter
			MessagePictogrammeMqttRest message = (MessagePictogrammeMqttRest) Util
					.jsonToObjet(pParametres.get("message")[0], MessagePictogrammeMqttRest.class);

			if (message == null) {
				return "";
			}

			pTraiteRequetesRest.traiteDemandePilotagePictogramme(pParametres.get("idExpediteur")[0],
					pParametres.get("idCommande")[0], pParametres.get("idEquipement")[0], message);
			return "";
		}

		if (PILOTAGE_R24.equals(pUri)) {
			// Désérialisation du message à piloter
			MessageR24MqttRest message = (MessageR24MqttRest) Util.jsonToObjet(pParametres.get("message")[0],
					MessageR24MqttRest.class);

			if (message == null) {
				return "";
			}

			pTraiteRequetesRest.traiteDemandePilotageR24(pParametres.get("idExpediteur")[0],
					pParametres.get("idCommande")[0], pParametres.get("idEquipement")[0], message);
			return "";
		}

		if (PILOTAGE_PRISME.equals(pUri)) {
			// Désérialisation du message à piloter
			MessagePrismeMqttRest message = (MessagePrismeMqttRest) Util.jsonToObjet(pParametres.get("message")[0],
					MessagePrismeMqttRest.class);

			if (message == null) {
				return "";
			}

			pTraiteRequetesRest.traiteDemandePilotagePrisme(pParametres.get("idExpediteur")[0],
					pParametres.get("idCommande")[0], pParametres.get("idEquipement")[0], message);
			return "";
		}

		if (PILOTAGE_BARRIERE.equals(pUri)) {
			// Désérialisation du message à piloter
			MessageBarriereMqttRest message = (MessageBarriereMqttRest) Util.jsonToObjet(pParametres.get("message")[0],
					MessageBarriereMqttRest.class);

			if (message == null) {
				return "";
			}

			pTraiteRequetesRest.traiteDemandePilotageBarriere(pParametres.get("idExpediteur")[0],
					pParametres.get("idCommande")[0], pParametres.get("idEquipement")[0], message);
			return "";
		}

		if (PILOTAGE_BRA.equals(pUri)) {
			// Désérialisation du message à piloter
			MessageBraMqttRest message = (MessageBraMqttRest) Util.jsonToObjet(pParametres.get("message")[0],
					MessageBraMqttRest.class);

			if (message == null) {
				return "";
			}

			pTraiteRequetesRest.traiteDemandePilotageBra(pParametres.get("idExpediteur")[0],
					pParametres.get("idCommande")[0], pParametres.get("idEquipement")[0], message);
			return "";
		}

		if (PILOTAGE_MESSAGES.equals(pUri)) {
			// Désérialisation des messages à piloter
			List<IMessageAffichageEquipementMqttRest> messages = Util
					.jsonToListeObjet(pParametres.get("messages")[0], IMessageAffichageEquipementMqttRest.class)
					.stream().map(IMessageAffichageEquipementMqttRest.class::cast).collect(Collectors.toList());

			if (messages.size() == 0) {
				return "";
			}

			pTraiteRequetesRest.traiteDemandePilotageMessages(pParametres.get("idExpediteur")[0],
					pParametres.get("idCommande")[0], messages);
			return "";
		}

		return "";
	}
}
