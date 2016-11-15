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
 * Requ�tes vers le serveur REST pour r�cup�rer des informations sur Pegase
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
	 * @return la liste des cat�gories
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
