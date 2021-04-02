package com.example.pathebredabioscoopapp.logic;

import android.os.AsyncTask;
import com.example.pathebredabioscoopapp.domain.Films;

import java.util.ArrayList;

public class FilmAPITask extends AsyncTask<String, Void, ArrayList<Character>> {
    private final String TAG = getClass().getSimpleName();
    private FilmListener filmListener;

    public FilmAPITask(FilmListener filmListener) {
        this.filmListener = filmListener;
    }

    @Override
    protected ArrayList<Character> doInBackground(String... strings) {
        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(ArrayList<Character> characters) {
        super.onPostExecute(characters);
    }

    public interface FilmListener {
       void onFilmsListAvailable(ArrayList<Films> filmList);
    }
}
