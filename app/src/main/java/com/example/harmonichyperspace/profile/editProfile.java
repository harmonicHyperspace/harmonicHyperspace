package com.example.harmonichyperspace.profile;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

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
import android.widget.Toast;

import com.example.harmonichyperspace.DB.User;
import com.example.harmonichyperspace.DB.harmonicHyperspaceDAO;
import com.example.harmonichyperspace.R;
import com.example.harmonichyperspace.DB.harmonicHyperspaceDatabase;

import com.squareup.picasso.Picasso;

public class editProfile extends AppCompatActivity {

    private Button mBack;
    private ImageView mProfile;
    private Button mSave;
    private Button mPicture;
    private EditText mEditUsername;
    private EditText mEditName;
    private EditText mEditEmail;
    private EditText mEditBio;
    User currentUser;
    private int mUserId;

    String mNewUserName;
    String mNewUserEmail;
    String mNewUserBio;
    String mNewName;
    private static final String USER_ID_Key = "com.example.harmonichyperspace.useridKey";
    private static final String PREFRENCE_KEY = "com.example.harmonichyperspace.preferenceKey";

    private harmonicHyperspaceDAO mHarmonicHyperspaceDAO;
    private ImageView mprofilepic;

    public static Intent intentFactory(Context context){
        Intent intent = new Intent(context, editProfile.class);
        return intent;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_edit_profile);
        mHarmonicHyperspaceDAO = Room.databaseBuilder(this, harmonicHyperspaceDatabase.class, harmonicHyperspaceDatabase.DATABASE_NAME)
                .allowMainThreadQueries()
                .build()
                .harmonicHyperspaceDAO();
        setUser();
        wiringUpDisplay();
    }

    private void setUser() {
        SharedPreferences preferences = getSharedPreferences(PREFRENCE_KEY,Context.MODE_PRIVATE);
        mUserId = preferences.getInt(USER_ID_Key,-1);
        if (mUserId != 1){
            currentUser = mHarmonicHyperspaceDAO.getUserByUserId(mUserId);
            if(currentUser == null){
                Log.e(TAG, "User not found");
            }
            else{
                mProfile = findViewById(R.id.editPic);
                String urlPic = currentUser.getProfilePic();
                Picasso.get()
                        .load(urlPic)
                        .into(mProfile);

            }
        }else{
            Log.e(TAG, "Invalid User Id");
        }
    }

    private void wiringUpDisplay(){
        mEditUsername = findViewById(R.id.editUsername);
        mEditName = findViewById(R.id.editName);
        mEditBio = findViewById(R.id.editBio);
        mEditEmail = findViewById(R.id.editEmail);
        mBack = findViewById(R.id.back);
        mSave = findViewById(R.id.save);
        mPicture = findViewById(R.id.picButton);

        mPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), image.class);
                startActivity(intent);
            }
        });

        mBack.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                returnProfile();
            }
        }));

        mSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getValues();
                if(usernameUsed()){
                    if(emailUsed()){
                        updateUser();

                    }
                }
            }
        });
    }

    private void getValues(){
        mNewUserName = mEditUsername.getText().toString().trim();
        mNewUserEmail = mEditEmail.getText().toString().trim();
        mNewUserBio = mEditBio.getText().toString().trim();
        mNewName = mEditName.getText().toString().trim();
    }

    private boolean usernameUsed(){
        if (mNewUserName == ""){
            return true;
        }
        if(mHarmonicHyperspaceDAO.getUserByUsername(mNewUserName) != null){
            Toast.makeText(this, "Username " + mNewUserName + " found choose new user name", Toast.LENGTH_SHORT).show();
            return false;
        }
        else{
            return true;
        }
    }

    private boolean emailUsed() {
        if (mNewUserEmail == ""){
            return true;
        }
        if (mHarmonicHyperspaceDAO.getUserByEmail(mNewUserEmail) != null){
            Toast.makeText(this, "Email " + mNewUserEmail + " has already been used", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }
    }
    private void returnProfile(){
        Intent intent = (new Intent(getApplicationContext(), profile.class));
        startActivity(intent);
    }

    private void updateUser(){
        if(mNewUserName.trim().length() > 0){
            currentUser.setUsername(mNewUserName);
        }
        if(mNewName.trim().length() > 0){
            currentUser.setName(mNewName);
        }
        if(mNewUserEmail.trim().length() > 0){
            currentUser.setEmail(mNewUserEmail);
        }
        if(mNewUserBio.trim().length() > 0){
            currentUser.setBio(mNewUserBio);
        }

        mHarmonicHyperspaceDAO.update(currentUser);
        }
    }

