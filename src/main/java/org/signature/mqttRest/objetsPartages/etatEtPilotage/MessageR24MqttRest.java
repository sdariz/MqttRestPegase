package org.signature.mqttRest.objetsPartages.etatEtPilotage;

/**
 * Message sur un équipement de type Feu R24
 * @author SDARIZCUREN
 *
 */
public class MessageR24MqttRest extends MessageEquipementModuleUniqueMqttRest {
	
	/**
	 * Construction du message
	 */
	public MessageR24MqttRest() {
		this("");
	}
	
	/**
	 * Construction du message
	 * @param pId l'identifiant de l'équipement
	 */
	public MessageR24MqttRest(String pId) {
		super(pId);
	}
	
	/**
	 * Indique le type de l'équipement concerné par le message d'affichage
	 * 
	 * @return le type de l'équipement
	 */
	public TypeEquipement getTypeEquipement() {
		return TypeEquipement.R24;
	}
	
	/**
	 * Copie de l'objet courant
	 * 
	 * @return une copie de l'instance courante
	 */
	@Override
	public MessageR24MqttRest duplique() {
		MessageR24MqttRest retour = new MessageR24MqttRest(getIdentifiantEquipement());
		retour.setTypeMessage(getTypeMessage());
		
		if(getMessagesModuleUnique() != null) {
			retour.setMessagesModuleUnique(getMessagesModuleUnique().duplique());
		}

		return retour;
	}
	
	@Override
	public int hashCode() {
		return 157894 * super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		
		return super.equals(obj);
	}
}
