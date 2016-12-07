package org.signature.mqttRest.objetsPartages.etatEtPilotage;

import java.util.Date;

import org.signature.mqttRest.objetsPartages.IMessageMqttRest;

/**
 * Classe d�crivant l'�tat d'affichage d'un �quipement
 * 
 * @author SDARIZCUREN
 *
 */
public class MessageEtatAffichageMqttRest implements IMessageMqttRest {
	private String _idExpediteur;
	private String _referenceMessage;

	private Date _horodateGeneration;
	private Date _horodateFin;

	private IMessageAffichageEquipementMqttRest _messageEquipement;

	/**
	 * Construction du message
	 */
	public MessageEtatAffichageMqttRest() {
		this("", "");
	}

	/**
	 * Construction du message
	 * 
	 * @param pIdExpediteur
	 *            l'identifiant de l'exp�diteur
	 * @param pReferenceMessage
	 *            la r�f�rence du message, donn�e par l'exp�diteur
	 */
	public MessageEtatAffichageMqttRest(String pIdExpediteur, String pReferenceMessage) {
		_idExpediteur = pIdExpediteur;
		_referenceMessage = pReferenceMessage;

		_horodateGeneration = new Date();
	}

	/**
	 * Donne l'identifiant de l'expediteur du message
	 * 
	 * @return l'identifiant de l'exp�diteur
	 */
	public String getIdentifiantExpediteur() {
		return _idExpediteur;
	}

	/**
	 * Initialise l'identifiant de l'exp�diteur
	 * 
	 * @param pId
	 *            l'identifiant de l'exp�diteur
	 */
	public void setIdentifiantExpediteur(String pId) {
		_idExpediteur = pId;
	}

	/**
	 * La r�f�rence du message, attribu�e par l'exp�diteur
	 * 
	 * @return la r�f�rence de l'exp�diteur sur le message
	 */
	public String getReferenceMessage() {
		return _referenceMessage;
	}

	/**
	 * Initialise la r�f�rence du message pour son exp�diteur
	 * 
	 * @param pRef
	 *            la r�f�rence du message
	 */
	public void setReferenceMessage(String pRef) {
		_referenceMessage = pRef;
	}

	/**
	 * Donne l'identifiant de l'�quipement
	 * 
	 * @return l'identifiant de l'�quipement
	 */
	public String getIdentifiantEquipement() {
		if(_messageEquipement != null) {
			return _messageEquipement.getIdentifiantEquipement();
		}
		return "";
	}


	/**
	 * Donne l'horodate de g�n�ration du message
	 * 
	 * @return l'horodate de g�n�ration
	 */
	public Date getHorodateGeneration() {
		return _horodateGeneration;
	}

	/**
	 * Initialise l'horodate de g�n�ration du message
	 * 
	 * @param pDate
	 *            la nouvelle horodate
	 */
	public void setHorodateGeneration(Date pDate) {
		_horodateGeneration = pDate;
	}

	/**
	 * Donne l'horodate de fin du message
	 * 
	 * @return l'horodate de fin. Null si pas d�finie
	 */
	public Date getHorodateFin() {
		return _horodateFin;
	}

	/**
	 * Initialise l'horodate de fin du message
	 * 
	 * @param pDate
	 *            la nouvelle horodate. Null si pas d�finie
	 */
	public void setHorodateFin(Date pDate) {
		_horodateFin = pDate;
	}

	/**
	 * Initialise le message affich� par l'�quipement
	 * 
	 * @param pMsg
	 *            le message sur l'�quipement
	 */
	public void setMessageEquipement(IMessageAffichageEquipementMqttRest pMsg) {
		_messageEquipement = pMsg;
	}

	/**
	 * Retourne le message affich� par l'�quipement
	 * 
	 * @return le message sur l'�quipement, ou null si inconnu
	 */
	public IMessageAffichageEquipementMqttRest getMessageEquipement() {
		return _messageEquipement;
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
		result = prime * result + ((_horodateFin == null) ? 0 : _horodateFin.hashCode());
		result = prime * result + ((_horodateGeneration == null) ? 0 : _horodateGeneration.hashCode());
		result = prime * result + ((_idExpediteur == null) ? 0 : _idExpediteur.hashCode());
		result = prime * result + ((_messageEquipement == null) ? 0 : _messageEquipement.hashCode());
		result = prime * result + ((_referenceMessage == null) ? 0 : _referenceMessage.hashCode());
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
		MessageEtatAffichageMqttRest other = (MessageEtatAffichageMqttRest) obj;
		if (_horodateFin == null) {
			if (other._horodateFin != null)
				return false;
		} else if (!_horodateFin.equals(other._horodateFin))
			return false;
		if (_horodateGeneration == null) {
			if (other._horodateGeneration != null)
				return false;
		} else if (!_horodateGeneration.equals(other._horodateGeneration))
			return false;
		if (_idExpediteur == null) {
			if (other._idExpediteur != null)
				return false;
		} else if (!_idExpediteur.equals(other._idExpediteur))
			return false;
		if (_messageEquipement == null) {
			if (other._messageEquipement != null)
				return false;
		} else if (!_messageEquipement.equals(other._messageEquipement))
			return false;
		if (_referenceMessage == null) {
			if (other._referenceMessage != null)
				return false;
		} else if (!_referenceMessage.equals(other._referenceMessage))
			return false;
		return true;
	}

}
