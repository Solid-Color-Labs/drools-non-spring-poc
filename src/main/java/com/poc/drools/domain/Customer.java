package com.poc.drools.domain;

import java.io.Serializable;

public class Customer implements Serializable {

    private CustomerType type;

    private int years;

    private String name;

    private int discount;

    public Customer(CustomerType type, int years, String name) {
        this.type = type;
        this.years = years;
        this.name = name;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public CustomerType getType() {
        return type;
    }

    public int getYears() {
        return years;
    }

    public String getName() {
        return name;
    }

    public int getDiscount() {
        return discount;
    }

    // Standard getters and setters

    public enum CustomerType {
        INDIVIDUAL,
        BUSINESS;
    }
}
