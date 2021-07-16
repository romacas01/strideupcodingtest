package com.strideup.codingexercise.model;

import javax.persistence.*;

@Entity
public class PhoneNumber {

    @Id
    @GeneratedValue
    private Long id;

    private Type type;

    private String phoneNumber;

    @Column(columnDefinition="TEXT")
    private String description;

    @Column(columnDefinition="TEXT")
    private String extension;

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
