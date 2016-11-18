package signature.mqttRest.objetsPartages.etatEtPilotage;

/**
 * Message sur un équipement de type BRA
 * 
 * @author SDARIZCUREN
 *
 */
public class MessageBraMqttRest extends MessageEquipementModuleUniqueMqttRest {

	/**
	 * Construction du message
	 */
	public MessageBraMqttRest() {
		this("");
	}

	/**
	 * Construction du message
	 * 
	 * @param pId
	 *            l'identifiant de l'équipement
	 */
	public MessageBraMqttRest(String pId) {
		super(pId);
	}

	/**
	 * Indique le type de l'équipement concerné par le message d'affichage
	 * 
	 * @return le type de l'équipement
	 */
	public TypeEquipement getTypeEquipement() {
		return TypeEquipement.BRA;
	}

	/**
	 * Clone de l'objet courant
	 * 
	 * @return une copie de l'instance courante
	 */
	@Override
	public MessageBraMqttRest clone() {
		MessageBraMqttRest retour = new MessageBraMqttRest(getIdentifiantEquipement());

		if (getMessagesModuleUnique() != null) {
			retour.setMessagesModuleUnique(getMessagesModuleUnique().clone());
		}

		return retour;
	}

	@Override
	public int hashCode() {
		return 127894 * super.hashCode();
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
