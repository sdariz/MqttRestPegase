package org.signature.mqttRest.services.rest.serveur.administration;

import java.util.List;

/**
 * Traitement des requêtes d'administration de Pegase
 * 
 * @author SDARIZCUREN
 *
 */
public interface ITraitementRequetesAdministration {

	/**
	 * Traite une demande d'autorisation ou d'interdiction des pilotages sur Pegase
	 * 
	 * @param pIdentifiantExpediteur l'identifiant unique de l'expéditeur : peut
	 *                               être vide
	 * @param pReferenceCommande     la référence unique de la demande : peut être
	 *                               vide
	 * @param pInterdit              true pour interdire les pilotages sur Pegase
	 */
	public void traiteDemandeInterdictionPilotages(String pIdentifiantExpediteur, String pReferenceCommande,
			boolean pInterdit);

	/**
	 * Traite une demande d'autorisation ou d'interdiction des pilotages sur Pegase,
	 * sur certains équipements
	 * 
	 * @param pIdentifiantExpediteur l'identifiant unique de l'expéditeur : peut
	 *                               être vide
	 * @param pReferenceCommande     la référence unique de la demande : peut être
	 *                               vide
	 * @param pIdsEquipements        les identifiants des équipements concernés
	 * @param pInterdit              true pour interdire les pilotages sur Pegase
	 */
	public void traiteDemandeInterdictionPilotages(String pIdentifiantExpediteur, String pReferenceCommande,
			List<String> pIdsEquipements, boolean pInterdit);

	/**
	 * Traite une demande d'activation ou désactivation d'un bouton
	 * 
	 * @param pIdentifiantExpediteur l'identifiant unique de l'expéditeur : peut
	 *                               être vide
	 * @param pReferenceCommande     la référence unique de la demande : peut être
	 *                               vide
	 * @param pIdBouton              l'identifiant du bouton
	 * @param pActif                 true pour activer, false pour désactiver
	 */
	public void traiteDemandeActivationBouton(String pIdentifiantExpediteur, String pReferenceCommande,
			String pIdBouton, boolean pActif);

	/**
	 * Traite une demande de lancement de l'action rattachée à un bouton
	 * 
	 * @param pIdentifiantExpediteur l'identifiant unique de l'expéditeur : peut
	 *                               être vide
	 * @param pReferenceCommande     la référence unique de la demande : peut être
	 *                               vide
	 * @param pIdBouton              l'identifiant du bouton
	 */
	public void traiteDemandeLancementActionBouton(String pIdentifiantExpediteur, String pReferenceCommande,
			String pIdBouton);

	/**
	 * Forçage de l'arret de l'application hébergeant le serveur REST
	 * 
	 * @param pIdentifiantExpediteur l'identifiant unique de l'expéditeur : peut
	 *                               être vide
	 * @param pReferenceCommande     la référence unique de la demande : peut être
	 *                               vide
	 */
	public void traiteForcageArretApplication(String pIdentifiantExpediteur, String pReferenceCommande);

	/**
	 * Demande la description au format JSON des données de travail sauvegardés. Ce
	 * sont toutes les données exceptés les alarmes et évènements. mAis avec les
	 * images utilisés (synoptiques, pictogrammes, PPAD, ...) au format base64
	 * 
	 * @param pHost                  l'adresse IP du serveur REST
	 * @param pPort                  le port TCP utilisé par le serveur
	 * @param pIdentifiantExpediteur l'identifiant unique de l'expéditeur : peut
	 *                               être vide
	 * @param pReferenceCommande     la référence unique de la demande : peut être
	 *                               vide
	 * @return la description au format JSON
	 */
	public String traiteDemandeDecriptionDonnesTravailSauvegardes(String pIdentifiantExpediteur,
			String pReferenceCommande);

}
