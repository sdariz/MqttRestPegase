package signature.mqttRest.services.rest.client;

import java.util.List;

import signature.mqttRest.objetsPartages.etatEtPilotage.MessageEtatAffichageMqttRest;
import signature.mqttRest.objetsPartages.utilisateur.MessageUtilisateurMqttRest;
import signature.mqttRest.services.rest.client.administration.ServiceRequetesAdministration;
import signature.mqttRest.services.rest.client.etatEtPilotage.ServiceRequetesEtatEtPilotage;
import signature.mqttRest.services.rest.client.utilisateur.ServiceRequetesUtilisateur;

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
	 * @param pId
	 *            l'id de l'équipement à interroger
	 * @return l'état d'affichage de l'équipement, ou null si problème
	 */
	public static MessageEtatAffichageMqttRest requeteDemandeEtatAffichageEquipement(String pHost, int pPort,
			String pId) {
		return ServiceRequetesEtatEtPilotage.requeteDemandeEtatAffichageEquipement(pHost, pPort, pId);
	}

	/**
	 * Demande d'état d'affichage de tous les équipements
	 * 
	 * @param pHost
	 *            l'adresse IP du serveur REST
	 * @param pPort
	 *            le port TCP utilisé par le serveur
	 * @return l'état d'affichage des équipements, liste vide si problème
	 */
	public static List<MessageEtatAffichageMqttRest> requeteDemandeEtatAffichageEquipements(String pHost, int pPort) {
		return ServiceRequetesEtatEtPilotage.requeteDemandeEtatAffichageEquipements(pHost, pPort);
	}

	/**
	 * Demande à actualiser l'état d'un équipement, en forçant une interrogation
	 * de l'équipement sur le terrain
	 * 
	 * @param pHost
	 *            l'adresse IP du serveur REST
	 * @param pPort
	 *            le port TCP utilisé par le serveur
	 * @param pId
	 *            l'id de l'équipement à interroger
	 */
	public static void requeteActualisationEtatEquipement(String pHost, int pPort, String pId) {
		ServiceRequetesEtatEtPilotage.requeteActualisationEtatEquipement(pHost, pPort, pId);
	}

	/**
	 * Demande de la liste des utilisateurs
	 * 
	 * @param pHost
	 *            l'adresse IP du serveur REST
	 * @param pPort
	 *            le port TCP utilisé par le serveur
	 * @return la liste des utilisateurs, liste vide si problème
	 */
	public static List<MessageUtilisateurMqttRest> requeteDemandeListeUtilisateurs(String pHost, int pPort) {
		return ServiceRequetesUtilisateur.requeteDemandeListeUtilisateurs(pHost, pPort);
	}

	/**
	 * Demande l'utilisateur connecté
	 * 
	 * @param pHost
	 *            l'adresse IP du serveur REST
	 * @param pPort
	 *            le port TCP utilisé par le serveur
	 * @return l'utilisateur connecté, null si aucun ou si problème
	 */
	public static MessageUtilisateurMqttRest requeteDemandeUtilisateurConnecte(String pHost, int pPort) {
		return ServiceRequetesUtilisateur.requeteDemandeUtilisateurConnecte(pHost, pPort);
	}

	/**
	 * Demande à valider un identifiant et mot de passe
	 * 
	 * @param pHost
	 *            l'adresse IP du serveur REST
	 * @param pPort
	 *            le port TCP utilisé par le serveur
	 * @param pLogin
	 *            le login à valider
	 * @param pMotPasse
	 *            le mot de passe associé au login
	 * @return true si valide, sinon false
	 */
	public static boolean requeteDemandeIdentifiantsValide(String pHost, int pPort, String pLogin, String pMotPasse) {
		return ServiceRequetesUtilisateur.requeteDemandeIdentifiantsValide(pHost, pPort, pLogin, pMotPasse);
	}

	/**
	 * Autorise ou interdit les pilotages sur Pegase
	 * 
	 * @param pHost
	 *            l'adresse IP du serveur REST
	 * @param pPort
	 *            le port TCP utilisé par le serveur
	 * @param pInterdit
	 *            true pour interdire les pilotages sur Pegase
	 */
	public static void requeteInterdictionPilotages(String pHost, int pPort, boolean pInterdit) {
		ServiceRequetesAdministration.requeteInterdictionPilotages(pHost, pPort, pInterdit);
	}

}
