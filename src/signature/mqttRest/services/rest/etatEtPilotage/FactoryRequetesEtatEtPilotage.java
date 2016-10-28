package signature.mqttRest.services.rest.etatEtPilotage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import signature.mqttRest.objetsPartages.MessageEtatAffichageMqttRest;
import signature.mqttRest.services.rest.client.ClientHttpRest;
import signature.mqttRest.util.Util;

/**
 * Une fabrique de requêtes
 * @author SDARIZCUREN
 *
 */
public class FactoryRequetesEtatEtPilotage {
	
	public final static String ETAT_AFFICHAGE_EQUIPEMENT = "/etatAffichage";
	public final static String ETAT_TECHNIQUE_EQUIPEMENT = "/etatTechnique";
	
	/**
	 * Demande d'état d'affichage d'un équipement
	 * @param pHost l'adresse IP du serveur REST
	 * @param pPort le port TCP utilisé par le serveur
	 * @param pId l'id de l'équipement à interroger
	 * @return l'état d'affichage de l'équipement, ou null si problème
	 */
	public static MessageEtatAffichageMqttRest requeteDemandeEtatAffichageEquipement(String pHost, int pPort, String pId) {
		Map<String, String> params = new HashMap<>();
		params.put("id", pId);
		
		String retour = ClientHttpRest.envoiRequeteGET(pHost, pPort, ETAT_AFFICHAGE_EQUIPEMENT, params);
		if(retour.length() == 0) {
			return null;
		}
		
		return (MessageEtatAffichageMqttRest)Util.jsonToObjet(retour, MessageEtatAffichageMqttRest.class);
	}
	
	/**
	 * Demande d'état d'affichage de tous les équipements
	 * @param pHost l'adresse IP du serveur REST
	 * @param pPort le port TCP utilisé par le serveur
	 * @return l'état d'affichage des équipements, liste vide si problème
	 */
	public static List<MessageEtatAffichageMqttRest> requeteDemandeEtatAffichageEquipements(String pHost, int pPort) {
		String json = ClientHttpRest.envoiRequeteGET(pHost, pPort, ETAT_AFFICHAGE_EQUIPEMENT);
		if(json.length() == 0) {
			return new ArrayList<>();
		}
		
		// Formatage du retour vers le bon format
		List<MessageEtatAffichageMqttRest> retour = Util.jsonToListeObjet(json, MessageEtatAffichageMqttRest.class)
				.stream().map(MessageEtatAffichageMqttRest.class::cast).collect(Collectors.toList());
		
		return retour;
	}

}
