package signature.mqttRest.objetsPartages.etatEtPilotage;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Classe décrivant une alarme sur un équipement
 * 
 * @author SDARIZCUREN
 *
 */
public class MessageAlarmeMqttRest {
	private String _libelle;
	private Type _type;
	private Gravite _gravite;
	private Etat _etat;
	private List<DefautAfficheurModulePositionnableMqttRest> _listeDefautsAfficheurModulePositionnable;

	private Date _horodateGeneration;
	private Date _horodateFin;

	public enum Type {
		DEFAUT_DE_COMMUNICATION, DEFAUT_COM_ARMOIRE_EQUIPEMENT, DEFAUT_SECTEUR, DEFAUT_SECTEUR_EQUIPEMENT, DEFAUT_24V, DEFAUT_24V_EQUIPEMENT, DEFAUT_DISJONCTEUR, DEFAUT_COM_UC_UPP, DEFAUT_CARTE, DEFAUT_PIXEL, CONNECTE_LOCAL, PORTE_OUVERTE, PORTE_OUVERTE_EQUIPEMENT, CONNECTE_LOCAL_EQUIPEMENT, RACKHS, BISHS, CANAL_SECOURS, DEFAUT_PARAFOUDRE, DEFAUT_COMMUNICATION_INTERNE, DEFAUT_MAJEUR_COMMUNICATION_INTERNE, DEFAUT_DELESTAGE, DEFAUT_POSITIONNEMENT_BARRIERE, DEFAUT_GENERAL_BARRIERE, DEFAUT_DEGONDAGE_BARRIERE, DEFAUT_LAMPE, DEFAUT_BATTERIE, DEFAUT_CELLULE, DEFAUT_REENCLENCHEUR, DEFAUT_TEMPERATURE, DEFAUT_TEMPERATURE_ARMOIRE, MODE_VIRTUEL, DEFAUT_FLASH, DEFAUT_MAJEUR_GENERIQUE, DEFAUT_MINEUR_GENERIQUE, DEFAUT_AFFICHEUR_GENERIQUE, DEFAUT_MOTEUR_BRA, DEFAUT_COLLISION_BRA, DEFAUT_POSITIONNEMENT_PRISME, DEFAUT_MOTEUR_PRISME, DEFAUT_COULEUR_LIGNE_FEUX
	}

	public enum Gravite {
		MINEURE, INTERMEDIAIRE, MAJEURE
	}

	public enum Etat {
		ACTIVE, DISPARUE
	}

	/**
	 * Construction d'une alarme
	 */
	public MessageAlarmeMqttRest() {
		_libelle = "";
		_type = Type.DEFAUT_DE_COMMUNICATION;
		_gravite = Gravite.MAJEURE;
		_etat = Etat.ACTIVE;
		_listeDefautsAfficheurModulePositionnable = new ArrayList<>();

		_horodateGeneration = new Date();
	}

	/**
	 * Donne la gravité de l'alarme
	 * 
	 * @return la gravité de l'alarme
	 */
	public Gravite getGravite() {
		return _gravite;
	}

	/**
	 * Initialise la gravité de l'alarme
	 * 
	 * @param pGravite
	 *            la gravité de l'alarme
	 */
	public void setGravite(Gravite pGravite) {
		_gravite = pGravite;
	}

	/**
	 * Donne le libellé de l'alarme
	 * 
	 * @return la description de l'alarme
	 */
	public String getLibelle() {
		return _libelle;
	}

	/**
	 * Initialise le libellé de l'alarme
	 * 
	 * @param pLibelle
	 *            la description de l'alarme
	 */
	public void setLibelle(String pLibelle) {
		if (pLibelle == null) {
			pLibelle = "";
		}

		_libelle = pLibelle;
	}

	/**
	 * Donne le type de l'alarme
	 * 
	 * @return son type : DEFAUT_DE_COMMUNICATION, ...
	 */
	public Type getType() {
		return _type;
	}

