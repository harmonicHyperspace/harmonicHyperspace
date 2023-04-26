package com.example.harmonichyperspace.profile;

import androidx.appcompat.app.AppCompatActivity;
import com.example.harmonichyperspace.DB.harmonicHyperspaceDatabase;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import com.example.harmonichyperspace.DB.harmonicHyperspaceDAO;
import com.example.harmonichyperspace.R;

public class profile extends AppCompatActivity {

    private Button mEditProfile;
    private String mUsername;
    private harmonicHyperspaceDAO mHarmonicHyperspaceDAO;

    private static Intent intentFactory(Context context){
        Intent intent = new Intent(context, profile.class);
        return intent;
    }



}