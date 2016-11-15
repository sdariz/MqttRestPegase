package signature.mqttRest.services.rest.serveur.interrogationArmoire;

/**
 * Traitement des requ�tes d'interrogation d'une armoire
 * 
 * @author SDARIZCUREN
 *
 */
public interface ITraitementRequetesInterrogationArmoire {

	/**
	 * Traite une demande de lancement de test sur les �quipements d'une armoire
	 * 
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'exp�diteur : peut �tre vide
	 * @param pReferenceCommande
	 *            la r�f�rence unique de la demande : peut �tre vide
	 * @param pIdArmoire
	 *            l'identifiant de l'armoire � tester
	 */
	public void traiteDemandeLancementTestEquipements(String pIdentifiantExpediteur, String pReferenceCommande,
			String pIdArmoire);

	/**
	 * Traite une demande de remise � l'heure d'une armoire
	 * 
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'exp�diteur : peut �tre vide
	 * @param pReferenceCommande
	 *            la r�f�rence unique de la demande : peut �tre vide
	 * @param pIdArmoire
	 *            l'identifiant de l'armoire � remettre � l'heure
	 */
	public void traiteDemandeRemiseHeureArmoire(String pIdentifiantExpediteur, String pReferenceCommande,
			String pIdArmoire);

	/**
	 * Traite une demande d'interrogation d'une armoire
	 * 
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'exp�diteur : peut �tre vide
	 * @param pReferenceCommande
	 *            la r�f�rence unique de la demande : peut �tre vide
	 * @param pIdArmoire
	 *            l'identifiant de l'armoire � remettre � l'heure
	 * @param pTrame
	 *            la commande � transmettre � l'armoire
	 * @return la r�ponse de l'armoire
	 */
	public String traiteDemandeArmoire(String pIdentifiantExpediteur, String pReferenceCommande, String pIdArmoire,
			String pTrame);
}
