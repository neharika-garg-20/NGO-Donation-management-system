
package com.example.thymeleaf2_demo.model;

import java.util.Set;

public class User {
    private String userType;
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
    private String badgeLevel;
    private String organizationName;
    private Set<String> acceptedItemTypes;
    private String websiteUrl;
    private String upiId;
    private String bankAccountNumber;
    private String bankIfsc;
    private String bankAccountName;
    private String vehicleType;
    private String registrationId;
    private String documentUrl;

    // public interface DefaultValidation {}
    // public interface NGOValidation {}
    // public interface PickupAgentValidation {}

    // Getters and Setters
    public String getUserType() { return userType; }
    public void setUserType(String userType) { this.userType = userType; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public String getMiddleName() { return middleName; }
    public void setMiddleName(String middleName) { this.middleName = middleName; }
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }
    public String getStreetAddress() { return streetAddress; }
    public void setStreetAddress(String streetAddress) { this.streetAddress = streetAddress; }
    public String getHouseNumber() { return houseNumber; }
    public void setHouseNumber(String houseNumber) { this.houseNumber = houseNumber; }
    public String getApartment() { return apartment; }
    public void setApartment(String apartment) { this.apartment = apartment; }
    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
    public String getBadgeLevel() { return badgeLevel; }
    public void setBadgeLevel(String badgeLevel) { this.badgeLevel = badgeLevel; }
    public String getOrganizationName() { return organizationName; }
    public void setOrganizationName(String organizationName) { this.organizationName = organizationName; }
    public Set<String> getAcceptedItemTypes() { return acceptedItemTypes; }
    public void setAcceptedItemTypes(Set<String> acceptedItemTypes) { this.acceptedItemTypes = acceptedItemTypes; }
    public String getWebsiteUrl() { return websiteUrl; }
    public void setWebsiteUrl(String websiteUrl) { this.websiteUrl = websiteUrl; }
    public String getUpiId() { return upiId; }
    public void setUpiId(String upiId) { this.upiId = upiId; }
    public String getBankAccountNumber() { return bankAccountNumber; }
    public void setBankAccountNumber(String bankAccountNumber) { this.bankAccountNumber = bankAccountNumber; }
    public String getBankIfsc() { return bankIfsc; }
    public void setBankIfsc(String bankIfsc) { this.bankIfsc = bankIfsc; }
    public String getBankAccountName() { return bankAccountName; }
    public void setBankAccountName(String bankAccountName) { this.bankAccountName = bankAccountName; }
    public String getVehicleType() { return vehicleType; }
    public void setVehicleType(String vehicleType) { this.vehicleType = vehicleType; }
    public String getRegistrationId() { return registrationId; }
    public void setRegistrationId(String registrationId) { this.registrationId = registrationId; }
    public String getDocumentUrl() { return documentUrl; }
    public void setDocumentUrl(String documentUrl) { this.documentUrl = documentUrl; }
}