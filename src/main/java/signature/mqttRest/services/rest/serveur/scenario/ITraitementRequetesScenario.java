package signature.mqttRest.services.rest.serveur.scenario;

import java.util.List;

/**
 * Traitement des requ�tes concernant les sc�narios
 * 
 * @author SDARIZCUREN
 *
 */
public interface ITraitementRequetesScenario {

	/**
	 * Traite une demande d'obtention de la liste des identifiants des sc�narios
	 * 
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'exp�diteur : peut �tre vide
	 * @param pReferenceCommande
	 *            la r�f�rence unique de la demande : peut �tre vide
	 * @return la liste des identifiants des sc�narios
	 */
	public List<String> traiteDemandeIdentifiantsScenarios(String pIdentifiantExpediteur, String pReferenceCommande);
}
