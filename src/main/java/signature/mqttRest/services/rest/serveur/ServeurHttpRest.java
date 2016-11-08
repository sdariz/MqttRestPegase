package signature.mqttRest.services.rest.serveur;

import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.port;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import spark.QueryParamsMap;

/**
 * Initialisation d'un serveur HTTP REST, avec mise en place des diff�rentes
 * routes.
 * 
 * @author SDARIZCUREN
 *
 */
public class ServeurHttpRest {
	private ITraitementRequetesRest _traitementRequetesRest;
	private final static Logger LOG = LoggerFactory.getLogger(ServeurHttpRest.class);
	
	private final static int PORT = 4567;
	
	/**
	 * D�marre le serveur rest sur le port par d�faut : 4567.
	 * 
	 */
	public ServeurHttpRest() {
		this(null);
	}

	/**
	 * D�marre le serveur rest sur le port par d�faut : 4567, et met en place
	 * les routes � traiter.
	 * 
	 * @param pTraiteRequetesRest
	 *            l'objet qui va traiter les requ�tes re�ues
	 */
	public ServeurHttpRest(ITraitementRequetesRest pTraiteRequetesRest) {
		this(PORT, pTraiteRequetesRest);
	}

	/**
	 * D�marre le serveur HTTP REST sur le port sp�cifi�, et met en place les
	 * routes � traiter.
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
				LOG.warn("Arret du serveur REST");
				spark.Spark.stop();
			}
		});
	}
	
	/**
	 * Initialise l'objet qui va traiter les requ�tes re�ues
	 * @param pTraiteRequetesRest l'objet qui va traiter les requ�tes re�ues
	 */
	public void setTraitementRequetesRest(ITraitementRequetesRest pTraiteRequetesRest) {
		_traitementRequetesRest = pTraiteRequetesRest;
	}
	
	/**
	 * Demande l'arret du serveur
	 */
	public void arretServeur() {
		spark.Spark.stop();
	}

	// Cr�ation des diff�rentes routes
	private void creationRoutes() {
		creationRoutesGet();
		creationRoutesPost();
	}

	// Cr�ation des routes GET
	private void creationRoutesGet() {
		GestionnaireRoutes.GETRoutes().forEach(route -> {
			get(route, (req, res) -> {
				if(_traitementRequetesRest == null) {
					return "";
				}
				
				// R�cup�ration des param�tres de la requ�te
				QueryParamsMap queryMap = req.queryMap();
				Map<String, String[]> map = queryMap.toMap();

				String retour = GestionnaireRoutes.traiteGET(route, map, _traitementRequetesRest);

				return retour;
			});
		});
	}

	// Cr�ation des routes POST
	private void creationRoutesPost() {
		GestionnaireRoutes.POSTRoutes().forEach(route -> {
			post(route, (req, res) -> {
				if(_traitementRequetesRest == null) {
					return "";
				}
				
				// R�cup�ration des param�tres de la requ�te
				QueryParamsMap queryMap = req.queryMap();
				Map<String, String[]> map = queryMap.toMap();

				String retour = GestionnaireRoutes.traitePOST(route, map, _traitementRequetesRest);

				return retour;
			});
		});
	}

}
