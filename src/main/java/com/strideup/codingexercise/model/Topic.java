package com.strideup.codingexercise.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@JsonIgnoreProperties("id")
public class Topic {

    @Id
    @GeneratedValue
    private Long id;

    @Column(columnDefinition="TEXT")
    private String name;


    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
