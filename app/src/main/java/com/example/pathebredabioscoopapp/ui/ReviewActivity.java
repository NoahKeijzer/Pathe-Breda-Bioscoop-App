package com.example.pathebredabioscoopapp.ui;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.pathebredabioscoopapp.R;
import com.example.pathebredabioscoopapp.domain.Actors;
import com.example.pathebredabioscoopapp.domain.Films;
import com.example.pathebredabioscoopapp.domain.Reviews;
import com.example.pathebredabioscoopapp.logic.FilmAdapter;
import com.example.pathebredabioscoopapp.logic.ReviewAdapter;
import com.example.pathebredabioscoopapp.logic.ReviewsAPITask;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ReviewActivity extends AppCompatActivity implements ReviewsAPITask.ReviewsListener {
    private ArrayList<Reviews> reviews;
    private TextView mUsername;
    private TextView mContent;
    private ReviewAdapter reviewAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        Reviews review = (Reviews) getIntent().getSerializableExtra("REVIEW_NAME");
        fillViews(review);

        new ReviewsAPITask(this::onReviewAvailable).execute();
    }

    public void fillViews(Reviews review){
        mUsername = findViewById(R.id.tv_username);
        mUsername.setText(review.getUsername());
        mContent = findViewById(R.id.tv_review_description);
        mContent.setText(review.getContent());
    }

    @Override
    public void onReviewAvailable(ArrayList<Reviews> reviewsList) {
        this.reviews.addAll(reviewsList);
        this.reviewAdapter.notifyDataSetChanged();
    }
}
