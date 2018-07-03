package org.signature.mqttRest.objetsPartages.informationPegase;

/**
 * La position en X et Y sur un synoptique
 * 
 * @author SDARIZCUREN
 *
 */
public class PositionSurSynoptique {
	private String _nomSynoptique;
	private int _xlocation;
	private int _ylocation;
	private boolean _avecLabel;
	private FORMAT _format;

	public enum FORMAT {
		ICONE, REEL
	};

	/**
	 * Constructeur par défaut
	 * 
	 */
	public PositionSurSynoptique() {
		this("", -1, -1, true, FORMAT.REEL);
	}

	/**
	 * Constructeur de l'objet
	 * 
	 * @param pNomSynoptique
	 *            le nom du synoptique
	 * @param pXlocation
	 *            la position en x sur le synoptique
	 * @param pYlocation
	 *            la position en y sur le synoptique
	 */
	public PositionSurSynoptique(String pNomSynoptique, int pXlocation, int pYlocation, boolean pAvecLabel,
			FORMAT pFormat) {
		_nomSynoptique = pNomSynoptique;
		_xlocation = pXlocation;
		_ylocation = pYlocation;
		_avecLabel = pAvecLabel;
		_format = pFormat;
	}

	/**
	 * Donne le nom du synoptique
	 * 
	 * @return le nom
	 */
	public String getNomSynoptique() {
		return _nomSynoptique;
	}

	/**
	 * Initialise le nom du synoptique
	 * 
	 * @param pNom
	 *            le nouveau nom
	 */
	public void setNomSynoptique(String pNom) {
		if (pNom != null) {
			_nomSynoptique = pNom;
		}
	}

	/**
	 * Donne la position en x sur le synoptique
	 * 
	 * @return la position en x
	 */
	public int getXlocation() {
		return _xlocation;
	}

	/**
	 * Initialise la position en x sur le synoptique
	 * 
	 * @param pVal
	 *            la position en x
	 */
	public void setXlocation(int pVal) {
		_xlocation = pVal;
	}

	/**
	 * Donne la position en y sur le synoptique
	 * 
	 * @return la position en y
	 */
	public int getYlocation() {
		return _ylocation;
	}

	/**
	 * Initialise la position en y sur le synoptique
	 * 
	 * @param pVal
	 *            la position en y
	 */
	public void setYlocation(int pVal) {
		_ylocation = pVal;
	}
	
	/**
	 * Affichage ou nom du label de l'équipement
	 * 
	 * @return true pour afficher, sinon false
	 */
	public boolean getAvecLabel() {
		return _avecLabel;
	}

	/**
	 * Initialise si affichage ou nom du label de l'équipement
	 * 
	 * @param pVal
	 *            true pour afficher, sinon false
	 */
	public void setAvecLabel(boolean pVal) {
		_avecLabel = pVal;
	}

	/**
	 * Donne le format sur le synoptique
	 * 
	 * @return le format
	 */
	public FORMAT getformat() {
		return _format;
	}

	/**
	 * Initialise le format sur le synoptique
	 * 
	 * @param pVal
	 *            le format
	 */
	public void setFormat(FORMAT pVal) {
		_format = pVal;
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
		result = prime * result + ((_format == null) ? 0 : _format.hashCode());
		result = prime * result + ((_nomSynoptique == null) ? 0 : _nomSynoptique.hashCode());
		result = prime * result + _xlocation;
		result = prime * result + _ylocation;
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
		PositionSurSynoptique other = (PositionSurSynoptique) obj;
		if (_format != other._format)
			return false;
		if (_nomSynoptique == null) {
			if (other._nomSynoptique != null)
				return false;
		} else if (!_nomSynoptique.equals(other._nomSynoptique))
			return false;
		if (_xlocation != other._xlocation)
			return false;
		if (_ylocation != other._ylocation)
			return false;
		return true;
	}
}
