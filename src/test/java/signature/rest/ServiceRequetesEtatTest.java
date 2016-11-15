package signature.rest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import signature.mqttRest.objetsPartages.etatEtPilotage.DefautAfficheurModulePositionnableMqttRest.Couleur;
import signature.mqttRest.objetsPartages.etatEtPilotage.DefautAfficheurModulePositionnableMqttRest.TypeDefautAfficheur;
import signature.mqttRest.objetsPartages.etatEtPilotage.DefautAfficheurModulePositionnableMqttRest.TypeModuleDefaut;
import signature.mqttRest.objetsPartages.etatEtPilotage.IMessageAffichageEquipement.TypeEquipement;
import signature.mqttRest.objetsPartages.etatEtPilotage.DefautAfficheurModulePositionnableMqttRest;
import signature.mqttRest.objetsPartages.etatEtPilotage.MessageAlarmeMqttRest;
import signature.mqttRest.objetsPartages.etatEtPilotage.MessageAlarmeMqttRest.Gravite;
import signature.mqttRest.objetsPartages.etatEtPilotage.MessageAlarmeMqttRest.Type;
import signature.mqttRest.objetsPartages.etatEtPilotage.MessageBarriereMqttRest;
import signature.mqttRest.objetsPartages.etatEtPilotage.MessageBraMqttRest;
import signature.mqttRest.objetsPartages.etatEtPilotage.MessageEquipementModuleUniqueMqttRest;
import signature.mqttRest.objetsPartages.etatEtPilotage.MessageEtatAffichageMqttRest;
import signature.mqttRest.objetsPartages.etatEtPilotage.MessageEtatTechniqueMqttRest;
import signature.mqttRest.objetsPartages.etatEtPilotage.MessageModuleMqttRest;
import signature.mqttRest.objetsPartages.etatEtPilotage.MessageModuleMqttRest.Luminosite;
import signature.mqttRest.objetsPartages.etatEtPilotage.MessageModuleMqttRest.Mode;
import signature.mqttRest.objetsPartages.etatEtPilotage.MessagePictogrammeMqttRest;
import signature.mqttRest.objetsPartages.etatEtPilotage.MessagePmvMqttRest;
import signature.mqttRest.objetsPartages.etatEtPilotage.MessagePpadMqttRest;
import signature.mqttRest.objetsPartages.etatEtPilotage.MessagePplmvMqttRest;
import signature.mqttRest.objetsPartages.etatEtPilotage.MessagePrismeMqttRest;
import signature.mqttRest.objetsPartages.etatEtPilotage.MessageR24MqttRest;
import signature.mqttRest.services.rest.client.InterrogationServeurHttpRest;
import signature.mqttRest.services.rest.serveur.ServeurHttpRest;
import signature.mqttRest.services.rest.serveur.TraitementRequetesRestAdapteur;

/**
 * Test des requêtes d'état d'affichage et état technique
 * 
 * @author SDARIZCUREN
 *
 */
public class ServiceRequetesEtatTest {

	private static ServeurHttpRest serveurHttpRest;
	private static TraitementRequetesEtatEtPilotage traitementsRequetesRest;

