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
 * Requ�tes vers le serveur REST pour r�cup�rer ou agir sur les sc�narios
 * 
 * @author SDARIZCUREN
 *
 */
public class ServiceRequetesScenario {

	/**
	 * Envoi au serveur REST une demande d'obtention de la liste des
	 * identifiants des sc�narios
	 * 
	 * @param pHost
	 *            l'adresse IP du serveur REST
	 * @param pPort
	 *            le port TCP utilis� par le serveur
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'exp�diteur : peut �tre vide
	 * @param pReferenceCommande
	 *            la r�f�rence unique de la demande : peut �tre vide
	 * @return la liste des identifiants des sc�narios
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
	 * Envoi au serveur REST une demande de sc�nario en particulier
	 * 
	 * @param pHost
	 *            l'adresse IP du serveur REST
	 * @param pPort
	 *            le port TCP utilis� par le serveur
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'exp�diteur : peut �tre vide
	 * @param pReferenceCommande
	 *            la r�f�rence unique de la demande : peut �tre vide
	 * @param pIdScenario
	 *            l'identifiant du sc�nario � r�cup�rer
	 * @return le sc�nario demand�, ou null si probl�me
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
	 * Envoi au serveur REST une demande la liste des sc�narios
	 * 
	 * @param pHost
	 *            l'adresse IP du serveur REST
	 * @param pPort
	 *            le port TCP utilis� par le serveur
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'exp�diteur : peut �tre vide
	 * @param pReferenceCommande
	 *            la r�f�rence unique de la demande : peut �tre vide
	 * @return le sc�nario demand�, ou null si probl�me
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
