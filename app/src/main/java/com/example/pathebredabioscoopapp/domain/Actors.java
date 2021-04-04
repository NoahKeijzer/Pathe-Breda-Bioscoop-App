package com.example.pathebredabioscoopapp.domain;

public class Actors {
    private final String TAG = getClass().getSimpleName();
    private int id;
    private String name;
    private String character;
    private String picture;

    public Actors(int id, String name, String character, String picture) {
        this.id = id;
        this.name = name;
        this.character = character;
        this.picture = picture;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPicture() {
        return picture;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
        this.character = character;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}
