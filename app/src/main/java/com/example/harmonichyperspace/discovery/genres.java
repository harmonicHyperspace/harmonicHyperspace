package com.example.harmonichyperspace.discovery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.harmonichyperspace.R;
import com.example.harmonichyperspace.landing.logIn;

public class genres extends AppCompatActivity {

    public static Intent intentFactory(Context context) {
        Intent intent = new Intent(context, genres.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_genres);
    }
}