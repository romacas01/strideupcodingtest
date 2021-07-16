package com.strideup.codingexercise.model;

import javax.persistence.*;

@Entity
public class Exception {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String startDate;
    private String endDate;

    @OneToOne(mappedBy="exception", cascade = CascadeType.ALL)
    private ExceptionHours exceptionHours;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public ExceptionHours getExceptionHours() {
        return exceptionHours;
    }
}
