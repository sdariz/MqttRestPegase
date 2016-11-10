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
	 * @param pId
	 *            l'identifiantde l'armoire � tester
	 */
	public void traiteDemandeLancementTestEquipements(String pIdentifiantExpediteur,
			String pReferenceCommande, String pId);
}
