package com.example.pathebredabioscoopapp.controller;

import android.util.Log;

import com.example.pathebredabioscoopapp.domain.Films;
import com.example.pathebredabioscoopapp.logic.FilmAPI;
import com.example.pathebredabioscoopapp.logic.FilmAPIResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 *
 */
public class MovieController
        extends BaseMovieAppController
        implements Callback<FilmAPIResponse> {

    private final String LOG_TAG = this.getClass().getSimpleName();

    private MovieControllerListener listener;
    private final FilmAPI movieAPI;

    public MovieController(MovieControllerListener listener) {
        super();
        this.listener = listener;
        movieAPI = retrofit.create(FilmAPI.class);
    }

    public void loadTrendingMoviesPerWeek(int pageNr) {
        Call<FilmAPIResponse> call = movieAPI.loadTrendingMoviesByWeek(pageNr);
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<FilmAPIResponse> call, Response<FilmAPIResponse> response) {
        Log.d(LOG_TAG, "onResponse() - statuscode: " + response.code());

        if(response.isSuccessful()) {
            Log.d(LOG_TAG, "response: " + response.body());

            // Deserialization
            ArrayList<Films> films = response.body().getResults();
            listener.onFilmsAvailable(films);
        } else {
            Log.e(LOG_TAG, "Not successful! Message: " + response.message());
        }
    }

    @Override
    public void onFailure(@NotNull Call<FilmAPIResponse> call, Throwable t) {
        Log.e(LOG_TAG, "onFailure - " + t.getMessage());
        listener.onError(t.getMessage());
    }

    public interface MovieControllerListener {
        void onFilmsAvailable(ArrayList<Films> movies);
        void onError(String message);
    }
}
