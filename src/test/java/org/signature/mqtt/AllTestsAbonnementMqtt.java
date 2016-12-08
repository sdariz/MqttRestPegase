package org.signature.mqtt;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ AbonnementEtatAffichageTest.class, AbonnementEtatTechniqueTest.class,
		AbonnementEtatAffichageEtatTechniqueTest.class, AbonnementModificationPegaseTest.class,
		AbonnementPilotageScenarioTest.class })
public class AllTestsAbonnementMqtt {

}
