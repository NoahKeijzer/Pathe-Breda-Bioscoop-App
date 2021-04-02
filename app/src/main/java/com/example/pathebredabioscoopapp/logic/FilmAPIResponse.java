package com.example.pathebredabioscoopapp.logic;

import com.example.pathebredabioscoopapp.domain.Films;

import java.util.ArrayList;

/**
 * Dit object komt overeen met het response zoals je dat van de API in het response ontvangt.
 * Je maakt dus voor een API, voor de JSON die je ontvangt, een specifiek response class.
 */
public class FilmAPIResponse {

    private int page;
    private ArrayList<Films> results;

    public FilmAPIResponse(int page, ArrayList<Films> results) {
        this.page = page;
        this.results = results;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public ArrayList<Films> getResults() {
        return results;
    }

    public void setResults(ArrayList<Films> results) {
        this.results = results;
    }

    @Override
    public String toString() {
        return "MovieApiResponse{" +
                "page=" + page +
                ", results=" + results +
                '}';
    }
}


