package com.example.harmonichyperspace.profile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.harmonichyperspace.DB.User;
import com.example.harmonichyperspace.DB.harmonicHyperspaceDAO;
import com.example.harmonichyperspace.R;
import com.example.harmonichyperspace.DB.harmonicHyperspaceDatabase;

import com.example.harmonichyperspace.landing.signUp;

public class editProfile extends AppCompatActivity {

    private Button mBack;
    private Button mSave;
    private Button mPicture;
    private EditText mEditUsername;
    private EditText mEditName;
    private EditText mEditEmail;
    private EditText mEditBio;
    User currentUser;

    String mNewUserName;
    String mNewUserEmail;
    String mNewUserBio;
    String mNewName;

    private harmonicHyperspaceDAO mHarmonicHyperspaceDAO;

    public static Intent intentFactory(Context context){
        Intent intent = new Intent(context, editProfile.class);
        return intent;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_edit_profile);

        harmonicHyperspaceDatabase db = harmonicHyperspaceDatabase.getInstance(this);
        mHarmonicHyperspaceDAO = db.harmonicHyperspaceDAO();

        wiringUpDisplay();
        mHarmonicHyperspaceDAO = Room.databaseBuilder(this, harmonicHyperspaceDatabase.class, harmonicHyperspaceDatabase.DATABASE_NAME)
                .allowMainThreadQueries()
                .build()
                .harmonicHyperspaceDAO();
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
                Intent intent = new Intent(getApplicationContext(),Image.class);
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
        if(mHarmonicHyperspaceDAO.getUserByUsername(mNewUserBio) != null){
            Toast.makeText(this, "Username " + mNewUserName + " found choose new user name", Toast.LENGTH_SHORT).show();
            return false;
        }
        else{
            return true;
        }
    }

    private boolean emailUsed() {
        if (mNewUserEmail != null) {
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
        currentUser.setUsername(mNewUserName);
        currentUser.setName(mNewName);
        currentUser.setEmail(mNewUserEmail);
        currentUser.setBio(mNewUserBio);
        }
    }

