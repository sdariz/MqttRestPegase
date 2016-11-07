package signature.mqttRest.services.rest.serveur.administration;

/**
 * Traitement des requêtes d'administration de Pegase
 * @author SDARIZCUREN
 *
 */
public interface ITraitementRequetesAdministration {
	
	/**
	 * Traite une demande d'autorisation ou d'interdiction des pilotages sur Pegase
	 * 
	 * @param pInterdit
	 *            true pour interdire les pilotages sur Pegase
	 */
	public void traiteDemandeInterdictionPilotages(boolean pInterdit);

}
