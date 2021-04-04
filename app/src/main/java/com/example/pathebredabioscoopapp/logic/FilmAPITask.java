package com.example.pathebredabioscoopapp.logic;

import android.os.AsyncTask;
import android.util.Log;

import com.example.pathebredabioscoopapp.domain.FilmList;
import com.example.pathebredabioscoopapp.domain.Films;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

import retrofit2.Call;

public class FilmAPITask extends AsyncTask<String, Void, ArrayList<Films>> {
    private final String TAG = getClass().getSimpleName();
    private FilmListener filmListener;

    public FilmAPITask(FilmListener filmListener) {
        this.filmListener = filmListener;

    }

    @Override
    protected ArrayList<Films> doInBackground(String... strings) {
        return null;
    }
    
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(ArrayList<Films> film) {
        super.onPostExecute(film);
    }


    public interface FilmListener {
       void onFilmsListAvailable(ArrayList<Films> filmList);
    }
}
