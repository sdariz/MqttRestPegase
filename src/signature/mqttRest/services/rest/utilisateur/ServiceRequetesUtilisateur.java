package signature.mqttRest.services.rest.utilisateur;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import signature.mqttRest.objetsPartages.utilisateur.MessageUtilisateurMqttRest;
import signature.mqttRest.services.rest.client.ClientHttpRest;
import signature.mqttRest.util.Util;

/**
 * Services d'envoi de requ�tes vers le serveur REST, et retour de la r�ponse.
 * 
 * @author SDARIZCUREN
 *
 */
public class ServiceRequetesUtilisateur {

	/**
	 * Demande la liste des �quipements, au serveur REST
	 * 
	 * @param pHost
	 *            l'adresse IP du serveur REST
	 * @param pPort
	 *            le port TCP utilis� par le serveur
	 * @return la liste des utilisateurs, vide si aucun (ou probl�me)
	 */
	public static List<MessageUtilisateurMqttRest> requeteDemandeListeUtilisateurs(String pHost, int pPort) {
		String json = ClientHttpRest.envoiRequeteGET(pHost, pPort, GestionnaireRoutesUtilisateur.LISTE_UTILISATEURS);
		if (json.length() == 0) {
			return new ArrayList<>();
		}

		// Formatage du retour vers le bon format
		List<MessageUtilisateurMqttRest> retour = Util.jsonToListeObjet(json, MessageUtilisateurMqttRest.class).stream()
				.map(MessageUtilisateurMqttRest.class::cast).collect(Collectors.toList());

		return retour;
	}

	/**
	 * Demande l'utilisateur connect�, au serveur REST
	 * 
	 * @param pHost
	 *            l'adresse IP du serveur REST
	 * @param pPort
	 *            le port TCP utilis� par le serveur
	 * @param l'utilisateur
	 *            connect�, null si aucun (ou probl�me)
	 */
	public static MessageUtilisateurMqttRest requeteDemandeUtilisateurConnecte(String pHost, int pPort) {
		String json = ClientHttpRest.envoiRequeteGET(pHost, pPort, GestionnaireRoutesUtilisateur.UTILISATEUR_CONNECTE);
		if (json.length() == 0) {
			return null;
		}

		return (MessageUtilisateurMqttRest) Util.jsonToObjet(json, MessageUtilisateurMqttRest.class);
	}

	/**
	 * Demande � valider un identifiant et mot de passe, au serveur REST
	 * 
	 * @param pHost
	 *            l'adresse IP du serveur REST
	 * @param pPort
	 *            le port TCP utilis� par le serveur
	 * @param pLogin
	 *            le login � valider
	 * @param pMotPasse
	 *            le mot de passe associ� au login
	 * @return true si valide, sinon false
	 */
	public static boolean requeteDemandeIdentifiantsValide(String pHost, int pPort, String pLogin, String pMotPasse) {
		Map<String, String> params = new HashMap<>();
		params.put("login", pLogin);
		params.put("password", pMotPasse);

		String json = ClientHttpRest.envoiRequeteGET(pHost, pPort, GestionnaireRoutesUtilisateur.IDENTIFIANTS_VALIDE,
				params);
		if (json.length() == 0) {
			return false;
		}

		return Util.jsonToBoolean(json);
	}

}
