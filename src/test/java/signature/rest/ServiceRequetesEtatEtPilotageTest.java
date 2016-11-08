package signature.rest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

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

import signature.mqttRest.objetsPartages.etatEtPilotage.MessageEquipementModuleUniqueMqttRest;
import signature.mqttRest.objetsPartages.etatEtPilotage.MessageEtatAffichageMqttRest;
import signature.mqttRest.objetsPartages.etatEtPilotage.MessageModuleMqttRest;
import signature.mqttRest.objetsPartages.etatEtPilotage.MessageModuleMqttRest.Luminosite;
import signature.mqttRest.objetsPartages.etatEtPilotage.MessageModuleMqttRest.Mode;
import signature.mqttRest.objetsPartages.etatEtPilotage.MessagePmvMqttRest;
import signature.mqttRest.objetsPartages.etatEtPilotage.MessagePpadMqttRest;
import signature.mqttRest.services.rest.client.InterrogationServeurHttpRest;
import signature.mqttRest.services.rest.serveur.ServeurHttpRest;
import signature.mqttRest.services.rest.serveur.TraitementRequetesRestAdapteur;

public class ServiceRequetesEtatEtPilotageTest {

	private static ServeurHttpRest serveurHttpRest;
	private static TraitementRequetesEtatEtPilotage traitementsRequetesRest;

	private final static int PORT = 1122;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		traitementsRequetesRest = new TraitementRequetesEtatEtPilotage();

