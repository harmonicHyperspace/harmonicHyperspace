package com.example.harmonichyperspace.search;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;

import com.example.harmonichyperspace.R;

public class searchResults extends AppCompatActivity {
    
    private String mSearchQuery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_search_results);
        
        //retrieving the search text, using shared preferences
        Intent intent = getIntent();
        mSearchQuery = getIntent().getStringExtra("search");
        
        //query the spotify api with the search text
        searchSpotifyAPI(mSearchQuery);
    }

    private void searchSpotifyAPI(String searchQuery) {
    }
}