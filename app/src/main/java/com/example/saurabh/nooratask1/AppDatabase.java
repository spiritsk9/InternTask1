package com.example.saurabh.nooratask1;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {ClassData.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract NDao nDao();
}
