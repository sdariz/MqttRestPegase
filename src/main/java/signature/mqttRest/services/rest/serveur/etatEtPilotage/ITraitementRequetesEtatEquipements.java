package signature.mqttRest.services.rest.serveur.etatEtPilotage;

import java.util.List;

import signature.mqttRest.objetsPartages.etatEtPilotage.MessageEtatAffichageMqttRest;
import signature.mqttRest.objetsPartages.etatEtPilotage.MessageEtatTechniqueMqttRest;

/**
 * Traitement des requ�tes d'�tat des �quipements
 * 
 * @author SDARIZCUREN
 *
 */
public interface ITraitementRequetesEtatEquipements {

	/**
	 * Traite une demande d'�tat d'affichage d'un �quipement
	 * 
	 * @param pId
	 *            l'id de l'�quipement
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'exp�diteur : peut �tre vide
	 * @param pReferenceCommande
	 *            la r�f�rence unique de la demande : peut �tre vide
	 * @return l'�tat d'affichage de l'�quipement
	 */
	public MessageEtatAffichageMqttRest demandeEtatAffichageEquipement(String pId, String pIdentifiantExpediteur,
			String pReferenceCommande);

	/**
	 * Traite une demande d'�tat d'affichage pour tous les �quipements
	 * 
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'exp�diteur : peut �tre vide
	 * @param pReferenceCommande
	 *            la r�f�rence unique de la demande : peut �tre vide
	 * @return l'�tat d'affichage des �quipements
	 */
	public List<MessageEtatAffichageMqttRest> demandeEtatAffichageEquipements(String pIdentifiantExpediteur,
			String pReferenceCommande);

	/**
	 * Traite une demande d'�tat technique d'un �quipement
	 * 
	 * @param pId
	 *            l'id de l'�quipement
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'exp�diteur : peut �tre vide
	 * @param pReferenceCommande
	 *            la r�f�rence unique de la demande : peut �tre vide
	 * @return l'�tat technique de l'�quipement
	 */
	public MessageEtatTechniqueMqttRest demandeEtatTechniqueEquipement(String pId, String pIdentifiantExpediteur,
			String pReferenceCommande);

	/**
	 * Traite une demande d'�tat technique pour tous les �quipements
	 * 
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'exp�diteur : peut �tre vide
	 * @param pReferenceCommande
	 *            la r�f�rence unique de la demande : peut �tre vide
	 * @return l'�tat technique des �quipements
	 */
	public List<MessageEtatTechniqueMqttRest> demandeEtatTechniqueEquipements(String pIdentifiantExpediteur,
			String pReferenceCommande);

	/**
	 * Traite une demande d'actualisation de l'�tat d'un �quipement, en for�ant
	 * une interrogation de l'�quipement sur le terrain
	 * 
	 * @param pId
	 *            l'id de l'�quipement � rafra�chir
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'exp�diteur : peut �tre vide
	 * @param pReferenceCommande
	 *            la r�f�rence unique de la demande : peut �tre vide
	 */
	public void demandeActualisationEtatEquipement(String pId, String pIdentifiantExpediteur,
			String pReferenceCommande);
}
