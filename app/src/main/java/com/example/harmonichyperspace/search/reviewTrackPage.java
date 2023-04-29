package com.example.harmonichyperspace.search;

import android.content.Context;
import android.content.Intent;
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
import com.example.harmonichyperspace.DB.SongReview;
import com.example.harmonichyperspace.DB.harmonicHyperspaceDAO;
import com.example.harmonichyperspace.DB.harmonicHyperspaceDatabase;
import com.example.harmonichyperspace.MainHomePage;
import com.example.harmonichyperspace.R;

public class reviewTrackPage extends AppCompatActivity {

    private EditText mTitleField;
    private EditText mReviewField;
    private EditText mRatingField;
    private String mCategoryField;
    private String mArtistField;
    private String mAlbulmField;
    private String mSongField;
    private Button mSubmitButton;
    private String mThumbnailField;
    private harmonicHyperspaceDAO mHarmonicHyperspaceDAO;

    public static Intent intentFactory(Context context, String trackId, String trackName, String artistName, String trackThumbnail) {
        Intent intent = new Intent(context, reviewTrackPage.class);
        intent.putExtra("trackId", trackId);
        intent.putExtra("trackName", trackName);
        intent.putExtra("artistName", artistName);
        intent.putExtra("trackThumbnail", trackThumbnail);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_review_track_page);

        //Initialize the DAO
        getDatabase();

        wiringUpDisplay();

        ImageView trackThumbnail = findViewById(R.id.trackThumbnail);
        TextView trackName = findViewById(R.id.trackName);
        TextView artistName = findViewById(R.id.trackInfo);

        Intent intent = getIntent();
        String trackId = intent.getStringExtra("trackId");
        String trackNameString = intent.getStringExtra("trackName");
        String artistNameString = intent.getStringExtra("artistName");
        String trackThumbnailString = intent.getStringExtra("trackThumbnail");


        Log.d("ReviewTrackPage", "Track ID: " + trackId);
        Log.d("ReviewTrackPage", "Track Name: " + trackNameString);
        Log.d("ReviewTrackPage", "Artist Name: " + artistNameString);
        Log.d("ReviewTrackPage", "Track Thumbnail: " + trackThumbnailString);

        trackName.setText(trackNameString);
        artistName.setText(artistNameString);
        Glide.with(this).load(trackThumbnailString).into(trackThumbnail);

        mSongField = trackNameString;
        mArtistField = artistNameString;
        mAlbulmField = "N/A";
        mThumbnailField = trackThumbnailString;
    }

    private void goBack() {
        Intent intent = new Intent(getApplicationContext(), MainHomePage.class);
        startActivity(intent);
    }

    public void wiringUpDisplay() {
        mTitleField = findViewById(R.id.reviewTitle);
        mReviewField = findViewById(R.id.reviewBody);
        mRatingField = findViewById(R.id.reviewScore);
        mSubmitButton = findViewById(R.id.submit);

        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveReview();
                goBack();
            }
        });
    }


    private void saveReview() {
        String userId = "1";
        String title = mTitleField.getText().toString();
        String review = mReviewField.getText().toString();
        String rating = mRatingField.getText().toString();
        String category = mCategoryField;
        String artist = mArtistField;
        String albulm = mAlbulmField;
        String song = mSongField;
        //String thumbnail = mThumbnailField;

        SongReview newReview = new SongReview(userId, title, review, rating, song, artist, albulm, category);
        harmonicHyperspaceDatabase db = Room.databaseBuilder(getApplicationContext(), harmonicHyperspaceDatabase.class, "harmonicHyperspaceDatabase").build();
        mHarmonicHyperspaceDAO.insert(newReview);

    }

    public void getDatabase() {
        mHarmonicHyperspaceDAO = Room.databaseBuilder(this, harmonicHyperspaceDatabase.class, "harmonicHyperspace.db")
                .allowMainThreadQueries()
                .build()
                .harmonicHyperspaceDAO();
    }
}