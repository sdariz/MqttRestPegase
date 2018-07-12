package org.signature.mqttRest.objetsPartages.etatEtPilotage;

import java.util.ArrayList;
import java.util.List;

/**
 * Le message sur un équipement PPLMV
 * 
 * @author SDARIZCUREN
 *
 */
public class MessagePplmvMqttRest implements IMessageAffichageEquipementMqttRest {
	private MessageModuleMqttRest _messagesPanonceauHaut;
	private MessageModuleMqttRest _messagesPanonceauBas;
	private MessageModuleMqttRest _messagesPictogramme;
	private MessageModuleMqttRest _messagesFlashs;
	private TypeMessage _typeMessage;
	private String _idEquipement;
	private int _priorite;

	/**
	 * Construction du message
	 */
	public MessagePplmvMqttRest() {
		this("");
	}

	/**
	 * Construction du message
	 * 
	 * @param pId
	 *            l'identifiant de l'équipement
	 */
	public MessagePplmvMqttRest(String pId) {
		_idEquipement = pId;

		_messagesPanonceauHaut = null;
		_messagesPanonceauBas = null;
		_messagesPictogramme = null;
		_messagesFlashs = null;
		_typeMessage = TypeMessage.ETEINT;
	}

	/**
	 * Donne l'identifant unique associé à l'équipement
	 * 
	 * @return l'identifant unique de l'équipement
	 */
	@Override
	public String getIdentifiantEquipement() {
		return _idEquipement;
	}

	/**
	 * Initialise l'identifant unique associé à l'équipement
	 * 
	 * @param pId
	 *            l'identifant unique de l'équipement
	 */
	@Override
	public void setIdentifiantEquipement(String pId) {
		_idEquipement = pId;
	}

	/**
	 * Indique le type de l'équipement concerné par le message d'affichage
	 * 
	 * @return le type de l'équipement
	 */
	@Override
	public TypeEquipement getTypeEquipement() {
		return TypeEquipement.PPLMV;
	}

	/**
	 * Indique le type du message : message d'exploitation, de mise au neutre
	 * 
	 * @return le type de message
	 */
	@Override
	public TypeMessage getTypeMessage() {
		return _typeMessage;
	}
	
	/**
	 * Initialise le type du message : message d'exploitation, de mise au neutre
	 * 
	 * @param pType
	 *            le type de message
	 */
	@Override
	public void setTypeMessage(TypeMessage pType) {
		_typeMessage = pType;
	}
	
	/**
	 * Retourne la priorité du message. C'est une valeur positive, avec 0 la priorité la
	 * plus basse
	 * 
	 * @return la priorité du message (positive ou nulle)
	 */
	@Override
	public int getPrioriteMessage() {
		return _priorite;
	}

	/**
	 * Initialise la priorité du message. C'est une valeur positive, avec 0 la priorité
	 * la plus basse
	 * 
	 * @param pPriorite
	 *            la priorité du message (positive ou nulle)
	 */
	@Override
	public void setPrioriteMessage(int pPriorite) {
		if(pPriorite < 0) {
			pPriorite = 0;
		}
		
		_priorite = pPriorite;
	}

	/**
	 * Indique si le PPLMV à un panonceau haut
	 * 
	 * @return true si présent
	 */
	public boolean avecPanonceauHaut() {
		return _messagesPanonceauHaut != null;
	}

	/**
	 * Indique si le PPLMV à des flashs
	 * 
	 * @return true si présent
	 */
	public boolean avecFlashs() {
		return _messagesFlashs != null;
	}

	/**
	 * Initialise le message du panonceau haut
	 * 
	 * @param pMsg
	 *            le nouveau message
	 */
	public void setMessagePanonceauHaut(MessageModuleMqttRest pMsg) {
		_messagesPanonceauHaut = pMsg;
	}

	/**
	 * Retourne le message du panonceau haut
	 * 
	 * @return le message
	 */
	public MessageModuleMqttRest getMessagePanonceauHaut() {
		return _messagesPanonceauHaut;
	}

	/**
	 * Initialise le message du panonceau bas
	 * 
	 * @param pMsg
	 *            le nouveau message
	 */
	public void setMessagePanonceauBas(MessageModuleMqttRest pMsg) {
		_messagesPanonceauBas = pMsg;
	}

	/**
	 * Retourne le message du panonceau bas
	 * 
	 * @return le message
	 */
	public MessageModuleMqttRest getMessagePanonceauBas() {
		return _messagesPanonceauBas;
	}

