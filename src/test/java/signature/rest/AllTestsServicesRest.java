package signature.rest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ ServiceRequetesAdministrationTest.class, ServiceRequetesUtilisateurTest.class,
		ServiceRequetesEtatEtPilotageTest.class })
public class AllTestsServicesRest {

}
