package org.signature.mqttRest.services.rest.serveur.scenario;

import java.util.List;

import org.signature.mqttRest.objetsPartages.scenario.MessageScenarioMqttRest;

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

	/**
	 * Traite une demande d'obtention d'un scénario en particulier
	 * 
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'expéditeur : peut être vide
	 * @param pReferenceCommande
	 *            la référence unique de la demande : peut être vide
	 * @param pIdScenario
	 *            l'identifiant du scénario à récupérer
	 * @return le scénario demandé, ou null si problème
	 */
	public MessageScenarioMqttRest traiteDemandeScenario(String pIdentifiantExpediteur, String pReferenceCommande,
			String pIdScenario);

	/**
	 * Traite une demande d'obtention de tous les scénarios
	 * 
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'expéditeur : peut être vide
	 * @param pReferenceCommande
	 *            la référence unique de la demande : peut être vide
	 * @return la liste des scénarios
	 */
	public List<MessageScenarioMqttRest> traiteDemandeScenarios(String pIdentifiantExpediteur,
			String pReferenceCommande);
}
