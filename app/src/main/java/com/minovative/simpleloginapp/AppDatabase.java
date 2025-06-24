package com.minovative.simpleloginapp;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;

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
