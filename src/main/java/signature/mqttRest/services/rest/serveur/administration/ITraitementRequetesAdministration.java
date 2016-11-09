package signature.mqttRest.services.rest.serveur.administration;

/**
 * Traitement des requêtes d'administration de Pegase
 * 
 * @author SDARIZCUREN
 *
 */
public interface ITraitementRequetesAdministration {

	/**
	 * Traite une demande d'autorisation ou d'interdiction des pilotages sur
	 * Pegase
	 * 
	 * @param pInterdit
	 *            true pour interdire les pilotages sur Pegase
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'expéditeur : peut être vide
	 * @param pReferenceCommande
	 *            la référence unique de la demande : peut être vide
	 */
	public void traiteDemandeInterdictionPilotages(boolean pInterdit, String pIdentifiantExpediteur,
			String pReferenceCommande);

	/**
	 * Traite une demande d'activation ou désactivation d'un bouton
	 * 
	 * @param pIdBouton
	 *            l'identifiant du bouton
	 * @param pActif
	 *            true pour activer, false pour désactiver
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'expéditeur : peut être vide
	 * @param pReferenceCommande
	 *            la référence unique de la demande : peut être vide
	 */
	public void traiteDemandeActivationBouton(String pIdBouton, boolean pActif, String pIdentifiantExpediteur,
			String pReferenceCommande);

	/**
	 * Traite une demande de lancement de l'action rattachée à un bouton
	 * 
	 * @param pIdBouton
	 *            l'identifiant du bouton
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'expéditeur : peut être vide
	 * @param pReferenceCommande
	 *            la référence unique de la demande : peut être vide
	 */
	public void traiteDemandeLancementActionBouton(String pIdBouton, String pIdentifiantExpediteur,
			String pReferenceCommande);

}
