package org.signature.mqttRest.objetsPartages.etatEtPilotage;

/**
 * Message sur un ?quipement de type BRA
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
	 *            l'identifiant de l'?quipement
	 */
	public MessageBraMqttRest(String pId) {
		super(pId);
	}

	/**
	 * Indique le type de l'?quipement concern? par le message d'affichage
	 * 
	 * @return le type de l'?quipement
	 */
	@Override
	public TypeEquipement getTypeEquipement() {
		return TypeEquipement.BRA;
	}

	/**
	 * Copie de l'objet courant
	 * 
	 * @return une copie de l'instance courante
	 */
	@Override
	public MessageBraMqttRest duplique() {
		MessageBraMqttRest retour = new MessageBraMqttRest(getIdentifiantEquipement());
		retour.setTypeMessage(getTypeMessage());

		if (getMessagesModuleUnique() != null) {
			retour.setMessagesModuleUnique(getMessagesModuleUnique().duplique());
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
