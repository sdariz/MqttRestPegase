package org.signature.mqttRest.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.signature.mqttRest.objetsPartages.etatEtPilotage.IMessageAffichageEquipementMqttRest.TypeEquipement;
import org.signature.mqttRest.objetsPartages.etatEtPilotage.MessageBarriereMqttRest;
import org.signature.mqttRest.objetsPartages.etatEtPilotage.MessageBraMqttRest;
import org.signature.mqttRest.objetsPartages.etatEtPilotage.MessageEquipementModuleUniqueMqttRest;
import org.signature.mqttRest.objetsPartages.etatEtPilotage.MessageFeuRegulationMqttRest;
import org.signature.mqttRest.objetsPartages.etatEtPilotage.MessageModuleMqttRest;
import org.signature.mqttRest.objetsPartages.etatEtPilotage.MessagePictogrammeMqttRest;
import org.signature.mqttRest.objetsPartages.etatEtPilotage.MessagePmvMqttRest;
import org.signature.mqttRest.objetsPartages.etatEtPilotage.MessagePpadMqttRest;
import org.signature.mqttRest.objetsPartages.etatEtPilotage.MessagePrismeMqttRest;
import org.signature.mqttRest.objetsPartages.etatEtPilotage.MessageR24MqttRest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

/**
 * Une classe de m?thodes utilitaires
 * 
 * @author SDARIZCUREN
 *
 */
public class Util {
	private static Logger LOG = LogManager.getLogger(Util.class);

	/**
	 * S?rialise la valeur au format JSON
	 * 
	 * @param pVal
	 *            la valeur ? s?rialiser
	 * @return la cha?ne JSON correspondante ? la valeur
	 */
	public static String booleanToJsonString(boolean pVal) {
		ObjectMapper mapper = new ObjectMapper();

		try {
			return mapper.writeValueAsString(pVal);
		} catch (JsonProcessingException e) {
			LOG.error("Conversion boolean", e);
			return "";
		}
	}

	/**
	 * S?rialise l'objet au format JSON
	 * 
	 * @param pObj
	 *            l'objet ? s?rialiser
	 * @return la cha?ne JSON correspondante ? l'objet
	 */
	public static String ObjectToJsonString(Object pObj) {
		if (pObj == null) {
			return "";
		}

		ObjectMapper mapper = new ObjectMapper();
		mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		try {
			// return
			// mapper.writerWithDefaultPrettyPrinter().writeValueAsString(pObj);
			return mapper.writeValueAsString(pObj);
		} catch (JsonProcessingException e) {
			LOG.error("Conversion objet", e);
			return "";
		}
	}

	/**
	 * S?rialise les objets au format JSON
	 * 
	 * @param pObjs
	 *            les objets ? s?rialiser
	 * @return la cha?ne JSON correspondante aux objets
	 */
	public static String listObjectToJsonString(List<?> pObjs) {
		if (pObjs == null || pObjs.size() == 0) {
			return "";
		}

		ObjectMapper mapper = new ObjectMapper();
		mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		try {
			return mapper.writeValueAsString(pObjs);
		} catch (JsonProcessingException e) {
			LOG.error("Conversion liste d'objets", e);
			return "";
		}
	}

	/**
	 * Deserialise un objet au format JSON
	 * 
	 * @param pChaineJson
	 *            la cha?ne JSON encodant l'objet
	 * @param pClassObjet
	 *            la classe de l'objet ? d?s?rialiser
	 * @return l'objet d?s?rialis? ou null si probl?me
	 */
	public static Object jsonToObjet(String pChaineJson, Class<?> pClassObjet) {
		if (pChaineJson == null) {
			return null;
		}

		ObjectMapper mapper = new ObjectMapper();
		mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		try {
			return mapper.readValue(pChaineJson, pClassObjet);
		} catch (IOException e) {
			LOG.error("Deserialisation objet", e);
		}

		return null;
	}

	/**
	 * Deserialise des objets au format JSON
	 * 
	 * @param pChaineJson
	 *            la cha?ne JSON encodant les objets
	 * @param pClassObjet
	 *            la classe de l'objet ? d?s?rialiser
	 * @return les objets d?s?rialis?s ou liste vide si probl?me
	 */
	public static List<Object> jsonToListeObjet(String pChaineJson, Class<?> pClassObjet) {
		if (pChaineJson == null) {
			return new ArrayList<>();
		}

		ObjectMapper mapper = new ObjectMapper();
		mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		try {
			final CollectionType javaType = mapper.getTypeFactory().constructCollectionType(List.class, pClassObjet);
			return mapper.readValue(pChaineJson, javaType);
		} catch (IOException e) {
			LOG.error("Deserialisation liste d'objets", e);
		}

		return new ArrayList<>();
	}