	private final static String HOST = "localhost";
	private final static int PORT = 1122;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		traitementsRequetesRest = new TraitementRequetesEtatEtPilotage();

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
	 * Test demande d'état d'affichage d'un PMV
	 */
	@Test
	public void testRequeteDemandeEtatAffichagePMV() {
		MessageEtatAffichageMqttRest msg = InterrogationServeurHttpRest.requeteDemandeEtatAffichageEquipement(HOST,
				PORT, "ab", "cd", "1111");

		assertEquals("Id expéditeur incorrect", msg.getIdentifiantExpediteur(), "ab");
		assertEquals("Id commande incorrect", msg.getReferenceMessage(), "cd");
		assertEquals("Identifiant incorrect", msg.getIdentifiantEquipement(), "1111");

		LocalDateTime ldt = LocalDateTime.of(2016, Month.NOVEMBER, 8, 16, 50, 10);
		Instant ist = ldt.toInstant(ZoneOffset.UTC);
		assertEquals("Horodate génération incorrect", msg.getHorodateGeneration(), Date.from(ist));

		ldt = LocalDateTime.of(2016, Month.NOVEMBER, 8, 16, 55, 20);
		ist = ldt.toInstant(ZoneOffset.UTC);
		assertEquals("Horodate FIN incorrect", msg.getHorodateFin(), Date.from(ist));

		assertEquals("Type PMV incorrect", msg.getMessageEquipement().getTypeEquipement(), TypeEquipement.PMV);
		MessagePmvMqttRest msgPmv = (MessagePmvMqttRest) msg.getMessageEquipement();

		// Test des lignes
		// Ligne 1
		assertEquals("Nombre lignes incorrect", 2, msgPmv.getMessagesLignes().size());
		assertEquals("DV ligne 1 incorrect", 11, msgPmv.getMessagesLignes().get(0).getDureeValidite());
		assertEquals("DV restante ligne 1 incorrect", 1, msgPmv.getMessagesLignes().get(0).getDureeValiditeRestante());
		assertEquals("Labels ligne 1 incorrect", Arrays.asList("label10", "label11"),
				msgPmv.getMessagesLignes().get(0).getLabelsParPage());
		assertEquals("Luminosité ligne 1 incorrecte", Luminosite.JOUR,
				msgPmv.getMessagesLignes().get(0).getLuminosite());
		assertEquals("Messages ligne 1 incorrecte", Arrays.asList("msg10", "msg11"),
				msgPmv.getMessagesLignes().get(0).getMessagesParPage());
		assertEquals("Mode affichage ligne 1 incorrect", Mode.ALTERNAT,
				msgPmv.getMessagesLignes().get(0).getModeAffichage());
		assertEquals("Temps affichage ligne 1 incorrect", Arrays.asList(101, 102),
				msgPmv.getMessagesLignes().get(0).getTempsAllumage());
		assertEquals("Temps extinction ligne 1 incorrect", Arrays.asList(103, 104),
				msgPmv.getMessagesLignes().get(0).getTempsExtinction());
		// Ligne 2
		assertEquals("DV ligne 2 incorrect", 22, msgPmv.getMessagesLignes().get(1).getDureeValidite());
		assertEquals("DV restante ligne 2 incorrect", 2, msgPmv.getMessagesLignes().get(1).getDureeValiditeRestante());
		assertEquals("Labels ligne 2 incorrect", Arrays.asList("label20"),
				msgPmv.getMessagesLignes().get(1).getLabelsParPage());
		assertEquals("Luminosité ligne 2 incorrecte", Luminosite.NUIT,
				msgPmv.getMessagesLignes().get(1).getLuminosite());
		assertEquals("Messages ligne 2 incorrecte", Arrays.asList("msg20"),
				msgPmv.getMessagesLignes().get(1).getMessagesParPage());
		assertEquals("Mode affichage ligne 2 incorrect", Mode.CLIGNOTANT,
				msgPmv.getMessagesLignes().get(1).getModeAffichage());
		assertEquals("Temps affichage ligne 2 incorrect", Arrays.asList(201),
				msgPmv.getMessagesLignes().get(1).getTempsAllumage());
		assertEquals("Temps extinction ligne 2 incorrect", Arrays.asList(202),
				msgPmv.getMessagesLignes().get(1).getTempsExtinction());

		// Test du panonceau
		assertEquals("Nombre panonceaux incorrect", 1, msgPmv.getMessagesPanonceaux().size());
		assertEquals("DV panonceaux incorrect", 33, msgPmv.getMessagesPanonceaux().get(0).getDureeValidite());
		assertEquals("DV restante panonceaux incorrect", 3,
				msgPmv.getMessagesPanonceaux().get(0).getDureeValiditeRestante());
		assertEquals("Labels panonceaux incorrect", Arrays.asList("label30"),
				msgPmv.getMessagesPanonceaux().get(0).getLabelsParPage());
		assertEquals("Luminosité panonceaux incorrecte", Luminosite.SURBRILLANCE,
				msgPmv.getMessagesPanonceaux().get(0).getLuminosite());
		assertEquals("Messages panonceaux incorrecte", Arrays.asList("msg30"),
				msgPmv.getMessagesPanonceaux().get(0).getMessagesParPage());
		assertEquals("Mode affichage panonceaux incorrect", Mode.FIXE,
				msgPmv.getMessagesPanonceaux().get(0).getModeAffichage());

		// Test du pictogramme
		assertEquals("Nombre pictogrammes incorrect", 1, msgPmv.getMessagesPictogrammes().size());
		assertEquals("DV pictogrammes incorrect", 44, msgPmv.getMessagesPictogrammes().get(0).getDureeValidite());
		assertEquals("DV restante pictogrammes incorrect", 4,
				msgPmv.getMessagesPictogrammes().get(0).getDureeValiditeRestante());
		assertEquals("Labels pictogrammes incorrect", Arrays.asList("label40"),
				msgPmv.getMessagesPictogrammes().get(0).getLabelsParPage());
		assertEquals("Luminosité pictogrammes incorrecte", Luminosite.AUTOMATIQUE,
				msgPmv.getMessagesPictogrammes().get(0).getLuminosite());
		assertEquals("Messages pictogrammes incorrecte", Arrays.asList("msg40"),
				msgPmv.getMessagesPictogrammes().get(0).getMessagesParPage());
		assertEquals("Mode affichage pictogrammes incorrect", Mode.CLIGNOTANT,
				msgPmv.getMessagesPictogrammes().get(0).getModeAffichage());
		assertEquals("Temps affichage pictogrammes incorrect", Arrays.asList(401),
				msgPmv.getMessagesPictogrammes().get(0).getTempsAllumage());
		assertEquals("Temps extinction pictogrammes incorrect", Arrays.asList(402),
				msgPmv.getMessagesPictogrammes().get(0).getTempsExtinction());

		// Test des flashs
		assertEquals("DV flashs incorrect", 55, msgPmv.getMessagesFlashs().getDureeValidite());
		assertEquals("DV restante flashs incorrect", 5, msgPmv.getMessagesFlashs().getDureeValiditeRestante());
		assertEquals("Labels flashs incorrect", Arrays.asList("label50"),
				msgPmv.getMessagesFlashs().getLabelsParPage());
		assertEquals("Luminosité flashs incorrecte", Luminosite.JOUR, msgPmv.getMessagesFlashs().getLuminosite());
		assertEquals("Messages flashs incorrecte", Arrays.asList("msg50"),
				msgPmv.getMessagesFlashs().getMessagesParPage());
		assertEquals("Mode affichage flashs incorrect", Mode.CLIGNOTANT, msgPmv.getMessagesFlashs().getModeAffichage());
		assertEquals("Temps affichage flashs incorrect", Arrays.asList(501),
				msgPmv.getMessagesFlashs().getTempsAllumage());
		assertEquals("Temps extinction flashs incorrect", Arrays.asList(502),
				msgPmv.getMessagesFlashs().getTempsExtinction());
	}

