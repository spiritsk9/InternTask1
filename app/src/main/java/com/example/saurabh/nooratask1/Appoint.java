package com.example.saurabh.nooratask1;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

public class Appoint extends AppCompatActivity {
private RecyclerView recyclerView;
Button atnd;
TextView count;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appoint);

        recyclerView = findViewById(R.id.recyclerview_tasks);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        atnd=findViewById(R.id.mark);
        count=findViewById(R.id.getcount);

      //  count.setText(Integer.parseInt(count.getText().toString())+1);
       // int i = recyclerView.getItemCount();



        atnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Appoint.this,Add_a_New_Class.class);
                startActivity(intent);
            }
        });


        getTasks();
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

            @Override
            protected void onPostExecute(List<ClassData> tasks) {
                super.onPostExecute(tasks);
                Collections.reverse(tasks);
                Adapter adapter = new Adapter(Appoint.this, tasks);

                recyclerView.setAdapter(adapter);
            }
        }

        GetTasks gt = new GetTasks();
        gt.execute();
    }
}