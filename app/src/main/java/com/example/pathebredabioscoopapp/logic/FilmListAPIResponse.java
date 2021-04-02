package com.example.pathebredabioscoopapp.logic;

import com.example.pathebredabioscoopapp.domain.FilmList;

import java.util.ArrayList;

public class FilmListAPIResponse {
    private int page;
    private ArrayList<FilmList> results;

    public FilmListAPIResponse(int page, ArrayList<FilmList> results) {
        this.page = page;
        this.results = results;
    }

    public ArrayList<FilmList> getResults() {
        return results;
    }

    @Override
    public String toString() {
        return "MovieListsApiResponse{" +
                "page=" + page +
                ", results=" + results +
                '}';
    }
}
