package com.example.harmonichyperspace.information;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.SearchView;

import com.example.harmonichyperspace.POJO.SearchResults;
import com.example.harmonichyperspace.R;
import com.example.harmonichyperspace.background.SpotifyAuthTask;
import com.example.harmonichyperspace.background.SpotifyClient;
import com.example.harmonichyperspace.background.SpotifyService;
import com.example.harmonichyperspace.search.search;
import com.example.harmonichyperspace.search.searchResults;

import java.io.UnsupportedEncodingException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class informationSearch extends AppCompatActivity {

    private SearchView mSearchButton;

    public static Intent intentFactory(Context context) {
        Intent intent = new Intent(context, search.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_information_search);
        wireupDisplay();
    }

    private void wireupDisplay() {
        mSearchButton = findViewById(R.id.searchView);

        mSearchButton.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(mSearchButton.getWindowToken(), 0);
                Log.d("search", "searching for: " + query);
                storeSearchandGoToResults(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        mSearchButton.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    // When the SearchView loses focus, hide the keyboard
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
        });
    }

    private void storeSearchandGoToResults(String query) {
        Log.d("search", "searching for: " + query);
        //storing the seach text, using shared preferences
        getSharedPreferences("search", Context.MODE_PRIVATE)
                .edit()
                .putString("search", query)
                .apply();

        //fetch the auth key and make the api call using SpotifyService
        SpotifyAuthTask authTask = new SpotifyAuthTask(new SpotifyAuthTask.SpotifyAuthListener() {
            @Override
            public void onAccessTokenReceived(String accessToken) {
                if (accessToken != null) {
                    searchObject(accessToken, query);
                } else {
                    Log.d("search", "access token is null");
                }
            }
        });
        authTask.execute();
    }

    private void searchObject(String accessToken, String query) {
        SpotifyService spotifyService = SpotifyClient.createSpotifyService(accessToken);
        String type = "track,album,artist";

        //Url encode the query
        String encodedQuery;
        try{
            encodedQuery = java.net.URLEncoder.encode(query, "UTF-8");
        } catch (UnsupportedEncodingException e){
            Log.d("Search", "Error: " + e.getMessage());
            e.printStackTrace();
            return;
        }

        Call<SearchResults> call = spotifyService.search(encodedQuery, type);
        call.enqueue(new Callback<SearchResults>() {
            @Override
            public void onResponse(Call<SearchResults> call, Response<SearchResults> response) {
                if (response.isSuccessful()) {
                    SearchResults searchResult = response.body();

                    // Pass the search results to the searchResults activity
                    Intent intent = new Intent(getApplicationContext(), searchResults.class);
                    intent.putExtra("searchResult", searchResult);
                    startActivity(intent);
                } else {
                    Log.d("Search", "Error: " + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<SearchResults> call, Throwable t) {
                Log.d("Search", "Error: " + t.getMessage());
            }
        });
    }
}