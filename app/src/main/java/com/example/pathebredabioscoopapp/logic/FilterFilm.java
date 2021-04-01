package com.example.pathebredabioscoopapp.logic;

import android.widget.Filter;

import com.example.pathebredabioscoopapp.domain.Films;

import java.util.ArrayList;
import java.util.Date;

public class FilterFilm {
    private final String TAG = getClass().getSimpleName();
    private ArrayList<Films> filmList;
    private ArrayList<Films> fullFilmList;
    private Filter filter = new Filter() {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            return null;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {

        }
    };

    public Filter getFilter(){
        return filter;
    }

    public ArrayList<Films> filterGenre(String genreConstraint){
        return null;
    }

    public ArrayList<Films> filterRating(Double ratingConstraint){
        return null;
    }

    public ArrayList<Films> filterReleaseDate(Date dateConstraint){
        return null;
    }
}
