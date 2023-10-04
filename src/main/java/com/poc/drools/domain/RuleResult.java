package com.poc.drools.domain;

import java.util.List;

public class RuleResult {

    private boolean isRuleBreak;
    private List<String> namesOfRulesBroken;

    public boolean isRuleBreak() {
        return isRuleBreak;
    }

    public void setRuleBreak(boolean ruleBreak) {
        isRuleBreak = ruleBreak;
    }

    public List<String> getNamesOfRulesBroken() {
        return namesOfRulesBroken;
    }

    public void setNamesOfRulesBroken(List<String> namesOfRulesBroken) {
        this.namesOfRulesBroken = namesOfRulesBroken;
    }
}
