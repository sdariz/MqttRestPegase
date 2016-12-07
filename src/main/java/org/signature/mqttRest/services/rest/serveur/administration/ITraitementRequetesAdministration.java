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
	 * Traite une demande d'autorisation ou d'interdiction des pilotages sur
	 * Pegase
	 * 
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'exp�diteur : peut �tre vide
	 * @param pReferenceCommande
	 *            la r�f�rence unique de la demande : peut �tre vide
	 * @param pInterdit
	 *            true pour interdire les pilotages sur Pegase
	 */
	public void traiteDemandeInterdictionPilotages(String pIdentifiantExpediteur, String pReferenceCommande,
			boolean pInterdit);

	/**
	 * Traite une demande d'autorisation ou d'interdiction des pilotages sur
	 * Pegase, sur certains �quipements
	 * 
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'exp�diteur : peut �tre vide
	 * @param pReferenceCommande
	 *            la r�f�rence unique de la demande : peut �tre vide
	 * @param pIdsEquipements
	 *            les identifiants des �quipements concern�s
	 * @param pInterdit
	 *            true pour interdire les pilotages sur Pegase
	 */
	public void traiteDemandeInterdictionPilotages(String pIdentifiantExpediteur, String pReferenceCommande,
			List<String> pIdsEquipements, boolean pInterdit);

	/**
	 * Traite une demande d'activation ou d�sactivation d'un bouton
	 * 
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'exp�diteur : peut �tre vide
	 * @param pReferenceCommande
	 *            la r�f�rence unique de la demande : peut �tre vide
	 * @param pIdBouton
	 *            l'identifiant du bouton
	 * @param pActif
	 *            true pour activer, false pour d�sactiver
	 */
	public void traiteDemandeActivationBouton(String pIdentifiantExpediteur, String pReferenceCommande,
			String pIdBouton, boolean pActif);

	/**
	 * Traite une demande de lancement de l'action rattach�e � un bouton
	 * 
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'exp�diteur : peut �tre vide
	 * @param pReferenceCommande
	 *            la r�f�rence unique de la demande : peut �tre vide
	 * @param pIdBouton
	 *            l'identifiant du bouton
	 */
	public void traiteDemandeLancementActionBouton(String pIdentifiantExpediteur, String pReferenceCommande,
			String pIdBouton);

}
