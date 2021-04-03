package com.example.pathebredabioscoopapp.logic;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FilmAdapter extends RecyclerView.Adapter<FilmAdapter.FilmViewHolder>{
    private final String TAG = getClass().getSimpleName();
    private List filmList;

    public FilmAdapter(List filmList){
        this.filmList = filmList;
    }

    @NonNull
    @Override
    public FilmViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull FilmViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class FilmViewHolder extends RecyclerView.ViewHolder {
        private ImageView mFilmImage;
        private TextView mTitleText;
        private TextView mRatingText;
        private TextView mGenreText;
        private TextView mReleaseText;
        private TextView mLengthText;
        private Button mDeleteButton;
        private Button mAddButton;

       public FilmViewHolder(@NonNull View view) {
           super(view);


       }
   }
}
