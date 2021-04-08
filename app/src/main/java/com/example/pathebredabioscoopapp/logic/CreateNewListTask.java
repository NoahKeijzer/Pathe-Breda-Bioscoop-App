package com.example.pathebredabioscoopapp.logic;

import android.os.AsyncTask;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Headers;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.OkHttpClient;



public class CreateNewListTask extends AsyncTask<String, Void, Void> {
    private final OkHttpClient client = new OkHttpClient();

    @Override
    protected Void doInBackground(String... strings) {
        makePost(strings[0],strings[1]);
        return null;
    }

    private void makePost(String title, String description) {
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("name", title)
                .addFormDataPart("description", description)
                .build();

        Request request = new Request.Builder()
                .url("https://api.themoviedb.org/3/list?api_key=90104c23f74fdca587142d076b5df361&session_id=db55b43e42578d56dabbe2e110797041090fc6e7")
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
