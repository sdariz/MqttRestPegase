package org.signature.mqttRest.objetsPartages.etatEtPilotage;

import java.util.ArrayList;
import java.util.List;

import org.signature.mqttRest.util.Util;

/**
 * Le message sur un �quipement � module unique
 * 
 * @author SDARIZCUREN
 *
 */
public abstract class MessageEquipementModuleUniqueMqttRest implements IMessageAffichageEquipementMqttRest {
	private MessageModuleMqttRest _messagesModuleUnique;
	private TypeMessage _typeMessage;
	private String _idEquipement;

	/**
	 * Construction du message
	 */
	public MessageEquipementModuleUniqueMqttRest() {
		this("");
	}

	/**
	 * Construction du message
	 * 
	 * @param pId
	 *            l'identifiant de l'�quipement
	 */
	public MessageEquipementModuleUniqueMqttRest(String pId) {
		_idEquipement = pId;

		_messagesModuleUnique = null;
		_typeMessage = TypeMessage.ETEINT;
	}

	/**
	 * Cr�ation d'un message pour le type d'�quipement demand�
	 * 
	 * @param pIdEquipement
	 *            l'identifiant de l'�quipement
	 * @param pType
	 *            le type d'�quipement voulu
	 * @return lOe message ou null si probl�me de cr�ation (type pas adapt�, ...)
	 */
	public static MessageEquipementModuleUniqueMqttRest creationMessageEquipementModuleUnique(String pIdEquipement,
			TypeEquipement pType) {
		return Util.creationMessageViergeEquipementModuleUnique(pIdEquipement, pType);
	}

	/**
	 * Donne l'identifant unique associ� � l'�quipement
	 * 
	 * @return l'identifant unique de l'�quipement
	 */
	public String getIdentifiantEquipement() {
		return _idEquipement;
	}

	/**
	 * Initialise l'identifant unique associ� � l'�quipement
	 * 
	 * @param pId
	 *            l'identifant unique de l'�quipement
	 */
	public void setIdentifiantEquipement(String pId) {
		_idEquipement = pId;
	}

	/**
	 * Initialise les message sur l'�quipement
	 * 
	 * @param pMessages
	 *            les messages de l'�quipement
	 */
	public void setMessagesModuleUnique(MessageModuleMqttRest pMessages) {
		_messagesModuleUnique = pMessages;
	}

	/**
	 * Retourne les messages sur l'�quipement
	 * 
	 * @return les messages de l'�quipement
	 */
	public MessageModuleMqttRest getMessagesModuleUnique() {
		return _messagesModuleUnique;
	}

	/**
	 * Indique le type du message : message d'exploitation, de mise au neutre
	 * 
	 * @return le type de message
	 */
	public TypeMessage getTypeMessage() {
		return _typeMessage;
	}

	/**
	 * Initialise le type du message : message d'exploitation, de mise au neutre
	 * 
	 * @param pType
	 *            le type de message
	 */
	public void setTypeMessage(TypeMessage pType) {
		_typeMessage = pType;
	}

	/**
	 * Retourne la liste des messages pour chaque module
	 * 
	 * @return l'affichage des modules
	 */
	public List<MessageModuleMqttRest> getMessagesModules() {
		List<MessageModuleMqttRest> retour = new ArrayList<>();
		retour.add(_messagesModuleUnique);

		return retour;
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
		result = prime * result + ((_idEquipement == null) ? 0 : _idEquipement.hashCode());
		result = prime * result + ((_messagesModuleUnique == null) ? 0 : _messagesModuleUnique.hashCode());
		result = prime * result + ((_typeMessage == null) ? 0 : _typeMessage.hashCode());
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
		MessageEquipementModuleUniqueMqttRest other = (MessageEquipementModuleUniqueMqttRest) obj;
		if (_idEquipement == null) {
			if (other._idEquipement != null)
				return false;
		} else if (!_idEquipement.equals(other._idEquipement))
			return false;
		if (_messagesModuleUnique == null) {
			if (other._messagesModuleUnique != null)
				return false;
		} else if (!_messagesModuleUnique.equals(other._messagesModuleUnique))
			return false;
		if (_typeMessage != other._typeMessage)
			return false;
		return true;
	}
}
