package signature.mqttRest.objetsPartages.etatEtPilotage;

import signature.mqttRest.objetsPartages.IMessageMqttRest;

/**
 * Message de pilotage d'un scénario
 * 
 * @author SDARIZCUREN
 *
 */
public class MessagePilotageScenarioMqttRest implements IMessageMqttRest {
	private String _idExpediteur;
	private String _referenceMessage;
	private String _idScenario;

	/**
	 * Construction du message de pilotage
	 */
	public MessagePilotageScenarioMqttRest() {
		this("", "", "");
	}

	/**
	 * Construction du message de pilotage
	 * 
	 * @param pIdExpediteur
	 *            l'identifiant de l'expéditeur
	 * @param pReferenceMessage
	 *            la référence du message, donnée par l'expéditeur
	 * @param pIdScenario
	 *            l'identifiant unique du scénario à piloter
	 */
	public MessagePilotageScenarioMqttRest(String pIdExpediteur, String pReferenceMessage, String pIdScenario) {
		_idExpediteur = pIdExpediteur;
		_referenceMessage = pReferenceMessage;
		_idScenario = pIdScenario;
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
	 * Donne l'identifiant du scénario
	 * 
	 * @return l'identifiant du scénario
	 */
	public String getIdentifiantScenario() {
		return _idScenario;
	}

	/**
	 * Initialise l'identifiant du scénario
	 * 
	 * @param pId
	 *            l'identifiant du scénario
	 */
	public void setIdentifiantScenario(String pId) {
		_idScenario = pId;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((_idExpediteur == null) ? 0 : _idExpediteur.hashCode());
		result = prime * result + ((_idScenario == null) ? 0 : _idScenario.hashCode());
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
		MessagePilotageScenarioMqttRest other = (MessagePilotageScenarioMqttRest) obj;
		if (_idExpediteur == null) {
			if (other._idExpediteur != null)
				return false;
		} else if (!_idExpediteur.equals(other._idExpediteur))
			return false;
		if (_idScenario == null) {
			if (other._idScenario != null)
				return false;
		} else if (!_idScenario.equals(other._idScenario))
			return false;
		if (_referenceMessage == null) {
			if (other._referenceMessage != null)
				return false;
		} else if (!_referenceMessage.equals(other._referenceMessage))
			return false;
		return true;
	}
}
