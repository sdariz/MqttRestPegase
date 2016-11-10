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
	 * @param pId
	 *            l'identifiantde l'armoire à tester
	 */
	public void traiteDemandeLancementTestEquipements(String pIdentifiantExpediteur,
			String pReferenceCommande, String pId);
}
