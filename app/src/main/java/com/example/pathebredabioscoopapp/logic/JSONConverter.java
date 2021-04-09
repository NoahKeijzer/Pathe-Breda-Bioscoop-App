package com.example.pathebredabioscoopapp.logic;

import android.util.Log;

import com.example.pathebredabioscoopapp.domain.Actors;
import com.example.pathebredabioscoopapp.domain.FilmList;
import com.example.pathebredabioscoopapp.domain.Films;
import com.example.pathebredabioscoopapp.domain.Reviews;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;

public class JSONConverter implements Serializable {
    private final String TAG = getClass().getSimpleName();
    private String response = "";
    private final String ID_FILM = "id";
    private static final String TITLE_FILM = "title";
    private static final String POSTER_PATH = "poster_path";
    private static final String OVERVIEW_FILM = "overview";
    private static final String RELEASE_DATE = "release_date";
    private static final String RATING_FILM = "vote_average";
    private static final String GENRE_FILM = "name";
    private static final String DURATION_FILM = "runtime";
    private static final String TRAILER_FILM = "homepage";
    private static final String ACTOR_NAME = "name";
    private static final String ACTOR_PICTURE = "profile_path";
    private static final String ACTOR_CHARACTER = "character";
    private static final String ACTOR_ID = "id";
    private static final String REVIEW_NAME = "author";
    private static final String REVIEW_CONTENT = "content";
    private static final String REVIEW_USERNAME = "username";
    private static final String REVIEW_DETAILS = "author_details";
    private static final String REVIEW_ID = "id";
    private static final String REVIEW_RATING = "rating";
    private static final String JSON_FILMLIST_ID = "id";
    private static final String JSON_FILMLIST_NAME = "name";
    private static final String delimterResults = "results";

    public JSONConverter(String response) {
        this.response = response;
    }

    public ArrayList<FilmList> convertFilmList() {
        ArrayList<FilmList> filmLists = new ArrayList<>();
        try {
            JSONObject responseObject = new JSONObject(response);
            JSONArray responseResultSet = responseObject.getJSONArray(delimterResults);
            for (int s = 0; s < responseResultSet.length(); s++) {
                JSONObject resultSetObject = responseResultSet.getJSONObject(s);
                int id = resultSetObject.getInt(JSON_FILMLIST_ID);
                String name = resultSetObject.getString(JSON_FILMLIST_NAME);
                ArrayList<Films> filmList = new ArrayList<>();
                filmLists.add(new FilmList(id, name, filmList));
            }
        } catch (JSONException e) {
   //         Log.d(TAG, "Geen JSON object");
            e.printStackTrace();
        }
        return filmLists;
    }

    public ArrayList<Films> convertFilm() {
 //       Log.d(TAG, "convertJsonToArrayList is aangeroepen");
        ArrayList<Films> results = new ArrayList<>();

        try {
            JSONObject filmsResult = new JSONObject(response);
            JSONArray filmsList = filmsResult.getJSONArray("results");

            for (int i = 0; i < filmsList.length(); i++) {

                JSONObject film = filmsList.getJSONObject(i);
                int id = film.getInt(ID_FILM);
                String title = film.getString(TITLE_FILM);
                String overview = film.getString(OVERVIEW_FILM);
                String imgUrl = film.getString(POSTER_PATH);
                String release = film.getString(RELEASE_DATE);
                double rating = film.getDouble(RATING_FILM);

                results.add(new Films(id, title, imgUrl, overview, release, rating));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return results;
    }

    public ArrayList<Films> convertPersonalList() {
 //       Log.d(TAG, "convertJsonToArrayList is aangeroepen");
        ArrayList<Films> results = new ArrayList<>();

        try {
            JSONObject filmsResult = new JSONObject(response);
            JSONArray filmsList = filmsResult.getJSONArray("items");

            for (int i = 0; i < filmsList.length(); i++) {

                JSONObject film = filmsList.getJSONObject(i);
                int id = film.getInt(ID_FILM);
                String overview = film.getString(OVERVIEW_FILM);
                String imgUrl = film.getString(POSTER_PATH);
                String release = film.getString(RELEASE_DATE);
                String title = film.getString(TITLE_FILM);
                double rating = film.getDouble(RATING_FILM);

                results.add(new Films(id, title, imgUrl, overview, release, rating));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return results;
    }

    public String convertGenreFilm() {
 //       Log.d(TAG, "convertJsonToArrayList Genre is aangeroepen");
        String genre = "";
        try {
            JSONObject genreResult = new JSONObject(response);
            JSONArray genreList = genreResult.getJSONArray("genres");

            for (int i = 0; i < 1; i++) {
                JSONObject genres = genreList.getJSONObject(i);
                genre = genres.getString(GENRE_FILM);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return genre;
    }

    public ArrayList<Actors> convertActorsFilm() {
 //       Log.d(TAG, "convertJsonToArrayList Actors is aangeroepen");
        ArrayList<Actors> actors = new ArrayList<>();

        try {
            JSONObject actorsResult = new JSONObject(response);
            JSONArray actorsList = actorsResult.getJSONArray("cast");

            for (int i = 0; i < 3; i++) {
                JSONObject actorsJSON = actorsList.getJSONObject(i);
                String name = actorsJSON.getString(ACTOR_NAME);
                String picturePath = actorsJSON.getString(ACTOR_PICTURE);
                String character = actorsJSON.getString(ACTOR_CHARACTER);
                int id = actorsJSON.getInt(ACTOR_ID);
                actors.add(new Actors(id, name, character, picturePath));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return actors;
    }

    public ArrayList<Reviews> convertReviewFilm() {
        ArrayList<Reviews> reviews = new ArrayList<>();

        try {
            JSONObject reviewsResult = new JSONObject(response);
            JSONArray reviewList = reviewsResult.getJSONArray("results");

            for (int i = 0; i < reviewList.length(); i++) {
                JSONObject reviewJSONObject = reviewList.getJSONObject(i);
                String name = reviewJSONObject.getString(REVIEW_NAME);
                JSONObject jsonAuthorDetails = reviewJSONObject.getJSONObject(REVIEW_DETAILS);
                String authorUsername = jsonAuthorDetails.getString(REVIEW_USERNAME);
                double rating = jsonAuthorDetails.getDouble(REVIEW_RATING);
                String content = reviewJSONObject.getString(REVIEW_CONTENT);
                String id = reviewJSONObject.getString(REVIEW_ID);
                reviews.add(new Reviews(id, rating, name, authorUsername, content));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return reviews;
    }

    public String convertDirectorFilm() {
        String nameDirector = "";
        try {
            JSONObject actorsResult = new JSONObject(response);
            JSONArray crewList = actorsResult.getJSONArray("crew");

            for (int i = 0; i < crewList.length(); i++) {
                JSONObject actorsJSON = crewList.getJSONObject(i);
                String job = actorsJSON.getString("job");
                if (job.equals("Director")) {
                    nameDirector =  actorsJSON.getString("name");
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return nameDirector;
    }

    public int convertDuration(){
        int duration = 0;
        try {
            JSONObject genreResult = new JSONObject(response);
            duration =genreResult.getInt(DURATION_FILM);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return duration;
    }

    public String convertTrailer(){
        String trailer = "";
        try {
            JSONObject trailerResult = new JSONObject(response);
            trailer = trailerResult.getString(TRAILER_FILM);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return trailer;
    }
}

