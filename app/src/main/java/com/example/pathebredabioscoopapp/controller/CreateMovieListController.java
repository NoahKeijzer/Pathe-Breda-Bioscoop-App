package com.example.pathebredabioscoopapp.controller;

import android.util.Log;

import com.example.pathebredabioscoopapp.domain.FilmList;
import com.example.pathebredabioscoopapp.domain.Films;
import com.example.pathebredabioscoopapp.logic.CreateFilmListAPIResponse;
import com.example.pathebredabioscoopapp.logic.FilmAPITask;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 *
 */
public class CreateMovieListController
        extends BaseMovieAppController
        implements Callback<CreateFilmListAPIResponse> {

    private final String LOG_TAG = this.getClass().getSimpleName();
    private FilmList newFilmList = null;
    private MovieListsController.MovieListsControllerListener listener;
    private final FilmAPITask theMovieDbAPI;

    public CreateMovieListController(MovieListsController.MovieListsControllerListener listener) {
        super();
        this.listener = listener;
        theMovieDbAPI = retrofit.create(FilmAPITask.class);
    }

    public void createMovieList(int id, String name, boolean statusPublic, ArrayList<Films> filmList) {
        this.newFilmList = new FilmList(id, name, statusPublic, filmList);
        Call<CreateFilmListAPIResponse> call = theMovieDbAPI.createFilmList(newFilmList);
        call.enqueue(this);
    }

    @Override
    public void onResponse(
            Call<CreateFilmListAPIResponse> call,
            Response<CreateFilmListAPIResponse> response) {
        Log.d(LOG_TAG, "onResponse() - statuscode: " + response.code());

        if(response.isSuccessful()) {
            Log.d(LOG_TAG, "response: " + response.body());

            // Deserialization
            int filmListId = response.body().getList_id();
            Log.d(LOG_TAG, "ListId = " + filmListId);
            newFilmList.setId(filmListId);

            // We kunnen hier opnieuw een request versturen om de hele lijst van
            // MovieLists nogmaals op te halen, maar handiger is om de lijst die we
            // hebben gemaakt met de ID aan de bestaande lijst toe te voegen.
            // Dat scheelt een request. Risico is wel dat we ergens de synchronisatie
            // met de backend server verliezen.
            listener.onMovieListCreated(newFilmList);
        } else {
            Log.e(LOG_TAG, "Not successful! Message: " + response.message());
        }
    }

    @Override
    public void onFailure(Call<CreateFilmListAPIResponse> call, Throwable t) {

    }


    public interface MovieControllerListener {
        public void onFilmsAvailable(ArrayList<Films> movies);
    }
}
