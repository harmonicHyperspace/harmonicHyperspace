package com.example.harmonichyperspace.landing;

import static android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.harmonichyperspace.DB.harmonicHyperspaceDAO;
import com.example.harmonichyperspace.DB.harmonicHyperspaceDatabase;
import com.example.harmonichyperspace.R;

public class MainActivity extends AppCompatActivity {

    harmonicHyperspaceDAO mDao;
    private Button mSignUp;
    private Button mLogIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);


        setContentView(R.layout.activity_main);

        wireupDisplay();
        mDao = Room.databaseBuilder(this, harmonicHyperspaceDatabase.class, harmonicHyperspaceDatabase.DATABASE_NAME)
                .allowMainThreadQueries()
                .build()
                .harmonicHyperspaceDAO();

    }

    private void wireupDisplay() {
        mLogIn = findViewById(R.id.alreadyHaveAnAccount);
        mSignUp = findViewById(R.id.createAccountButton);

        mLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startLogIn();
            }
        });

        mSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startSignUp();
            }
        });
    }

    private void startSignUp() {
        Intent intent = new Intent(getApplicationContext(), signUp.class);
        startActivity(intent);
    }

    private void startLogIn() {
        Intent intent = new Intent(getApplicationContext(), logIn.class);
        startActivity(intent);

    }

    private void logoutUser() {
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);
        alertBuilder.setMessage("Are you sure you want to logout?");

        alertBuilder.setPositiveButton(("yes"),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        clearUserFromPref();
                    }
        });
        alertBuilder.setNegativeButton(("no"),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

        alertBuilder.create().show();
    }

    private void clearUserFromPref() {
        Toast.makeText(this, "clear users not yet implemented", Toast.LENGTH_SHORT).show();
    }
}