package com.example.personapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.personapp.R;
import com.example.personapp.models.Task;
import com.example.personapp.ui.AddTaskActivity;
import com.example.personapp.ui.MainActivity;

import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskHolder> {

    List<Task> taskList;
    Context context;

    public TaskAdapter(List<Task> taskList,Context context) {
        this.taskList = taskList;
        this.context=context;
    }

    public  void  refresh(List<Task> list)
    {
        taskList=list;
        notifyDataSetChanged();
    }

    @Override
    public TaskHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View contactView = inflater.inflate(R.layout.list_item_task, parent, false);
        TaskHolder viewHolder = new TaskHolder(contactView);
        return viewHolder;
    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(TaskHolder holder, int position) {
        // Get the data model based on position
        if(taskList!=null) {
            Task task = taskList.get(position);
            if(task!=null) {
                holder.taskTitle.setText("[" + position + "] " + task.taskName);
                if (task.taskDescription != null)
                    if (task.taskDescription.isEmpty()) {
                        holder.taskDesc.setText("NO DESC");
                    } else
                        holder.taskDesc.setText(task.taskDescription);
            }
        }
        final int  pos=position;

        holder.root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)context).taskClicked(taskList.get(pos));
            }
        });

    }


    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return taskList.size();
    }


    public static class TaskHolder extends RecyclerView.ViewHolder {
        View root;
        TextView taskTitle;
        TextView taskDesc;

        public TaskHolder(View itemView) {
            super(itemView);
            taskTitle=itemView.findViewById(R.id.taskTitle);
            taskDesc=itemView.findViewById(R.id.taskDesc);
            root=itemView.findViewById(R.id.root);

        }
    }
}