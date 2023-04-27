package com.example.harmonichyperspace.discovery;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.harmonichyperspace.MainHomePage;
import com.example.harmonichyperspace.R;
import com.example.harmonichyperspace.profile.profile;
import com.example.harmonichyperspace.search.search;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class albums extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_albulms);

        setNavBar();
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
                    startActivity(new Intent(getApplicationContext(), search.class));
                    overridePendingTransition(0, 0);
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