package org.signature.mqttRest.objetsPartages.etatEtPilotage;

import java.util.ArrayList;
import java.util.List;

/**
 * Description d'un message pour PMV
 * 
 * @author SDARIZCUREN
 *
 */
public class MessagePmvMqttRest implements IMessageAffichageEquipementMqttRest {
	private List<MessageModuleMqttRest> _messagesLignes;
	private List<MessageModuleMqttRest> _messagesPanonceaux;
	private List<MessageModuleMqttRest> _messagesPictogrammes;
	private MessageModuleMqttRest _messagesFlashs;
	private TypeMessage _typeMessage;
	private String _idEquipement;

	/**
	 * Construction du message
	 */
	public MessagePmvMqttRest() {
		this("");
	}

	/**
	 * Construction du message
	 * 
	 * @param pId
	 *            l'identifiant de l'équipement
	 */
	public MessagePmvMqttRest(String pId) {
		_idEquipement = pId;

		_messagesLignes = new ArrayList<>();
		_messagesPanonceaux = new ArrayList<>();
		_messagesPictogrammes = new ArrayList<>();
		_messagesFlashs = null;
		_typeMessage = TypeMessage.ETEINT;
	}

	/**
	 * Donne l'identifant unique associé à l'équipement
	 * 
	 * @return l'identifant unique de l'équipement
	 */
	public String getIdentifiantEquipement() {
		return _idEquipement;
	}

	/**
	 * Initialise l'identifant unique associé à l'équipement
	 * 
	 * @param pId
	 *            l'identifant unique de l'équipement
	 */
	public void setIdentifiantEquipement(String pId) {
		_idEquipement = pId;
	}

	/**
	 * Indique le type de l'équipement concerné par le message d'affichage
	 * 
	 * @return le type de l'équipement
	 */
	public TypeEquipement getTypeEquipement() {
		return TypeEquipement.PMV;
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
	 * Indique le nombre de lignes du panneau
	 * 
	 * @return le nombre de lignes
	 */
	public int getNombreLignes() {
		return _messagesLignes.size();
	}

	/**
	 * Indique le nombre de panonceaux du panneau
	 * 
	 * @return le nombre de panonceaux
	 */
	public int getNombrePanonceaux() {
		return _messagesPanonceaux.size();
	}

	/**
	 * Indique le nombre de pictogrammes du panneau
	 * 
	 * @return le nombre de pictogrammes
	 */
	public int getNombrePictogrammes() {
		return _messagesPictogrammes.size();
	}

	/**
	 * Indique si le PMV possède des feux flashs
	 * 
	 * @return true si les feux flashs sont présents
	 */
	public boolean avecFeuxFlashs() {
		return _messagesFlashs != null;
	}

	/**
	 * Retourne les messages pour chaque ligne du panneau
	 * 
	 * @return les messages par ligne
	 */
	public List<MessageModuleMqttRest> getMessagesLignes() {
		return _messagesLignes;
	}

	/**
	 * Initialisation des messages pour chaque ligne du panneau
	 * 
	 * @param pMessages
	 *            les messages pour chaque ligne
	 */
	public void setMessagesLignes(List<MessageModuleMqttRest> pMessages) {
		_messagesLignes = pMessages;
	}

	/**
	 * Retourne les messages pour chaque panonceau
	 * 
	 * @return les messages de chaque panonceau
	 */
	public List<MessageModuleMqttRest> getMessagesPanonceaux() {
		return _messagesPanonceaux;
	}

	/**
	 * Initialisation des messages pour chaque panonceau
	 * 
	 * @return les messages de chaque panonceau
	 */
	public void setMessagesPanonceaux(List<MessageModuleMqttRest> pMessages) {
		_messagesPanonceaux = pMessages;
	}

	/**
	 * Retourne les messages pour chaque pictogramme
	 * 
	 * @return les messages de chaque pictogramme
	 */
	public List<MessageModuleMqttRest> getMessagesPictogrammes() {
		return _messagesPictogrammes;
	}

	/**
	 * Initialisation des messages pour chaque pictogramme
	 * 
	 * @return les messages de chaque pictogramme
	 */
	public void setMessagesPictogrammes(List<MessageModuleMqttRest> pMessages) {
		_messagesPictogrammes = pMessages;
	}

	/**
	 * Retourne les messages des feux flashs
	 * 
	 * @return les messages des feux flashs
	 */
	public MessageModuleMqttRest getMessagesFlashs() {
		return _messagesFlashs;
	}

	/**
	 * Initialisation des messages des feux flashs
	 * 
	 * @return les messages des feux flashs
	 */
	public void setMessagesFlashs(MessageModuleMqttRest pMessage) {
		_messagesFlashs = pMessage;
	}

	/**
	 * Retourne la liste des messages pour chaque module. Dans l'ordre: les
	 * lignes, les panonceaux, les pictogrammes, et les flash. Les éléments
	 * absents ne sont pas positionnés
	 * 
	 * @return l'affichage des modules
	 */
	@Override
	public List<MessageModuleMqttRest> getMessagesModules() {
		// Lignes puis panonceaux puis pictogrammes puis flashs
		List<MessageModuleMqttRest> retour = new ArrayList<>();

		retour.addAll(_messagesLignes);
		retour.addAll(_messagesPanonceaux);
		retour.addAll(_messagesPictogrammes);

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
	public MessagePmvMqttRest duplique() {
		MessagePmvMqttRest retour = new MessagePmvMqttRest(_idEquipement);

		_messagesLignes.forEach(m -> retour._messagesLignes.add(m.duplique()));
		_messagesPanonceaux.forEach(m -> retour._messagesPanonceaux.add(m.duplique()));
		_messagesPictogrammes.forEach(m -> retour._messagesPictogrammes.add(m.duplique()));

		if (_messagesFlashs != null) {
			retour._messagesFlashs = _messagesFlashs.duplique();
		}

		return retour;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((_idEquipement == null) ? 0 : _idEquipement.hashCode());
		result = prime * result + ((_messagesFlashs == null) ? 0 : _messagesFlashs.hashCode());
		result = prime * result + ((_messagesLignes == null) ? 0 : _messagesLignes.hashCode());
		result = prime * result + ((_messagesPanonceaux == null) ? 0 : _messagesPanonceaux.hashCode());
		result = prime * result + ((_messagesPictogrammes == null) ? 0 : _messagesPictogrammes.hashCode());
		result = prime * result + ((_typeMessage == null) ? 0 : _typeMessage.hashCode());
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
		MessagePmvMqttRest other = (MessagePmvMqttRest) obj;
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
		if (_messagesLignes == null) {
			if (other._messagesLignes != null)
				return false;
		} else if (!_messagesLignes.equals(other._messagesLignes))
			return false;
		if (_messagesPanonceaux == null) {
			if (other._messagesPanonceaux != null)
				return false;
		} else if (!_messagesPanonceaux.equals(other._messagesPanonceaux))
			return false;
		if (_messagesPictogrammes == null) {
			if (other._messagesPictogrammes != null)
				return false;
		} else if (!_messagesPictogrammes.equals(other._messagesPictogrammes))
			return false;
		if (_typeMessage != other._typeMessage)
			return false;
		return true;
	}
}
