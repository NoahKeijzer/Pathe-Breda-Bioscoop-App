package com.example.pathebredabioscoopapp.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pathebredabioscoopapp.R;
import com.example.pathebredabioscoopapp.domain.*;
import com.example.pathebredabioscoopapp.logic.FilmAdapter;
import com.example.pathebredabioscoopapp.logic.FilmListAPITask;
import com.example.pathebredabioscoopapp.logic.ListAdapter;

import java.util.ArrayList;

public class AllListsActivity extends AppCompatActivity implements FilmListAPITask.FilmListListener {
    private final String TAG = getClass().getSimpleName();
    private RecyclerView.LayoutManager layoutManager;
    private TextView mTitleText;
    private ImageView mAddButton;
    private ArrayList<FilmList> filmList = new ArrayList<>();
    private RecyclerView personalListRecyclerView;
    private ListAdapter personalListAdapter;
    private FilmAdapter filmAdapter;
    private ImageView mShareButton;
    private final String PREFS = "MyPrefs";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new FilmListAPITask(this).execute();
        setContentView(R.layout.activity_personal_lists);
        mTitleText = findViewById(R.id.tv_personal_lists_title);
        layoutManager = new LinearLayoutManager(this);
        personalListRecyclerView = findViewById(R.id.rv_personal_lists_recyclerview);
        personalListRecyclerView.setLayoutManager(layoutManager);
        personalListAdapter = new ListAdapter(filmList);
        personalListRecyclerView.setAdapter(personalListAdapter);
        mTitleText.setText("Personal lists");
        Films film = (Films) getIntent().getSerializableExtra("ADD_TO_LIST");
        if (film != null) {
            personalListAdapter.setFilm(film);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_add_list,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        SharedPreferences sharedPreferences = getSharedPreferences(PREFS,getApplicationContext().MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        switch(item.getItemId()){
            case R.id.action_add:
                Intent launchNewIntent = new Intent(this, NewListsActivity.class);
                startActivityForResult(launchNewIntent, 0);
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

    @Override
    public void onFilmListReady(ArrayList<FilmList> FilmLists) {
        this.filmList.addAll(FilmLists);
        this.personalListAdapter.notifyDataSetChanged();
    }
}
