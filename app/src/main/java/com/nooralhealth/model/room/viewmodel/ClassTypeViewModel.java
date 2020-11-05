package com.nooralhealth.model.room.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;


import com.nooralhealth.model.room.AppDatabase;
import com.nooralhealth.model.room.model.ClassTypeItem;

import java.util.List;


public class ClassTypeViewModel extends AndroidViewModel {

    private AppDatabase appDatabase;

    public ClassTypeViewModel(Application application) {
        super(application);
        appDatabase = AppDatabase.getDatabase(this.getApplication());
    }

    public LiveData<List<ClassTypeItem>> getClassTypeItems() {
        return appDatabase.getClassTypeDao().getClassTypeItems();
    }

    public void addClassTypeItems(final List<ClassTypeItem> item) {
        new Thread(() -> appDatabase.getClassTypeDao().insertList(item)).start();
    }

    public void addClassTypeItem(final ClassTypeItem item) {
        new Thread(() -> appDatabase.getClassTypeDao().insert(item)).start();
    }

    public void deleteClassTypeItem(final ClassTypeItem item) {
        new Thread(() -> appDatabase.getClassTypeDao().delete(item)).start();
    }
}
