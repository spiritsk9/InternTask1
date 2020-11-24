package com.nooralhealth.fcm.model;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;



public class MyFirebaseMessagingService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);


        if(remoteMessage.getData().size() > 0){

        }


        String title = remoteMessage.getNotification().getTitle();
        String body = remoteMessage.getNotification().getBody();


    }
}