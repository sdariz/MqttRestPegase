package org.signature.mqttRest.services.rest.client.bibliothequePmv;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.signature.mqttRest.objetsPartages.etatEtPilotage.MessagePmvMqttRest;
import org.signature.mqttRest.services.rest.client.ClientHttpRest;
import org.signature.mqttRest.services.rest.serveur.bibliothequePmv.GestionnaireRoutesBibliothequePmv;
import org.signature.mqttRest.util.Util;

/**
 * Requ�tes vers le serveur REST pour r�cup�rer ou agir sur la biblioth�que PMV
 * 
 * @author SDARIZCUREN
 *
 */
public class ServiceRequetesBibliothequePmv {

	/**
	 * Envoi au serveur REST une demande de la liste des cat�gories de la
	 * biblioth�que PMV
	 * 
	 * @param pHost
	 *            l'adresse IP du serveur REST
	 * @param pPort
	 *            le port TCP utilis� par le serveur
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'exp�diteur : peut �tre vide
	 * @param pReferenceCommande
	 *            la r�f�rence unique de la demande : peut �tre vide
	 * @return la liste des cat�gories
	 */
	public static List<String> requeteDemandeCategoriesBibliothequePmv(String pHost, int pPort,
			String pIdentifiantExpediteur, String pReferenceCommande) {
		if (pIdentifiantExpediteur == null) {
			pIdentifiantExpediteur = "";
		}

		if (pReferenceCommande == null) {
			pReferenceCommande = "";
		}

		Map<String, String> params = new HashMap<>();
		params.put("idExpediteur", pIdentifiantExpediteur);
		params.put("idCommande", pReferenceCommande);

		String json = ClientHttpRest.envoiRequeteGET(pHost, pPort, GestionnaireRoutesBibliothequePmv.LISTE_CATEGORIES,
				params);
		if (json.length() == 0) {
			return new ArrayList<>();
		}

		// Formatage du retour vers le bon format
		return Util.jsonToListeObjet(json, String.class).stream().map(String.class::cast)
				.collect(Collectors.toList());
	}

	/**
	 * Envoi au serveur REST une demande de la liste des noms des messages dans
	 * une cat�gorie de la biblioth�que PMV
	 * 
	 * @param pHost
	 *            l'adresse IP du serveur REST
	 * @param pPort
	 *            le port TCP utilis� par le serveur
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'exp�diteur : peut �tre vide
	 * @param pReferenceCommande
	 *            la r�f�rence unique de la demande : peut �tre vide
	 * @param pCategorie
	 *            la cat�gorie concern�e
	 * @return la liste des noms des messages de la cat�gorie
	 */
	public static List<String> requeteDemandeMessagesDansCategorieBibliothequePmv(String pHost, int pPort,
			String pIdentifiantExpediteur, String pReferenceCommande, String pCategorie) {
		if (pIdentifiantExpediteur == null) {
			pIdentifiantExpediteur = "";
		}

		if (pReferenceCommande == null) {
			pReferenceCommande = "";
		}

		Map<String, String> params = new HashMap<>();
		params.put("idExpediteur", pIdentifiantExpediteur);
		params.put("idCommande", pReferenceCommande);
		params.put("categorie", pCategorie);

		String json = ClientHttpRest.envoiRequeteGET(pHost, pPort,
				GestionnaireRoutesBibliothequePmv.LISTE_MESSAGES_DANS_CATEGORIE, params);
		if (json.length() == 0) {
			return new ArrayList<>();
		}

		// Formatage du retour vers le bon format
		return Util.jsonToListeObjet(json, String.class).stream().map(String.class::cast)
				.collect(Collectors.toList());
	}

	/**
	 * Envoi au serveur REST une demande de message de la biblioth�que PMV
	 * 
	 * @param pHost
	 *            l'adresse IP du serveur REST
	 * @param pPort
	 *            le port TCP utilis� par le serveur
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'exp�diteur : peut �tre vide
	 * @param pReferenceCommande
	 *            la r�f�rence unique de la demande : peut �tre vide
	 * @param pCategorie
	 *            la cat�gorie concern�e
	 * @param pNom
	 *            le nom du message dans la cat�gorie
	 * @return le message correspondant, ou null si probl�me
	 */
	public static MessagePmvMqttRest requeteDemandeMessageBibliothequePmv(String pHost, int pPort,
			String pIdentifiantExpediteur, String pReferenceCommande, String pCategorie, String pNom) {
		if (pIdentifiantExpediteur == null) {
			pIdentifiantExpediteur = "";
		}

		if (pReferenceCommande == null) {
			pReferenceCommande = "";
		}

		Map<String, String> params = new HashMap<>();
		params.put("idExpediteur", pIdentifiantExpediteur);
		params.put("idCommande", pReferenceCommande);
		params.put("categorie", pCategorie);
		params.put("nom", pNom);

		String json = ClientHttpRest.envoiRequeteGET(pHost, pPort,
				GestionnaireRoutesBibliothequePmv.MESSAGE, params);
		if (json.length() == 0) {
			return null;
		}

		return (MessagePmvMqttRest) Util.jsonToObjet(json, MessagePmvMqttRest.class);
	}

}
