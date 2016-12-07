package org.signature;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import org.signature.mqtt.AllTestsAbonnementMqtt;
import org.signature.rest.AllTestsServicesRest;

@RunWith(Suite.class)
@SuiteClasses({ AllTestsAbonnementMqtt.class, AllTestsServicesRest.class })
public class AllTests {

}
