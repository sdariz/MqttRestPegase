package signature.mqttRest.objetsPartages.etatEtPilotage;

/**
 * Message sur un équipement de type Barrière
 * @author SDARIZCUREN
 *
 */
public class MessageBarriereMqttRest extends MessageEquipementModuleUniqueMqttRest {
	
	public MessageBarriereMqttRest() {
		super();
	}
	
	/**
	 * Indique le type de l'équipement concerné par le message d'affichage
	 * 
	 * @return le type de l'équipement
	 */
	public TypeEquipement getTypeEquipement() {
		return TypeEquipement.BARRIERE;
	}
	
	/**
	 * Clone de l'objet courant
	 * 
	 * @return une copie de l'instance courante
	 */
	@Override
	public MessageBarriereMqttRest clone() {
		MessageBarriereMqttRest retour = new MessageBarriereMqttRest();
		
		if(getMessagesModuleUnique() != null) {
			retour.setMessagesModuleUnique(getMessagesModuleUnique().clone());
		}

		return retour;
	}
	
	@Override
	public int hashCode() {
		return 137894 * super.hashCode();
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
