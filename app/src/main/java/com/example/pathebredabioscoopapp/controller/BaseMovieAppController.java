package com.example.pathebredabioscoopapp.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public abstract class  BaseMovieAppController {

    public static final String BASE_URL = "https://api.themoviedb.org/3/";
    public static final String BASE_POSTER_PATH_URL = "https://image.tmdb.org/t/p/w500";
    public static final String API_KEY = "90104c23f74fdca587142d076b5df361\n";
    public static final String SESSION_ID = "db55b43e42578d56dabbe2e110797041090fc6e7";

    protected final Retrofit retrofit;
    protected final Gson gson;

    public BaseMovieAppController() {
        gson = new GsonBuilder()
                .setLenient()
                .create();

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

    }
}
