package signature.mqttRest.services.rest.serveur;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import signature.mqttRest.objetsPartages.etatEtPilotage.MessageAlarmeMqttRest;
import signature.mqttRest.objetsPartages.etatEtPilotage.MessageEtatAffichageMqttRest;
import signature.mqttRest.objetsPartages.etatEtPilotage.MessageEtatTechniqueMqttRest;
import signature.mqttRest.objetsPartages.etatEtPilotage.MessagePmvMqttRest;
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
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'expéditeur : peut être vide
	 * @param pReferenceCommande
	 *            la référence unique de la demande : peut être vide
	 * @param pIdEquipement
	 *            l'id de l'équipement
	 * @return null
	 */
	@Override
	public MessageEtatAffichageMqttRest demandeEtatAffichageEquipement(String pIdentifiantExpediteur,
			String pReferenceCommande, String pIdEquipement) {
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
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'expéditeur : peut être vide
	 * @param pReferenceCommande
	 *            la référence unique de la demande : peut être vide
	 * @param pIdEquipement
	 *            l'id de l'équipement
	 * @return null
	 */
	@Override
	public MessageEtatTechniqueMqttRest demandeEtatTechniqueEquipement(String pIdentifiantExpediteur,
			String pReferenceCommande, String pIdEquipement) {
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
	public List<MessageEtatTechniqueMqttRest> demandeEtatTechniqueEquipements(String pIdentifiantExpediteur,
			String pReferenceCommande) {
		return new ArrayList<>();
	}

	/**
	 * Traite une demande d'actualisation de l'état d'un équipement, en forçant
	 * une interrogation de l'équipement sur le terrain
	 * 
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'expéditeur : peut être vide
	 * @param pReferenceCommande
	 *            la référence unique de la demande : peut être vide
	 * @param pIdEquipement
	 *            l'id de l'équipement à rafraîchir
	 */
	@Override
	public void demandeActualisationEtatEquipement(String pIdentifiantExpediteur, String pReferenceCommande,
			String pIdEquipement) {
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
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'expéditeur : peut être vide
	 * @param pReferenceCommande
	 *            la référence unique de la demande : peut être vide
	 * @param pLogin
	 *            le login de l'utilisateur
	 * @param pMotPasse
	 *            le mot de passe associé au login
	 * @return false
	 */
	@Override
	public boolean estValide(String pIdentifiantExpediteur, String pReferenceCommande, String pLogin,
			String pMotPasse) {
		return false;
	}

	/**
	 * Traite une demande d'autorisation ou d'interdiction des pilotages sur
	 * Pegase
	 * 
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'expéditeur : peut être vide
	 * @param pReferenceCommande
	 *            la référence unique de la demande : peut être vide
	 * @param pInterdit
	 *            true pour interdire les pilotages sur Pegase
	 */
	@Override
	public void traiteDemandeInterdictionPilotages(String pIdentifiantExpediteur, String pReferenceCommande,
			boolean pInterdit) {
	}

	/**
	 * Traite une demande d'activation ou désactivation d'un bouton
	 * 
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'expéditeur : peut être vide
	 * @param pReferenceCommande
	 *            la référence unique de la demande : peut être vide
	 * @param pIdBouton
	 *            l'identifiant du bouton
	 * @param pActif
	 *            true pour activer, false pour désactiver
	 */
	@Override
	public void traiteDemandeActivationBouton(String pIdentifiantExpediteur, String pReferenceCommande,
			String pIdBouton, boolean pActif) {
	}

	/**
	 * Traite une demande de lancement de l'action rattachée à un bouton
	 * 
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'expéditeur : peut être vide
	 * @param pReferenceCommande
	 *            la référence unique de la demande : peut être vide
	 * @param pIdBouton
	 *            l'identifiant du bouton
	 */
	@Override
	public void traiteDemandeLancementActionBouton(String pIdentifiantExpediteur, String pReferenceCommande,
			String pIdBouton) {
	}

	/**
	 * Traite une demande de lancement de test sur les équipements d'une armoire
	 * 
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'expéditeur : peut être vide
	 * @param pReferenceCommande
	 *            la référence unique de la demande : peut être vide
	 * @param pIdArmoire
	 *            l'identifiant de l'armoire à tester
	 */
	@Override
	public void traiteDemandeLancementTestEquipements(String pIdentifiantExpediteur, String pReferenceCommande,
			String pIdArmoire) {
	}

	/**
	 * Traite une demande de remise à l'heure d'une armoire
	 * 
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'expéditeur : peut être vide
	 * @param pReferenceCommande
	 *            la référence unique de la demande : peut être vide
	 * @param pIdArmoire
	 *            l'identifiant de l'armoire à remettre à l'heure
	 */
	@Override
	public void traiteDemandeRemiseHeureArmoire(String pIdentifiantExpediteur, String pReferenceCommande,
			String pIdArmoire) {
	}

	/**
	 * Traite une demande d'interrogation d'une armoire
	 * 
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'expéditeur : peut être vide
	 * @param pReferenceCommande
	 *            la référence unique de la demande : peut être vide
	 * @param pIdArmoire
	 *            l'identifiant de l'armoire à remettre à l'heure
	 * @param pTrame
	 *            la commande à transmettre à l'armoire
	 * @return la réponse de l'armoire
	 */
	public String traiteDemandeArmoire(String pIdentifiantExpediteur, String pReferenceCommande, String pIdArmoire,
			String pTrame) {
		return "";
	}

	/**
	 * Traite une demande d'obtention de la liste des catégorie de la
	 * bibliothèque
	 * 
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'expéditeur : peut être vide
	 * @param pReferenceCommande
	 *            la référence unique de la demande : peut être vide
	 * @return une liste vide
	 */
	public List<String> traiteDemandeCategoriesBibliothequePmv(String pIdentifiantExpediteur,
			String pReferenceCommande) {
		return new ArrayList<>();
	}

	/**
	 * Traite une demande d'obtention de la liste des noms des messages dans une
	 * catégorie de la bibliothèque
	 * 
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'expéditeur : peut être vide
	 * @param pReferenceCommande
	 *            la référence unique de la demande : peut être vide
	 * @param pCategorie
	 *            la catégorie concernée
	 * @return une liste vide
	 */
	public List<String> traiteDemandeMessagesDansCategorieBibliothequePmv(String pIdentifiantExpediteur,
			String pReferenceCommande, String pCategorie) {
		return new ArrayList<>();
	}

	/**
	 * Traite une demande d'obtention d'un message de la bibliothèque
	 * 
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'expéditeur : peut être vide
	 * @param pReferenceCommande
	 *            la référence unique de la demande : peut être vide
	 * @param pCategorie
	 *            la catégorie concernée
	 * @param pNom
	 *            le nom du message
	 * @return null
	 */
	public MessagePmvMqttRest traiteDemandeMessageBibliothequePmv(String pIdentifiantExpediteur,
			String pReferenceCommande, String pCategorie, String pNom) {
		return null;
	}

	/**
	 * Traite une demande d'obtention de la liste des catégories de la
	 * bibliothèque
	 * 
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'expéditeur : peut être vide
	 * @param pReferenceCommande
	 *            la référence unique de la demande : peut être vide
	 * @param pIdEquipement
	 *            l'id de l'équipement concerné
	 * @param pHorodateDebut
	 *            l'horodate de début de l'alarme
	 * @param pHorodateFin
	 *            l'horodate de fin de l'alarme
	 * @param pActiveSeul
	 *            true pour ne sélectionner que les alarmes actives
	 * @return une liste vide
	 */
	public List<MessageAlarmeMqttRest> traiteDemandeListeAlarmes(String pIdentifiantExpediteur,
			String pReferenceCommande, String pIdEquipement, Date pHorodateDebut, Date pHorodateFin, boolean pActiveSeul) {
		return new ArrayList<>();
	}
}
