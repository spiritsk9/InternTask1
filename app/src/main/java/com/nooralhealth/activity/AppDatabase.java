package com.nooralhealth.activity;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {ClassData.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract TaskDao taskDao();
}
