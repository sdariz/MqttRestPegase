package org.signature.mqttRest.objetsPartages.etatEtPilotage;

import java.util.ArrayList;
import java.util.List;

/**
 * Un message s'appliquant sur un module afficheur
 * 
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
	 * Construction du message. Initialisé par défaut en mode fixe, luminosité
	 * automatique, DV = 0
	 */
	public MessageModuleMqttRest() {
		this(new ArrayList<>());
	}

	/**
	 * Construction du message. Initialisé par défaut en mode fixe, luminosité
	 * automatique, DV = 0
	 * 
	 * @param pMessagesParPage
	 *            les messages par pages
	 */
	public MessageModuleMqttRest(List<String> pMessagesParPage) {
		this(pMessagesParPage, new ArrayList<>());
	}

	/**
	 * Construction du message. Initialisé par défaut en mode fixe, luminosité
	 * automatique, DV = 0
	 * 
	 * @param pMessagesParPage
	 *            les messages par pages
	 * @param pLabelsParPage
	 *            les labels décrivant chaque message du module
	 */
	public MessageModuleMqttRest(List<String> pMessagesParPage, List<String> pLabelsParPage) {
		this(pMessagesParPage, pLabelsParPage, new ArrayList<>(), new ArrayList<>(), Luminosite.AUTOMATIQUE,
				Mode.FIXE, 0, -1);
	}

	/**
	 * Construction du message.
	 * 
	 * @param pMessagesParPage
	 *            les messages par pages
	 * @param pLabelsParPage
	 *            les labels décrivant chaque message du module
	 */

	/**
	 * Construction du message.
	 * 
	 * @param pMessagesParPage
	 *            les messages par pages
	 * @param pLabelsParPage
	 *            les labels décrivant chaque message du module
	 * @param pTempsAllumage
	 *            les temps d'allumage du message en mode clignotant et alternat
	 * @param pTempsExtinction
	 *            les temps d'extinction du message en mode clignotant et
	 *            alternat
	 * @param pLuminosite
	 *            la luminosité affichée
	 * @param pModeAffichage
	 *            le mode d'affichage : fixe, clignotant ou alternat
	 * @param pDureeValidite
	 *            la durée de validité du message
	 * @param pDureeValiditeRestante
	 *            la durée de validité restante du message, -1 si pas géré
	 */
	public MessageModuleMqttRest(List<String> pMessagesParPage, List<String> pLabelsParPage,
			List<Integer> pTempsAllumage, List<Integer> pTempsExtinction, Luminosite pLuminosite, Mode pModeAffichage,
			int pDureeValidite, int pDureeValiditeRestante) {
		if (pMessagesParPage == null) {
			pMessagesParPage = new ArrayList<>();
		}

		if (pLabelsParPage == null) {
			pLabelsParPage = new ArrayList<>();
		}

		if (pTempsAllumage == null) {
			pTempsAllumage = new ArrayList<>();
		}

		if (pTempsExtinction == null) {
			pTempsExtinction = new ArrayList<>();
		}

		_messagesParPage = pMessagesParPage;
		_labelsParPage = pLabelsParPage;

		_tempsAllumage = pTempsAllumage;
		_tempsExtinction = pTempsExtinction;

		_luminosite = pLuminosite;
		_modeAffichage = pModeAffichage;

		_dureeValidite = pDureeValidite;
		_dureeValiditeRestante = pDureeValiditeRestante;
	}

	/**
	 * Initialisation des messages du module
	 * 
	 * @param pMessages
	 *            les messages à utiliser
	 */
	public void setMessagesParPage(List<String> pMessages) {
		if (pMessages == null) {
			pMessages = new ArrayList<>();
		}

		_messagesParPage = pMessages;
	}

	/**
	 * Retourne les pages de message
	 * 
	 * @return les messages du module
	 */
	public List<String> getMessagesParPage() {
		return _messagesParPage;
	}

	/**
	 * Initialisation des labels des messages
	 * 
	 * @param plabels
	 *            les labels décrivant chaque message du module
	 */
	public void setLabelsParPage(List<String> pLabels) {
		if (pLabels == null) {
			pLabels = new ArrayList<>();
		}

		_labelsParPage = pLabels;
	}

	/**
	 * Retourne les labels des messages
	 * 
	 * @return les labels décrivant chaque message du module
	 */
	public List<String> getLabelsParPage() {
		return _labelsParPage;
	}

	/**
	 * Retourne les temps d'allumage de chaque page
	 * 
	 * @return les temps d'allumage en centièmes de seconde
	 */
	public List<Integer> getTempsAllumage() {
		return _tempsAllumage;
	}

	/**
	 * Initialise les temps d'allumage de chaque page
	 * 
	 * @param pTempsAllumage
	 *            les temps d'allumage en centièmes de seconde
	 */
	public void setTempsAllumage(List<Integer> pTemps) {
		if (pTemps == null) {
			pTemps = new ArrayList<>();
		}

		_tempsAllumage = pTemps;
	}

	/**
	 * Retourne les temps d'extinction de chaque page
	 * 
	 * @return les temps d'extinction en centièmes de seconde
	 */
	public List<Integer> getTempsExtinction() {
		return _tempsExtinction;
	}

	/**
	 * Initialise les temps d'extinction de chaque page
	 * 
	 * @param pTempsAllumage
	 *            les temps d'extinction en centièmes de seconde
	 */
	public void setTempsExtinction(List<Integer> pTemps) {
		if (pTemps == null) {
			pTemps = new ArrayList<>();
		}

		_tempsExtinction = pTemps;
	}

	/**
	 * Retourne la luminosité à appliquer sur le module
	 * 
	 * @return la luminosité
	 */
	public Luminosite getLuminosite() {
		return _luminosite;
	}

	/**
	 * Initialisation de la luminosité à appliquer sur le module
	 * 
	 * @param pLuminosite
	 *            la luminosité à utiliser
	 */
	public void setLuminosite(Luminosite pLuminosite) {
		_luminosite = pLuminosite;
	}

	/**
	 * Retourne le mode d'affichage
	 * 
	 * @return le mode d'affichage
	 */
	public Mode getModeAffichage() {
		return _modeAffichage;
	}

	/**
	 * Initialisation du mode d'affichage
	 * 
	 * @param pModeAffichage
	 *            le mode d'affichage
	 */
	public void setModeAffichage(Mode pModeAffichage) {
		_modeAffichage = pModeAffichage;
	}

	/**
	 * Retourne la durée de validité du message
	 * 
	 * @return la durée de validité en secondes
	 */
	public int getDureeValidite() {
		return _dureeValidite;
	}

	/**
	 * Initialisation de la durée de validité du message
	 * 
	 * @param pDuree
	 *            la durée de validité en secondes
	 */
	public void setDureeValidite(int pDuree) {
		_dureeValidite = pDuree;
	}

	/**
	 * Retourne la durée de validité restante du message
	 * 
	 * @return la durée de validité restante en secondes, -1 si pas gérée
	 */
	public int getDureeValiditeRestante() {
		return _dureeValiditeRestante;
	}

	/**
	 * Initialisation de la durée de validité restante du message
	 * 
	 * @param pDuree
	 *            la durée de validité restante en secondes, -1 pour ne pas gérer cette
	 *            valeur (valeur par défaut)
	 */
	public void setDureeValiditeRestante(int pDuree) {
		_dureeValiditeRestante = pDuree;
	}

	/**
	 * Copie de l'objet courant
	 * 
	 * @return une copie de l'instance courante
	 */
	public MessageModuleMqttRest duplique() {
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
