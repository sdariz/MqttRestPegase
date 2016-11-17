package signature.mqttRest.objetsPartages.scenario;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import signature.mqttRest.objetsPartages.etatEtPilotage.IMessageAffichageEquipementMqttRest;
import signature.mqttRest.objetsPartages.etatEtPilotage.MessagePictogrammeMqttRest;
import signature.mqttRest.objetsPartages.etatEtPilotage.IMessageAffichageEquipementMqttRest.TypeEquipement;
import signature.mqttRest.objetsPartages.etatEtPilotage.MessageBarriereMqttRest;
import signature.mqttRest.objetsPartages.etatEtPilotage.MessageBraMqttRest;
import signature.mqttRest.objetsPartages.etatEtPilotage.MessagePmvMqttRest;
import signature.mqttRest.objetsPartages.etatEtPilotage.MessagePpadMqttRest;
import signature.mqttRest.objetsPartages.etatEtPilotage.MessagePplmvMqttRest;
import signature.mqttRest.objetsPartages.etatEtPilotage.MessagePrismeMqttRest;
import signature.mqttRest.objetsPartages.etatEtPilotage.MessageR24MqttRest;

/**
 * Description d'un sc�nario
 * 
 * @author SDARIZCUREN
 *
 */
public class MessageScenarioMqttRest {
	private String _nom;
	private String _categorie;
	private List<OrdonnanceurMessagesScenarioMqttRest> _ordonnanceurs;

	/**
	 * Construction du message
	 */
	public MessageScenarioMqttRest() {
		this("", "");
	}

	/**
	 * Construction du message
	 * 
	 * @param pNom
	 *            le nom du sc�nario
	 * @param pCategorie
	 *            la cat�gorie associ�e
	 */
	public MessageScenarioMqttRest(String pNom, String pCategorie) {
		this(pNom, pCategorie, new ArrayList<>());
	}

	/**
	 * Construction du message
	 * 
	 * @param pNom
	 *            le nom du sc�nario
	 * @param pCategorie
	 *            la cat�gorie associ�e
	 * @param pOrdonnanceurs
	 *            les ordonnanceurs pour ordonner les pilotages
	 */
	public MessageScenarioMqttRest(String pNom, String pCategorie,
			List<OrdonnanceurMessagesScenarioMqttRest> pOrdonnanceurs) {
		_nom = pNom;
		_categorie = pCategorie;

		if (pOrdonnanceurs == null) {
			pOrdonnanceurs = new ArrayList<>();
		}

		_ordonnanceurs = pOrdonnanceurs;
	}

	/**
	 * Initialise le nom du sc�nario
	 * 
	 * @param pNom
	 *            le nom du sc�nario
	 */
	public void setNom(String pNom) {
		_nom = pNom;
	}

	/**
	 * Retourne le nom du sc�nario
	 * 
	 * @return son nom
	 */
	public String getNom() {
		return _nom;
	}

	/**
	 * Initialise la cat�gorie du sc�nario
	 * 
	 * @param pCategorie
	 *            la cat�gorie du sc�nario
	 */
	public void setCategorie(String pCategorie) {
		_categorie = pCategorie;
	}

	/**
	 * Retourne la cat�gorie du sc�nario
	 * 
	 * @return sa cat�gorie
	 */
	public String getCategorie() {
		return _categorie;
	}

	/**
	 * Initialise les ordonnanceurs
	 * 
	 * @param pOrdonnanceurs
	 *            les ordonnanceurs
	 */
	public void setOrdonnanceurs(List<OrdonnanceurMessagesScenarioMqttRest> pOrdonnanceurs) {
		if (pOrdonnanceurs == null) {
			pOrdonnanceurs = new ArrayList<>();
		}

		_ordonnanceurs = pOrdonnanceurs;
	}

	/**
	 * Retourne les ordonnanceurs
	 * 
	 * @return les ordonnanceurs
	 */
	public List<OrdonnanceurMessagesScenarioMqttRest> getOrdonnanceurs() {
		return _ordonnanceurs;
	}

	/**
	 * Retourne tous les messages pilot�s dans ce sc�nario
	 * 
	 * @return les messages du sc�nario
	 */
	public List<IMessageAffichageEquipementMqttRest> getTousMessages() {
		List<IMessageAffichageEquipementMqttRest> retour = new ArrayList<>();

		_ordonnanceurs.forEach(o -> retour.addAll(o.getListeMessages()));

		return retour;
	}

