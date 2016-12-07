package org.signature.mqttRest.services.rest.serveur.evenement;

import java.util.Date;
import java.util.List;

import org.signature.mqttRest.objetsPartages.etatEtPilotage.IMessageAffichageEquipementMqttRest;
import org.signature.mqttRest.objetsPartages.evenement.MessageAlarmeMqttRest;

/**
 * Traitement des requêtes concernant des évènements sur Pegase
 * 
 * @author SDARIZCUREN
 *
 */
public interface ITraitementRequetesEvenement {

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
	public List<MessageAlarmeMqttRest> traiteDemandeListeAlarmes(String pIdentifiantExpediteur,
			String pReferenceCommande, String pIdEquipement, Date pHorodateDebut, Date pHorodateFin,
			boolean pActiveSeul);

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
	 * @return l'état d'affichage de l'équipement ou null si problème
	 */
	public IMessageAffichageEquipementMqttRest traiteDemandeEtatAffichageEquipementPourDate(String pIdentifiantExpediteur,
			String pReferenceCommande, String pIdEquipement, Date pHorodate);

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
	 * @return les états d'affichage de l'équipement
	 */
	public List<IMessageAffichageEquipementMqttRest> traiteDemandeEtatAffichageEquipementEntreDeuxDates(
			String pIdentifiantExpediteur, String pReferenceCommande, String pIdEquipement, Date pHorodateDebut,
			Date pHorodateFin);

}
