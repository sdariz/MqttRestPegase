package org.signature.mqtt;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.eclipse.paho.client.mqttv3.IMqttMessageListener;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;
import org.eclipse.paho.client.mqttv3.MqttSecurityException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.signature.mqttRest.objetsPartages.etatEtPilotage.MessageEtatAffichageMqttRest;
import org.signature.mqttRest.services.mqtt.ITopicMqtt.Topic;
import org.signature.mqttRest.services.mqtt.PublicationMqtt;

import io.netty.handler.codec.mqtt.MqttQoS;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.mqtt.MqttEndpoint;
import io.vertx.mqtt.MqttServer;
import io.vertx.mqtt.MqttServerOptions;
import io.vertx.mqtt.MqttTopicSubscription;

public class VertxBroker {

	//private final static String MQTT_TOPIC = "/my_topic";
	private final static String MQTT_TOPIC = Topic.ETAT_AFFICHAGE_EQUIPEMENT.toString();
	private final static String MQTT_MESSAGE = "Hello Vert.x MQTT Server";

	private List<MqttEndpoint> _listEndpoints = new ArrayList<>();

	public VertxBroker() {
		MqttServerOptions options = new MqttServerOptions().setPort(1883);
		Vertx vertx = Vertx.vertx();
		MqttServer server = MqttServer.create(vertx, options);

		
		server.endpointHandler(this::endpointHandler).listen(ar -> {

			if (ar.succeeded()) {
				System.out.println("MQTT server listening on port " + ar.result().actualPort());
			} else {
				System.out.println("Error starting MQTT server : " + ar.cause());
				System.exit(1);
			}
		});

	}

