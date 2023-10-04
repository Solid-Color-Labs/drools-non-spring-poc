package com.poc.drools.service;

import com.poc.drools.configuration.DroolsConfiguration;
import com.poc.drools.domain.RuleRequest;
import com.poc.drools.domain.RuleResult;
import com.poc.drools.listener.RuleBreakEventListener;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import java.io.Serializable;

public class DroolsService {

    private KieContainer kieContainer;

    public DroolsService(String bFunction) {
        kieContainer = DroolsConfiguration.getInstance().getBFunction(bFunction);
    }

    public <T extends Serializable> RuleResult evaluateRules(RuleRequest<T> request) {
        RuleResult result = new RuleResult();
        RuleBreakEventListener ruleBreakEventListener = new RuleBreakEventListener();
        KieSession kieSession = kieContainer.newKieSession();
        kieSession.getAgenda().getAgendaGroup(request.getRulesetName()).setFocus();
        kieSession.addEventListener(ruleBreakEventListener);
        kieSession.setGlobal("ruleResult", result);
        kieSession.insert(request.getInput());
        kieSession.fireAllRules();
        kieSession.dispose();
        result.setNamesOfRulesBroken(ruleBreakEventListener.getNamesOfRulesBroken());
        return result;
    }

}
