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
 * Une fabrique de requ�tes
 * @author SDARIZCUREN
 *
 */
public class FactoryRequetesEtatEtPilotage {
	
	public final static String ETAT_AFFICHAGE_EQUIPEMENT = "/etatAffichage";
	public final static String ETAT_TECHNIQUE_EQUIPEMENT = "/etatTechnique";
	
	/**
	 * Demande d'�tat d'affichage d'un �quipement
	 * @param pHost l'adresse IP du serveur REST
	 * @param pPort le port TCP utilis� par le serveur
	 * @param pId l'id de l'�quipement � interroger
	 * @return l'�tat d'affichage de l'�quipement, ou null si probl�me
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
	 * Demande d'�tat d'affichage de tous les �quipements
	 * @param pHost l'adresse IP du serveur REST
	 * @param pPort le port TCP utilis� par le serveur
	 * @return l'�tat d'affichage des �quipements, liste vide si probl�me
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
