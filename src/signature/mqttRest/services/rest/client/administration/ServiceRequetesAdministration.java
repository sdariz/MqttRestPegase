package signature.mqttRest.services.rest.client.administration;

import java.util.HashMap;
import java.util.Map;

import signature.mqttRest.services.rest.client.ClientHttpRest;
import signature.mqttRest.services.rest.serveur.administration.GestionnaireRoutesAdministration;

/**
 * Services d'envoi de requ�tes vers le serveur REST, et retour de la r�ponse.
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
	 *            le port TCP utilis� par le serveur
	 */
	public static void requetteInterdictionPilotages(String pHost, int pPort, boolean pInterdit) {
		// Param�tre de la requette
		Map<String, String> params = new HashMap<>();
		params.put("interdiction", Boolean.toString(pInterdit));
		ClientHttpRest.envoiRequetePOST(pHost, pPort, GestionnaireRoutesAdministration.INTERDICTION_PILOTAGES, params);
	}

}
