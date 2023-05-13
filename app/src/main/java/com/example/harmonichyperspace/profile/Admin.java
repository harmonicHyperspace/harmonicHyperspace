package com.example.harmonichyperspace.profile;

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
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.harmonichyperspace.DB.User;
import com.example.harmonichyperspace.DB.harmonicHyperspaceDAO;
import com.example.harmonichyperspace.DB.harmonicHyperspaceDatabase;
import com.example.harmonichyperspace.R;
import com.squareup.picasso.Picasso;

public class Admin extends AppCompatActivity {
    private int mUserId;
    User currentUser;
    User baned;

    private static final String USER_ID_Key = "com.example.harmonichyperspace.useridKey";
    private static final String PREFRENCE_KEY = "com.example.harmonichyperspace.preferenceKey";

    private harmonicHyperspaceDAO mHarmonicHyperspaceDAO;
    private ImageView mprofilepic;
    private Button mback;
    private EditText medit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_admin);
        mHarmonicHyperspaceDAO = Room.databaseBuilder(this, harmonicHyperspaceDatabase.class, harmonicHyperspaceDatabase.DATABASE_NAME)
                .allowMainThreadQueries()
                .build()
                .harmonicHyperspaceDAO();
        setUser();
        wiringUpDisplay();
    }

    private void wiringUpDisplay() {
        medit = findViewById(R.id.BanName);
        mback = findViewById(R.id.bbutton);

        mback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ban();
                Intent intent = (new Intent(getApplicationContext(), profile.class));
                startActivity(intent);
            }
        });

    }

    private void ban() {
        String username = medit.getText().toString().toString();
        baned = mHarmonicHyperspaceDAO.getUserByUsername(username);
        if(baned != null){
            mHarmonicHyperspaceDAO.delete(baned);
            Toast.makeText(this, "User has been banned ", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, "This user( " + username + " ) does not exist", Toast.LENGTH_SHORT).show();
        }
    }

    private void setUser() {
        SharedPreferences preferences = getSharedPreferences(PREFRENCE_KEY, Context.MODE_PRIVATE);
        mUserId = preferences.getInt(USER_ID_Key, -1);
        if (mUserId != -1) {
            currentUser = mHarmonicHyperspaceDAO.getUserByUserId(mUserId);
            if (currentUser == null) {
                Log.e(TAG, "User not found");
            } else {
                String urlPic = currentUser.getProfilePic();
                mprofilepic = findViewById(R.id.imagePrev);
                Picasso.get()
                        .load(urlPic)
                        .into(mprofilepic);


            }
        }else {
            Log.e(TAG, "Invalid user Id");
        }
    }
}
