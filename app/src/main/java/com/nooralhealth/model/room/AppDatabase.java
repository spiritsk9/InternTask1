package com.nooralhealth.model.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.nooralhealth.model.room.dao.ClassTypeDao;
import com.nooralhealth.model.room.dao.MyImageDao;
import com.nooralhealth.model.room.dao.NDao;
import com.nooralhealth.model.room.model.ClassData;
import com.nooralhealth.model.room.model.ClassTypeItem;
import com.nooralhealth.model.room.model.MyImage;


@Database(entities = {ClassTypeItem.class, ClassData.class, MyImage.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    private static final String DB_NAME = "noora_db";
    private static AppDatabase INSTANCE;

    public static AppDatabase getDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, DB_NAME)
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

    public abstract ClassTypeDao getClassTypeDao();

    public abstract NDao nDao();

    public abstract MyImageDao myImageDao();

   public AppDatabase getAppDatabase() {
       return INSTANCE;

   }
}
