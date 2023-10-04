package com.poc.drools.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

@Getter
@AllArgsConstructor
public class RuleRequest<T extends Serializable> {

    private String rulesetName;
    private T input;

}
