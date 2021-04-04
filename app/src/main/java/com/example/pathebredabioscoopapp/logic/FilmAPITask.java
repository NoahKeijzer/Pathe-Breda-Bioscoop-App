package com.example.pathebredabioscoopapp.logic;

import android.os.AsyncTask;
import android.util.Log;

import com.example.pathebredabioscoopapp.domain.Actors;
import com.example.pathebredabioscoopapp.domain.FilmList;
import com.example.pathebredabioscoopapp.domain.Films;
import com.example.pathebredabioscoopapp.domain.Reviews;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

import retrofit2.Call;

public class FilmAPITask extends AsyncTask<String, Void, ArrayList<Films>> {
    public static final String API_KEY = "90104c23f74fdca587142d076b5df361";
    public static final String BASE_URL = "https://api.themoviedb.org/3/";
    public static final String BASE_POSTER_PATH_URL = "https://image.tmdb.org/t/p/w500";
    public static final String SESSION_ID = "db55b43e42578d56dabbe2e110797041090fc6e7";
    private final String TAG = getClass().getSimpleName();
    private JSONConverter jsonConverter;
    private FilmListener filmListener;

    public FilmAPITask(FilmListener filmListener) {
        this.filmListener = filmListener;
    }

    public String stringRequestAllMovies() {
        return BASE_URL + "movie/popular?api_key=" + API_KEY + "&language=en-US&page=1";
    }

    public String stringRequestTrendingMovies() {
        return BASE_URL + "trending/movie/week?api_key=" + API_KEY;
    }

    public String stringRequestActorsFilm(int id) {
        return BASE_URL + "movie/" + id + "/credits?api_key=" + API_KEY + "&language=en-US";
    }

    public String stringRequestGetGenreFilm(int id) {
        return BASE_URL + "movie/" + id + "?api_key=" + API_KEY + "&language=en-US";
    }

    @Override
    protected ArrayList<Films> doInBackground(String... strings) {
        Log.d(TAG, "doInBackground is aangeroepen");
        //Create/get URL
        HttpURLConnection urlConnection = null;
        URL urlGetMovie = null;

        try {
            urlGetMovie = new URL(stringRequestAllMovies());
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

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(ArrayList<Films> films) {
        super.onPostExecute(films);
    }

    public interface FilmListener {
        void onFilmsListAvailable(ArrayList<Films> filmList);
    }
}
