package signature.mqttRest.services.rest.serveur.interrogationArmoire;

/**
 * Traitement des requêtes d'interrogation d'une armoire
 * 
 * @author SDARIZCUREN
 *
 */
public interface ITraitementRequetesInterrogationArmoire {

	/**
	 * Traite une demande de lancement de test sur les équipements d'une armoire
	 * 
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'expéditeur : peut être vide
	 * @param pReferenceCommande
	 *            la référence unique de la demande : peut être vide
	 * @param pIdArmoire
	 *            l'identifiant de l'armoire à tester
	 */
	public void traiteDemandeLancementTestEquipements(String pIdentifiantExpediteur, String pReferenceCommande,
			String pIdArmoire);

	/**
	 * Traite une demande de remise à l'heure d'une armoire
	 * 
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'expéditeur : peut être vide
	 * @param pReferenceCommande
	 *            la référence unique de la demande : peut être vide
	 * @param pIdArmoire
	 *            l'identifiant de l'armoire à remettre à l'heure
	 */
	public void traiteDemandeRemiseHeureArmoire(String pIdentifiantExpediteur, String pReferenceCommande,
			String pIdArmoire);

	/**
	 * Traite une demande d'interrogation d'une armoire
	 * 
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'expéditeur : peut être vide
	 * @param pReferenceCommande
	 *            la référence unique de la demande : peut être vide
	 * @param pIdArmoire
	 *            l'identifiant de l'armoire à remettre à l'heure
	 * @param pTrame
	 *            la commande à transmettre à l'armoire
	 * @return la réponse de l'armoire
	 */
	public String traiteDemandeArmoire(String pIdentifiantExpediteur, String pReferenceCommande, String pIdArmoire,
			String pTrame);
}
