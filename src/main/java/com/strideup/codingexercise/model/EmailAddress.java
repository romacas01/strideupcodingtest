package com.strideup.codingexercise.model;

import javax.persistence.*;

@Entity
public class EmailAddress {

    @Id
    @GeneratedValue
    private Long id;

    private String description;
    private String emailAddress;

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getEmailAddress() {
        return emailAddress;
    }
}
