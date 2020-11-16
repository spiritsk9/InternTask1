package com.nooralhealth.model.room.dao;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;


import com.nooralhealth.model.room.model.ImagePath;

import java.util.List;

@Dao
public interface ImgDao {

    @Query("SELECT * FROM imagepath")
    List<ImagePath> getAll();

    @Insert
    void insert(ImagePath path);

    @Delete
    void delete(ImagePath path);

    @Update
    void update(ImagePath path);

}
