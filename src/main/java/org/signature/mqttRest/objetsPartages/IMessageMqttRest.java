package org.signature.mqttRest.objetsPartages;

/**
 * Racine des messages �chang�s
 * @author SDARIZCUREN
 *
 */
public interface IMessageMqttRest {
	
	/**
	 * Donne l'identifiant de l'expediteur du message
	 * @return l'identifiant de l'exp�diteur
	 */
	public String getIdentifiantExpediteur();
	
	/**
	 * Initialise l'identifiant de l'exp�diteur
	 * @param pId l'identifiant de l'exp�diteur
	 */
	public void setIdentifiantExpediteur(String pId);
	
	/**
	 * La r�f�rence du message, attribu�e par l'exp�diteur
	 * @return la r�f�rence de l'exp�diteur sur le message
	 */
	public String getReferenceMessage();
	
	/**
	 * Initialise la r�f�rence du message pour son exp�diteur
	 * @param pRef la r�f�rence du message
	 */
	public void setReferenceMessage(String pRef);

}
