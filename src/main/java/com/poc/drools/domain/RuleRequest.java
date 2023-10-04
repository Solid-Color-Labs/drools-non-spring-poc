package com.poc.drools.domain;

import java.io.Serializable;

public class RuleRequest<T extends Serializable> {

    private String rulesetName;
    private T input;

    public RuleRequest(String rulesetName, T input) {
        this.rulesetName = rulesetName;
        this.input = input;
    }

    public String getRulesetName() {
        return rulesetName;
    }

    public T getInput() {
        return input;
    }

}
