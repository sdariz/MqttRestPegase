package org.signature.mqttRest.objetsPartages.etatEtPilotage;

/**
 * Message sur un équipement de type Feu de régulation
 * @author SDARIZCUREN
 *
 */
public class MessageFeuRegulationMqttRest extends MessageEquipementModuleUniqueMqttRest {
	
	/**
	 * Construction du message
	 */
	public MessageFeuRegulationMqttRest() {
		this("");
	}
	
	/**
	 * Construction du message
	 * @param pId l'identifiant de l'équipement
	 */
	public MessageFeuRegulationMqttRest(String pId) {
		super(pId);
	}
	
	/**
	 * Indique le type de l'équipement concerné par le message d'affichage
	 * 
	 * @return le type de l'équipement
	 */
	@Override
	public TypeEquipement getTypeEquipement() {
		return TypeEquipement.FEU_REGULATION;
	}
	
	/**
	 * Copie de l'objet courant
	 * 
	 * @return une copie de l'instance courante
	 */
	@Override
	public MessageFeuRegulationMqttRest duplique() {
		MessageFeuRegulationMqttRest retour = new MessageFeuRegulationMqttRest(getIdentifiantEquipement());
		retour.setTypeMessage(getTypeMessage());
		
		if(getMessagesModuleUnique() != null) {
			retour.setMessagesModuleUnique(getMessagesModuleUnique().duplique());
		}

		return retour;
	}
	
	@Override
	public int hashCode() {
		return 12789654 * super.hashCode();
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
