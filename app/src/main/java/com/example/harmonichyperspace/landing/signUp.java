package com.example.harmonichyperspace.landing;

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
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.harmonichyperspace.DB.User;
import com.example.harmonichyperspace.DB.harmonicHyperspaceDAO;
import com.example.harmonichyperspace.DB.harmonicHyperspaceDatabase;
import com.example.harmonichyperspace.MainHomePage;
import com.example.harmonichyperspace.R;

public class signUp extends AppCompatActivity {
    private EditText mUsernameField;
    private EditText mPasswordField;
    private EditText mEmailField;
    private Button mGoogleButton;
    private Button mSignUpButton;
    private String username;
    private String password;
    private String email;
    private User mUser;
    private User mEmail;

    private harmonicHyperspaceDAO mHarmonicHyperspaceDAO;

    public static Intent intentFactory(Context context) {
        Intent intent = new Intent(context, signUp.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_sign_up);

        harmonicHyperspaceDatabase db = harmonicHyperspaceDatabase.getInstance(this);
        mHarmonicHyperspaceDAO = db.harmonicHyperspaceDAO();

        wiringUpDisplay();
        mHarmonicHyperspaceDAO = Room.databaseBuilder(this, harmonicHyperspaceDatabase.class, harmonicHyperspaceDatabase.DATABASE_NAME)
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
                if (checkForUserInDatabase()) {
                    if (emailInDataBase()) {
                        User newUser = createUser();
                        if(newUser != null) {
                            startApp(newUser);
                        }
                    }
                }
            }
        });
//        mGoogleButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
    }

    private boolean emailInDataBase() {
        mEmail = mHarmonicHyperspaceDAO.getUserByEmail(email);
        if (mEmail != null) {
            Toast.makeText(this, "Email " + email + " has been used before, choose new email", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private boolean checkForUserInDatabase() {
        mUser = mHarmonicHyperspaceDAO.getUserByUsername(username);
        if (mUser != null) {
            Toast.makeText(this, "Username " + username + " found choose new user name", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void getValuesFromDisplay() {
        username = mUsernameField.getText().toString().trim();
        password = mPasswordField.getText().toString().trim();
        email = mEmailField.getText().toString().trim();

    }

    private User createUser() {

        User newUser = new User(username, password, email, false);

        if (mHarmonicHyperspaceDAO != null) {
            mHarmonicHyperspaceDAO.insert(newUser);
            saveUserId(newUser.getUserId());
            return newUser;
        } else {
            Log.e(TAG, "DAO object is null");
            return null;
        }
    }

    private void startApp(User newUser) {
        Intent intent = MainHomePage.intentFactory(getApplicationContext(), newUser.getUserId());
        startActivity(intent);
    }

    private void saveUserId(int userId) {
        SharedPreferences sharedPreferences = getSharedPreferences("hyperspaceData", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("userId", String.valueOf(userId));
        editor.apply();
    }
}