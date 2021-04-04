package com.example.pathebredabioscoopapp.logic;

import android.icu.text.SimpleDateFormat;
import android.util.Log;
import android.widget.Filter;

import com.example.pathebredabioscoopapp.domain.FilmList;
import com.example.pathebredabioscoopapp.domain.Films;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

public class FilterFilm {
    private final String TAG = getClass().getSimpleName();
    private ArrayList<Films> filteredFilmList;
    private ArrayList<Films> fullFilmList;

    public FilterFilm(ArrayList<Films> fullFilmList){
        this.fullFilmList = fullFilmList;
    }

    private Filter filter = new Filter() {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            Log.d(TAG, "performFiltering is aangeroepen");

            FilterResults results = new FilterResults();
            String strConstraint = constraint.toString();

            if(isGenre(strConstraint)){
                filterGenre(strConstraint);
            }else if(isRating(strConstraint)){
                Double dblConstraint = Double.parseDouble(strConstraint);
                filterRating(dblConstraint);
            }else if(isDate(strConstraint)){
                Date date = parseToDate(strConstraint);
                filterReleaseDate(date);
            }else{
                return null;
            }

            results.values = filteredFilmList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            fullFilmList.clear();
            fullFilmList.addAll((ArrayList) results.values);
        }
    };

    public Filter getFilter(){
        return filter;
    }

    public boolean isGenre(String strConstraint) {
        //Lijst met Genres moet hieronder nog worden gemaakt!
        ArrayList<String> possibleGenres = new ArrayList<>();
        possibleGenres.add("Hier moeten alle genres komen");

        for(String genre: possibleGenres){
            if(genre.equals(strConstraint)){
                return true;
            }
        }

        return false;
    }

    public boolean isRating(String strConstraint) {
        try {
            Double.parseDouble(strConstraint);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public boolean isDate(String strConstraint) {
        String sDate = strConstraint;
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy/MM/dd").parse(sDate);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    public Date parseToDate(String strConstraint){
        String sDate = strConstraint;
        Date date = null;

        try {
            date = new SimpleDateFormat("yyyy/MM/dd").parse(sDate);
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<Films> filterGenre(String genreConstraint){

        for(Films film: this.fullFilmList){
            if(film.getGenre().equals(genreConstraint)){
                this.filteredFilmList.add(film);
            }
        }

        if(this.filteredFilmList != null){
            return this.filteredFilmList;
        }

        return null;
    }

    public ArrayList<Films> filterRating(Double ratingConstraint){

        for(Films film: this.fullFilmList){
            if(film.getRating() == ratingConstraint){
                this.filteredFilmList.add(film);
            }
        }

        if(this.filteredFilmList != null){
            return this.filteredFilmList;
        }

        return null;
    }

    public ArrayList<Films> filterReleaseDate(Date dateConstraint){

        for(Films film: this.fullFilmList){
            if(film.getReleaseDate().equals(dateConstraint.toString())){
                this.filteredFilmList.add(film);
            }
        }

        if(this.filteredFilmList != null){
            return this.filteredFilmList;
        }

        return null;
    }
}
