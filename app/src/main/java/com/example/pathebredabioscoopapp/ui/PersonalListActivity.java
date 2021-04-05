package com.example.pathebredabioscoopapp.ui;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import com.example.pathebredabioscoopapp.R;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pathebredabioscoopapp.domain.FilmList;
import com.example.pathebredabioscoopapp.domain.Films;
import com.example.pathebredabioscoopapp.logic.AddToListTask;
import com.example.pathebredabioscoopapp.logic.FilmAPITask;
import com.example.pathebredabioscoopapp.logic.FilmAdapter;

import java.util.ArrayList;

public class PersonalListActivity extends AppCompatActivity implements FilmAPITask.FilmListener {
    private AllListsActivity allListsActivity;
    private TextView mTitleText;
    private FilmAdapter filmAdapter;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Films> filmList = new ArrayList<>();
    private String nameList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

            setContentView(R.layout.activity_recycler_view);
            mTitleText = findViewById(R.id.tv_general_recyclerview_title);
            layoutManager = new LinearLayoutManager(this);

            recyclerView = findViewById(R.id.rv_general_recyclerview);
            recyclerView.setLayoutManager(layoutManager);
            filmAdapter = new FilmAdapter(filmList);
            recyclerView.setAdapter(filmAdapter);

            FilmList filmlist = (FilmList) getIntent().getSerializableExtra("LIST_NAME");
            Films film = (Films) getIntent().getSerializableExtra("ADD_TO_LIST");
            mTitleText.setText(filmlist.getName());
            new FilmAPITask(this, filmlist.getId()).execute();

            if(film != null && filmlist != null){
                new AddToListTask(film, filmlist, filmAdapter).execute();
            }
        }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_personal_list,menu);
        return true;
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
    public void onFilmsListAvailable(ArrayList<Films> filmList) {
        this.filmList.addAll(filmList);
        this.filmAdapter.notifyDataSetChanged();
    }
}
