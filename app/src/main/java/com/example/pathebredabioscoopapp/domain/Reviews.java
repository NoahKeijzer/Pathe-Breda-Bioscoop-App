package com.example.pathebredabioscoopapp.domain;

import java.io.Serializable;

public class Reviews implements Serializable {
    private final String TAG = getClass().getSimpleName();
    private String id;
    private double rating;
    private String name;
    private String username;
    private String content;

    public Reviews(String id, double rating, String name, String username, String content) {
        this.id = id;
        this.rating = rating;
        this.name = name;
        this.username = username;
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
