package com.example.harmonichyperspace.landing;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.harmonichyperspace.R;

public class landing extends AppCompatActivity {

    private Button mSignUp;
    private Button mLogIn;

    public static Intent intentFactory(Context context) {
        Intent intent = new Intent(context, landing.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);

        wireupDisplay();
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

}