package signature.mqttRest.services.rest.serveur;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import signature.mqttRest.objetsPartages.etatEtPilotage.IMessageAffichageEquipementMqttRest;
import signature.mqttRest.objetsPartages.etatEtPilotage.MessageBarriereMqttRest;
import signature.mqttRest.objetsPartages.etatEtPilotage.MessageBraMqttRest;
import signature.mqttRest.objetsPartages.etatEtPilotage.MessageEtatAffichageMqttRest;
import signature.mqttRest.objetsPartages.etatEtPilotage.MessageEtatTechniqueMqttRest;
import signature.mqttRest.objetsPartages.etatEtPilotage.MessagePictogrammeMqttRest;
import signature.mqttRest.objetsPartages.etatEtPilotage.MessagePmvMqttRest;
import signature.mqttRest.objetsPartages.etatEtPilotage.MessagePpadMqttRest;
import signature.mqttRest.objetsPartages.etatEtPilotage.MessagePplmvMqttRest;
import signature.mqttRest.objetsPartages.etatEtPilotage.MessagePrismeMqttRest;
import signature.mqttRest.objetsPartages.etatEtPilotage.MessageR24MqttRest;
import signature.mqttRest.objetsPartages.evenement.MessageAlarmeMqttRest;
import signature.mqttRest.objetsPartages.informationPegase.MessageProprietesArmoireMqttRest;
import signature.mqttRest.objetsPartages.informationPegase.MessageProprietesEquipementMqttRest;
import signature.mqttRest.objetsPartages.scenario.MessageScenarioMqttRest;
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
	 * Traite une demande d'autorisation ou d'interdiction des pilotages sur
	 * Pegase, sur certains équipements
	 * 
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'expéditeur : peut être vide
	 * @param pReferenceCommande
	 *            la référence unique de la demande : peut être vide
	 * @param pIdsEquipements
	 *            les identifiants des équipements concernés
	 * @param pInterdit
	 *            true pour interdire les pilotages sur Pegase
	 */
	@Override
	public void traiteDemandeInterdictionPilotages(String pIdentifiantExpediteur, String pReferenceCommande,
			List<String> pIdsEquipements, boolean pInterdit) {
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
	@Override
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
	@Override
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
	@Override
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
	@Override
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
	@Override
	public List<MessageAlarmeMqttRest> traiteDemandeListeAlarmes(String pIdentifiantExpediteur,
			String pReferenceCommande, String pIdEquipement, Date pHorodateDebut, Date pHorodateFin,
			boolean pActiveSeul) {
		return new ArrayList<>();
	}

	/**
	 * Traite une demande d'état d'un équipement à une certaine date
	 * 
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'expéditeur : peut être vide
	 * @param pReferenceCommande
	 *            la référence unique de la demande : peut être vide
	 * @param pIdEquipement
	 *            l'id de l'équipement concerné
	 * @param pHorodate
	 *            l'horodate de l'évènement
	 * @return null
	 */
	@Override
	public IMessageAffichageEquipementMqttRest traiteDemandeEtatAffichageEquipementPourDate(
			String pIdentifiantExpediteur, String pReferenceCommande, String pIdEquipement, Date pHorodate) {
		return null;
	}

	/**
	 * Traite une demande d'état d'un équipement entre deux dates
	 * 
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'expéditeur : peut être vide
	 * @param pReferenceCommande
	 *            la référence unique de la demande : peut être vide
	 * @param pIdEquipement
	 *            l'id de l'équipement concerné
	 * @param pHorodateDebut
	 *            l'horodate de début de l'évènement
	 * @param pHorodateFin
	 *            l'horodate de fin de l'évènement
	 * @return une liste vide
	 */
	@Override
	public List<IMessageAffichageEquipementMqttRest> traiteDemandeEtatAffichageEquipementEntreDeuxDates(
			String pIdentifiantExpediteur, String pReferenceCommande, String pIdEquipement, Date pHorodateDebut,
			Date pHorodateFin) {
		return new ArrayList<>();
	}

	/**
	 * Traite une demande d'obtention des propriétés d'un équipement
	 * 
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'expéditeur : peut être vide
	 * @param pReferenceCommande
	 *            la référence unique de la demande : peut être vide
	 * @param pIdEquipement
	 *            l'id de l'équipement concerné
	 * @return null
	 */
	@Override
	public MessageProprietesEquipementMqttRest traiteDemandeProprietesEquipement(String pIdentifiantExpediteur,
			String pReferenceCommande, String pIdEquipement) {
		return null;
	}

	/**
	 * Traite une demande d'obtention des propriétés des équipements
	 * 
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'expéditeur : peut être vide
	 * @param pReferenceCommande
	 *            la référence unique de la demande : peut être vide
	 * @return une liste vide
	 */
	@Override
	public List<MessageProprietesEquipementMqttRest> traiteDemandeProprietesEquipements(String pIdentifiantExpediteur,
			String pReferenceCommande) {
		return new ArrayList<>();
	}

	/**
	 * Traite une demande d'obtention des propriétés d'une armoire
	 * 
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'expéditeur : peut être vide
	 * @param pReferenceCommande
	 *            la référence unique de la demande : peut être vide
	 * @param pIdArmoire
	 *            l'id de l'armoire concernée
	 * @return null
	 */
	@Override
	public MessageProprietesArmoireMqttRest traiteDemandeProprietesArmoire(String pIdentifiantExpediteur,
			String pReferenceCommande, String pIdArmoire) {
		return null;
	}

	/**
	 * Traite une demande d'obtention des propriétés des armoires
	 * 
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'expéditeur : peut être vide
	 * @param pReferenceCommande
	 *            la référence unique de la demande : peut être vide
	 * @return une liste vide
	 */
	@Override
	public List<MessageProprietesArmoireMqttRest> traiteDemandeProprietesArmoires(String pIdentifiantExpediteur,
			String pReferenceCommande) {
		return new ArrayList<>();
	}

	/**
	 * Traite une demande d'obtention de la liste des identifiants des scénarios
	 * 
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'expéditeur : peut être vide
	 * @param pReferenceCommande
	 *            la référence unique de la demande : peut être vide
	 * @return une liste vide
	 */
	@Override
	public List<String> traiteDemandeIdentifiantsScenarios(String pIdentifiantExpediteur, String pReferenceCommande) {
		return new ArrayList<>();
	}

	/**
	 * Traite une demande d'obtention d'un scénario en particulier
	 * 
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'expéditeur : peut être vide
	 * @param pReferenceCommande
	 *            la référence unique de la demande : peut être vide
	 * @param pIdScenario
	 *            l'identifiant du scénario à récupérer
	 * @return null
	 */
	@Override
	public MessageScenarioMqttRest traiteDemandeScenario(String pIdentifiantExpediteur, String pReferenceCommande,
			String pIdScenario) {
		return null;
	}

	/**
	 * Traite une demande d'obtention de tous les scénarios
	 * 
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'expéditeur : peut être vide
	 * @param pReferenceCommande
	 *            la référence unique de la demande : peut être vide
	 * @return une liste vide
	 */
	@Override
	public List<MessageScenarioMqttRest> traiteDemandeScenarios(String pIdentifiantExpediteur,
			String pReferenceCommande) {
		return new ArrayList<>();
	}

	/**
	 * Traite une demande de pilotage d'un scénario, via l'identifiant fourni
	 * 
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'expéditeur : peut être vide
	 * @param pReferenceCommande
	 *            la référence unique de la demande : peut être vide
	 * @param pIdScenario
	 *            l'identifiant du scénario à piloter
	 */
	@Override
	public void traiteDemandePilotageScenario(String pIdentifiantExpediteur, String pReferenceCommande,
			String pIdScenario) {
	}

	/**
	 * Traite une demande de pilotage d'un scénario, via le scénario temporaire
	 * fourni
	 * 
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'expéditeur : peut être vide
	 * @param pReferenceCommande
	 *            la référence unique de la demande : peut être vide
	 * @param pScenarioTemporaire
	 *            le scénario temporaire à piloter
	 */
	@Override
	public void traiteDemandePilotageScenario(String pIdentifiantExpediteur, String pReferenceCommande,
			MessageScenarioMqttRest pScenarioTemporaire) {
	}

	/**
	 * Traite une demande de pilotage d'une liste de messages dans un scénario
	 * 
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'expéditeur : peut être vide
	 * @param pReferenceCommande
	 *            la référence unique de la demande : peut être vide
	 * @param pIdScenario
	 *            l'identifiant du scénario concerné
	 * @param pMessagesAPiloter
	 *            les messages à piloter dans le scénario
	 */
	@Override
	public void traiteDemandePilotageScenario(String pIdentifiantExpediteur, String pReferenceCommande,
			String pIdScenario, List<IMessageAffichageEquipementMqttRest> pMessagesAPiloter) {
	}

	/**
	 * Traite une demande de pilotage d'un PMV
	 * 
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'expéditeur : peut être vide
	 * @param pReferenceCommande
	 *            la référence unique de la demande : peut être vide
	 * @param pIdEquipement
	 *            l'identifiant de l'équipement à piloter
	 * @param pMessageAPiloter
	 *            le message à piloter
	 */
	@Override
	public void traiteDemandePilotagePmv(String pIdentifiantExpediteur, String pReferenceCommande, String pIdEquipement,
			MessagePmvMqttRest pMessageAPiloter) {
	}

	/**
	 * Traite une demande de pilotage d'un PPLMV
	 * 
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'expéditeur : peut être vide
	 * @param pReferenceCommande
	 *            la référence unique de la demande : peut être vide
	 * @param pIdEquipement
	 *            l'identifiant de l'équipement à piloter
	 * @param pMessageAPiloter
	 *            le message à piloter
	 */
	@Override
	public void traiteDemandePilotagePplmv(String pIdentifiantExpediteur, String pReferenceCommande,
			String pIdEquipement, MessagePplmvMqttRest pMessageAPiloter) {
	}

	/**
	 * Traite une demande de pilotage d'un PPAD
	 * 
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'expéditeur : peut être vide
	 * @param pReferenceCommande
	 *            la référence unique de la demande : peut être vide
	 * @param pIdEquipement
	 *            l'identifiant de l'équipement à piloter
	 * @param pMessageAPiloter
	 *            le message à piloter
	 */
	@Override
	public void traiteDemandePilotagePpad(String pIdentifiantExpediteur, String pReferenceCommande,
			String pIdEquipement, MessagePpadMqttRest pMessageAPiloter) {
	}

	/**
	 * Traite une demande de pilotage d'un Pictogramme
	 * 
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'expéditeur : peut être vide
	 * @param pReferenceCommande
	 *            la référence unique de la demande : peut être vide
	 * @param pIdEquipement
	 *            l'identifiant de l'équipement à piloter
	 * @param pMessageAPiloter
	 *            le message à piloter
	 */
	@Override
	public void traiteDemandePilotagePictogramme(String pIdentifiantExpediteur, String pReferenceCommande,
			String pIdEquipement, MessagePictogrammeMqttRest pMessageAPiloter) {
	}

	/**
	 * Traite une demande de pilotage d'un Feu R24
	 * 
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'expéditeur : peut être vide
	 * @param pReferenceCommande
	 *            la référence unique de la demande : peut être vide
	 * @param pIdEquipement
	 *            l'identifiant de l'équipement à piloter
	 * @param pMessageAPiloter
	 *            le message à piloter
	 */
	@Override
	public void traiteDemandePilotageR24(String pIdentifiantExpediteur, String pReferenceCommande, String pIdEquipement,
			MessageR24MqttRest pMessageAPiloter) {
	}

	/**
	 * Traite une demande de pilotage d'un Prisme
	 * 
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'expéditeur : peut être vide
	 * @param pReferenceCommande
	 *            la référence unique de la demande : peut être vide
	 * @param pIdEquipement
	 *            l'identifiant de l'équipement à piloter
	 * @param pMessageAPiloter
	 *            le message à piloter
	 */
	@Override
	public void traiteDemandePilotagePrisme(String pIdentifiantExpediteur, String pReferenceCommande,
			String pIdEquipement, MessagePrismeMqttRest pMessageAPiloter) {
	}

	/**
	 * Traite une demande de pilotage d'une Barrière
	 * 
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'expéditeur : peut être vide
	 * @param pReferenceCommande
	 *            la référence unique de la demande : peut être vide
	 * @param pIdEquipement
	 *            l'identifiant de l'équipement à piloter
	 * @param pMessageAPiloter
	 *            le message à piloter
	 */
	@Override
	public void traiteDemandePilotageBarriere(String pIdentifiantExpediteur, String pReferenceCommande,
			String pIdEquipement, MessageBarriereMqttRest pMessageAPiloter) {
	}

	/**
	 * Traite une demande de pilotage d'un BRA
	 * 
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'expéditeur : peut être vide
	 * @param pReferenceCommande
	 *            la référence unique de la demande : peut être vide
	 * @param pIdEquipement
	 *            l'identifiant de l'équipement à piloter
	 * @param pMessageAPiloter
	 *            le message à piloter
	 */
	@Override
	public void traiteDemandePilotageBra(String pIdentifiantExpediteur, String pReferenceCommande, String pIdEquipement,
			MessageBraMqttRest pMessageAPiloter) {
	}

	/**
	 * Traite une demande de pilotage d'une liste de messages
	 * 
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'expéditeur : peut être vide
	 * @param pReferenceCommande
	 *            la référence unique de la demande : peut être vide
	 * @param pMessages
	 *            les messages à piloter
	 */
	@Override
	public void traiteDemandePilotageMessages(String pIdentifiantExpediteur, String pReferenceCommande,
			List<IMessageAffichageEquipementMqttRest> pMessages) {
	}

	/**
	 * Traite une demande pour savoir si un pilotage est en cours sur un
	 * équipement
	 * 
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'expéditeur : peut être vide
	 * @param pReferenceCommande
	 *            la référence unique de la demande : peut être vide
	 * @param pIdEquipement
	 *            l'identifiant de l'équipement à tester
	 * @return false
	 */
	@Override
	public boolean traiteDemandePilotageEnCours(String pIdentifiantExpediteur, String pReferenceCommande,
			String pIdEquipement) {
		return false;
	}

	/**
	 * Traite une demande pour savoir si un pilotage est en cours sur un
	 * équipement quelconque
	 * 
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'expéditeur : peut être vide
	 * @param pReferenceCommande
	 *            la référence unique de la demande : peut être vide
	 * @return false
	 */
	@Override
	public boolean traiteDemandePilotageEnCours(String pIdentifiantExpediteur, String pReferenceCommande) {
		return false;
	}
}
