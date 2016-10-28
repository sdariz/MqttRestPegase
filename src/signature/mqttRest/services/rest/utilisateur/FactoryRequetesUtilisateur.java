package signature.mqttRest.services.rest.utilisateur;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import signature.mqttRest.objetsPartages.MessageUtilisateurMqttRest;
import signature.mqttRest.services.rest.client.ClientHttpRest;
import signature.mqttRest.util.Util;

public class FactoryRequetesUtilisateur {
	public final static String LISTE_UTILISATEURS = "/utilisateurs";

	/**
	 * Demande la liste des équipements
	 * 
	 * @param pHost
	 *            l'adresse IP du serveur REST
	 * @param pPort
	 *            le port TCP utilisé par le serveur
	 */
	public static List<MessageUtilisateurMqttRest> requeteDemandeListeUtilisateurs(String pHost, int pPort) {
		String json = ClientHttpRest.envoiRequeteGET(pHost, pPort, LISTE_UTILISATEURS);
		if (json.length() == 0) {
			return new ArrayList<>();
		}

		// Formatage du retour vers le bon format
		List<MessageUtilisateurMqttRest> retour = Util.jsonToListeObjet(json, MessageUtilisateurMqttRest.class)
				.stream().map(MessageUtilisateurMqttRest.class::cast).collect(Collectors.toList());

		return retour;
	}

}
