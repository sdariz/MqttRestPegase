package org.signature.mqttRest.services.rest.serveur.evenement;

import java.util.Date;
import java.util.List;

import org.signature.mqttRest.objetsPartages.etatEtPilotage.IMessageAffichageEquipementMqttRest;
import org.signature.mqttRest.objetsPartages.evenement.MessageAlarmeMqttRest;

/**
 * Traitement des requ�tes concernant des �v�nements sur Pegase
 * 
 * @author SDARIZCUREN
 *
 */
public interface ITraitementRequetesEvenement {

	/**
	 * Traite une demande d'obtention de la liste des cat�gories de la
	 * biblioth�que
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
	 * @return la liste des alarmes sur l'�quipement
	 */
	public List<MessageAlarmeMqttRest> traiteDemandeListeAlarmes(String pIdentifiantExpediteur,
			String pReferenceCommande, String pIdEquipement, Date pHorodateDebut, Date pHorodateFin,
			boolean pActiveSeul);

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
	 * @return l'�tat d'affichage de l'�quipement ou null si probl�me
	 */
	public IMessageAffichageEquipementMqttRest traiteDemandeEtatAffichageEquipementPourDate(String pIdentifiantExpediteur,
			String pReferenceCommande, String pIdEquipement, Date pHorodate);

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
	 * @return les �tats d'affichage de l'�quipement
	 */
	public List<IMessageAffichageEquipementMqttRest> traiteDemandeEtatAffichageEquipementEntreDeuxDates(
			String pIdentifiantExpediteur, String pReferenceCommande, String pIdEquipement, Date pHorodateDebut,
			Date pHorodateFin);

}
