package signature.mqttRest.services.rest.client;

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
import signature.mqttRest.services.rest.client.administration.ServiceRequetesAdministration;
import signature.mqttRest.services.rest.client.bibliothequePmv.ServiceRequetesBibliothequePmv;
import signature.mqttRest.services.rest.client.etatEquipement.ServiceRequetesEtatEquipement;
import signature.mqttRest.services.rest.client.evenement.ServiceRequetesEvenement;
import signature.mqttRest.services.rest.client.informationPegase.ServiceRequetesInformationPegase;
import signature.mqttRest.services.rest.client.interrogationArmoire.ServiceRequetesInterrogationArmoire;
import signature.mqttRest.services.rest.client.pilotage.ServiceRequetesPilotage;
import signature.mqttRest.services.rest.client.scenario.ServiceRequetesScenario;
import signature.mqttRest.services.rest.client.utilisateur.ServiceRequetesUtilisateur;

/**
 * M�thodes utilitaires d'interrogation du serveur HTTP REST, pour r�cup�rer
 * diff�rentes informations.
 * 
 * @author SDARIZCUREN
 *
 */
public class InterrogationServeurHttpRest {

	/**
	 * Demande d'�tat d'affichage d'un �quipement
	 * 
	 * @param pHost
	 *            l'adresse IP du serveur REST
	 * @param pPort
	 *            le port TCP utilis� par le serveur
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'exp�diteur : peut �tre vide
	 * @param pReferenceCommande
	 *            la r�f�rence unique de la demande : peut �tre vide
	 * @param pIdEquipement
	 *            l'id de l'�quipement � interroger
	 * @return l'�tat d'affichage de l'�quipement, ou null si probl�me
	 */
	public static MessageEtatAffichageMqttRest requeteDemandeEtatAffichageEquipement(String pHost, int pPort,
			String pIdentifiantExpediteur, String pReferenceCommande, String pIdEquipement) {
		return ServiceRequetesEtatEquipement.requeteDemandeEtatAffichageEquipement(pHost, pPort, pIdentifiantExpediteur,
				pReferenceCommande, pIdEquipement);
	}

	/**
	 * Demande d'�tat d'affichage de tous les �quipements
	 * 
	 * @param pHost
	 *            l'adresse IP du serveur REST
	 * @param pPort
	 *            le port TCP utilis� par le serveur
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'exp�diteur : peut �tre vide
	 * @param pReferenceCommande
	 *            la r�f�rence unique de la demande : peut �tre vide
	 * @return l'�tat d'affichage des �quipements, liste vide si probl�me
	 */
	public static List<MessageEtatAffichageMqttRest> requeteDemandeEtatAffichageEquipements(String pHost, int pPort,
			String pIdentifiantExpediteur, String pReferenceCommande) {
		return ServiceRequetesEtatEquipement.requeteDemandeEtatAffichageEquipements(pHost, pPort,
				pIdentifiantExpediteur, pReferenceCommande);
	}

	/**
	 * Demande d'�tat technique d'un �quipement
	 * 
	 * @param pHost
	 *            l'adresse IP du serveur REST
	 * @param pPort
	 *            le port TCP utilis� par le serveur
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'exp�diteur : peut �tre vide
	 * @param pReferenceCommande
	 *            la r�f�rence unique de la demande : peut �tre vide
	 * @param pIdEquipement
	 *            l'id de l'�quipement � interroger
	 * @return l'�tat technique de l'�quipement, ou null si probl�me
	 */
	public static MessageEtatTechniqueMqttRest requeteDemandeEtatTechniqueEquipement(String pHost, int pPort,
			String pIdentifiantExpediteur, String pReferenceCommande, String pIdEquipement) {
		return ServiceRequetesEtatEquipement.requeteDemandeEtatTechniqueEquipement(pHost, pPort, pIdentifiantExpediteur,
				pReferenceCommande, pIdEquipement);
	}

	/**
	 * Demande d'�tat technique de tous les �quipements
	 * 
	 * @param pHost
	 *            l'adresse IP du serveur REST
	 * @param pPort
	 *            le port TCP utilis� par le serveur
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'exp�diteur : peut �tre vide
	 * @param pReferenceCommande
	 *            la r�f�rence unique de la demande : peut �tre vide
	 * @return l'�tat technique des �quipements, liste vide si probl�me
	 */
	public static List<MessageEtatTechniqueMqttRest> requeteDemandeEtatTechniqueEquipements(String pHost, int pPort,
			String pIdentifiantExpediteur, String pReferenceCommande) {
		return ServiceRequetesEtatEquipement.requeteDemandeEtatTechniqueEquipements(pHost, pPort,
				pIdentifiantExpediteur, pReferenceCommande);
	}

