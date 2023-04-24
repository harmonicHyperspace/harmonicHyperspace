package com.example.harmonichyperspace.landing;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.harmonichyperspace.DB.DatabaseCallback;
import com.example.harmonichyperspace.DB.harmonicHyperspaceDAO;
import com.example.harmonichyperspace.DB.harmonicHyperspaceDatabase;
import com.example.harmonichyperspace.Landing;
import com.example.harmonichyperspace.MainHomePage;
import com.example.harmonichyperspace.R;
import com.example.harmonichyperspace.DB.User;

public class logIn extends AppCompatActivity {

    private EditText mUsernameField;
    private EditText mPasswordField;

    private Button mLogIn;
    private Button mGoogleLogIn;

    private String mUsername;
    private String mPassword;
    private harmonicHyperspaceDAO mHarmonicHyperspaceDAO;
    private User mUser;

    public static Intent intentFactory(Context context) {
        Intent intent = new Intent(context, logIn.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        wireupDisplay();

        getDatabase();
    }

    private void wireupDisplay() {
        mUsernameField = findViewById(R.id.usernameInput);
        mPasswordField = findViewById(R.id.passwordInput);
        mLogIn = findViewById(R.id.nextButton);
        mGoogleLogIn = findViewById(R.id.googleButton);

        mLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getValuesFromDisplay();
                if(checkForUserInDatabase()) {
                    if(!validatePassword()){
                        Toast.makeText(getApplicationContext(), "Incorrect password", Toast.LENGTH_SHORT).show();
                    }else{
                        Intent intent = Landing.intentFactory(getApplicationContext(), mUser.getUserId());
                        startActivity(intent);
                    }
                };
            }
        });
    }

    private boolean validatePassword() {
        return mUser.getPassword().equals(mPassword);
    }

    private boolean checkForUserInDatabase() {
        mUser = mHarmonicHyperspaceDAO.getUserByUsername(mUsername);
        if(mUser == null) {
            Toast.makeText(this, "No user " + mUsername + " found", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void getValuesFromDisplay() {
        mUsername = mUsernameField.getText().toString();
        mPassword = mPasswordField.getText().toString();
    }

    private void getDatabase() {
        mHarmonicHyperspaceDAO = Room.databaseBuilder(this, harmonicHyperspaceDatabase.class, "harmonicHyperspace.db")
                .allowMainThreadQueries()
                .addCallback(new DatabaseCallback(this))
                .build()
                .harmonicHyperspaceDAO();


    }
}