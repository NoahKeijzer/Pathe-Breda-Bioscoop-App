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
import com.example.pathebredabioscoopapp.domain.Reviews;
import com.example.pathebredabioscoopapp.ui.AllListsActivity;
import com.example.pathebredabioscoopapp.ui.DetailActivity;
import com.example.pathebredabioscoopapp.ui.PersonalListActivity;
import com.example.pathebredabioscoopapp.ui.ReviewActivity;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.List;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ReviewViewHolder> implements Serializable {
    private final String TAG = getClass().getSimpleName();
    private List<Reviews> reviewList;
    private AllListsActivity allListsActivity = new AllListsActivity();

    public ReviewAdapter(List reviewList) {
  //      Log.d(TAG, "ReviewAdapter constructor is aangeroepen.");
        this.reviewList = reviewList;
    }

    @NonNull
    @Override
    public ReviewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        Log.d(TAG, "onCreateViewHolder() is aangeroepen.");

        Context context = parent.getContext();

        int layoutIdForListItem = R.layout.review_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, parent, shouldAttachToParentImmediately);
        return new ReviewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewAdapter.ReviewViewHolder holder, int position) {
   //     Log.d(TAG, "onBind is aangeroepen");

        Reviews review = reviewList.get(position);

        holder.mUsername.setText("Username: " + String.valueOf(review.getUsername()));
        holder.mContent.setText("Review: \n\n" + String.valueOf(review.getContent()));
    }

    @Override
    public int getItemCount() {

        if (null == reviewList) {
   //         Log.d(TAG, "getItemCount(): Er zijn 0 items.");
            return 0;
        }

  //      Log.d(TAG, "getItemCount(): Er zijn " + reviewList.size() + " items.");
        return reviewList.size();
    }

    public class ReviewViewHolder extends RecyclerView.ViewHolder {
        private TextView mUsername;
        private TextView mContent;

        public ReviewViewHolder(@NonNull View view) {
            super(view);

      //      Log.d(TAG, "ViewHolder constructor is aangeroepen.");

            mUsername = (TextView) itemView.findViewById(R.id.tv_username);
            mContent = (TextView) itemView.findViewById(R.id.tv_review_description);

        }
    }
}
