package org.signature.mqttRest.objetsPartages.informationPegase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonGetter;

/**
 * Propriétés d'un équipement utilisés par l'interface web. Les paramètres sont
 * liés à un client web, et peuvent varier d'un client à un autre
 * 
 * @author SDARIZCUREN
 *
 */
public class MessageProprietesEquipementWebMqttRest {
	private String _id;
	private Map<String, String> _nomsWeb;
	private Map<String, Boolean> _avecLabels;
	private Map<String, List<PositionSurSynoptique>> _positionsSynoptique;
	
	/**
	 * Construction du message
	 */
	public MessageProprietesEquipementWebMqttRest() {
		this("", new HashMap<>(), new HashMap<>(), new HashMap<>());
	}

	
	public MessageProprietesEquipementWebMqttRest(String pId, Map<String, String> pNomsWeb,
			Map<String, Boolean> pAvecLabels, Map<String, List<PositionSurSynoptique>> pPositionsSynoptique) {
		_id = pId;

		_nomsWeb = new HashMap<>();
		pNomsWeb.forEach((k, v) -> _nomsWeb.put(k, v));

		_avecLabels = new HashMap<>();
		pAvecLabels.forEach((k, v) -> _avecLabels.put(k, v));

		_positionsSynoptique = new HashMap<>();
		pPositionsSynoptique.forEach((k, v) -> _positionsSynoptique.put(k, v));
	}
	
	// Méthodes ci dessous pour la sérialisation json
	// Car je ne donne pas accès directement aux valeurs des objets
	@JsonGetter("_id")
	private String get_id() {
		return _id;
	}

	// Pour la sérialisation json
	@JsonGetter("_nomsWeb")
	private Map<String, String> get_nomsWeb() {
		return _nomsWeb;
	}

	// Pour la sérialisation json
	@JsonGetter("_avecLabels")
	private Map<String, Boolean> get_avecLabels() {
		return _avecLabels;
	}
	
	// Pour la sérialisation json
	@JsonGetter("_positionsSynoptique")
	private Map<String, List<PositionSurSynoptique>> get_positionsSynoptique() {
		return _positionsSynoptique;
	}

	/**
	 * Retourne l'identifiant de l'équipement
	 * 
	 * @return l'id de l'équipement
	 */
	public String getIdEquipement() {
		return _id;
	}

	/**
	 * Retourne le nom web de l'équipement, associé à l'identifiant du client web
	 * demandeur
	 * 
	 * @param pIdentifiantWeb
	 *            l'identifiant du client web
	 * @return le nom web pour l'interface web demandeur
	 */
	public String getNomWeb(String pIdentifiantWeb) {
		if (pIdentifiantWeb == null || !_nomsWeb.containsKey(pIdentifiantWeb)) {
			return "";
		}

		return _nomsWeb.get(pIdentifiantWeb);
	}
	

	/**
	 * Indique si le label doit être affiché sur le synoptique, selon l'identifiant
	 * du client web demandeur
	 * 
	 * @param pIdentifiantWeb
	 *            l'identifiant du client web
	 * 
	 * @return true si le label doit être affiché, false sinon
	 */
	public boolean getAvecLabel(String pIdentifiantWeb) {
		if (pIdentifiantWeb == null || !_avecLabels.containsKey(pIdentifiantWeb)) {
			return false;
		}

		return _avecLabels.get(pIdentifiantWeb);
	}

	/**
	 * Donne la position sur les différents synoptiques web, selon l'identifiant du
	 * client web demandeur
	 * 
	 * @param pIdentifiantWeb
	 *            l'identifiant du client web
	 * 
	 * @return les positions sur les différents synoptiques
	 */
	public List<PositionSurSynoptique> getPositionsSynoptiques(String pIdentifiantWeb) {
		if (pIdentifiantWeb == null || !_positionsSynoptique.containsKey(pIdentifiantWeb)) {
			return new ArrayList<PositionSurSynoptique>();
		}

		return _positionsSynoptique.get(pIdentifiantWeb);
	}

	/**
	 * Donne la position sur un synoptique web, selon l'identifiant du client web
	 * demandeur
	 * 
	 * @param pIdentifiantWeb
	 *            l'identifiant du client web
	 * @param pNomSynoptique
	 *            le nom du synoptique concerné
	 * 
	 * @return la position sur le synoptique, ou null si non défini
	 */
	public PositionSurSynoptique getPositionSynoptique(String pIdentifiantWeb, String pNomSynoptique) {
		if (pIdentifiantWeb == null || !_positionsSynoptique.containsKey(pIdentifiantWeb)) {
			return null;
		}

		return _positionsSynoptique.get(pIdentifiantWeb).stream()
				.filter(p -> p.getNomSynoptique().equals(pNomSynoptique)).findFirst().orElse(null);
	}
}
