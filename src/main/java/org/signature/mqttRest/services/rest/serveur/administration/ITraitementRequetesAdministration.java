package org.signature.mqttRest.services.rest.serveur.administration;

import java.util.List;

/**
 * Traitement des requ�tes d'administration de Pegase
 * 
 * @author SDARIZCUREN
 *
 */
public interface ITraitementRequetesAdministration {

	/**
	 * Traite une demande d'autorisation ou d'interdiction des pilotages sur Pegase
	 * 
	 * @param pIdentifiantExpediteur l'identifiant unique de l'exp�diteur : peut
	 *                               �tre vide
	 * @param pReferenceCommande     la r�f�rence unique de la demande : peut �tre
	 *                               vide
	 * @param pInterdit              true pour interdire les pilotages sur Pegase
	 */
	public void traiteDemandeInterdictionPilotages(String pIdentifiantExpediteur, String pReferenceCommande,
			boolean pInterdit);

	/**
	 * Traite une demande d'autorisation ou d'interdiction des pilotages sur Pegase,
	 * sur certains �quipements
	 * 
	 * @param pIdentifiantExpediteur l'identifiant unique de l'exp�diteur : peut
	 *                               �tre vide
	 * @param pReferenceCommande     la r�f�rence unique de la demande : peut �tre
	 *                               vide
	 * @param pIdsEquipements        les identifiants des �quipements concern�s
	 * @param pInterdit              true pour interdire les pilotages sur Pegase
	 */
	public void traiteDemandeInterdictionPilotages(String pIdentifiantExpediteur, String pReferenceCommande,
			List<String> pIdsEquipements, boolean pInterdit);

	/**
	 * Traite une demande d'activation ou d�sactivation d'un bouton
	 * 
	 * @param pIdentifiantExpediteur l'identifiant unique de l'exp�diteur : peut
	 *                               �tre vide
	 * @param pReferenceCommande     la r�f�rence unique de la demande : peut �tre
	 *                               vide
	 * @param pIdBouton              l'identifiant du bouton
	 * @param pActif                 true pour activer, false pour d�sactiver
	 */
	public void traiteDemandeActivationBouton(String pIdentifiantExpediteur, String pReferenceCommande,
			String pIdBouton, boolean pActif);

	/**
	 * Traite une demande de lancement de l'action rattach�e � un bouton
	 * 
	 * @param pIdentifiantExpediteur l'identifiant unique de l'exp�diteur : peut
	 *                               �tre vide
	 * @param pReferenceCommande     la r�f�rence unique de la demande : peut �tre
	 *                               vide
	 * @param pIdBouton              l'identifiant du bouton
	 */
	public void traiteDemandeLancementActionBouton(String pIdentifiantExpediteur, String pReferenceCommande,
			String pIdBouton);

	/**
	 * For�age de l'arret de l'application h�bergeant le serveur REST
	 * 
	 * @param pIdentifiantExpediteur l'identifiant unique de l'exp�diteur : peut
	 *                               �tre vide
	 * @param pReferenceCommande     la r�f�rence unique de la demande : peut �tre
	 *                               vide
	 */
	public void traiteForcageArretApplication(String pIdentifiantExpediteur, String pReferenceCommande);

	/**
	 * Demande la description au format JSON des donn�es de travail sauvegard�s. Ce
	 * sont toutes les donn�es except�s les alarmes et �v�nements. mAis avec les
	 * images utilis�s (synoptiques, pictogrammes, PPAD, ...) au format base64
	 * 
	 * @param pHost                  l'adresse IP du serveur REST
	 * @param pPort                  le port TCP utilis� par le serveur
	 * @param pIdentifiantExpediteur l'identifiant unique de l'exp�diteur : peut
	 *                               �tre vide
	 * @param pReferenceCommande     la r�f�rence unique de la demande : peut �tre
	 *                               vide
	 * @return la description au format JSON
	 */
	public String traiteDemandeDecriptionDonnesTravailSauvegardes(String pIdentifiantExpediteur,
			String pReferenceCommande);

}
