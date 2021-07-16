package com.strideup.codingexercise.model;

import javax.persistence.*;

@Entity
public class Image {

    @Id
    @GeneratedValue
    private Long Id;

    private String credit;
    private String altText;
    private String title;
    private String caption;
    private String url;

    public Long getId() {
        return Id;
    }

    public String getCredit() {
        return credit;
    }

    public String getAltText() {
        return altText;
    }

    public String getTitle() {
        return title;
    }

    public String getCaption() {
        return caption;
    }

    public String getUrl() {
        return url;
    }
}
