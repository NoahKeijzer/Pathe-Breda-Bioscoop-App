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
    private ArrayList<Films> fullFilmList ;
    private ArrayList<Films> entireFilmList = new ArrayList<>();
    private FilmAdapter filmAdapter;
    private SortFilm sortFilm;

    public FilterFilm(ArrayList<Films> filteredFilmList, ArrayList<Films> fullFilmList, FilmAdapter filmAdapter){
        this.filteredFilmList = filteredFilmList;
        this.fullFilmList = fullFilmList;
        this.filmAdapter = filmAdapter;
    }

    public Filter filter = new Filter() {

        @Override
        public FilterResults performFiltering(CharSequence constraint) {
 //           Log.d(TAG, "performFiltering is aangeroepen");

            FilterResults results = new FilterResults();
            String strConstraint = constraint.toString();

            if(entireFilmList.isEmpty()){
                entireFilmList.addAll(fullFilmList);
            }

            if(strConstraint.equals("All")){
                results.values = entireFilmList;
                return results;
            }else if(isGenre(strConstraint)){
                filterGenre(strConstraint);
            }else if(isRating(strConstraint)){
                Double dblConstraint = Double.parseDouble(strConstraint);
                filterRating(dblConstraint);
            }else{
                filterReleaseDate(strConstraint);
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

    public ArrayList<Films> filterGenre(String genreConstraint){
        Log.d(TAG, "filterGenre is aangeroepen.");
        this.filteredFilmList.clear();

        for(Films film: this.entireFilmList){
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
        Log.d(TAG, "filterRating aangeroepen.");
        this.filteredFilmList.clear();

        for(Films film: this.entireFilmList){
            if(film.getRating() >= ratingConstraint){
                this.filteredFilmList.add(film);
            }
        }

        if(this.filteredFilmList != null){
            sortFilm = new SortFilm(filteredFilmList, filmAdapter);
            filteredFilmList = sortFilm.sortRating(sortFilm.sortHashMapByValuesLowToHigh(sortFilm.getFilmRating()));
            return filteredFilmList;
        }
        return null;
    }

    public ArrayList<Films> filterReleaseDate(String dateConstraint){
        Log.d(TAG, "filterReleaseDate aangeroepen.");
        this.filteredFilmList.clear();

        for(Films film: this.entireFilmList){
            if(film.getReleaseDate().equals(dateConstraint)){
                this.filteredFilmList.add(film);
            }
        }
        if(this.filteredFilmList != null){
            return this.filteredFilmList;
        }

        return null;
    }
}
