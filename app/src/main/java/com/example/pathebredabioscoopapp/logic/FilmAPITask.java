package com.example.pathebredabioscoopapp.logic;

import android.os.AsyncTask;
import android.util.Log;

import com.example.pathebredabioscoopapp.domain.Actors;
import com.example.pathebredabioscoopapp.domain.FilmList;
import com.example.pathebredabioscoopapp.domain.Films;
import com.example.pathebredabioscoopapp.domain.Reviews;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

import retrofit2.Call;

public class FilmAPITask extends AsyncTask<String, Void, ArrayList<Films>> implements Serializable {
    public static final String API_KEY = "90104c23f74fdca587142d076b5df361";
    public static final String BASE_URL = "https://api.themoviedb.org/3/";
    private final String TAG = getClass().getSimpleName();
    private int id = 0;
    private JSONConverter jsonConverter;
    private FilmListener filmListener;

    public FilmAPITask(FilmListener filmListener) {
        this.filmListener = filmListener;
    }

    public FilmAPITask(FilmListener filmListener, int id) {
        this.filmListener = filmListener;
        this.id = id;
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

    public String stringRequestAllPersonalMovies(int id) {
        return BASE_URL + "list/" + id + "?api_key=" + API_KEY + "&language=en-US&page=1";
    }

    @Override
    protected ArrayList<Films> doInBackground(String... strings) {
 //       Log.d(TAG, "doInBackground is aangeroepen");
        ArrayList<Films> resultList = new ArrayList<>();
        //Create/get URL
        HttpURLConnection urlConnection = null;
        URL urlGetMovie = null;

        try {
            if(id == 0){
                urlGetMovie = new URL(stringRequestAllMovies());
            }else{
                urlGetMovie = new URL(stringRequestAllPersonalMovies(id));
            }
            //Request bouwen en versturen
            urlConnection = (HttpURLConnection) urlGetMovie.openConnection();
            InputStream in = urlConnection.getInputStream();
            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                String response = scanner.next();
                //      Log.d(TAG, "response: " + response);
                jsonConverter = new JSONConverter(response);
                if(id == 0){
                    resultList = jsonConverter.convertFilm();
                }else{
                    resultList = jsonConverter.convertPersonalList();
                }
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

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(ArrayList<Films> films) {
        super.onPostExecute(films);
        filmListener.onFilmsListAvailable(films);

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
                   // Log.d(TAG, "response: " + response);
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
//                    Log.d(TAG, "response: " + response);
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

    public interface FilmListener {
        void onFilmsListAvailable(ArrayList<Films> filmList);
    }
}
