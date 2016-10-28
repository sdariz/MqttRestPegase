package signature.mqttRest.services.rest.serveur;

import signature.mqttRest.services.rest.etatEtPilotage.ITraitementRequetesEtatEquipements;
import signature.mqttRest.services.rest.utilisateur.ITraitementRequetesUtilisateur;

/**
 * Interface décrivant les méthodes à implémenter pour traiter les requêtes REST
 * @author SDARIZCUREN
 *
 */
public interface ITraitementRequetesRest extends ITraitementRequetesEtatEquipements, ITraitementRequetesUtilisateur {
}