	/**
	 * Initialise le type de l'alarme
	 * 
	 * @param pType
	 *            son type : DEFAUT_DE_COMMUNICATION, ...
	 */
	public void setType(Type pType) {
		_type = pType;
	}

	/**
	 * Donne l'état de l'alarme
	 * 
	 * @return son état : ACTIVE, ...
	 */
	public Etat getEtat() {
		return _etat;
	}

	/**
	 * Initialise l'état de l'alarme
	 * 
	 * @param pEtat
	 *            son état : ACTIVE, ...
	 */
	public void setEtat(Etat pEtat) {
		_etat = pEtat;
	}

	/**
	 * Horodate de génération de l'alarme
	 * 
	 * @return l'horodate de génération
	 */
	public Date getHorodateGeneration() {
		return _horodateGeneration;
	}

	/**
	 * Initialise l'horodate de génération de l'alarme
	 * 
	 * @param pDate
	 *            l'horodate de génération
	 */
	public void setHorodateGeneration(Date pDate) {
		if (pDate != null) {
			_horodateGeneration = pDate;
		}
	}

	/**
	 * Horodate de fin de l'alarme
	 * 
	 * @return l'horodate de fin. Null si l'alarme n'est pas terminé
	 */
	public Date getHorodateFin() {
		return _horodateFin;
	}

	/**
	 * Initialise l'horodate de fin de l'alarme
	 * 
	 * @param pDate
	 *            l'horodate de fin.
	 */
	public void setHorodateFin(Date pDate) {
		_horodateFin = pDate;
	}

	/**
	 * Donne la liste des défauts afficheurs, et des défauts sur un module à
	 * position
	 * 
	 * @return la liste des défauts
	 */
	public List<DefautAfficheurModulePositionnableMqttRest> getListeDefautsAfficheurModulePositionnable() {
		return _listeDefautsAfficheurModulePositionnable;
	}

	/**
	 * Initialise la liste des défauts afficheurs, et des défauts sur un module
	 * à position
	 * 
	 * @param pListeDefauts
	 *            la liste des défauts
	 */
	public void setListeDefautsAfficheurModulePositionnable(
			List<DefautAfficheurModulePositionnableMqttRest> pListeDefauts) {
		if (pListeDefauts == null) {
			pListeDefauts = new ArrayList<DefautAfficheurModulePositionnableMqttRest>();
		}

		_listeDefautsAfficheurModulePositionnable = pListeDefauts;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((_etat == null) ? 0 : _etat.hashCode());
		result = prime * result + ((_gravite == null) ? 0 : _gravite.hashCode());
		result = prime * result + ((_horodateFin == null) ? 0 : _horodateFin.hashCode());
		result = prime * result + ((_horodateGeneration == null) ? 0 : _horodateGeneration.hashCode());
		result = prime * result + ((_libelle == null) ? 0 : _libelle.hashCode());
		result = prime * result + ((_listeDefautsAfficheurModulePositionnable == null) ? 0
				: _listeDefautsAfficheurModulePositionnable.hashCode());
		result = prime * result + ((_type == null) ? 0 : _type.hashCode());
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
		MessageAlarmeMqttRest other = (MessageAlarmeMqttRest) obj;
		if (_etat != other._etat)
			return false;
		if (_gravite != other._gravite)
			return false;
		if (_horodateFin == null) {
			if (other._horodateFin != null)
				return false;
		} else if (!_horodateFin.equals(other._horodateFin))
			return false;
		if (_horodateGeneration == null) {
			if (other._horodateGeneration != null)
				return false;
		} else if (!_horodateGeneration.equals(other._horodateGeneration))
			return false;
		if (_libelle == null) {
			if (other._libelle != null)
				return false;
		} else if (!_libelle.equals(other._libelle))
			return false;
		if (_listeDefautsAfficheurModulePositionnable == null) {
			if (other._listeDefautsAfficheurModulePositionnable != null)
				return false;
		} else if (!_listeDefautsAfficheurModulePositionnable.equals(other._listeDefautsAfficheurModulePositionnable))
			return false;
		if (_type != other._type)
			return false;
		return true;
	}
	
	

}
