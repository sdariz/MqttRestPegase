package org.signature.mqttRest.objetsPartages.scenario;

import java.util.ArrayList;
import java.util.List;

import org.signature.mqttRest.objetsPartages.etatEtPilotage.IMessageAffichageEquipementMqttRest;

/**
 * Ordonnanceur permettant d'ordonner le pilotage des messages d'un scénario
 * 
 * @author SDARIZCUREN
 *
 */
public class OrdonnanceurMessagesScenarioMqttRest {
	private int _numeroOrdre;
	private int _tempsAvantPilotage;
	private List<IMessageAffichageEquipementMqttRest> _messages;

	/**
	 * Construction de l'ordonnanceur
	 */
	public OrdonnanceurMessagesScenarioMqttRest() {
		this(1, 0);
	}

	/**
	 * Construction de l'ordonnanceur
	 * 
	 * @param pNumeroOrdre
	 *            le numéro d'ordre dans les séquences de pilotage
	 * @param pTempsAvantPilotage
	 *            le temps d'attente avant de piloter les messages, en secondes
	 */
	public OrdonnanceurMessagesScenarioMqttRest(int pNumeroOrdre, int pTempsAvantPilotage) {
		this(pNumeroOrdre, pTempsAvantPilotage, new ArrayList<>());
	}

	/**
	 * Construction de l'ordonnanceur
	 * 
	 * @param pNumeroOrdre
	 *            le numéro d'ordre dans les séquences de pilotage
	 * @param pTempsAvantPilotage
	 *            le temps d'attente avant de piloter les messages, en secondes
	 * @param pMessages
	 *            les messages à piloter
	 */
	public OrdonnanceurMessagesScenarioMqttRest(int pNumeroOrdre, int pTempsAvantPilotage,
			List<IMessageAffichageEquipementMqttRest> pMessages) {
		_numeroOrdre = pNumeroOrdre;
		_tempsAvantPilotage = pTempsAvantPilotage;

		if (pMessages == null) {
			pMessages = new ArrayList<>();
		}

		_messages = pMessages;
	}

	/**
	 * Initialise le numéro d'ordre
	 * 
	 * @return le numéro d'ordre dans les séquences de pilotage
	 */
	public int getNumeroOrdre() {
		return _numeroOrdre;
	}

	/**
	 * Retourne le numéro d'ordre
	 * 
	 * @param pNumero
	 *            le numéro d'ordre dans les séquences de pilotage
	 */
	public void setNumeroOrdre(int pNumero) {
		_numeroOrdre = pNumero;
	}

	/**
	 * Initialise le temps avant pilotage, en seconde
	 * 
	 * @return le temps avant pilotage
	 */
	public int getTempsAvantPilotage() {
		return _tempsAvantPilotage;
	}

	/**
	 * Retourne le temps avant pilotage
	 * 
	 * @param pTemps
	 *            le temps avant pilotage
	 */
	public void setTempsAvantPilotage(int pTemps) {
		_tempsAvantPilotage = pTemps;
	}

	/**
	 * Retourne la liste des messages liés à cet ordonnanceur
	 * 
	 * @return la liste des messages à piloter
	 */
	public List<IMessageAffichageEquipementMqttRest> getListeMessages() {
		return _messages;
	}

	/**
	 * Initialise la liste des messages liés à cet ordonnanceur
	 * 
	 * @param pMessages
	 *            la liste des messages à piloter
	 */
	public void setListeMessages(List<IMessageAffichageEquipementMqttRest> pMessages) {
		if (pMessages == null) {
			pMessages = new ArrayList<>();
		}

		_messages = pMessages;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((_messages == null) ? 0 : _messages.hashCode());
		result = prime * result + _numeroOrdre;
		result = prime * result + _tempsAvantPilotage;
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
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
		OrdonnanceurMessagesScenarioMqttRest other = (OrdonnanceurMessagesScenarioMqttRest) obj;
		if (_messages == null) {
			if (other._messages != null)
				return false;
		} else if (!_messages.equals(other._messages))
			return false;
		if (_numeroOrdre != other._numeroOrdre)
			return false;
		if (_tempsAvantPilotage != other._tempsAvantPilotage)
			return false;
		return true;
	}
}
