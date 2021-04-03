package com.example.pathebredabioscoopapp.ui;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pathebredabioscoopapp.R;
import com.example.pathebredabioscoopapp.controller.CreateMovieListController;
import com.example.pathebredabioscoopapp.controller.MovieListsController;
import com.example.pathebredabioscoopapp.domain.FilmList;
import com.example.pathebredabioscoopapp.domain.Films;
import com.example.pathebredabioscoopapp.logic.CreateFilmListAPIResponse;
import com.example.pathebredabioscoopapp.logic.FilmAPI;
import com.example.pathebredabioscoopapp.logic.FilmAPIResponse;
import com.example.pathebredabioscoopapp.logic.FilmAPITask;
import com.example.pathebredabioscoopapp.logic.FilmAdapter;
import com.example.pathebredabioscoopapp.ui.home.HomeAdapter;
import com.example.pathebredabioscoopapp.controller.BaseMovieAppController;

import java.util.ArrayList;

import retrofit2.Call;

public class ExploreMoviesActivity extends AppCompatActivity implements CreateMovieListController.MovieControllerListener {
    private final String TAG = getClass().getSimpleName();
    private TextView mTitleText;
    private FilmAdapter filmAdapter;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<FilmList> filmList;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
        layoutManager = new LinearLayoutManager(this);
        recyclerView = findViewById(R.id.film_recycler_view);
        recyclerView.setLayoutManager(layoutManager);
        homeAdapter = new HomeAdapter(onFilmsListAvailable);
        recyclerView.setAdapter(homeAdapter);

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

    @Override
    public void onFilmsAvailable(ArrayList<Films> movies) {
        this.films.addAll(movies);
        this.homeAdapter.notifyDataSetChanged();
    }
}
