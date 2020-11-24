package com.nooralhealth.fcm.model;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;


public class MyFirebaseInstanceIdService extends FirebaseInstanceIdService {



    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();


        String token = FirebaseInstanceId.getInstance().getToken();

        Log.d("MyRefreshedToken", token);
    }
}