	/**
	 * Demande � actualiser l'�tat d'un �quipement, en for�ant une interrogation
	 * de l'�quipement sur le terrain
	 * 
	 * @param pHost
	 *            l'adresse IP du serveur REST
	 * @param pPort
	 *            le port TCP utilis� par le serveur
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'exp�diteur : peut �tre vide
	 * @param pReferenceCommande
	 *            la r�f�rence unique de la demande : peut �tre vide
	 * @param pIdEquipement
	 *            l'id de l'�quipement � interroger
	 */
	public static void requeteActualisationEtatEquipement(String pHost, int pPort, String pIdentifiantExpediteur,
			String pReferenceCommande, String pIdEquipement) {
		ServiceRequetesEtatEquipement.requeteActualisationEtatEquipement(pHost, pPort, pIdentifiantExpediteur,
				pReferenceCommande, pIdEquipement);
	}

	/**
	 * Demande de la liste des utilisateurs
	 * 
	 * @param pHost
	 *            l'adresse IP du serveur REST
	 * @param pPort
	 *            le port TCP utilis� par le serveur
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'exp�diteur : peut �tre vide
	 * @param pReferenceCommande
	 *            la r�f�rence unique de la demande : peut �tre vide
	 * @return la liste des utilisateurs, liste vide si probl�me
	 */
	public static List<MessageUtilisateurMqttRest> requeteDemandeListeUtilisateurs(String pHost, int pPort,
			String pIdentifiantExpediteur, String pReferenceCommande) {
		return ServiceRequetesUtilisateur.requeteDemandeListeUtilisateurs(pHost, pPort, pIdentifiantExpediteur,
				pReferenceCommande);
	}

	/**
	 * Demande l'utilisateur connect�
	 * 
	 * @param pHost
	 *            l'adresse IP du serveur REST
	 * @param pPort
	 *            le port TCP utilis� par le serveur
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'exp�diteur : peut �tre vide
	 * @param pReferenceCommande
	 *            la r�f�rence unique de la demande : peut �tre vide
	 * @return l'utilisateur connect�, null si aucun ou si probl�me
	 */
	public static MessageUtilisateurMqttRest requeteDemandeUtilisateurConnecte(String pHost, int pPort,
			String pIdentifiantExpediteur, String pReferenceCommande) {
		return ServiceRequetesUtilisateur.requeteDemandeUtilisateurConnecte(pHost, pPort, pIdentifiantExpediteur,
				pReferenceCommande);
	}

	/**
	 * Demande � valider un identifiant et mot de passe
	 * 
	 * @param pHost
	 *            l'adresse IP du serveur REST
	 * @param pPort
	 *            le port TCP utilis� par le serveur
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'exp�diteur : peut �tre vide
	 * @param pReferenceCommande
	 *            la r�f�rence unique de la demande : peut �tre vide
	 * @param pLogin
	 *            le login � valider
	 * @param pMotPasse
	 *            le mot de passe associ� au login
	 * @return true si valide, sinon false
	 */
	public static boolean requeteDemandeIdentifiantsValide(String pHost, int pPort, String pIdentifiantExpediteur,
			String pReferenceCommande, String pLogin, String pMotPasse) {
		return ServiceRequetesUtilisateur.requeteDemandeIdentifiantsValide(pHost, pPort, pIdentifiantExpediteur,
				pReferenceCommande, pLogin, pMotPasse);
	}

	/**
	 * Autorise ou interdit les pilotages sur Pegase
	 * 
	 * @param pHost
	 *            l'adresse IP du serveur REST
	 * @param pPort
	 *            le port TCP utilis� par le serveur
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'exp�diteur : peut �tre vide
	 * @param pReferenceCommande
	 *            la r�f�rence unique de la demande : peut �tre vide
	 * @param pInterdit
	 *            true pour interdire les pilotages sur Pegase
	 */
	public static void requeteInterdictionPilotages(String pHost, int pPort, String pIdentifiantExpediteur,
			String pReferenceCommande, boolean pInterdit) {
		ServiceRequetesAdministration.requeteInterdictionPilotages(pHost, pPort, pIdentifiantExpediteur,
				pReferenceCommande, pInterdit);
	}

