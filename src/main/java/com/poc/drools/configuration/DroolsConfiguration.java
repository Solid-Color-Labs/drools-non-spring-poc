package com.poc.drools.configuration;

import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.KieModule;
import org.kie.api.runtime.KieContainer;
import org.kie.internal.io.ResourceFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DroolsConfiguration {

    private final Map<String, KieContainer> bFunctions = new ConcurrentHashMap<>();

    private DroolsConfiguration() {
    }

    private static class SingletonHelper {
        private static final DroolsConfiguration INSTANCE = new DroolsConfiguration();
    }

    public static DroolsConfiguration getInstance() {
        return SingletonHelper.INSTANCE;
    }

    public KieContainer getBFunction(String bFunction) {
        if (bFunctions.containsKey(bFunction)) {
            return bFunctions.get(bFunction);
        }
        KieContainer kieContainer = kieContainer(bFunction);
        bFunctions.put(bFunction, kieContainer(bFunction));
        return kieContainer;
    }

    // TODO instead of classpath retrieve from database or something like that
    private KieContainer kieContainer(String bFunction) {
        KieServices kieServices = KieServices.Factory.get();

        KieFileSystem kieFileSystem = kieServices.newKieFileSystem();
        kieFileSystem.write(ResourceFactory.newClassPathResource("CUSTOMER_RULES.drl"));

        KieBuilder kieBuilder = kieServices.newKieBuilder(kieFileSystem);
        kieBuilder.buildAll();

        KieModule kieModule = kieBuilder.getKieModule();

        return kieServices.newKieContainer(kieModule.getReleaseId());
    }

}
