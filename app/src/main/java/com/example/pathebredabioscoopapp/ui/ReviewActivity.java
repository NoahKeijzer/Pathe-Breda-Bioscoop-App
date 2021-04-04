package com.example.pathebredabioscoopapp.ui;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.pathebredabioscoopapp.domain.Reviews;
import com.example.pathebredabioscoopapp.logic.ReviewsAPITask;

import java.util.ArrayList;

public class ReviewActivity extends AppCompatActivity implements ReviewsAPITask.ReviewsListener {
    private ArrayList<Reviews> reviews;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new ReviewsAPITask(this::onReviewAvailable).execute();
    }

    @Override
    public void onReviewAvailable(ArrayList<Reviews> reviewsList) {
        this.reviews.addAll(reviewsList);
    }
}
