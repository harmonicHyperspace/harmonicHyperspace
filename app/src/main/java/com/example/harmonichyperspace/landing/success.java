package com.example.harmonichyperspace.landing;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.harmonichyperspace.R;

public class success extends AppCompatActivity {

    public static Intent intentFactory(Context applicationContext) {
        Intent intent = new Intent(applicationContext, success.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success);
    }
}