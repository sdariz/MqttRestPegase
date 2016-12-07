package org.signature.mqttRest.objetsPartages.informationPegase;

/**
 * Message décrivant les propriétés d'un équipement
 * 
 * @author SDARIZCUREN
 *
 */
public class MessageProprietesEquipementMqttRest {
	private String _idEquipement;
	private String _idArmoireCommande;
	private String _nom;
	private String _informationComplementaire;
	private String _localisation;
	private String _reference;
	private String _pk;
	private String _sens;
	private String _adresse;
	private int _idGroupe;

	/**
	 * Construction du message
	 */
	public MessageProprietesEquipementMqttRest() {
		this("", "", "", "", "", "", "", "", "", 0);
	}

	/**
	 * Construction du message
	 * 
	 * @param pIdEquipement
	 *            l'identifiant de l'èquipement
	 * @param pIdArmoireCommande
	 *            l'identifiant de l'armoire
	 * @param pNom
	 *            le nom de l'équipement
	 * @param pInformationComplementaire
	 *            un texte complémentaire pour caractériser l'équipement
	 * @param pLocalisation
	 *            la localisation de l'équipement
	 * @param pReference
	 *            une référence associée à l'équipement
	 * @param pPk
	 *            le point kilométrique
	 * @param pSens
	 *            le sens sur la voie
	 * @param pAdresse
	 *            l'adresse de l'équipement, si utilisé
	 * @param pIdGroupe
	 *            l'identifiant du groupe auquel appartient l'équipement. 0 si
	 *            aucun
	 */
	public MessageProprietesEquipementMqttRest(String pIdEquipement, String pIdArmoireCommande, String pNom,
			String pInformationComplementaire, String pLocalisation, String pReference, String pPk, String pSens,
			String pAdresse, int pIdGroupe) {
		_idEquipement = pIdEquipement;
		_idArmoireCommande = pIdArmoireCommande;
		_nom = pNom;
		_informationComplementaire = pInformationComplementaire;
		_localisation = pLocalisation;
		_reference = pReference;
		_pk = pPk;
		_sens = pSens;
		_adresse = pAdresse;
		_idGroupe = pIdGroupe;
	}

	/**
	 * Initialise l'identiffiant de l'équipement
	 * 
	 * @param pId
	 *            l'id de l'équipement
	 */
	public void setIdEquipement(String pId) {
		_idEquipement = pId;
	}

	/**
	 * Retourne l'identifiant de l'équipement
	 * 
	 * @return l'id de l'équipement
	 */
	public String getIdEquipement() {
		return _idEquipement;
	}

	/**
	 * Initialise l'identiffiant de l'armoire de commande
	 * 
	 * @param pId
	 *            l'id de l'armoire de commande
	 */
	public void setIdArmoireCommande(String pId) {
		_idArmoireCommande = pId;
	}

	/**
	 * Retourne l'identifiant de l'armoire de commande
	 * 
	 * @return l'id de l'armoire de commande
	 */
	public String getIdArmoireCommande() {
		return _idArmoireCommande;
	}

	/**
	 * Initialise le nom de l'équipement
	 * 
	 * @param pNom
	 *            son nom
	 */
	public void setNom(String pNom) {
		_nom = pNom;
	}

	/**
	 * Retourne le nom de l'équipement
	 * 
	 * @return son nom
	 */
	public String getNom() {
		return _nom;
	}

	/**
	 * Initialise une information complémentaire pour un équipement
	 * 
	 * @param pInfo
	 *            l'information Complémentaire
	 */
	public void setInformationComplementaire(String pInfo) {
		_informationComplementaire = pInfo;
	}

	/**
	 * Retourne une information complémentaire associée à un équipement
	 * 
	 * @return une information complémentaire
	 */
	public String getInformationComplementaire() {
		return _informationComplementaire;
	}

	/**
	 * Initialise la localisation de l'équipement
	 * 
	 * @param pLocalisation
	 *            sa localisation
	 */
	public void setLocalisation(String pLocalisation) {
		_localisation = pLocalisation;
	}

	/**
	 * Retourne la localisation de l'équipement
	 * 
	 * @return sa localisation
	 */
	public String getLocalisation() {
		return _localisation;
	}

