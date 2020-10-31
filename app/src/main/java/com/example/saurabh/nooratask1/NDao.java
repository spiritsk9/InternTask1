package com.example.saurabh.nooratask1;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface NDao {

    @Query("SELECT * FROM classdata")
    List<ClassData> getAll();

    @Insert
    void insert(ClassData task);

    @Delete
    void delete(ClassData task);

    @Update
    void update(ClassData task);

}
