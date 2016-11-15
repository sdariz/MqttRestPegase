package signature.rest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import signature.mqttRest.objetsPartages.etatEtPilotage.MessageModuleMqttRest;
import signature.mqttRest.objetsPartages.etatEtPilotage.MessagePmvMqttRest;
import signature.mqttRest.services.rest.client.InterrogationServeurHttpRest;
import signature.mqttRest.services.rest.serveur.ServeurHttpRest;
import signature.mqttRest.services.rest.serveur.TraitementRequetesRestAdapteur;

public class ServiceRequetesBibliothequePmvTest {

	private static ServeurHttpRest serveurHttpRest;
	private static TraitementRequetesBibliothequePmv traitementsRequetesRest;

	private final static String HOST = "localhost";
	private final static int PORT = 1122;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		traitementsRequetesRest = new TraitementRequetesBibliothequePmv();

		// D�marrage du serveur HTTP REST
		serveurHttpRest = new ServeurHttpRest(PORT, traitementsRequetesRest);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		serveurHttpRest.arretServeur();
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test de r�cup�ration des cat�gories de la biblioth�que PMV
	 */
	@Test
	public void testListeCategoriesBibliothequePmv() {
		List<String> categories = InterrogationServeurHttpRest.requeteDemandeCategoriesBibliothequePmv(HOST, PORT, "ab",
				"cd");

		assertEquals("Taille incorrect", 3, categories.size());
		assertEquals("Cat�gorie incorrect", "Cat�gorie 1", categories.get(0));
		assertEquals("Cat�gorie incorrect", "Cat�gorie 2", categories.get(1));
		assertEquals("Cat�gorie incorrect", "Cat�gorie 3", categories.get(2));
	}

	/**
	 * Test de r�cup�ration des messages d'une cat�gorie de la biblioth�que PMV
	 */
	@Test
	public void testListeMessagesDansCategorieBibliothequePmv() {
		List<String> messages = InterrogationServeurHttpRest.requeteDemandeMessagesDansCategorieBibliothequePmv(HOST,
				PORT, "ab", "cd", "Cat�gorie 1");

		assertEquals("Taille incorrect", 3, messages.size());
		assertEquals("Cat�gorie incorrect", "Message 1", messages.get(0));
		assertEquals("Cat�gorie incorrect", "Message 2", messages.get(1));
		assertEquals("Cat�gorie incorrect", "Message 3", messages.get(2));
	}
	
	/**
	 * Test de r�cup�ration d'un message de la biblioth�que PMV
	 */
	@Test
	public void testMessageBibliothequePmv() {
		MessagePmvMqttRest msg = InterrogationServeurHttpRest.requeteDemandeMessageBibliothequePmv(HOST,
				PORT, "ab", "cd", "Cat�gorie 1", "Message 1");

		assertNotNull("Message null", msg);
		assertEquals("Nombre de lignes incorrect", 3, msg.getMessagesLignes().size());
	}

}

class TraitementRequetesBibliothequePmv extends TraitementRequetesRestAdapteur {

	@Override
	public List<String> traiteDemandeCategoriesBibliothequePmv(String pIdentifiantExpediteur,
			String pReferenceCommande) {
		assertEquals("Id expediteur incorrect", "ab", pIdentifiantExpediteur);
		assertEquals("Id commande incorrect", "cd", pReferenceCommande);

		return Arrays.asList("Cat�gorie 1", "Cat�gorie 2", "Cat�gorie 3");
	}

	@Override
	public List<String> traiteDemandeMessagesDansCategorieBibliothequePmv(String pIdentifiantExpediteur,
			String pReferenceCommande, String pCategorie) {
		assertEquals("Id expediteur incorrect", "ab", pIdentifiantExpediteur);
		assertEquals("Id commande incorrect", "cd", pReferenceCommande);
		assertEquals("Cat�gorie incorrect", "Cat�gorie 1", pCategorie);

		return Arrays.asList("Message 1", "Message 2", "Message 3");
	}
	
	@Override
	public MessagePmvMqttRest traiteDemandeMessageBibliothequePmv(String pIdentifiantExpediteur,
			String pReferenceCommande, String pCategorie, String pNom) {
		assertEquals("Id expediteur incorrect", "ab", pIdentifiantExpediteur);
		assertEquals("Id commande incorrect", "cd", pReferenceCommande);
		assertEquals("Cat�gorie incorrect", "Cat�gorie 1", pCategorie);
		assertEquals("Nom incorrect", "Message 1", pNom);

		MessagePmvMqttRest retour = new MessagePmvMqttRest();
		
		List<MessageModuleMqttRest> lignes = new ArrayList<>();
		lignes.add(new MessageModuleMqttRest());
		lignes.add(new MessageModuleMqttRest());
		lignes.add(new MessageModuleMqttRest());
		retour.setMessagesLignes(lignes);
		
		return retour;
	}
}
