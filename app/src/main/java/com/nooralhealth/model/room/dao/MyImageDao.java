package com.nooralhealth.model.room.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.nooralhealth.model.room.model.MyImage;

import java.util.List;

/**
 * Created by asif on 12/24/17.
 */
@Dao
public interface MyImageDao {
    @Query("SELECT * FROM myimage")
    List<MyImage> getAll();

    @Query("SELECT * FROM myimage ORDER BY UID DESC LIMIT 0,1")
    MyImage last();

    @Query("SELECT * FROM myimage ORDER BY UID ASC LIMIT 0,1")
    MyImage first();

    @Insert
    void insertAll(MyImage... myImages);

    @Delete
    void delete(MyImage myImage);
}
