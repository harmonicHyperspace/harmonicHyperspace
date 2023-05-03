package com.example.harmonichyperspace.profile;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import com.example.harmonichyperspace.R;

public class editProfile extends AppCompatActivity {

    private Button mBack;
    private Button mSave;
    private EditText mEditUsername;
    private EditText eEditName;
    private EditText eEditEmail;
    private EditText eEditBio;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_edit_profile);
    }
}