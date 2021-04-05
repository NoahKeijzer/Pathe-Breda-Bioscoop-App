package com.example.pathebredabioscoopapp.logic;

import android.widget.Filter;

import com.example.pathebredabioscoopapp.domain.Films;

import java.util.ArrayList;
import java.util.Collections;

public class SortFilm {
    private final String TAG = getClass().getSimpleName();
    private ArrayList<Films> filmList;
    private ArrayList<String> filmTitles;

    public SortFilm(ArrayList<Films> filmList) {
        this.filmList = filmList;
        filmTitles = new ArrayList<>();
    }

    private Filter filter = new Filter() {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            return null;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {

        }
    };
    public ArrayList<String> getAllFilmTitles(){
        for(Films x : filmList){
            filmTitles.add(x.getTitle());
        }
        return filmTitles;
    }

    public Filter getFilter(){
        return filter;
    }

    public ArrayList<Films> sortZtoA(){
        ArrayList<Films> sortedList = new ArrayList<>();
        Collections.sort(this.filmTitles);
        for(String i : filmTitles){
            for(Films j : filmList){
                if(i.equals(j.getTitle())){
                    sortedList.add(j);
                }
            }
        }
        return sortedList;
    }

    public ArrayList<Films> sortRatingHigh(){
        return null;
    }

    public ArrayList<Films> sortRatingLow(){
        return null;
    }

    public ArrayList<Films> sortReleaseDateOld(){
        return null;
    }

    public ArrayList<Films> sortReleaseDateNew(){
        return null;
    }

}