	/**
	 * Test de pr�sence du serveur REST
	 * 
	 * @param pHost
	 *            l'adresse IP du serveur REST
	 * @param pPort
	 *            le port TCP utilis� par le serveur
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'exp�diteur : peut �tre vide
	 * @param pReferenceCommande
	 *            la r�f�rence unique de la demande : peut �tre vide
	 * @return true si r�ponse OK du serveur, false si probl�me
	 */
	public static boolean requeteTestPresence(String pHost, int pPort, String pIdentifiantExpediteur,
			String pReferenceCommande) {
		return ServiceRequetesAdministration.requeteTestPresence(pHost, pPort, pIdentifiantExpediteur,
				pReferenceCommande);
	}

	/**
	 * Demande d'activation ou d�sactivation d'un bouton
	 * 
	 * @param pHost
	 *            l'adresse IP du serveur REST
	 * @param pPort
	 *            le port TCP utilis� par le serveur
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'exp�diteur : peut �tre vide
	 * @param pReferenceCommande
	 *            la r�f�rence unique de la demande : peut �tre vide
	 * @param pIdBouton
	 *            l'identifiant du bouton
	 * @param pActif
	 *            true pour activer, false pour d�sactiver
	 */
	public static void requeteActivationBouton(String pHost, int pPort, String pIdentifiantExpediteur,
			String pReferenceCommande, String pIdBouton, boolean pActif) {
		ServiceRequetesAdministration.requeteActivationBouton(pHost, pPort, pIdentifiantExpediteur, pReferenceCommande,
				pIdBouton, pActif);
	}

	/**
	 * Demande de lancement de l'action rattach�e � un bouton
	 * 
	 * @param pHost
	 *            l'adresse IP du serveur REST
	 * @param pPort
	 *            le port TCP utilis� par le serveur
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'exp�diteur : peut �tre vide
	 * @param pReferenceCommande
	 *            la r�f�rence unique de la demande : peut �tre vide
	 * @param pIdBouton
	 *            l'identifiant du bouton
	 */
	public static void requeteLancementActionBouton(String pHost, int pPort, String pIdentifiantExpediteur,
			String pReferenceCommande, String pIdBouton) {
		ServiceRequetesAdministration.requeteLancementActionBouton(pHost, pPort, pIdentifiantExpediteur,
				pReferenceCommande, pIdBouton);
	}

	/**
	 * Demande de lancement de test sur les �quipements d'une armoire
	 * 
	 * @param pHost
	 *            l'adresse IP du serveur REST
	 * @param pPort
	 *            le port TCP utilis� par le serveur
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'exp�diteur : peut �tre vide
	 * @param pReferenceCommande
	 *            la r�f�rence unique de la demande : peut �tre vide
	 * @param pIdArmoire
	 *            l'identifiant de l'armoire
	 */
	public static void requeteLancementTestEquipementsArmoire(String pHost, int pPort, String pIdentifiantExpediteur,
			String pReferenceCommande, String pIdArmoire) {
		ServiceRequetesInterrogationArmoire.requeteLancementTestEquipementsArmoire(pHost, pPort, pIdentifiantExpediteur,
				pReferenceCommande, pIdArmoire);
	}

	/**
	 * Demande de remise � l'heure d'une armoire
	 * 
	 * @param pHost
	 *            l'adresse IP du serveur REST
	 * @param pPort
	 *            le port TCP utilis� par le serveur
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'exp�diteur : peut �tre vide
	 * @param pReferenceCommande
	 *            la r�f�rence unique de la demande : peut �tre vide
	 * @param pIdArmoire
	 *            l'identifiant de l'armoire
	 */
	public static void requeteRemiseHeureArmoire(String pHost, int pPort, String pIdentifiantExpediteur,
			String pReferenceCommande, String pIdArmoire) {
		ServiceRequetesInterrogationArmoire.requeteRemiseHeureArmoire(pHost, pPort, pIdentifiantExpediteur,
				pReferenceCommande, pIdArmoire);
	}

	/**
	 * Envoi d'une demande � une armoire d'une armoire
	 * 
	 * @param pHost
	 *            l'adresse IP du serveur REST
	 * @param pPort
	 *            le port TCP utilis� par le serveur
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'exp�diteur : peut �tre vide
	 * @param pReferenceCommande
	 *            la r�f�rence unique de la demande : peut �tre vide
	 * @param pIdArmoire
	 *            l'identifiant de l'armoire
	 * @param pTrame
	 *            la trame � envoyer � l'armoire
	 * @return la r�ponse de l'armoire
	 */
	public static String requeteDemandeArmoire(String pHost, int pPort, String pIdentifiantExpediteur,
			String pReferenceCommande, String pIdArmoire, String pTrame) {
		return ServiceRequetesInterrogationArmoire.requeteDemandeArmoire(pHost, pPort, pIdentifiantExpediteur,
				pReferenceCommande, pIdArmoire, pTrame);
	}

