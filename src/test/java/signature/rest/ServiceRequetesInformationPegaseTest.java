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

import signature.mqttRest.objetsPartages.informationPegase.MessageProprietesArmoireMqttRest;
import signature.mqttRest.objetsPartages.informationPegase.MessageProprietesEquipementMqttRest;
import signature.mqttRest.services.rest.client.InterrogationServeurHttpRest;
import signature.mqttRest.services.rest.serveur.ServeurHttpRest;
import signature.mqttRest.services.rest.serveur.TraitementRequetesRestAdapteur;

public class ServiceRequetesInformationPegaseTest {

	private static ServeurHttpRest serveurHttpRest;
	private static TraitementRequetesInformationPegase traitementsRequetesRest;

	private final static String HOST = "localhost";
	private final static int PORT = 1122;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		traitementsRequetesRest = new TraitementRequetesInformationPegase();

		// Démarrage du serveur HTTP REST
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
	 * Test de demandes des propriétés d'un équipement
	 */
	@Test
	public void testDemandeProprietesEquipement() {
		MessageProprietesEquipementMqttRest props = InterrogationServeurHttpRest
				.requeteDemandeProprietesEquipement(HOST, PORT, "ab", "cd", "1111");

		assertNotNull("Propriétés null", props);
		assertEquals("Id équipement incorrect", "1111", props.getIdEquipement());
		assertEquals("Id armoire incorrect", "9999", props.getIdArmoireCommande());
		assertEquals("Nom incorrect", "eqpt1", props.getNom());
		assertEquals("Information incorrect", "info1", props.getInformationComplementaire());
		assertEquals("Localisation incorrect", "loc1", props.getLocalisation());
		assertEquals("Référence incorrect", "ref1", props.getReference());
		assertEquals("Pk incorrect", "pk1", props.getPK());
		assertEquals("Sens incorrect", "sens1", props.getSens());
		assertEquals("Adresse incorrect", "adr1", props.getAdresse());
		assertEquals("Id groupe incorrect", 10, props.getIdGroupe());
	}
	
	/**
	 * Test de demandes des propriétés des équipements
	 */
	@Test
	public void testDemandeProprietesEquipements() {
		List<MessageProprietesEquipementMqttRest> props = InterrogationServeurHttpRest
				.requeteDemandeProprietesEquipements(HOST, PORT, "ab", "cd");

		assertEquals("Taille incorrect", 3, props.size());
		
		assertEquals("Id équipement 1 incorrect", "1111", props.get(0).getIdEquipement());
		assertEquals("Id équipement 2 incorrect", "2222", props.get(1).getIdEquipement());
		assertEquals("Id équipement 3 incorrect", "3333", props.get(2).getIdEquipement());
	}
	
	/**
	 * Test de demandes des propriétés d'une armoire
	 */
	@Test
	public void testDemandeProprietesArmoire() {
		MessageProprietesArmoireMqttRest props = InterrogationServeurHttpRest
				.requeteDemandeProprietesArmoire(HOST, PORT, "ab", "cd", "9999");

		assertNotNull("Propriétés null", props);
		assertEquals("Id armoire incorrect", "9999", props.getIdArmoire());
		assertEquals("Nom incorrect", "arm1", props.getNom());
		assertEquals("Information incorrect", "info1", props.getInformationComplementaire());
		assertEquals("Adresse incorrect", "adr1", props.getAdresse());
		assertEquals("Simulation incorrect", false, props.getSimulation());
		assertEquals("Taille incorrect", 3, props.getIdentifiantsEquipements().size());
		assertEquals("Ids eqpts incorrect", Arrays.asList("1111", "2222", "3333"), props.getIdentifiantsEquipements());
	}
	
