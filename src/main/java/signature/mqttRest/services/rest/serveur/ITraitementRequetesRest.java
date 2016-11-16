package signature.mqttRest.services.rest.serveur;

import signature.mqttRest.services.rest.serveur.administration.ITraitementRequetesAdministration;
import signature.mqttRest.services.rest.serveur.bibliothequePmv.ITraitementRequetesBibliothequePmv;
import signature.mqttRest.services.rest.serveur.etatEtPilotage.ITraitementRequetesEtatEquipements;
import signature.mqttRest.services.rest.serveur.evenement.ITraitementRequetesEvenement;
import signature.mqttRest.services.rest.serveur.informationPegase.ITraitementRequetesInformationPegase;
import signature.mqttRest.services.rest.serveur.interrogationArmoire.ITraitementRequetesInterrogationArmoire;
import signature.mqttRest.services.rest.serveur.scenario.ITraitementRequetesScenario;
import signature.mqttRest.services.rest.serveur.utilisateur.ITraitementRequetesUtilisateur;

/**
 * Interface d�crivant les m�thodes � impl�menter pour traiter les requ�tes REST
 * 
 * @author SDARIZCUREN
 *
 */
public interface ITraitementRequetesRest extends ITraitementRequetesEtatEquipements, ITraitementRequetesUtilisateur,
		ITraitementRequetesAdministration, ITraitementRequetesInterrogationArmoire, ITraitementRequetesBibliothequePmv,
		ITraitementRequetesEvenement, ITraitementRequetesInformationPegase, ITraitementRequetesScenario {
}
