package com.minovative.simpleloginapp;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {User.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public AppDatabase( ) {
    }
    public abstract UserDao userDao();

    private static AppDatabase INSTANCE = null;

    public static synchronized AppDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "may-database")
                    .fallbackToDestructiveMigration().build();
        } return INSTANCE;
    }
}
