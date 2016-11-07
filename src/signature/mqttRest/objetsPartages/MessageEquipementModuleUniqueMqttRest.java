package signature.mqttRest.objetsPartages;

import java.util.ArrayList;
import java.util.List;

/**
 * Le message sur un équipement à module unique
 * @author SDARIZCUREN
 *
 */
public class MessageEquipementModuleUniqueMqttRest implements IMessageAffichageEquipement {
	private TYPE_EQUIPEMENT _typeEquipement;
	private MessageModuleMqttRest _messagesModuleUnique;
	
	/**
	 * Construction du message
	 */
	public MessageEquipementModuleUniqueMqttRest(TYPE_EQUIPEMENT pType) {
		_typeEquipement = pType;
		_messagesModuleUnique = null;
	}
	
	/**
	 * Indique le type de l'équipement concerné par le message d'affichage
	 * 
	 * @return le type de l'équipement
	 */
	public TYPE_EQUIPEMENT getTypeEquipement() {
		return _typeEquipement;
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
	 * Retourne la liste des messages pour chaque module
	 * @return l'affichage des modules
	 */
	public List<MessageModuleMqttRest> getMessagesModules() {
		List<MessageModuleMqttRest> retour = new ArrayList<>();
		retour.add(_messagesModuleUnique);
		
		return retour;
	}
	
	/**
	 * Clone de l'objet courant
	 * 
	 * @return une copie de l'instance courante
	 */
	@Override
	public MessageEquipementModuleUniqueMqttRest clone() {
		MessageEquipementModuleUniqueMqttRest retour = new MessageEquipementModuleUniqueMqttRest(_typeEquipement);
		
		if(_messagesModuleUnique != null) {
			retour._messagesModuleUnique = _messagesModuleUnique.clone();
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
		result = prime * result + ((_messagesModuleUnique == null) ? 0 : _messagesModuleUnique.hashCode());
		result = prime * result + ((_typeEquipement == null) ? 0 : _typeEquipement.hashCode());
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
		if (_typeEquipement != other._typeEquipement)
			return false;
		return true;
	}
}
