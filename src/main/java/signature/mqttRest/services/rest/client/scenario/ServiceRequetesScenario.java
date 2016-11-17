package signature.mqttRest.services.rest.client.scenario;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import signature.mqttRest.objetsPartages.scenario.MessageScenarioMqttRest;
import signature.mqttRest.services.rest.client.ClientHttpRest;
import signature.mqttRest.services.rest.serveur.scenario.GestionnaireRoutesScenario;
import signature.mqttRest.util.Util;

/**
 * Requêtes vers le serveur REST pour récupérer ou agir sur les scénarios
 * 
 * @author SDARIZCUREN
 *
 */
public class ServiceRequetesScenario {

	/**
	 * Envoi au serveur REST une demande d'obtention de la liste des
	 * identifiants des scénarios
	 * 
	 * @param pHost
	 *            l'adresse IP du serveur REST
	 * @param pPort
	 *            le port TCP utilisé par le serveur
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'expéditeur : peut être vide
	 * @param pReferenceCommande
	 *            la référence unique de la demande : peut être vide
	 * @return la liste des identifiants des scénarios
	 */
	public static List<String> requeteDemandeIdentifiantsScenarios(String pHost, int pPort,
			String pIdentifiantExpediteur, String pReferenceCommande) {
		if (pIdentifiantExpediteur == null) {
			pIdentifiantExpediteur = "";
		}

		if (pReferenceCommande == null) {
			pReferenceCommande = "";
		}

		Map<String, String> params = new HashMap<>();
		params.put("idExpediteur", pIdentifiantExpediteur);
		params.put("idCommande", pReferenceCommande);

		String json = ClientHttpRest.envoiRequeteGET(pHost, pPort, GestionnaireRoutesScenario.LISTE_IDENTIFIANTS,
				params);
		if (json.length() == 0) {
			return new ArrayList<>();
		}

		// Formatage du retour vers le bon format
		return Util.jsonToListeObjet(json, String.class).stream().map(String.class::cast).collect(Collectors.toList());
	}

	/**
	 * Envoi au serveur REST une demande de scénario en particulier
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
	 *            l'identifiant du scénario à récupérer
	 * @return le scénario demandé, ou null si problème
	 */
	public static MessageScenarioMqttRest requeteDemandeScenario(String pHost, int pPort, String pIdentifiantExpediteur,
			String pReferenceCommande, String pIdScenario) {
		if (pIdentifiantExpediteur == null) {
			pIdentifiantExpediteur = "";
		}

		if (pReferenceCommande == null) {
			pReferenceCommande = "";
		}

		Map<String, String> params = new HashMap<>();
		params.put("idExpediteur", pIdentifiantExpediteur);
		params.put("idCommande", pReferenceCommande);
		params.put("idScenario", pIdScenario);

		String json = ClientHttpRest.envoiRequeteGET(pHost, pPort, GestionnaireRoutesScenario.SCENARIO, params);
		if (json.length() == 0) {
			return null;
		}

		// Formatage du retour vers le bon format
		return (MessageScenarioMqttRest) Util.jsonToObjet(json, MessageScenarioMqttRest.class);
	}

	/**
	 * Envoi au serveur REST une demande la liste des scénarios
	 * 
	 * @param pHost
	 *            l'adresse IP du serveur REST
	 * @param pPort
	 *            le port TCP utilisé par le serveur
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'expéditeur : peut être vide
	 * @param pReferenceCommande
	 *            la référence unique de la demande : peut être vide
	 * @return le scénario demandé, ou null si problème
	 */
	public static List<MessageScenarioMqttRest> requeteDemandeScenarios(String pHost, int pPort,
			String pIdentifiantExpediteur, String pReferenceCommande) {
		if (pIdentifiantExpediteur == null) {
			pIdentifiantExpediteur = "";
		}

		if (pReferenceCommande == null) {
			pReferenceCommande = "";
		}

		Map<String, String> params = new HashMap<>();
		params.put("idExpediteur", pIdentifiantExpediteur);
		params.put("idCommande", pReferenceCommande);

		String json = ClientHttpRest.envoiRequeteGET(pHost, pPort, GestionnaireRoutesScenario.SCENARIO, params);
		if (json.length() == 0) {
			return new ArrayList<>();
		}

		// Formatage du retour vers le bon format
		return Util.jsonToListeObjet(json, MessageScenarioMqttRest.class).stream()
				.map(MessageScenarioMqttRest.class::cast).collect(Collectors.toList());
	}

}
