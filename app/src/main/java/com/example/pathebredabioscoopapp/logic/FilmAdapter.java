package com.example.pathebredabioscoopapp.logic;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pathebredabioscoopapp.R;
import com.example.pathebredabioscoopapp.domain.FilmList;
import com.example.pathebredabioscoopapp.domain.Films;
import com.example.pathebredabioscoopapp.ui.AllListsActivity;
import com.example.pathebredabioscoopapp.ui.DetailActivity;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class FilmAdapter extends RecyclerView.Adapter<FilmAdapter.FilmViewHolder> implements Serializable {
    private final String TAG = getClass().getSimpleName();
    private List<Films> filmList;
    public static final String BASE_POSTER_PATH_URL = "https://image.tmdb.org/t/p/w500";
    private int layoutIdForListItem;
    private FilmList filmListObject;

    public FilmAdapter(List filmList, int layoutIdForListItem, FilmList filmListObject) {
        Log.d(TAG, "FilmAdapter constructor is aangeroepen.");
        this.filmList = filmList;
        this.layoutIdForListItem = layoutIdForListItem;
        this.filmListObject = filmListObject;
    }

    public FilmAdapter(ArrayList<Films> fullFilmList, int layoutIdForListItem) {
        this.filmList = filmList;
        this.layoutIdForListItem = layoutIdForListItem;
    }

    @NonNull
    @Override
    public FilmViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder() is aangeroepen.");

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(this.layoutIdForListItem, parent, shouldAttachToParentImmediately);
        return new FilmViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FilmViewHolder holder, int position) {
        Log.d(TAG, "onBind is aangeroepen");

        Films film = filmList.get(position);

        holder.mTitleText.setText(String.valueOf(film.getTitle()));
        holder.mRatingText.setText(String.valueOf(film.getRating()));
        holder.mGenreText.setText(String.valueOf(film.getGenre()));
        holder.mReleaseText.setText(String.valueOf(film.getReleaseDate()));
        holder.mLengthText.setText(String.valueOf(film.getDuration()));
        holder.mAddButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Class destinationActivity = AllListsActivity.class;
                Intent startChildActivityIntent = new Intent(context, destinationActivity);
                startChildActivityIntent.putExtra("ADD_TO_LIST", film);
                context.startActivity(startChildActivityIntent);
            }
        });

        String fullPath = BASE_POSTER_PATH_URL + film.getPoster();
        Picasso.get().load(fullPath).resize(250, 310).into(holder.mFilmImage);
    }

    @Override
    public int getItemCount() {
        Log.d(TAG, "getItemCount(): Er zijn " + filmList.size() + " items.");
        return filmList.size();
    }

    public class FilmViewHolder extends RecyclerView.ViewHolder {
        private ImageView mFilmImage;
        private TextView mTitleText;
        private TextView mRatingText;
        private TextView mGenreText;
        private TextView mReleaseText;
        private TextView mLengthText;
        private ImageView mDeleteButton;
        private ImageView mAddButton;

        public FilmViewHolder(@NonNull View view) {
            super(view);

            Log.d(TAG, "ViewHolder constructor is aangeroepen.");

            mFilmImage = (ImageView) itemView.findViewById(R.id.iv_poster_image);
            mTitleText = (TextView) itemView.findViewById(R.id.tv_movie_title);
            mRatingText = (TextView) itemView.findViewById(R.id.tv_movie_rating);
            mGenreText = (TextView) itemView.findViewById(R.id.tv_movie_genre);
            mReleaseText = (TextView) itemView.findViewById(R.id.tv_movie_release_year);
            mLengthText = (TextView) itemView.findViewById(R.id.tv_movie_length);
            mDeleteButton = (ImageView) itemView.findViewById(R.id.iv_delete_icon);
            mDeleteButton.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    int position = 1;
                    for (Films movie : filmList) {
                        if (mTitleText.getText().toString().equals(movie.getTitle())) {
                            position = filmList.indexOf(movie);
                        }
                    }
                    Films film = filmList.get(position);
                    new RemoveFilmFromListTask(film, filmListObject).execute();
                }
            });

            mAddButton = (ImageView) itemView.findViewById(R.id.iv_add_icon);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d(TAG, "onClick() van een view is aangeroepen.");
                    int position = 1;
                    for (Films movie : filmList) {
                        if (mTitleText.getText().toString().equals(movie.getTitle())) {
                            position = filmList.indexOf(movie);
                        }
                    }
                    Films film = filmList.get(position);
                    Context context = v.getContext();
                    Class destinationActivity = DetailActivity.class;
                    Intent startActivity = new Intent(context, destinationActivity);
                    startActivity.putExtra("FILM_NAME", film);
                    context.startActivity(startActivity);
                }
            });
        }
    }
}
