package com.example.pathebredabioscoopapp.ui;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.pathebredabioscoopapp.domain.Films;

import java.io.Serializable;

public class DetailActivity extends AppCompatActivity implements Serializable {
    private Films film;
    private TextView mTitleText;
    private ImageView mPosterImageView;
    private TextView mGenreTextView;
    private TextView mReleaseDateText;
    private TextView mTimeText;
    private TextView mDirectorText;
    private TextView mDescriptionText;
    private TextView mActorOneText;
    private TextView mActorTwoText;
    private TextView mActorThreeText;
    private ImageView mActorOneImage;
    private ImageView mActorTwoImage;
    private ImageView mActorThreeImage;
    private TextView mURLText;
    private Button mWriteReviewButton;
    private Button mGiveRatingButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    public void fillViews(){}

}
