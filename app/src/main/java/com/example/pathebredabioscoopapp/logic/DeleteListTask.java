package com.example.pathebredabioscoopapp.logic;

import android.os.AsyncTask;

import com.example.pathebredabioscoopapp.domain.FilmList;
import com.example.pathebredabioscoopapp.domain.Films;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import okhttp3.Headers;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class DeleteListTask extends AsyncTask<Integer, Void, Void> {
    private final OkHttpClient client = new OkHttpClient();
    private ListAdapter listAdapter;
    private int filmListId;

    public DeleteListTask(FilmList filmlist, ListAdapter listAdapter) {
        this.filmListId = filmlist.getId();
        this.listAdapter = listAdapter;
    }

    @Override
    protected Void doInBackground(Integer... integers) {
        try {
            makeDelete(filmListId);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void makeDelete(int filmListId) throws IOException {
        URL url = new URL("https://api.themoviedb.org/3/list/"+ filmListId +"?api_key=90104c23f74fdca587142d076b5df361&session_id=db55b43e42578d56dabbe2e110797041090fc6e7");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("DELETE");
        int responseCode = connection.getResponseCode();
        listAdapter.notifyDataSetChanged();
    }
}