package com.nooralhealth.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.nooralhealth.R;
import com.nooralhealth.activity.profile.GmailProfile;

public class Open_Diff_Activities extends AppCompatActivity {

    Button cam, vid, list, attnd,fcm,profile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open__diff__activities);

        cam = findViewById(R.id.button);
        attnd = findViewById(R.id.button2);
        list = findViewById(R.id.button3);
        vid = findViewById(R.id.button4);
        fcm = findViewById(R.id.FCM);
        profile = findViewById(R.id.profile);

      cam.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent i = new Intent(Open_Diff_Activities.this, OpenCamera.class);
        startActivity(i);
          }
      });

        attnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Open_Diff_Activities.this, AttendanceActivity.class);
                startActivity(i);
            }
        });

        list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Open_Diff_Activities.this, ListDemo.class);
                startActivity(i);
            }
        });

        vid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Open_Diff_Activities.this, VideoRecord.class);
                startActivity(i);
            }
        });

        fcm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Open_Diff_Activities.this, FCMdemo.class);
                startActivity(i);
            }
        });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Open_Diff_Activities.this, GmailProfile.class);
                startActivity(i);
            }
        });
    }
}