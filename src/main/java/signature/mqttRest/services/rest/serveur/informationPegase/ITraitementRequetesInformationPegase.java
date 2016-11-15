package signature.mqttRest.services.rest.serveur.informationPegase;

import java.util.Date;
import java.util.List;

import signature.mqttRest.objetsPartages.etatEtPilotage.MessageAlarmeMqttRest;

/**
 * Traitement des requêtes concernant des demandes d'information sur Pegase
 * 
 * @author SDARIZCUREN
 *
 */
public interface ITraitementRequetesInformationPegase {

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
	 * @return la liste des alarmes sur l'équipement
	 */
	public List<MessageAlarmeMqttRest> traiteDemandeListeAlarmes(String pIdentifiantExpediteur, String pReferenceCommande,
			String pIdEquipement, Date pHorodateDebut, Date pHorodateFin, boolean pActiveSeul);

}
