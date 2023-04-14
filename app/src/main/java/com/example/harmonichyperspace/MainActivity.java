package com.example.harmonichyperspace;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.harmonichyperspace.DB.harmonicHyperspaceDAO;
import com.example.harmonichyperspace.DB.harmonicHyperspaceDatabase;
import com.example.harmonichyperspace.landing.landing;
import com.example.harmonichyperspace.landing.logIn;
import com.example.harmonichyperspace.profile.User;

import java.util.List;

;

public class MainActivity extends AppCompatActivity {

    private static final String USER_ID_KEY = "com.example.harmonichyperspace.useridKey";
    private static final String PREFRENCE_KEY = "com.example.harmonichyperspace.preferenceKey";
    private int mUserId = -1;

    private harmonicHyperspaceDAO mharmonicHyperspaceDAO;

    public static Intent intentFactory(Context context, int userId) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra(USER_ID_KEY, userId);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getDatabase();

        checkForUser();

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
            User defaultUser = new User("default", "default");
            mharmonicHyperspaceDAO.insert(defaultUser);
        }

        Intent intent = landing.intentFactory(this);
        startActivity(intent);
    }
}