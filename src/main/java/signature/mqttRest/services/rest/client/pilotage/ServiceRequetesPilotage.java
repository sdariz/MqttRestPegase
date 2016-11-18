package signature.mqttRest.services.rest.client.pilotage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import signature.mqttRest.services.rest.client.ClientHttpRest;
import signature.mqttRest.services.rest.serveur.pilotage.GestionnaireRoutesPilotage;
import signature.mqttRest.util.Util;

/**
 * Services d'envoi de requêtes de pilotage vers le serveur REST
 * 
 * @author SDARIZCUREN
 *
 */
public class ServiceRequetesPilotage {

	/**
	 * Pilotage d'un scénario, selon son identifiant
	 * 
	 * @param pHost
	 *            l'adresse IP du serveur REST
	 * @param pPort
	 *            le port TCP utilisé par le serveur
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'expéditeur : peut être vide
	 * @param pReferenceCommande
	 *            la référence unique de la demande : peut être vide
	 * @param pIdScenario
	 *            l'id du scénario à piloter
	 */
	public static void requetePilotageScenario(String pHost, int pPort, String pIdentifiantExpediteur,
			String pReferenceCommande, String pIdScenario) {
		if (pIdentifiantExpediteur == null) {
			pIdentifiantExpediteur = "";
		}

		if (pReferenceCommande == null) {
			pReferenceCommande = "";
		}

		Map<String, String> params = new HashMap<>();
		params.put("idScenario", pIdScenario);
		params.put("idExpediteur", pIdentifiantExpediteur);
		params.put("idCommande", pReferenceCommande);

		ClientHttpRest.envoiRequetePOST(pHost, pPort, GestionnaireRoutesPilotage.PILOTAGE_IDENTIFIANT_SCENARIO, params);
	}

	/**
	 * Pilotage d'un scénario temporaire : non sauvegardé, avec identifiant vide
	 * 
	 * @param pHost
	 *            l'adresse IP du serveur REST
	 * @param pPort
	 *            le port TCP utilisé par le serveur
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'expéditeur : peut être vide
	 * @param pReferenceCommande
	 *            la référence unique de la demande : peut être vide
	 * @param pScenarioTemporaire
	 *            le scénario temporaire à piloter
	 */
	public static void requetePilotageScenario(String pHost, int pPort, String pIdentifiantExpediteur,
			String pReferenceCommande, MessageScenarioMqttRest pScenarioTemporaire) {
		if (pIdentifiantExpediteur == null) {
			pIdentifiantExpediteur = "";
		}

		if (pReferenceCommande == null) {
			pReferenceCommande = "";
		}

		Map<String, String> params = new HashMap<>();
		params.put("scenario", Util.toJsonString(pScenarioTemporaire));
		params.put("idExpediteur", pIdentifiantExpediteur);
		params.put("idCommande", pReferenceCommande);

		ClientHttpRest.envoiRequetePOST(pHost, pPort, GestionnaireRoutesPilotage.PILOTAGE_SCENARIO, params);
	}

	/**
	 * Pilotage d'une liste de messages dans un scénario
	 * 
	 * @param pHost
	 *            l'adresse IP du serveur REST
	 * @param pPort
	 *            le port TCP utilisé par le serveur
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'expéditeur : peut être vide
	 * @param pReferenceCommande
	 *            la référence unique de la demande : peut être vide
	 * @param pIdScenario
	 *            l'identifiant du scénario concerné
	 * @param pMessagesAPiloter
	 *            les messages à piloter dans le scénario
	 */
	public static void requetePilotageScenario(String pHost, int pPort, String pIdentifiantExpediteur,
			String pReferenceCommande, String pIdScenario,
			List<IMessageAffichageEquipementMqttRest> pMessagesAPiloter) {
		if (pIdentifiantExpediteur == null) {
			pIdentifiantExpediteur = "";
		}

		if (pReferenceCommande == null) {
			pReferenceCommande = "";
		}

		Map<String, String> params = new HashMap<>();
		params.put("idScenario", pIdScenario);
		params.put("messages", Util.toJsonString(pMessagesAPiloter));
		params.put("idExpediteur", pIdentifiantExpediteur);
		params.put("idCommande", pReferenceCommande);

		ClientHttpRest.envoiRequetePOST(pHost, pPort, GestionnaireRoutesPilotage.PILOTAGE_MESSAGES_SCENARIO, params);
	}

