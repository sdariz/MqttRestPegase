package signature.mqttRest.objetsPartages.etatEtPilotage;

/**
 * Message sur un équipement de type PPAD
 * 
 * @author SDARIZCUREN
 *
 */
public class MessagePpadMqttRest extends MessageEquipementModuleUniqueMqttRest {

	public MessagePpadMqttRest() {
		super();
	}

	/**
	 * Indique le type de l'équipement concerné par le message d'affichage
	 * 
	 * @return le type de l'équipement
	 */
	public TypeEquipement getTypeEquipement() {
		return TypeEquipement.PPAD;
	}

	/**
	 * Clone de l'objet courant
	 * 
	 * @return une copie de l'instance courante
	 */
	@Override
	public MessagePpadMqttRest clone() {
		MessagePpadMqttRest retour = new MessagePpadMqttRest();

		if (getMessagesModuleUnique() != null) {
			retour.setMessagesModuleUnique(getMessagesModuleUnique().clone());
		}

		return retour;
	}

	@Override
	public int hashCode() {
		return 117894 * super.hashCode();
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