	/**
	 * Demande la liste des cat�gories de la biblioth�que PMV
	 * 
	 * @param pHost
	 *            l'adresse IP du serveur REST
	 * @param pPort
	 *            le port TCP utilis� par le serveur
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'exp�diteur : peut �tre vide
	 * @param pReferenceCommande
	 *            la r�f�rence unique de la demande : peut �tre vide
	 * @return la liste des cat�gories
	 */
	public static List<String> requeteDemandeCategoriesBibliothequePmv(String pHost, int pPort,
			String pIdentifiantExpediteur, String pReferenceCommande) {
		return ServiceRequetesBibliothequePmv.requeteDemandeCategoriesBibliothequePmv(pHost, pPort,
				pIdentifiantExpediteur, pReferenceCommande);
	}

	/**
	 * Demande la liste des noms des messages pour une cat�gorie de la
	 * biblioth�que PMV
	 * 
	 * @param pHost
	 *            l'adresse IP du serveur REST
	 * @param pPort
	 *            le port TCP utilis� par le serveur
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'exp�diteur : peut �tre vide
	 * @param pReferenceCommande
	 *            la r�f�rence unique de la demande : peut �tre vide
	 * @param pCategorie
	 *            le nom de la cat�gorie
	 * @return la liste noms des messages pour la cat�gorie demand�e
	 */
	public static List<String> requeteDemandeMessagesDansCategorieBibliothequePmv(String pHost, int pPort,
			String pIdentifiantExpediteur, String pReferenceCommande, String pCategorie) {
		return ServiceRequetesBibliothequePmv.requeteDemandeMessagesDansCategorieBibliothequePmv(pHost, pPort,
				pIdentifiantExpediteur, pReferenceCommande, pCategorie);
	}

	/**
	 * Demande un message de la biblioth�que PMV
	 * 
	 * @param pHost
	 *            l'adresse IP du serveur REST
	 * @param pPort
	 *            le port TCP utilis� par le serveur
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'exp�diteur : peut �tre vide
	 * @param pReferenceCommande
	 *            la r�f�rence unique de la demande : peut �tre vide
	 * @param pCategorie
	 *            la cat�gorie concern�e
	 * @pNom le nom du message dans la cat�gorie
	 * @return le message correspondant, ou null si probl�me
	 */
	public static MessagePmvMqttRest requeteDemandeMessageBibliothequePmv(String pHost, int pPort,
			String pIdentifiantExpediteur, String pReferenceCommande, String pCategorie, String pNom) {
		return ServiceRequetesBibliothequePmv.requeteDemandeMessageBibliothequePmv(pHost, pPort, pIdentifiantExpediteur,
				pReferenceCommande, pCategorie, pNom);
	}

	/**
	 * Lance une demande de recherche d'alarmes
	 * 
	 * @param pHost
	 *            l'adresse IP du serveur REST
	 * @param pPort
	 *            le port TCP utilis� par le serveur
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
	 * @return la liste des alarmes
	 */
	public static List<MessageAlarmeMqttRest> requeteDemandeListeAlarmes(String pHost, int pPort,
			String pIdentifiantExpediteur, String pReferenceCommande, String pIdEquipement, Date pHorodateDebut,
			Date pHorodateFin, boolean pActiveSeul) {
		return ServiceRequetesEvenement.requeteDemandeListeAlarmes(pHost, pPort, pIdentifiantExpediteur,
				pReferenceCommande, pIdEquipement, pHorodateDebut, pHorodateFin, pActiveSeul);
	}

	/**
	 * Lance une demande pour conna�tre l'�tat d'affichage d'un �quipement � une
	 * date donn�e
	 * 
	 * @param pHost
	 *            l'adresse IP du serveur REST
	 * @param pPort
	 *            le port TCP utilis� par le serveur
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'exp�diteur : peut �tre vide
	 * @param pReferenceCommande
	 *            la r�f�rence unique de la demande : peut �tre vide
	 * @param pIdEquipement
	 *            l'id de l'�quipement concern�
	 * @param pHorodate
	 *            l'horodate de l'�v�nement
	 * @return l'�tat d'affichage ou null si probl�me
	 */
	public static IMessageAffichageEquipementMqttRest requeteDemandeEtatAffichageEquipementPourDate(String pHost,
			int pPort, String pIdentifiantExpediteur, String pReferenceCommande, String pIdEquipement, Date pHorodate) {
		return ServiceRequetesEvenement.requeteDemandeEtatAffichageEquipementPourDate(pHost, pPort,
				pIdentifiantExpediteur, pReferenceCommande, pIdEquipement, pHorodate);
	}

