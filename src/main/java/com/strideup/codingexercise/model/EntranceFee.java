package com.strideup.codingexercise.model;

import javax.persistence.*;

@Entity
public class EntranceFee {

    @Id
    @GeneratedValue
    private Long id;
    private String cost;

    private String description;
    private String title;

    public Long getId() {
        return id;
    }

    public String getCost() {
        return cost;
    }

    public String getDescription() {
        return description;
    }

    public String getTitle() {
        return title;
    }
}
