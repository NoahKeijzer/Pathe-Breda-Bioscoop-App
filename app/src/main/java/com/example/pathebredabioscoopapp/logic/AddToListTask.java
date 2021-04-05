package com.example.pathebredabioscoopapp.logic;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.pathebredabioscoopapp.domain.FilmList;
import com.example.pathebredabioscoopapp.domain.Films;
import com.example.pathebredabioscoopapp.ui.AllListsActivity;

import java.io.IOException;

import okhttp3.Headers;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class AddToListTask extends AsyncTask<Integer, Void, Void>  {
    private final OkHttpClient client = new OkHttpClient();
    private FilmAdapter filmAdapter;
    private int filmId;
    private int filmListId;

    public AddToListTask(Films film, FilmList filmlist, FilmAdapter filmAdapter) {
        this.filmId = film.getId();
        this.filmListId = filmlist.getId();
        this.filmAdapter = filmAdapter;
    }

    @Override
    protected Void doInBackground(Integer... integers) {
        makePost(filmListId);
        return null;
    }

    private void makePost(int filmListId) {
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("media_id", String.valueOf(filmId))
                .build();

        Request request = new Request.Builder()
                .url("https://api.themoviedb.org/3/list/"+ filmListId +"/add_item?api_key=90104c23f74fdca587142d076b5df361&session_id=db55b43e42578d56dabbe2e110797041090fc6e7")
                .post(requestBody)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

            Headers responseHeaders = response.headers();
            for (int i = 0; i < responseHeaders.size(); i++) {
                System.out.println(responseHeaders.name(i) + ": " + responseHeaders.value(i));
            }
            System.out.println(response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