	/**
	 * Lance une demande pour conna�tre l'�tat d'affichage d'un �quipement entre
	 * deux dates
	 * 
	 * @param pHost
	 *            l'adresse IP du serveur REST
	 * @param pPort
	 *            le port TCP utilis� par le serveur
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'exp�diteur : peut �tre vide
	 * @param pReferenceCommande
	 *            la r�f�rence unique de la demande : peut �tre vide
	 * @param pIdEquipement
	 *            l'id de l'�quipement concern�
	 * @param pHorodateDebut
	 *            l'horodate de d�but
	 * @param pHorodateFin
	 *            l'horodate de fin
	 * @return les �tats d'affichage de l'�quipement
	 */
	public static List<IMessageAffichageEquipementMqttRest> requeteDemandeEtatAffichageEquipementEntreDeuxDates(
			String pHost, int pPort, String pIdentifiantExpediteur, String pReferenceCommande, String pIdEquipement,
			Date pHorodateDebut, Date pHorodateFin) {
		return ServiceRequetesEvenement.requeteDemandeEtatAffichageEquipementEntreDeuxDates(pHost, pPort,
				pIdentifiantExpediteur, pReferenceCommande, pIdEquipement, pHorodateDebut, pHorodateFin);
	}

	/**
	 * Demande de r�cup�ration des propri�t�s d'un �quipement
	 * 
	 * @param pHost
	 *            l'adresse IP du serveur REST
	 * @param pPort
	 *            le port TCP utilis� par le serveur
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'exp�diteur : peut �tre vide
	 * @param pReferenceCommande
	 *            la r�f�rence unique de la demande : peut �tre vide
	 * @param pIdEquipement
	 *            l'id de l'�quipement concern�
	 * @return les propri�t�s d'un �quipement, ou null si probl�me
	 */
	public static MessageProprietesEquipementMqttRest requeteDemandeProprietesEquipement(String pHost, int pPort,
			String pIdentifiantExpediteur, String pReferenceCommande, String pIdEquipement) {
		return ServiceRequetesInformationPegase.requeteDemandeProprietesEquipement(pHost, pPort, pIdentifiantExpediteur,
				pReferenceCommande, pIdEquipement);
	}

	/**
	 * Demande de r�cup�ration des propri�t�s des �quipements
	 * 
	 * @param pHost
	 *            l'adresse IP du serveur REST
	 * @param pPort
	 *            le port TCP utilis� par le serveur
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'exp�diteur : peut �tre vide
	 * @param pReferenceCommande
	 *            la r�f�rence unique de la demande : peut �tre vide
	 * @return les propri�t�s des �quipements
	 */
	public static List<MessageProprietesEquipementMqttRest> requeteDemandeProprietesEquipements(String pHost, int pPort,
			String pIdentifiantExpediteur, String pReferenceCommande) {
		return ServiceRequetesInformationPegase.requeteDemandeProprietesEquipements(pHost, pPort,
				pIdentifiantExpediteur, pReferenceCommande);
	}

	/**
	 * Demande de r�cup�ration des propri�t�s d'une armoire
	 * 
	 * @param pHost
	 *            l'adresse IP du serveur REST
	 * @param pPort
	 *            le port TCP utilis� par le serveur
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'exp�diteur : peut �tre vide
	 * @param pReferenceCommande
	 *            la r�f�rence unique de la demande : peut �tre vide
	 * @param pIdArmoire
	 *            l'id de l'armoire concern�
	 * @return les propri�t�s d'une armoire, ou null si probl�me
	 */
	public static MessageProprietesArmoireMqttRest requeteDemandeProprietesArmoire(String pHost, int pPort,
			String pIdentifiantExpediteur, String pReferenceCommande, String pIdArmoire) {
		return ServiceRequetesInformationPegase.requeteDemandeProprietesArmoire(pHost, pPort, pIdentifiantExpediteur,
				pReferenceCommande, pIdArmoire);
	}

