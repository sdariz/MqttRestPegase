package signature.mqttRest.services.rest.serveur.scenario;

import java.util.List;

/**
 * Traitement des requêtes concernant les scénarios
 * 
 * @author SDARIZCUREN
 *
 */
public interface ITraitementRequetesScenario {

	/**
	 * Traite une demande d'obtention de la liste des identifiants des scénarios
	 * 
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'expéditeur : peut être vide
	 * @param pReferenceCommande
	 *            la référence unique de la demande : peut être vide
	 * @return la liste des identifiants des scénarios
	 */
	public List<String> traiteDemandeIdentifiantsScenarios(String pIdentifiantExpediteur, String pReferenceCommande);
}
