package com.example.pathebredabioscoopapp.ui;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.pathebredabioscoopapp.R;
import com.example.pathebredabioscoopapp.logic.CreateNewListTask;

import java.io.IOException;

import okhttp3.Headers;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class NewListsActivity extends AppCompatActivity {
    private TextView mTitleText;
    private EditText mTitleInput;
    private TextView mDescription;
    private EditText mDescriptionInput;
    private RadioGroup mRadioGroup;
    private Button mbtnSave;
    private Button mbtnCancel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_list);

        mTitleText = findViewById(R.id.tv_make_list_title);
        mTitleInput = findViewById(R.id.et_make_list_title);
        mDescription = findViewById(R.id.tv_make_list_description);
        mDescriptionInput = findViewById(R.id.et_make_list_description);
        mbtnSave = findViewById(R.id.btn_save_list);
        mbtnCancel = findViewById(R.id.btn_cancel_list);

        mbtnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = String.valueOf(mTitleInput.getText());
                String description = String.valueOf(mDescriptionInput.getText());
                new CreateNewListTask().execute(title,description);

                Context context = v.getContext();
                Class destinationActivity = AllListsActivity.class;
                Intent startChildActivityIntent = new Intent(context, destinationActivity);
                context.startActivity(startChildActivityIntent);

            }
        });

        mbtnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


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


}

