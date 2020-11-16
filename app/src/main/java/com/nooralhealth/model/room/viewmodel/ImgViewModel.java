package com.nooralhealth.model.room.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.nooralhealth.model.room.AppDatabase;
import com.nooralhealth.model.room.model.ClassTypeItem;
import com.nooralhealth.model.room.model.ImagePath;

import java.util.List;


public class ImgViewModel extends AndroidViewModel {

    private AppDatabase appDatabase;

    public ImgViewModel(Application application) {
        super(application);
        appDatabase = AppDatabase.getDatabase(this.getApplication());
    }

    public LiveData<List<ImagePath>> getImagePathItems() {
        return appDatabase.getImgDao().getAll();
    }

    public void addImagePathItem(final ImagePath item) {
        new Thread(() -> appDatabase.getImgDao().insert(item)).start();
    }

    public void deleteImagePathItem(final ImagePath item) {
        new Thread(() -> appDatabase.getImgDao().delete(item)).start();
    }
}
