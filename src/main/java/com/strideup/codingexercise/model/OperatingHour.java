package com.strideup.codingexercise.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class OperatingHour {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @Column(columnDefinition="TEXT")
    private String description;

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "operatingHour_id", referencedColumnName = "id")
    private StandardHour standardHours;

    @OneToMany(cascade=CascadeType.ALL)
    private List<Exception> exceptions;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public StandardHour getStandardHours() {
        return standardHours;
    }

    public List<Exception> getExceptions() {
        return exceptions;
    }
}
