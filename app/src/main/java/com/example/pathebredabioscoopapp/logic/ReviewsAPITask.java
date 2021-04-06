package com.example.pathebredabioscoopapp.logic;

import android.os.AsyncTask;
import android.util.Log;

import com.example.pathebredabioscoopapp.domain.Films;
import com.example.pathebredabioscoopapp.domain.Reviews;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

import static com.example.pathebredabioscoopapp.logic.FilmAPITask.API_KEY;
import static com.example.pathebredabioscoopapp.logic.FilmAPITask.BASE_URL;

public class ReviewsAPITask extends AsyncTask<String, Void, ArrayList<Reviews>> {
    private final String TAG = getClass().getSimpleName();
    private Films film;
    private ReviewsListener listener;

    public ReviewsAPITask(ReviewsListener listener, Films film) {
        this.listener = listener;
        this.film = film;
    }

    public String stringRequestReviewsMovies(int id) {
        return BASE_URL + "movie/" + film.getId() + "/reviews?api_key=" + API_KEY + "&language=en-US&page=1";
    }

    @Override
    protected ArrayList<Reviews> doInBackground(String... strings) {
        Log.d(TAG, "doInBackground is aangeroepen");
        //Create/get URL
        HttpURLConnection urlConnection = null;
        URL urlGetMovie = null;
        // Id via intent meegeven aan de ReviewsActivity en als parameter geven aan de AsyncTask
        int id = 399566;

        try {
            urlGetMovie = new URL(stringRequestReviewsMovies(id));
            //Request bouwen en versturen
            urlConnection = (HttpURLConnection) urlGetMovie.openConnection();
            InputStream in = urlConnection.getInputStream();
            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                String response = scanner.next();
                Log.d(TAG, "response: " + response);
                JSONConverter jsonConverter = new JSONConverter(response);
                ArrayList<Reviews> resultList = jsonConverter.convertReviewFilm();
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
    protected void onPostExecute(ArrayList<Reviews> reviews) {
        super.onPostExecute(reviews);
        listener.onReviewAvailable(reviews);

    }

    public interface ReviewsListener {
        void onReviewAvailable(ArrayList<Reviews> reviewsList);
    }
}

