package signature.mqttRest.services.rest.serveur;

import java.util.ArrayList;
import java.util.List;

import signature.mqttRest.objetsPartages.etatEtPilotage.MessageEtatAffichageMqttRest;
import signature.mqttRest.objetsPartages.utilisateur.MessageUtilisateurMqttRest;

/**
 * Une classe bouchon, à étendre pour implémenter les méthodes nécessaires
 * 
 * @author SDARIZCUREN
 *
 */
public class TraitementRequetesRestAdapteur implements ITraitementRequetesRest {
	/**
	 * Traite une demande d'état d'affichage d'un équipement
	 * 
	 * @param pId
	 *            l'id de l'équipement
	 * @return null
	 */
	public MessageEtatAffichageMqttRest demandeEtatAffichageEquipement(String pId) {
		return null;
	}

	/**
	 * Traite une demande d'état d'affichage pour tous les équipements
	 * 
	 * @return une liste vide
	 */
	public List<MessageEtatAffichageMqttRest> demandeEtatAffichageEquipements() {
		return new ArrayList<>();
	}

	/**
	 * Traite une demande d'état technique d'un équipement
	 * 
	 * @param pId
	 *            l'id de l'équipement
	 * @return une chaîne vide
	 */
	public String demandeEtatTechniqueEquipement(String pId) {
		return "";
	}

	/**
	 * Traite une demande d'état technique pour tous les équipements
	 * 
	 * @return une chaîne vide
	 */
	public String demandeEtatTechniqueEquipements() {
		return "";
	}

	/**
	 * Traite une demande d'actualisation de l'état d'un équipement, en forçant
	 * une interrogation de l'équipement sur le terrain
	 * 
	 * @param pId
	 *            l'id de l'équipement à rafraîchir
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
	 * Traite une demande d'obtention de l'utilisateur connecté
	 * 
	 * @return null
	 */
	public MessageUtilisateurMqttRest demandeUtilisateurConnecte() {
		return null;
	}

	/**
	 * Indique si le login et le mot de passe associé sont valides
	 * 
	 * @param pLogin
	 *            le login de l'utilisateur
	 * @param pMotPasse
	 *            le mot de passe associé au login
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
