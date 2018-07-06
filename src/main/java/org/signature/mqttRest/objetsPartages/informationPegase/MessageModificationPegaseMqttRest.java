package org.signature.mqttRest.objetsPartages.informationPegase;

import org.signature.mqttRest.objetsPartages.IMessageMqttRest;

/**
 * Évènements de modification sur Pegase
 * 
 * @author SDARIZCUREN
 *
 */
public class MessageModificationPegaseMqttRest implements IMessageMqttRest {
	private String _idExpediteur;
	private String _referenceMessage;
	private TypeModification _typeModif;

	public enum TypeModification {
		INCONNU, MODIFICATION_LISTE_SCENARIOS, MODIFICATION_LISTE_MESSAGES_BIBLIOTHEQUE_PMV,
		MODIFICATION_LISTE_UTILISATEURS, MODIFICATION_PROPRIETES_EQUIPEMENTS, MODIFICATION_FICHIER_PROPRIETES_PEGASE,
		ARRET_APPLICATION_PEGASE
	}

	/**
	 * Construction du message
	 */
	public MessageModificationPegaseMqttRest() {
		this("", "", TypeModification.INCONNU);
	}

	/**
	 * Construction du message
	 * 
	 * @param pIdExpediteur
	 *            l'identifiant de l'expéditeur
	 * @param pReferenceMessage
	 *            la référence du message, donnée par l'expéditeur
	 * @param pType
	 *            Le type de modification
	 */
	public MessageModificationPegaseMqttRest(String pIdExpediteur, String pReferenceMessage, TypeModification pType) {
		_idExpediteur = pIdExpediteur;
		_referenceMessage = pReferenceMessage;
		_typeModif = pType;
	}

	/**
	 * Donne l'identifiant de l'expediteur du message
	 * 
	 * @return l'identifiant de l'expéditeur
	 */
	public String getIdentifiantExpediteur() {
		return _idExpediteur;
	}

	/**
	 * Initialise l'identifiant de l'expéditeur
	 * 
	 * @param pId
	 *            l'identifiant de l'expéditeur
	 */
	public void setIdentifiantExpediteur(String pId) {
		_idExpediteur = pId;
	}

	/**
	 * La référence du message, attribuée par l'expéditeur
	 * 
	 * @return la référence de l'expéditeur sur le message
	 */
	public String getReferenceMessage() {
		return _referenceMessage;
	}

	/**
	 * Initialise la référence du message pour son expéditeur
	 * 
	 * @param pRef
	 *            la référence du message
	 */
	public void setReferenceMessage(String pRef) {
		_referenceMessage = pRef;
	}

	/**
	 * Donne le type de modification effectué
	 * 
	 * @return le type de modification
	 */
	public TypeModification getTypeModification() {
		return _typeModif;
	}

	/**
	 * Initialise le type de modification effectué
	 * 
	 * @param pTypeModif
	 *            le type de modification
	 */
	public void setTypeModification(TypeModification pTypeModif) {
		_typeModif = pTypeModif;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((_idExpediteur == null) ? 0 : _idExpediteur.hashCode());
		result = prime * result + ((_referenceMessage == null) ? 0 : _referenceMessage.hashCode());
		result = prime * result + ((_typeModif == null) ? 0 : _typeModif.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MessageModificationPegaseMqttRest other = (MessageModificationPegaseMqttRest) obj;
		if (_idExpediteur == null) {
			if (other._idExpediteur != null)
				return false;
		} else if (!_idExpediteur.equals(other._idExpediteur))
			return false;
		if (_referenceMessage == null) {
			if (other._referenceMessage != null)
				return false;
		} else if (!_referenceMessage.equals(other._referenceMessage))
			return false;
		if (_typeModif != other._typeModif)
			return false;
		return true;
	}
}
