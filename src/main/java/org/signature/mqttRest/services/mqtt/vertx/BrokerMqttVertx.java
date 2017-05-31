package org.signature.mqttRest.services.mqtt.vertx;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.signature.mqttRest.services.mqtt.IBrokerMqtt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.handler.codec.mqtt.MqttQoS;
import io.vertx.core.Vertx;
import io.vertx.mqtt.MqttEndpoint;
import io.vertx.mqtt.MqttServer;
import io.vertx.mqtt.MqttServerOptions;
import io.vertx.mqtt.MqttTopicSubscription;

/**
 * Le broker MQTT Vertx
 * 
 * @author SDARIZCUREN
 *
 */
public class BrokerMqttVertx implements IBrokerMqtt {
	private final static Logger LOG = LoggerFactory.getLogger(BrokerMqttVertx.class);

	private MqttServer _serveur = null;
	private Vertx _vertx;

	private List<MqttEndpoint> _listSuscribers;
	
	// Taille header + payload
	// Taille importante car un message doit pouvoir transporter un sc�nario, qui d�finit les affichages pour plusieurs PMV
	private final static int MAX_MESSAGE_SIZE = 1000000;

	/**
	 * D�marrage du broker MQTT
	 * 
	 * @param pPort
	 *            le port � utiliser
	 * @param pInMemoryDb
	 *            true pour ne pas sauvegarder les donn�es de fonctionnement du
	 *            serveur : reprise, ...
	 */
	public synchronized void startBroker(int pPort, boolean pInMemoryDb) {
		// Rien � faire si le serveur est d�j� d�marr�
		if (_serveur != null) {
			return;
		}

		_listSuscribers = new ArrayList<>();

		MqttServerOptions options = new MqttServerOptions().setPort(pPort).setMaxMessageSize(MAX_MESSAGE_SIZE);
		_vertx = Vertx.vertx();
		MqttServer server = MqttServer.create(_vertx, options);
		
		server.endpointHandler(this::endpointHandler).listen(ar -> {
			if (!ar.succeeded()) {
				LOG.error("Probl�me pour d�marrer le broker mqtt", ar.cause());
			}
		});
	}

	/**
	 * Arr�t du broker MQTT
	 */
	public synchronized void stopBroker() {
		if (_serveur != null) {
			_serveur.close();
			_vertx.close();
			_serveur = null;
		}
	}

	// Handler des �v�nements MQTT
	private void endpointHandler(MqttEndpoint pEndpoint) {
		// Reception des requ�tes d'inscription
		pEndpoint.subscribeHandler(subscribe -> {
			// Sauvegarde des abonn�s, car c'est vers eux qu'ils faudra poster
			// sur r�ception d'un message
			_listSuscribers.add(pEndpoint);

			// Collecte les QOS de chaque topic
			List<MqttQoS> grantedQosLevels = subscribe.topicSubscriptions().stream()
					.map(MqttTopicSubscription::qualityOfService).collect(Collectors.toList());

			// Acquittement de la requ�te d'inscription
			pEndpoint.subscribeAcknowledge(subscribe.messageId(), grantedQosLevels);
		}).unsubscribeHandler(unsubscribe -> {
			// Suppression de l'abonn�
			_listSuscribers.remove(pEndpoint);

			// Acquittement de la requ�te
			pEndpoint.unsubscribeAcknowledge(unsubscribe.messageId());
		});

		// R�ception d'un message � publier
		pEndpoint.publishHandler(message -> {
			switch (message.qosLevel()) {
			case AT_LEAST_ONCE:
				pEndpoint.publishAcknowledge(message.messageId());
				break;

			case EXACTLY_ONCE:
				pEndpoint.publishReceived(message.messageId());
				break;

			case AT_MOST_ONCE:
				break;

			case FAILURE:
				LOG.error("Probl�me de publication d'un message mqtt");
				return;
			}

			// Publication aux abonn�s
			_listSuscribers.forEach(e -> {
				// Que pour les clients encore abonn�s
				if (e.isConnected()) {
					e.publish(message.topicName(), message.payload(), message.qosLevel(), false, false);

					e.publishReceivedHandler(messageId -> {
						e.publishRelease(messageId);
					});
				}
			});
		}).publishReleaseHandler(messageId -> {
			pEndpoint.publishComplete(messageId);
		});

		// Accepte la demande de connection du client distant
		pEndpoint.accept(false);
	}

}
