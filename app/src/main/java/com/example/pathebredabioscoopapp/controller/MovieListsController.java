package com.example.pathebredabioscoopapp.controller;

import android.util.Log;

import com.example.pathebredabioscoopapp.domain.FilmList;
import com.example.pathebredabioscoopapp.logic.FilmAPI;
import com.example.pathebredabioscoopapp.logic.FilmListAPIResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 *
 */
public class MovieListsController implements Callback<FilmListAPIResponse> {

    private final String LOG_TAG = this.getClass().getSimpleName();
    public static final String BASE_URL = "https://api.themoviedb.org/3/";

    private MovieListsControllerListener listener;

    private final Retrofit retrofit;
    private final Gson gson;
    private final FilmAPI movieAPI;

    public MovieListsController(MovieListsControllerListener listener) {
        this.listener = listener;

        gson = new GsonBuilder()
                .setLenient()
                .create();

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        movieAPI = retrofit.create(FilmAPI.class);
    }

    public void loadMovieListsForUser() {
        Call<FilmListAPIResponse> call = movieAPI.loadMovieListsForUser();
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<FilmListAPIResponse> call, Response<FilmListAPIResponse> response) {
        Log.d(LOG_TAG, "onResponse() - statuscode: " + response.code());

        if(response.isSuccessful()) {
            Log.d(LOG_TAG, "response: " + response.body());

            // Deserialization
            List<FilmList> movieLists = response.body().getResults();
            listener.onMovieListsAvailable(movieLists);
        } else {
            Log.e(LOG_TAG, "Not successful! Message: " + response.message());
        }
    }

    @Override
    public void onFailure(@NotNull Call<FilmListAPIResponse> call, Throwable t) {
        Log.e(LOG_TAG, "onFailure - " + t.getMessage());
    }

    /**
     *
     */
    public interface MovieListsControllerListener {
        void onMovieListsAvailable(List<FilmList> movieLists);
        void onMovieListCreated(FilmList newMovieList);
    }
}
