package com.nooralhealth.activity.base;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.widget.TextView;

import com.nooralhealth.R;
import com.nooralhealth.activity.ClassData;
import com.nooralhealth.view.adapter.Adapter_ClassItem;

import java.util.ArrayList;
import java.util.List;

public class TotalAttendance extends AppCompatActivity {

    TextView headerMessage;
    private List<ClassData> classitem;
    private Adapter_ClassItem adapter;

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

        headerMessage.setText(longDescription, TextView.BufferType.SPANNABLE);
        getdata();

    }
    private void getdata() {

        classitem.add(new ClassData("PNC"));
        classitem.add(new ClassData("MNC"));

}
}




