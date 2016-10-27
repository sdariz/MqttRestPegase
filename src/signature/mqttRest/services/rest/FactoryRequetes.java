package signature.mqttRest.services.rest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import signature.mqttRest.objetsPartages.MessageEtatAffichageMqttRest;
import signature.mqttRest.util.Util;

/**
 * Une fabrique de requ�tes
 * @author SDARIZCUREN
 *
 */
public class FactoryRequetes {
	
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
		
		String retour = ClientHttpRest.envoiRequeteGET(pHost, pPort, FactoryRoutes.getRouteDemandeEtatAffichageEquipement(), params);
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
		String json = ClientHttpRest.envoiRequeteGET(pHost, pPort, FactoryRoutes.getRouteDemandeEtatAffichageEquipement());
		if(json.length() == 0) {
			return new ArrayList<>();
		}
		
		// Formatage du retour vers le bon format
		List<Object> list = Util.jsonToListeObjet(json, MessageEtatAffichageMqttRest.class);
		List<MessageEtatAffichageMqttRest> retour = new ArrayList<>();
		list.forEach(l -> retour.add((MessageEtatAffichageMqttRest)l));
		
		return retour;
	}

}
