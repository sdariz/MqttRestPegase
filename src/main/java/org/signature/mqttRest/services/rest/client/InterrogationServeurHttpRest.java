package org.signature.mqttRest.services.rest.client;

import java.util.Arrays;
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
import org.signature.mqttRest.objetsPartages.informationPegase.MessageProprietesEquipementWebMqttRest;
import org.signature.mqttRest.objetsPartages.scenario.MessageScenarioMqttRest;
import org.signature.mqttRest.objetsPartages.utilisateur.MessageUtilisateurMqttRest;
import org.signature.mqttRest.services.rest.client.administration.ServiceRequetesAdministration;
import org.signature.mqttRest.services.rest.client.bibliothequePmv.ServiceRequetesBibliothequePmv;
import org.signature.mqttRest.services.rest.client.etatEquipement.ServiceRequetesEtatEquipement;
import org.signature.mqttRest.services.rest.client.evenement.ServiceRequetesEvenement;
import org.signature.mqttRest.services.rest.client.informationPegase.ServiceRequetesInformationPegase;
import org.signature.mqttRest.services.rest.client.interrogationArmoire.ServiceRequetesInterrogationArmoire;
import org.signature.mqttRest.services.rest.client.pilotage.ServiceRequetesPilotage;
import org.signature.mqttRest.services.rest.client.scenario.ServiceRequetesScenario;
import org.signature.mqttRest.services.rest.client.utilisateur.ServiceRequetesUtilisateur;

/**
 * Méthodes utilitaires d'interrogation du serveur HTTP REST, pour récupérer
 * différentes informations.
 * 
 * @author SDARIZCUREN
 *
 */
public class InterrogationServeurHttpRest {

	/**
	 * Demande d'état d'affichage d'un équipement
	 * 
	 * @param pHost
	 *            l'adresse IP du serveur REST
	 * @param pPort
	 *            le port TCP utilisé par le serveur
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'expéditeur : peut être vide
	 * @param pReferenceCommande
	 *            la référence unique de la demande : peut être vide
	 * @param pIdEquipement
	 *            l'id de l'équipement à interroger
	 * @return l'état d'affichage de l'équipement, ou null si problème
	 */
	public MessageEtatAffichageMqttRest requeteDemandeEtatAffichageEquipement(String pHost, int pPort,
			String pIdentifiantExpediteur, String pReferenceCommande, String pIdEquipement) {
		return ServiceRequetesEtatEquipement.requeteDemandeEtatAffichageEquipement(pHost, pPort, pIdentifiantExpediteur,
				pReferenceCommande, pIdEquipement);
	}

	/**
	 * Demande d'état d'affichage de tous les équipements
	 * 
	 * @param pHost
	 *            l'adresse IP du serveur REST
	 * @param pPort
	 *            le port TCP utilisé par le serveur
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'expéditeur : peut être vide
	 * @param pReferenceCommande
	 *            la référence unique de la demande : peut être vide
	 * @return l'état d'affichage des équipements, liste vide si problème
	 */
	public List<MessageEtatAffichageMqttRest> requeteDemandeEtatAffichageEquipements(String pHost, int pPort,
			String pIdentifiantExpediteur, String pReferenceCommande) {
		return ServiceRequetesEtatEquipement.requeteDemandeEtatAffichageEquipements(pHost, pPort,
				pIdentifiantExpediteur, pReferenceCommande);
	}

	/**
	 * Demande d'état technique d'un équipement
	 * 
	 * @param pHost
	 *            l'adresse IP du serveur REST
	 * @param pPort
	 *            le port TCP utilisé par le serveur
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'expéditeur : peut être vide
	 * @param pReferenceCommande
	 *            la référence unique de la demande : peut être vide
	 * @param pIdEquipement
	 *            l'id de l'équipement à interroger
	 * @return l'état technique de l'équipement, ou null si problème
	 */
	public MessageEtatTechniqueMqttRest requeteDemandeEtatTechniqueEquipement(String pHost, int pPort,
			String pIdentifiantExpediteur, String pReferenceCommande, String pIdEquipement) {
		return ServiceRequetesEtatEquipement.requeteDemandeEtatTechniqueEquipement(pHost, pPort, pIdentifiantExpediteur,
				pReferenceCommande, pIdEquipement);
	}

	/**
	 * Demande d'état technique de tous les équipements
	 * 
	 * @param pHost
	 *            l'adresse IP du serveur REST
	 * @param pPort
	 *            le port TCP utilisé par le serveur
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'expéditeur : peut être vide
	 * @param pReferenceCommande
	 *            la référence unique de la demande : peut être vide
	 * @return l'état technique des équipements, liste vide si problème
	 */
	public List<MessageEtatTechniqueMqttRest> requeteDemandeEtatTechniqueEquipements(String pHost, int pPort,
			String pIdentifiantExpediteur, String pReferenceCommande) {
		return ServiceRequetesEtatEquipement.requeteDemandeEtatTechniqueEquipements(pHost, pPort,
				pIdentifiantExpediteur, pReferenceCommande);
	}

