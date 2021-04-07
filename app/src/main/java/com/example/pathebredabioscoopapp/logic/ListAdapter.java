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
import com.example.pathebredabioscoopapp.ui.PersonalListActivity;

import java.util.ArrayList;
import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ListViewHolder> {
    private final String TAG = getClass().getSimpleName();
    private List<FilmList> list;
    private Films film;
    private ListAdapter listAdapter = this;

    public ListAdapter(List list){
 //       Log.d(TAG, "ListAdapter constructor is aangeroepen.");
        this.list = list;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
 //       Log.d(TAG, "onCreateViewHolder() is aangeroepen.");

        Context context = parent.getContext();
        int layoutIdForListItem = R.layout.personal_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;
        View view = inflater.inflate(layoutIdForListItem, parent, shouldAttachToParentImmediately);
        return new ListAdapter.ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListAdapter.ListViewHolder holder, int position) {
  //      Log.d(TAG, "onBind is aangeroepen");

        FilmList filmList = list.get(position);
        int id = filmList.getId();
        holder.mListName.setText(filmList.getName());
    }

    @Override
    public int getItemCount() {
        if (null == list){
  //          Log.d(TAG, "getItemCount(): Er zijn 0 items.");
            return 0;
        }

   //     Log.d(TAG,  "getItemCount(): Er zijn "+ list.size() + " items.");
        return list.size();
    }

    public void setFilm(Films film) {
        this.film = film;
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {
        private TextView mListName;
        private ImageView mDeleteButton;
        private ImageView mShareButton;

        public ListViewHolder(@NonNull View view) {
            super(view);

    //        Log.d(TAG, "ViewHolder constructor is aangeroepen.");

            mListName = (TextView) itemView.findViewById(R.id.tv_list_name);
            mDeleteButton = (ImageView) itemView.findViewById(R.id.iv_delete_icon);
            mDeleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Log.d(TAG, "onClick() van een view is aangeroepen.");
                    int position = 1;
                    for (FilmList personalList : list) {
                        if (mListName.getText().toString().equals(personalList.getName())) {
                            position = list.indexOf(personalList);
                        }
                    }
                    FilmList filmList = list.get(position);
                    Context context = v.getContext();
                    new DeleteListTask(filmList, listAdapter).execute();
                    Class destinationActivity = AllListsActivity.class;
                    Intent startChildActivityIntent = new Intent(context, destinationActivity);
                    context.startActivity(startChildActivityIntent);
                }
            });
            mShareButton = (ImageView) itemView.findViewById(R.id.iv_share_icon);
            mShareButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
  //                  Log.d(TAG, "onClick() van een view is aangeroepen.");
                    int position = 1;
                    for (FilmList personalList : list) {
                        if (mListName.getText().toString().equals(personalList.getName())) {
                            position = list.indexOf(personalList);
                        }
                    }
                    Context context = v.getContext();
                    FilmList filmList = list.get(position);
                    Intent sendIntent = new Intent();
                    sendIntent.setAction(Intent.ACTION_SEND);
                    sendIntent.putExtra(Intent.EXTRA_TEXT, "See this awesome list: \n https://www.themoviedb.org/list/"+ filmList.getId() + "?language=nl");
                    sendIntent.setType("text/plain");
                    Intent shareIntent = Intent.createChooser(sendIntent, null);
                    context.startActivity(shareIntent);
                }
            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
   //                 Log.d(TAG, "onClick() van een view is aangeroepen.");
                    int position = 1;
                    for (FilmList personalList : list) {
                        if (mListName.getText().toString().equals(personalList.getName())) {
                            position = list.indexOf(personalList);
                        }
                    }

                    FilmList filmList = list.get(position);
                    Context context = v.getContext();
                    Class destinationActivity = PersonalListActivity.class;
                    Intent startChildActivityIntent = new Intent(context, destinationActivity);
                    startChildActivityIntent.putExtra("LIST_NAME",  filmList);
                    startChildActivityIntent.putExtra("ADD_TO_LIST",  film);
                    context.startActivity(startChildActivityIntent);
                }
            });
        }
    }
}

