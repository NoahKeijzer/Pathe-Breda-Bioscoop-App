package com.example.pathebredabioscoopapp.ui;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
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

public class NewListsActivity extends AppCompatActivity {
    private TextView mTitleText;
    private EditText mTitleInput;
    private TextView mDescription;
    private EditText mDescriptionInput;
    private RadioGroup mRadioGroup;
    private Spinner mSpinner;
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
        mSpinner = findViewById(R.id.sp_sort_list);
        mbtnSave = findViewById(R.id.btn_save_list);
        mbtnCancel = findViewById(R.id.btn_cancel_list);

        String[] arraySpinner = new String[]{
                "A -> Z", "Z -> A", "Rating High -> Low", "Rating Low -> High", "Release date New -> Old", "Release date Old -> Release date New"
        };
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.spinner_item, arraySpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner.setAdapter(adapter);

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
