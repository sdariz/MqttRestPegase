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
	
	/**
	 * Constructeur par défaut
	 * 
	 */
	public PositionSurSynoptique() {
		this("", -1, -1);
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
	public PositionSurSynoptique(String pNomSynoptique, int pXlocation, int pYlocation) {
		_nomSynoptique = pNomSynoptique;
		_xlocation = pXlocation;
		_ylocation = pYlocation;
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
	 * @param pNom le nouveau nom
	 */
	public void setNomSynoptique(String pNom) {
		if(pNom != null) {
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
	 * @param pVal la position en x
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
	 * @param pVal la position en y
	 */
	public void setYlocation(int pVal) {
		_ylocation = pVal;
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
