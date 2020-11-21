package com.nooralhealth.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.nooralhealth.R;

public class Open_Diff_Activities extends AppCompatActivity {

    Button cam, vid, list, attnd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open__diff__activities);

        cam = findViewById(R.id.button);
        attnd = findViewById(R.id.button2);
        list = findViewById(R.id.button3);
        vid = findViewById(R.id.button4);

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
    }
}