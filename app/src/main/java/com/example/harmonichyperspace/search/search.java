package com.example.harmonichyperspace.search;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.SearchView;

import com.example.harmonichyperspace.R;

public class search extends AppCompatActivity {

    private SearchView mSearchButton;

    public static Intent intenFactory(Context context) {
        Intent intent = new Intent(context, search.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        wireupDisplay();
    }

    private void wireupDisplay() {
        mSearchButton = findViewById(R.id.searchView);

        mSearchButton.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                storeSearchandGoToResults(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    private void storeSearchandGoToResults(String query) {
        //storing the seach text, using shared preferences
        getSharedPreferences("search", Context.MODE_PRIVATE)
                .edit()
                .putString("search", query)
                .apply();

        //go to the results page
        Intent intent = new Intent(search.this, searchResults.class);
        intent.putExtra("search", query);
        startActivity(intent);
    }
}