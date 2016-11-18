package signature.mqttRest.services.rest.serveur.pilotage;

import java.util.List;

import signature.mqttRest.objetsPartages.etatEtPilotage.IMessageAffichageEquipementMqttRest;
import signature.mqttRest.objetsPartages.etatEtPilotage.MessageBarriereMqttRest;
import signature.mqttRest.objetsPartages.etatEtPilotage.MessageBraMqttRest;
import signature.mqttRest.objetsPartages.etatEtPilotage.MessagePictogrammeMqttRest;
import signature.mqttRest.objetsPartages.etatEtPilotage.MessagePmvMqttRest;
import signature.mqttRest.objetsPartages.etatEtPilotage.MessagePpadMqttRest;
import signature.mqttRest.objetsPartages.etatEtPilotage.MessagePplmvMqttRest;
import signature.mqttRest.objetsPartages.etatEtPilotage.MessagePrismeMqttRest;
import signature.mqttRest.objetsPartages.etatEtPilotage.MessageR24MqttRest;
import signature.mqttRest.objetsPartages.scenario.MessageScenarioMqttRest;

public interface ITraitementRequetesPilotage {

	/**
	 * Traite une demande de pilotage d'un scénario, via l'identifiant fourni
	 * 
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'expéditeur : peut être vide
	 * @param pReferenceCommande
	 *            la référence unique de la demande : peut être vide
	 * @param pIdScenario
	 *            l'identifiant du scénario à piloter
	 */
	public void traiteDemandePilotageScenario(String pIdentifiantExpediteur, String pReferenceCommande,
			String pIdScenario);

	/**
	 * Traite une demande de pilotage d'un scénario, via le scénario temporaire
	 * fourni
	 * 
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'expéditeur : peut être vide
	 * @param pReferenceCommande
	 *            la référence unique de la demande : peut être vide
	 * @param pScenarioTemporaire
	 *            le scénario temporaire à piloter
	 */
	public void traiteDemandePilotageScenario(String pIdentifiantExpediteur, String pReferenceCommande,
			MessageScenarioMqttRest pScenarioTemporaire);

	/**
	 * Traite une demande de pilotage d'une liste de messages dans un scénario
	 * 
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'expéditeur : peut être vide
	 * @param pReferenceCommande
	 *            la référence unique de la demande : peut être vide
	 * @param pIdScenario
	 *            l'identifiant du scénario concerné
	 * @param pMessagesAPiloter
	 *            les messages à piloter dans le scénario
	 */
	public void traiteDemandePilotageScenario(String pIdentifiantExpediteur, String pReferenceCommande,
			String pIdScenario, List<IMessageAffichageEquipementMqttRest> pMessagesAPiloter);
	
	/**
	 * Traite une demande de pilotage d'un PMV
	 * 
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'expéditeur : peut être vide
	 * @param pReferenceCommande
	 *            la référence unique de la demande : peut être vide
	 * @param pIdEquipement
	 *            l'identifiant de l'équipement à piloter
	 * @param pMessageAPiloter
	 *            le message à piloter
	 */
	public void traiteDemandePilotagePmv(String pIdentifiantExpediteur, String pReferenceCommande,
			String pIdEquipement, MessagePmvMqttRest pMessageAPiloter);
	
	/**
	 * Traite une demande de pilotage d'un PPLMV
	 * 
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'expéditeur : peut être vide
	 * @param pReferenceCommande
	 *            la référence unique de la demande : peut être vide
	 * @param pIdEquipement
	 *            l'identifiant de l'équipement à piloter
	 * @param pMessageAPiloter
	 *            le message à piloter
	 */
	public void traiteDemandePilotagePplmv(String pIdentifiantExpediteur, String pReferenceCommande,
			String pIdEquipement, MessagePplmvMqttRest pMessageAPiloter);
	
	/**
	 * Traite une demande de pilotage d'un PPAD
	 * 
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'expéditeur : peut être vide
	 * @param pReferenceCommande
	 *            la référence unique de la demande : peut être vide
	 * @param pIdEquipement
	 *            l'identifiant de l'équipement à piloter
	 * @param pMessageAPiloter
	 *            le message à piloter
	 */
	public void traiteDemandePilotagePpad(String pIdentifiantExpediteur, String pReferenceCommande,
			String pIdEquipement, MessagePpadMqttRest pMessageAPiloter);
	
	/**
	 * Traite une demande de pilotage d'un Pictogramme
	 * 
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'expéditeur : peut être vide
	 * @param pReferenceCommande
	 *            la référence unique de la demande : peut être vide
	 * @param pIdEquipement
	 *            l'identifiant de l'équipement à piloter
	 * @param pMessageAPiloter
	 *            le message à piloter
	 */
	public void traiteDemandePilotagePictogramme(String pIdentifiantExpediteur, String pReferenceCommande,
			String pIdEquipement, MessagePictogrammeMqttRest pMessageAPiloter);
	
	/**
	 * Traite une demande de pilotage d'un Feu R24
	 * 
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'expéditeur : peut être vide
	 * @param pReferenceCommande
	 *            la référence unique de la demande : peut être vide
	 * @param pIdEquipement
	 *            l'identifiant de l'équipement à piloter
	 * @param pMessageAPiloter
	 *            le message à piloter
	 */
	public void traiteDemandePilotageR24(String pIdentifiantExpediteur, String pReferenceCommande,
			String pIdEquipement, MessageR24MqttRest pMessageAPiloter);
	
	/**
	 * Traite une demande de pilotage d'un Prisme
	 * 
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'expéditeur : peut être vide
	 * @param pReferenceCommande
	 *            la référence unique de la demande : peut être vide
	 * @param pIdEquipement
	 *            l'identifiant de l'équipement à piloter
	 * @param pMessageAPiloter
	 *            le message à piloter
	 */
	public void traiteDemandePilotagePrisme(String pIdentifiantExpediteur, String pReferenceCommande,
			String pIdEquipement, MessagePrismeMqttRest pMessageAPiloter);
	
	/**
	 * Traite une demande de pilotage d'une Barrière
	 * 
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'expéditeur : peut être vide
	 * @param pReferenceCommande
	 *            la référence unique de la demande : peut être vide
	 * @param pIdEquipement
	 *            l'identifiant de l'équipement à piloter
	 * @param pMessageAPiloter
	 *            le message à piloter
	 */
	public void traiteDemandePilotageBarriere(String pIdentifiantExpediteur, String pReferenceCommande,
			String pIdEquipement, MessageBarriereMqttRest pMessageAPiloter);
	
	/**
	 * Traite une demande de pilotage d'un BRA
	 * 
	 * @param pIdentifiantExpediteur
	 *            l'identifiant unique de l'expéditeur : peut être vide
	 * @param pReferenceCommande
	 *            la référence unique de la demande : peut être vide
	 * @param pIdEquipement
	 *            l'identifiant de l'équipement à piloter
	 * @param pMessageAPiloter
	 *            le message à piloter
	 */
	public void traiteDemandePilotageBra(String pIdentifiantExpediteur, String pReferenceCommande,
			String pIdEquipement, MessageBraMqttRest pMessageAPiloter);
}
