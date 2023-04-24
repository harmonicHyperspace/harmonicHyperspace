package com.example.harmonichyperspace.landing;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.harmonichyperspace.R;

public class testAdmin extends AppCompatActivity {

    public static Intent intentFactory(Context context) {
        Intent intent = new Intent(context, testAdmin.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_admin);
    }
}