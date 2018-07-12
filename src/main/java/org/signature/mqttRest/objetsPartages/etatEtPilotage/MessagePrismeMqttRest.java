package org.signature.mqttRest.objetsPartages.etatEtPilotage;

/**
 * Message sur un équipement de type Prisme
 * @author SDARIZCUREN
 *
 */
public class MessagePrismeMqttRest extends MessageEquipementModuleUniqueMqttRest {
	
	/**
	 * Construction du message
	 */
	public MessagePrismeMqttRest() {
		this("");
	}
	
	/**
	 * Construction du message
	 * @param pId l'identifiant de l'équipement
	 */
	public MessagePrismeMqttRest(String pId) {
		super(pId);
	}
	
	/**
	 * Indique le type de l'équipement concerné par le message d'affichage
	 * 
	 * @return le type de l'équipement
	 */
	@Override
	public TypeEquipement getTypeEquipement() {
		return TypeEquipement.PRISME;
	}
	
	/**
	 * Copie de l'objet courant
	 * 
	 * @return une copie de l'instance courante
	 */
	@Override
	public MessagePrismeMqttRest duplique() {
		MessagePrismeMqttRest retour = new MessagePrismeMqttRest(getIdentifiantEquipement());
		retour.setTypeMessage(getTypeMessage());
		
		if(getMessagesModuleUnique() != null) {
			retour.setMessagesModuleUnique(getMessagesModuleUnique().duplique());
		}

		return retour;
	}
	
	@Override
	public int hashCode() {
		return 147894 * super.hashCode();
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
