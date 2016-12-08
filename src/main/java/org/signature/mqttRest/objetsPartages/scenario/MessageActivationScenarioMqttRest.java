package org.signature.mqttRest.objetsPartages.scenario;

import org.signature.mqttRest.objetsPartages.IMessageMqttRest;

/**
 * Un message d'activation de sc�nario
 * 
 * @author SDARIZCUREN
 *
 */
public class MessageActivationScenarioMqttRest implements IMessageMqttRest {
	private String _idExpediteur;
	private String _referenceMessage;
	private MessageScenarioMqttRest _scenario;

	/**
	 * Construction du message
	 */
	public MessageActivationScenarioMqttRest() {
		this("", "", null);
	}

	/**
	 * Construction du message
	 * 
	 * @param pIdExpediteur
	 *            l'identifiant de l'exp�diteur
	 * @param pReferenceMessage
	 *            la r�f�rence du message, donn�e par l'exp�diteur
	 * @param pScenario
	 *            le sc�nario associ�
	 */
	public MessageActivationScenarioMqttRest(String pIdExpediteur, String pReferenceMessage,
			MessageScenarioMqttRest pScenario) {
		_idExpediteur = pIdExpediteur;
		_referenceMessage = pReferenceMessage;
		_scenario = pScenario;
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
	 * Acc�s au sc�nario associ� au message
	 * @return le sc�nario associ�
	 */
	public MessageScenarioMqttRest getScenario() {
		return _scenario;
	}
	
	/**
	 * Initialise le sc�nario associ� au message
	 * @param pScenario le sc�nario associ�
	 */
	public void setScenario(MessageScenarioMqttRest pScenario) {
		_scenario = pScenario;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((_idExpediteur == null) ? 0 : _idExpediteur.hashCode());
		result = prime * result + ((_referenceMessage == null) ? 0 : _referenceMessage.hashCode());
		result = prime * result + ((_scenario == null) ? 0 : _scenario.hashCode());
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
		MessageActivationScenarioMqttRest other = (MessageActivationScenarioMqttRest) obj;
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
		if (_scenario == null) {
			if (other._scenario != null)
				return false;
		} else if (!_scenario.equals(other._scenario))
			return false;
		return true;
	}
	
}
