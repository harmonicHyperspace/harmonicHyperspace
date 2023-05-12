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

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.harmonichyperspace.DB.User;
import com.example.harmonichyperspace.DB.harmonicHyperspaceDAO;
import com.example.harmonichyperspace.DB.harmonicHyperspaceDatabase;
import com.example.harmonichyperspace.MainHomePage;
import com.example.harmonichyperspace.R;
import com.example.harmonichyperspace.discovery.genres;
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
        mprofilepic = findViewById(R.id.profilePic);
        String urlPic = "https://cdn.discordapp.com/attachments/1020212941146042399/1103180175669202964/IMG_5215.jpg";
        Picasso.get()
                .load(urlPic)
                //.placeholder(R.drawable.applogo)
                //.error(R.drawable.baseline_person_24)
                .into(mprofilepic);
    }
//        mprofilepic = findViewById(R.id.profilePic);
//        //String picture = currentUser.getprofilepic();
////        if(picture == img1){
////            Picasso.get()
////                    .load(R.drawable.applogo)
////                    .placeholder(R.drawable.applogo)
////                    .into(mprofilepic);
////        }
////        else if(picture == img2){
////            Picasso.get()
////                    .load(R.drawable.applogo)
////                    .placeholder(R.drawable.applogo)
////                    .into(mprofilepic);
////        }
////        else{
////            Picasso.get()
////                    .load(R.drawable.applogo)
////                    .placeholder(R.drawable.applogo)
////                    .into(mprofilepic);
////        }
//
//        Picasso.get()
//                .load(R.drawable.applogo)
//                .placeholder(R.drawable.applogo)
//
//                .into(mprofilepic);
//
//
//    }


    private void wiredDisplay() {
        mUsername = findViewById(R.id.usernameView);
        mEditProfile = findViewById(R.id.editProfile);
        mBio = findViewById(R.id.profileInfo);
        mBio.setText(currentUser.getBio());
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
                case R.id.nav_discovery:
                    startActivity(new Intent(getApplicationContext(), genres.class));
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