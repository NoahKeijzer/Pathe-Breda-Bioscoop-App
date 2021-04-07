package com.example.pathebredabioscoopapp.ui;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pathebredabioscoopapp.R;
import com.example.pathebredabioscoopapp.domain.Films;
import com.example.pathebredabioscoopapp.logic.FilmAPITask;
import com.example.pathebredabioscoopapp.logic.FilmAdapter;
import com.example.pathebredabioscoopapp.logic.FilterFilm;
import com.example.pathebredabioscoopapp.logic.SearchFilm;
import com.example.pathebredabioscoopapp.logic.SortFilm;

import java.util.ArrayList;

/*import retrofit2.Call;*/

public class ExploreMoviesActivity extends AppCompatActivity implements FilmAPITask.FilmListener{
    private final String TAG = getClass().getSimpleName();
    private TextView mTitleText;
    private FilmAdapter filmAdapter;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Films> filteredFilmList = new ArrayList<>();
    private ArrayList<Films> fullFilmList = new ArrayList<>();
    private SortFilm sortFilm;
    private FilterFilm filterFilm;
    private  SearchFilm searchFilm;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        layoutManager = new LinearLayoutManager(this);
        mTitleText = findViewById(R.id.tv_general_recyclerview_title);
        mTitleText.setText("Populair movies");
        recyclerView = findViewById(R.id.rv_general_recyclerview);
        recyclerView.setLayoutManager(layoutManager);
        int layoutIdForListItem = R.layout.explore_list_item;
        filmAdapter = new FilmAdapter(fullFilmList, layoutIdForListItem);
        recyclerView.setAdapter(filmAdapter);

        new FilmAPITask(this).execute();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_explore_list,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        this.filterFilm = new FilterFilm(filteredFilmList, fullFilmList, filmAdapter);
        this.sortFilm = new SortFilm(fullFilmList, filmAdapter);
        this.searchFilm = new SearchFilm(fullFilmList, filmAdapter);
        switch(item.getItemId()) {
            case R.id.action_sort:
                return true;
            case R.id.filter_on_genre_action:
                this.filterFilm.getFilter().filter("Action");
                return true;
            case R.id.filter_on_genre_adventure:
                this.filterFilm.getFilter().filter("Adventure");
                return true;
            case R.id.filter_on_genre_animation:
                this.filterFilm.getFilter().filter("Animation");
                return true;
            case R.id.filter_on_genre_comedy:
                this.filterFilm.getFilter().filter("Comedy");
                return true;
            case R.id.filter_on_genre_crime:
                this.filterFilm.getFilter().filter("Crime");
                return true;
            case R.id.filter_on_genre_documentary:
                this.filterFilm.getFilter().filter("Documentary");
                return true;
            case R.id.filter_on_genre_drama:
                this.filterFilm.getFilter().filter("Drama");
                return true;
            case R.id.filter_on_genre_family:
                this.filterFilm.getFilter().filter("Family");
                return true;
            case R.id.filter_on_genre_fantasy:
                this.filterFilm.getFilter().filter("Fantasy");
                return true;
            case R.id.filter_on_genre_history:
                this.filterFilm.getFilter().filter("History");
                return true;
            case R.id.filter_on_genre_horror:
                this.filterFilm.getFilter().filter("Horror");
                return true;
            case R.id.filter_on_genre_music:
                this.filterFilm.getFilter().filter("Music");
                return true;
            case R.id.filter_on_genre_mystery:
                this.filterFilm.getFilter().filter("Mystery");
                return true;
            case R.id.filter_on_genre_romance:
                this.filterFilm.getFilter().filter("Romance");
                return true;
            case R.id.filter_on_genre_science_fiction:
                this.filterFilm.getFilter().filter("Science Fiction");
                return true;
            case R.id.filter_on_genre_thriller:
                this.filterFilm.getFilter().filter("Thriller");
                return true;
            case R.id.filter_on_genre_tv_movie:
                this.filterFilm.getFilter().filter("TV Movie");
                return true;
            case R.id.filter_on_genre_war:
                this.filterFilm.getFilter().filter("War");
                return true;
            case R.id.filter_on_genre_western:
                this.filterFilm.getFilter().filter("Western");
                return true;
            case R.id.sort_a_to_z:
                this.sortFilm.getFilter().filter("sortAtoZ");
                return true;
            case R.id.sort_z_to_a:
                this.sortFilm.getFilter().filter("sortZtoA");
                return true;
            case R.id.sort_high_to_low:
                this.sortFilm.getFilter().filter("sortRatingHigh");
                return true;
            case R.id.sort_low_to_high:
                this.sortFilm.getFilter().filter("sortRatingLow");
                return true;
            case R.id.sort_new_to_old:
                this.sortFilm.getFilter().filter("sortNewToOld");
                return true;
            case R.id.sort_old_to_new:
                this.sortFilm.getFilter().filter("sortOldToNew");
                return true;
            case R.id.search:
                SearchView searchView = (SearchView) item.getActionView();
                searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);
                searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

                    @Override
                    public boolean onQueryTextSubmit(String query) {
                        return false;
                    }

                    @Override
                    public boolean onQueryTextChange(String newText) {
                        searchFilm.getFilter().filter(newText);
                        return false;
                    }
                });
        }
        return true;
    }

    @Override
    public void onConfigurationChanged  (@NonNull Configuration newConfig) {
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
        this.fullFilmList.addAll(filmList);
        this.filmAdapter.notifyDataSetChanged();
    }
}