	public static void main(String[] args) {
		new VertxBroker();

		int qos = 2;

		try {
			MemoryPersistence persistence = new MemoryPersistence();
			MqttClient client1 = new MqttClient(String.format("tcp://%s:%d", "localhost", 1883), "12345", persistence);
			MqttConnectOptions connOpts = new MqttConnectOptions();
			connOpts.setCleanSession(true);
			connOpts.setAutomaticReconnect(true);
			connOpts.setMaxInflight(1000);
			client1.connect(connOpts);

			client1.subscribe(MQTT_TOPIC, qos, new IMqttMessageListener() {
				int cpt = 0;

				@Override
				public void messageArrived(String topic, MqttMessage mqttMessage) throws Exception {
					System.out.println("Dans messageArrived : " + (++cpt));
					System.out.println("Just received message [" + mqttMessage.toString() + "] on topic [" + topic
							+ "] with QoS [" + mqttMessage.getQos() + "]");

				}
			});

			persistence = new MemoryPersistence();
			MqttClient client0 = new MqttClient(String.format("tcp://%s:%d", "localhost", 1883), "12345", persistence);
			connOpts = new MqttConnectOptions();
			connOpts.setCleanSession(true);
			connOpts.setAutomaticReconnect(true);
			connOpts.setMaxInflight(1000);
			client0.connect(connOpts);

			client0.subscribe(MQTT_TOPIC, qos, new IMqttMessageListener() {
				int cpt = 0;

				@Override
				public void messageArrived(String topic, MqttMessage mqttMessage) throws Exception {
					System.out.println("Dans messageArrived : " + (++cpt));
					System.out.println("Just received message [" + mqttMessage.toString() + "] on topic [" + topic
							+ "] with QoS [" + mqttMessage.getQos() + "]");

				}
			});

			for (int i = 0; i < 1; i++) {

				ExecutorService service = Executors.newCachedThreadPool();
				service.execute(new Runnable() {

					@Override
					public void run() {

						try {
							Thread.sleep(100);

							MemoryPersistence persistence = new MemoryPersistence();
							MqttClient client2 = new MqttClient(String.format("tcp://%s:%d", "localhost", 1883),
									"67890", persistence);
							MqttConnectOptions connOpts = new MqttConnectOptions();
							connOpts.setCleanSession(true);
							connOpts.setAutomaticReconnect(true);
							connOpts.setMaxInflight(1000);
							client2.connect(connOpts);

							//client2.publish(MQTT_TOPIC, MQTT_MESSAGE.getBytes(), qos, false);

							client2.disconnect();
							client2.close();
							PublicationMqtt.publicationMessage(new MessageEtatAffichageMqttRest(), "localhost", 1883, Topic.ETAT_AFFICHAGE_EQUIPEMENT);
						} catch (MqttPersistenceException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (MqttException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
				service.shutdown();

				/*
				 * new Thread(() -> { try { client.publish(MQTT_TOPIC,
				 * MQTT_MESSAGE.getBytes(), qos, false); } catch
				 * (MqttPersistenceException e) { // TODO Auto-generated catch
				 * block e.printStackTrace(); } catch (MqttException e) { //
				 * TODO Auto-generated catch block e.printStackTrace(); }
				 * }).start();
				 * 
				 */

			}

		} catch (MqttSecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MqttException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		while (true) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		// System.out.println("Fin");
	}

	private void endpointHandler(MqttEndpoint endpoint) {

		System.out.println("1 - " + endpoint);

		// handling requests for subscriptions
		endpoint.subscribeHandler(subscribe -> {

			System.out.println("2");

			List<MqttQoS> grantedQosLevels = new ArrayList<>();
			for (MqttTopicSubscription s : subscribe.topicSubscriptions()) {
				System.out.println("Subscription for " + s.topicName() + " with QoS " + s.qualityOfService());
				grantedQosLevels.add(s.qualityOfService());
			}
			_listEndpoints.add(endpoint);

			// ack the subscriptions request
			endpoint.subscribeAcknowledge(subscribe.messageId(), grantedQosLevels);

		});

		// handling requests for unsubscriptions
		endpoint.unsubscribeHandler(unsubscribe -> {
			System.out.println("3");

			for (String t : unsubscribe.topics()) {
				System.out.println("Unsubscription for " + t);
			}

			_listEndpoints.remove(endpoint);

			// ack the subscriptions request
			endpoint.unsubscribeAcknowledge(unsubscribe.messageId());
		});

		endpoint.publishHandler(message -> {

			System.out.println("4");

			// System.out.println("Just received message on [" +
			// message.topicName() + "] payload ["
			// + message.payload().toString(Charset.defaultCharset()) + "] with
			// QoS [" + message.qosLevel() + "]");

			switch (message.qosLevel()) {

			case AT_LEAST_ONCE:

				endpoint.publishAcknowledge(message.messageId());
				break;

			case EXACTLY_ONCE:
				endpoint.publishReceived(message.messageId());
				break;

			case AT_MOST_ONCE:
				break;

			case FAILURE:
				System.out.println("Failure");
				break;
			}

			// Publication aux abonnés
			_listEndpoints.forEach(e -> {
				e.publish(MQTT_TOPIC, Buffer.buffer(MQTT_MESSAGE), message.qosLevel(), false, false);
				
				// specifing handlers for handling QoS 1 and 2
				e.publishAcknowledgeHandler(messageId -> {
					System.out.println("5");
					System.out.println("Received ack for message = " + messageId);

				}).publishReceivedHandler(messageId -> {
					System.out.println("6");
					System.out.println("publishReceivedHandler");

					e.publishRelease(messageId);

				}).publishCompleteHandler(messageId -> {
					System.out.println("7");
					System.out.println("Received ack for message = " + messageId);
				});
			});
		}).publishReleaseHandler(messageId -> {
			System.out.println("8");
			endpoint.publishComplete(messageId);
		});

		/*
		 * endpoint.publishAcknowledgeHandler(messageId -> { //
		 * System.out.println("publishReceivedHandler"); });
		 * endpoint.publishReceivedHandler(messageId -> { //
		 * System.out.println("publishReceivedHandler");
		 * endpoint.publishRelease(messageId); });
		 * endpoint.publishCompleteHandler(messageId -> { //
		 * System.out.println("publishReceivedHandler"); });
		 */

		// handling ping from client
		endpoint.pingHandler(v -> {
			System.out.println("9");

			// System.out.println("Ping received from client");
		});

		// handling disconnect message
		endpoint.disconnectHandler(v -> {
			System.out.println("10");

			// System.out.println("Received disconnect from client");
		});

		// handling closing connection
		endpoint.closeHandler(v -> {
			System.out.println("11");

			// System.out.println("Connection closed");
		});

		// accept connection from the remote client
		endpoint.accept(false);
	}
}
