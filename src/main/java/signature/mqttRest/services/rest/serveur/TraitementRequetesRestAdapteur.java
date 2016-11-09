package signature.mqttRest.services.rest.serveur;

import java.util.ArrayList;
import java.util.List;

import signature.mqttRest.objetsPartages.etatEtPilotage.MessageEtatAffichageMqttRest;
import signature.mqttRest.objetsPartages.etatEtPilotage.MessageEtatTechniqueMqttRest;
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
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'expéditeur : peut être vide
	 * @param pReferenceCommande
	 *            la référence unique de la demande : peut être vide
	 * @return null
	 */
	@Override
	public MessageEtatAffichageMqttRest demandeEtatAffichageEquipement(String pId, String pIdentifiantExpediteur,
			String pReferenceCommande) {
		return null;
	}

	/**
	 * Traite une demande d'état d'affichage pour tous les équipements
	 * 
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'expéditeur : peut être vide
	 * @param pReferenceCommande
	 *            la référence unique de la demande : peut être vide
	 * 
	 * @return une liste vide
	 */
	@Override
	public List<MessageEtatAffichageMqttRest> demandeEtatAffichageEquipements(String pIdentifiantExpediteur,
			String pReferenceCommande) {
		return new ArrayList<>();
	}

	/**
	 * Traite une demande d'état technique d'un équipement
	 * 
	 * @param pId
	 *            l'id de l'équipement
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'expéditeur : peut être vide
	 * @param pReferenceCommande
	 *            la référence unique de la demande : peut être vide
	 * @return null
	 */
	@Override
	public MessageEtatTechniqueMqttRest demandeEtatTechniqueEquipement(String pId, String pIdentifiantExpediteur,
			String pReferenceCommande) {
		return null;
	}

	/**
	 * Traite une demande d'état technique pour tous les équipements
	 * 
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'expéditeur : peut être vide
	 * @param pReferenceCommande
	 *            la référence unique de la demande : peut être vide
	 * 
	 * @return une liste vide
	 */
	@Override
	public List<MessageEtatTechniqueMqttRest> demandeEtatTechniqueEquipements(String pIdentifiantExpediteur, String pReferenceCommande) {
		return new ArrayList<>();
	}

	/**
	 * Traite une demande d'actualisation de l'état d'un équipement, en forçant
	 * une interrogation de l'équipement sur le terrain
	 * 
	 * @param pId
	 *            l'id de l'équipement à rafraîchir
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'expéditeur : peut être vide
	 * @param pReferenceCommande
	 *            la référence unique de la demande : peut être vide
	 */
	@Override
	public void demandeActualisationEtatEquipement(String pId, String pIdentifiantExpediteur,
			String pReferenceCommande) {
	}

	/**
	 * Traite une demande d'obtention de la liste des utilisateurs
	 * 
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'expéditeur : peut être vide
	 * @param pReferenceCommande
	 *            la référence unique de la demande : peut être vide
	 * 
	 * @return une liste vide
	 */
	@Override
	public List<MessageUtilisateurMqttRest> demandeListeUtilisateurs(String pIdentifiantExpediteur,
			String pReferenceCommande) {
		return new ArrayList<>();
	}

	/**
	 * Traite une demande d'obtention de l'utilisateur connecté
	 * 
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'expéditeur : peut être vide
	 * @param pReferenceCommande
	 *            la référence unique de la demande : peut être vide
	 * 
	 * @return null
	 */
	@Override
	public MessageUtilisateurMqttRest demandeUtilisateurConnecte(String pIdentifiantExpediteur,
			String pReferenceCommande) {
		return null;
	}

	/**
	 * Indique si le login et le mot de passe associé sont valides
	 * 
	 * @param pLogin
	 *            le login de l'utilisateur
	 * @param pMotPasse
	 *            le mot de passe associé au login
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'expéditeur : peut être vide
	 * @param pReferenceCommande
	 *            la référence unique de la demande : peut être vide
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
	 *            l'identifiant unique de l'expéditeur : peut être vide
	 * @param pReferenceCommande
	 *            la référence unique de la demande : peut être vide
	 */
	@Override
	public void traiteDemandeInterdictionPilotages(boolean pInterdit, String pIdentifiantExpediteur,
			String pReferenceCommande) {
	}
}
