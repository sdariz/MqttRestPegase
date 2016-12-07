package org.signature.mqttRest.services.rest.serveur;

import org.signature.mqttRest.services.rest.serveur.administration.ITraitementRequetesAdministration;
import org.signature.mqttRest.services.rest.serveur.bibliothequePmv.ITraitementRequetesBibliothequePmv;
import org.signature.mqttRest.services.rest.serveur.etatEquipement.ITraitementRequetesEtatEquipements;
import org.signature.mqttRest.services.rest.serveur.evenement.ITraitementRequetesEvenement;
import org.signature.mqttRest.services.rest.serveur.informationPegase.ITraitementRequetesInformationPegase;
import org.signature.mqttRest.services.rest.serveur.interrogationArmoire.ITraitementRequetesInterrogationArmoire;
import org.signature.mqttRest.services.rest.serveur.pilotage.ITraitementRequetesPilotage;
import org.signature.mqttRest.services.rest.serveur.scenario.ITraitementRequetesScenario;
import org.signature.mqttRest.services.rest.serveur.utilisateur.ITraitementRequetesUtilisateur;

/**
 * Interface décrivant les méthodes à implémenter pour traiter les requêtes REST
 * 
 * @author SDARIZCUREN
 *
 */
public interface ITraitementRequetesRest
		extends ITraitementRequetesEtatEquipements, ITraitementRequetesUtilisateur, ITraitementRequetesAdministration,
		ITraitementRequetesInterrogationArmoire, ITraitementRequetesBibliothequePmv, ITraitementRequetesEvenement,
		ITraitementRequetesInformationPegase, ITraitementRequetesScenario, ITraitementRequetesPilotage {
}
