package com.strideup.codingexercise.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
public class Address {

    @Id
    @GeneratedValue
    private Long id;

    private String postalCode;
    private String city;
    private String stateCode;
    private String line1;
    private Type type;
    private String line3;
    private String line2;

    public Long getId() {
        return id;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getCity() {
        return city;
    }

    public String getStateCode() {
        return stateCode;
    }

    public String getLine1() {
        return line1;
    }

    public Type getType() {
        return type;
    }

    public String getLine3() {
        return line3;
    }

    public String getLine2() {
        return line2;
    }
}
