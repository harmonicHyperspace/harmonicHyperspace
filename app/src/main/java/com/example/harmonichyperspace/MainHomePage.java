package com.example.harmonichyperspace;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Window;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.harmonichyperspace.DB.User;
import com.example.harmonichyperspace.DB.harmonicHyperspaceDAO;
import com.example.harmonichyperspace.DB.harmonicHyperspaceDatabase;
import com.example.harmonichyperspace.profile.profile;
import com.example.harmonichyperspace.search.search;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

public class MainHomePage extends AppCompatActivity {

    private static final String USER_ID_KEY = "com.example.harmonichyperspace.useridKey";
    private static final String PREFRENCE_KEY = "com.example.harmonichyperspace.preferenceKey";
    BottomNavigationView bottomNavigationView;
    private int mUserId = -1;
    private harmonicHyperspaceDAO mharmonicHyperspaceDAO;

    public static Intent intentFactory(Context context, int userId) {
        Intent intent = new Intent(context, MainHomePage.class);
        intent.putExtra(USER_ID_KEY, userId);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main_home_page);

        setNavBar();

        getDatabase();

        checkForUser();
    }

    private void setNavBar() {
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.nav_home);

        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.nav_home:
                    return true;
                case R.id.nav_search:
                    startActivity(new Intent(getApplicationContext(), search.class));
                    overridePendingTransition(0, 0);
                    return true;
                case R.id.nav_profile:
                    Intent intent = (new Intent(getApplicationContext(), profile.class));
                    startActivity(intent);
                    overridePendingTransition(0, 0);
                    return true;
            }
            return false;
        });
    }

    private void getDatabase() {
        mharmonicHyperspaceDAO = Room.databaseBuilder(this, harmonicHyperspaceDatabase.class, "harmonicHyperspace.db")
                .allowMainThreadQueries()
                .build()
                .harmonicHyperspaceDAO();
    }

    private void checkForUser() {
        //do we have a user in the intent?
        mUserId = getIntent().getIntExtra(USER_ID_KEY, -1);
        if (mUserId != -1) {
            return;
        }

        //do we have a user in the preferences?
        SharedPreferences preferences = this.getSharedPreferences(PREFRENCE_KEY, Context.MODE_PRIVATE);

        mUserId = preferences.getInt(USER_ID_KEY, -1);

        if (mUserId != -1) {
            return;
        }

        //do we have a user at all?
        List<User> users = mharmonicHyperspaceDAO.getAllUsers();
        if (users.size() <= 0) {
            User defaultUser = new User("admin", "admin", "admin","admin","admin","https://cdn.discordapp.com/attachments/1070198307281969255/1106082024680333392/IMG_5215.jpg", true);
            mharmonicHyperspaceDAO.insert(defaultUser);
        }

    }
}