package com.example.pathebredabioscoopapp.domain;

import java.io.Serializable;
import java.util.ArrayList;

public class FilmList implements Serializable {
    private final String TAG = getClass().getSimpleName();
    private int id;
    private String name;
    private ArrayList<Films> filmList;

    public FilmList(int id, String name, ArrayList<Films> filmList) {
        this.id = id;
        this.name = name;
        this.filmList = filmList;
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

    public ArrayList<Films> getFilmList() {
        return filmList;
    }

    public void setFilmList(ArrayList<Films> filmList) {
        this.filmList = filmList;
    }
}
