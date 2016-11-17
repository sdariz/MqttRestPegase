package signature.mqttRest.objetsPartages.informationPegase;

import signature.mqttRest.objetsPartages.IMessageMqttRest;

/**
 * �v�nements de modification sur Pegase
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
	 *            l'identifiant de l'exp�diteur
	 * @param pReferenceMessage
	 *            la r�f�rence du message, donn�e par l'exp�diteur
	 */
	public MessageModificationPegaseMqttRest(String pIdExpediteur, String pReferenceMessage) {
		_idExpediteur = pIdExpediteur;
		_referenceMessage = pReferenceMessage;
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
	

}
