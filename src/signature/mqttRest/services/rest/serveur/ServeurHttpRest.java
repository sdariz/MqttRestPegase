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
	 * D�marre le serveur rest sur le port par d�faut : 4567
	 * 
	 * @param pTraiteRequetesRest
	 *            l'objet qui va traiter les requ�tes re�ues
	 */
	public ServeurHttpRest(ITraitementRequetesRest pTraiteRequetesRest) {
		this(4567, pTraiteRequetesRest);
	}

	/**
	 * D�marre le serveur HTTP REST sur le port sp�cifi�
	 * 
	 * @param port
	 *            le port � utiliser
	 * @param pTraiteRequetesRest
	 *            l'objet qui va traiter les requ�tes re�ues
	 */
	public ServeurHttpRest(int port, ITraitementRequetesRest pTraiteRequetesRest) {
		_traitementRequetesRest = pTraiteRequetesRest;

		port(port);

		creationRoutes();

		// Hook pour arr�ter le serveur sur arr�t application
		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {
				LOG.info("Arret du serveur REST");
				spark.Spark.stop();
			}
		});
	}

	// Cr�ation des diff�rentes routes
	private void creationRoutes() {
		// Les routes de type GET
		FactoryRoutes.GETRoutes().forEach(route -> {
			get(route, (req, res) -> {
				// R�cup�ration des param�tres de la requ�te
				QueryParamsMap queryMap = req.queryMap();
				Map<String, String[]> map = queryMap.toMap();

				String retour = FactoryRoutes.traiteGET(route, map, _traitementRequetesRest);

				return retour;

			});
		});
	}

}
