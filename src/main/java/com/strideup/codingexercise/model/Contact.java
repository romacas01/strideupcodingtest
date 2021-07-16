package com.strideup.codingexercise.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.List;

@Entity
public class Contact {

    @Id
    @GeneratedValue
    private Long id;

    @OneToMany(cascade=CascadeType.ALL)
    private List<PhoneNumber> phoneNumbers;

    @OneToMany(cascade=CascadeType.ALL)
    private List<EmailAddress> emailAddresses;

    public Long getId() {
        return id;
    }

    public List<PhoneNumber> getPhoneNumbers() {
        return phoneNumbers;
    }

    public List<EmailAddress> getEmailAddresses() {
        return emailAddresses;
    }
}