	/**
	 * Demande de r�cup�ration des propri�t�s des armoires
	 * 
	 * @param pHost
	 *            l'adresse IP du serveur REST
	 * @param pPort
	 *            le port TCP utilis� par le serveur
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'exp�diteur : peut �tre vide
	 * @param pReferenceCommande
	 *            la r�f�rence unique de la demande : peut �tre vide
	 * @return les propri�t�s des �quipements
	 */
	public static List<MessageProprietesArmoireMqttRest> requeteDemandeProprietesArmoires(String pHost, int pPort,
			String pIdentifiantExpediteur, String pReferenceCommande) {
		return ServiceRequetesInformationPegase.requeteDemandeProprietesArmoires(pHost, pPort, pIdentifiantExpediteur,
				pReferenceCommande);
	}

	/**
	 * Demande d'obtention de la liste des identifiants des sc�narios
	 * 
	 * @param pHost
	 *            l'adresse IP du serveur REST
	 * @param pPort
	 *            le port TCP utilis� par le serveur
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'exp�diteur : peut �tre vide
	 * @param pReferenceCommande
	 *            la r�f�rence unique de la demande : peut �tre vide
	 * @return la liste des identifiants des sc�narios
	 */
	public static List<String> requeteDemandeIdentifiantsScenarios(String pHost, int pPort,
			String pIdentifiantExpediteur, String pReferenceCommande) {
		return ServiceRequetesScenario.requeteDemandeIdentifiantsScenarios(pHost, pPort, pIdentifiantExpediteur,
				pReferenceCommande);
	}

	/**
	 * Demande un sc�nario en particulier
	 * 
	 * @param pHost
	 *            l'adresse IP du serveur REST
	 * @param pPort
	 *            le port TCP utilis� par le serveur
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'exp�diteur : peut �tre vide
	 * @param pReferenceCommande
	 *            la r�f�rence unique de la demande : peut �tre vide
	 * @param pIdScenario
	 *            l'identifiant du sc�nario � r�cup�rer
	 * @return le sc�nario demand�, ou null si probl�me
	 */
	public static MessageScenarioMqttRest requeteDemandeScenario(String pHost, int pPort, String pIdentifiantExpediteur,
			String pReferenceCommande, String pIdScenario) {
		return ServiceRequetesScenario.requeteDemandeScenario(pHost, pPort, pIdentifiantExpediteur, pReferenceCommande,
				pIdScenario);
	}

	/**
	 * Demande la liste des sc�narios
	 * 
	 * @param pHost
	 *            l'adresse IP du serveur REST
	 * @param pPort
	 *            le port TCP utilis� par le serveur
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'exp�diteur : peut �tre vide
	 * @param pReferenceCommande
	 *            la r�f�rence unique de la demande : peut �tre vide
	 * @return la liste des identifiants des sc�narios
	 */
	public static List<MessageScenarioMqttRest> requeteDemandeScenarios(String pHost, int pPort,
			String pIdentifiantExpediteur, String pReferenceCommande) {
		return ServiceRequetesScenario.requeteDemandeScenarios(pHost, pPort, pIdentifiantExpediteur,
				pReferenceCommande);
	}

	/**
	 * Demande de pilotage d'un sc�nario, selon son identifiant
	 * 
	 * @param pHost
	 *            l'adresse IP du serveur REST
	 * @param pPort
	 *            le port TCP utilis� par le serveur
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'exp�diteur : peut �tre vide
	 * @param pReferenceCommande
	 *            la r�f�rence unique de la demande : peut �tre vide
	 * @param pIdScenario
	 *            l'identifiant du sc�nario � piloter
	 */
	public static void requetePilotageScenario(String pHost, int pPort, String pIdentifiantExpediteur,
			String pReferenceCommande, String pIdScenario) {
		ServiceRequetesPilotage.requetePilotageScenario(pHost, pPort, pIdentifiantExpediteur, pReferenceCommande,
				pIdScenario);
	}

	/**
	 * Demande de pilotage d'un sc�nario, en fournissant un message sc�nario
	 * temporaire : non sauvegard�, avec identifiant vide
	 * 
	 * @param pHost
	 *            l'adresse IP du serveur REST
	 * @param pPort
	 *            le port TCP utilis� par le serveur
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'exp�diteur : peut �tre vide
	 * @param pReferenceCommande
	 *            la r�f�rence unique de la demande : peut �tre vide
	 * @param pScenarioTemporaire
	 *            le sc�nario temporaire � piloter
	 */
	public static void requetePilotageScenario(String pHost, int pPort, String pIdentifiantExpediteur,
			String pReferenceCommande, MessageScenarioMqttRest pScenarioTemporaire) {
		ServiceRequetesPilotage.requetePilotageScenario(pHost, pPort, pIdentifiantExpediteur, pReferenceCommande,
				pScenarioTemporaire);
	}

