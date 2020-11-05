package com.nooralhealth.view.adapter;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.nooralhealth.R;
import com.nooralhealth.activity.ClassData;

import java.util.List;

public class Adapter_ClassItem extends RecyclerView.Adapter<Adapter_ClassItem.TasksViewHolder> {
    private Context mCtx;
    private List<ClassData> classDataList;


    public Adapter_ClassItem(Context mCtx, List<ClassData> taskList) {
        this.mCtx = mCtx;
        this.classDataList = taskList;
    }


    @Override
    public TasksViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.adapter_total_attendance, parent, false);
        return new TasksViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TasksViewHolder holder, int position) {
        ClassData t = classDataList.get(position);
        holder.classes.setText(t.getClasses());

        //  holder.classs.setText(g.get);

        //   holder.count.setText(Integer.parseInt(count.getText().toString())+1);


    }

    @Override
    public int getItemCount() {
        if (classDataList != null)
            return classDataList.size();
        return 0;
    }

    class TasksViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView classes;
        ;

        public TasksViewHolder(View itemView) {
            super(itemView);

            classes = itemView.findViewById(R.id.classs);


            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            ClassData task = classDataList.get(getAdapterPosition());
/*
            Intent intent = new Intent(mCtx, UpdateClass.class);
            intent.putExtra("task", task);

            mCtx.startActivity(intent);*/
        }
    }
}
