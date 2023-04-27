package com.example.harmonichyperspace.search;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.harmonichyperspace.MainHomePage;
import com.example.harmonichyperspace.R;
import com.example.harmonichyperspace.discovery.genres;
import com.example.harmonichyperspace.profile.profile;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class search extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    private SearchView mSearchButton;

    public static Intent intenFactory(Context context) {
        Intent intent = new Intent(context, search.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_search);
        setNavBar();
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

    private void setNavBar() {
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.nav_home);

        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.nav_home:
                    startActivity(new Intent(getApplicationContext(), MainHomePage.class));
                    overridePendingTransition(0, 0);
                    return true;
                case R.id.nav_discovery:
                    startActivity(new Intent(getApplicationContext(), genres.class));
                    overridePendingTransition(0, 0);
                    return true;
                case R.id.nav_search:
                    return true;
                case R.id.nav_profile:
                    startActivity(new Intent(getApplicationContext(), profile.class));
                    overridePendingTransition(0, 0);
                    return true;
            }
            return false;
        });

    }
}