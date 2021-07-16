package com.strideup.codingexercise.model;

import javax.persistence.*;

@Entity
public class ExceptionHours {

    @Id
    @GeneratedValue
    private Long id;

    private String sunday;
    private String monday;
    private String tuesday;
    private String wednesday;
    private String thursday;
    private String friday;
    private String saturday;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "exception_id", referencedColumnName = "id")
    private Exception exception;

    public Long getId() {
        return id;
    }

    public String getSunday() {
        return sunday;
    }

    public String getMonday() {
        return monday;
    }

    public String getTuesday() {
        return tuesday;
    }

    public String getWednesday() {
        return wednesday;
    }

    public String getThursday() {
        return thursday;
    }

    public String getFriday() {
        return friday;
    }

    public String getSaturday() {
        return saturday;
    }
}
