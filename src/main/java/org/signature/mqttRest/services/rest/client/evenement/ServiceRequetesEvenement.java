package org.signature.mqttRest.services.rest.client.evenement;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.signature.mqttRest.objetsPartages.etatEtPilotage.IMessageAffichageEquipementMqttRest;
import org.signature.mqttRest.objetsPartages.evenement.MessageAlarmeMqttRest;
import org.signature.mqttRest.services.rest.client.ClientHttpRest;
import org.signature.mqttRest.services.rest.serveur.evenement.GestionnaireRoutesEvenement;
import org.signature.mqttRest.util.Util;

/**
 * Requ�tes vers le serveur REST pour r�cup�rer les alarmes et �tats d'affichages, archiv�s en base de donn�es
 * @author SDARIZCUREN
 *
 */
public class ServiceRequetesEvenement {
	
	/**
	 * Envoi au serveur REST une demande de recherche d'alarmes
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
		if (pIdentifiantExpediteur == null) {
			pIdentifiantExpediteur = "";
		}

		if (pReferenceCommande == null) {
			pReferenceCommande = "";
		}

		if (pHorodateFin == null) {
			pHorodateFin = new Date();
		}

		Map<String, String> params = new HashMap<>();
		params.put("idExpediteur", pIdentifiantExpediteur);
		params.put("idCommande", pReferenceCommande);
		params.put("idEquipement", pIdEquipement);
		params.put("horodateDebut", Long.toString(pHorodateDebut.getTime()));
		params.put("horodateFin", Long.toString(pHorodateFin.getTime()));
		params.put("active", Boolean.toString(pActiveSeul));

		String json = ClientHttpRest.envoiRequeteGET(pHost, pPort, GestionnaireRoutesEvenement.LISTE_ALARMES,
				params);
		if (json.length() == 0) {
			return new ArrayList<>();
		}

		// Formatage du retour vers le bon format
		return Util.jsonToListeObjet(json, MessageAlarmeMqttRest.class).stream()
				.map(MessageAlarmeMqttRest.class::cast).collect(Collectors.toList());
	}
	
	/**
	 * Envoi au serveur REST une demande pour conna�tre l'�tat d'affichage d'un �quipement � une
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
	public static IMessageAffichageEquipementMqttRest requeteDemandeEtatAffichageEquipementPourDate(String pHost, int pPort,
			String pIdentifiantExpediteur, String pReferenceCommande, String pIdEquipement, Date pHorodate) {
		if (pIdentifiantExpediteur == null) {
			pIdentifiantExpediteur = "";
		}

		if (pReferenceCommande == null) {
			pReferenceCommande = "";
		}

		if (pHorodate == null) {
			pHorodate = new Date();
		}

		Map<String, String> params = new HashMap<>();
		params.put("idExpediteur", pIdentifiantExpediteur);
		params.put("idCommande", pReferenceCommande);
		params.put("idEquipement", pIdEquipement);
		params.put("horodate", Long.toString(pHorodate.getTime()));

		String json = ClientHttpRest.envoiRequeteGET(pHost, pPort, GestionnaireRoutesEvenement.ETAT_AFFICHAGE_EQUIPEMENT_POUR_DATE,
				params);
		if (json.length() == 0) {
			return null;
		}

		// Formatage du retour vers le bon format
		return (IMessageAffichageEquipementMqttRest) Util.jsonToObjet(json, IMessageAffichageEquipementMqttRest.class);
	}
	
	/**
	 * Envoi au serveur REST une demande pour conna�tre l'�tat d'affichage d'un �quipement entre deux dates
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
	public static List<IMessageAffichageEquipementMqttRest> requeteDemandeEtatAffichageEquipementEntreDeuxDates(String pHost, int pPort,
			String pIdentifiantExpediteur, String pReferenceCommande, String pIdEquipement, Date pHorodateDebut, Date pHorodateFin) {
		if (pIdentifiantExpediteur == null) {
			pIdentifiantExpediteur = "";
		}

		if (pReferenceCommande == null) {
			pReferenceCommande = "";
		}

		if (pHorodateFin == null) {
			pHorodateFin = new Date();
		}

		Map<String, String> params = new HashMap<>();
		params.put("idExpediteur", pIdentifiantExpediteur);
		params.put("idCommande", pReferenceCommande);
		params.put("idEquipement", pIdEquipement);
		params.put("horodateDebut", Long.toString(pHorodateDebut.getTime()));
		params.put("horodateFin", Long.toString(pHorodateFin.getTime()));

		String json = ClientHttpRest.envoiRequeteGET(pHost, pPort, GestionnaireRoutesEvenement.ETAT_AFFICHAGE_EQUIPEMENT_ENTRE_DEUX_DATES,
				params);
		if (json.length() == 0) {
			return new ArrayList<>();
		}

		// Formatage du retour vers le bon format
		return Util.jsonToListeObjet(json, IMessageAffichageEquipementMqttRest.class).stream()
				.map(IMessageAffichageEquipementMqttRest.class::cast).collect(Collectors.toList());
	}
}
