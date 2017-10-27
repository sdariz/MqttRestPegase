package org.signature.mqttRest.services.rest.serveur;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.signature.mqttRest.objetsPartages.etatEtPilotage.IMessageAffichageEquipementMqttRest;
import org.signature.mqttRest.objetsPartages.etatEtPilotage.MessageBarriereMqttRest;
import org.signature.mqttRest.objetsPartages.etatEtPilotage.MessageBraMqttRest;
import org.signature.mqttRest.objetsPartages.etatEtPilotage.MessageEtatAffichageMqttRest;
import org.signature.mqttRest.objetsPartages.etatEtPilotage.MessageEtatTechniqueMqttRest;
import org.signature.mqttRest.objetsPartages.etatEtPilotage.MessageFeuRegulationMqttRest;
import org.signature.mqttRest.objetsPartages.etatEtPilotage.MessagePictogrammeMqttRest;
import org.signature.mqttRest.objetsPartages.etatEtPilotage.MessagePmvMqttRest;
import org.signature.mqttRest.objetsPartages.etatEtPilotage.MessagePpadMqttRest;
import org.signature.mqttRest.objetsPartages.etatEtPilotage.MessagePplmvMqttRest;
import org.signature.mqttRest.objetsPartages.etatEtPilotage.MessagePrismeMqttRest;
import org.signature.mqttRest.objetsPartages.etatEtPilotage.MessageR24MqttRest;
import org.signature.mqttRest.objetsPartages.evenement.MessageAlarmeMqttRest;
import org.signature.mqttRest.objetsPartages.informationPegase.MessageProprietesArmoireMqttRest;
import org.signature.mqttRest.objetsPartages.informationPegase.MessageProprietesEquipementMqttRest;
import org.signature.mqttRest.objetsPartages.scenario.MessageScenarioMqttRest;
import org.signature.mqttRest.objetsPartages.utilisateur.MessageUtilisateurMqttRest;

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
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'exp�diteur : peut �tre vide
	 * @param pReferenceCommande
	 *            la r�f�rence unique de la demande : peut �tre vide
	 * @param pIdEquipement
	 *            l'id de l'�quipement
	 * @return null
	 */
	@Override
	public MessageEtatAffichageMqttRest demandeEtatAffichageEquipement(String pIdentifiantExpediteur,
			String pReferenceCommande, String pIdEquipement) {
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
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'exp�diteur : peut �tre vide
	 * @param pReferenceCommande
	 *            la r�f�rence unique de la demande : peut �tre vide
	 * @param pIdEquipement
	 *            l'id de l'�quipement
	 * @return null
	 */
	@Override
	public MessageEtatTechniqueMqttRest demandeEtatTechniqueEquipement(String pIdentifiantExpediteur,
			String pReferenceCommande, String pIdEquipement) {
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
	public List<MessageEtatTechniqueMqttRest> demandeEtatTechniqueEquipements(String pIdentifiantExpediteur,
			String pReferenceCommande) {
		return new ArrayList<>();
	}

	/**
	 * Traite une demande d'actualisation de l'�tat d'un �quipement, en for�ant
	 * une interrogation de l'�quipement sur le terrain
	 * 
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'exp�diteur : peut �tre vide
	 * @param pReferenceCommande
	 *            la r�f�rence unique de la demande : peut �tre vide
	 * @param pIdEquipement
	 *            l'id de l'�quipement � rafra�chir
	 */
	@Override
	public void demandeActualisationEtatEquipement(String pIdentifiantExpediteur, String pReferenceCommande,
			String pIdEquipement) {
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
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'exp�diteur : peut �tre vide
	 * @param pReferenceCommande
	 *            la r�f�rence unique de la demande : peut �tre vide
	 * @param pLogin
	 *            le login de l'utilisateur
	 * @param pMotPasse
	 *            le mot de passe associ� au login
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
	 *            l'identifiant unique de l'exp�diteur : peut �tre vide
	 * @param pReferenceCommande
	 *            la r�f�rence unique de la demande : peut �tre vide
	 * @param pInterdit
	 *            true pour interdire les pilotages sur Pegase
	 */
	@Override
	public void traiteDemandeInterdictionPilotages(String pIdentifiantExpediteur, String pReferenceCommande,
			boolean pInterdit) {
	}
	
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
	@Override
	public void traiteDemandeInterdictionPilotages(String pIdentifiantExpediteur, String pReferenceCommande,
			List<String> pIdsEquipements, boolean pInterdit) {
	}

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
	@Override
	public void traiteDemandeActivationBouton(String pIdentifiantExpediteur, String pReferenceCommande,
			String pIdBouton, boolean pActif) {
	}

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
	@Override
	public void traiteDemandeLancementActionBouton(String pIdentifiantExpediteur, String pReferenceCommande,
			String pIdBouton) {
	}

	/**
	 * Traite une demande de lancement de test sur les �quipements d'une armoire
	 * 
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'exp�diteur : peut �tre vide
	 * @param pReferenceCommande
	 *            la r�f�rence unique de la demande : peut �tre vide
	 * @param pIdEquipement
	 *            l'identifiant d'un �quipement de l'armoire � tester
	 */
	@Override
	public void traiteDemandeLancementTestEquipements(String pIdentifiantExpediteur, String pReferenceCommande,
			String pIdEquipement) {
	}

	/**
	 * Traite une demande de remise � l'heure d'une armoire
	 * 
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'exp�diteur : peut �tre vide
	 * @param pReferenceCommande
	 *            la r�f�rence unique de la demande : peut �tre vide
	 * @param pIdEquipement
	 *            l'identifiant d'un �quipement de l'armoire � remettre � l'heure
	 */
	@Override
	public void traiteDemandeRemiseHeureArmoire(String pIdentifiantExpediteur, String pReferenceCommande,
			String pIdEquipement) {
	}

	/**
	 * Traite une demande d'interrogation d'une armoire
	 * 
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'exp�diteur : peut �tre vide
	 * @param pReferenceCommande
	 *            la r�f�rence unique de la demande : peut �tre vide
	 * @param pIdEquipement
	 *            l'identifiant d'un �quipement de l'armoire � interroger
	 * @param pTrame
	 *            la commande � transmettre � l'armoire
	 * @return la r�ponse de l'armoire
	 */
	@Override
	public String traiteDemandeArmoire(String pIdentifiantExpediteur, String pReferenceCommande, String pIdEquipement,
			String pTrame) {
		return "";
	}

	/**
	 * Traite une demande d'obtention de la liste des cat�gorie de la
	 * biblioth�que
	 * 
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'exp�diteur : peut �tre vide
	 * @param pReferenceCommande
	 *            la r�f�rence unique de la demande : peut �tre vide
	 * @return une liste vide
	 */
	@Override
	public List<String> traiteDemandeCategoriesBibliothequePmv(String pIdentifiantExpediteur,
			String pReferenceCommande) {
		return new ArrayList<>();
	}

	/**
	 * Traite une demande d'obtention de la liste des noms des messages dans une
	 * cat�gorie de la biblioth�que
	 * 
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'exp�diteur : peut �tre vide
	 * @param pReferenceCommande
	 *            la r�f�rence unique de la demande : peut �tre vide
	 * @param pCategorie
	 *            la cat�gorie concern�e
	 * @return une liste vide
	 */
	@Override
	public List<String> traiteDemandeMessagesDansCategorieBibliothequePmv(String pIdentifiantExpediteur,
			String pReferenceCommande, String pCategorie) {
		return new ArrayList<>();
	}

	/**
	 * Traite une demande d'obtention d'un message de la biblioth�que
	 * 
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'exp�diteur : peut �tre vide
	 * @param pReferenceCommande
	 *            la r�f�rence unique de la demande : peut �tre vide
	 * @param pCategorie
	 *            la cat�gorie concern�e
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
	 * Traite une demande d'obtention des alarmes entre deux dates
	 * 
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'exp�diteur : peut �tre vide
	 * @param pReferenceCommande
	 *            la r�f�rence unique de la demande : peut �tre vide
	 * @param pIdEquipement
	 *            l'id de l'�quipement concern�
	 * @param pHorodateDebut
	 *            l'horodate de d�but de l'alarme
	 * @param pHorodateFin
	 *            l'horodate de fin de l'alarme
	 * @param pActiveSeul
	 *            true pour ne s�lectionner que les alarmes actives
	 * @return une liste vide
	 */
	@Override
	public List<MessageAlarmeMqttRest> traiteDemandeListeAlarmes(String pIdentifiantExpediteur,
			String pReferenceCommande, String pIdEquipement, Date pHorodateDebut, Date pHorodateFin,
			boolean pActiveSeul) {
		return new ArrayList<>();
	}

	/**
	 * Traite une demande d'�tat d'un �quipement � une certaine date
	 * 
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'exp�diteur : peut �tre vide
	 * @param pReferenceCommande
	 *            la r�f�rence unique de la demande : peut �tre vide
	 * @param pIdEquipement
	 *            l'id de l'�quipement concern�
	 * @param pHorodate
	 *            l'horodate de l'�v�nement
	 * @return null
	 */
	@Override
	public IMessageAffichageEquipementMqttRest traiteDemandeEtatAffichageEquipementPourDate(
			String pIdentifiantExpediteur, String pReferenceCommande, String pIdEquipement, Date pHorodate) {
		return null;
	}

	/**
	 * Traite une demande d'�tat d'un �quipement entre deux dates
	 * 
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'exp�diteur : peut �tre vide
	 * @param pReferenceCommande
	 *            la r�f�rence unique de la demande : peut �tre vide
	 * @param pIdEquipement
	 *            l'id de l'�quipement concern�
	 * @param pHorodateDebut
	 *            l'horodate de d�but de l'�v�nement
	 * @param pHorodateFin
	 *            l'horodate de fin de l'�v�nement
	 * @return une liste vide
	 */
	@Override
	public List<IMessageAffichageEquipementMqttRest> traiteDemandeEtatAffichageEquipementEntreDeuxDates(
			String pIdentifiantExpediteur, String pReferenceCommande, String pIdEquipement, Date pHorodateDebut,
			Date pHorodateFin) {
		return new ArrayList<>();
	}

	/**
	 * Traite une demande d'obtention des propri�t�s d'un �quipement
	 * 
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'exp�diteur : peut �tre vide
	 * @param pReferenceCommande
	 *            la r�f�rence unique de la demande : peut �tre vide
	 * @param pIdEquipement
	 *            l'id de l'�quipement concern�
	 * @return null
	 */
	@Override
	public MessageProprietesEquipementMqttRest traiteDemandeProprietesEquipement(String pIdentifiantExpediteur,
			String pReferenceCommande, String pIdEquipement) {
		return null;
	}

	/**
	 * Traite une demande d'obtention des propri�t�s des �quipements
	 * 
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'exp�diteur : peut �tre vide
	 * @param pReferenceCommande
	 *            la r�f�rence unique de la demande : peut �tre vide
	 * @return une liste vide
	 */
	@Override
	public List<MessageProprietesEquipementMqttRest> traiteDemandeProprietesEquipements(String pIdentifiantExpediteur,
			String pReferenceCommande) {
		return new ArrayList<>();
	}

	/**
	 * Traite une demande d'obtention des propri�t�s d'une armoire
	 * 
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'exp�diteur : peut �tre vide
	 * @param pReferenceCommande
	 *            la r�f�rence unique de la demande : peut �tre vide
	 * @param pIdEquipement
	 *            l'identifiant d'un �quipement de l'armoire � interroger
	 * @return null
	 */
	@Override
	public MessageProprietesArmoireMqttRest traiteDemandeProprietesArmoire(String pIdentifiantExpediteur,
			String pReferenceCommande, String pIdEquipement) {
		return null;
	}

	/**
	 * Traite une demande d'obtention des propri�t�s des armoires
	 * 
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'exp�diteur : peut �tre vide
	 * @param pReferenceCommande
	 *            la r�f�rence unique de la demande : peut �tre vide
	 * @return une liste vide
	 */
	@Override
	public List<MessageProprietesArmoireMqttRest> traiteDemandeProprietesArmoires(String pIdentifiantExpediteur,
			String pReferenceCommande) {
		return new ArrayList<>();
	}

	/**
	 * Traite une demande d'obtention de la liste des identifiants des sc�narios
	 * 
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'exp�diteur : peut �tre vide
	 * @param pReferenceCommande
	 *            la r�f�rence unique de la demande : peut �tre vide
	 * @return une liste vide
	 */
	@Override
	public List<String> traiteDemandeIdentifiantsScenarios(String pIdentifiantExpediteur, String pReferenceCommande) {
		return new ArrayList<>();
	}

	/**
	 * Traite une demande d'obtention d'un sc�nario en particulier
	 * 
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'exp�diteur : peut �tre vide
	 * @param pReferenceCommande
	 *            la r�f�rence unique de la demande : peut �tre vide
	 * @param pIdScenario
	 *            l'identifiant du sc�nario � r�cup�rer
	 * @return null
	 */
	@Override
	public MessageScenarioMqttRest traiteDemandeScenario(String pIdentifiantExpediteur, String pReferenceCommande,
			String pIdScenario) {
		return null;
	}

	/**
	 * Traite une demande d'obtention de tous les sc�narios
	 * 
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'exp�diteur : peut �tre vide
	 * @param pReferenceCommande
	 *            la r�f�rence unique de la demande : peut �tre vide
	 * @return une liste vide
	 */
	@Override
	public List<MessageScenarioMqttRest> traiteDemandeScenarios(String pIdentifiantExpediteur,
			String pReferenceCommande) {
		return new ArrayList<>();
	}

	/**
	 * Traite une demande de pilotage d'un sc�nario, via l'identifiant fourni
	 * 
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'exp�diteur : peut �tre vide
	 * @param pReferenceCommande
	 *            la r�f�rence unique de la demande : peut �tre vide
	 * @param pIdScenario
	 *            l'identifiant du sc�nario � piloter
	 */
	@Override
	public void traiteDemandePilotageScenario(String pIdentifiantExpediteur, String pReferenceCommande,
			String pIdScenario) {
	}

	/**
	 * Traite une demande de pilotage d'un sc�nario, via le sc�nario temporaire
	 * fourni
	 * 
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'exp�diteur : peut �tre vide
	 * @param pReferenceCommande
	 *            la r�f�rence unique de la demande : peut �tre vide
	 * @param pScenarioTemporaire
	 *            le sc�nario temporaire � piloter
	 */
	@Override
	public void traiteDemandePilotageScenario(String pIdentifiantExpediteur, String pReferenceCommande,
			MessageScenarioMqttRest pScenarioTemporaire) {
	}

	/**
	 * Traite une demande de pilotage d'une liste de messages dans un sc�nario
	 * 
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'exp�diteur : peut �tre vide
	 * @param pReferenceCommande
	 *            la r�f�rence unique de la demande : peut �tre vide
	 * @param pIdScenario
	 *            l'identifiant du sc�nario concern�
	 * @param pMessagesAPiloter
	 *            les messages � piloter dans le sc�nario
	 */
	@Override
	public void traiteDemandePilotageScenario(String pIdentifiantExpediteur, String pReferenceCommande,
			String pIdScenario, List<IMessageAffichageEquipementMqttRest> pMessagesAPiloter) {
	}

	/**
	 * Traite une demande de pilotage d'un PMV
	 * 
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'exp�diteur : peut �tre vide
	 * @param pReferenceCommande
	 *            la r�f�rence unique de la demande : peut �tre vide
	 * @param pIdEquipement
	 *            l'identifiant de l'�quipement � piloter
	 * @param pMessageAPiloter
	 *            le message � piloter
	 */
	@Override
	public void traiteDemandePilotagePmv(String pIdentifiantExpediteur, String pReferenceCommande, String pIdEquipement,
			MessagePmvMqttRest pMessageAPiloter) {
	}

	/**
	 * Traite une demande de pilotage d'un PPLMV
	 * 
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'exp�diteur : peut �tre vide
	 * @param pReferenceCommande
	 *            la r�f�rence unique de la demande : peut �tre vide
	 * @param pIdEquipement
	 *            l'identifiant de l'�quipement � piloter
	 * @param pMessageAPiloter
	 *            le message � piloter
	 */
	@Override
	public void traiteDemandePilotagePplmv(String pIdentifiantExpediteur, String pReferenceCommande,
			String pIdEquipement, MessagePplmvMqttRest pMessageAPiloter) {
	}

	/**
	 * Traite une demande de pilotage d'un PPAD
	 * 
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'exp�diteur : peut �tre vide
	 * @param pReferenceCommande
	 *            la r�f�rence unique de la demande : peut �tre vide
	 * @param pIdEquipement
	 *            l'identifiant de l'�quipement � piloter
	 * @param pMessageAPiloter
	 *            le message � piloter
	 */
	@Override
	public void traiteDemandePilotagePpad(String pIdentifiantExpediteur, String pReferenceCommande,
			String pIdEquipement, MessagePpadMqttRest pMessageAPiloter) {
	}

	/**
	 * Traite une demande de pilotage d'un Pictogramme
	 * 
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'exp�diteur : peut �tre vide
	 * @param pReferenceCommande
	 *            la r�f�rence unique de la demande : peut �tre vide
	 * @param pIdEquipement
	 *            l'identifiant de l'�quipement � piloter
	 * @param pMessageAPiloter
	 *            le message � piloter
	 */
	@Override
	public void traiteDemandePilotagePictogramme(String pIdentifiantExpediteur, String pReferenceCommande,
			String pIdEquipement, MessagePictogrammeMqttRest pMessageAPiloter) {
	}

	/**
	 * Traite une demande de pilotage d'un Feu R24
	 * 
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'exp�diteur : peut �tre vide
	 * @param pReferenceCommande
	 *            la r�f�rence unique de la demande : peut �tre vide
	 * @param pIdEquipement
	 *            l'identifiant de l'�quipement � piloter
	 * @param pMessageAPiloter
	 *            le message � piloter
	 */
	@Override
	public void traiteDemandePilotageR24(String pIdentifiantExpediteur, String pReferenceCommande, String pIdEquipement,
			MessageR24MqttRest pMessageAPiloter) {
	}

	/**
	 * Traite une demande de pilotage d'un Prisme
	 * 
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'exp�diteur : peut �tre vide
	 * @param pReferenceCommande
	 *            la r�f�rence unique de la demande : peut �tre vide
	 * @param pIdEquipement
	 *            l'identifiant de l'�quipement � piloter
	 * @param pMessageAPiloter
	 *            le message � piloter
	 */
	@Override
	public void traiteDemandePilotagePrisme(String pIdentifiantExpediteur, String pReferenceCommande,
			String pIdEquipement, MessagePrismeMqttRest pMessageAPiloter) {
	}

	/**
	 * Traite une demande de pilotage d'une Barri�re
	 * 
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'exp�diteur : peut �tre vide
	 * @param pReferenceCommande
	 *            la r�f�rence unique de la demande : peut �tre vide
	 * @param pIdEquipement
	 *            l'identifiant de l'�quipement � piloter
	 * @param pMessageAPiloter
	 *            le message � piloter
	 */
	@Override
	public void traiteDemandePilotageBarriere(String pIdentifiantExpediteur, String pReferenceCommande,
			String pIdEquipement, MessageBarriereMqttRest pMessageAPiloter) {
	}

	/**
	 * Traite une demande de pilotage d'un BRA
	 * 
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'exp�diteur : peut �tre vide
	 * @param pReferenceCommande
	 *            la r�f�rence unique de la demande : peut �tre vide
	 * @param pIdEquipement
	 *            l'identifiant de l'�quipement � piloter
	 * @param pMessageAPiloter
	 *            le message � piloter
	 */
	@Override
	public void traiteDemandePilotageBra(String pIdentifiantExpediteur, String pReferenceCommande, String pIdEquipement,
			MessageBraMqttRest pMessageAPiloter) {
	}
	
	/**
	 * Traite une demande de pilotage d'un Feu de Regulation
	 * 
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'exp�diteur : peut �tre vide
	 * @param pReferenceCommande
	 *            la r�f�rence unique de la demande : peut �tre vide
	 * @param pIdEquipement
	 *            l'identifiant de l'�quipement � piloter
	 * @param pMessageAPiloter
	 *            le message � piloter
	 */
	@Override
	public void traiteDemandePilotageFeuRegulation(String pIdentifiantExpediteur, String pReferenceCommande, String pIdEquipement,
			MessageFeuRegulationMqttRest pMessageAPiloter) {
	}

	/**
	 * Traite une demande de pilotage d'une liste de messages
	 * 
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'exp�diteur : peut �tre vide
	 * @param pReferenceCommande
	 *            la r�f�rence unique de la demande : peut �tre vide
	 * @param pMessages
	 *            les messages � piloter
	 */
	@Override
	public void traiteDemandePilotageMessages(String pIdentifiantExpediteur, String pReferenceCommande,
			List<IMessageAffichageEquipementMqttRest> pMessages) {
	}

	/**
	 * Traite une demande pour savoir si un pilotage est en cours sur un
	 * �quipement
	 * 
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'exp�diteur : peut �tre vide
	 * @param pReferenceCommande
	 *            la r�f�rence unique de la demande : peut �tre vide
	 * @param pIdEquipement
	 *            l'identifiant de l'�quipement � tester
	 * @return false
	 */
	@Override
	public boolean traiteDemandePilotageEnCours(String pIdentifiantExpediteur, String pReferenceCommande,
			String pIdEquipement) {
		return false;
	}

	/**
	 * Traite une demande pour savoir si un pilotage est en cours sur un
	 * �quipement quelconque
	 * 
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'exp�diteur : peut �tre vide
	 * @param pReferenceCommande
	 *            la r�f�rence unique de la demande : peut �tre vide
	 * @return false
	 */
	@Override
	public boolean traiteDemandePilotageEnCours(String pIdentifiantExpediteur, String pReferenceCommande) {
		return false;
	}
}
