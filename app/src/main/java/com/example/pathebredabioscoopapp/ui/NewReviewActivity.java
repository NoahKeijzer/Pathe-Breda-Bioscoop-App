package com.example.pathebredabioscoopapp.ui;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.pathebredabioscoopapp.R;

import org.w3c.dom.Text;

public class NewReviewActivity extends AppCompatActivity {
    private final String TAG = getClass().getSimpleName();
    private TextView mNewReviewTitle;
    private TextView mGiveRating;
    private TextView mCurrentRate;
    private EditText mNewReviewDescription;
    private String reviewDescriptionString;
    private SeekBar mRatingSeekbar;
    private Button mSubmit;
    private Button mCancel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_review);
        mNewReviewTitle = findViewById(R.id.tv_new_review_title);
        mNewReviewTitle.setText("New Review");
        mNewReviewDescription = findViewById(R.id.et_review_description);
        reviewDescriptionString = String.valueOf(mNewReviewDescription.getText());
        mGiveRating = findViewById(R.id.tv_give_rating_title);
        mCurrentRate = findViewById(R.id.tv_current_rating);
        mRatingSeekbar = findViewById(R.id.simpleSeeker);
        mCurrentRate.setText("Rating: " + mRatingSeekbar.getProgress());
        mRatingSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progress = 0;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progressValue, boolean fromUser) {
                progress = progressValue;
                mCurrentRate.setText("Rating: " + progress);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mCurrentRate.setText("Rating: " + progress);
            }
        });

        mSubmit = findViewById(R.id.btn_new_review_submit);
        mCancel = findViewById(R.id.btn_new_review_cancel);



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
