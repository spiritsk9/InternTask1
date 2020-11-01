package com.example.saurabh.nooratask1;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class UpdateClass extends AppCompatActivity implements View.OnClickListener{

    Toolbar toolbar;
    AlertDialog.Builder dialogBuilder;
    AlertDialog alertDialog;
    Button upload;
    private TextView date,time;
    private EditText nop,location,sessions,notes;
    private RecyclerView recyclerView;

    private int mYear, mMonth, mDay, mHour, mMinute;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_class);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        upload=findViewById(R.id.upload);
        location=findViewById(R.id.location);
        sessions=findViewById(R.id.session);
        nop=findViewById(R.id.nop);
        notes=findViewById(R.id.notes);
        date=findViewById(R.id.date);
        time=findViewById(R.id.time);

        date.setOnClickListener(this);
        time.setOnClickListener(this);

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAlertDialog(R.layout.dialoguebox);
            }
        });

        getSupportActionBar().setTitle("Update your class");


        final ClassData classData = (ClassData) getIntent().getSerializableExtra("task");

        loadTask(classData);

        findViewById(R.id.submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Clicked", Toast.LENGTH_LONG).show();
                updateTask(classData);
            }
        });

/*        findViewById(R.id.button_delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(UpdateTaskActivity.this);
                builder.setTitle("Are you sure?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        deleteTask(task);
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

                AlertDialog ad = builder.create();
                ad.show();
            }
        });*/
    }

    private void showAlertDialog(int layout){
        dialogBuilder = new AlertDialog.Builder(UpdateClass.this);
        View layoutView = getLayoutInflater().inflate(layout, null);
        Button dialogButton = layoutView.findViewById(R.id.upload);
        dialogBuilder.setView(layoutView);
        alertDialog = dialogBuilder.create();
        alertDialog.show();
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });
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

    private void loadTask(ClassData classData) {
        date.setText(classData.getDate());
        time.setText(classData.getTime());
        nop.setText(classData.getNop());
        location.setText(classData.getLocation());
        sessions.setText(classData.getSession());
        notes.setText(classData.getNotes());

    }

    private void updateTask(final ClassData classData) {
        final String udate = date.getText().toString().trim();
        final String utime = time.getText().toString().trim();
        final String unop = nop.getText().toString().trim();
        final String uloc = location.getText().toString().trim();
        final String usession = sessions.getText().toString().trim();
        final String unotes = notes.getText().toString().trim();

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
            nop.setError("Field is required");
            nop.requestFocus();
            return;
        }



        if (uloc.isEmpty()) {
            location.setError("Field is required");
            location.requestFocus();
            return;
        }

        if (usession.isEmpty()) {
            sessions.setError("Field is required");
            sessions.requestFocus();
            return;
        }

        if (unotes.isEmpty()) {
            notes.setError("Field is required");
            notes.requestFocus();
            return;
        }

        class UpdateTask extends AsyncTask<Void, Void, Void> {

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
                DatabaseClient.getInstance(getApplicationContext()).getAppDatabase()
                        .nDao()
                        .update(classData);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Toast.makeText(getApplicationContext(), "Updated", Toast.LENGTH_LONG).show();
                finish();
                startActivity(new Intent(UpdateClass.this, Appoint.class));
            }
        }

        UpdateTask ut = new UpdateTask();
        ut.execute();
    }


/*    private void deleteTask(final ClassData task) {
        class DeleteTask extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {
                DatabaseClient.getInstance(getApplicationContext()).getAppDatabase()
                        .nDao()
                        .delete(task);
                return null;
            }

     *//*       @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Toast.makeText(getApplicationContext(), "Deleted", Toast.LENGTH_LONG).show();
                finish();
                startActivity(new Intent(UpdateClass.this, Appoint.class));
            }*//*
        }

        DeleteTask dt = new DeleteTask();
        dt.execute();

    }*/

}
