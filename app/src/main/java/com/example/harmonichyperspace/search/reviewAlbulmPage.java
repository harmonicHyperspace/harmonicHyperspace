package com.example.harmonichyperspace.search;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.harmonichyperspace.DB.AlbumReview;
import com.example.harmonichyperspace.DB.harmonicHyperspaceDAO;
import com.example.harmonichyperspace.landing.MainActivity;
import com.example.harmonichyperspace.R;
import com.example.harmonichyperspace.discovery.genres;

public class reviewAlbulmPage extends AppCompatActivity {
    private EditText mTitleField;
    private EditText mReviewField;
    private EditText mRatingField;
    private String mCategoryField;
    private String mArtistField;
    private String mAlbulmField;
    private Button mSubmitButton;
    private harmonicHyperspaceDAO mHarmonicHyperspaceDAO;

    public static Intent intentFactory(Context context) {
        Intent intent = new Intent(context, genres.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_review_albulm_page);

        wiringUpDisplay();
    }

    private void wiringUpDisplay() {
        mTitleField = findViewById(R.id.reviewTitle);
        mReviewField = findViewById(R.id.reviewBody);
        mRatingField = findViewById(R.id.reviewScore);
//        mCategoryField = findViewById(R.id.genreInput);
//        mArtistField = findViewById(R.id.artistInput);
//        mAlbulmField = findViewById(R.id.albulmInput);

        mSubmitButton = findViewById(R.id.submit);

        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitReview();
                goHome();
            }
        });
    }

    private void goHome() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }

    private void submitReview() {
        String title = mTitleField.getText().toString();
        String review = mReviewField.getText().toString();
        int rating = mRatingField.getInputType();
        String category = mCategoryField;
        String artist = mArtistField;
        String albulm = mAlbulmField;

        AlbumReview newReview = new AlbumReview(title, review, rating, artist, albulm, category);
        mHarmonicHyperspaceDAO.insert(newReview);

    }
}