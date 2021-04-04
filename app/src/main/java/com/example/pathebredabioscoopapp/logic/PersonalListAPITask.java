package com.example.pathebredabioscoopapp.logic;

import android.os.AsyncTask;
import android.util.Log;

import com.example.pathebredabioscoopapp.domain.Actors;
import com.example.pathebredabioscoopapp.domain.Films;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

import static com.example.pathebredabioscoopapp.logic.FilmAPITask.API_KEY;
import static com.example.pathebredabioscoopapp.logic.FilmAPITask.BASE_URL;

public class PersonalListAPITask extends AsyncTask<String, Void, ArrayList<Films>> implements Serializable {
    private final String TAG = getClass().getSimpleName();
    private JSONConverter jsonConverter;
    private FilmAPITask.FilmListener filmListener;
    private int id;

    public PersonalListAPITask(FilmAPITask.FilmListener filmListener, int id) {
        this.filmListener = filmListener;
        this.id = id;
    }

    public String stringRequestActorsFilm(int id) {
        return BASE_URL + "movie/" + id + "/credits?api_key=" + API_KEY + "&language=en-US";
    }

    public String stringRequestGetGenreFilm(int id) {
        return BASE_URL + "movie/" + id + "?api_key=" + API_KEY + "&language=en-US";
    }

    public String stringRequestAllMovies(int id) {
        return BASE_URL + "list/" + id + "?api_key=" + API_KEY + "&language=en-US&page=1";
    }

    @Override
    protected ArrayList<Films> doInBackground(String... strings) {
        Log.d(TAG, "doInBackground is aangeroepen");
        //Create/get URL
        HttpURLConnection urlConnection = null;
        URL urlGetMovie = null;

        try {
            urlGetMovie = new URL(stringRequestAllMovies(id));
            //Request bouwen en versturen
            urlConnection = (HttpURLConnection) urlGetMovie.openConnection();
            InputStream in = urlConnection.getInputStream();
            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                String response = scanner.next();
                Log.d(TAG, "response: " + response);
                jsonConverter = new JSONConverter(response);
                ArrayList<Films> resultList = jsonConverter.convertFilm();
                getDetailsFilm(resultList);
                getActorsFilm(resultList);
                return resultList;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }
        return null;
    }

    public void getDetailsFilm(ArrayList<Films> resultList) {
        for (Films x : resultList) {
            HttpURLConnection urlConnection = null;
            URL urlGetGenreFilm = null;

            try {
                urlGetGenreFilm = new URL(stringRequestGetGenreFilm(x.getId()));
                urlConnection = (HttpURLConnection) urlGetGenreFilm.openConnection();
                InputStream in = urlConnection.getInputStream();
                Scanner scanner = new Scanner(in);
                scanner.useDelimiter("\\A");

                if (scanner.hasNext()) {
                    String response = scanner.next();
                    Log.d(TAG, "response: " + response);
                    JSONConverter jsonConverter = new JSONConverter(response);
                    String genre = jsonConverter.convertGenreFilm();
                    String trailer = jsonConverter.convertTrailer();
                    int duration = jsonConverter.convertDuration();
                    x.setGenre(genre);
                    x.setDuration(duration);
                    x.setTrailer(trailer);
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
            }
        }
    }

    public void getActorsFilm(ArrayList<Films> resultList) {
        for (Films x : resultList) {
            HttpURLConnection urlConnection = null;
            URL urlGetMovieDetails = null;

            try {
                urlGetMovieDetails = new URL(stringRequestActorsFilm(x.getId()));
                urlConnection = (HttpURLConnection) urlGetMovieDetails.openConnection();
                InputStream in = urlConnection.getInputStream();
                Scanner scanner = new Scanner(in);
                scanner.useDelimiter("\\A");

                if (scanner.hasNext()) {
                    String response = scanner.next();
                    Log.d(TAG, "response: " + response);
                    JSONConverter jsonConverter = new JSONConverter(response);
                    ArrayList<Actors> actors = jsonConverter.convertActorsFilm();
                    x.setActors(actors);
                    String director = jsonConverter.convertDirectorFilm();
                    x.setDirector(director);

                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
            }
        }
    }
}

