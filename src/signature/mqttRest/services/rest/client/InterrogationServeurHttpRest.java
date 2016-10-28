package signature.mqttRest.services.rest.client;

import java.util.List;

import signature.mqttRest.objetsPartages.MessageEtatAffichageMqttRest;
import signature.mqttRest.objetsPartages.MessageUtilisateurMqttRest;
import signature.mqttRest.services.rest.etatEtPilotage.FactoryRequetesEtatEtPilotage;
import signature.mqttRest.services.rest.utilisateur.FactoryRequetesUtilisateur;

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
	 * @return l'�tat d'affichage de l'�quipement, ou null si probl�me
	 */
	public static MessageEtatAffichageMqttRest requeteDemandeEtatAffichageEquipement(String pHost, int pPort,
			String pId) {
		return FactoryRequetesEtatEtPilotage.requeteDemandeEtatAffichageEquipement(pHost, pPort, pId);
	}

	/**
	 * Demande d'�tat d'affichage de tous les �quipements
	 * 
	 * @param pHost
	 *            l'adresse IP du serveur REST
	 * @param pPort
	 *            le port TCP utilis� par le serveur
	 * @return l'�tat d'affichage des �quipements, liste vide si probl�me
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
	 *            le port TCP utilis� par le serveur
	 * @return la liste des utilisateurs, liste vide si probl�me
	 */
	public static List<MessageUtilisateurMqttRest> requeteDemandeListeUtilisateurs(String pHost, int pPort) {
		return FactoryRequetesUtilisateur.requeteDemandeListeUtilisateurs(pHost, pPort);
	}

}