	/**
	 * Retourne tous les messages de type PMV, pilot�s dans ce sc�nario
	 * 
	 * @return tous les messages PMV du sc�nario
	 */
	public List<MessagePmvMqttRest> getTousMessagesPmv() {
		// Cast le tableau vers le bon format
		return getTousMessagesPourType(TypeEquipement.PMV).stream().map(MessagePmvMqttRest.class::cast)
				.collect(Collectors.toList());
	}
	
	/**
	 * Retourne tous les messages de type PPLMV, pilot�s dans ce sc�nario
	 * 
	 * @return tous les messages PPLMV du sc�nario
	 */
	public List<MessagePplmvMqttRest> getTousMessagesPplmv() {
		// Cast le tableau vers le bon format
		return getTousMessagesPourType(TypeEquipement.PPLMV).stream().map(MessagePplmvMqttRest.class::cast)
				.collect(Collectors.toList());
	}
	
	/**
	 * Retourne tous les messages de type PPAD, pilot�s dans ce sc�nario
	 * 
	 * @return tous les messages PPAD du sc�nario
	 */
	public List<MessagePpadMqttRest> getTousMessagesPpad() {
		// Cast le tableau vers le bon format
		return getTousMessagesPourType(TypeEquipement.PPAD).stream().map(MessagePpadMqttRest.class::cast)
				.collect(Collectors.toList());
	}
	
	/**
	 * Retourne tous les messages de type Pictogramme, pilot�s dans ce sc�nario
	 * 
	 * @return tous les messages Pictogrammes du sc�nario
	 */
	public List<MessagePictogrammeMqttRest> getTousMessagesPictogramme() {
		// Cast le tableau vers le bon format
		return getTousMessagesPourType(TypeEquipement.PICTOGRAMME).stream().map(MessagePictogrammeMqttRest.class::cast)
				.collect(Collectors.toList());
	}
	
	/**
	 * Retourne tous les messages de type R24, pilot�s dans ce sc�nario
	 * 
	 * @return tous les messages R24 du sc�nario
	 */
	public List<MessageR24MqttRest> getTousMessagesR24() {
		// Cast le tableau vers le bon format
		return getTousMessagesPourType(TypeEquipement.R24).stream().map(MessageR24MqttRest.class::cast)
				.collect(Collectors.toList());
	}
	
	/**
	 * Retourne tous les messages de type Prisme, pilot�s dans ce sc�nario
	 * 
	 * @return tous les messages Prisme du sc�nario
	 */
	public List<MessagePrismeMqttRest> getTousMessagesPrisme() {
		// Cast le tableau vers le bon format
		return getTousMessagesPourType(TypeEquipement.PRISME).stream().map(MessagePrismeMqttRest.class::cast)
				.collect(Collectors.toList());
	}
	
	/**
	 * Retourne tous les messages de type Barri�re, pilot�s dans ce sc�nario
	 * 
	 * @return tous les messages Barri�re du sc�nario
	 */
	public List<MessageBarriereMqttRest> getTousMessagesBarriere() {
		// Cast le tableau vers le bon format
		return getTousMessagesPourType(TypeEquipement.BARRIERE).stream().map(MessageBarriereMqttRest.class::cast)
				.collect(Collectors.toList());
	}
	
	/**
	 * Retourne tous les messages de type BRA, pilot�s dans ce sc�nario
	 * 
	 * @return tous les messages BRA du sc�nario
	 */
	public List<MessageBraMqttRest> getTousMessagesBra() {
		// Cast le tableau vers le bon format
		return getTousMessagesPourType(TypeEquipement.BRA).stream().map(MessageBraMqttRest.class::cast)
				.collect(Collectors.toList());
	}

	// Filtre les messages du sc�nario selon le type demand�
	private List<IMessageAffichageEquipementMqttRest> getTousMessagesPourType(TypeEquipement pType) {
		List<IMessageAffichageEquipementMqttRest> retour = new ArrayList<>();

		getTousMessages().forEach(m -> {
			if (m.getTypeEquipement() == pType) {
				retour.add(m);
			}
		});

		return retour;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((_categorie == null) ? 0 : _categorie.hashCode());
		result = prime * result + ((_nom == null) ? 0 : _nom.hashCode());
		result = prime * result + ((_ordonnanceurs == null) ? 0 : _ordonnanceurs.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MessageScenarioMqttRest other = (MessageScenarioMqttRest) obj;
		if (_categorie == null) {
			if (other._categorie != null)
				return false;
		} else if (!_categorie.equals(other._categorie))
			return false;
		if (_nom == null) {
			if (other._nom != null)
				return false;
		} else if (!_nom.equals(other._nom))
			return false;
		if (_ordonnanceurs == null) {
			if (other._ordonnanceurs != null)
				return false;
		} else if (!_ordonnanceurs.equals(other._ordonnanceurs))
			return false;
		return true;
	}
}
