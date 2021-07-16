package com.strideup.codingexercise.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@JsonIgnoreProperties(value = { "fees", "id" })
public class Park {

    @Id
    @GeneratedValue
    private Long id;

    @Column(columnDefinition="TEXT")
    private String description;
    @Column(columnDefinition="TEXT")
    private String designation;
    @Column(columnDefinition="TEXT")
    private String directionsInfo;
    @Column(columnDefinition="TEXT")
    private String directionsUrl;
    @Column(columnDefinition="TEXT")
    private String fullName;
    @Column(columnDefinition="TEXT")
    private String latLong;
    @Column(columnDefinition="TEXT")
    private String latitude;
    @Column(columnDefinition="TEXT")
    private String longitude;
    @Column(columnDefinition="TEXT")
    private String name;
    @Column(columnDefinition="TEXT")
    private String parkCode;
    @Column(columnDefinition="TEXT")
    private String states;
    @Column(columnDefinition="TEXT")
    private String url;
    @Column(columnDefinition="TEXT")
    private String weatherInfo;

    @OneToMany( cascade=CascadeType.ALL)
    private List<Activity> activities;

    //@OneToMany(cascade = CascadeType.MERGE)
    @OneToMany(cascade=CascadeType.ALL)
    private List<Address> addresses;

    @OneToMany(cascade=CascadeType.ALL)
    private List<Contact> contacts;

    @OneToMany(cascade=CascadeType.ALL)
    private List<EntranceFee> entranceFees;

    @OneToMany(cascade=CascadeType.ALL)
    private List<EntranceFee> entrancePasses;

    @OneToMany(cascade=CascadeType.ALL)
    private List<Image> images;

    @OneToMany(cascade=CascadeType.ALL)
    private List<Topic> topics;

    @OneToMany(cascade=CascadeType.ALL)
    private List<OperatingHour> operatingHours;

    public Long getId() {
        return id;
    }

    public List<Activity> getActivities() {
        return activities;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public String getDescription() {
        return description;
    }

    public String getDesignation() {
        return designation;
    }

    public String getDirectionsInfo() {
        return directionsInfo;
    }

    public String getDirectionsUrl() {
        return directionsUrl;
    }

    public String getFullName() {
        return fullName;
    }

    public String getLatLong() {
        return latLong;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getName() {
        return name;
    }

    public String getParkCode() {
        return parkCode;
    }

    public String getStates() {
        return states;
    }

    public String getUrl() {
        return url;
    }

    public String getWeatherInfo() {
        return weatherInfo;
    }

    public List<EntranceFee> getEntranceFees() {
        return entranceFees;
    }

    public List<EntranceFee> getEntrancePasses() {
        return entrancePasses;
    }

    public List<Image> getImages() {
        return images;
    }

    public List<Topic> getTopics() {
        return topics;
    }

    public List<OperatingHour> getOperatingHours() {
        return operatingHours;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public void setDirectionsInfo(String directionsInfo) {
        this.directionsInfo = directionsInfo;
    }

    public void setDirectionsUrl(String directionsUrl) {
        this.directionsUrl = directionsUrl;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setLatLong(String latLong) {
        this.latLong = latLong;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setParkCode(String parkCode) {
        this.parkCode = parkCode;
    }

    public void setStates(String states) {
        this.states = states;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setWeatherInfo(String weatherInfo) {
        this.weatherInfo = weatherInfo;
    }

    public void setActivities(List<Activity> activities) {
        this.activities = activities;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }

    public void setEntranceFees(List<EntranceFee> entranceFees) {
        this.entranceFees = entranceFees;
    }

    public void setEntrancePasses(List<EntranceFee> entrancePasses) {
        this.entrancePasses = entrancePasses;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public void setTopics(List<Topic> topics) {
        this.topics = topics;
    }

    public void setOperatingHours(List<OperatingHour> operatingHours) {
        this.operatingHours = operatingHours;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Park park = (Park) o;
        return Objects.equals(description, park.description) &&
                Objects.equals(designation, park.designation) &&
                Objects.equals(directionsInfo, park.directionsInfo) &&
                Objects.equals(directionsUrl, park.directionsUrl) &&
                Objects.equals(fullName, park.fullName) &&
                Objects.equals(latLong, park.latLong) &&
                Objects.equals(latitude, park.latitude) &&
                Objects.equals(longitude, park.longitude) &&
                Objects.equals(name, park.name) &&
                Objects.equals(parkCode, park.parkCode) &&
                Objects.equals(states, park.states) &&
                Objects.equals(url, park.url) &&
                Objects.equals(weatherInfo, park.weatherInfo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, designation, directionsInfo, directionsUrl, fullName, latLong, latitude, longitude, name, parkCode, states, url, weatherInfo);
    }

    public static class Builder{
        //TODO: Finish the implementation of the builder
        private String description;
        private String designation;
        private String directionsInfo;
        private String directionsUrl;
        private String fullName;
        private String latLong;
        private String latitude;
        private String longitude;
        private String name;
        private String parkCode;
        private String states;
        private String url;
        private String weatherInfo;
        private List<Activity> activities;
        private List<Address> addresses;
        private List<Contact> contacts;
        private List<EntranceFee> entranceFees;
        private List<EntranceFee> entrancePasses;
        private List<Image> images;
        private List<Topic> topics;
        private List<OperatingHour> operatingHours;

        public Builder(String fullName){
            this.fullName = name;
        }

        public Builder withParkCode(String parkCode){
            this.parkCode = parkCode;
            return this;
        }

        public Builder withDescription(String description){
            this.description = description;
            return this;
        }

        public Park build(){
            Park park = new Park();
            park.fullName = this.fullName;
            park.parkCode = this.parkCode;
            park.description = this.description;

            return park;
        }
    }
}
