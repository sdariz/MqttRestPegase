package signature.rest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ ServiceRequetesAdministrationTest.class, ServiceRequetesUtilisateurTest.class,
		ServiceRequetesEtatTest.class, ServiceRequetesInterrogationArmoireTest.class,
		ServiceRequetesBibliothequePmvTest.class, serviceRequetesInformationPegaseTest.class })
public class AllTestsServicesRest {

}