	/**
	 * Pilotage d'un PMV
	 * 
	 * @param pHost
	 *            l'adresse IP du serveur REST
	 * @param pPort
	 *            le port TCP utilisé par le serveur
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'expéditeur : peut être vide
	 * @param pReferenceCommande
	 *            la référence unique de la demande : peut être vide
	 * @param pIdEquipement
	 *            l'identifiant de l'équipement à piloter
	 * @param pMessageAPiloter
	 *            le message à piloter
	 */
	public static void requetePilotagePmv(String pHost, int pPort, String pIdentifiantExpediteur,
			String pReferenceCommande, String pIdEquipement, MessagePmvMqttRest pMessageAPiloter) {
		requetePilotageEquipement(pHost, pPort, pIdentifiantExpediteur, pReferenceCommande, pIdEquipement,
				Util.toJsonString(pMessageAPiloter), GestionnaireRoutesPilotage.PILOTAGE_PMV);
	}

	/**
	 * Pilotage d'un PPLMV
	 * 
	 * @param pHost
	 *            l'adresse IP du serveur REST
	 * @param pPort
	 *            le port TCP utilisé par le serveur
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'expéditeur : peut être vide
	 * @param pReferenceCommande
	 *            la référence unique de la demande : peut être vide
	 * @param pIdEquipement
	 *            l'identifiant de l'équipement à piloter
	 * @param pMessageAPiloter
	 *            le message à piloter
	 */
	public static void requetePilotagePplmv(String pHost, int pPort, String pIdentifiantExpediteur,
			String pReferenceCommande, String pIdEquipement, MessagePplmvMqttRest pMessageAPiloter) {
		requetePilotageEquipement(pHost, pPort, pIdentifiantExpediteur, pReferenceCommande, pIdEquipement,
				Util.toJsonString(pMessageAPiloter), GestionnaireRoutesPilotage.PILOTAGE_PPLMV);
	}

	/**
	 * Pilotage d'un PPAD
	 * 
	 * @param pHost
	 *            l'adresse IP du serveur REST
	 * @param pPort
	 *            le port TCP utilisé par le serveur
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'expéditeur : peut être vide
	 * @param pReferenceCommande
	 *            la référence unique de la demande : peut être vide
	 * @param pIdEquipement
	 *            l'identifiant de l'équipement à piloter
	 * @param pMessageAPiloter
	 *            le message à piloter
	 */
	public static void requetePilotagePpad(String pHost, int pPort, String pIdentifiantExpediteur,
			String pReferenceCommande, String pIdEquipement, MessagePpadMqttRest pMessageAPiloter) {
		requetePilotageEquipement(pHost, pPort, pIdentifiantExpediteur, pReferenceCommande, pIdEquipement,
				Util.toJsonString(pMessageAPiloter), GestionnaireRoutesPilotage.PILOTAGE_PPAD);
	}

	/**
	 * Pilotage d'un Pictogramme
	 * 
	 * @param pHost
	 *            l'adresse IP du serveur REST
	 * @param pPort
	 *            le port TCP utilisé par le serveur
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'expéditeur : peut être vide
	 * @param pReferenceCommande
	 *            la référence unique de la demande : peut être vide
	 * @param pIdEquipement
	 *            l'identifiant de l'équipement à piloter
	 * @param pMessageAPiloter
	 *            le message à piloter
	 */
	public static void requetePilotagePictogramme(String pHost, int pPort, String pIdentifiantExpediteur,
			String pReferenceCommande, String pIdEquipement, MessagePictogrammeMqttRest pMessageAPiloter) {
		requetePilotageEquipement(pHost, pPort, pIdentifiantExpediteur, pReferenceCommande, pIdEquipement,
				Util.toJsonString(pMessageAPiloter), GestionnaireRoutesPilotage.PILOTAGE_PICTOGRAMME);
	}

