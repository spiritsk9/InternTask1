package com.nooralhealth.model.room.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.nooralhealth.model.room.model.ClassTypeItem;

import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface ClassTypeDao {

    @Query("SELECT * FROM ClassTypeItem")
    LiveData<List<ClassTypeItem>> getClassTypeItems();

    @Insert(onConflict = REPLACE)
    void insert(ClassTypeItem classData);

    @Insert(onConflict = REPLACE)
    void insertList(List<ClassTypeItem> classData);

    @Delete
    void delete(ClassTypeItem classData);

    @Update
    void update(ClassTypeItem classData);
}
