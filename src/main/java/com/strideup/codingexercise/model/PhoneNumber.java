package com.strideup.codingexercise.model;

import javax.persistence.*;

@Entity
public class PhoneNumber {

    @Id
    @GeneratedValue
    private Long id;
    private String phoneNumber;
    private String description;
    private String extension;
    private Type type;

    public Long getId() {
        return id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getDescription() {
        return description;
    }

    public String getExtension() {
        return extension;
    }

    public Type getType() {
        return type;
    }
}
