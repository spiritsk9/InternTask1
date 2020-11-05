package com.nooralhealth.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.nooralhealth.R;
import com.nooralhealth.activity.base.BaseActivity;

public class AttendanceActivity extends BaseActivity {

    TextView headerMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_total_attendance);

        initView();
        setDetails();
    }

    private void initView() {
        headerMessage = findViewById(R.id.headerMessage);
    }

    private void setDetails() {
        makeMessageBold(headerMessage, R.string.class_list_title_message, R.string.class_list_title_message_bold);
    }
}