	/**
	 * Test demande d'état d'affichage d'un panneau module unique
	 */
	@Test
	public void testRequeteDemandeEtatAffichagePanneauModuleUnique() {
		MessageEtatAffichageMqttRest msg = InterrogationServeurHttpRest.requeteDemandeEtatAffichageEquipement(HOST,
				PORT, "ab", "cd", "2222");

		assertEquals("Id expéditeur incorrect", msg.getIdentifiantExpediteur(), "ab");
		assertEquals("Id commande incorrect", msg.getReferenceMessage(), "cd");
		assertEquals("Identifiant incorrect", msg.getIdentifiantEquipement(), "2222");

		LocalDateTime ldt = LocalDateTime.of(2016, Month.NOVEMBER, 8, 16, 50, 10);
		Instant ist = ldt.toInstant(ZoneOffset.UTC);
		assertEquals("Horodate génération incorrect", msg.getHorodateGeneration(), Date.from(ist));

		ldt = LocalDateTime.of(2016, Month.NOVEMBER, 8, 16, 55, 20);
		ist = ldt.toInstant(ZoneOffset.UTC);
		assertEquals("Horodate FIN incorrect", msg.getHorodateFin(), Date.from(ist));

		MessageEquipementModuleUniqueMqttRest msgPan = (MessageEquipementModuleUniqueMqttRest) msg
				.getMessageEquipement();

		// Test des flashs
		assertEquals("DV panneau incorrect", 11, msgPan.getMessagesModuleUnique().getDureeValidite());
		assertEquals("DV restante flashs incorrect", 1, msgPan.getMessagesModuleUnique().getDureeValiditeRestante());
		assertEquals("Labels flashs incorrect", Arrays.asList("label10", "label11"),
				msgPan.getMessagesModuleUnique().getLabelsParPage());
		assertEquals("Luminosité flashs incorrecte", Luminosite.JOUR, msgPan.getMessagesModuleUnique().getLuminosite());
		assertEquals("Messages flashs incorrecte", Arrays.asList("msg10", "msg11"),
				msgPan.getMessagesModuleUnique().getMessagesParPage());
		assertEquals("Mode affichage flashs incorrect", Mode.ALTERNAT,
				msgPan.getMessagesModuleUnique().getModeAffichage());
		assertEquals("Temps affichage flashs incorrect", Arrays.asList(101, 102),
				msgPan.getMessagesModuleUnique().getTempsAllumage());
		assertEquals("Temps extinction flashs incorrect", Arrays.asList(103, 104),
				msgPan.getMessagesModuleUnique().getTempsExtinction());
	}

