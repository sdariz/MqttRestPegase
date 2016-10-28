package signature.mqttRest.services.rest.serveur;

import static spark.Spark.get;
import static spark.Spark.port;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import spark.QueryParamsMap;

/**
 * Initialisation d'un serveur HTTP REST
 * 
 * @author SDARIZCUREN
 *
 */
public class ServeurHttpRest {
	private ITraitementRequetesRest _traitementRequetesRest;
	private final static Logger LOG = LoggerFactory.getLogger(ServeurHttpRest.class);

	/**
	 * Démarre le serveur rest sur le port par défaut : 4567
	 * 
	 * @param pTraiteRequetesRest
	 *            l'objet qui va traiter les requêtes reçues
	 */
	public ServeurHttpRest(ITraitementRequetesRest pTraiteRequetesRest) {
		this(4567, pTraiteRequetesRest);
	}

	/**
	 * Démarre le serveur HTTP REST sur le port spécifié
	 * 
	 * @param port
	 *            le port à utiliser
	 * @param pTraiteRequetesRest
	 *            l'objet qui va traiter les requêtes reçues
	 */
	public ServeurHttpRest(int port, ITraitementRequetesRest pTraiteRequetesRest) {
		_traitementRequetesRest = pTraiteRequetesRest;

		port(port);

		creationRoutes();

		// Hook pour arrêter le serveur sur arrêt application
		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {
				LOG.info("Arret du serveur REST");
				spark.Spark.stop();
			}
		});
	}

	// Création des différentes routes
	private void creationRoutes() {
		// Les routes de type GET
		FactoryRoutes.GETRoutes().forEach(route -> {
			get(route, (req, res) -> {
				// Récupération des paramètres de la requête
				QueryParamsMap queryMap = req.queryMap();
				Map<String, String[]> map = queryMap.toMap();

				String retour = FactoryRoutes.traiteGET(route, map, _traitementRequetesRest);

				return retour;

			});
		});
	}

}