	/**
	 * Demande de pilotage d'une liste de messages dans un sc�nario
	 * 
	 * @param pHost
	 *            l'adresse IP du serveur REST
	 * @param pPort
	 *            le port TCP utilis� par le serveur
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'exp�diteur : peut �tre vide
	 * @param pReferenceCommande
	 *            la r�f�rence unique de la demande : peut �tre vide
	 * @param pIdScenario
	 *            l'identifiant du sc�nario concern�
	 * @param pMessagesAPiloter
	 *            les messages � piloter dans le sc�nario
	 */
	public static void requetePilotageScenario(String pHost, int pPort, String pIdentifiantExpediteur,
			String pReferenceCommande, String pIdScenario,
			List<IMessageAffichageEquipementMqttRest> pMessagesAPiloter) {
		ServiceRequetesPilotage.requetePilotageScenario(pHost, pPort, pIdentifiantExpediteur, pReferenceCommande,
				pIdScenario, pMessagesAPiloter);
	}

	/**
	 * Demande de pilotage d'un PMV
	 * 
	 * @param pHost
	 *            l'adresse IP du serveur REST
	 * @param pPort
	 *            le port TCP utilis� par le serveur
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'exp�diteur : peut �tre vide
	 * @param pReferenceCommande
	 *            la r�f�rence unique de la demande : peut �tre vide
	 * @param pIdEquipement
	 *            l'identifiant de l'�quipement � piloter
	 * @param pMessageAPiloter
	 *            le message � piloter
	 */
	public static void requetePilotagePmv(String pHost, int pPort, String pIdentifiantExpediteur,
			String pReferenceCommande, String pIdEquipement, MessagePmvMqttRest pMessageAPiloter) {
		ServiceRequetesPilotage.requetePilotagePmv(pHost, pPort, pIdentifiantExpediteur, pReferenceCommande,
				pIdEquipement, pMessageAPiloter);
	}

	/**
	 * Demande de pilotage d'un PPLMV
	 * 
	 * @param pHost
	 *            l'adresse IP du serveur REST
	 * @param pPort
	 *            le port TCP utilis� par le serveur
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'exp�diteur : peut �tre vide
	 * @param pReferenceCommande
	 *            la r�f�rence unique de la demande : peut �tre vide
	 * @param pIdEquipement
	 *            l'identifiant de l'�quipement � piloter
	 * @param pMessageAPiloter
	 *            le message � piloter
	 */
	public static void requetePilotagePplmv(String pHost, int pPort, String pIdentifiantExpediteur,
			String pReferenceCommande, String pIdEquipement, MessagePplmvMqttRest pMessageAPiloter) {
		ServiceRequetesPilotage.requetePilotagePplmv(pHost, pPort, pIdentifiantExpediteur, pReferenceCommande,
				pIdEquipement, pMessageAPiloter);
	}

	/**
	 * Demande de pilotage d'un PPAD
	 * 
	 * @param pHost
	 *            l'adresse IP du serveur REST
	 * @param pPort
	 *            le port TCP utilis� par le serveur
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'exp�diteur : peut �tre vide
	 * @param pReferenceCommande
	 *            la r�f�rence unique de la demande : peut �tre vide
	 * @param pIdEquipement
	 *            l'identifiant de l'�quipement � piloter
	 * @param pMessageAPiloter
	 *            le message � piloter
	 */
	public static void requetePilotagePpad(String pHost, int pPort, String pIdentifiantExpediteur,
			String pReferenceCommande, String pIdEquipement, MessagePpadMqttRest pMessageAPiloter) {
		ServiceRequetesPilotage.requetePilotagePpad(pHost, pPort, pIdentifiantExpediteur, pReferenceCommande,
				pIdEquipement, pMessageAPiloter);
	}

	/**
	 * Demande de pilotage d'un Pictogramme
	 * 
	 * @param pHost
	 *            l'adresse IP du serveur REST
	 * @param pPort
	 *            le port TCP utilis� par le serveur
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'exp�diteur : peut �tre vide
	 * @param pReferenceCommande
	 *            la r�f�rence unique de la demande : peut �tre vide
	 * @param pIdEquipement
	 *            l'identifiant de l'�quipement � piloter
	 * @param pMessageAPiloter
	 *            le message � piloter
	 */
	public static void requetePilotagePictogramme(String pHost, int pPort, String pIdentifiantExpediteur,
			String pReferenceCommande, String pIdEquipement, MessagePictogrammeMqttRest pMessageAPiloter) {
		ServiceRequetesPilotage.requetePilotagePictogramme(pHost, pPort, pIdentifiantExpediteur, pReferenceCommande,
				pIdEquipement, pMessageAPiloter);
	}