		// D�marrage du serveur HTTP REST
		serveurHttpRest = new ServeurHttpRest(PORT, traitementsRequetesRest);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		serveurHttpRest.arretServeur();
		Thread.sleep(100); // Attente arr�t du serveur avant de passer aux tests
							// suivants
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test demande d'�tat d'affichage d'un PMV
	 */
	@Test
	public void testRequeteDemandeEtatAffichagePMV() {
		MessageEtatAffichageMqttRest msg = InterrogationServeurHttpRest
				.requeteDemandeEtatAffichageEquipement("localhost", PORT, "1111", "ab", "cd");

		assertEquals("Id exp�diteur incorrect", msg.getIdentifiantExpediteur(), "ab");
		assertEquals("Id commande incorrect", msg.getReferenceMessage(), "cd");
		assertEquals("Identifiant incorrect", msg.getIdentifiantEquipement(), "1111");

		LocalDateTime ldt = LocalDateTime.of(2016, Month.NOVEMBER, 8, 16, 50, 10);
		Instant ist = ldt.toInstant(ZoneOffset.UTC);
		assertEquals("Horodate g�n�ration incorrect", msg.getHorodateGeneration(), Date.from(ist));

		ldt = LocalDateTime.of(2016, Month.NOVEMBER, 8, 16, 55, 20);
		ist = ldt.toInstant(ZoneOffset.UTC);
		assertEquals("Horodate FIN incorrect", msg.getHorodateFin(), Date.from(ist));

		MessagePmvMqttRest msgPmv = (MessagePmvMqttRest) msg.getMessageEquipement();

		// Test des lignes
		// Ligne 1
		assertEquals("Nombre lignes incorrect", 2, msgPmv.getMessagesLignes().size());
		assertEquals("DV ligne 1 incorrect", 11, msgPmv.getMessagesLignes().get(0).getDureeValidite());
		assertEquals("DV restante ligne 1 incorrect", 1, msgPmv.getMessagesLignes().get(0).getDureeValiditeRestante());
		assertEquals("Labels ligne 1 incorrect", Arrays.asList("label10", "label11"),
				msgPmv.getMessagesLignes().get(0).getLabelsParPage());
		assertEquals("Luminosit� ligne 1 incorrecte", Luminosite.JOUR,
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
		assertEquals("Luminosit� ligne 2 incorrecte", Luminosite.NUIT,
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
		assertEquals("Luminosit� panonceaux incorrecte", Luminosite.SURBRILLANCE,
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
		assertEquals("Luminosit� pictogrammes incorrecte", Luminosite.AUTOMATIQUE,
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
		assertEquals("DV restante flashs incorrect", 5,
				msgPmv.getMessagesFlashs().getDureeValiditeRestante());
		assertEquals("Labels flashs incorrect", Arrays.asList("label50"),
				msgPmv.getMessagesFlashs().getLabelsParPage());
		assertEquals("Luminosit� flashs incorrecte", Luminosite.JOUR,
				msgPmv.getMessagesFlashs().getLuminosite());
		assertEquals("Messages flashs incorrecte", Arrays.asList("msg50"),
				msgPmv.getMessagesFlashs().getMessagesParPage());
		assertEquals("Mode affichage flashs incorrect", Mode.CLIGNOTANT,
				msgPmv.getMessagesFlashs().getModeAffichage());
		assertEquals("Temps affichage flashs incorrect", Arrays.asList(501),
				msgPmv.getMessagesFlashs().getTempsAllumage());
		assertEquals("Temps extinction flashs incorrect", Arrays.asList(502),
				msgPmv.getMessagesFlashs().getTempsExtinction());
	}
	
	/**
	 * Test demande d'�tat d'affichage d'un panneau module unique
	 */
	@Test
	public void testRequeteDemandeEtatAffichagePanneauModuleUnique() {
		MessageEtatAffichageMqttRest msg = InterrogationServeurHttpRest
				.requeteDemandeEtatAffichageEquipement("localhost", PORT, "2222", "ab", "cd");

		assertEquals("Id exp�diteur incorrect", msg.getIdentifiantExpediteur(), "ab");
		assertEquals("Id commande incorrect", msg.getReferenceMessage(), "cd");
		assertEquals("Identifiant incorrect", msg.getIdentifiantEquipement(), "2222");

		LocalDateTime ldt = LocalDateTime.of(2016, Month.NOVEMBER, 8, 16, 50, 10);
		Instant ist = ldt.toInstant(ZoneOffset.UTC);
		assertEquals("Horodate g�n�ration incorrect", msg.getHorodateGeneration(), Date.from(ist));

		ldt = LocalDateTime.of(2016, Month.NOVEMBER, 8, 16, 55, 20);
		ist = ldt.toInstant(ZoneOffset.UTC);
		assertEquals("Horodate FIN incorrect", msg.getHorodateFin(), Date.from(ist));

		MessageEquipementModuleUniqueMqttRest msgPan = (MessageEquipementModuleUniqueMqttRest) msg.getMessageEquipement();

		// Test des flashs
		assertEquals("DV panneau incorrect", 11, msgPan.getMessagesModuleUnique().getDureeValidite());
		assertEquals("DV restante flashs incorrect", 1,
				msgPan.getMessagesModuleUnique().getDureeValiditeRestante());
		assertEquals("Labels flashs incorrect", Arrays.asList("label10", "label11"),
				msgPan.getMessagesModuleUnique().getLabelsParPage());
		assertEquals("Luminosit� flashs incorrecte", Luminosite.JOUR,
				msgPan.getMessagesModuleUnique().getLuminosite());
		assertEquals("Messages flashs incorrecte", Arrays.asList("msg10", "msg11"),
				msgPan.getMessagesModuleUnique().getMessagesParPage());
		assertEquals("Mode affichage flashs incorrect", Mode.ALTERNAT,
				msgPan.getMessagesModuleUnique().getModeAffichage());
		assertEquals("Temps affichage flashs incorrect", Arrays.asList(101, 102),
				msgPan.getMessagesModuleUnique().getTempsAllumage());
		assertEquals("Temps extinction flashs incorrect", Arrays.asList(103, 104),
				msgPan.getMessagesModuleUnique().getTempsExtinction());
	}

	@Test
	public void testRequeteDemandeEtatAffichageEquipements() {
		fail("Not yet implemented");
	}

	@Test
	public void testRequeteActualisationEtatEquipement() {
		fail("Not yet implemented");
	}
}

class TraitementRequetesEtatEtPilotage extends TraitementRequetesRestAdapteur {

	@Override
	public MessageEtatAffichageMqttRest demandeEtatAffichageEquipement(String pId, String pIdentifiantExpediteur,
			String pReferenceCommande) {
		MessageEtatAffichageMqttRest retour = new MessageEtatAffichageMqttRest(pId, pIdentifiantExpediteur,
				pReferenceCommande);

		LocalDateTime ldt = LocalDateTime.of(2016, Month.NOVEMBER, 8, 16, 50, 10);
		Instant ist = ldt.toInstant(ZoneOffset.UTC);
		retour.setHorodateGeneration(Date.from(ist));

		ldt = LocalDateTime.of(2016, Month.NOVEMBER, 8, 16, 55, 20);
		ist = ldt.toInstant(ZoneOffset.UTC);
		retour.setHorodateFin(Date.from(ist));

		// Cas demande �tat PMV
		if (pId.equals("1111")) {
			retour.setMessageEquipement(getEtatAffichagePmv());
		}
		// Cas demande PPAD
		else if(pId.equals("2222")) {
			retour.setMessageEquipement(getEtatAffichagePpad());
		}

		return retour;
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
}
