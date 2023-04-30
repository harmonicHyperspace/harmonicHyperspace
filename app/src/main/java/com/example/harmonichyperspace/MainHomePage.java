package com.example.harmonichyperspace;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.harmonichyperspace.DB.User;
import com.example.harmonichyperspace.DB.harmonicHyperspaceDAO;
import com.example.harmonichyperspace.DB.harmonicHyperspaceDatabase;
import com.example.harmonichyperspace.discovery.genres;
import com.example.harmonichyperspace.landing.MainActivity;
import com.example.harmonichyperspace.profile.profile;
import com.example.harmonichyperspace.search.search;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Calendar;
import java.util.List;

public class MainHomePage extends AppCompatActivity {

    private static final String USER_ID_KEY = "com.example.harmonichyperspace.useridKey";
    private static final String PREFRENCE_KEY = "com.example.harmonichyperspace.preferenceKey";
    BottomNavigationView bottomNavigationView;
    private int mUserId = -1;
    private harmonicHyperspaceDAO mharmonicHyperspaceDAO;

    public static Intent intentFactory(Context context, int userId) {
        Intent intent = new Intent(context, MainHomePage.class);
        intent.putExtra(USER_ID_KEY, userId);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main_home_page);

        setNavBar();

        getDatabase();

        checkForUser();

        updateWelcomeMessage();
    }

    private void setNavBar() {
        setTopNavBar();
        setBottomNavBar();
    }

    private void setTopNavBar() {
        ImageButton searchButton = findViewById(R.id.searchButton);
        ImageButton profileButton = findViewById(R.id.profileButton);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), search.class));
            }
        });

        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //show profile menu with logout option
                showProfileMenu(view);
            }
        });
    }

    private void showProfileMenu(View anchorView) {
        PopupMenu popup = new PopupMenu(this, anchorView);
        popup.getMenuInflater().inflate(R.menu.profile_menu, popup.getMenu());
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case (R.id.logOutMenuItem):
                        //log out
                        logOutUser();
                        return true;
                    default:
                        return false;
                }
            }
        });
        popup.show();
    }


    private void setBottomNavBar() {
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.nav_home);

        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.nav_home:
                    return true;
                case R.id.nav_discovery:
                    startActivity(new Intent(getApplicationContext(), genres.class));
                    overridePendingTransition(0, 0);
                    return true;
                case R.id.nav_search:
                    startActivity(new Intent(getApplicationContext(), search.class));
                    overridePendingTransition(0, 0);
                    return true;
                case R.id.nav_profile:
                    startActivity(new Intent(getApplicationContext(), profile.class));
                    overridePendingTransition(0, 0);
                    return true;
            }
            return false;
        });
    }

    private void getDatabase() {
        mharmonicHyperspaceDAO = Room.databaseBuilder(this, harmonicHyperspaceDatabase.class, "harmonicHyperspace.db")
                .allowMainThreadQueries()
                .build()
                .harmonicHyperspaceDAO();
    }

    private void checkForUser() {
        //do we have a user in the intent?
        mUserId = getIntent().getIntExtra(USER_ID_KEY, -1);
        if (mUserId != -1) {
            return;
        }

        //do we have a user in the preferences?
        SharedPreferences preferences = this.getSharedPreferences(PREFRENCE_KEY, Context.MODE_PRIVATE);

        mUserId = preferences.getInt(USER_ID_KEY, -1);

        if (mUserId != -1) {
            return;
        }

        //do we have a user at all?
        List<User> users = mharmonicHyperspaceDAO.getAllUsers();
        if (users.size() <= 0) {
            User defaultUser = new User("admin", "admin", "admin", true);
            mharmonicHyperspaceDAO.insert(defaultUser);
        }

    }

    private void logOutUser() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to log out?");

        builder.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        clearUserfromPref();
                    }
                });
        builder.setNegativeButton("No",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        builder.create().show();
    }

    private String getWelcomeMessage(String username) {
        Calendar calendar = Calendar.getInstance();
        int timeOfDay = calendar.get(Calendar.HOUR_OF_DAY);
        String greeting;

        if (timeOfDay >= 5 && timeOfDay < 12) {
            greeting = "Good Morning";
        } else if (timeOfDay >= 12 && timeOfDay < 16) {
            greeting = "Good Afternoon";
        } else if (timeOfDay >= 16 && timeOfDay < 21) {
            greeting = "Good Evening";
        } else {
            greeting = "Good Night";
        }

        return greeting + ", " + username;
    }

    private void updateWelcomeMessage() {
        TextView welcomeMessageView = findViewById(R.id.welcomeMessage);
        User user = mharmonicHyperspaceDAO.getUserByUserId(mUserId);
        if (user != null) {
            String welcomeMessage = getWelcomeMessage(user.getUsername());
            welcomeMessageView.setText(welcomeMessage);
        }
    }

    private void clearUserfromPref() {
        SharedPreferences preferences = getSharedPreferences(PREFRENCE_KEY, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(USER_ID_KEY, -1);
        editor.apply();
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }
}