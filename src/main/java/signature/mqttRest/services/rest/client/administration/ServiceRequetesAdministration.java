package signature.mqttRest.services.rest.client.administration;

import java.util.HashMap;
import java.util.Map;

import signature.mqttRest.services.rest.client.ClientHttpRest;
import signature.mqttRest.services.rest.serveur.administration.GestionnaireRoutesAdministration;

/**
 * Services d'envoi de requêtes vers le serveur REST, et retour de la réponse.
 * 
 * @author SDARIZCUREN
 *
 */
public class ServiceRequetesAdministration {

	/**
	 * Envoi au serveur REST une interdiction ou autorisation de pilotage
	 * 
	 * @param pHost
	 *            l'adresse IP du serveur REST
	 * @param pPort
	 *            le port TCP utilisé par le serveur
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'expéditeur : peut être vide
	 * @param pReferenceCommande
	 *            la référence unique de la demande : peut être vide
	 */
	public static void requeteInterdictionPilotages(String pHost, int pPort, boolean pInterdit,
			String pIdentifiantExpediteur, String pReferenceCommande) {
		if(pIdentifiantExpediteur == null) {
			pIdentifiantExpediteur = "";
		}
		
		if(pReferenceCommande == null) {
			pReferenceCommande = "";
		}
		
		// Paramètre de la requette
		Map<String, String> params = new HashMap<>();
		params.put("interdiction", Boolean.toString(pInterdit));
		params.put("idExpediteur", pIdentifiantExpediteur);
		params.put("idCommande", pReferenceCommande);
		
		ClientHttpRest.envoiRequetePOST(pHost, pPort, GestionnaireRoutesAdministration.INTERDICTION_PILOTAGES, params);
	}

}
