package com.nooralhealth.activity.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.lifecycle.LiveData;

import com.nooralhealth.R;
import com.nooralhealth.activity.AttendanceActivity;
import com.nooralhealth.activity.base.BaseActivity;
import com.nooralhealth.model.room.model.ClassTypeItem;
import com.nooralhealth.utill.MyLg;
import com.nooralhealth.view.adapter.Adapter_ClassItem;

import java.util.ArrayList;
import java.util.List;

public class SplashActivity extends BaseActivity {

    private static final String TAG = SplashActivity.class.getSimpleName();

    boolean dataCheck = false;
    boolean splashDone = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(() -> {
            splashDone = true;
            openActivity();
        }, 3000);

        getClassTypeList();
    }

    private void openActivity() {
        if (splashDone && dataCheck) {
            Intent i = new Intent(SplashActivity.this, AttendanceActivity.class);
            startActivity(i);
            finish();
        }
    }

    //For ClassType
    private LiveData<List<ClassTypeItem>> classTypeItemsLive;
    private List<ClassTypeItem> classTypeItems;

    private void getClassTypeList() {
        removeClassTypeObservables();
        classTypeItemsLive = classTypeViewModel.getClassTypeItems();
        classTypeItemsLive.observe(this, list -> {
            if (list != null) {
                MyLg.e(TAG, "ClassType list size " + list.size());
                if (list.size() != 0) {
                    classTypeItems = list;
                } else {
                    classTypeItems = null;  //In case last media deleted
                }
            } else {
                MyLg.e(TAG, "ClassType list null");
                classTypeItems = null; //In case last media deleted
            }
            refreshData();
        });
    }

    private void refreshData() {
        removeClassTypeObservables();
        if (classTypeItems == null) {
            classTypeItems= new ArrayList<>();
            classTypeItems.add(new ClassTypeItem("ANC"));
            classTypeItems.add(new ClassTypeItem("SNCU"));
            classTypeItems.add(new ClassTypeItem("PNC"));
            classTypeItems.add(new ClassTypeItem("Inpatient"));
            classTypeItems.add(new ClassTypeItem("Cardiac Surgery"));
            classTypeItems.add(new ClassTypeItem("Cardiology"));
            classTypeItems.add(new ClassTypeItem("Oncology"));
            classTypeViewModel.addClassTypeItems(classTypeItems);
        }
        dataCheck = true;
        openActivity();
    }

    private void removeClassTypeObservables() {
        try {
            if (classTypeItemsLive != null) {
                classTypeItemsLive.removeObservers(this);
                MyLg.e(TAG, "ClassType Removed Observers");
            }
        } catch (Exception e) {
            MyLg.printStackTrace(e);
        }
    }


    @Override
    public void onDestroy() {
        try {
            removeClassTypeObservables();
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.onDestroy();
    }
}