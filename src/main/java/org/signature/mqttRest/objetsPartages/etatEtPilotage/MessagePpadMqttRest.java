package org.signature.mqttRest.objetsPartages.etatEtPilotage;

/**
 * Message sur un équipement de type PPAD
 * 
 * @author SDARIZCUREN
 *
 */
public class MessagePpadMqttRest extends MessageEquipementModuleUniqueMqttRest {

	/**
	 * Construction du message
	 */
	public MessagePpadMqttRest() {
		this("");
	}

	/**
	 * Construction du message
	 * 
	 * @param pId
	 *            l'identifiant de l'équipement
	 */
	public MessagePpadMqttRest(String pId) {
		super(pId);
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
	 * Copie de l'objet courant
	 * 
	 * @return une copie de l'instance courante
	 */
	@Override
	public MessagePpadMqttRest duplique() {
		MessagePpadMqttRest retour = new MessagePpadMqttRest(getIdentifiantEquipement());

		if (getMessagesModuleUnique() != null) {
			retour.setMessagesModuleUnique(getMessagesModuleUnique().duplique());
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
