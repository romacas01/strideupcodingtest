package com.strideup.codingexercise.model;

import javax.persistence.*;

@Entity
public class Image {

    @Id
    @GeneratedValue
    private Long Id;

    private String title;
    private String url;

    @Column(columnDefinition="TEXT")
    private String credit;

    @Column(columnDefinition="TEXT")
    private String altText;

    @Column(columnDefinition="TEXT")
    private String caption;

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
