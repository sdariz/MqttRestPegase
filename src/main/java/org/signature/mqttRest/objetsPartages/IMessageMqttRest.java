package org.signature.mqttRest.objetsPartages;

/**
 * Racine des messages échangés
 * @author SDARIZCUREN
 *
 */
public interface IMessageMqttRest {
	
	/**
	 * Donne l'identifiant de l'expediteur du message
	 * @return l'identifiant de l'expéditeur
	 */
	public String getIdentifiantExpediteur();
	
	/**
	 * Initialise l'identifiant de l'expéditeur
	 * @param pId l'identifiant de l'expéditeur
	 */
	public void setIdentifiantExpediteur(String pId);
	
	/**
	 * La référence du message, attribuée par l'expéditeur
	 * @return la référence de l'expéditeur sur le message
	 */
	public String getReferenceMessage();
	
	/**
	 * Initialise la référence du message pour son expéditeur
	 * @param pRef la référence du message
	 */
	public void setReferenceMessage(String pRef);

}
