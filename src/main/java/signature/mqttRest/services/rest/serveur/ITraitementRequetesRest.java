package signature.mqttRest.services.rest.serveur;

import signature.mqttRest.services.rest.serveur.administration.ITraitementRequetesAdministration;
import signature.mqttRest.services.rest.serveur.etatEtPilotage.ITraitementRequetesEtatEquipements;
import signature.mqttRest.services.rest.serveur.interrogationArmoire.ITraitementRequetesInterrogationArmoire;
import signature.mqttRest.services.rest.serveur.utilisateur.ITraitementRequetesUtilisateur;

/**
 * Interface décrivant les méthodes à implémenter pour traiter les requêtes REST
 * 
 * @author SDARIZCUREN
 *
 */
public interface ITraitementRequetesRest extends ITraitementRequetesEtatEquipements, ITraitementRequetesUtilisateur,
		ITraitementRequetesAdministration, ITraitementRequetesInterrogationArmoire {
}
