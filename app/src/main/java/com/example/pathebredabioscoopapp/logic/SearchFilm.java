package com.example.pathebredabioscoopapp.logic;

import android.util.Log;
import android.widget.Adapter;
import android.widget.Filter;

import com.example.pathebredabioscoopapp.domain.Films;

import java.util.ArrayList;

public class SearchFilm {
    private final String TAG = getClass().getSimpleName();
    private ArrayList<Films> filmList;
    private ArrayList<Films> fullFilmList;
    private FilmAdapter filmAdapter;

    public SearchFilm(ArrayList<Films> filmList, FilmAdapter filmAdapter){
        this.filmList = filmList;
        this.fullFilmList = new ArrayList<>();
        this.filmAdapter = filmAdapter;
    }

    private Filter filter = new Filter() {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            ArrayList<Films> filteredList = new ArrayList<>();
            FilterResults results = new FilterResults();

            if (fullFilmList.isEmpty()) {
                fullFilmList.addAll(filmList);
            }

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(fullFilmList);
                results.values = fullFilmList;
                return results;
            }

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(fullFilmList);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                results.values = filterSearchBar(filterPattern);
                return results;
            }
            results.values = fullFilmList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
    //        Log.d(TAG, "publishResults() is aangeroepen");
            filmList.clear();
            filmList.addAll((ArrayList) results.values);
            filmAdapter.notifyDataSetChanged();
        }
    };

    public ArrayList<Films> filterSearchBar(String constraint) {
        ArrayList<Films> filteredList = new ArrayList<>();
        String filterPattern = constraint.toLowerCase().trim();

        if (constraint == null) {
            filteredList.addAll(fullFilmList);
            return filteredList;
        }

        for (Films item : filmList) {
            if (item.getTitle().toLowerCase().contains(filterPattern)) {
                filteredList.add(item);
            }
        }
        filmList.addAll(fullFilmList);
        return filteredList;
    }

    public Filter getFilter(){
        return filter;
    }
}
