package org.signature.mqttRest.services.mqtt;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;

/**
 * Un gestionnaire pour créer ou purger les répertoires de travail (persistance
 * des messages échangés)
 * 
 * @author SDARIZCUREN
 *
 */
public class GestionnaireRepertoiresTravail {
	private static GestionnaireRepertoiresTravail _instance = null;
	private boolean dossierClientsPurges = false;
	
	public final static String DOSSIER_PERSISTANCES_MESSAGES_CLIENTS = "mqtt-clients";

	/**
	 * Construction de l'instance unique
	 */
	private GestionnaireRepertoiresTravail() {
		
	}

	/**
	 * Accès à l'instance unique de la classe
	 * 
	 * @return l'instance unique
	 */
	public final static synchronized GestionnaireRepertoiresTravail getInstance() {
		if (_instance == null) {
			_instance = new GestionnaireRepertoiresTravail();
		}

		return _instance;
	}
	
	/**
	 * Purge du dossier des messages persistants des clients au démarrage.
	 * Purge si pas déjà fait
	 */
	public synchronized void purgeDossierPersistanceClientsUneFoisAuDemarrage() {
		if(dossierClientsPurges == true) {
			return;
		}
		
		suppressionCreationDossier(DOSSIER_PERSISTANCES_MESSAGES_CLIENTS);
		
		dossierClientsPurges = true;
	}
	
	// Suppression des fichiers du repertoire, et recréation
	private void suppressionCreationDossier(String pRepertoire) {
		Path rootPath = Paths.get(pRepertoire);
		try {
			// Suppression du dossier courant apres ses sous dossiers grace à reverseOrder
			Files.walk(rootPath, FileVisitOption.FOLLOW_LINKS)
			    .sorted(Comparator.reverseOrder())
			    .map(Path::toFile)
			    .forEach(File::delete);
		} catch (IOException e) {
		}
		
		try {
			// Recréation du dossier
			Files.createDirectories(rootPath);
		} catch (IOException e) {
		}
	}
	
	

}
