package com.example.harmonichyperspace;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.harmonichyperspace.DB.User;
import com.example.harmonichyperspace.DB.harmonicHyperspaceDAO;
import com.example.harmonichyperspace.DB.harmonicHyperspaceDatabase;
import com.example.harmonichyperspace.landing.MainActivity;

public class Landing extends AppCompatActivity {
    private TextView mUsernameTextView;
    private Button mAdminAreaButton;
    private Button mLogoutButton;
    private harmonicHyperspaceDAO mHarmonicHyperspaceDAO;
    private User mUser;

    public static Intent intentFactory(Context context, int userId) {
        Intent intent = new Intent(context, Landing.class);
        intent.putExtra("USER_ID", userId);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing2);

        int userId = getIntent().getIntExtra("USER_ID", -1);

        mHarmonicHyperspaceDAO = Room.databaseBuilder(this, harmonicHyperspaceDatabase.class, "harmonicHyperspace.db")
                .allowMainThreadQueries()
                .build()
                .harmonicHyperspaceDAO();

        mUser = mHarmonicHyperspaceDAO.getUserByUserId(userId);

        mUsernameTextView = findViewById(R.id.usernameName);
        mAdminAreaButton = findViewById(R.id.buttonAdminButton);
        mLogoutButton = findViewById(R.id.logoutButtonButton);

        mUsernameTextView.setText("Welcome, " + mUser.getUsername());

        if (mUser.isAdmin()) {
            mAdminAreaButton.setVisibility(View.VISIBLE);
        } else {
            mAdminAreaButton.setVisibility(View.GONE);
        }

        mAdminAreaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: Start AdminAreaActivity
            }
        });

        mLogoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("LoginPrefs", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.apply();

                Intent intent = new Intent(Landing.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}