	/**
	 * Initialise le message du pictogramme
	 * 
	 * @param pMsg
	 *            le nouveau message
	 */
	public void setMessagePictogramme(MessageModuleMqttRest pMsg) {
		_messagesPictogramme = pMsg;
	}

	/**
	 * Retourne le message du pictogramme
	 * 
	 * @return le message
	 */
	public MessageModuleMqttRest getMessagePictogramme() {
		return _messagesPictogramme;
	}

	/**
	 * Initialise le message des flashs
	 * 
	 * @param pMsg
	 *            le nouveau message
	 */
	public void setMessageFlashs(MessageModuleMqttRest pMsg) {
		_messagesFlashs = pMsg;
	}

	/**
	 * Retourne le message des flashs
	 * 
	 * @return le message
	 */
	public MessageModuleMqttRest getMessageFlashs() {
		return _messagesFlashs;
	}

	/**
	 * Retourne la liste des messages pour chaque module. Dans l'ordre: panonceau
	 * bas, pictogramme, panonceau haut, flash. Les éléments absents ne sont pas
	 * positionnés
	 * 
	 * @return l'affichage des modules
	 */
	@Override
	public List<MessageModuleMqttRest> getMessagesModules() {
		// Lignes puis panonceaux puis pictogrammes puis flashs
		List<MessageModuleMqttRest> retour = new ArrayList<>();

		if (_messagesPanonceauBas != null) {
			retour.add(_messagesPanonceauBas);
		}

		if (_messagesPictogramme != null) {
			retour.add(_messagesPictogramme);
		}

		if (_messagesPanonceauHaut != null) {
			retour.add(_messagesPanonceauHaut);
		}

		if (_messagesFlashs != null) {
			retour.add(_messagesFlashs);
		}

		return retour;
	}

	/**
	 * Copie de l'objet courant
	 * 
	 * @return une copie de l'instance courante
	 */
	@Override
	public MessagePplmvMqttRest duplique() {
		MessagePplmvMqttRest retour = new MessagePplmvMqttRest(_idEquipement);
		retour.setTypeMessage(_typeMessage);

		if (_messagesPanonceauBas != null) {
			retour._messagesPanonceauBas = _messagesPanonceauBas.duplique();
		}

		if (_messagesPictogramme != null) {
			retour._messagesPictogramme = _messagesPictogramme.duplique();
		}

		if (_messagesPanonceauHaut != null) {
			retour._messagesPanonceauHaut = _messagesPanonceauHaut.duplique();
		}

		if (_messagesFlashs != null) {
			retour._messagesFlashs = _messagesFlashs.duplique();
		}

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
		result = prime * result + ((_messagesFlashs == null) ? 0 : _messagesFlashs.hashCode());
		result = prime * result + ((_messagesPanonceauBas == null) ? 0 : _messagesPanonceauBas.hashCode());
		result = prime * result + ((_messagesPanonceauHaut == null) ? 0 : _messagesPanonceauHaut.hashCode());
		result = prime * result + ((_messagesPictogramme == null) ? 0 : _messagesPictogramme.hashCode());
		result = prime * result + ((_typeMessage == null) ? 0 : _typeMessage.hashCode());
		result = prime * result + _priorite;
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
		MessagePplmvMqttRest other = (MessagePplmvMqttRest) obj;
		if (_idEquipement == null) {
			if (other._idEquipement != null)
				return false;
		} else if (!_idEquipement.equals(other._idEquipement))
			return false;
		if (_messagesFlashs == null) {
			if (other._messagesFlashs != null)
				return false;
		} else if (!_messagesFlashs.equals(other._messagesFlashs))
			return false;
		if (_messagesPanonceauBas == null) {
			if (other._messagesPanonceauBas != null)
				return false;
		} else if (!_messagesPanonceauBas.equals(other._messagesPanonceauBas))
			return false;
		if (_messagesPanonceauHaut == null) {
			if (other._messagesPanonceauHaut != null)
				return false;
		} else if (!_messagesPanonceauHaut.equals(other._messagesPanonceauHaut))
			return false;
		if (_messagesPictogramme == null) {
			if (other._messagesPictogramme != null)
				return false;
		} else if (!_messagesPictogramme.equals(other._messagesPictogramme))
			return false;
		if (_typeMessage != other._typeMessage)
			return false;
		if (_priorite != other._priorite)
			return false;
		return true;
	}
}