	/**
	 * Pilotage d'un Feu R24
	 * 
	 * @param pHost
	 *            l'adresse IP du serveur REST
	 * @param pPort
	 *            le port TCP utilisé par le serveur
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'expéditeur : peut être vide
	 * @param pReferenceCommande
	 *            la référence unique de la demande : peut être vide
	 * @param pIdEquipement
	 *            l'identifiant de l'équipement à piloter
	 * @param pMessageAPiloter
	 *            le message à piloter
	 */
	public static void requetePilotageR24(String pHost, int pPort, String pIdentifiantExpediteur,
			String pReferenceCommande, String pIdEquipement, MessageR24MqttRest pMessageAPiloter) {
		requetePilotageEquipement(pHost, pPort, pIdentifiantExpediteur, pReferenceCommande, pIdEquipement,
				Util.toJsonString(pMessageAPiloter), GestionnaireRoutesPilotage.PILOTAGE_R24);
	}

	/**
	 * Pilotage d'un Prisme
	 * 
	 * @param pHost
	 *            l'adresse IP du serveur REST
	 * @param pPort
	 *            le port TCP utilisé par le serveur
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'expéditeur : peut être vide
	 * @param pReferenceCommande
	 *            la référence unique de la demande : peut être vide
	 * @param pIdEquipement
	 *            l'identifiant de l'équipement à piloter
	 * @param pMessageAPiloter
	 *            le message à piloter
	 */
	public static void requetePilotagePrisme(String pHost, int pPort, String pIdentifiantExpediteur,
			String pReferenceCommande, String pIdEquipement, MessagePrismeMqttRest pMessageAPiloter) {
		requetePilotageEquipement(pHost, pPort, pIdentifiantExpediteur, pReferenceCommande, pIdEquipement,
				Util.toJsonString(pMessageAPiloter), GestionnaireRoutesPilotage.PILOTAGE_PRISME);
	}

	/**
	 * Pilotage d'une Barrière
	 * 
	 * @param pHost
	 *            l'adresse IP du serveur REST
	 * @param pPort
	 *            le port TCP utilisé par le serveur
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'expéditeur : peut être vide
	 * @param pReferenceCommande
	 *            la référence unique de la demande : peut être vide
	 * @param pIdEquipement
	 *            l'identifiant de l'équipement à piloter
	 * @param pMessageAPiloter
	 *            le message à piloter
	 */
	public static void requetePilotageBarriere(String pHost, int pPort, String pIdentifiantExpediteur,
			String pReferenceCommande, String pIdEquipement, MessageBarriereMqttRest pMessageAPiloter) {
		requetePilotageEquipement(pHost, pPort, pIdentifiantExpediteur, pReferenceCommande, pIdEquipement,
				Util.toJsonString(pMessageAPiloter), GestionnaireRoutesPilotage.PILOTAGE_BARRIERE);
	}

	/**
	 * Pilotage d'un BRA
	 * 
	 * @param pHost
	 *            l'adresse IP du serveur REST
	 * @param pPort
	 *            le port TCP utilisé par le serveur
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'expéditeur : peut être vide
	 * @param pReferenceCommande
	 *            la référence unique de la demande : peut être vide
	 * @param pIdEquipement
	 *            l'identifiant de l'équipement à piloter
	 * @param pMessageAPiloter
	 *            le message à piloter
	 */
	public static void requetePilotageBra(String pHost, int pPort, String pIdentifiantExpediteur,
			String pReferenceCommande, String pIdEquipement, MessageBraMqttRest pMessageAPiloter) {
		requetePilotageEquipement(pHost, pPort, pIdentifiantExpediteur, pReferenceCommande, pIdEquipement,
				Util.toJsonString(pMessageAPiloter), GestionnaireRoutesPilotage.PILOTAGE_BRA);
	}

	// Requête de pilotage d'un équipement
	private static void requetePilotageEquipement(String pHost, int pPort, String pIdentifiantExpediteur,
			String pReferenceCommande, String pIdEquipement, String pMessageJson, String pUrl) {
		if (pIdentifiantExpediteur == null) {
			pIdentifiantExpediteur = "";
		}

		if (pReferenceCommande == null) {
			pReferenceCommande = "";
		}

		Map<String, String> params = new HashMap<>();
		params.put("idEquipement", pIdEquipement);
		params.put("idExpediteur", pIdentifiantExpediteur);
		params.put("idCommande", pReferenceCommande);
		params.put("message", pMessageJson);

		ClientHttpRest.envoiRequetePOST(pHost, pPort, pUrl, params);
	}

}
