package com.example.saurabh.nooratask1;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.TasksViewHolder> {
    private Context mCtx;
    private List<ClassData> classDataList;


    public Adapter(Context mCtx, List<ClassData> taskList) {
        this.mCtx = mCtx;
        this.classDataList = taskList;
    }


    @Override
    public TasksViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.adapter, parent, false);
        return new TasksViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TasksViewHolder holder, int position) {
        ClassData t = classDataList.get(position);
        holder.date.setText(t.getDate());
        holder.time.setText(t.getTime());
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

        TextView date, time,count,classs;

        public TasksViewHolder(View itemView) {
            super(itemView);

            date = itemView.findViewById(R.id.date);
            time = itemView.findViewById(R.id.time);
            count=itemView.findViewById(R.id.getcount);
            classs=itemView.findViewById(R.id.classes);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            ClassData task = classDataList.get(getAdapterPosition());

            Intent intent = new Intent(mCtx, UpdateClass.class);
            intent.putExtra("task", task);

            mCtx.startActivity(intent);
        }
    }
}