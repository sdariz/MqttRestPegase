package org.signature.mqttRest.services.rest.serveur.scenario;

import java.util.List;

import org.signature.mqttRest.objetsPartages.scenario.MessageScenarioMqttRest;

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

	/**
	 * Traite une demande d'obtention d'un sc�nario en particulier
	 * 
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'exp�diteur : peut �tre vide
	 * @param pReferenceCommande
	 *            la r�f�rence unique de la demande : peut �tre vide
	 * @param pIdScenario
	 *            l'identifiant du sc�nario � r�cup�rer
	 * @return le sc�nario demand�, ou null si probl�me
	 */
	public MessageScenarioMqttRest traiteDemandeScenario(String pIdentifiantExpediteur, String pReferenceCommande,
			String pIdScenario);

	/**
	 * Traite une demande d'obtention de tous les sc�narios
	 * 
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'exp�diteur : peut �tre vide
	 * @param pReferenceCommande
	 *            la r�f�rence unique de la demande : peut �tre vide
	 * @return la liste des sc�narios
	 */
	public List<MessageScenarioMqttRest> traiteDemandeScenarios(String pIdentifiantExpediteur,
			String pReferenceCommande);
}
