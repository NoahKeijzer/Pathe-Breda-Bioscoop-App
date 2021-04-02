package com.example.pathebredabioscoopapp.ui.home;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pathebredabioscoopapp.R;
import com.example.pathebredabioscoopapp.domain.Films;


import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 *
 */
public class HomeAdapter
        extends RecyclerView.Adapter<HomeAdapter.MoviesGridViewHolder> {

    private final String LOG_TAG = this.getClass().getSimpleName();
    private final ArrayList<Films> filmsArrayList = new ArrayList<>();
    private final OnMovieSelectionListener listener;

    public HomeAdapter(OnMovieSelectionListener listener) {
        Log.d(LOG_TAG, "Constructor aangeroepen");
        this.listener = listener;
    }

    @NonNull
    @Override
    public MoviesGridViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Log.d(LOG_TAG, "onCreateViewHolder aangeroepen");

        int layoutIdForListItem = R.layout.view_holder_films;
        final boolean shouldAttachToParentImmediately = false;

        View view = LayoutInflater.from(parent.getContext()).inflate(layoutIdForListItem, parent, shouldAttachToParentImmediately);
        return new MoviesGridViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MoviesGridViewHolder holder, int position) {
        Films film = filmsArrayList.get(position);
        Log.d(LOG_TAG, "onBindViewHolder - " + film.toString());
        holder.mTitleFilm.setText(film.getTitle());

//         Picasso caches images in its own cache. Use that to get images
//         in case we're offline.
//        Picasso.get()
//                .load(film.getFullPosterPath())
//                // Check whether we have the image in our offline Picasso cache
//                .networkPolicy(NetworkPolicy.OFFLINE)
//                .resize(700, 700)
//                .centerInside()
//                .into(holder.mMovieImage);//, new Callback() {

//                    @Override
//                    public void onSuccess() { }
//
//                    @Override
//                    public void onError(Exception e) {
//                        // Image not found in offline Picasso cache on the device -
//                        // retry to get the online image
//                        Picasso.get()
//                                .load(movie.getFullPosterPath())
//                                .networkPolicy(NetworkPolicy.NO_CACHE)
//                                .placeholder(R.mipmap.movie_no_poster_available)
//                                .resize(700, 700)
//                                .centerInside()
//                                .into(holder.mMovieImage);
//                    }
//                });
    }

    @Override
    public int getItemCount() {
        return filmsArrayList.size();
    }

    public void setMovieList(ArrayList<Films> movies) {
        Log.d(LOG_TAG, "setMovieList");
        this.filmsArrayList.clear();
        this.filmsArrayList.addAll(movies);
        this.notifyDataSetChanged();
    }

    public class MoviesGridViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public ImageView mImageFilm;
        public TextView mTitleFilm;

        public MoviesGridViewHolder(@NonNull View itemView) {
            super(itemView);
            mImageFilm = (ImageView) itemView.findViewById(R.id.iv_poster_film);
            mTitleFilm = (TextView) itemView.findViewById(R.id.tv_title_film);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Log.d(LOG_TAG, "onClick on item " + getAdapterPosition());
            listener.onMovieSelected(getAdapterPosition());
        }
    }

    public interface OnMovieSelectionListener {
        void onMovieSelected(int position);
    }
}