	/**
	 * Test demande d'état d'affichage d'un PPAD
	 */
	@Test
	public void testRequeteDemandeEtatAffichagePpad() {
		MessageEtatAffichageMqttRest msg = InterrogationServeurHttpRest.requeteDemandeEtatAffichageEquipement(HOST,
				PORT, "ab", "cd", "2222");

		assertEquals("Type PPAD incorrect", msg.getMessageEquipement().getTypeEquipement(), TypeEquipement.PPAD);
		assertEquals("Classe incorrecte", MessagePpadMqttRest.class, msg.getMessageEquipement().getClass());
	}

	/**
	 * Test demande d'état d'affichage d'un pictogramme
	 */
	@Test
	public void testRequeteDemandeEtatAffichagePictogramme() {
		MessageEtatAffichageMqttRest msg = InterrogationServeurHttpRest.requeteDemandeEtatAffichageEquipement(HOST,
				PORT, "ab", "cd", "3333");

		assertEquals("Type Pictogramme incorrect", msg.getMessageEquipement().getTypeEquipement(),
				TypeEquipement.PICTOGRAMME);
		assertEquals("Classe incorrecte", MessagePictogrammeMqttRest.class, msg.getMessageEquipement().getClass());
	}

	/**
	 * Test demande d'état d'affichage d'un Feu R24
	 */
	@Test
	public void testRequeteDemandeEtatAffichageFeuR24() {
		MessageEtatAffichageMqttRest msg = InterrogationServeurHttpRest.requeteDemandeEtatAffichageEquipement(HOST,
				PORT, "ab", "cd", "4444");

		assertEquals("Type PPAD incorrect", msg.getMessageEquipement().getTypeEquipement(), TypeEquipement.R24);
		assertEquals("Classe incorrecte", MessageR24MqttRest.class, msg.getMessageEquipement().getClass());
	}

	/**
	 * Test demande d'état d'affichage d'une barrière
	 */
	@Test
	public void testRequeteDemandeEtatAffichageBarriere() {
		MessageEtatAffichageMqttRest msg = InterrogationServeurHttpRest.requeteDemandeEtatAffichageEquipement(HOST,
				PORT, "ab", "cd", "5555");

		assertEquals("Type Barrière incorrect", msg.getMessageEquipement().getTypeEquipement(),
				TypeEquipement.BARRIERE);
		assertEquals("Classe incorrecte", MessageBarriereMqttRest.class, msg.getMessageEquipement().getClass());
	}

	/**
	 * Test demande d'état d'affichage d'un prisme
	 */
	@Test
	public void testRequeteDemandeEtatAffichagePrisme() {
		MessageEtatAffichageMqttRest msg = InterrogationServeurHttpRest.requeteDemandeEtatAffichageEquipement(HOST,
				PORT, "ab", "cd", "6666");

		assertEquals("Type Prisme incorrect", msg.getMessageEquipement().getTypeEquipement(), TypeEquipement.PRISME);
		assertEquals("Classe incorrecte", MessagePrismeMqttRest.class, msg.getMessageEquipement().getClass());
	}

	/**
	 * Test demande d'état d'affichage d'un BRA
	 */
	@Test
	public void testRequeteDemandeEtatAffichageBra() {
		MessageEtatAffichageMqttRest msg = InterrogationServeurHttpRest.requeteDemandeEtatAffichageEquipement(HOST,
				PORT, "ab", "cd", "7777");

		assertEquals("Type BRA incorrect", msg.getMessageEquipement().getTypeEquipement(), TypeEquipement.BRA);
		assertEquals("Classe incorrecte", MessageBraMqttRest.class, msg.getMessageEquipement().getClass());
	}

	/**
	 * Test demande d'état d'affichage d'un PPLMV
	 */
	@Test
	public void testRequeteDemandeEtatAffichagePplmv() {
		MessageEtatAffichageMqttRest msg = InterrogationServeurHttpRest.requeteDemandeEtatAffichageEquipement(HOST,
				PORT, "ab", "cd", "8888");

		assertEquals("Type PPLMV incorrect", msg.getMessageEquipement().getTypeEquipement(), TypeEquipement.PPLMV);
		assertEquals("Classe incorrecte", MessagePplmvMqttRest.class, msg.getMessageEquipement().getClass());
	}

