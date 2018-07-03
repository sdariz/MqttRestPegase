package org.signature.mqttRest.objetsPartages.informationPegase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	private Map<String, List<PositionSurSynoptique>> _positionsSynoptique;

	/**
	 * Construction du message
	 */
	public MessageProprietesEquipementWebMqttRest() {
		this("", new HashMap<>(), new HashMap<>());
	}

	/**
	 * Construction du message
	 * 
	 * @param pId
	 *            l'identifiant de l'équipement
	 */
	public MessageProprietesEquipementWebMqttRest(String pId) {
		this(pId, new HashMap<>(), new HashMap<>());
	}

	/**
	 * Construction du message
	 * 
	 * @param pId
	 *            l'identifiant de l'équipement
	 * @param pNomsWeb
	 *            les associations identifiantWeb/nomEquipement
	 * @param pPositionsSynoptique
	 *            les associations identifiantWeb/positionsSurSynoptique
	 */
	public MessageProprietesEquipementWebMqttRest(String pId, Map<String, String> pNomsWeb,
			Map<String, List<PositionSurSynoptique>> pPositionsSynoptique) {
		_id = pId;

		_nomsWeb = pNomsWeb;
		_positionsSynoptique = pPositionsSynoptique;
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
	 * Initialise l'identifiant de l'équipement
	 * 
	 * @param pId
	 *            l'id de l'équipement
	 */
	public void setIdEquipement(String pId) {
		if (pId != null) {
			_id = pId;
		}
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
	 * Retourne les nom web des l'équipements, associés aux identifiants des clients
	 * web
	 * 
	 * @return les associations identifiantWeb/nomEquipement
	 */
	public Map<String, String> getNomsWeb() {
		return _nomsWeb;
	}

	/**
	 * Initialise les nom web des l'équipements, associés aux identifiants des clients web
	 * 
	 * @param pNomsWeb les associations identifiantWeb/nomEquipement
	 */
	public void setNomsWeb(Map<String, String> pNomsWeb) {
		if(pNomsWeb != null) {
			_nomsWeb = pNomsWeb;
		}
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
	
	/**
	 * Retourne les positions sur synoptiques des l'équipements, associés aux identifiants des clients
	 * web
	 * 
	 * @return les associations identifiantWeb/positionsSurSynoptique
	 */
	public Map<String, List<PositionSurSynoptique>> getPositionsSynoptiques() {
		return _positionsSynoptique;
	}

	/**
	 * Initialise les positions sur synoptiques des l'équipements, associés aux identifiants des clients web
	 * 
	 * @param pPositionsSynoptique les associations identifiantWeb/positionsSurSynoptique
	 */
	public void setPositionsSynoptiques(Map<String, List<PositionSurSynoptique>> pPositionsSynoptique) {
		if(pPositionsSynoptique != null) {
			_positionsSynoptique = pPositionsSynoptique;
		}
	}
}
