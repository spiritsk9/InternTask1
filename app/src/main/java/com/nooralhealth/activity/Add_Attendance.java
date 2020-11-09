package com.nooralhealth.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.nooralhealth.R;
import com.nooralhealth.model.room.DatabaseClient;
import com.nooralhealth.model.room.model.ClassData;

import java.util.Calendar;
import java.util.List;

public class Add_Attendance extends AppCompatActivity implements View.OnClickListener {

    Toolbar toolbar;
    TextView date,time,upload ;
    EditText inputnop,inputloc,inputsession ,inputnotes;
    Button btnMarkAttnd;
    private int mYear, mMonth, mDay, mHour, mMinute;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__attendance);

        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Add a New Class");
        toolbar.setTitleTextColor(getResources().getColor(android.R.color.white));

        date=findViewById(R.id.inputdate);
        time=findViewById(R.id.inputtime);
        btnMarkAttnd = findViewById(R.id.btnmark);
        upload = findViewById(R.id.upload);

        date.setOnClickListener(this);
        time.setOnClickListener(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        initView();

        findViewById(R.id.btnmark).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveTask();
            }
        });
        getTasks();



        upload.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(Add_Attendance.this);

                LayoutInflater inflater = getLayoutInflater();
                View dialogView = inflater.inflate(R.layout.dialogbox,null);

                builder.setView(dialogView);

                ImageView one = (ImageView) dialogView.findViewById(R.id.gallerypick);
                ImageView two = (ImageView) dialogView.findViewById(R.id.camerapick);

                final AlertDialog dialog = builder.create();

                two.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(takePicture, 0);//zero can be replaced with any action code (called requestCode)

                    }
                });

                one.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        Intent pickPhoto = new Intent(Intent.ACTION_PICK,
                                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        startActivityForResult(pickPhoto , 1);//one can be replaced with any action code

                    }
                });
                // Display the custom alert dialog on interface
                dialog.show();

            }
            protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
                Add_Attendance.super.onActivityResult(requestCode, resultCode, imageReturnedIntent);
                switch(requestCode) {
                    case 0:
                        ImageSwitcher imageview;
                        if(resultCode == RESULT_OK){
                            Uri selectedImage = imageReturnedIntent.getData();
                        }

                        break;
                    case 1:
                        if(resultCode == RESULT_OK){
                            Uri selectedImage = imageReturnedIntent.getData();
                        }
                        break;
                }
            }
        });

    }

    private void initView() {
        date=findViewById(R.id.inputdate);
        time=findViewById(R.id.inputtime);
        inputloc=findViewById(R.id.inputlocWard);
        inputnop=findViewById(R.id.inputnop);
        inputnop.addTextChangedListener(new CheckPercentage());
        inputsession=findViewById(R.id.inputsession);
        inputnotes=findViewById(R.id.inputnotes);
        btnMarkAttnd=findViewById(R.id.btnmark);

    }

    @Override
    public void onClick(View v) {

        if (v == date) {

            // Get Current Date
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);


            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {

                            date.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();
        }
        if (v == time) {

            // Get Current Time
            final Calendar c = Calendar.getInstance();
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);


            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {

                            time.setText(hourOfDay + ":" + minute);
                        }
                    }, mHour, mMinute, false);
            timePickerDialog.show();
        }
    }

    class CheckPercentage implements TextWatcher {
        public void afterTextChanged(Editable s) {
            try {
                Log.d("Percentage", "input: " + s);
                if(Integer.parseInt(s.toString()) > 100)
                    s.replace(0, s.length(), "100");
            }
            catch(NumberFormatException nfe){}
        }
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            // Not used, details on text just before it changed
            // used to track in detail changes made to text, e.g. implement an undo
        }
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            // Not used, details on text at the point change made
        }
    }

    private void getTasks() {
        class GetTasks extends AsyncTask<Void, Void, List<ClassData>> {

            @Override
            protected List<ClassData> doInBackground(Void... voids) {
                List<ClassData> taskList = DatabaseClient
                        .getInstance(getApplicationContext())
                        .getAppDatabase()
                        .nDao()
                        .getAll();
                return taskList;
            }


        }

        GetTasks gt = new GetTasks();
        gt.execute();
    }

    private void saveTask() {
        final String udate = date.getText().toString().trim();
        final String utime = time.getText().toString().trim();
        final String unop = inputnop.getText().toString().trim();
        final String uloc = inputloc.getText().toString().trim();
        final String usession = inputsession.getText().toString().trim();
        final String unotes = inputnotes.getText().toString().trim();


        if (udate.isEmpty()) {
            date.setError("Date required");
            date.requestFocus();
            return;
        }

        if (utime.isEmpty()) {
            time.setError("Time required");
            time.requestFocus();
            return;
        }

        if (unop.isEmpty()) {
            inputnop.setError("Field is required");
            inputnop.requestFocus();
            return;
        }



        if (uloc.isEmpty()) {
            inputloc.setError("Field is required");
            inputloc.requestFocus();
            return;
        }

        if (usession.isEmpty()) {
            inputsession.setError("Field is required");
            inputsession.requestFocus();
            return;
        }

        if (unotes.isEmpty()) {
            inputnotes.setError("Field is required");
            inputnotes.requestFocus();
            return;
        }

        class SaveTask extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {

                //creating a task
                ClassData classData = new ClassData();
                classData.setDate(udate);
                classData.setTime(utime);
                classData.setNop(unop);
                classData.setLocation(uloc);
                classData.setSession(usession);
                classData.setNotes(unotes);

                //adding to database
                DatabaseClient.getInstance(getApplicationContext()).getAppDatabase()
                        .nDao()
                        .insert(classData);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                finish();
                startActivity(new Intent(getApplicationContext(), AttendanceActivity.class));
                Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_LONG).show();
            }
        }

        SaveTask st = new SaveTask();
        st.execute();

    }
}