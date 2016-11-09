package signature.mqttRest.services.rest.serveur;

import java.util.ArrayList;
import java.util.List;

import signature.mqttRest.objetsPartages.etatEtPilotage.MessageEtatAffichageMqttRest;
import signature.mqttRest.objetsPartages.etatEtPilotage.MessageEtatTechniqueMqttRest;
import signature.mqttRest.objetsPartages.utilisateur.MessageUtilisateurMqttRest;

/**
 * Une classe bouchon, � �tendre pour impl�menter les m�thodes n�cessaires
 * 
 * @author SDARIZCUREN
 *
 */
public class TraitementRequetesRestAdapteur implements ITraitementRequetesRest {

	/**
	 * Traite une demande d'�tat d'affichage d'un �quipement
	 * 
	 * @param pId
	 *            l'id de l'�quipement
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'exp�diteur : peut �tre vide
	 * @param pReferenceCommande
	 *            la r�f�rence unique de la demande : peut �tre vide
	 * @return null
	 */
	@Override
	public MessageEtatAffichageMqttRest demandeEtatAffichageEquipement(String pId, String pIdentifiantExpediteur,
			String pReferenceCommande) {
		return null;
	}

	/**
	 * Traite une demande d'�tat d'affichage pour tous les �quipements
	 * 
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'exp�diteur : peut �tre vide
	 * @param pReferenceCommande
	 *            la r�f�rence unique de la demande : peut �tre vide
	 * 
	 * @return une liste vide
	 */
	@Override
	public List<MessageEtatAffichageMqttRest> demandeEtatAffichageEquipements(String pIdentifiantExpediteur,
			String pReferenceCommande) {
		return new ArrayList<>();
	}

	/**
	 * Traite une demande d'�tat technique d'un �quipement
	 * 
	 * @param pId
	 *            l'id de l'�quipement
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'exp�diteur : peut �tre vide
	 * @param pReferenceCommande
	 *            la r�f�rence unique de la demande : peut �tre vide
	 * @return null
	 */
	@Override
	public MessageEtatTechniqueMqttRest demandeEtatTechniqueEquipement(String pId, String pIdentifiantExpediteur,
			String pReferenceCommande) {
		return null;
	}

	/**
	 * Traite une demande d'�tat technique pour tous les �quipements
	 * 
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'exp�diteur : peut �tre vide
	 * @param pReferenceCommande
	 *            la r�f�rence unique de la demande : peut �tre vide
	 * 
	 * @return une liste vide
	 */
	@Override
	public List<MessageEtatTechniqueMqttRest> demandeEtatTechniqueEquipements(String pIdentifiantExpediteur, String pReferenceCommande) {
		return new ArrayList<>();
	}

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
	@Override
	public void demandeActualisationEtatEquipement(String pId, String pIdentifiantExpediteur,
			String pReferenceCommande) {
	}

	/**
	 * Traite une demande d'obtention de la liste des utilisateurs
	 * 
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'exp�diteur : peut �tre vide
	 * @param pReferenceCommande
	 *            la r�f�rence unique de la demande : peut �tre vide
	 * 
	 * @return une liste vide
	 */
	@Override
	public List<MessageUtilisateurMqttRest> demandeListeUtilisateurs(String pIdentifiantExpediteur,
			String pReferenceCommande) {
		return new ArrayList<>();
	}

	/**
	 * Traite une demande d'obtention de l'utilisateur connect�
	 * 
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'exp�diteur : peut �tre vide
	 * @param pReferenceCommande
	 *            la r�f�rence unique de la demande : peut �tre vide
	 * 
	 * @return null
	 */
	@Override
	public MessageUtilisateurMqttRest demandeUtilisateurConnecte(String pIdentifiantExpediteur,
			String pReferenceCommande) {
		return null;
	}

	/**
	 * Indique si le login et le mot de passe associ� sont valides
	 * 
	 * @param pLogin
	 *            le login de l'utilisateur
	 * @param pMotPasse
	 *            le mot de passe associ� au login
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'exp�diteur : peut �tre vide
	 * @param pReferenceCommande
	 *            la r�f�rence unique de la demande : peut �tre vide
	 * @return false
	 */
	@Override
	public boolean estValide(String pLogin, String pMotPasse, String pIdentifiantExpediteur,
			String pReferenceCommande) {
		return false;
	}

	/**
	 * Traite une demande d'autorisation ou d'interdiction des pilotages sur
	 * Pegase
	 * 
	 * @param pInterdit
	 *            true pour interdire les pilotages sur Pegase
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'exp�diteur : peut �tre vide
	 * @param pReferenceCommande
	 *            la r�f�rence unique de la demande : peut �tre vide
	 */
	@Override
	public void traiteDemandeInterdictionPilotages(boolean pInterdit, String pIdentifiantExpediteur,
			String pReferenceCommande) {
	}
}