	/**
	 * Test etat affichage deux équipements
	 */
	@Test
	public void testRequeteDemandeEtatAffichageEquipements() {
		List<MessageEtatAffichageMqttRest> msgs = InterrogationServeurHttpRest
				.requeteDemandeEtatAffichageEquipements(HOST, PORT, "ab", "cd");

		assertEquals("Nombre état affichage incorrect", 2, msgs.size());
		assertEquals("Type PMV incorrect", msgs.get(0).getMessageEquipement().getTypeEquipement(), TypeEquipement.PMV);
		assertEquals("Type PPAD incorrect", msgs.get(1).getMessageEquipement().getTypeEquipement(),
				TypeEquipement.PPAD);
	}

	/**
	 * Test état technique, avec trois alarmes présentes
	 */
	@Test
	public void testRequeteDemandeEtatTechniqueEquipement() {
		MessageEtatTechniqueMqttRest etatTech = InterrogationServeurHttpRest.requeteDemandeEtatTechniqueEquipement(HOST,
				PORT, "ab", "cd", "1111");

		assertEquals("Id equipement incorrect", "1111", etatTech.getIdentifiantEquipement());
		assertEquals("Id expediteur incorrect", "ab", etatTech.getIdentifiantExpediteur());
		assertEquals("Id commande incorrect", "cd", etatTech.getReferenceMessage());

		List<MessageAlarmeMqttRest> alarmes = etatTech.getAlarmes();
		assertEquals("Nombre d'alarmes incorrect", 2, alarmes.size());

		assertEquals("Gravite incorrect", Gravite.INTERMEDIAIRE, alarmes.get(0).getGravite());
		LocalDateTime ldt = LocalDateTime.of(2016, Month.NOVEMBER, 8, 16, 50, 10);
		Instant ist = ldt.toInstant(ZoneOffset.UTC);
		assertEquals("Horodate génération incorrect", alarmes.get(0).getHorodateGeneration(), Date.from(ist));
		assertNull("Horodate fin non null", alarmes.get(0).getHorodateFin());
		assertEquals("Libellé incorrect", "Alarme 1", alarmes.get(0).getLibelle());
		assertEquals("Type incorrect", Type.DEFAUT_24V, alarmes.get(0).getType());

		assertEquals("Gravite incorrect", Gravite.MAJEURE, alarmes.get(1).getGravite());
		ldt = LocalDateTime.of(2016, Month.NOVEMBER, 8, 16, 55, 20);
		ist = ldt.toInstant(ZoneOffset.UTC);
		assertEquals("Horodate génération incorrect", alarmes.get(1).getHorodateGeneration(), Date.from(ist));
		ldt = LocalDateTime.of(2016, Month.NOVEMBER, 8, 16, 56, 30);
		ist = ldt.toInstant(ZoneOffset.UTC);
		assertEquals("Horodate fin incorrect", alarmes.get(1).getHorodateFin(), Date.from(ist));
		assertEquals("Libellé incorrect", "Alarme 2", alarmes.get(1).getLibelle());
		assertEquals("Type incorrect", Type.DEFAUT_CARTE, alarmes.get(1).getType());

		// Défauts carte
		List<DefautAfficheurModulePositionnableMqttRest> defauts = alarmes.get(1)
				.getListeDefautsAfficheurModulePositionnable();
		assertEquals("Nombre de défauts incorrect", 2, defauts.size());

		assertEquals("Adresse défaut incorrect", "1.1", defauts.get(0).getAdresseDefaut());
		assertEquals("Adresse défaut dans module incorrect", "12", defauts.get(0).getAdresseDefautDansModule());
		assertEquals("Couleur incorrect", Couleur.BLEUE, defauts.get(0).getCouleurDefaut());
		assertEquals("Position x incorrect", 5, defauts.get(0).getPositionX());
		assertEquals("Position y incorrect", 10, defauts.get(0).getPositionY());
		assertEquals("Type défaut incorrect", TypeDefautAfficheur.DEFAUT_CARTE, defauts.get(0).getTypeDefaut());
		assertEquals("Module incorrect", TypeModuleDefaut.LIGNE5, defauts.get(0).getTypeModuleDefaut());

		assertEquals("Adresse défaut incorrect", "5.1", defauts.get(1).getAdresseDefaut());
		assertEquals("Adresse défaut dans module incorrect", "8", defauts.get(1).getAdresseDefautDansModule());
		assertEquals("Couleur incorrect", Couleur.VERT, defauts.get(1).getCouleurDefaut());
		assertEquals("Position x incorrect", 20, defauts.get(1).getPositionX());
		assertEquals("Position y incorrect", 100, defauts.get(1).getPositionY());
		assertEquals("Type défaut incorrect", TypeDefautAfficheur.DEFAUT_PIXEL, defauts.get(1).getTypeDefaut());
		assertEquals("Module incorrect", TypeModuleDefaut.PICTOGRAMME2, defauts.get(1).getTypeModuleDefaut());
	}

