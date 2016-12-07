package org.signature.mqttRest.objetsPartages.etatEtPilotage;

import java.util.ArrayList;
import java.util.List;

/**
 * Un message s'appliquant sur un module afficheur
 * @author SDARIZCUREN
 *
 */
public class MessageModuleMqttRest {
	public enum Luminosite {
		JOUR, NUIT, SURBRILLANCE, AUTOMATIQUE
	}

	public enum Mode {
		FIXE, CLIGNOTANT, ALTERNAT
	}
	
	private List<String> _messagesParPage;
	private List<String> _labelsParPage;
	private List<Integer> _tempsAllumage;
	private List<Integer> _tempsExtinction;
	private Luminosite _luminosite;
	private Mode _modeAffichage;
	private int _dureeValidite;
	private int _dureeValiditeRestante;
	
	/**
	 * Construction du message. Initialis� par d�faut en mode fixe, luminosit� automatique, DV = 0
	 */
	public MessageModuleMqttRest() {
		_messagesParPage = new ArrayList<>();
		_labelsParPage = new ArrayList<>();
		_tempsAllumage = new ArrayList<>();
		_tempsExtinction = new ArrayList<>();
		_luminosite = Luminosite.AUTOMATIQUE;
		_modeAffichage = Mode.FIXE;
		_dureeValidite = 0;
		_dureeValiditeRestante = -1;
	}
	
	/**
	 * Initialisation des messages du module
	 * @param pMessages les messages � utiliser
	 */
	public void setMessagesParPage(List<String> pMessages) {
		if(pMessages == null) {
			_messagesParPage = new ArrayList<>();
			return;
		}
		
		_messagesParPage = pMessages;
	}
	
	/**
	 * Retourne les pages de message
	 * @return les messages du module
	 */
	public List<String> getMessagesParPage() {
		return _messagesParPage;
	}
	
	/**
	 * Initialisation des labels des messages
	 * @param plabels les labels d�crivant chaque message du module
	 */
	public void setLabelsParPage(List<String> pLabels) {
		if(pLabels == null) {
			_labelsParPage = new ArrayList<>();
			return;
		}
		
		_labelsParPage = pLabels;
	}
	
	/**
	 * Retourne les labels des messages
	 * @return les labels d�crivant chaque message du module
	 */
	public List<String> getLabelsParPage() {
		return _labelsParPage;
	}
	
	/**
	 * Retourne les temps d'allumage de chaque page
	 * @return les temps d'allumage
	 */
	public List<Integer> getTempsAllumage() {
		return _tempsAllumage;
	}
	
	/**
	 * Initialise les temps d'allumage de chaque page
	 * @param pTempsAllumage les temps d'allumage
	 */
	public void setTempsAllumage(List<Integer> pTemps) {
		if(pTemps == null) {
			_tempsAllumage = new ArrayList<>();
			return;
		}
		
		_tempsAllumage = pTemps;
	}
	
	/**
	 * Retourne les temps d'extinction de chaque page
	 * @return les temps d'extinction
	 */
	public List<Integer> getTempsExtinction() {
		return _tempsExtinction;
	}
	
	/**
	 * Initialise les temps d'extinction de chaque page
	 * @param pTempsAllumage les temps d'extinction
	 */
	public void setTempsExtinction(List<Integer> pTemps) {
		if(pTemps == null) {
			_tempsExtinction = new ArrayList<>();
			return;
		}
		
		_tempsExtinction = pTemps;
	}

	/**
	 * Retourne la luminosit� � appliquer sur le module
	 * @return la luminosit�
	 */
	public Luminosite getLuminosite() {
		return _luminosite;
	}

	/**
	 * Initialisation de la luminosit� � appliquer sur le module
	 * @param pLuminosite la luminosit� � utiliser
	 */
	public void setLuminosite(Luminosite pLuminosite) {
		_luminosite = pLuminosite;
	}

	/**
	 * Retourne le mode d'affichage
	 * @return le mode d'affichage
	 */
	public Mode getModeAffichage() {
		return _modeAffichage;
	}

	/**
	 * Initialisation du mode d'affichage
	 * @param pModeAffichage le mode d'affichage
	 */
	public void setModeAffichage(Mode pModeAffichage) {
		_modeAffichage = pModeAffichage;
	}

	/**
	 * Retourne la dur�e de validit� du message
	 * @return la dur�e de validit�
	 */
	public int getDureeValidite() {
		return _dureeValidite;
	}

	/**
	 * Initialisation de la dur�e de validit� du message
	 * @param pDuree la dur�e de validit�
	 */
	public void setDureeValidite(int pDuree) {
		_dureeValidite = pDuree;
	}

	/**
	 * Retourne la dur�e de validit� restante du message
	 * @return la dur�e de validit� restante, -1 si pas g�r�e
	 */
	public int getDureeValiditeRestante() {
		return _dureeValiditeRestante;
	}

	/**
	 * Initialisation de la dur�e de validit� restante du message
	 * @param pDuree la dur�e de validit� restante, -1 pour ne pas g�rer cette valeur (valeur par d�faut)
	 */
	public void setDureeValiditeRestante(int pDuree) {
		_dureeValiditeRestante = pDuree;
	}
	
	/**
	 * Clone de l'objet courant
	 * @return une copie de l'instance courante
	 */
	@Override
	public MessageModuleMqttRest clone() {
		MessageModuleMqttRest retour = new MessageModuleMqttRest();
		
		retour._messagesParPage.addAll(_messagesParPage);
		retour._labelsParPage.addAll(_labelsParPage);
		retour._tempsAllumage.addAll(_tempsAllumage);
		retour._tempsExtinction.addAll(_tempsExtinction);
		retour._luminosite = _luminosite;
		retour._modeAffichage = _modeAffichage;
		retour._dureeValidite = _dureeValidite;
		retour._dureeValiditeRestante = _dureeValiditeRestante;
		
		return retour;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + _dureeValidite;
		result = prime * result + _dureeValiditeRestante;
		result = prime * result + ((_labelsParPage == null) ? 0 : _labelsParPage.hashCode());
		result = prime * result + ((_luminosite == null) ? 0 : _luminosite.hashCode());
		result = prime * result + ((_messagesParPage == null) ? 0 : _messagesParPage.hashCode());
		result = prime * result + ((_modeAffichage == null) ? 0 : _modeAffichage.hashCode());
		result = prime * result + ((_tempsAllumage == null) ? 0 : _tempsAllumage.hashCode());
		result = prime * result + ((_tempsExtinction == null) ? 0 : _tempsExtinction.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MessageModuleMqttRest other = (MessageModuleMqttRest) obj;
		if (_dureeValidite != other._dureeValidite)
			return false;
		if (_dureeValiditeRestante != other._dureeValiditeRestante)
			return false;
		if (_labelsParPage == null) {
			if (other._labelsParPage != null)
				return false;
		} else if (!_labelsParPage.equals(other._labelsParPage))
			return false;
		if (_luminosite != other._luminosite)
			return false;
		if (_messagesParPage == null) {
			if (other._messagesParPage != null)
				return false;
		} else if (!_messagesParPage.equals(other._messagesParPage))
			return false;
		if (_modeAffichage != other._modeAffichage)
			return false;
		if (_tempsAllumage == null) {
			if (other._tempsAllumage != null)
				return false;
		} else if (!_tempsAllumage.equals(other._tempsAllumage))
			return false;
		if (_tempsExtinction == null) {
			if (other._tempsExtinction != null)
				return false;
		} else if (!_tempsExtinction.equals(other._tempsExtinction))
			return false;
		return true;
	}
}