	/**
	 * Test de demandes des propriétés des armoires
	 */
	@Test
	public void testDemandeProprietesArmoires() {
		List<MessageProprietesArmoireMqttRest> props = InterrogationServeurHttpRest
				.requeteDemandeProprietesArmoires(HOST, PORT, "ab", "cd");

		assertEquals("Taille incorrect", 3, props.size());
		
		assertEquals("Id armoire 1 incorrect", "9999", props.get(0).getIdArmoire());
		assertEquals("Id armoire 2 incorrect", "8888", props.get(1).getIdArmoire());
		assertEquals("Id armoire 3 incorrect", "7777", props.get(2).getIdArmoire());
	}

}

class TraitementRequetesInformationPegase extends TraitementRequetesRestAdapteur {

	@Override
	public MessageProprietesEquipementMqttRest traiteDemandeProprietesEquipement(String pIdentifiantExpediteur,
			String pReferenceCommande, String pIdEquipement) {
		assertEquals("Id expediteur incorrect", "ab", pIdentifiantExpediteur);
		assertEquals("Id commande incorrect", "cd", pReferenceCommande);
		assertEquals("Id équipement incorrect", "1111", pIdEquipement);
		
		MessageProprietesEquipementMqttRest retour = new MessageProprietesEquipementMqttRest(pIdEquipement, "9999",
				"eqpt1", "info1", "loc1", "ref1", "pk1", "sens1", "adr1", 10);
		
		return retour;
	}
	
	@Override
	public List<MessageProprietesEquipementMqttRest> traiteDemandeProprietesEquipements(String pIdentifiantExpediteur,
			String pReferenceCommande) {
		assertEquals("Id expediteur incorrect", "ab", pIdentifiantExpediteur);
		assertEquals("Id commande incorrect", "cd", pReferenceCommande);
		
		List<MessageProprietesEquipementMqttRest> retour = new ArrayList<>();
		retour.add(new MessageProprietesEquipementMqttRest("1111", "9999",
				"eqpt1", "info1", "loc1", "ref1", "pk1", "sens1", "adr1", 10));
		retour.add(new MessageProprietesEquipementMqttRest("2222", "8888",
				"eqpt2", "info2", "loc2", "ref2", "pk2", "sens2", "adr2", 9));
		retour.add(new MessageProprietesEquipementMqttRest("3333", "7777",
				"eqpt3", "info3", "loc3", "ref3", "pk3", "sens3", "adr3", 7));
		
		return retour;
	}
	
	@Override
	public MessageProprietesArmoireMqttRest traiteDemandeProprietesArmoire(String pIdentifiantExpediteur,
			String pReferenceCommande, String pIdArmoire) {
		assertEquals("Id expediteur incorrect", "ab", pIdentifiantExpediteur);
		assertEquals("Id commande incorrect", "cd", pReferenceCommande);
		assertEquals("Id équipement incorrect", "9999", pIdArmoire);
		
		MessageProprietesArmoireMqttRest retour = new MessageProprietesArmoireMqttRest(pIdArmoire,
				"arm1", "info1", "adr1", false, Arrays.asList("1111", "2222", "3333"));
		
		return retour;
	}
	
	@Override
	public List<MessageProprietesArmoireMqttRest> traiteDemandeProprietesArmoires(String pIdentifiantExpediteur,
			String pReferenceCommande) {
		assertEquals("Id expediteur incorrect", "ab", pIdentifiantExpediteur);
		assertEquals("Id commande incorrect", "cd", pReferenceCommande);
		
		List<MessageProprietesArmoireMqttRest> retour = new ArrayList<>();
		
		retour.add(new MessageProprietesArmoireMqttRest("9999",
				"arm1", "info1", "adr1", false, Arrays.asList("1111", "2222", "3333")));
		retour.add(new MessageProprietesArmoireMqttRest("8888",
				"arm1", "info1", "adr1", false, Arrays.asList("1111", "2222", "3333")));
		retour.add(new MessageProprietesArmoireMqttRest("7777",
				"arm1", "info1", "adr1", false, Arrays.asList("1111", "2222", "3333")));
		
		return retour;
	}
}