	/**
	 * Test etat technique pour deux équipements
	 */
	@Test
	public void testRequeteDemandeEtatTechniqueEquipements() {
		List<MessageEtatTechniqueMqttRest> msgs = InterrogationServeurHttpRest
				.requeteDemandeEtatTechniqueEquipements(HOST, PORT, "ab", "cd");

		assertEquals("Nombre état technique incorrect", 2, msgs.size());

	}

	/**
	 * Test actualisation affichage d'un panneau
	 */
	@Test
	public void testRequeteActualisationEtatEquipement() {
		InterrogationServeurHttpRest.requeteActualisationEtatEquipement(HOST, PORT, "ab", "cd", "1234");

		assertEquals("Id equipement incorrect", "1234", traitementsRequetesRest.idEqpt);
	}
}

class TraitementRequetesEtatEtPilotage extends TraitementRequetesRestAdapteur {
	protected String idEqpt = "";

	@Override
	public MessageEtatAffichageMqttRest demandeEtatAffichageEquipement(String pIdentifiantExpediteur,
			String pReferenceCommande, String pId) {
		MessageEtatAffichageMqttRest retour = new MessageEtatAffichageMqttRest(pId, pIdentifiantExpediteur,
				pReferenceCommande);

		LocalDateTime ldt = LocalDateTime.of(2016, Month.NOVEMBER, 8, 16, 50, 10);
		Instant ist = ldt.toInstant(ZoneOffset.UTC);
		retour.setHorodateGeneration(Date.from(ist));

		ldt = LocalDateTime.of(2016, Month.NOVEMBER, 8, 16, 55, 20);
		ist = ldt.toInstant(ZoneOffset.UTC);
		retour.setHorodateFin(Date.from(ist));

		// Cas demande état PMV
		if (pId.equals("1111")) {
			retour.setMessageEquipement(getEtatAffichagePmv());
		}
		// Cas demande PPAD
		else if (pId.equals("2222")) {
			retour.setMessageEquipement(getEtatAffichagePpad());
		}
		// Cas pictogramme
		else if (pId.equals("3333")) {
			retour.setMessageEquipement(getEtatAffichagePictogramme());
		}
		// Cas feu R24
		else if (pId.equals("4444")) {
			retour.setMessageEquipement(getEtatAffichageFeuR24());
		}
		// Cas Barrière
		else if (pId.equals("5555")) {
			retour.setMessageEquipement(getEtatAffichageBarriere());
		}
		// Cas Prisme
		else if (pId.equals("6666")) {
			retour.setMessageEquipement(getEtatAffichagePrisme());
		}
		// Cas BRA
		else if (pId.equals("7777")) {
			retour.setMessageEquipement(getEtatAffichageBra());
		}
		// Cas PPLMV
		else if (pId.equals("8888")) {
			retour.setMessageEquipement(getEtatAffichagePplmv());
		}

		return retour;
	}

	@Override
	public List<MessageEtatAffichageMqttRest> demandeEtatAffichageEquipements(String pIdentifiantExpediteur,
			String pReferenceCommande) {
		// Deux états affichage : PMV + PPAD
		List<MessageEtatAffichageMqttRest> retour = new ArrayList<>();

		MessageEtatAffichageMqttRest msg = new MessageEtatAffichageMqttRest("1111", pIdentifiantExpediteur,
				pReferenceCommande);
		msg.setMessageEquipement(getEtatAffichagePmv());
		retour.add(msg);

		msg = new MessageEtatAffichageMqttRest("2222", pIdentifiantExpediteur, pReferenceCommande);
		msg.setMessageEquipement(getEtatAffichagePpad());
		retour.add(msg);

		return retour;
	}

