package signature.mqttRest.services.rest.serveur.administration;

/**
 * Traitement des requ�tes d'administration de Pegase
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
	 *            l'identifiant unique de l'exp�diteur : peut �tre vide
	 * @param pReferenceCommande
	 *            la r�f�rence unique de la demande : peut �tre vide
	 */
	public void traiteDemandeInterdictionPilotages(boolean pInterdit, String pIdentifiantExpediteur,
			String pReferenceCommande);

	/**
	 * Traite une demande d'activation ou d�sactivation d'un bouton
	 * 
	 * @param pIdBouton
	 *            l'identifiant du bouton
	 * @param pActif
	 *            true pour activer, false pour d�sactiver
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'exp�diteur : peut �tre vide
	 * @param pReferenceCommande
	 *            la r�f�rence unique de la demande : peut �tre vide
	 */
	public void traiteDemandeActivationBouton(String pIdBouton, boolean pActif, String pIdentifiantExpediteur,
			String pReferenceCommande);

	/**
	 * Traite une demande de lancement de l'action rattach�e � un bouton
	 * 
	 * @param pIdBouton
	 *            l'identifiant du bouton
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'exp�diteur : peut �tre vide
	 * @param pReferenceCommande
	 *            la r�f�rence unique de la demande : peut �tre vide
	 */
	public void traiteDemandeLancementActionBouton(String pIdBouton, String pIdentifiantExpediteur,
			String pReferenceCommande);

}
