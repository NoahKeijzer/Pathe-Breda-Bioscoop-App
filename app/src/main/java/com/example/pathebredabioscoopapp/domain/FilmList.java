package com.example.pathebredabioscoopapp.domain;

import java.util.ArrayList;

public class FilmList {
    private final String TAG = getClass().getSimpleName();
    private int id;
    private String name;
    private boolean statusPublic;
    private ArrayList<Films> filmList;

    public FilmList(int id, String name, boolean statusPublic, ArrayList<Films> filmList) {
        this.id = id;
        this.name = name;
        this.statusPublic = statusPublic;
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

    public boolean isStatusPublic() {
        return statusPublic;
    }

    public void setStatusPublic(boolean statusPublic) {
        this.statusPublic = statusPublic;
    }

    public ArrayList<Films> getFilmList() {
        return filmList;
    }

    public void setFilmList(ArrayList<Films> filmList) {
        this.filmList = filmList;
    }
}