	/**
	 * Demande de pilotage d'un Feu R24
	 * 
	 * @param pHost
	 *            l'adresse IP du serveur REST
	 * @param pPort
	 *            le port TCP utilis� par le serveur
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'exp�diteur : peut �tre vide
	 * @param pReferenceCommande
	 *            la r�f�rence unique de la demande : peut �tre vide
	 * @param pIdEquipement
	 *            l'identifiant de l'�quipement � piloter
	 * @param pMessageAPiloter
	 *            le message � piloter
	 */
	public static void requetePilotageR24(String pHost, int pPort, String pIdentifiantExpediteur,
			String pReferenceCommande, String pIdEquipement, MessageR24MqttRest pMessageAPiloter) {
		ServiceRequetesPilotage.requetePilotageR24(pHost, pPort, pIdentifiantExpediteur, pReferenceCommande,
				pIdEquipement, pMessageAPiloter);
	}

	/**
	 * Demande de pilotage d'un Prisme
	 * 
	 * @param pHost
	 *            l'adresse IP du serveur REST
	 * @param pPort
	 *            le port TCP utilis� par le serveur
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'exp�diteur : peut �tre vide
	 * @param pReferenceCommande
	 *            la r�f�rence unique de la demande : peut �tre vide
	 * @param pIdEquipement
	 *            l'identifiant de l'�quipement � piloter
	 * @param pMessageAPiloter
	 *            le message � piloter
	 */
	public static void requetePilotagePrisme(String pHost, int pPort, String pIdentifiantExpediteur,
			String pReferenceCommande, String pIdEquipement, MessagePrismeMqttRest pMessageAPiloter) {
		ServiceRequetesPilotage.requetePilotagePrisme(pHost, pPort, pIdentifiantExpediteur, pReferenceCommande,
				pIdEquipement, pMessageAPiloter);
	}

	/**
	 * Demande de pilotage d'une Barri�re
	 * 
	 * @param pHost
	 *            l'adresse IP du serveur REST
	 * @param pPort
	 *            le port TCP utilis� par le serveur
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'exp�diteur : peut �tre vide
	 * @param pReferenceCommande
	 *            la r�f�rence unique de la demande : peut �tre vide
	 * @param pIdEquipement
	 *            l'identifiant de l'�quipement � piloter
	 * @param pMessageAPiloter
	 *            le message � piloter
	 */
	public static void requetePilotageBarriere(String pHost, int pPort, String pIdentifiantExpediteur,
			String pReferenceCommande, String pIdEquipement, MessageBarriereMqttRest pMessageAPiloter) {
		ServiceRequetesPilotage.requetePilotageBarriere(pHost, pPort, pIdentifiantExpediteur, pReferenceCommande,
				pIdEquipement, pMessageAPiloter);
	}

	/**
	 * Demande de pilotage d'un BRA
	 * 
	 * @param pHost
	 *            l'adresse IP du serveur REST
	 * @param pPort
	 *            le port TCP utilis� par le serveur
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'exp�diteur : peut �tre vide
	 * @param pReferenceCommande
	 *            la r�f�rence unique de la demande : peut �tre vide
	 * @param pIdEquipement
	 *            l'identifiant de l'�quipement � piloter
	 * @param pMessageAPiloter
	 *            le message � piloter
	 */
	public static void requetePilotageBra(String pHost, int pPort, String pIdentifiantExpediteur,
			String pReferenceCommande, String pIdEquipement, MessageBraMqttRest pMessageAPiloter) {
		ServiceRequetesPilotage.requetePilotageBra(pHost, pPort, pIdentifiantExpediteur, pReferenceCommande,
				pIdEquipement, pMessageAPiloter);
	}
	
	/**
	 * Demande de pilotage d'un ensemble de messages sur des �quipements
	 * 
	 * @param pHost
	 *            l'adresse IP du serveur REST
	 * @param pPort
	 *            le port TCP utilis� par le serveur
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'exp�diteur : peut �tre vide
	 * @param pReferenceCommande
	 *            la r�f�rence unique de la demande : peut �tre vide
	 * @param pMessages
	 *            les messages � piloter
	 */
	public static void requetePilotageEquipements(String pHost, int pPort, String pIdentifiantExpediteur,
			String pReferenceCommande, List<IMessageAffichageEquipementMqttRest> pMessages) {
		
	}
}
