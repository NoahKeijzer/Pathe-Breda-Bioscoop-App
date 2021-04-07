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
    private ArrayList<Films> filteredFilmList ;
    private ArrayList<Films> fullFilmList ;
    private FilmAdapter filmAdapter;

    public FilterFilm(ArrayList<Films> filteredFilmList, ArrayList<Films> fullFilmList, FilmAdapter filmAdapter){
        this.filteredFilmList = filteredFilmList;
        this.fullFilmList = fullFilmList;
        this.filmAdapter = filmAdapter;
    }

    public Filter filter = new Filter() {

        @Override
        public FilterResults performFiltering(CharSequence constraint) {
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
            filmAdapter.notifyDataSetChanged();
        }
    };

    public Filter getFilter(){
        return filter;
    }

    public boolean isGenre(String strConstraint) {
        String[] possibleGenres = {"Action", "Adventure", "Animation", "Comedy", "Crime", "Documentary", "Drama",
                "Family", "Fantasy", "History", "Horror", "Music", "Mystery", "Romance", "Science Fiction", "Thriller",
                "TV Movie", "War", "Western"};

        for(String genre: possibleGenres){
            if(strConstraint.contains(genre)){
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
