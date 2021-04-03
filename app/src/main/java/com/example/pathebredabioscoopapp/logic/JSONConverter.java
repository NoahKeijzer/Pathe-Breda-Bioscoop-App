package com.example.pathebredabioscoopapp.logic;

import android.util.Log;

import com.example.pathebredabioscoopapp.domain.Actors;
import com.example.pathebredabioscoopapp.domain.FilmList;
import com.example.pathebredabioscoopapp.domain.Films;
import com.example.pathebredabioscoopapp.domain.Reviews;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JSONConverter {
    private final String TAG = getClass().getSimpleName();
    private ArrayList<Films> filmList;
    private String response;

    private final String JSON_FILMLIST_ID = "id";
    private final String JSON_FILMLIST_NAME = "name";
    private final String JSON_FILMLIST_LISTS = "";


    private String delimterResults = "results";

    public JSONConverter(String response){
        this.response = response;
    }

    public ArrayList<FilmList> convertFilmList(){
        ArrayList<FilmList> filmLists = new ArrayList<>();

        try {
            JSONObject responseObject = new JSONObject(response);
            JSONArray responseResultSet = responseObject.getJSONArray(delimterResults);

            for (int s = 0; s <responseResultSet.length(); s++){
                JSONObject resultSetObject = responseResultSet.getJSONObject(s);
                int id = resultSetObject.getInt(JSON_FILMLIST_ID);
                String name = resultSetObject.getString(JSON_FILMLIST_NAME);
                ArrayList<Films> filmList = new ArrayList<>();

                filmLists.add(new FilmList(id,name,filmList));
            }
        } catch (JSONException e) {
            Log.d(TAG, "Geen JSON object");
            e.printStackTrace();
        }
        return filmLists;
    }


    public ArrayList<Films> convertMovieList(){
        return null;
    }

    public ArrayList<Actors> convertActor(){
        return null;
    }

    public ArrayList<Films> convertFilm(){
        return null;
    }

    public ArrayList<Reviews> convertReview(){
        return null;
    }


}

