package signature.mqttRest.objetsPartages.etatEtPilotage;

import java.util.ArrayList;
import java.util.List;

/**
 * Le message sur un équipement à module unique
 * @author SDARIZCUREN
 *
 */
public abstract class MessageEquipementModuleUniqueMqttRest implements IMessageAffichageEquipementMqttRest {
	private MessageModuleMqttRest _messagesModuleUnique;
	private TypeMessage _typeMessage;
	
	/**
	 * Construction du message
	 */
	public MessageEquipementModuleUniqueMqttRest() {
		_messagesModuleUnique = null;
		_typeMessage = TypeMessage.ETEINT;
	}
	
	/**
	 * Initialise les message sur l'équipement
	 * @param pMessages les messages de l'équipement
	 */
	public void setMessagesModuleUnique(MessageModuleMqttRest pMessages) {
		_messagesModuleUnique = pMessages;
	}
	
	/**
	 * Retourne les messages sur l'équipement
	 * @return les messages de l'équipement
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
	 * @param pType le type de message
	 */
	public void setTypeMessage(TypeMessage pType) {
		_typeMessage = pType;
	}
	
	/**
	 * Retourne la liste des messages pour chaque module
	 * @return l'affichage des modules
	 */
	public List<MessageModuleMqttRest> getMessagesModules() {
		List<MessageModuleMqttRest> retour = new ArrayList<>();
		retour.add(_messagesModuleUnique);
		
		return retour;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((_messagesModuleUnique == null) ? 0 : _messagesModuleUnique.hashCode());
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
		MessageEquipementModuleUniqueMqttRest other = (MessageEquipementModuleUniqueMqttRest) obj;
		if (_messagesModuleUnique == null) {
			if (other._messagesModuleUnique != null)
				return false;
		} else if (!_messagesModuleUnique.equals(other._messagesModuleUnique))
			return false;
		return true;
	}
}
