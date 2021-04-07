package com.example.pathebredabioscoopapp.logic;

import android.os.AsyncTask;

import java.io.IOException;

import okhttp3.Headers;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class NewRatingReviewTask extends AsyncTask<String, Void, Void> {
    private final OkHttpClient client = new OkHttpClient();
    private String value;
    private String listNumber;
    private String API_URL;

    @Override
    protected Void doInBackground(String... strings) {
        value = strings[0];
        listNumber = strings[1];
        API_URL = "https://api.themoviedb.org/3/movie/" + listNumber + "/rating?api_key=90104c23f74fdca587142d076b5df361&session_id=db55b43e42578d56dabbe2e110797041090fc6e7";
        makePost(value);
        return null;
    }

    private void makePost(String value) {
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("value", value)
                .build();

        Request request = new Request.Builder()
                .url(API_URL)
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
