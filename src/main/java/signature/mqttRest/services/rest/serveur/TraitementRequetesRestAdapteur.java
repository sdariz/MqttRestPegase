package signature.mqttRest.services.rest.serveur;

import java.util.ArrayList;
import java.util.List;

import signature.mqttRest.objetsPartages.etatEtPilotage.MessageEtatAffichageMqttRest;
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
	 * @return null
	 */
	public MessageEtatAffichageMqttRest demandeEtatAffichageEquipement(String pId) {
		return null;
	}

	/**
	 * Traite une demande d'�tat d'affichage pour tous les �quipements
	 * 
	 * @return une liste vide
	 */
	public List<MessageEtatAffichageMqttRest> demandeEtatAffichageEquipements() {
		return new ArrayList<>();
	}

	/**
	 * Traite une demande d'�tat technique d'un �quipement
	 * 
	 * @param pId
	 *            l'id de l'�quipement
	 * @return une cha�ne vide
	 */
	public String demandeEtatTechniqueEquipement(String pId) {
		return "";
	}

	/**
	 * Traite une demande d'�tat technique pour tous les �quipements
	 * 
	 * @return une cha�ne vide
	 */
	public String demandeEtatTechniqueEquipements() {
		return "";
	}

	/**
	 * Traite une demande d'actualisation de l'�tat d'un �quipement, en for�ant
	 * une interrogation de l'�quipement sur le terrain
	 * 
	 * @param pId
	 *            l'id de l'�quipement � rafra�chir
	 */
	public void demandeActualisationEtatEquipement(String pId) {
	}
	
	/**
	 * Traite une demande d'obtention de la liste des utilisateurs
	 * 
	 * @return une liste vide
	 */
	public List<MessageUtilisateurMqttRest> demandeListeUtilisateurs() {
		return new ArrayList<>();
	}

	/**
	 * Traite une demande d'obtention de l'utilisateur connect�
	 * 
	 * @return null
	 */
	public MessageUtilisateurMqttRest demandeUtilisateurConnecte() {
		return null;
	}

	/**
	 * Indique si le login et le mot de passe associ� sont valides
	 * 
	 * @param pLogin
	 *            le login de l'utilisateur
	 * @param pMotPasse
	 *            le mot de passe associ� au login
	 * @return false
	 */
	public boolean estValide(String pLogin, String pMotPasse) {
		return false;
	}
	
	/**
	 * Traite une demande d'autorisation ou d'interdiction des pilotages sur Pegase
	 * 
	 * @param pInterdit
	 *            true pour interdire les pilotages sur Pegase
	 */
	public void traiteDemandeInterdictionPilotages(boolean pInterdit) {
	}
}
