package com.blockermode.focusmode.uibilllingjava.Entity;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Subscription.class} , exportSchema = false, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract SubscriptionDao subscriptionDao();
    private static final String DB_NAME ="dbname";

    // Singleton pattern to get an instance of the database
    private static AppDatabase instance;

    public static synchronized AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, DB_NAME)
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }

}

