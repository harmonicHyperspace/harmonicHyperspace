package com.example.harmonichyperspace.profile;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.harmonichyperspace.DB.User;
import com.example.harmonichyperspace.DB.harmonicHyperspaceDAO;
import com.example.harmonichyperspace.DB.harmonicHyperspaceDatabase;
import com.example.harmonichyperspace.MainHomePage;
import com.example.harmonichyperspace.R;
import com.example.harmonichyperspace.search.search;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.squareup.picasso.Picasso;

public class image extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;
    private static final String USER_ID_Key = "com.example.harmonichyperspace.useridKey";
    private static final String PREFRENCE_KEY = "com.example.harmonichyperspace.preferenceKey";
    private User currentUser;
    private harmonicHyperspaceDAO mHarmonicHyperspaceDAO;
    private Button mPreview;
    private Button mSave;
    private ImageView mProfileImage;
    private EditText mUrl;
    private String urlPic;
    private int mUserId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_image);
        mHarmonicHyperspaceDAO = Room.databaseBuilder(this, harmonicHyperspaceDatabase.class, harmonicHyperspaceDatabase.DATABASE_NAME)
                .allowMainThreadQueries()
                .build()
                .harmonicHyperspaceDAO();
        
        getUser();
        setDisplay();
        wiringUpDisplay();
    }

    private void wiringUpDisplay() {
        //mPreview = findViewById(R.id.refresh);
        mUrl = findViewById(R.id.urlPic);
        mSave = findViewById(R.id.saveImage);
//
        mSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                urlPic = mUrl.getText().toString().trim();
                currentUser.setProfilePic(urlPic);
                mHarmonicHyperspaceDAO.update(currentUser);
                Intent intent = (new Intent(getApplicationContext(), editProfile.class));
                        startActivity(intent);
            }
        });

//        mPreview.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                setDisplay();
//            }
//        });

    }

    private void setDisplay() {
        mProfileImage = findViewById(R.id.imagePrev);
        String urlPic = currentUser.getProfilePic();
        Picasso.get()
                .load(urlPic)
                .into(mProfileImage);
    }

    private void getUser() {
        SharedPreferences preferences = getSharedPreferences(PREFRENCE_KEY, Context.MODE_PRIVATE);
        mUserId = preferences.getInt(USER_ID_Key, -1);
        if (mUserId != -1) {
            currentUser = mHarmonicHyperspaceDAO.getUserByUserId(mUserId);
            if (currentUser == null) {
                Log.e(TAG, "User not found");
            } else {

            }
        }else {
            Log.e(TAG, "Invalid user Id");
        }
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
    //private void wiringUpDisplay() {
//        mImg1 = findViewById(R.id.img1);
//        mImg2 = findViewById(R.id.img2);
//        mImg3 = findViewById(R.id.img3);
//        mSave = findViewById(R.id.saveImage);
//        mProfileImage = findViewById(R.id.imagePrev);
//        mpImg1 = findViewById(R.id.image1);
//        mpImg2 = findViewById(R.id.image2);
//        mpImg3 = findViewById(R.id.image3);
//
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
//        Picasso.get()
//                .load(R.drawable.applogo)
//                .placeholder(R.drawable.applogo)
//
//                .into(mProfileImage);
//
//        Picasso.get().load(R.drawable.applogo).into(mpImg1);
//        Picasso.get().load(R.drawable.baseline_person_24).into(mpImg2);
//        Picasso.get().load(R.drawable.baseline_add_24).into(mpImg3);
//
//        mImg1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                //currentUser.setPic("img1");
//            }
//        });
//        mImg2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                //currentUser.setPic("img2");
//            }
//        });
//        mImg3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                //currentUser.setPic("img3");
//            }
//        });
//        mSave.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = (new Intent(getApplicationContext(), editProfile.class));
//                startActivity(intent);
//            }
//        });
//    }