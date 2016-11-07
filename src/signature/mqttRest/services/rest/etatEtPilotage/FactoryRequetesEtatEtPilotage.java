package signature.mqttRest.services.rest.etatEtPilotage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import signature.mqttRest.objetsPartages.etatEtPilotage.MessageEtatAffichageMqttRest;
import signature.mqttRest.services.rest.client.ClientHttpRest;
import signature.mqttRest.services.rest.serveur.ITraitementRequetesRest;
import signature.mqttRest.util.Util;

/**
 * Une fabrique de requêtes
 * 
 * @author SDARIZCUREN
 *
 */
public class FactoryRequetesEtatEtPilotage {

	private final static String ETAT_AFFICHAGE_EQUIPEMENT = "/etatAffichage";
	private final static String ETAT_TECHNIQUE_EQUIPEMENT = "/etatTechnique";

	/**
	 * Donne la liste des routes de type GET
	 * 
	 * @return la liste des routes
	 */
	public static List<String> getGETRoutes() {
		return Arrays.asList(ETAT_AFFICHAGE_EQUIPEMENT, ETAT_TECHNIQUE_EQUIPEMENT);
	}

	/**
	 * Donne la liste des routes de type POST
	 * 
	 * @return la liste des routes
	 */
	public static List<String> getPOSTRoutes() {
		return new ArrayList<>();
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
	 * @return la réponse à retourner, au format JSON. Chaîne vide si pas de réponse
	 */
	public static String traiteDemandeGET(String pUri, Map<String, String[]> pParametres,
			ITraitementRequetesRest pTraiteRequetesRest) {
		if (ETAT_AFFICHAGE_EQUIPEMENT.equals(pUri)) {
			// Soit demande d'un équipement en particulier, soit demande pour
			// tous les équipements
			if (pParametres.keySet().size() == 0) {
				return Util.toJsonString(pTraiteRequetesRest.demandeEtatAffichageEquipements());
			}
			
			// Décodage de l'id de l'équipement
			return Util.toJsonString(pTraiteRequetesRest.demandeEtatAffichageEquipement(pParametres.get("id")[0]));
		}
		
		if(ETAT_TECHNIQUE_EQUIPEMENT.equals(pUri)) {
			// Soit demande d'un équipement en particulier, soit demande pour
			// tous les équipements
			if (pParametres.keySet().size() == 0) {
				return pTraiteRequetesRest.demandeEtatTechniqueEquipements();
			}
			
			// Décodage de l'id de l'équipement
			return pTraiteRequetesRest.demandeEtatTechniqueEquipement(pParametres.get("id")[0]);
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
	 * @return la réponse à retourner, au format JSON. Chaîne vide si pas de réponse
	 */
	public static String traiteDemandePOST(String pUri, Map<String, String[]> pParametres,
			ITraitementRequetesRest pTraiteRequetesRest) {
		return "";
	}

	/**
	 * Demande d'état d'affichage d'un équipement
	 * 
	 * @param pHost
	 *            l'adresse IP du serveur REST
	 * @param pPort
	 *            le port TCP utilisé par le serveur
	 * @param pId
	 *            l'id de l'équipement à interroger
	 * @return l'état d'affichage de l'équipement, ou null si problème
	 */
	public static MessageEtatAffichageMqttRest requeteDemandeEtatAffichageEquipement(String pHost, int pPort,
			String pId) {
		Map<String, String> params = new HashMap<>();
		params.put("id", pId);

		String json = ClientHttpRest.envoiRequeteGET(pHost, pPort, ETAT_AFFICHAGE_EQUIPEMENT, params);
		if (json.length() == 0) {
			return null;
		}

		return (MessageEtatAffichageMqttRest) Util.jsonToObjet(json, MessageEtatAffichageMqttRest.class);
	}

	/**
	 * Demande d'état d'affichage de tous les équipements
	 * 
	 * @param pHost
	 *            l'adresse IP du serveur REST
	 * @param pPort
	 *            le port TCP utilisé par le serveur
	 * @return l'état d'affichage des équipements, liste vide si problème
	 */
	public static List<MessageEtatAffichageMqttRest> requeteDemandeEtatAffichageEquipements(String pHost, int pPort) {
		String json = ClientHttpRest.envoiRequeteGET(pHost, pPort, ETAT_AFFICHAGE_EQUIPEMENT);
		if (json.length() == 0) {
			return new ArrayList<>();
		}

		// Formatage du retour vers le bon format
		List<MessageEtatAffichageMqttRest> retour = Util.jsonToListeObjet(json, MessageEtatAffichageMqttRest.class)
				.stream().map(MessageEtatAffichageMqttRest.class::cast).collect(Collectors.toList());

		return retour;
	}

}
