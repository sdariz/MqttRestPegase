package org.signature.mqttRest.services.rest.client;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Un client pour envoyer des requ�tes REST au serveur. Ne pas utiliser
 * directement, mais passer par les m�thodes utilitaires de la classe
 * InterrogationServeurHttpRest
 * 
 * @author SDARIZCUREN
 *
 */
public class ClientHttpRest {
	private final static Logger LOG = LoggerFactory.getLogger(ClientHttpRest.class);

	/**
	 * Envoi d'une requ�te GET au serveur HTTP REST
	 * 
	 * @param pHost
	 *            l'adresse IP du serveur
	 * @param pPort
	 *            le port utilis�
	 * @param pUri
	 *            l'uri � contacter
	 * @return le r�sultat de la requ�te au format JSON, "" si probl�me
	 */
	public static String envoiRequeteGET(String pHost, int pPort, String pUri) {
		return envoiRequeteGET(pHost, pPort, pUri, new HashMap<>());
	}

	/**
	 * Envoi d'une requ�te GET au serveur HTTP REST
	 * 
	 * @param pHost
	 *            l'adresse IP du serveur
	 * @param pPort
	 *            le port utilis�
	 * @param pUri
	 *            l'uri � contacter
	 * @param pParametres
	 *            les param�tres de la requ�te
	 * @return le r�sultat de la requ�te au format JSON, "" si probl�me
	 */
	public static String envoiRequeteGET(String pHost, int pPort, String pUri, Map<String, String> pParametres) {
		CloseableHttpClient httpClient = HttpClients.createDefault();

		StringBuilder sb = new StringBuilder("http://");
		sb.append(pHost);
		sb.append(":");
		sb.append(pPort);
		sb.append("/");
		sb.append(pUri);

		// Construction d'une URI avec les param�tres demand�s
		String uri = "";
		try {
			URIBuilder uriBuilder = new URIBuilder("http://" + pHost + ":" + pPort + "/" + pUri);

			if (!pParametres.isEmpty()) {
				pParametres.forEach((k, v) -> {
					uriBuilder.addParameter(k, v);
				});
			}

			// uri = uriBuilder.build().toString();
			uri = uriBuilder.toString();
		} catch (Exception e) {
			LOG.error("Erreur de construction de l'URI", e);
			return "";
		}

		// Requ�te GET
		HttpGet httpGet = new HttpGet(uri);
		
		// Timeout de connexion
		RequestConfig defaultRequestConfig = RequestConfig.custom().setSocketTimeout(5000).setConnectTimeout(5000)
				.setConnectionRequestTimeout(5000).build();
		httpGet.setConfig(defaultRequestConfig);
		
		String retour = "";

		// Emission de la requ�te
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
	 * Envoi d'une requ�te POST au serveur HTTP REST
	 * 
	 * @param pHost
	 *            l'adresse IP du serveur
	 * @param pPort
	 *            le port utilis�
	 * @param pUri
	 *            l'uri � contacter
	 * @return la r�ponse du serveur au format JSON, "" si pas de r�ponse
	 */
	public static String envoiRequetePOST(String pHost, int pPort, String pUri) {
		return envoiRequetePOST(pHost, pPort, pUri, new HashMap<>());
	}

	/**
	 * Envoi d'une requ�te POST au serveur HTTP REST
	 * 
	 * @param pHost
	 *            l'adresse IP du serveur
	 * @param pPort
	 *            le port utilis�
	 * @param pUri
	 *            l'uri � contacter
	 * @param pParametres
	 *            les param�tres de la requ�te
	 * @return la r�ponse du serveur au format JSON, "" si pas de r�ponse
	 */
	public static String envoiRequetePOST(String pHost, int pPort, String pUri, Map<String, String> pParametres) {
		CloseableHttpClient httpClient = HttpClients.createDefault();

		StringBuilder sb = new StringBuilder("http://");
		sb.append(pHost);
		sb.append(":");
		sb.append(pPort);
		sb.append("/");
		sb.append(pUri);

		// Requ�te POST
		HttpPost httpPost = new HttpPost(sb.toString());

		// Timeout de connexion
		RequestConfig defaultRequestConfig = RequestConfig.custom().setSocketTimeout(5000).setConnectTimeout(5000)
				.setConnectionRequestTimeout(5000).build();
		httpPost.setConfig(defaultRequestConfig);

		// Construction de la liste des param�tres de la requ�te
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

		// Emission de la requ�te
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
