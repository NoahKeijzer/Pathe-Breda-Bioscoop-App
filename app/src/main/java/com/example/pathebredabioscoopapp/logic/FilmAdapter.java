package com.example.pathebredabioscoopapp.logic;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pathebredabioscoopapp.R;
import com.example.pathebredabioscoopapp.domain.Films;
import com.example.pathebredabioscoopapp.ui.DetailActivity;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.List;

public class FilmAdapter extends RecyclerView.Adapter<FilmAdapter.FilmViewHolder> implements Serializable {
    private final String TAG = getClass().getSimpleName();
    private List<Films> filmList;
    public static final String BASE_POSTER_PATH_URL = "https://image.tmdb.org/t/p/w500";

    public FilmAdapter(List filmList){
        Log.d(TAG, "FilmAdapter constructor is aangeroepen.");
        this.filmList = filmList;
    }

    @NonNull
    @Override
    public FilmViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder() is aangeroepen.");

        Context context = parent.getContext();

        int layoutIdForListItem = R.layout.explore_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, parent, shouldAttachToParentImmediately);
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
        String fullPath = BASE_POSTER_PATH_URL + film.getPoster();
        Picasso.get().load(fullPath).resize(250, 250).into(holder.mFilmImage);
    }

    @Override
    public int getItemCount() {

        if (null == filmList){
            Log.d(TAG, "getItemCount(): Er zijn 0 items.");
            return 0;
        }

        Log.d(TAG,  "getItemCount(): Er zijn "+ filmList.size() + " items.");
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

                   Intent startChildActivityIntent = new Intent(context, destinationActivity);

                   startChildActivityIntent.putExtra("EXTRA_NAME", film);

                   context.startActivity(startChildActivityIntent);
               }
           });

           /*mDeleteButton.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v){
                   Log.d(TAG, "onClick() van een delete button is aangeroepen.");

                   int position = 1;

                   for (Films movie : filmList) {
                       if (mTitleText.getText().toString().equals(movie.getTitle())) {
                           position = filmList.indexOf(movie);
                       }
                   }

                   Films film = filmList.get(position);

                   filmList.remove(film);
               }
           });

           mAddButton.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v){
                   Log.d(TAG, "onClick() van een delete button is aangeroepen.");

                   int position = 1;

                   for (Films movie : filmList) {
                       if (mTitleText.getText().toString().equals(movie.getTitle())) {
                           position = filmList.indexOf(movie);
                       }
                   }

                   Films film = filmList.get(position);



               }
           });*/
       }
   }
}
