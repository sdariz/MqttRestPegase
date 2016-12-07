package org.signature.mqttRest.objetsPartages.etatEtPilotage;

/**
 * Description des défauts sur un afficheur ou un module positionnable
 * 
 * @author SDARIZCUREN
 *
 */
public class DefautAfficheurModulePositionnableMqttRest {
	private TypeDefautAfficheur _typeDefaut;
	private int _positionX;
	private int _positionY;
	private Couleur _couleur;
	private TypeModuleDefaut _moduleDefaut;
	private String _adresseDefautDansModule;
	private String _adresseDefaut;

	public enum TypeDefautAfficheur {
		DEFAUT_CARTE, DEFAUT_PIXEL, DEFAUT_LAMPE, DEFAUT_BISEAU, DEFAUT_BRA, DEFAUT_SDV, DEFAUT_INCONNU
	}

	public enum TypeModuleDefaut {
		LIGNE1, LIGNE2, LIGNE3, LIGNE4, LIGNE5, LIGNE6, LIGNE7, LIGNE8, LIGNE9, LIGNE10, PANONCEAU1, PANONCEAU2, PANONCEAU3, PANONCEAU4, PICTOGRAMME1, PICTOGRAMME2, PICTOGRAMME3, PICTOGRAMME4, FLASH, DEFAUT_MOTEUR, DEFAUT_COLLISION, INCONNU
	}

	public enum Couleur {
		ROUGE, ORANGE, JAUNE, VERT, BLEUE, BLANC, NOIR, INCONNU
	}

	/**
	 * Construction du défaut
	 */
	public DefautAfficheurModulePositionnableMqttRest() {
		_typeDefaut = TypeDefautAfficheur.DEFAUT_INCONNU;
		_positionX = 0;
		_positionY = 0;
		_couleur = Couleur.INCONNU;
		_moduleDefaut = TypeModuleDefaut.INCONNU;
		_adresseDefautDansModule = "";
		_adresseDefaut = "";
	}
	
	/**
	 * Donne l'adresse du défaut dans le module
	 * 
	 * @return l'adresse du défaut dans le module
	 */
	public String getAdresseDefautDansModule() {
		return _adresseDefautDansModule;
	}
	
	/**
	 * Initialise l'adresse du défaut dans le module
	 * 
	 * @param pAdresse l'adresse du défaut dans le module
	 */
	public void setAdresseDefautDansModule(String pAdresse) {
		if(pAdresse == null) {
			pAdresse = "";
		}
		_adresseDefautDansModule = pAdresse;
	}
	
	/**
	 * Donne l'adresse du défaut
	 * 
	 * @return l'adresse du défaut
	 */
	public String getAdresseDefaut() {
		return _adresseDefaut;
	}
	
	/**
	 * Initialise l'adresse du défaut
	 * 
	 * @param pAdresse l'adresse du défaut
	 */
	public void setAdresseDefaut(String pAdresse) {
		if(pAdresse == null) {
			pAdresse = "";
		}
		_adresseDefaut = pAdresse;
	}

	/**
	 * Donne la couleur de l'afficheur en défaut
	 * 
	 * @return la couleur de l'afficheur en défaut
	 */
	public Couleur getCouleurDefaut() {
		return _couleur;
	}
	
	/**
	 * Initialise la couleur de l'afficheur en défaut
	 * 
	 * @param pCouleur la couleur de l'afficheur en défaut
	 */
	public void setCouleurDefaut(Couleur pCouleur) {
		_couleur = pCouleur;
	}

	/**
	 * Donne le type de module en défaut
	 * 
	 * @return le type du module en défaut : LIGNE1, PICTOGRAMME, ...
	 */
	public TypeModuleDefaut getTypeModuleDefaut() {
		return _moduleDefaut;
	}
	
	/**
	 * Initialise le type de module en défaut
	 * 
	 * @param pTypeModule le type du module en défaut : LIGNE1, PICTOGRAMME, ...
	 */
	public void setTypeModuleDefaut(TypeModuleDefaut pTypeModule) {
		_moduleDefaut = pTypeModule;
	}

	/**
	 * Donne la position en x de l'afficheur en défaut
	 * 
	 * @return la position en x
	 */
	public int getPositionX() {
		return _positionX;
	}
	
	/**
	 * Initialise la position en x de l'afficheur en défaut
	 * 
	 * @param pPos la position en x
	 */
	public void setPositionX(int pPos) {
		_positionX = pPos;
	}

	/**
	 * Donne la position en y de l'afficheur en défaut
	 * 
	 * @return la position en y
	 */
	public int getPositionY() {
		return _positionY;
	}
	
	/**
	 * Initialise la position en y de l'afficheur en défaut
	 * 
	 * @param pPos la position en y
	 */
	public void setPositionY(int pPos) {
		_positionY = pPos;
	}

	/**
	 * Donne le type de défaut
	 * 
	 * @return le type de défaut : DEFAUT_CARTE, ...
	 */
	public TypeDefautAfficheur getTypeDefaut() {
		return _typeDefaut;
	}
	
	/**
	 * Initialise le type de défaut
	 * 
	 * @param pType le type de défaut : DEFAUT_CARTE, ...
	 */
	public void setTypeDefaut(TypeDefautAfficheur pType) {
		_typeDefaut = pType;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((_adresseDefaut == null) ? 0 : _adresseDefaut.hashCode());
		result = prime * result + ((_adresseDefautDansModule == null) ? 0 : _adresseDefautDansModule.hashCode());
		result = prime * result + ((_couleur == null) ? 0 : _couleur.hashCode());
		result = prime * result + ((_moduleDefaut == null) ? 0 : _moduleDefaut.hashCode());
		result = prime * result + _positionX;
		result = prime * result + _positionY;
		result = prime * result + ((_typeDefaut == null) ? 0 : _typeDefaut.hashCode());
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
		DefautAfficheurModulePositionnableMqttRest other = (DefautAfficheurModulePositionnableMqttRest) obj;
		if (_adresseDefaut == null) {
			if (other._adresseDefaut != null)
				return false;
		} else if (!_adresseDefaut.equals(other._adresseDefaut))
			return false;
		if (_adresseDefautDansModule == null) {
			if (other._adresseDefautDansModule != null)
				return false;
		} else if (!_adresseDefautDansModule.equals(other._adresseDefautDansModule))
			return false;
		if (_couleur != other._couleur)
			return false;
		if (_moduleDefaut != other._moduleDefaut)
			return false;
		if (_positionX != other._positionX)
			return false;
		if (_positionY != other._positionY)
			return false;
		if (_typeDefaut != other._typeDefaut)
			return false;
		return true;
	}
}