	@Override
	public MessageEtatTechniqueMqttRest demandeEtatTechniqueEquipement(String pIdentifiantExpediteur,
			String pReferenceCommande, String pId) {
		MessageEtatTechniqueMqttRest retour = new MessageEtatTechniqueMqttRest(pId, pIdentifiantExpediteur,
				pReferenceCommande);

		// Ajout 2 alarmes
		List<MessageAlarmeMqttRest> alarmes = new ArrayList<>();
		retour.setAlarmes(alarmes);

		MessageAlarmeMqttRest al = new MessageAlarmeMqttRest();
		alarmes.add(al);
		al.setGravite(Gravite.INTERMEDIAIRE);
		LocalDateTime ldt = LocalDateTime.of(2016, Month.NOVEMBER, 8, 16, 50, 10);
		Instant ist = ldt.toInstant(ZoneOffset.UTC);
		al.setHorodateGeneration(Date.from(ist));
		al.setLibelle("Alarme 1");
		al.setType(Type.DEFAUT_24V);

		al = new MessageAlarmeMqttRest();
		alarmes.add(al);
		al.setGravite(Gravite.MAJEURE);
		ldt = LocalDateTime.of(2016, Month.NOVEMBER, 8, 16, 55, 20);
		ist = ldt.toInstant(ZoneOffset.UTC);
		al.setHorodateGeneration(Date.from(ist));
		ldt = LocalDateTime.of(2016, Month.NOVEMBER, 8, 16, 56, 30);
		ist = ldt.toInstant(ZoneOffset.UTC);
		al.setHorodateFin(Date.from(ist));
		al.setLibelle("Alarme 2");
		al.setType(Type.DEFAUT_CARTE);

		List<DefautAfficheurModulePositionnableMqttRest> defauts = new ArrayList<>();
		al.setListeDefautsAfficheurModulePositionnable(defauts);

		DefautAfficheurModulePositionnableMqttRest def = new DefautAfficheurModulePositionnableMqttRest();
		defauts.add(def);
		def.setAdresseDefaut("1.1");
		def.setAdresseDefautDansModule("12");
		def.setCouleurDefaut(Couleur.BLEUE);
		def.setPositionX(5);
		def.setPositionY(10);
		def.setTypeDefaut(TypeDefautAfficheur.DEFAUT_CARTE);
		def.setTypeModuleDefaut(TypeModuleDefaut.LIGNE5);

		def = new DefautAfficheurModulePositionnableMqttRest();
		defauts.add(def);
		def.setAdresseDefaut("5.1");
		def.setAdresseDefautDansModule("8");
		def.setCouleurDefaut(Couleur.VERT);
		def.setPositionX(20);
		def.setPositionY(100);
		def.setTypeDefaut(TypeDefautAfficheur.DEFAUT_PIXEL);
		def.setTypeModuleDefaut(TypeModuleDefaut.PICTOGRAMME2);

		return retour;
	}

	@Override
	public List<MessageEtatTechniqueMqttRest> demandeEtatTechniqueEquipements(String pIdentifiantExpediteur,
			String pReferenceCommande) {
		List<MessageEtatTechniqueMqttRest> retour = new ArrayList<>();

		MessageEtatTechniqueMqttRest msg = new MessageEtatTechniqueMqttRest("1111", pIdentifiantExpediteur,
				pReferenceCommande);
		retour.add(msg);
		msg = new MessageEtatTechniqueMqttRest("2222", pIdentifiantExpediteur, pReferenceCommande);
		retour.add(msg);

		return retour;
	}

	@Override
	public void demandeActualisationEtatEquipement(String pIdentifiantExpediteur, String pReferenceCommande,
			String pId) {
		idEqpt = pId;
	}

