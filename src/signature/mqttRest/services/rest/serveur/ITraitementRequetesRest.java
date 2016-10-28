package signature.mqttRest.services.rest.serveur;

import signature.mqttRest.services.rest.etatEtPilotage.ITraitementRequetesEtatEquipements;
import signature.mqttRest.services.rest.utilisateur.ITraitementRequetesUtilisateur;

/**
 * Interface d�crivant les m�thodes � impl�menter pour traiter les requ�tes REST
 * @author SDARIZCUREN
 *
 */
public interface ITraitementRequetesRest extends ITraitementRequetesEtatEquipements, ITraitementRequetesUtilisateur {
}
