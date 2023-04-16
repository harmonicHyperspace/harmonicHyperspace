package com.example.harmonichyperspace.DB;

import android.content.Context;

import androidx.room.AutoMigration;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(
        version = 3,
        entities = {User.class, SongReview.class, AlbumReview.class},
        exportSchema = true,
        autoMigrations = {
                @AutoMigration(from = 1, to = 2),
                @AutoMigration(from = 2, to = 3)
        }
)
public abstract class harmonicHyperspaceDatabase extends RoomDatabase {
    public static final String DATABASE_NAME = "harmonicHyperspace.db";
    public static final String HARMONIC_TABLE = "harmonic_table";
    public static final String USER_TABLE = "user_table";
    public static final String SONG_REVIEW_TABLE = "song_review_table";
    public static final String ALBUM_REVIEW_TABLE = "album_review_table";
    private static final Object LOCK = new Object();
    private static volatile harmonicHyperspaceDatabase instance;

    public static harmonicHyperspaceDatabase getInstance(Context context) {
        if (instance == null) {
            synchronized (LOCK) {
                if (instance == null) {
                    instance = Room.databaseBuilder(context.getApplicationContext(),
                                    harmonicHyperspaceDatabase.class, DATABASE_NAME)
                            .build();
                }
            }
        }
        return instance;
    }

    public abstract harmonicHyperspaceDAO harmonicHyperspaceDAO();

}
