package signature.mqttRest.objetsPartages.etatEtPilotage;

/**
 * Message sur un équipement de type Feu R24
 * @author SDARIZCUREN
 *
 */
public class MessageR24MqttRest extends MessageEquipementModuleUniqueMqttRest {
	
	public MessageR24MqttRest() {
		super();
	}
	
	/**
	 * Indique le type de l'équipement concerné par le message d'affichage
	 * 
	 * @return le type de l'équipement
	 */
	public TYPE_EQUIPEMENT getTypeEquipement() {
		return TYPE_EQUIPEMENT.R24;
	}
	
	/**
	 * Clone de l'objet courant
	 * 
	 * @return une copie de l'instance courante
	 */
	@Override
	public MessageR24MqttRest clone() {
		MessageR24MqttRest retour = new MessageR24MqttRest();
		
		if(getMessagesModuleUnique() != null) {
			retour.setMessagesModuleUnique(getMessagesModuleUnique().clone());
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
