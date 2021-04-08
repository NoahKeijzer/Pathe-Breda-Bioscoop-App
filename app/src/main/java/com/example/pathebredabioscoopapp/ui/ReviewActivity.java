package com.example.pathebredabioscoopapp.ui;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pathebredabioscoopapp.R;
import com.example.pathebredabioscoopapp.domain.Actors;
import com.example.pathebredabioscoopapp.domain.Films;
import com.example.pathebredabioscoopapp.domain.Reviews;
import com.example.pathebredabioscoopapp.logic.FilmAPITask;
import com.example.pathebredabioscoopapp.logic.FilmAdapter;
import com.example.pathebredabioscoopapp.logic.ReviewAdapter;
import com.example.pathebredabioscoopapp.logic.ReviewsAPITask;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ReviewActivity extends AppCompatActivity implements ReviewsAPITask.ReviewsListener, Serializable {
    private ArrayList<Reviews> reviews = new ArrayList<>();
    private Films film;
    private TextView mUsername;
    private TextView mContent;
    private ReviewAdapter reviewAdapter;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private TextView mTitle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_recycler_view);
        layoutManager = new LinearLayoutManager(this);
        recyclerView = findViewById(R.id.rv_general_recyclerview);
        recyclerView.setLayoutManager(layoutManager);
        mTitle = findViewById(R.id.tv_general_recyclerview_title);
        mTitle.setText(R.string.reviews);

        film = (Films) getIntent().getSerializableExtra("REVIEW_NAME");

        reviewAdapter = new ReviewAdapter(reviews);
        recyclerView.setAdapter(reviewAdapter);

        new ReviewsAPITask(this, film).execute();
    }

    @Override
    public void onReviewAvailable(ArrayList<Reviews> reviewsList) {
        this.reviews.addAll(reviewsList);
        this.reviewAdapter.notifyDataSetChanged();
    }
}
