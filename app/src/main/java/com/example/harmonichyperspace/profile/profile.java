package com.example.harmonichyperspace.profile;

import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.SharedMemory;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.harmonichyperspace.DB.User;
import com.example.harmonichyperspace.DB.harmonicHyperspaceDAO;
import com.example.harmonichyperspace.DB.harmonicHyperspaceDatabase;
import com.example.harmonichyperspace.MainHomePage;
import com.example.harmonichyperspace.R;
import com.example.harmonichyperspace.landing.MainActivity;
import com.example.harmonichyperspace.search.search;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.squareup.picasso.Picasso;

public class profile extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;

    private Button mEditProfile;
    private TextView mUsername;
    private TextView mBio;
    private ImageView mprofilepic;
    private int mUserId;
    private Button mLogout;
    private Button mBan;
    User currentUser;
    private static final String USER_ID_Key = "com.example.harmonichyperspace.useridKey";
    private static final String PREFRENCE_KEY = "com.example.harmonichyperspace.preferenceKey";

    private harmonicHyperspaceDAO mHarmonicHyperspaceDAO;

    private static Intent intentFactory(Context context){
        Intent intent = new Intent(context, profile.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_profile);

        harmonicHyperspaceDatabase db = harmonicHyperspaceDatabase.getInstance(this);
        mHarmonicHyperspaceDAO = db.harmonicHyperspaceDAO();
//        mHarmonicHyperspaceDAO = Room.databaseBuilder(this, harmonicHyperspaceDatabase.class, harmonicHyperspaceDatabase.DATABASE_NAME)
//                .allowMainThreadQueries()
//                .build()
//                .harmonicHyperspaceDAO();

        getUser();
        image();
        setNavBar();
        wiredDisplay();
        if(currentUser.isAdmin()){
            removeuser();
        }

    }

    private void removeuser() {
        mBan = findViewById(R.id.Admin);
        mBan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Admin.class);
                startActivity(intent);
            }
        });

    }


    private void getUser() {
        SharedPreferences preferences = getSharedPreferences(PREFRENCE_KEY, Context.MODE_PRIVATE);
        mUserId = preferences.getInt(USER_ID_Key, -1);
        if (mUserId != -1) {
            currentUser = mHarmonicHyperspaceDAO.getUserByUserId(mUserId);
            if (currentUser == null) {
                Log.e(TAG, "User not found");
            } else {
                String username = currentUser.getUsername();
                mUsername = findViewById(R.id.usernameView);
                mUsername.setText(username);


            }
        }else {
            Log.e(TAG, "Invalid user Id");
        }
    }


    private void image(){
        String urlPic = currentUser.getProfilePic();
        mprofilepic = findViewById(R.id.profilePic);
        Picasso.get()
                .load(urlPic)
                .into(mprofilepic);
    }


    private void wiredDisplay() {
        mLogout = findViewById(R.id.Logout);
        mUsername = findViewById(R.id.usernameView);
        mEditProfile = findViewById(R.id.editProfile);
        mBio = findViewById(R.id.profileInfo);
        mBio.setText(currentUser.getBio());

        mLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
        mEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startEditProfile();
            }
        });

    }

    private void startEditProfile(){
        Intent intent = new Intent(getApplicationContext(), editProfile.class);
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
                case R.id.nav_search:
                    startActivity(new Intent(getApplicationContext(), search.class));
                    overridePendingTransition(0, 0);
                    return true;
                case R.id.nav_profile:
                    return true;
            }
            return false;
        });

    }
}