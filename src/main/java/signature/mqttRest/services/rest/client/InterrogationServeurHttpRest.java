package signature.mqttRest.services.rest.client;

import java.util.List;

import signature.mqttRest.objetsPartages.etatEtPilotage.MessageEtatAffichageMqttRest;
import signature.mqttRest.objetsPartages.etatEtPilotage.MessageEtatTechniqueMqttRest;
import signature.mqttRest.objetsPartages.utilisateur.MessageUtilisateurMqttRest;
import signature.mqttRest.services.rest.client.administration.ServiceRequetesAdministration;
import signature.mqttRest.services.rest.client.etatEtPilotage.ServiceRequetesEtatEtPilotage;
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
	 * @param pId
	 *            l'id de l'�quipement � interroger
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'exp�diteur : peut �tre vide
	 * @param pReferenceCommande
	 *            la r�f�rence unique de la demande : peut �tre vide
	 * @return l'�tat d'affichage de l'�quipement, ou null si probl�me
	 */
	public static MessageEtatAffichageMqttRest requeteDemandeEtatAffichageEquipement(String pHost, int pPort,
			String pId, String pIdentifiantExpediteur, String pReferenceCommande) {
		return ServiceRequetesEtatEtPilotage.requeteDemandeEtatAffichageEquipement(pHost, pPort, pId,
				pIdentifiantExpediteur, pReferenceCommande);
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
		return ServiceRequetesEtatEtPilotage.requeteDemandeEtatAffichageEquipements(pHost, pPort,
				pIdentifiantExpediteur, pReferenceCommande);
	}
	
	/**
	 * Demande d'�tat technique d'un �quipement
	 * 
	 * @param pHost
	 *            l'adresse IP du serveur REST
	 * @param pPort
	 *            le port TCP utilis� par le serveur
	 * @param pId
	 *            l'id de l'�quipement � interroger
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'exp�diteur : peut �tre vide
	 * @param pReferenceCommande
	 *            la r�f�rence unique de la demande : peut �tre vide
	 * @return l'�tat technique de l'�quipement, ou null si probl�me
	 */
	public static MessageEtatTechniqueMqttRest requeteDemandeEtatTechniqueEquipement(String pHost, int pPort,
			String pId, String pIdentifiantExpediteur, String pReferenceCommande) {
		return ServiceRequetesEtatEtPilotage.requeteDemandeEtatTechniqueEquipement(pHost, pPort, pId,
				pIdentifiantExpediteur, pReferenceCommande);
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
		return ServiceRequetesEtatEtPilotage.requeteDemandeEtatTechniqueEquipements(pHost, pPort,
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
	 * @param pId
	 *            l'id de l'�quipement � interroger
	 */
	public static void requeteActualisationEtatEquipement(String pHost, int pPort, String pId,
			String pIdentifiantExpediteur, String pReferenceCommande) {
		ServiceRequetesEtatEtPilotage.requeteActualisationEtatEquipement(pHost, pPort, pId, pIdentifiantExpediteur,
				pReferenceCommande);
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
	 * @param pLogin
	 *            le login � valider
	 * @param pMotPasse
	 *            le mot de passe associ� au login
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'exp�diteur : peut �tre vide
	 * @param pReferenceCommande
	 *            la r�f�rence unique de la demande : peut �tre vide
	 * @return true si valide, sinon false
	 */
	public static boolean requeteDemandeIdentifiantsValide(String pHost, int pPort, String pLogin, String pMotPasse,
			String pIdentifiantExpediteur, String pReferenceCommande) {
		return ServiceRequetesUtilisateur.requeteDemandeIdentifiantsValide(pHost, pPort, pLogin, pMotPasse,
				pIdentifiantExpediteur, pReferenceCommande);
	}

	/**
	 * Autorise ou interdit les pilotages sur Pegase
	 * 
	 * @param pHost
	 *            l'adresse IP du serveur REST
	 * @param pPort
	 *            le port TCP utilis� par le serveur
	 * @param pInterdit
	 *            true pour interdire les pilotages sur Pegase
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'exp�diteur : peut �tre vide
	 * @param pReferenceCommande
	 *            la r�f�rence unique de la demande : peut �tre vide
	 */
	public static void requeteInterdictionPilotages(String pHost, int pPort, boolean pInterdit,
			String pIdentifiantExpediteur, String pReferenceCommande) {
		ServiceRequetesAdministration.requeteInterdictionPilotages(pHost, pPort, pInterdit, pIdentifiantExpediteur,
				pReferenceCommande);
	}

}
