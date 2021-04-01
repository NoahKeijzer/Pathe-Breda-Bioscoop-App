package com.example.pathebredabioscoopapp.logic;

import android.widget.Filter;

import com.example.pathebredabioscoopapp.domain.Films;

import java.util.ArrayList;

public class SearchFilm {
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
}
