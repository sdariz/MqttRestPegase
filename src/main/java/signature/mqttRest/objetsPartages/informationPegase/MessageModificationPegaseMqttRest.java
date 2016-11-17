package signature.mqttRest.objetsPartages.informationPegase;

import signature.mqttRest.objetsPartages.IMessageMqttRest;

/**
 * Évènements de modification sur Pegase
 * 
 * @author SDARIZCUREN
 *
 */
public class MessageModificationPegaseMqttRest implements IMessageMqttRest {
	private String _idExpediteur;
	private String _referenceMessage;
	
	/**
	 * Construction du message
	 */
	public MessageModificationPegaseMqttRest() {
		this("", "");
	}

	/**
	 * Construction du message
	 * 
	 * @param pIdExpediteur
	 *            l'identifiant de l'expéditeur
	 * @param pReferenceMessage
	 *            la référence du message, donnée par l'expéditeur
	 */
	public MessageModificationPegaseMqttRest(String pIdExpediteur, String pReferenceMessage) {
		_idExpediteur = pIdExpediteur;
		_referenceMessage = pReferenceMessage;
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
	

}
