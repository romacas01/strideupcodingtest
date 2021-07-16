package com.strideup.codingexercise.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class StandardHour {

    @Id
    @GeneratedValue
    private Long Id;

    private String sunday;
    private String  monday;
    private String  tuesday;
    private String  wednesday;
    private String  thursday;
    private String  friday;
    private String  saturday;

    @OneToOne(mappedBy = "standardHours")
    private OperatingHour operatingHour;

    public Long getId() {
        return Id;
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
