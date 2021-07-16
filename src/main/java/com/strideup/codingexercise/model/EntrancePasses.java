package com.strideup.codingexercise.model;

import javax.persistence.*;

@Entity
public class EntrancePasses {

    @Id
    @GeneratedValue
    private Long id;

    private String title;
    private Long cost;

    @Column(columnDefinition="TEXT")
    private String description;

    public Long getId() {
        return id;
    }

    public Long getCost() {
        return cost;
    }

    public String getDescription() {
        return description;
    }

    public String getTitle() {
        return title;
    }
}
