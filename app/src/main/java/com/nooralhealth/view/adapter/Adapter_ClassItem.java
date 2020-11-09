package com.nooralhealth.view.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.nooralhealth.R;
import com.nooralhealth.model.room.model.ClassTypeItem;

import java.util.List;

public class Adapter_ClassItem extends RecyclerView.Adapter<Adapter_ClassItem.TasksViewHolder> {
    private Context mCtx;
    private List<ClassTypeItem> classDataList;


    public Adapter_ClassItem(Context mCtx, List<ClassTypeItem> taskList) {
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
        ClassTypeItem t = classDataList.get(position);
     //   holder.inputdate.setText(t.getDate());

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

        TextView inputdate, inputtime;


        public TasksViewHolder(View itemView) {
            super(itemView);

            inputdate = itemView.findViewById(R.id.date);
            inputtime = itemView.findViewById(R.id.time);


            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            ClassTypeItem task = classDataList.get(getAdapterPosition());
/*
            Intent intent = new Intent(mCtx, UpdateClass.class);
            intent.putExtra("task", task);

            mCtx.startActivity(intent);*/
        }
    }
}
