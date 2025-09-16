package com.example.thymeleaf2_demo.model;
public class Donor {
    private Integer donorId;
    private String username;
    private String firstName;
    private String middleName;
    private String lastName;
    private String email;
    private String password;
    private String city;
    private String streetAddress;
    private String houseNumber;
    private String apartment;
    private String phoneNumber;
    private String badgeLevel;   // Silver, Gold, Platinum
    private Integer totalDonations;

    // Getters and Setters
    public Integer getDonorId() {
        return donorId;
    }
    public void setDonorId(Integer donorId) {
        this.donorId = donorId;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }
    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }

    public String getStreetAddress() {
        return streetAddress;
    }
    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getHouseNumber() {
        return houseNumber;
    }
    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getApartment() {
        return apartment;
    }
    public void setApartment(String apartment) {
        this.apartment = apartment;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getBadgeLevel() {
        return badgeLevel;
    }
    public void setBadgeLevel(String badgeLevel) {
        this.badgeLevel = badgeLevel;
    }

    public Integer getTotalDonations() {
        return totalDonations;
    }
    public void setTotalDonations(Integer totalDonations) {
        this.totalDonations = totalDonations;
    }
}
