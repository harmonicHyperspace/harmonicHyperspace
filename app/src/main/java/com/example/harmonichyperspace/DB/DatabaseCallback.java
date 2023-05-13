package com.example.harmonichyperspace.DB;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

public class DatabaseCallback  extends RoomDatabase.Callback{

    private final Context mContext;

    public DatabaseCallback(Context context) {
        mContext = context;
    }

    @Override
    public void onCreate(@NonNull SupportSQLiteDatabase db) {
        super.onCreate(db);
        new Thread(new Runnable() {
            @Override
            public void run() {
                harmonicHyperspaceDAO dao = Room.databaseBuilder(mContext, harmonicHyperspaceDatabase.class, "harmonicHyperspace.db")
                        .allowMainThreadQueries()
                        .build()
                        .harmonicHyperspaceDAO();

                User admin = new User("admin2", "admin2", "admin2" ,"admin","I am the Admin","https://cdn.discordapp.com/attachments/1070198307281969255/1106082024680333392/IMG_5215.jpg",true);
                dao.insert(admin);

                User user = new User("testuser1", "testuser1", "testuser1","Test user","Test User","https://cdn.discordapp.com/attachments/1070198307281969255/1106082024680333392/IMG_5215.jpg",false);
                dao.insert(user);
            }
        }).start();
    }
}
