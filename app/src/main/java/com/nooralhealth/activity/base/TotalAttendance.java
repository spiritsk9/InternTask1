package com.nooralhealth.activity.base;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.widget.TextView;

import com.nooralhealth.R;

public class TotalAttendance extends AppCompatActivity {

    TextView headerMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_total_attendance);

        headerMessage = findViewById(R.id.headerMessage);

        SpannableStringBuilder longDescription = new SpannableStringBuilder();
        longDescription.append("You can ");
        int start = longDescription.length();
        longDescription.append("View Or Edit ");
        longDescription.setSpan(new ForegroundColorSpan(Color.WHITE), start, longDescription.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        longDescription.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), start, longDescription.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        longDescription.append("your previous class entries by clicking on them.");

        headerMessage.setText(longDescription,  TextView.BufferType.SPANNABLE);
    }
}
