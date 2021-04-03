package com.example.pathebredabioscoopapp.ui;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pathebredabioscoopapp.R;
import com.example.pathebredabioscoopapp.domain.FilmList;
import com.example.pathebredabioscoopapp.domain.Films;
import com.example.pathebredabioscoopapp.logic.FilmAPI;
import com.example.pathebredabioscoopapp.logic.FilmAPITask;
import com.example.pathebredabioscoopapp.logic.FilmAdapter;

import java.util.ArrayList;

/*import retrofit2.Call;*/

public class ExploreMoviesActivity extends AppCompatActivity{
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
        recyclerView = findViewById(R.id.rv_general_recyclerview);
        recyclerView.setLayoutManager(layoutManager);
        filmAdapter = new FilmAdapter(filmList);
        recyclerView.setAdapter(filmAdapter);

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

    public void onFilmsAvailable(ArrayList<FilmList> movies) {
        this.filmList.addAll(movies);
        this.filmAdapter.notifyDataSetChanged();
    }
}
