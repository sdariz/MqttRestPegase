package org.signature.rest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.signature.mqttRest.objetsPartages.informationPegase.MessageProprietesArmoireMqttRest;
import org.signature.mqttRest.objetsPartages.informationPegase.MessageProprietesEquipementMqttRest;
import org.signature.mqttRest.objetsPartages.informationPegase.MessageProprietesEquipementWebMqttRest;
import org.signature.mqttRest.objetsPartages.informationPegase.PositionSurSynoptique;
import org.signature.mqttRest.objetsPartages.informationPegase.PositionSurSynoptique.FORMAT;
import org.signature.mqttRest.services.rest.client.InterrogationServeurHttpRest;
import org.signature.mqttRest.services.rest.serveur.ServeurHttpRest;
import org.signature.mqttRest.services.rest.serveur.TraitementRequetesRestAdapteur;

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
		MessageProprietesEquipementMqttRest props = new InterrogationServeurHttpRest()
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
		List<MessageProprietesEquipementMqttRest> props = new InterrogationServeurHttpRest()
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
		MessageProprietesArmoireMqttRest props = new InterrogationServeurHttpRest()
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
		List<MessageProprietesArmoireMqttRest> props = new InterrogationServeurHttpRest()
				.requeteDemandeProprietesArmoires(HOST, PORT, "ab", "cd");

		assertEquals("Taille incorrect", 3, props.size());
		
		assertEquals("Id armoire 1 incorrect", "9999", props.get(0).getIdArmoire());
		assertEquals("Id armoire 2 incorrect", "8888", props.get(1).getIdArmoire());
		assertEquals("Id armoire 3 incorrect", "7777", props.get(2).getIdArmoire());
	}
	
	/**
	 * Test de demandes des propriétés d'un équipement web
	 */
	@Test
	public void testDemandeProprietesEquipementWeb() {
		MessageProprietesEquipementWebMqttRest props = new InterrogationServeurHttpRest()
				.requeteDemandeProprietesEquipementWeb(HOST, PORT, "ab", "cd", "1111");

		assertNotNull("Propriétés null", props);
		
		assertEquals("Id équipement incorrect", "1111", props.getIdEquipement());
		
		assertEquals("Web1 nom incorrect", "nom1", props.getNomWeb("web1"));
		assertEquals("Web2 nom incorrect", "nom2", props.getNomWeb("web2"));
		
		assertEquals("PositionSurSynoptique sur interface Web1 Nom1 incorrect", "syno1", props.getPositionSynoptique("web1", "syno1").getNomSynoptique());
		assertEquals("PositionSurSynoptique sur interface Web1 Nom2 incorrect", "syno2", props.getPositionSynoptique("web1", "syno2").getNomSynoptique());
		
		assertEquals("PositionSurSynoptique sur interface Web1 syno1 X incorrect", 10, props.getPositionSynoptique("web1", "syno1").getXlocation());
		assertEquals("PositionSurSynoptique sur interface Web1 syno1 Y incorrect", 20, props.getPositionSynoptique("web1", "syno1").getYlocation());
		
		assertEquals("PositionSurSynoptique sur interface Web1 syno2 X incorrect", 30, props.getPositionSynoptique("web1", "syno2").getXlocation());
		assertEquals("PositionSurSynoptique sur interface Web1 syno2 Y incorrect", 40, props.getPositionSynoptique("web1", "syno2").getYlocation());
		
		assertEquals("PositionSurSynoptique sur interface Web1 syno1 format incorrect", FORMAT.ICONE, props.getPositionSynoptique("web1", "syno1").getformat());
		assertEquals("PositionSurSynoptique sur interface Web1 syno2 format incorrect", FORMAT.REEL, props.getPositionSynoptique("web1", "syno2").getformat());
		
		assertEquals("PositionSurSynoptique sur interface Web1 syno1 avec label incorrect", true, props.getPositionSynoptique("web1", "syno1").getAvecLabel());
		assertEquals("PositionSurSynoptique sur interface Web1 syno2 avec label incorrect", false, props.getPositionSynoptique("web1", "syno2").getAvecLabel());		
		
		assertEquals("PositionSurSynoptique sur interface Web2 Nom1 incorrect", "syno1", props.getPositionSynoptique("web2", "syno1").getNomSynoptique());
		assertEquals("PositionSurSynoptique sur interface Web2 Nom2 incorrect", "syno2", props.getPositionSynoptique("web2", "syno2").getNomSynoptique());
		
		assertEquals("PositionSurSynoptique sur interface Web2 syno1 X incorrect", 100, props.getPositionSynoptique("web2", "syno1").getXlocation());
		assertEquals("PositionSurSynoptique sur interface Web2 syno1 Y incorrect", 200, props.getPositionSynoptique("web2", "syno1").getYlocation());
		
		assertEquals("PositionSurSynoptique sur interface Web2 syno2 X incorrect", 300, props.getPositionSynoptique("web2", "syno2").getXlocation());
		assertEquals("PositionSurSynoptique sur interface Web2 syno2 Y incorrect", 400, props.getPositionSynoptique("web2", "syno2").getYlocation());
		
		assertEquals("PositionSurSynoptique sur interface Web2 syno1 format incorrect", FORMAT.REEL, props.getPositionSynoptique("web2", "syno1").getformat());
		assertEquals("PositionSurSynoptique sur interface Web2 syno2 format incorrect", FORMAT.ICONE, props.getPositionSynoptique("web2", "syno2").getformat());
		
		assertEquals("PositionSurSynoptique sur interface Web2 syno1 avec label incorrect", false, props.getPositionSynoptique("web2", "syno1").getAvecLabel());
		assertEquals("PositionSurSynoptique sur interface Web2 syno2 avec label incorrect", true, props.getPositionSynoptique("web2", "syno2").getAvecLabel());
		
		
	}
	
	/**
	 * Test de demandes des propriétés des équipements web
	 */
	@Test
	public void testDemandeProprietesEquipementsWeb() {
		List<MessageProprietesEquipementWebMqttRest> props = new InterrogationServeurHttpRest()
				.requeteDemandeProprietesEquipementsWeb(HOST, PORT, "ab", "cd");

		assertEquals("Taille incorrect", 2, props.size());
		
		assertEquals("Id équipement 1 incorrect", "1111", props.get(0).getIdEquipement());
		assertEquals("Id équipement 2 incorrect", "2222", props.get(1).getIdEquipement());
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
	
	@Override
	public MessageProprietesEquipementWebMqttRest traiteDemandeProprietesEquipementWeb(String pIdentifiantExpediteur,
			String pReferenceCommande, String pIdEquipement) {
		assertEquals("Id expediteur incorrect", "ab", pIdentifiantExpediteur);
		assertEquals("Id commande incorrect", "cd", pReferenceCommande);
		assertEquals("Id équipement incorrect", "1111", pIdEquipement);
		
		Map<String, String> nomsWeb = new HashMap<>();
		nomsWeb.put("web1", "nom1");
		nomsWeb.put("web2", "nom2");
		
		Map<String, List<PositionSurSynoptique>> positionsSynoptique = new HashMap<>();
		positionsSynoptique.put("web1", List.of(new PositionSurSynoptique("syno1", 10,  20, true, FORMAT.ICONE), new PositionSurSynoptique("syno2", 30,  40, false, FORMAT.REEL)));
		positionsSynoptique.put("web2", List.of(new PositionSurSynoptique("syno1", 100,  200, false, FORMAT.REEL), new PositionSurSynoptique("syno2", 300,  400, true, FORMAT.ICONE)));
		
		MessageProprietesEquipementWebMqttRest retour = new MessageProprietesEquipementWebMqttRest(pIdEquipement, nomsWeb,
				positionsSynoptique);
		
		return retour;
	}
	
	@Override
	public List<MessageProprietesEquipementWebMqttRest> traiteDemandeProprietesEquipementsWeb(String pIdentifiantExpediteur,
			String pReferenceCommande) {
		assertEquals("Id expediteur incorrect", "ab", pIdentifiantExpediteur);
		assertEquals("Id commande incorrect", "cd", pReferenceCommande);
		
		List<MessageProprietesEquipementWebMqttRest> retour = new ArrayList<>();
		
		Map<String, String> nomsWeb = new HashMap<>();
		nomsWeb.put("web1", "nom1");
		nomsWeb.put("web2", "nom2");
		
		
		Map<String, List<PositionSurSynoptique>> positionsSynoptique = new HashMap<>();
		positionsSynoptique.put("web1", List.of(new PositionSurSynoptique("syno1", 10,  20, true, FORMAT.ICONE), new PositionSurSynoptique("syno2", 30,  40, false, FORMAT.REEL)));
		positionsSynoptique.put("web2", List.of(new PositionSurSynoptique("syno1", 100,  200, false, FORMAT.REEL), new PositionSurSynoptique("syno2", 300,  400, true, FORMAT.ICONE)));
		
		retour.add(new MessageProprietesEquipementWebMqttRest("1111", nomsWeb,
				positionsSynoptique));
		
		nomsWeb = new HashMap<>();
		nomsWeb.put("web1", "nom3");
		nomsWeb.put("web2", "nom4");
		
		
		positionsSynoptique = new HashMap<>();
		positionsSynoptique.put("web1", List.of(new PositionSurSynoptique("syno1", 1,  2, true, FORMAT.REEL), new PositionSurSynoptique("syno2", 3,  4, false, FORMAT.ICONE)));
		positionsSynoptique.put("web2", List.of(new PositionSurSynoptique("syno1", 5,  6, false, FORMAT.ICONE), new PositionSurSynoptique("syno2", 7,  8, true, FORMAT.REEL)));
		
		retour.add(new MessageProprietesEquipementWebMqttRest("2222", nomsWeb,
				positionsSynoptique));
		
		return retour;
	}
}
