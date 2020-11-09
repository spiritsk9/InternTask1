package com.nooralhealth.model.room.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.nooralhealth.model.room.model.ClassData;

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
