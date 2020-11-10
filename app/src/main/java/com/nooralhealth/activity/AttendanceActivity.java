package com.nooralhealth.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.nooralhealth.R;
import com.nooralhealth.activity.base.BaseActivity;

public class AttendanceActivity extends BaseActivity {

    TextView headerMessage;
    Button markAttendance, openlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_total_attendance);

        initView();
        setDetails();
        markAttendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AttendanceActivity.this,Add_Attendance.class);
                startActivity(i);
            }
        });

        openlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AttendanceActivity.this,ListDemo.class);
                startActivity(i);
            }
        });
    }

    private void initView() {
        headerMessage = findViewById(R.id.headerMessage);
        markAttendance = findViewById(R.id.btnmark);
        openlist = findViewById(R.id.openlist);
    }

    private void setDetails() {
        makeMessageBold(headerMessage, R.string.class_list_title_message, R.string.class_list_title_message_bold);
    }
}




