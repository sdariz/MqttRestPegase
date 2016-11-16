package signature.mqttRest.objetsPartages.etatEtPilotage;

import java.util.ArrayList;
import java.util.List;


/**
 * Description d'un message pour PMV
 * 
 * @author SDARIZCUREN
 *
 */
public class MessagePmvMqttRest implements IMessageAffichageEquipement {
	private List<MessageModuleMqttRest> _messagesLignes;
	private List<MessageModuleMqttRest> _messagesPanonceaux;
	private List<MessageModuleMqttRest> _messagesPictogrammes;
	private MessageModuleMqttRest _messagesFlashs;

	/**
	 * Construction du message
	 */
	public MessagePmvMqttRest() {
		_messagesLignes = new ArrayList<>();
		_messagesPanonceaux = new ArrayList<>();
		_messagesPictogrammes = new ArrayList<>();
		_messagesFlashs = null;
	}
	
	/**
	 * Indique le type de l'�quipement concern� par le message d'affichage
	 * 
	 * @return le type de l'�quipement
	 */
	public TypeEquipement getTypeEquipement() {
		return TypeEquipement.PMV;
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
	 * Indique si le PMV poss�de des feux flashs
	 * 
	 * @return true si les feux flashs sont pr�sents
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
	 * lignes, les panonceaux, les pictogrammes, et les flash. Les �l�ments
	 * absents ne sont pas positionn�s
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
	 * Clone de l'objet courant
	 * 
	 * @return une copie de l'instance courante
	 */
	@Override
	public MessagePmvMqttRest clone() {
		MessagePmvMqttRest retour = new MessagePmvMqttRest();

		_messagesLignes.forEach(m -> retour._messagesLignes.add(m.clone()));
		_messagesPanonceaux.forEach(m -> retour._messagesPanonceaux.add(m.clone()));
		_messagesPictogrammes.forEach(m -> retour._messagesPictogrammes.add(m.clone()));

		if (_messagesFlashs != null) {
			retour._messagesFlashs = _messagesFlashs.clone();
		}

		return retour;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((_messagesFlashs == null) ? 0 : _messagesFlashs.hashCode());
		result = prime * result + ((_messagesLignes == null) ? 0 : _messagesLignes.hashCode());
		result = prime * result + ((_messagesPanonceaux == null) ? 0 : _messagesPanonceaux.hashCode());
		result = prime * result + ((_messagesPictogrammes == null) ? 0 : _messagesPictogrammes.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MessagePmvMqttRest other = (MessagePmvMqttRest) obj;
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
		return true;
	}
}