	private MessagePmvMqttRest getEtatAffichagePmv() {
		MessagePmvMqttRest retour = new MessagePmvMqttRest();

		// 2 lignes + panonceau + picto + flashs

		List<MessageModuleMqttRest> lignes = new ArrayList<>();
		retour.setMessagesLignes(lignes);

		MessageModuleMqttRest l1 = new MessageModuleMqttRest();
		l1.setDureeValidite(11);
		l1.setDureeValiditeRestante(1);
		l1.setLabelsParPage(Arrays.asList("label10", "label11"));
		l1.setLuminosite(Luminosite.JOUR);
		l1.setMessagesParPage(Arrays.asList("msg10", "msg11"));
		l1.setModeAffichage(Mode.ALTERNAT);
		l1.setTempsAllumage(Arrays.asList(101, 102));
		l1.setTempsExtinction(Arrays.asList(103, 104));
		lignes.add(l1);

		MessageModuleMqttRest l2 = new MessageModuleMqttRest();
		l2.setDureeValidite(22);
		l2.setDureeValiditeRestante(2);
		l2.setLabelsParPage(Arrays.asList("label20"));
		l2.setLuminosite(Luminosite.NUIT);
		l2.setMessagesParPage(Arrays.asList("msg20"));
		l2.setModeAffichage(Mode.CLIGNOTANT);
		l2.setTempsAllumage(Arrays.asList(201));
		l2.setTempsExtinction(Arrays.asList(202));
		lignes.add(l2);

		List<MessageModuleMqttRest> panonceaux = new ArrayList<>();
		retour.setMessagesPanonceaux(panonceaux);

		MessageModuleMqttRest pan1 = new MessageModuleMqttRest();
		pan1.setDureeValidite(33);
		pan1.setDureeValiditeRestante(3);
		pan1.setLabelsParPage(Arrays.asList("label30"));
		pan1.setLuminosite(Luminosite.SURBRILLANCE);
		pan1.setMessagesParPage(Arrays.asList("msg30"));
		pan1.setModeAffichage(Mode.FIXE);
		panonceaux.add(pan1);

		List<MessageModuleMqttRest> pictos = new ArrayList<>();
		retour.setMessagesPictogrammes(pictos);

		MessageModuleMqttRest pic1 = new MessageModuleMqttRest();
		pic1.setDureeValidite(44);
		pic1.setDureeValiditeRestante(4);
		pic1.setLabelsParPage(Arrays.asList("label40"));
		pic1.setLuminosite(Luminosite.AUTOMATIQUE);
		pic1.setMessagesParPage(Arrays.asList("msg40"));
		pic1.setModeAffichage(Mode.CLIGNOTANT);
		pic1.setTempsAllumage(Arrays.asList(401));
		pic1.setTempsExtinction(Arrays.asList(402));
		pictos.add(pic1);

		MessageModuleMqttRest fl = new MessageModuleMqttRest();
		fl.setDureeValidite(55);
		fl.setDureeValiditeRestante(5);
		fl.setLabelsParPage(Arrays.asList("label50"));
		fl.setLuminosite(Luminosite.JOUR);
		fl.setMessagesParPage(Arrays.asList("msg50"));
		fl.setModeAffichage(Mode.CLIGNOTANT);
		fl.setTempsAllumage(Arrays.asList(501));
		fl.setTempsExtinction(Arrays.asList(502));
		retour.setMessagesFlashs(fl);

		return retour;
	}

	private MessagePpadMqttRest getEtatAffichagePpad() {
		MessagePpadMqttRest retour = new MessagePpadMqttRest();

		MessageModuleMqttRest mod = new MessageModuleMqttRest();
		mod.setDureeValidite(11);
		mod.setDureeValiditeRestante(1);
		mod.setLabelsParPage(Arrays.asList("label10", "label11"));
		mod.setLuminosite(Luminosite.JOUR);
		mod.setMessagesParPage(Arrays.asList("msg10", "msg11"));
		mod.setModeAffichage(Mode.ALTERNAT);
		mod.setTempsAllumage(Arrays.asList(101, 102));
		mod.setTempsExtinction(Arrays.asList(103, 104));
		retour.setMessagesModuleUnique(mod);

		return retour;
	}

	private MessagePictogrammeMqttRest getEtatAffichagePictogramme() {
		MessagePictogrammeMqttRest retour = new MessagePictogrammeMqttRest();
		return retour;
	}

	private MessageR24MqttRest getEtatAffichageFeuR24() {
		MessageR24MqttRest retour = new MessageR24MqttRest();
		return retour;
	}

	private MessageBarriereMqttRest getEtatAffichageBarriere() {
		MessageBarriereMqttRest retour = new MessageBarriereMqttRest();
		return retour;
	}

	private MessagePrismeMqttRest getEtatAffichagePrisme() {
		MessagePrismeMqttRest retour = new MessagePrismeMqttRest();
		return retour;
	}

	private MessageBraMqttRest getEtatAffichageBra() {
		MessageBraMqttRest retour = new MessageBraMqttRest();
		return retour;
	}

	private MessagePplmvMqttRest getEtatAffichagePplmv() {
		MessagePplmvMqttRest retour = new MessagePplmvMqttRest();
		return retour;
	}
}
