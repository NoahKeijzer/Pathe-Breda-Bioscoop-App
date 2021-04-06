package com.example.pathebredabioscoopapp.ui;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.Layout;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pathebredabioscoopapp.R;
import com.example.pathebredabioscoopapp.domain.Actors;
import com.example.pathebredabioscoopapp.domain.FilmList;
import com.example.pathebredabioscoopapp.domain.Films;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity {
    private Films film;
    private TextView mTitleText;
    private TextView mGenreTextView;
    private TextView mReleaseDateText;
    private TextView mTimeText;
    private ImageView mPosterImageView;
    private TextView mDirectorText;
    private TextView mDescriptionText;
    private TextView mActorOneText;
    private TextView mActorTwoText;
    private TextView mActorThreeText;
    private ImageView mActorOneImage;
    private ImageView mActorTwoImage;
    private ImageView mActorThreeImage;
    private Button mTrailerButton;
    private Button mViewReviewButton;
    private RecyclerView mRecyclerView;
    private LinearLayoutManager layout;
    private Button mGiveRatingButton;

    private static final String FILM_INTENT = "FILM_INTENT";
    private static final String BASE_POSTER_PATH_URL = "https://image.tmdb.org/t/p/w500";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        film = (Films) getIntent().getSerializableExtra("FILM_NAME");
        fillViews();
    }

    public void fillViews(){
        mTitleText = findViewById(R.id.tv_movie_title);
        mTitleText.setText(film.getTitle());
        mGenreTextView = findViewById(R.id.tv_movie_detail_genre);
        mGenreTextView.setText(film.getGenre());
        mReleaseDateText = findViewById(R.id.tv_movie_detail_release_date);
        mReleaseDateText.setText(String.valueOf(film.getReleaseDate()));
        mTimeText = findViewById(R.id.tv_movie_detail_time);
        mTimeText.setText(String.valueOf(film.getDuration()));
        mDirectorText = findViewById(R.id.tv_movie_detail_director);
        mDirectorText.setText(film.getDirector());
        mDescriptionText = findViewById(R.id.tv_movie_detail_description);
        mDescriptionText.setText(film.getDescription());
        mActorOneText = findViewById(R.id.tv_movie_detail_actor_one);
        mActorTwoText = findViewById(R.id.tv_movie_detail_actor_two);
        mActorThreeText = findViewById(R.id.tv_movie_detail_actor_three);


        ArrayList<Actors> actor = film.getActors();
        for(int i = 0; i < actor.size(); i++){
            if(i == 0){
                mActorOneText.setText(actor.get(i).getName());
            }else if(i == 1){
                mActorTwoText.setText(actor.get(i).getName());
            }else if(i == 2){
                mActorThreeText.setText(actor.get(i).getName());
            }else{
                break;
            }
        }

        mActorOneImage = findViewById(R.id.iv_movie_detail_actor_one);
        mActorTwoImage = findViewById(R.id.iv_movie_detail_actor_two);
        mActorThreeImage = findViewById(R.id.iv_movie_detail_actor_three);


        mTrailerButton = findViewById(R.id.btn_view_trailer);
        mViewReviewButton = findViewById(R.id.btn_view_reviews);
        mGiveRatingButton = findViewById(R.id.btn_write_review);
        mGiveRatingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Class destination = NewReviewActivity.class;
                Intent startChildIntent = new Intent(context, destination);
                startChildIntent.putExtra(FILM_INTENT, film);
                context.startActivity(startChildIntent);
            }
        });

        mPosterImageView = findViewById(R.id.iv_movie_detail_poster_image);
        String fullPath = BASE_POSTER_PATH_URL + film.getPoster();
        Picasso.get().load(fullPath).resize(250, 350).into(mPosterImageView);

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_movie_detail,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()) {
            case R.id.action_add:
                Context context = this;
                Class destinationActivity = AllListsActivity.class;
                Intent startChildActivityIntent = new Intent(context, destinationActivity);
                startChildActivityIntent.putExtra("ADD_TO_LIST", film);
                context.startActivity(startChildActivityIntent);
                return true;
            case R.id.action_share:
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "See this awesome movie: \n" + film.getTrailer());
                sendIntent.setType("text/plain");
                Intent shareIntent = Intent.createChooser(sendIntent, null);
                startActivity(shareIntent);
        }
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


}
