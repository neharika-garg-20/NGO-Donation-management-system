package com.example.thymeleaf2_demo.model;

import java.util.List;

public class ngo {
    private int id;
    private String name;
    private String city;
    private List<String> items;
    private String email;
    private String website;

    public ngo(int id, String name, String city, List<String> items, String email, String website) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.items = items;
        this.email = email;
        this.website = website;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public List<String> getItems() {
        return items;
    }

    public void setItems(List<String> items) {
        this.items = items;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }
}