	/**
	 * Demande à actualiser l'état d'un équipement, en forçant une interrogation
	 * de l'équipement sur le terrain
	 * 
	 * @param pHost
	 *            l'adresse IP du serveur REST
	 * @param pPort
	 *            le port TCP utilisé par le serveur
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'expéditeur : peut être vide
	 * @param pReferenceCommande
	 *            la référence unique de la demande : peut être vide
	 * @param pIdEquipement
	 *            l'id de l'équipement à interroger
	 */
	public void requeteActualisationEtatEquipement(String pHost, int pPort, String pIdentifiantExpediteur,
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
	 *            le port TCP utilisé par le serveur
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'expéditeur : peut être vide
	 * @param pReferenceCommande
	 *            la référence unique de la demande : peut être vide
	 * @return la liste des utilisateurs, liste vide si problème
	 */
	public List<MessageUtilisateurMqttRest> requeteDemandeListeUtilisateurs(String pHost, int pPort,
			String pIdentifiantExpediteur, String pReferenceCommande) {
		return ServiceRequetesUtilisateur.requeteDemandeListeUtilisateurs(pHost, pPort, pIdentifiantExpediteur,
				pReferenceCommande);
	}

	/**
	 * Demande l'utilisateur connecté
	 * 
	 * @param pHost
	 *            l'adresse IP du serveur REST
	 * @param pPort
	 *            le port TCP utilisé par le serveur
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'expéditeur : peut être vide
	 * @param pReferenceCommande
	 *            la référence unique de la demande : peut être vide
	 * @return l'utilisateur connecté, null si aucun ou si problème
	 */
	public MessageUtilisateurMqttRest requeteDemandeUtilisateurConnecte(String pHost, int pPort,
			String pIdentifiantExpediteur, String pReferenceCommande) {
		return ServiceRequetesUtilisateur.requeteDemandeUtilisateurConnecte(pHost, pPort, pIdentifiantExpediteur,
				pReferenceCommande);
	}

	/**
	 * Demande à valider un identifiant et mot de passe
	 * 
	 * @param pHost
	 *            l'adresse IP du serveur REST
	 * @param pPort
	 *            le port TCP utilisé par le serveur
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'expéditeur : peut être vide
	 * @param pReferenceCommande
	 *            la référence unique de la demande : peut être vide
	 * @param pLogin
	 *            le login à valider
	 * @param pMotPasse
	 *            le mot de passe associé au login
	 * @return true si valide, sinon false
	 */
	public boolean requeteDemandeIdentifiantsValide(String pHost, int pPort, String pIdentifiantExpediteur,
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
	 *            le port TCP utilisé par le serveur
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'expéditeur : peut être vide
	 * @param pReferenceCommande
	 *            la référence unique de la demande : peut être vide
	 * @param pInterdit
	 *            true pour interdire les pilotages sur Pegase
	 */
	public void requeteInterdictionPilotages(String pHost, int pPort, String pIdentifiantExpediteur,
			String pReferenceCommande, boolean pInterdit) {
		ServiceRequetesAdministration.requeteInterdictionPilotages(pHost, pPort, pIdentifiantExpediteur,
				pReferenceCommande, pInterdit);
	}

	/**
	 * Autorise ou interdit les pilotages de certains équipements sur Pegase
	 * 
	 * @param pHost
	 *            l'adresse IP du serveur REST
	 * @param pPort
	 *            le port TCP utilisé par le serveur
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'expéditeur : peut être vide
	 * @param pReferenceCommande
	 *            la référence unique de la demande : peut être vide
	 * @param pIdsEquipements
	 *            les identifiants des équipements concernés
	 * @param pInterdit
	 *            true pour interdire les pilotages sur Pegase
	 */
	public void requeteInterdictionPilotages(String pHost, int pPort, String pIdentifiantExpediteur,
			String pReferenceCommande, List<String> pIdsEquipements, boolean pInterdit) {
		ServiceRequetesAdministration.requeteInterdictionPilotages(pHost, pPort, pIdentifiantExpediteur,
				pReferenceCommande, pIdsEquipements, pInterdit);
	}

	/**
	 * Test de présence du serveur REST
	 * 
	 * @param pHost
	 *            l'adresse IP du serveur REST
	 * @param pPort
	 *            le port TCP utilisé par le serveur
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'expéditeur : peut être vide
	 * @param pReferenceCommande
	 *            la référence unique de la demande : peut être vide
	 * @return true si réponse OK du serveur, false si problème
	 */
	public boolean requeteTestPresence(String pHost, int pPort, String pIdentifiantExpediteur,
			String pReferenceCommande) {
		return ServiceRequetesAdministration.requeteTestPresence(pHost, pPort, pIdentifiantExpediteur,
				pReferenceCommande);
	}

	/**
	 * Demande d'activation ou désactivation d'un bouton
	 * 
	 * @param pHost
	 *            l'adresse IP du serveur REST
	 * @param pPort
	 *            le port TCP utilisé par le serveur
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'expéditeur : peut être vide
	 * @param pReferenceCommande
	 *            la référence unique de la demande : peut être vide
	 * @param pIdBouton
	 *            l'identifiant du bouton
	 * @param pActif
	 *            true pour activer, false pour désactiver
	 */
	public void requeteActivationBouton(String pHost, int pPort, String pIdentifiantExpediteur,
			String pReferenceCommande, String pIdBouton, boolean pActif) {
		ServiceRequetesAdministration.requeteActivationBouton(pHost, pPort, pIdentifiantExpediteur, pReferenceCommande,
				pIdBouton, pActif);
	}

	/**
	 * Demande de lancement de l'action rattachée à un bouton
	 * 
	 * @param pHost
	 *            l'adresse IP du serveur REST
	 * @param pPort
	 *            le port TCP utilisé par le serveur
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'expéditeur : peut être vide
	 * @param pReferenceCommande
	 *            la référence unique de la demande : peut être vide
	 * @param pIdBouton
	 *            l'identifiant du bouton
	 */
	public void requeteLancementActionBouton(String pHost, int pPort, String pIdentifiantExpediteur,
			String pReferenceCommande, String pIdBouton) {
		ServiceRequetesAdministration.requeteLancementActionBouton(pHost, pPort, pIdentifiantExpediteur,
				pReferenceCommande, pIdBouton);
	}

	/**
	 * Demande de lancement de test sur les équipements d'une armoire
	 * 
	 * @param pHost
	 *            l'adresse IP du serveur REST
	 * @param pPort
	 *            le port TCP utilisé par le serveur
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'expéditeur : peut être vide
	 * @param pReferenceCommande
	 *            la référence unique de la demande : peut être vide
	 * @param pIdEquipement
	 *            l'identifiant d'un équipement de l'armoire à tester
	 */
	public void requeteLancementTestEquipementsArmoire(String pHost, int pPort, String pIdentifiantExpediteur,
			String pReferenceCommande, String pIdEquipement) {
		ServiceRequetesInterrogationArmoire.requeteLancementTestEquipementsArmoire(pHost, pPort, pIdentifiantExpediteur,
				pReferenceCommande, pIdEquipement);
	}

	/**
	 * Demande de remise à l'heure d'une armoire
	 * 
	 * @param pHost
	 *            l'adresse IP du serveur REST
	 * @param pPort
	 *            le port TCP utilisé par le serveur
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'expéditeur : peut être vide
	 * @param pReferenceCommande
	 *            la référence unique de la demande : peut être vide
	 * @param pIdEquipement
	 *            l'identifiant d'un équipement de l'armoire à remettre à
	 *            l'heure
	 */
	public void requeteRemiseHeureArmoire(String pHost, int pPort, String pIdentifiantExpediteur,
			String pReferenceCommande, String pIdEquipement) {
		ServiceRequetesInterrogationArmoire.requeteRemiseHeureArmoire(pHost, pPort, pIdentifiantExpediteur,
				pReferenceCommande, pIdEquipement);
	}

	/**
	 * Envoi d'une demande à une armoire d'une armoire
	 * 
	 * @param pHost
	 *            l'adresse IP du serveur REST
	 * @param pPort
	 *            le port TCP utilisé par le serveur
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'expéditeur : peut être vide
	 * @param pReferenceCommande
	 *            la référence unique de la demande : peut être vide
	 * @param pIdEquipement
	 *            l'identifiant d'un équipement de l'armoire à interroger
	 * @param pTrame
	 *            la trame à envoyer à l'armoire
	 * @return la réponse de l'armoire
	 */
	public String requeteDemandeArmoire(String pHost, int pPort, String pIdentifiantExpediteur,
			String pReferenceCommande, String pIdEquipement, String pTrame) {
		return ServiceRequetesInterrogationArmoire.requeteDemandeArmoire(pHost, pPort, pIdentifiantExpediteur,
				pReferenceCommande, pIdEquipement, pTrame);
	}

	/**
	 * Demande la liste des catégories de la bibliothèque PMV
	 * 
	 * @param pHost
	 *            l'adresse IP du serveur REST
	 * @param pPort
	 *            le port TCP utilisé par le serveur
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'expéditeur : peut être vide
	 * @param pReferenceCommande
	 *            la référence unique de la demande : peut être vide
	 * @return la liste des catégories
	 */
	public List<String> requeteDemandeCategoriesBibliothequePmv(String pHost, int pPort,
			String pIdentifiantExpediteur, String pReferenceCommande) {
		return ServiceRequetesBibliothequePmv.requeteDemandeCategoriesBibliothequePmv(pHost, pPort,
				pIdentifiantExpediteur, pReferenceCommande);
	}

	/**
	 * Demande la liste des noms des messages pour une catégorie de la
	 * bibliothèque PMV
	 * 
	 * @param pHost
	 *            l'adresse IP du serveur REST
	 * @param pPort
	 *            le port TCP utilisé par le serveur
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'expéditeur : peut être vide
	 * @param pReferenceCommande
	 *            la référence unique de la demande : peut être vide
	 * @param pCategorie
	 *            le nom de la catégorie
	 * @return la liste noms des messages pour la catégorie demandée
	 */
	public List<String> requeteDemandeMessagesDansCategorieBibliothequePmv(String pHost, int pPort,
			String pIdentifiantExpediteur, String pReferenceCommande, String pCategorie) {
		return ServiceRequetesBibliothequePmv.requeteDemandeMessagesDansCategorieBibliothequePmv(pHost, pPort,
				pIdentifiantExpediteur, pReferenceCommande, pCategorie);
	}

	/**
	 * Demande un message de la bibliothèque PMV
	 * 
	 * @param pHost
	 *            l'adresse IP du serveur REST
	 * @param pPort
	 *            le port TCP utilisé par le serveur
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'expéditeur : peut être vide
	 * @param pReferenceCommande
	 *            la référence unique de la demande : peut être vide
	 * @param pCategorie
	 *            la catégorie concernée
	 * @param pNom le nom du message dans la catégorie
	 * @return le message correspondant, ou null si problème
	 */
	public MessagePmvMqttRest requeteDemandeMessageBibliothequePmv(String pHost, int pPort,
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
	 *            le port TCP utilisé par le serveur
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
	 * @return la liste des alarmes
	 */
	public List<MessageAlarmeMqttRest> requeteDemandeListeAlarmes(String pHost, int pPort,
			String pIdentifiantExpediteur, String pReferenceCommande, String pIdEquipement, Date pHorodateDebut,
			Date pHorodateFin, boolean pActiveSeul) {
		return ServiceRequetesEvenement.requeteDemandeListeAlarmes(pHost, pPort, pIdentifiantExpediteur,
				pReferenceCommande, pIdEquipement, pHorodateDebut, pHorodateFin, pActiveSeul);
	}

	/**
	 * Lance une demande pour connaître l'état d'affichage d'un équipement à une
	 * date donnée
	 * 
	 * @param pHost
	 *            l'adresse IP du serveur REST
	 * @param pPort
	 *            le port TCP utilisé par le serveur
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'expéditeur : peut être vide
	 * @param pReferenceCommande
	 *            la référence unique de la demande : peut être vide
	 * @param pIdEquipement
	 *            l'id de l'équipement concerné
	 * @param pHorodate
	 *            l'horodate de l'évènement
	 * @return l'état d'affichage ou null si problème
	 */
	public IMessageAffichageEquipementMqttRest requeteDemandeEtatAffichageEquipementPourDate(String pHost,
			int pPort, String pIdentifiantExpediteur, String pReferenceCommande, String pIdEquipement, Date pHorodate) {
		return ServiceRequetesEvenement.requeteDemandeEtatAffichageEquipementPourDate(pHost, pPort,
				pIdentifiantExpediteur, pReferenceCommande, pIdEquipement, pHorodate);
	}

	/**
	 * Lance une demande pour connaître l'état d'affichage d'un équipement entre
	 * deux dates
	 * 
	 * @param pHost
	 *            l'adresse IP du serveur REST
	 * @param pPort
	 *            le port TCP utilisé par le serveur
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'expéditeur : peut être vide
	 * @param pReferenceCommande
	 *            la référence unique de la demande : peut être vide
	 * @param pIdEquipement
	 *            l'id de l'équipement concerné
	 * @param pHorodateDebut
	 *            l'horodate de début
	 * @param pHorodateFin
	 *            l'horodate de fin
	 * @return les états d'affichage de l'équipement
	 */
	public List<IMessageAffichageEquipementMqttRest> requeteDemandeEtatAffichageEquipementEntreDeuxDates(
			String pHost, int pPort, String pIdentifiantExpediteur, String pReferenceCommande, String pIdEquipement,
			Date pHorodateDebut, Date pHorodateFin) {
		return ServiceRequetesEvenement.requeteDemandeEtatAffichageEquipementEntreDeuxDates(pHost, pPort,
				pIdentifiantExpediteur, pReferenceCommande, pIdEquipement, pHorodateDebut, pHorodateFin);
	}

	/**
	 * Demande de récupération des propriétés d'un équipement
	 * 
	 * @param pHost
	 *            l'adresse IP du serveur REST
	 * @param pPort
	 *            le port TCP utilisé par le serveur
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'expéditeur : peut être vide
	 * @param pReferenceCommande
	 *            la référence unique de la demande : peut être vide
	 * @param pIdEquipement
	 *            l'id de l'équipement concerné
	 * @return les propriétés d'un équipement, ou null si problème
	 */
	public MessageProprietesEquipementMqttRest requeteDemandeProprietesEquipement(String pHost, int pPort,
			String pIdentifiantExpediteur, String pReferenceCommande, String pIdEquipement) {
		return ServiceRequetesInformationPegase.requeteDemandeProprietesEquipement(pHost, pPort, pIdentifiantExpediteur,
				pReferenceCommande, pIdEquipement);
	}

	/**
	 * Demande de récupération des propriétés des équipements
	 * 
	 * @param pHost
	 *            l'adresse IP du serveur REST
	 * @param pPort
	 *            le port TCP utilisé par le serveur
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'expéditeur : peut être vide
	 * @param pReferenceCommande
	 *            la référence unique de la demande : peut être vide
	 * @return les propriétés des équipements
	 */
	public List<MessageProprietesEquipementMqttRest> requeteDemandeProprietesEquipements(String pHost, int pPort,
			String pIdentifiantExpediteur, String pReferenceCommande) {
		return ServiceRequetesInformationPegase.requeteDemandeProprietesEquipements(pHost, pPort,
				pIdentifiantExpediteur, pReferenceCommande);
	}

	/**
	 * Demande de récupération des propriétés d'une armoire
	 * 
	 * @param pHost
	 *            l'adresse IP du serveur REST
	 * @param pPort
	 *            le port TCP utilisé par le serveur
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'expéditeur : peut être vide
	 * @param pReferenceCommande
	 *            la référence unique de la demande : peut être vide
	 * @param pIdEquipement
	 *            l'identifiant d'un équipement de l'armoire à interroger
	 * @return les propriétés d'une armoire, ou null si problème
	 */
	public MessageProprietesArmoireMqttRest requeteDemandeProprietesArmoire(String pHost, int pPort,
			String pIdentifiantExpediteur, String pReferenceCommande, String pIdEquipement) {
		return ServiceRequetesInformationPegase.requeteDemandeProprietesArmoire(pHost, pPort, pIdentifiantExpediteur,
				pReferenceCommande, pIdEquipement);
	}

	/**
	 * Demande de récupération des propriétés des armoires
	 * 
	 * @param pHost
	 *            l'adresse IP du serveur REST
	 * @param pPort
	 *            le port TCP utilisé par le serveur
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'expéditeur : peut être vide
	 * @param pReferenceCommande
	 *            la référence unique de la demande : peut être vide
	 * @return les propriétés des équipements
	 */
	public List<MessageProprietesArmoireMqttRest> requeteDemandeProprietesArmoires(String pHost, int pPort,
			String pIdentifiantExpediteur, String pReferenceCommande) {
		return ServiceRequetesInformationPegase.requeteDemandeProprietesArmoires(pHost, pPort, pIdentifiantExpediteur,
				pReferenceCommande);
	}
	
	/**
	 * Demande de récupération des propriétés d'un équipement web : synoptiques, positions, ...
	 * 
	 * @param pHost
	 *            l'adresse IP du serveur REST
	 * @param pPort
	 *            le port TCP utilisé par le serveur
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'expéditeur : peut être vide
	 * @param pReferenceCommande
	 *            la référence unique de la demande : peut être vide
	 * @param pIdEquipement
	 *            l'id de l'équipement concerné
	 * @return les propriétés d'un équipement web, ou null si problème
	 */
	public MessageProprietesEquipementWebMqttRest requeteDemandeProprietesEquipementWeb(String pHost, int pPort,
			String pIdentifiantExpediteur, String pReferenceCommande, String pIdEquipement) {
		return ServiceRequetesInformationPegase.requeteDemandeProprietesEquipementWeb(pHost, pPort, pIdentifiantExpediteur,
				pReferenceCommande, pIdEquipement);
	}

	/**
	 * Demande de récupération des propriétés des équipements web : synoptiques, positions, ...
	 * 
	 * @param pHost
	 *            l'adresse IP du serveur REST
	 * @param pPort
	 *            le port TCP utilisé par le serveur
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'expéditeur : peut être vide
	 * @param pReferenceCommande
	 *            la référence unique de la demande : peut être vide
	 * @return les propriétés des équipements web
	 */
	public List<MessageProprietesEquipementWebMqttRest> requeteDemandeProprietesEquipementsWeb(String pHost, int pPort,
			String pIdentifiantExpediteur, String pReferenceCommande) {
		return ServiceRequetesInformationPegase.requeteDemandeProprietesEquipementsWeb(pHost, pPort,
				pIdentifiantExpediteur, pReferenceCommande);
	}

	/**
	 * Demande d'obtention de la liste des identifiants des scénarios
	 * 
	 * @param pHost
	 *            l'adresse IP du serveur REST
	 * @param pPort
	 *            le port TCP utilisé par le serveur
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'expéditeur : peut être vide
	 * @param pReferenceCommande
	 *            la référence unique de la demande : peut être vide
	 * @return la liste des identifiants des scénarios
	 */
	public List<String> requeteDemandeIdentifiantsScenarios(String pHost, int pPort,
			String pIdentifiantExpediteur, String pReferenceCommande) {
		return ServiceRequetesScenario.requeteDemandeIdentifiantsScenarios(pHost, pPort, pIdentifiantExpediteur,
				pReferenceCommande);
	}

	/**
	 * Demande un scénario en particulier
	 * 
	 * @param pHost
	 *            l'adresse IP du serveur REST
	 * @param pPort
	 *            le port TCP utilisé par le serveur
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'expéditeur : peut être vide
	 * @param pReferenceCommande
	 *            la référence unique de la demande : peut être vide
	 * @param pIdScenario
	 *            l'identifiant du scénario à récupérer
	 * @return le scénario demandé, ou null si problème
	 */
	public MessageScenarioMqttRest requeteDemandeScenario(String pHost, int pPort, String pIdentifiantExpediteur,
			String pReferenceCommande, String pIdScenario) {
		return ServiceRequetesScenario.requeteDemandeScenario(pHost, pPort, pIdentifiantExpediteur, pReferenceCommande,
				pIdScenario);
	}

	/**
	 * Demande la liste des scénarios
	 * 
	 * @param pHost
	 *            l'adresse IP du serveur REST
	 * @param pPort
	 *            le port TCP utilisé par le serveur
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'expéditeur : peut être vide
	 * @param pReferenceCommande
	 *            la référence unique de la demande : peut être vide
	 * @return la liste des identifiants des scénarios
	 */
	public List<MessageScenarioMqttRest> requeteDemandeScenarios(String pHost, int pPort,
			String pIdentifiantExpediteur, String pReferenceCommande) {
		return ServiceRequetesScenario.requeteDemandeScenarios(pHost, pPort, pIdentifiantExpediteur,
				pReferenceCommande);
	}

	/**
	 * Demande de pilotage d'un scénario, selon son identifiant
	 * 
	 * @param pHost
	 *            l'adresse IP du serveur REST
	 * @param pPort
	 *            le port TCP utilisé par le serveur
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'expéditeur : peut être vide
	 * @param pReferenceCommande
	 *            la référence unique de la demande : peut être vide
	 * @param pIdScenario
	 *            l'identifiant du scénario à piloter
	 */
	public void requetePilotageScenario(String pHost, int pPort, String pIdentifiantExpediteur,
			String pReferenceCommande, String pIdScenario) {
		ServiceRequetesPilotage.requetePilotageScenario(pHost, pPort, pIdentifiantExpediteur, pReferenceCommande,
				pIdScenario);
	}

	/**
	 * Demande de pilotage d'un scénario, en fournissant un message scénario
	 * temporaire : non sauvegardé, avec identifiant vide
	 * 
	 * @param pHost
	 *            l'adresse IP du serveur REST
	 * @param pPort
	 *            le port TCP utilisé par le serveur
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'expéditeur : peut être vide
	 * @param pReferenceCommande
	 *            la référence unique de la demande : peut être vide
	 * @param pScenarioTemporaire
	 *            le scénario temporaire à piloter
	 */
	public void requetePilotageScenario(String pHost, int pPort, String pIdentifiantExpediteur,
			String pReferenceCommande, MessageScenarioMqttRest pScenarioTemporaire) {
		ServiceRequetesPilotage.requetePilotageScenario(pHost, pPort, pIdentifiantExpediteur, pReferenceCommande,
				pScenarioTemporaire);
	}

	/**
	 * Demande de pilotage d'une liste de messages dans un scénario
	 * 
	 * @param pHost
	 *            l'adresse IP du serveur REST
	 * @param pPort
	 *            le port TCP utilisé par le serveur
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'expéditeur : peut être vide
	 * @param pReferenceCommande
	 *            la référence unique de la demande : peut être vide
	 * @param pIdScenario
	 *            l'identifiant du scénario concerné
	 * @param pMessagesAPiloter
	 *            les messages à piloter dans le scénario
	 */
	public void requetePilotageScenario(String pHost, int pPort, String pIdentifiantExpediteur,
			String pReferenceCommande, String pIdScenario,
			List<IMessageAffichageEquipementMqttRest> pMessagesAPiloter) {
		ServiceRequetesPilotage.requetePilotageScenario(pHost, pPort, pIdentifiantExpediteur, pReferenceCommande,
				pIdScenario, pMessagesAPiloter);
	}

	/**
	 * Demande de pilotage d'un équipement
	 * 
	 * @param pHost
	 *            l'adresse IP du serveur REST
	 * @param pPort
	 *            le port TCP utilisé par le serveur
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'expéditeur : peut être vide
	 * @param pReferenceCommande
	 *            la référence unique de la demande : peut être vide
	 * @param pIdEquipement
	 *            l'identifiant de l'équipement à piloter
	 * @param pMessageAPiloter
	 *            le message à piloter
	 */
	public void requetePilotageEquipement(String pHost, int pPort, String pIdentifiantExpediteur,
			String pReferenceCommande, String pIdEquipement, IMessageAffichageEquipementMqttRest pMessageAPiloter) {
		if (pMessageAPiloter == null) {
			return;
		}

		switch (pMessageAPiloter.getTypeEquipement()) {
		case PMV:
			ServiceRequetesPilotage.requetePilotagePmv(pHost, pPort, pIdentifiantExpediteur, pReferenceCommande,
					pIdEquipement, (MessagePmvMqttRest) pMessageAPiloter);
			break;
		case PPLMV:
			ServiceRequetesPilotage.requetePilotagePplmv(pHost, pPort, pIdentifiantExpediteur, pReferenceCommande,
					pIdEquipement, (MessagePplmvMqttRest) pMessageAPiloter);
			break;
		case PPAD:
			ServiceRequetesPilotage.requetePilotagePpad(pHost, pPort, pIdentifiantExpediteur, pReferenceCommande,
					pIdEquipement, (MessagePpadMqttRest) pMessageAPiloter);
			break;
		case PICTOGRAMME:
			ServiceRequetesPilotage.requetePilotagePictogramme(pHost, pPort, pIdentifiantExpediteur, pReferenceCommande,
					pIdEquipement, (MessagePictogrammeMqttRest) pMessageAPiloter);
			break;
		case R24:
			ServiceRequetesPilotage.requetePilotageR24(pHost, pPort, pIdentifiantExpediteur, pReferenceCommande,
					pIdEquipement, (MessageR24MqttRest) pMessageAPiloter);
			break;
		case PRISME:
			ServiceRequetesPilotage.requetePilotagePrisme(pHost, pPort, pIdentifiantExpediteur, pReferenceCommande,
					pIdEquipement, (MessagePrismeMqttRest) pMessageAPiloter);
			break;
		case BARRIERE:
			ServiceRequetesPilotage.requetePilotageBarriere(pHost, pPort, pIdentifiantExpediteur, pReferenceCommande,
					pIdEquipement, (MessageBarriereMqttRest) pMessageAPiloter);
			break;
		case BRA:
			ServiceRequetesPilotage.requetePilotageBra(pHost, pPort, pIdentifiantExpediteur, pReferenceCommande,
					pIdEquipement, (MessageBraMqttRest) pMessageAPiloter);
			break;
		case FEU_REGULATION:
			ServiceRequetesPilotage.requetePilotageFeuRegulation(pHost, pPort, pIdentifiantExpediteur, pReferenceCommande,
					pIdEquipement, (MessageFeuRegulationMqttRest) pMessageAPiloter);
			break;
		}
	}

	/**
	 * Demande de pilotage d'un PMV
	 * 
	 * @param pHost
	 *            l'adresse IP du serveur REST
	 * @param pPort
	 *            le port TCP utilisé par le serveur
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'expéditeur : peut être vide
	 * @param pReferenceCommande
	 *            la référence unique de la demande : peut être vide
	 * @param pIdEquipement
	 *            l'identifiant de l'équipement à piloter
	 * @param pMessageAPiloter
	 *            le message à piloter
	 */
	public void requetePilotagePmv(String pHost, int pPort, String pIdentifiantExpediteur,
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
	 *            le port TCP utilisé par le serveur
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'expéditeur : peut être vide
	 * @param pReferenceCommande
	 *            la référence unique de la demande : peut être vide
	 * @param pIdEquipement
	 *            l'identifiant de l'équipement à piloter
	 * @param pMessageAPiloter
	 *            le message à piloter
	 */
	public void requetePilotagePplmv(String pHost, int pPort, String pIdentifiantExpediteur,
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
	 *            le port TCP utilisé par le serveur
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'expéditeur : peut être vide
	 * @param pReferenceCommande
	 *            la référence unique de la demande : peut être vide
	 * @param pIdEquipement
	 *            l'identifiant de l'équipement à piloter
	 * @param pMessageAPiloter
	 *            le message à piloter
	 */
	public void requetePilotagePpad(String pHost, int pPort, String pIdentifiantExpediteur,
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
	 *            le port TCP utilisé par le serveur
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'expéditeur : peut être vide
	 * @param pReferenceCommande
	 *            la référence unique de la demande : peut être vide
	 * @param pIdEquipement
	 *            l'identifiant de l'équipement à piloter
	 * @param pMessageAPiloter
	 *            le message à piloter
	 */
	public void requetePilotagePictogramme(String pHost, int pPort, String pIdentifiantExpediteur,
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
	 *            le port TCP utilisé par le serveur
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'expéditeur : peut être vide
	 * @param pReferenceCommande
	 *            la référence unique de la demande : peut être vide
	 * @param pIdEquipement
	 *            l'identifiant de l'équipement à piloter
	 * @param pMessageAPiloter
	 *            le message à piloter
	 */
	public void requetePilotageR24(String pHost, int pPort, String pIdentifiantExpediteur,
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
	 *            le port TCP utilisé par le serveur
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'expéditeur : peut être vide
	 * @param pReferenceCommande
	 *            la référence unique de la demande : peut être vide
	 * @param pIdEquipement
	 *            l'identifiant de l'équipement à piloter
	 * @param pMessageAPiloter
	 *            le message à piloter
	 */
	public void requetePilotagePrisme(String pHost, int pPort, String pIdentifiantExpediteur,
			String pReferenceCommande, String pIdEquipement, MessagePrismeMqttRest pMessageAPiloter) {
		ServiceRequetesPilotage.requetePilotagePrisme(pHost, pPort, pIdentifiantExpediteur, pReferenceCommande,
				pIdEquipement, pMessageAPiloter);
	}

	/**
	 * Demande de pilotage d'une Barrière
	 * 
	 * @param pHost
	 *            l'adresse IP du serveur REST
	 * @param pPort
	 *            le port TCP utilisé par le serveur
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'expéditeur : peut être vide
	 * @param pReferenceCommande
	 *            la référence unique de la demande : peut être vide
	 * @param pIdEquipement
	 *            l'identifiant de l'équipement à piloter
	 * @param pMessageAPiloter
	 *            le message à piloter
	 */
	public void requetePilotageBarriere(String pHost, int pPort, String pIdentifiantExpediteur,
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
	 *            le port TCP utilisé par le serveur
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'expéditeur : peut être vide
	 * @param pReferenceCommande
	 *            la référence unique de la demande : peut être vide
	 * @param pIdEquipement
	 *            l'identifiant de l'équipement à piloter
	 * @param pMessageAPiloter
	 *            le message à piloter
	 */
	public void requetePilotageBra(String pHost, int pPort, String pIdentifiantExpediteur,
			String pReferenceCommande, String pIdEquipement, MessageBraMqttRest pMessageAPiloter) {
		ServiceRequetesPilotage.requetePilotageBra(pHost, pPort, pIdentifiantExpediteur, pReferenceCommande,
				pIdEquipement, pMessageAPiloter);
	}
	
	/**
	 * Demande de pilotage d'un Feu de Regulation
	 * 
	 * @param pHost
	 *            l'adresse IP du serveur REST
	 * @param pPort
	 *            le port TCP utilisé par le serveur
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'expéditeur : peut être vide
	 * @param pReferenceCommande
	 *            la référence unique de la demande : peut être vide
	 * @param pIdEquipement
	 *            l'identifiant de l'équipement à piloter
	 * @param pMessageAPiloter
	 *            le message à piloter
	 */
	public void requetePilotageFeuRegulation(String pHost, int pPort, String pIdentifiantExpediteur,
			String pReferenceCommande, String pIdEquipement, MessageFeuRegulationMqttRest pMessageAPiloter) {
		ServiceRequetesPilotage.requetePilotageFeuRegulation(pHost, pPort, pIdentifiantExpediteur, pReferenceCommande,
				pIdEquipement, pMessageAPiloter);
	}

	/**
	 * Demande de pilotage d'un message sur un équipement
	 * 
	 * @param pHost
	 *            l'adresse IP du serveur REST
	 * @param pPort
	 *            le port TCP utilisé par le serveur
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'expéditeur : peut être vide
	 * @param pReferenceCommande
	 *            la référence unique de la demande : peut être vide
	 * @param pMessage
	 *            le message à piloter
	 */
	public void requetePilotageMessage(String pHost, int pPort, String pIdentifiantExpediteur,
			String pReferenceCommande, IMessageAffichageEquipementMqttRest pMessage) {
		requetePilotageMessages(pHost, pPort, pIdentifiantExpediteur, pReferenceCommande, Arrays.asList(pMessage));
	}

	/**
	 * Demande de pilotage d'un ensemble de messages sur des équipements
	 * 
	 * @param pHost
	 *            l'adresse IP du serveur REST
	 * @param pPort
	 *            le port TCP utilisé par le serveur
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'expéditeur : peut être vide
	 * @param pReferenceCommande
	 *            la référence unique de la demande : peut être vide
	 * @param pMessages
	 *            les messages à piloter
	 */
	public void requetePilotageMessages(String pHost, int pPort, String pIdentifiantExpediteur,
			String pReferenceCommande, List<IMessageAffichageEquipementMqttRest> pMessages) {
		ServiceRequetesPilotage.requetePilotageMessages(pHost, pPort, pIdentifiantExpediteur, pReferenceCommande,
				pMessages);
	}

	/**
	 * Demande si un pilotage est en cours sur un équipement
	 * 
	 * @param pHost
	 *            l'adresse IP du serveur REST
	 * @param pPort
	 *            le port TCP utilisé par le serveur
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'expéditeur : peut être vide
	 * @param pReferenceCommande
	 *            la référence unique de la demande : peut être vide
	 * @param pIdEquipement
	 *            l'identifiant de l'équipement à tester
	 * @return true si un pilotage est en cours
	 */
	public boolean requetePilotageEnCours(String pHost, int pPort, String pIdentifiantExpediteur,
			String pReferenceCommande, String pIdEquipement) {
		return ServiceRequetesPilotage.requetePilotageEnCours(pHost, pPort, pIdentifiantExpediteur, pReferenceCommande,
				pIdEquipement);
	}

	/**
	 * Demande si un pilotage est en cours sur un équipement quelconque
	 * 
	 * @param pHost
	 *            l'adresse IP du serveur REST
	 * @param pPort
	 *            le port TCP utilisé par le serveur
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'expéditeur : peut être vide
	 * @param pReferenceCommande
	 *            la référence unique de la demande : peut être vide
	 * @return true si un pilotage est en cours
	 */
	public boolean requetePilotageEnCours(String pHost, int pPort, String pIdentifiantExpediteur,
			String pReferenceCommande) {
		return ServiceRequetesPilotage.requetePilotageEnCours(pHost, pPort, pIdentifiantExpediteur, pReferenceCommande);
	}
}
