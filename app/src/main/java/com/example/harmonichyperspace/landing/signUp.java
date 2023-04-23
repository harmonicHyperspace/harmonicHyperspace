package com.example.harmonichyperspace.landing;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.harmonichyperspace.DB.harmonicHyperspaceDAO;
import com.example.harmonichyperspace.DB.harmonicHyperspaceDatabase;
import com.example.harmonichyperspace.R;
import com.example.harmonichyperspace.discovery.genres;
import com.example.harmonichyperspace.DB.User;

public class signUp extends AppCompatActivity {

    private EditText mUsernameField;
    private EditText mPasswordField;
    private EditText mEmailField;

    private Button mGoogleButton;
    private Button mSignUpButton;

    private String username;
    private String password;
    private String email;
    harmonicHyperspaceDAO mDao;



    private harmonicHyperspaceDAO mHarmonicHyperspaceDAO;

    public static Intent intentFactory(Context context){
        Intent intent = new Intent(context, signUp.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        wiringUpDisplay();
        mDao = Room.databaseBuilder(this, harmonicHyperspaceDatabase.class, harmonicHyperspaceDatabase.DATABASE_NAME)
                .allowMainThreadQueries()
                .build()
                .harmonicHyperspaceDAO();
    }

    private void wiringUpDisplay() {
        mUsernameField = findViewById(R.id.usernameInput);
        mPasswordField = findViewById(R.id.passwordInput);
        mEmailField = findViewById(R.id.emailInput);

        mGoogleButton = findViewById(R.id.googleButton);
        mSignUpButton = findViewById(R.id.nextButton);

        mSignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getValuesFromDisplay();
                startApp();
            }
        });
//        mGoogleButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
    }

    private void getValuesFromDisplay() {
        username = mUsernameField.getText().toString().trim();
        password = mPasswordField.getText().toString().trim();
        email = mEmailField.getText().toString().trim();

        User newUser = new User(username, password);
        mHarmonicHyperspaceDAO.insert(newUser);
    }

    private void startApp() {
        Intent intent = genres.intentFactory(getApplicationContext());
        startActivity(intent);
    }
}