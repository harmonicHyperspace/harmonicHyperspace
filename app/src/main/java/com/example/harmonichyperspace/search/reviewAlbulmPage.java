package com.example.harmonichyperspace.search;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.bumptech.glide.Glide;
import com.example.harmonichyperspace.DB.AlbumReview;
import com.example.harmonichyperspace.DB.harmonicHyperspaceDAO;
import com.example.harmonichyperspace.DB.harmonicHyperspaceDatabase;
import com.example.harmonichyperspace.MainHomePage;
import com.example.harmonichyperspace.R;

public class reviewAlbulmPage extends AppCompatActivity {
    private EditText mTitleField;
    private EditText mReviewField;
    private EditText mRatingField;
    private String mCategoryField;
    private String mArtistField;
    private String mAlbulmField;
    private String mThumbnailField;
    private Button mSubmitButton;
    private harmonicHyperspaceDAO mHarmonicHyperspaceDAO;

    public static Intent intentFactory(Context context, String albulmId, String albulmName, String artistName, String genre, String albulmThumbnail) {
        Intent intent = new Intent(context, reviewAlbulmPage.class);
        intent.putExtra("albulmName", albulmName);
        intent.putExtra("artistName", artistName);
        intent.putExtra("genre", genre);
        intent.putExtra("albulmId", albulmId);
        intent.putExtra("albulmThumbnail", albulmThumbnail);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_review_albulm_page);

        //Initialize the DAO
        getDatabase();

        //attaching information and such
        wiringUpDisplay();

        //get the intent extras and set them to the fields
        getIntentExtras();
    }

    private void wiringUpDisplay() {
        mTitleField = findViewById(R.id.reviewTitle);
        mReviewField = findViewById(R.id.reviewBody);
        mRatingField = findViewById(R.id.reviewScore);
        mSubmitButton = findViewById(R.id.submit);

        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitReview();
                goBack();
            }
        });
    }

    private void getIntentExtras() {
        ImageView albumThumbnail = findViewById(R.id.albulmImage);
        TextView albumName = findViewById(R.id.albulmName);
        TextView artistName = findViewById(R.id.albulmInfo);

        Intent intent = getIntent();
        String albumId = intent.getStringExtra("albulmId");
        String albumNameString = intent.getStringExtra("albulmName");
        String artistNameString = intent.getStringExtra("artistName");
        String genreString = intent.getStringExtra("genre");
        String albumThumbnailString = intent.getStringExtra("albulmThumbnail");

        Log.d("ReviewTrackPage", "Track ID: " + albumId);
        Log.d("ReviewTrackPage", "Track Name: " + albumNameString);
        Log.d("ReviewTrackPage", "Artist Name: " + artistNameString);
        Log.d("ReviewTrackPage", "Track Thumbnail: " + albumThumbnailString);

        albumName.setText(albumNameString);
        artistName.setText(artistNameString);
        Glide.with(this).load(albumThumbnailString).into(albumThumbnail);

        mArtistField = artistNameString;
        mAlbulmField = albumNameString;
        mThumbnailField = albumThumbnailString;
    }

    private void goBack() {
        Intent intent = new Intent(getApplicationContext(), MainHomePage.class);
        startActivity(intent);
    }

    private void submitReview() {
        String userId = getUserId();
        if (userId == null) {
            Log.d("reviewAlbulmPage", "User is not logged in");
            return;
        }
        String title = mTitleField.getText().toString();
        String review = mReviewField.getText().toString();
        String rating = mRatingField.getText().toString();
        String category = mCategoryField;
        String artist = mArtistField;
        String albulm = mAlbulmField;

        AlbumReview newReview = new AlbumReview(title, review, rating, artist, albulm, category);
        mHarmonicHyperspaceDAO.insert(newReview);

    }

    private void getDatabase() {
        mHarmonicHyperspaceDAO = Room.databaseBuilder(this, harmonicHyperspaceDatabase.class, "harmonicHyperspace.db")
                .allowMainThreadQueries()
                .build()
                .harmonicHyperspaceDAO();
    }

    private String getUserId() {
        SharedPreferences sharedPreferences = getSharedPreferences("hyperspacePrefs", MODE_PRIVATE);
        return sharedPreferences.getString("userId", null);
    }
}