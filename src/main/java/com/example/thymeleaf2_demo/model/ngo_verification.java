package com.example.thymeleaf2_demo.model;

import java.time.LocalDate;

public class ngo_verification {
    private int verificationId;
    private int ngoId;
    private String registrationNumber;
    private String documentUrl;
    private LocalDate dateSubmitted;
    private LocalDate dateVerified;
    private Integer verifiedByAdminId;
    private String status;
    private String remarks;

    public ngo_verification(int verificationId, int ngoId, String registrationNumber, String documentUrl,
                           LocalDate dateSubmitted, LocalDate dateVerified, Integer verifiedByAdminId,
                           String status, String remarks) {
        this.verificationId = verificationId;
        this.ngoId = ngoId;
        this.registrationNumber = registrationNumber;
        this.documentUrl = documentUrl;
        this.dateSubmitted = dateSubmitted;
        this.dateVerified = dateVerified;
        this.verifiedByAdminId = verifiedByAdminId;
        this.status = status;
        this.remarks = remarks;
    }

    public int getVerificationId() {
        return verificationId;
    }

    public void setVerificationId(int verificationId) {
        this.verificationId = verificationId;
    }

    public int getNgoId() {
        return ngoId;
    }

    public void setNgoId(int ngoId) {
        this.ngoId = ngoId;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getDocumentUrl() {
        return documentUrl;
    }

    public void setDocumentUrl(String documentUrl) {
        this.documentUrl = documentUrl;
    }

    public LocalDate getDateSubmitted() {
        return dateSubmitted;
    }

    public void setDateSubmitted(LocalDate dateSubmitted) {
        this.dateSubmitted = dateSubmitted;
    }

    public LocalDate getDateVerified() {
        return dateVerified;
    }

    public void setDateVerified(LocalDate dateVerified) {
        this.dateVerified = dateVerified;
    }

    public Integer getVerifiedByAdminId() {
        return verifiedByAdminId;
    }

    public void setVerifiedByAdminId(Integer verifiedByAdminId) {
        this.verifiedByAdminId = verifiedByAdminId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}