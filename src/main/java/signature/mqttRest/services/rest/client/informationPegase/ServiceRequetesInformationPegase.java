package signature.mqttRest.services.rest.client.informationPegase;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import signature.mqttRest.objetsPartages.etatEtPilotage.MessageAlarmeMqttRest;
import signature.mqttRest.services.rest.client.ClientHttpRest;
import signature.mqttRest.services.rest.serveur.informationPegase.GestionnaireRoutesInformationPegase;
import signature.mqttRest.util.Util;

/**
 * Requêtes vers le serveur REST pour récupérer des informations sur Pegase
 * 
 * @author SDARIZCUREN
 *
 */
public class ServiceRequetesInformationPegase {

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
	 * @return la liste des catégories
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

		String json = ClientHttpRest.envoiRequeteGET(pHost, pPort, GestionnaireRoutesInformationPegase.LISTE_ALARMES,
				params);
		if (json.length() == 0) {
			return new ArrayList<>();
		}

		// Formatage du retour vers le bon format
		List<MessageAlarmeMqttRest> retour = Util.jsonToListeObjet(json, MessageAlarmeMqttRest.class).stream()
				.map(MessageAlarmeMqttRest.class::cast).collect(Collectors.toList());

		return retour;
	}

}