	/**
	 * Initialise une référence associé à l'équipement
	 * 
	 * @param pReference
	 *            sa référence
	 */
	public void setReference(String pReference) {
		_reference = pReference;
	}

	/**
	 * Retourne une référence associé à l'équipement
	 * 
	 * @return sa référence
	 */
	public String getReference() {
		return _reference;
	}

	/**
	 * Initialise le PK de l'équipement
	 * 
	 * @param pPk
	 *            son PK
	 */
	public void setPK(String pPk) {
		_pk = pPk;
	}

	/**
	 * Retourne le PK de l'équipement
	 * 
	 * @return son PK
	 */
	public String getPK() {
		return _pk;
	}

	/**
	 * Initialise le sens de l'équipement
	 * 
	 * @param pSens
	 *            son sens
	 */
	public void setSens(String pSens) {
		_sens = pSens;
	}

	/**
	 * Retourne le sens de l'équipement
	 * 
	 * @return son sens
	 */
	public String getSens() {
		return _sens;
	}

	/**
	 * Initialise l'id du groupe de l'équipement
	 * 
	 * @param pId
	 *            l'id du groupe
	 */
	public void setIdGroupe(int pId) {
		_idGroupe = pId;
	}

	/**
	 * Retourne l'id du groupe de l'équipement
	 * 
	 * @return l'id du groupe
	 */
	public int getIdGroupe() {
		return _idGroupe;
	}

	/**
	 * Initialise l'adresse de l'équipement
	 * 
	 * @param pAdresse
	 *            l'adresse de l'équipement
	 */
	public void setAdresse(String pAdresse) {
		_adresse = pAdresse;
	}

	/**
	 * Retourne l'adresse de l'équipement
	 * 
	 * @return l'adresse de l'équipement
	 */
	public String getAdresse() {
		return _adresse;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((_adresse == null) ? 0 : _adresse.hashCode());
		result = prime * result + ((_idArmoireCommande == null) ? 0 : _idArmoireCommande.hashCode());
		result = prime * result + ((_idEquipement == null) ? 0 : _idEquipement.hashCode());
		result = prime * result + _idGroupe;
		result = prime * result + ((_informationComplementaire == null) ? 0 : _informationComplementaire.hashCode());
		result = prime * result + ((_localisation == null) ? 0 : _localisation.hashCode());
		result = prime * result + ((_nom == null) ? 0 : _nom.hashCode());
		result = prime * result + ((_pk == null) ? 0 : _pk.hashCode());
		result = prime * result + ((_reference == null) ? 0 : _reference.hashCode());
		result = prime * result + ((_sens == null) ? 0 : _sens.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
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
		MessageProprietesEquipementMqttRest other = (MessageProprietesEquipementMqttRest) obj;
		if (_adresse == null) {
			if (other._adresse != null)
				return false;
		} else if (!_adresse.equals(other._adresse))
			return false;
		if (_idArmoireCommande == null) {
			if (other._idArmoireCommande != null)
				return false;
		} else if (!_idArmoireCommande.equals(other._idArmoireCommande))
			return false;
		if (_idEquipement == null) {
			if (other._idEquipement != null)
				return false;
		} else if (!_idEquipement.equals(other._idEquipement))
			return false;
		if (_idGroupe != other._idGroupe)
			return false;
		if (_informationComplementaire == null) {
			if (other._informationComplementaire != null)
				return false;
		} else if (!_informationComplementaire.equals(other._informationComplementaire))
			return false;
		if (_localisation == null) {
			if (other._localisation != null)
				return false;
		} else if (!_localisation.equals(other._localisation))
			return false;
		if (_nom == null) {
			if (other._nom != null)
				return false;
		} else if (!_nom.equals(other._nom))
			return false;
		if (_pk == null) {
			if (other._pk != null)
				return false;
		} else if (!_pk.equals(other._pk))
			return false;
		if (_reference == null) {
			if (other._reference != null)
				return false;
		} else if (!_reference.equals(other._reference))
			return false;
		if (_sens == null) {
			if (other._sens != null)
				return false;
		} else if (!_sens.equals(other._sens))
			return false;
		return true;
	}

}
