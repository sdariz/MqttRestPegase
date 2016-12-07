package org.signature.mqttRest.objetsPartages.etatEtPilotage;

import java.util.ArrayList;
import java.util.List;

import org.signature.mqttRest.objetsPartages.IMessageMqttRest;
import org.signature.mqttRest.objetsPartages.evenement.MessageAlarmeMqttRest;

/**
 * Classe décrivant l'état technique d'un équipement
 * 
 * @author SDARIZCUREN
 *
 */
public class MessageEtatTechniqueMqttRest implements IMessageMqttRest {
	private String _idEquipement;
	private String _idExpediteur;
	private String _referenceMessage;
	private List<MessageAlarmeMqttRest> _alarmes;

	/**
	 * Construction du message
	 */
	public MessageEtatTechniqueMqttRest() {
		this("", "", "");
	}

	/**
	 * Construction du message
	 * 
	 * @param pIdExpediteur
	 *            l'identifiant de l'expéditeur
	 * @param pReferenceMessage
	 *            la référence du message, donnée par l'expéditeur
	 * @param pIdEquipement
	 *            l'identifiant unique de l'équipement
	 */
	public MessageEtatTechniqueMqttRest(String pIdExpediteur, String pReferenceMessage, String pIdEquipement) {
		_idExpediteur = pIdExpediteur;
		_referenceMessage = pReferenceMessage;
		_idEquipement = pIdEquipement;

		_alarmes = new ArrayList<>();
	}

	/**
	 * Donne l'identifiant de l'expediteur du message
	 * 
	 * @return l'identifiant de l'expéditeur
	 */
	public String getIdentifiantExpediteur() {
		return _idExpediteur;
	}

	/**
	 * Initialise l'identifiant de l'expéditeur
	 * 
	 * @param pId
	 *            l'identifiant de l'expéditeur
	 */
	public void setIdentifiantExpediteur(String pId) {
		_idExpediteur = pId;
	}

	/**
	 * La référence du message, attribuée par l'expéditeur
	 * 
	 * @return la référence de l'expéditeur sur le message
	 */
	public String getReferenceMessage() {
		return _referenceMessage;
	}

	/**
	 * Initialise la référence du message pour son expéditeur
	 * 
	 * @param pRef
	 *            la référence du message
	 */
	public void setReferenceMessage(String pRef) {
		_referenceMessage = pRef;
	}

	/**
	 * Donne l'identifiant de l'équipement
	 * 
	 * @return l'identifiant de l'équipement
	 */
	public String getIdentifiantEquipement() {
		return _idEquipement;
	}

	/**
	 * Initialise l'identifiant de l'équipement
	 * 
	 * @param pId
	 *            l'identifiant de l'équipement
	 */
	public void setIdentifiantEquipement(String pId) {
		_idEquipement = pId;
	}

	/**
	 * Donne la liste des alarmes
	 * 
	 * @return la liste des alarmes sur l'équipement
	 */
	public List<MessageAlarmeMqttRest> getAlarmes() {
		return _alarmes;
	}

	/**
	 * Initialisation des alarmes sur l'équipement
	 * 
	 * @param pAlarmes
	 *            la liste des alarmes
	 */
	public void setAlarmes(List<MessageAlarmeMqttRest> pAlarmes) {
		if (pAlarmes == null) {
			pAlarmes = new ArrayList<MessageAlarmeMqttRest>();
		}

		_alarmes = pAlarmes;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((_alarmes == null) ? 0 : _alarmes.hashCode());
		result = prime * result + ((_idEquipement == null) ? 0 : _idEquipement.hashCode());
		result = prime * result + ((_idExpediteur == null) ? 0 : _idExpediteur.hashCode());
		result = prime * result + ((_referenceMessage == null) ? 0 : _referenceMessage.hashCode());
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
		MessageEtatTechniqueMqttRest other = (MessageEtatTechniqueMqttRest) obj;
		if (_alarmes == null) {
			if (other._alarmes != null)
				return false;
		} else if (!_alarmes.equals(other._alarmes))
			return false;
		if (_idEquipement == null) {
			if (other._idEquipement != null)
				return false;
		} else if (!_idEquipement.equals(other._idEquipement))
			return false;
		if (_idExpediteur == null) {
			if (other._idExpediteur != null)
				return false;
		} else if (!_idExpediteur.equals(other._idExpediteur))
			return false;
		if (_referenceMessage == null) {
			if (other._referenceMessage != null)
				return false;
		} else if (!_referenceMessage.equals(other._referenceMessage))
			return false;
		return true;
	}
}
