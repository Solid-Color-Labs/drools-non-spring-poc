package com.poc.drools.configuration;

import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.KieModule;
import org.kie.api.runtime.KieContainer;
import org.kie.internal.io.ResourceFactory;

public class DroolsConfiguration {

    private final KieContainer kieContainer;

    private DroolsConfiguration(){
        kieContainer = kieContainer();
    }

    private static class SingletonHelper {
        private static final DroolsConfiguration INSTANCE = new DroolsConfiguration();
    }

    public static DroolsConfiguration getInstance() {
        return SingletonHelper.INSTANCE;
    }

    private KieContainer kieContainer() {
        KieServices kieServices = KieServices.Factory.get();

        KieFileSystem kieFileSystem = kieServices.newKieFileSystem();
        kieFileSystem.write(ResourceFactory.newClassPathResource("TAXI_FARE_RULE.drl"));
        kieFileSystem.write(ResourceFactory.newClassPathResource("BOOLEAN_RULE.drl"));
        kieFileSystem.write(ResourceFactory.newClassPathResource("CUSTOMER_RULES.drl"));
        kieFileSystem.write(ResourceFactory.newClassPathResource("LIST_EXAMPLE_RULES.drl"));
        kieFileSystem.write(ResourceFactory.newClassPathResource("EXIT_ON_FIRST_CONDITION_MATCH_EXAMPLE.drl"));
        kieFileSystem.write(ResourceFactory.newClassPathResource("Discount.drl.xlsx"));

        KieBuilder kieBuilder = kieServices.newKieBuilder(kieFileSystem);
        kieBuilder.buildAll();

        KieModule kieModule = kieBuilder.getKieModule();

        return kieServices.newKieContainer(kieModule.getReleaseId());
    }

    public KieContainer getKieContainer() {
        return kieContainer;
    }
}
