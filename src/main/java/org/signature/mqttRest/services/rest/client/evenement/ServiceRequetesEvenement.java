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
 * Requêtes vers le serveur REST pour récupérer les alarmes et états d'affichages, archivés en base de données
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
	 * Envoi au serveur REST une demande pour connaître l'état d'affichage d'un équipement à une
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
	 * Envoi au serveur REST une demande pour connaître l'état d'affichage d'un équipement entre deux dates
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
