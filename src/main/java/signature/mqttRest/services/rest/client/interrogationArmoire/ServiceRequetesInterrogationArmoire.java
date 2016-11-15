package signature.mqttRest.services.rest.client.interrogationArmoire;

import java.util.HashMap;
import java.util.Map;

import signature.mqttRest.services.rest.client.ClientHttpRest;
import signature.mqttRest.services.rest.serveur.interrogationArmoire.GestionnaireRoutesInterrogationArmoire;

/**
 * Requêtes REST pour interroger une armoire : envoi de commande, mise à
 * l'heure, ...
 * 
 * @author SDARIZCUREN
 *
 */
public class ServiceRequetesInterrogationArmoire {

	/**
	 * Envoi au serveur REST une demande de lancement de test sur les
	 * équipements d'une armoire
	 * 
	 * @param pHost
	 *            l'adresse IP du serveur REST
	 * @param pPort
	 *            le port TCP utilisé par le serveur
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'expéditeur : peut être vide
	 * @param pReferenceCommande
	 *            la référence unique de la demande : peut être vide
	 * @param pIdArmoire
	 *            l'identifiant de l'armoire
	 */
	public static void requeteLancementTestEquipementsArmoire(String pHost, int pPort, String pIdentifiantExpediteur,
			String pReferenceCommande, String pIdArmoire) {
		if (pIdentifiantExpediteur == null) {
			pIdentifiantExpediteur = "";
		}

		if (pReferenceCommande == null) {
			pReferenceCommande = "";
		}

		// Paramètre de la requette
		Map<String, String> params = new HashMap<>();
		params.put("idArmoire", pIdArmoire);
		params.put("idExpediteur", pIdentifiantExpediteur);
		params.put("idCommande", pReferenceCommande);

		ClientHttpRest.envoiRequetePOST(pHost, pPort, GestionnaireRoutesInterrogationArmoire.LANCEMENT_TEST_EQUIPEMENTS,
				params);
	}
	
	/**
	 * Envoi au serveur REST une demande de remise à l'heure d'une armoire
	 * 
	 * @param pHost
	 *            l'adresse IP du serveur REST
	 * @param pPort
	 *            le port TCP utilisé par le serveur
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'expéditeur : peut être vide
	 * @param pReferenceCommande
	 *            la référence unique de la demande : peut être vide
	 * @param pIdArmoire
	 *            l'identifiant de l'armoire
	 */
	public static void requeteRemiseHeureArmoire(String pHost, int pPort, String pIdentifiantExpediteur,
			String pReferenceCommande, String pIdArmoire) {
		if (pIdentifiantExpediteur == null) {
			pIdentifiantExpediteur = "";
		}

		if (pReferenceCommande == null) {
			pReferenceCommande = "";
		}

		// Paramètre de la requette
		Map<String, String> params = new HashMap<>();
		params.put("idArmoire", pIdArmoire);
		params.put("idExpediteur", pIdentifiantExpediteur);
		params.put("idCommande", pReferenceCommande);

		ClientHttpRest.envoiRequetePOST(pHost, pPort, GestionnaireRoutesInterrogationArmoire.REMISE_HEURE,
				params);
	}

}
