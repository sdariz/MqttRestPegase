package signature.mqttRest.objetsPartages;

/**
 * Description d'un utilisateur
 * 
 * @author SDARIZCUREN
 *
 */
public class MessageUtilisateurMqttRest implements IMessageMqttRest {
	public enum Droits {
		ADMINISTRATEUR, GESTIONNAIRE, UTILISATEUR, INCONNU
	}

	private String _idExpediteur;
	private String _referenceMessage;
	private String _nom;
	private String _prenom;
	private String _motDePasse;
	private Droits _droits;

	/**
	 * Construction du message
	 */
	public MessageUtilisateurMqttRest() {
		this("", "");
	}

	/**
	 * Construction du message
	 * @param pIdExpediteur l'identifiant de l'exp�diteur
	 */
	public MessageUtilisateurMqttRest(String pIdExpediteur) {
		this(pIdExpediteur, "");
	}

	/**
	 * Construction du message
	 * @param pIdExpediteur l'identifiant de l'exp�diteur
	 * @param pReferenceMessage la r�f�rence du message, donn�e par l'exp�diteur
	 */
	public MessageUtilisateurMqttRest(String pIdExpediteur, String pReferenceMessage) {
		_idExpediteur = pIdExpediteur;
		_referenceMessage = pReferenceMessage;
		_nom = "";
		_prenom = "";
		_motDePasse = "";
		_droits = Droits.INCONNU;
	}

	/**
	 * Donne l'identifiant de l'expediteur du message
	 * 
	 * @return l'identifiant de l'exp�diteur
	 */
	public String getIdentifiantExpediteur() {
		return _idExpediteur;
	}

	/**
	 * Initialise l'identifiant de l'exp�diteur
	 * 
	 * @param pId
	 *            l'identifiant de l'exp�diteur
	 */
	public void setIdentifiantExpediteur(String pId) {
		_idExpediteur = pId;
	}

	/**
	 * La r�f�rence du message, attribu�e par l'exp�diteur
	 * 
	 * @return la r�f�rence de l'exp�diteur sur le message
	 */
	public String getReferenceMessage() {
		return _referenceMessage;
	}

	/**
	 * Initialise la r�f�rence du message pour son exp�diteur
	 * 
	 * @param pRef
	 *            la r�f�rence du message
	 */
	public void setReferenceMessage(String pRef) {
		_referenceMessage = pRef;
	}

	/**
	 * Initialise le nom de l'utilisateur
	 * 
	 * @param pNom
	 *            le nom attribu� � l'utilisateur
	 */
	public void setNom(String pNom) {
		_nom = pNom;
	}

	/**
	 * Retourne le nom de l'utilisateur
	 * 
	 * @return son nom
	 */
	public String getNom() {
		return _nom;
	}

	/**
	 * Initialise le pr�nom de l'utilisateur
	 * 
	 * @param pPrenom
	 *            le pr�nom attribu� � l'utilisateur
	 */
	public void setPrenom(String pPrenom) {
		_prenom = pPrenom;
	}

	/**
	 * Retourne le pr�nom de l'utilisateur
	 * 
	 * @return son pr�nom
	 */
	public String getPrenom() {
		return _prenom;
	}

	/**
	 * Initialise le mot de passe de l'utilisateur
	 * 
	 * @param pMotPasse
	 *            le mot de passe attribu� � l'utilisateur
	 */
	public void setMotPasse(String pMotPasse) {
		_motDePasse = pMotPasse;
	}

	/**
	 * Retourne le mot de passe de l'utilisateur
	 * 
	 * @return son mot de passe
	 */
	public String getMotPasse() {
		return _motDePasse;
	}

	/**
	 * Initialise les droits attribu�s � l'utilisateur
	 * 
	 * @param pDroits
	 *            ses droits
	 */
	public void setDroits(Droits pDroits) {
		_droits = pDroits;
	}

	/**
	 * Retourne les droits attribu�s � l'utilisateur
	 * 
	 * @return les droits de l'utilisateur
	 */
	public Droits getDroits() {
		return _droits;
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
		result = prime * result + ((_droits == null) ? 0 : _droits.hashCode());
		result = prime * result + ((_idExpediteur == null) ? 0 : _idExpediteur.hashCode());
		result = prime * result + ((_motDePasse == null) ? 0 : _motDePasse.hashCode());
		result = prime * result + ((_nom == null) ? 0 : _nom.hashCode());
		result = prime * result + ((_prenom == null) ? 0 : _prenom.hashCode());
		result = prime * result + ((_referenceMessage == null) ? 0 : _referenceMessage.hashCode());
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
		MessageUtilisateurMqttRest other = (MessageUtilisateurMqttRest) obj;
		if (_droits != other._droits)
			return false;
		if (_idExpediteur == null) {
			if (other._idExpediteur != null)
				return false;
		} else if (!_idExpediteur.equals(other._idExpediteur))
			return false;
		if (_motDePasse == null) {
			if (other._motDePasse != null)
				return false;
		} else if (!_motDePasse.equals(other._motDePasse))
			return false;
		if (_nom == null) {
			if (other._nom != null)
				return false;
		} else if (!_nom.equals(other._nom))
			return false;
		if (_prenom == null) {
			if (other._prenom != null)
				return false;
		} else if (!_prenom.equals(other._prenom))
			return false;
		if (_referenceMessage == null) {
			if (other._referenceMessage != null)
				return false;
		} else if (!_referenceMessage.equals(other._referenceMessage))
			return false;
		return true;
	}
}
