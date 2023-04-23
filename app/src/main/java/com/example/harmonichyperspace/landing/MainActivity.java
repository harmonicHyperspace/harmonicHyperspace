package com.example.harmonichyperspace.landing;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.harmonichyperspace.R;
import com.example.harmonichyperspace.landing.logIn;
import com.example.harmonichyperspace.landing.signUp;

;

public class MainActivity extends AppCompatActivity {

    private Button mSignUp;
    private Button mLogIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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