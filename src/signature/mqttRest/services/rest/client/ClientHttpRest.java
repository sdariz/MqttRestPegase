package signature.mqttRest.services.rest.client;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Un client pour envoyer des requêtes REST au serveur. Ne pas utiliser
 * directement, mais passer par les méthodes utilitaires de la classe
 * InterrogationServeurHttpRest
 * 
 * @author SDARIZCUREN
 *
 */
public class ClientHttpRest {
	private final static Logger LOG = LoggerFactory.getLogger(ClientHttpRest.class);

	/**
	 * Envoi d'une requête GET au serveur HTTP REST
	 * 
	 * @param pHost
	 *            l'adresse IP du serveur
	 * @param pPort
	 *            le port utilisé
	 * @param pUri
	 *            l'uri à contacter
	 * @return le résultat de la requête au format JSON, "" si problème
	 */
	public static String envoiRequeteGET(String pHost, int pPort, String pUri) {
		return envoiRequeteGET(pHost, pPort, pUri, new HashMap<>());
	}

	/**
	 * Envoi d'une requête GET au serveur HTTP REST
	 * 
	 * @param pHost
	 *            l'adresse IP du serveur
	 * @param pPort
	 *            le port utilisé
	 * @param pUri
	 *            l'uri à contacter
	 * @param pParametres
	 *            les paramètres de la requête
	 * @return le résultat de la requête au format JSON, "" si problème
	 */
	public static String envoiRequeteGET(String pHost, int pPort, String pUri, Map<String, String> pParametres) {
		CloseableHttpClient httpClient = HttpClients.createDefault();

		StringBuilder sb = new StringBuilder("http://");
		sb.append(pHost);
		sb.append(":");
		sb.append(pPort);
		sb.append("/");
		sb.append(pUri);

		// Ajout des paramètres
		if (!pParametres.isEmpty()) {
			sb.append("?");
			pParametres.forEach((k, v) -> {
				sb.append(k);
				sb.append("=");
				sb.append(v);
				sb.append("&");
			});

			// Suppression & en fin de chaîne
			sb.deleteCharAt(sb.length() - 1);
		}

		// Requête GET
		HttpGet httpGet = new HttpGet(sb.toString());
		String retour = "";

		// Emission de la requête
		try (CloseableHttpResponse httpReponse = httpClient.execute(httpGet)) {
			HttpEntity entity = httpReponse.getEntity();
			if (entity != null) {
				retour = EntityUtils.toString(entity);
			}

			EntityUtils.consume(entity);
		} catch (ClientProtocolException e) {
			LOG.error("Erreur negociation", e);
		} catch (IOException e) {
			LOG.error("Erreur communication", e);
		}

		return retour;
	}
	
	/**
	 * Envoi d'une requête POST au serveur HTTP REST
	 * 
	 * @param pHost
	 *            l'adresse IP du serveur
	 * @param pPort
	 *            le port utilisé
	 * @param pUri
	 *            l'uri à contacter
	 * @return la réponse du serveur au format JSON, "" si pas de réponse
	 */
	public static String envoiRequetePOST(String pHost, int pPort, String pUri) {
		return envoiRequetePOST(pHost, pPort, pUri, new HashMap<>());
	}

	/**
	 * Envoi d'une requête POST au serveur HTTP REST
	 * 
	 * @param pHost
	 *            l'adresse IP du serveur
	 * @param pPort
	 *            le port utilisé
	 * @param pUri
	 *            l'uri à contacter
	 * @param pParametres
	 *            les paramètres de la requête
	 * @return la réponse du serveur au format JSON, "" si pas de réponse
	 */
	public static String envoiRequetePOST(String pHost, int pPort, String pUri, Map<String, String> pParametres) {
		CloseableHttpClient httpClient = HttpClients.createDefault();

		StringBuilder sb = new StringBuilder("http://");
		sb.append(pHost);
		sb.append(":");
		sb.append(pPort);
		sb.append("/");
		sb.append(pUri);

		// Requête POST
		HttpPost httpPost = new HttpPost(sb.toString());

		// Construction de la liste des paramètres de la requête
		List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
		pParametres.forEach((k, v) -> {
			urlParameters.add(new BasicNameValuePair(k, v));
		});

		try {
			httpPost.setEntity(new UrlEncodedFormEntity(urlParameters));
		} catch (UnsupportedEncodingException e) {
			LOG.error("Erreur encodage parametres POST", e);
		}

		String retour = "";

		// Emission de la requête
		try (CloseableHttpResponse httpReponse = httpClient.execute(httpPost)) {
			HttpEntity entity = httpReponse.getEntity();
			if (entity != null) {
				retour = EntityUtils.toString(entity);
			}

			EntityUtils.consume(entity);
		} catch (ClientProtocolException e) {
			LOG.error("Erreur negociation", e);
		} catch (IOException e) {
			LOG.error("Erreur communication", e);
		}

		return retour;
	}
}
