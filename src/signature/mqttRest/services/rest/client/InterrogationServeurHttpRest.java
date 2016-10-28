package signature.mqttRest.services.rest.client;

import java.util.List;

import signature.mqttRest.objetsPartages.MessageEtatAffichageMqttRest;
import signature.mqttRest.objetsPartages.MessageUtilisateurMqttRest;
import signature.mqttRest.services.rest.etatEtPilotage.FactoryRequetesEtatEtPilotage;
import signature.mqttRest.services.rest.utilisateur.FactoryRequetesUtilisateur;

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
		return FactoryRequetesEtatEtPilotage.requeteDemandeEtatAffichageEquipement(pHost, pPort, pId);
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
		return FactoryRequetesEtatEtPilotage.requeteDemandeEtatAffichageEquipements(pHost, pPort);
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
		return FactoryRequetesUtilisateur.requeteDemandeListeUtilisateurs(pHost, pPort);
	}

}