	/**
	 * Deserialise un boolean au format JSON
	 * 
	 * @param pChaineJson
	 *            la cha?ne JSON encodant le boolean
	 * @return true ou false
	 */
	public static boolean jsonToBoolean(String pChaineJson) {
		if (pChaineJson == null) {
			return false;
		}

		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.readValue(pChaineJson, Boolean.class);
		} catch (IOException e) {
			LOG.error("Deserialisation boolean", e);
		}

		return false;
	}

	/**
	 * Cr?ation d'un message PMV fixe, avec luminosit? automatique, DV = 0,
	 * flashs ?teints si pr?sents
	 * 
	 * @param pIdEquipement
	 *            l'identifiant de l'?quipement
	 * @param pLignes
	 *            les messages de chaque ligne, liste vide si non utilis?
	 * @param pPanonceaux
	 *            les messages de chaque panonceau, liste vide si non utilis?
	 * @param pPictos
	 *            les messages de chaque pictogramme, liste vide si non utilis?
	 * @param pLabelsPictos
	 *            les labels des messages de chaque pictogramme, liste vide si
	 *            non utilis?
	 * @param avecFlashs
	 *            true si le panneau ? des feux flashs
	 * @return le nouveau message
	 */
	public static MessagePmvMqttRest creationMessagePmvFixe(String pIdEquipement, List<String> pLignes,
			List<String> pPanonceaux, List<String> pPictos, List<String> pLabelsPictos, boolean avecFlashs) {
		if (pIdEquipement == null) {
			pIdEquipement = "";
		}

		MessagePmvMqttRest retour = new MessagePmvMqttRest(pIdEquipement);

		// Les lignes
		List<MessageModuleMqttRest> lignes = new ArrayList<>();
		retour.setMessagesLignes(lignes);
		pLignes.forEach(s -> {
			MessageModuleMqttRest msg = new MessageModuleMqttRest();
			List<String> txt = new ArrayList<>();
			txt.add(s);
			msg.setMessagesParPage(txt);
			lignes.add(msg);
		});

		// Panonceaux
		List<MessageModuleMqttRest> panonceaux = new ArrayList<>();
		retour.setMessagesPanonceaux(panonceaux);
		pPanonceaux.forEach(s -> {
			MessageModuleMqttRest msg = new MessageModuleMqttRest();
			List<String> txt = new ArrayList<>();
			txt.add(s);
			msg.setMessagesParPage(txt);
			panonceaux.add(msg);
		});

		// Pictogrammes : messages + labels
		List<MessageModuleMqttRest> pictogrammes = new ArrayList<>();
		retour.setMessagesPictogrammes(pictogrammes);
		int tailleMax = Math.max(pPictos.size(), pLabelsPictos.size());

		// Parcours de chaque picto
		for (int i = 0; i < tailleMax; i++) {
			MessageModuleMqttRest msg = new MessageModuleMqttRest();
			pictogrammes.add(msg);

			// Le message brut
			List<String> txt = new ArrayList<>();
			if (pPictos.size() > i) {
				txt.add(pPictos.get(i));
			}
			msg.setMessagesParPage(txt);

			// Les labels des messages
			txt = new ArrayList<>();
			if (pLabelsPictos.size() > i) {
				txt.add(pLabelsPictos.get(i));
			}
			msg.setLabelsParPage(txt);
		}

		// Les flashs
		if (avecFlashs) {
			MessageModuleMqttRest msg = new MessageModuleMqttRest();
			List<String> txt = new ArrayList<>();
			txt.add("0");
			msg.setMessagesParPage(txt);

			txt = new ArrayList<>();
			txt.add("ETEINT");
			msg.setLabelsParPage(txt);
		}

		return retour;
	}

	/**
	 * Cr?ation d'un message vierge pour un ?quipement ? module unique, avec
	 * luminosit? automatique, DV = 0, flashs ?teints si pr?sents
	 * 
	 * @param pIdEquipement
	 *            l'identifiant de l'?quipement
	 * @param pType
	 *            le type de l'?quipement
	 * @return le nouveau message, ou null si le message ne peut ?tre cr??
	 */
	public static MessageEquipementModuleUniqueMqttRest creationMessageViergeEquipementModuleUnique(
			String pIdEquipement, TypeEquipement pType) {
		if (pIdEquipement == null) {
			pIdEquipement = "";
		}

		if (pType == null) {
			return null;
		}

		switch (pType) {
		case PPAD:
			return new MessagePpadMqttRest(pIdEquipement);
		case PICTOGRAMME:
			return new MessagePictogrammeMqttRest(pIdEquipement);
		case R24:
			return new MessageR24MqttRest(pIdEquipement);
		case PRISME:
			return new MessagePrismeMqttRest(pIdEquipement);
		case BARRIERE:
			return new MessageBarriereMqttRest(pIdEquipement);
		case BRA:
			return new MessageBraMqttRest(pIdEquipement);
		case FEU_REGULATION:
			return new MessageFeuRegulationMqttRest(pIdEquipement);
		default: return null;
		}
	}

}
