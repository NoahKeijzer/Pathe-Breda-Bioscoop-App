package com.example.pathebredabioscoopapp.logic;

import android.os.AsyncTask;

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

import static com.example.pathebredabioscoopapp.logic.FilmAPITask.API_KEY;
import static com.example.pathebredabioscoopapp.logic.FilmAPITask.BASE_URL;

public class FilmListAPITask extends AsyncTask<String, Void, ArrayList<FilmList>> {
    public static final String SESSION_ID = "db55b43e42578d56dabbe2e110797041090fc6e7";
    public static final String ACCOUNT_ID = "10255531";

    private FilmListListener listener = null;

    public String stringRequestUserLists(){
        return BASE_URL + "account/" + ACCOUNT_ID + "/lists?api_key=" + API_KEY + "&language=en-US&session_id=" + SESSION_ID;
    }

    public FilmListAPITask(FilmListListener filmListener) {
        this.listener = filmListener;
    }

    @Override
    protected ArrayList<FilmList> doInBackground(String... strings) {
        ArrayList<FilmList> filmLists = new ArrayList<>();

        HttpURLConnection urlConnection = null;
        try {
            URL url = new URL(stringRequestUserLists());
            urlConnection = (HttpURLConnection) url.openConnection();
            InputStream in = urlConnection.getInputStream();
            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            if (scanner.hasNext()) {
                String response = scanner.next();
                JSONConverter jsonConverter = new JSONConverter(response);
                return filmLists = jsonConverter.convertFilmList();
            } else {
                return null;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != urlConnection) {
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
    protected void onPostExecute(ArrayList<FilmList> filmLists) {
        super.onPostExecute(filmLists);
        listener.onFilmListReady(filmLists);
    }

    public interface FilmListListener {
        public void onFilmListReady(ArrayList<FilmList> FilmLists);
    }
}
