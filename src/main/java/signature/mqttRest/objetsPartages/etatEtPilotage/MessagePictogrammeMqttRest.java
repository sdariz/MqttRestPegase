package signature.mqttRest.objetsPartages.etatEtPilotage;

/**
 * Message sur un équipement de type Pictogramme
 * @author SDARIZCUREN
 *
 */
public class MessagePictogrammeMqttRest extends MessageEquipementModuleUniqueMqttRest {
	
	public MessagePictogrammeMqttRest() {
		super();
	}
	
	/**
	 * Indique le type de l'équipement concerné par le message d'affichage
	 * 
	 * @return le type de l'équipement
	 */
	public TYPE_EQUIPEMENT getTypeEquipement() {
		return TYPE_EQUIPEMENT.PICTOGRAMME;
	}
	
	/**
	 * Clone de l'objet courant
	 * 
	 * @return une copie de l'instance courante
	 */
	@Override
	public MessagePictogrammeMqttRest clone() {
		MessagePictogrammeMqttRest retour = new MessagePictogrammeMqttRest();
		
		if(getMessagesModuleUnique() != null) {
			retour.setMessagesModuleUnique(getMessagesModuleUnique().clone());
		}

		return retour;
	}
	
	@Override
	public int hashCode() {
		return 167894 * super.hashCode();
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
