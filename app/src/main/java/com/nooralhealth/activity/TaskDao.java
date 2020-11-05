package com.nooralhealth.activity;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface TaskDao {

    @Query("SELECT * FROM classdata")
    List<ClassData> getAll();

    @Insert
    void insert(ClassData classData);

    @Delete
    void delete(ClassData classData);

    @Update
    void update(ClassData classData);

}
