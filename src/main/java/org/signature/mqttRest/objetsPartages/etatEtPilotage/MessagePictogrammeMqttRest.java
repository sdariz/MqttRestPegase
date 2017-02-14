package org.signature.mqttRest.objetsPartages.etatEtPilotage;

/**
 * Message sur un équipement de type Pictogramme
 * 
 * @author SDARIZCUREN
 *
 */
public class MessagePictogrammeMqttRest extends MessageEquipementModuleUniqueMqttRest {

	/**
	 * Construction du message
	 */
	public MessagePictogrammeMqttRest() {
		this("");
	}

	/**
	 * Construction du message
	 * 
	 * @param pId
	 *            l'identifiant de l'équipement
	 */
	public MessagePictogrammeMqttRest(String pId) {
		super(pId);
	}

	/**
	 * Indique le type de l'équipement concerné par le message d'affichage
	 * 
	 * @return le type de l'équipement
	 */
	public TypeEquipement getTypeEquipement() {
		return TypeEquipement.PICTOGRAMME;
	}

	/**
	 * Copie de l'objet courant
	 * 
	 * @return une copie de l'instance courante
	 */
	@Override
	public MessagePictogrammeMqttRest duplique() {
		MessagePictogrammeMqttRest retour = new MessagePictogrammeMqttRest(getIdentifiantEquipement());

		if (getMessagesModuleUnique() != null) {
			retour.setMessagesModuleUnique(getMessagesModuleUnique().duplique());
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
