package signature.mqttRest.services.rest.utilisateur;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import signature.mqttRest.objetsPartages.MessageUtilisateurMqttRest;
import signature.mqttRest.services.rest.client.ClientHttpRest;
import signature.mqttRest.services.rest.serveur.ITraitementRequetesRest;
import signature.mqttRest.util.Util;

public class FactoryRequetesUtilisateur {
	private final static String LISTE_UTILISATEURS = "/utilisateurs";
	private final static String UTILISATEUR_CONNECTE = "/utilisateurConnecte";
	private final static String IDENTIFIANTS_VALIDE = "/identifiantsValide";
	
	/**
	 * Donne la liste des routes de type GET
	 * 
	 * @return la liste des routes
	 */
	public static List<String> getGETRoutes() {
		return Arrays.asList(LISTE_UTILISATEURS, UTILISATEUR_CONNECTE, IDENTIFIANTS_VALIDE);
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
	 *            la route � traiter
	 * @param pParametres
	 *            les param�tres de la requ�te
	 * @param pTraiteRequetesRest
	 *            l'objet qui va traiter les requ�tes re�ues
	 * @return la r�ponse � retourner, au format JSON. Cha�ne vide si pas de r�ponse
	 */
	public static String traiteDemandeGET(String pUri, Map<String, String[]> pParametres,
			ITraitementRequetesRest pTraiteRequetesRest) {
		if (LISTE_UTILISATEURS.equals(pUri)) {
			return Util.toJsonString(pTraiteRequetesRest.traiteDemandeListeUtilisateurs());
		}
		
		if (UTILISATEUR_CONNECTE.equals(pUri)) {
			return Util.toJsonString(pTraiteRequetesRest.traiteDemandeUtilisateurConnecte());
		}
		
		if (IDENTIFIANTS_VALIDE.equals(pUri)) {
			return Util.toJsonString(pTraiteRequetesRest.estValide(pParametres.get("login")[0], pParametres.get("password")[0]));
		}
		
		return "";
	}
	
	/**
	 * Traite une demande de type POST
	 * 
	 * @param pUri
	 *            la route � traiter
	 * @param pParametres
	 *            les param�tres de la requ�te
	 * @param pTraiteRequetesRest
	 *            l'objet qui va traiter les requ�tes re�ues
	 * @return la r�ponse � retourner, au format JSON. Cha�ne vide si pas de r�ponse
	 */
	public static String traiteDemandePOST(String pUri, Map<String, String[]> pParametres,
			ITraitementRequetesRest pTraiteRequetesRest) {
		return "";
	}

	/**
	 * Demande la liste des �quipements
	 * 
	 * @param pHost
	 *            l'adresse IP du serveur REST
	 * @param pPort
	 *            le port TCP utilis� par le serveur
	 *            @return la liste des utilisateurs, vide si aucun (ou probl�me)
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
	
	/**
	 * Demande l'utilisateur connect�
	 * 
	 * @param pHost
	 *            l'adresse IP du serveur REST
	 * @param pPort
	 *            le port TCP utilis� par le serveur
	 *            @param l'utilisateur connect�, null si aucun (ou probl�me)
	 */
	public static MessageUtilisateurMqttRest requeteDemandeUtilisateurConnecte(String pHost, int pPort) {
		String json = ClientHttpRest.envoiRequeteGET(pHost, pPort, UTILISATEUR_CONNECTE);
		if(json.length() == 0) {
			return null;
		}
		
		return (MessageUtilisateurMqttRest)Util.jsonToObjet(json, MessageUtilisateurMqttRest.class);
	}
	
	/**
	 * Demande � valider un identifiant et mot de passe
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
		
		String json = ClientHttpRest.envoiRequeteGET(pHost, pPort, IDENTIFIANTS_VALIDE, params);
		if(json.length() == 0) {
			return false;
		}
		
		return Util.jsonToBoolean(json);
	}

}
