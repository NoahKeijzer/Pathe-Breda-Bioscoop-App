package com.example.pathebredabioscoopapp.logic;

import android.util.Log;

import com.example.pathebredabioscoopapp.domain.Actors;
import com.example.pathebredabioscoopapp.domain.Films;
import com.example.pathebredabioscoopapp.domain.Reviews;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;

public class JSONConverter {
    private final String TAG = getClass().getSimpleName();
    private String response = "";
    private final String ID_FILM = "id";
    private static final String TITLE_FILM = "title";
    private static final String POSTER_PATH = "poster_path";
    private static final String OVERVIEW_FILM = "overview";
    private static final String RELEASE_DATE = "release_date";
    private static final String RATING_FILM = "vote_average";
    private static final String GENRE_FILM = "name";
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
    private String url_details = "https://api.themoviedb.org/3/movie/" + ID_FILM + "464052?api_key=90104c23f74fdca587142d076b5df361&language=en-US";
    private String url_review ="https://api.themoviedb.org/3/movie/"+ ID_FILM + "/reviews?api_key=90104c23f74fdca587142d076b5df361&language=en-US&page=1";
    private String url_cast = "https://api.themoviedb.org/3/movie/"+ ID_FILM + "399566/credits?api_key=90104c23f74fdca587142d076b5df361&language=en-US";

    ArrayList<Reviews> reviews;


    private int duration;
    private String director;
    private String trailer;

    public JSONConverter(String response){
        this.response = response;
    }

    public ArrayList<Films> convertMovieList(){
        return null;
    }

    public ArrayList<Films> convertFilm() {
        Log.d(TAG, "convertJsonToArrayList is aangeroepen");
        ArrayList<Films> results = new ArrayList<>();

        try {
            JSONObject filmsResult = new JSONObject(response);
            JSONArray filmsList = filmsResult.getJSONArray("results");

            for (int i = 0; i < filmsList.length(); i++) {

                JSONObject film = filmsList.getJSONObject(i);
                String title = film.getString(TITLE_FILM);
                String imgUrl = film.getString(POSTER_PATH);
                String overview = film.getString(OVERVIEW_FILM);
                String release = film.getString(RELEASE_DATE);
                double rating = film.getDouble(RATING_FILM);
                int id = film.getInt(ID_FILM);
                results.add(new Films(id, title, imgUrl, overview, release, rating));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return results;
    }

    public String convertGenreFilm() {
        Log.d(TAG, "convertJsonToArrayList Genre is aangeroepen");
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
        Log.d(TAG, "convertJsonToArrayList Actors is aangeroepen");
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
                actors.add(new Actors(id, name, picturePath, character));
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
                int id = reviewJSONObject.getInt(REVIEW_ID);
                reviews.add(new Reviews(id, rating, name, authorUsername, content));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return reviews;
    }
}

