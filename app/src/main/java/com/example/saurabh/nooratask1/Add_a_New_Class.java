package com.example.saurabh.nooratask1;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextWatcher;
import android.text.style.ImageSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.support.v7.widget.Toolbar;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.RadioButton;

import java.util.Calendar;
import java.util.List;

public class Add_a_New_Class extends AppCompatActivity implements View.OnClickListener {
    Toolbar toolbar;
    AlertDialog.Builder dialogBuilder;
    AlertDialog alertDialog;
    Button upload;
    TextView date,time;
    EditText nop,location,sessions,notes;
    private RecyclerView recyclerView;

    RadioButton ANC;
    RadioButton SNCU;
    RadioButton PNC;
    RadioButton INPATIENT;
    RadioButton CS;
    RadioButton CARDIOLOGY;
    RadioButton ONCOLOGY;
    RadioGroup rdGroup;
    SQLiteDatabase db;
    String classs="";
    Button submit;

   private int mYear, mMonth, mDay, mHour, mMinute;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_a__new__class);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        upload=findViewById(R.id.upload);
        location=findViewById(R.id.location);
        sessions=findViewById(R.id.session);
        nop=findViewById(R.id.nop);
        nop.addTextChangedListener(new CheckPercentage());
        notes=findViewById(R.id.notes);
        date=findViewById(R.id.date);
        time=findViewById(R.id.time);
        submit=findViewById(R.id.submit);


        rdGroup=(RadioGroup) findViewById(R.id.radioGroup);
        ANC=(RadioButton) findViewById(R.id.anc);
        SNCU=(RadioButton) findViewById(R.id.sncu);
        PNC=(RadioButton) findViewById(R.id.pnc);
     INPATIENT=(RadioButton) findViewById(R.id.inpatient);
        CS=(RadioButton) findViewById(R.id.cs);
        CARDIOLOGY=(RadioButton) findViewById(R.id.cardiology);
        ONCOLOGY=(RadioButton) findViewById(R.id.oncology);

        //database
        db=openOrCreateDatabase("MyData3", Context.MODE_PRIVATE,null);
        db.execSQL("create table if not exists MyTable3(ID integer primary key autoincrement not null,NAME varchar,PHONE varchar,GENDER varchar)");


        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              Intent intent = new Intent(Add_a_New_Class.this,Appoint.class);
              startActivity(intent);
            }
        });

        getSupportActionBar().setTitle("Add a New Class");

        date.setOnClickListener(this);
        time.setOnClickListener(this);

        upload.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(Add_a_New_Class.this);

                LayoutInflater inflater = getLayoutInflater();
                View dialogView = inflater.inflate(R.layout.dummydialog,null);

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
                Add_a_New_Class.super.onActivityResult(requestCode, resultCode, imageReturnedIntent);
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

        Button button = (Button) findViewById(R.id.upload);
        Spannable buttonLabel = new SpannableString(" UPLOAD IMAGE OF THE CLASS");
        buttonLabel.setSpan(new ImageSpan(getApplicationContext(), R.drawable.ic_baseline_add_photo_alternate_24,
                ImageSpan.ALIGN_BOTTOM), 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        button.setText(buttonLabel);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        findViewById(R.id.submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                saveTask();
            }
        });
        getTasks();

/*        submit.setOnClickListener(new View.OnClickListener() {
            @Override            public void onClick(View view) {
                ContentValues cv=new ContentValues();
                cv.put("CLASS",PNC.toString().trim());
                Long i=db.insert("MyTable3",null,cv);
                if(i>0){
                    Toast.makeText(Add_a_New_Class.this,"Data inserted",Toast.LENGTH_LONG).show();
                    rdGroup.clearCheck();

                }
            }
        });*/

        rdGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(i==R.id.anc){
                    classs="ANC";
                }
                else if(i==R.id.sncu){
                    classs="SNCU";
                }
                else if(i==R.id.sncu){
                    classs="PNC";
                }
                else if(i==R.id.pnc){
                    classs="Inpatient";
                }
                else if(i==R.id.inpatient){
                    classs="CS";
                }
                else if(i==R.id.cardiology){
                    classs="Cardiology";
                }
                else if(i==R.id.oncology){
                    classs="Cardiology";
                }
            }
        });
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


    private void showAlertDialog(int layout){
        dialogBuilder = new AlertDialog.Builder(Add_a_New_Class.this);
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

    private void saveTask() {
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
                startActivity(new Intent(getApplicationContext(), Appoint.class));
                Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_LONG).show();
            }
        }

        SaveTask st = new SaveTask();
        st.execute();

    }

}