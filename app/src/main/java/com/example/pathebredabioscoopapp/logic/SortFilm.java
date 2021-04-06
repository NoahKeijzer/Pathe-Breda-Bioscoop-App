package com.example.pathebredabioscoopapp.logic;

import android.util.Log;
import android.widget.Filter;

import com.example.pathebredabioscoopapp.domain.Films;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

public class SortFilm {
    private final String TAG = getClass().getSimpleName();
    private ArrayList<Films> filmList;
    private ArrayList<Films> fullFilmList = new ArrayList<>();
    private ArrayList<String> filmTitles = new ArrayList<>();
    private ArrayList<Date> filmDates = new ArrayList<Date>();
    private ArrayList<Double> filmRating = new ArrayList<>();
    private FilmAdapter filmAdapter;

    public SortFilm(ArrayList<Films> filmList, FilmAdapter filmAdapter) {
        this.filmList = filmList;
        fullFilmList.addAll(filmList);
        this.filmAdapter = filmAdapter;
    }

    private Filter filter = new Filter() {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            Log.d(TAG, "performFiltering is aangeroepen");

            FilterResults results = new FilterResults();
            String strConstraint = constraint.toString();

            ArrayList<Films> filteredList = new ArrayList<>();

            if (fullFilmList.isEmpty()) {
                fullFilmList.addAll(filmList);
            }
            if (strConstraint.equals("sortAtoZ")) {
                getAllFilmTitles();
                results.values = sortAtoZ();
                return results;
            } else if (strConstraint.equals("sortZtoA")) {
                getAllFilmTitles();
                results.values = sortZtoA();
                return results;
            } else if (strConstraint.equals("sortRatingHigh")) {
                getFilmRating();
                results.values = sortRatingHigh();
                return results;
            } else if (strConstraint.equals("sortRatingLow")) {
                getFilmRating();
                results.values = sortRatingLow();
                return results;
            } else if (strConstraint.equals("sortOldToNew")) {
                try {
                    getReleaseDateFilms();
                    results.values = sortReleaseDateOld();
                    return results;
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            } else if (strConstraint.equals("sortNewToOld")) {
                try {
                    getReleaseDateFilms();
                    results.values = sortReleaseDateNew();
                    return results;
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(fullFilmList);
                results.values = fullFilmList;
                return results;
            }

            results.values = fullFilmList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            filmList.clear();
            filmList.addAll((ArrayList) results.values);
            filmAdapter.notifyDataSetChanged();
        }
    };

    public Filter getFilter() {
        return filter;
    }

    public ArrayList<Date> getReleaseDateFilms() throws ParseException {
        for (Films x : filmList) {
            filmDates.add(new SimpleDateFormat("yyyy-MM-dd").parse(x.getReleaseDate()));
        }
        return filmDates;
    }

    public ArrayList<String> getAllFilmTitles() {
        for (Films x : filmList) {
            filmTitles.add(x.getTitle());
        }
        return filmTitles;
    }

    public ArrayList<Double> getFilmRating() {
        for (Films x : filmList) {
            filmRating.add(x.getRating());
        }
        return filmRating;
    }

    public ArrayList<Films> sortZtoA() {
        ArrayList<Films> sortedList = new ArrayList<>();
        Collections.sort(this.filmTitles, Collections.reverseOrder());
        for (String i : filmTitles) {
            for (Films j : filmList) {
                if (i.equals(j.getTitle())) {
                    sortedList.add(j);
                }
            }
        }
        return sortedList;
    }

    public ArrayList<Films> sortAtoZ() {
        ArrayList<Films> sortedList = new ArrayList<>();
        Collections.sort(this.filmTitles);
        for (String i : filmTitles) {
            for (Films j : filmList) {
                if (i.equals(j.getTitle())) {
                    sortedList.add(j);
                }
            }
        }
        return sortedList;
    }

    public ArrayList<Films> sortRatingHigh() {
        ArrayList<Films> sortedList = new ArrayList<>();
        Collections.sort(this.filmRating, Collections.reverseOrder());
        for (double i : filmRating) {
            for (Films j : filmList) {
                if (i == j.getRating()) {
                    sortedList.add(j);
                }
            }
        }
        return sortedList;
    }

    public ArrayList<Films> sortRatingLow() {
        ArrayList<Films> sortedList = new ArrayList<>();
        Collections.sort(this.filmRating);
        for (double i : filmRating) {
            for (Films j : filmList) {
                if (i == j.getRating()) {
                    sortedList.add(j);
                }
            }
        }
        return sortedList;
    }

    public ArrayList<Films> sortReleaseDateOld() throws ParseException {
        ArrayList<Films> sortedList = new ArrayList<>();
        Collections.sort(this.filmDates);
        for (Date i : filmDates) {
            for (Films j : filmList) {
                Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(j.getReleaseDate());
                if (i.equals(date1)) {
                    sortedList.add(j);
                }
            }
        }
        return sortedList;
    }

    public ArrayList<Films> sortReleaseDateNew() throws ParseException {
        ArrayList<Films> sortedList = new ArrayList<>();
        Collections.sort(this.filmDates, Collections.reverseOrder());
        for (Date i : filmDates) {
            for (Films j : filmList) {
                Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(j.getReleaseDate());
                if (i.equals(date1)) {
                    sortedList.add(j);
                }
            }
        }
        return sortedList;
    }
}

