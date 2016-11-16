package signature.mqttRest.objetsPartages.etatEtPilotage;

import java.util.ArrayList;
import java.util.List;

/**
 * Description des propriétés d'une armoire
 * 
 * @author SDARIZCUREN
 *
 */
public class MessageProprietesArmoireMqttRest {
	private String _idArmoire;
	private String _nom;
	private String _informationComplementaire;
	private String _adresse;
	private boolean _simulation;
	private List<String> _idsEquipements;

	/**
	 * Construction du message
	 */
	public MessageProprietesArmoireMqttRest() {
		this("", "", "", "", true, new ArrayList<>());
	}

	/**
	 * Construction du message
	 * 
	 * @param pIdArmoire
	 *            l'identifiant de l'armoire
	 * @param pNom
	 *            le nom de l'armoire
	 * @param pInformationComplementaire
	 *            un texte complémentaire pour caractériser l'équipement
	 * @param pAdresse
	 *            l'adresse de l'armoire (adresse RGS, ...)
	 * @param pSimulation
	 *            true si l'armoire est en mode simulation
	 * @param pIdsEquipements
	 *            les identifiants des équipements de l'armoire
	 */
	public MessageProprietesArmoireMqttRest(String pIdArmoire, String pNom, String pInformationComplementaire,
			String pAdresse, boolean pSimulation, List<String> pIdsEquipements) {
		_idArmoire = pIdArmoire;
		_nom = pNom;
		_informationComplementaire = pInformationComplementaire;
		_adresse = pAdresse;
		_simulation = pSimulation;

		if (pIdsEquipements == null) {
			pIdsEquipements = new ArrayList<>();
		}

		_idsEquipements = pIdsEquipements;
	}

	/**
	 * Initialise l'identifiant de l'armoire
	 * 
	 * @param pId
	 *            l'id de l'armoire
	 */
	public void setIdArmoire(String pId) {
		_idArmoire = pId;
	}

	/**
	 * Retourne l'identifiant de l'armoire
	 * 
	 * @return l'id de l'armoire
	 */
	public String getIdArmoire() {
		return _idArmoire;
	}

	/**
	 * Initialise le nom de l'armoire
	 * 
	 * @param pNom
	 *            son nom
	 */
	public void setNom(String pNom) {
		_nom = pNom;
	}

	/**
	 * Retourne le nom de l'armoire
	 * 
	 * @return son nom
	 */
	public String getNom() {
		return _nom;
	}

	/**
	 * Initialise une information complémentaire pour une armoire
	 * 
	 * @param pInfo
	 *            l'information Complémentaire
	 */
	public void setInformationComplementaire(String pInfo) {
		_informationComplementaire = pInfo;
	}

	/**
	 * Retourne une information complémentaire associée à une armoire
	 * 
	 * @return une information complémentaire
	 */
	public String getInformationComplementaire() {
		return _informationComplementaire;
	}

	/**
	 * Initialise l'adresse de l'armoire
	 * 
	 * @param pAdresse
	 *            l'adresse de l'armoire
	 */
	public void setAdresse(String pAdresse) {
		_adresse = pAdresse;
	}

	/**
	 * Retourne l'adresse de l'armoire
	 * 
	 * @return l'adresse de l'armoire
	 */
	public String getAdresse() {
		return _adresse;
	}

	/**
	 * Initialise le mode simulation de l'armoire
	 * 
	 * @param pSimulation
	 *            true pour une armoire en mode simulation
	 */
	public void setSimulation(boolean pSimulation) {
		_simulation = pSimulation;
	}

	/**
	 * Retourne le mode simulation de l'armoire
	 * 
	 * @return true pour une armoire en mode simulation
	 */
	public boolean getSimulation() {
		return _simulation;
	}

	/**
	 * Initialise les identifiants des équipements de l'armoire
	 * 
	 * @param pIds
	 *            la liste des identifiants des équipements de l'armoire
	 */
	public void setIdentifiantsEquipements(List<String> pIds) {
		if (pIds == null) {
			pIds = new ArrayList<>();
		}

		_idsEquipements = pIds;
	}

	/**
	 * Retourne les identifiants des équipements de l'armoire
	 * 
	 * @return a liste des identifiants des équipements de l'armoire
	 */
	public List<String> getIdentifiantsEquipements() {
		return _idsEquipements;
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
		result = prime * result + ((_idArmoire == null) ? 0 : _idArmoire.hashCode());
		result = prime * result + ((_idsEquipements == null) ? 0 : _idsEquipements.hashCode());
		result = prime * result + ((_informationComplementaire == null) ? 0 : _informationComplementaire.hashCode());
		result = prime * result + ((_nom == null) ? 0 : _nom.hashCode());
		result = prime * result + (_simulation ? 1231 : 1237);
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
		MessageProprietesArmoireMqttRest other = (MessageProprietesArmoireMqttRest) obj;
		if (_adresse == null) {
			if (other._adresse != null)
				return false;
		} else if (!_adresse.equals(other._adresse))
			return false;
		if (_idArmoire == null) {
			if (other._idArmoire != null)
				return false;
		} else if (!_idArmoire.equals(other._idArmoire))
			return false;
		if (_idsEquipements == null) {
			if (other._idsEquipements != null)
				return false;
		} else if (!_idsEquipements.equals(other._idsEquipements))
			return false;
		if (_informationComplementaire == null) {
			if (other._informationComplementaire != null)
				return false;
		} else if (!_informationComplementaire.equals(other._informationComplementaire))
			return false;
		if (_nom == null) {
			if (other._nom != null)
				return false;
		} else if (!_nom.equals(other._nom))
			return false;
		if (_simulation != other._simulation)
			return false;
		return true;
	}
}
