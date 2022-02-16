package com.example.personapp.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.personapp.R;
import com.example.personapp.adapter.TaskAdapter;
import com.example.personapp.models.Task;
import com.example.personapp.utility.Database;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    ImageView   addLaptop;
    Database database;
    ArrayList<Task> list=new ArrayList<>();
    RecyclerView rv_taskList;
    TaskAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addLaptop=findViewById(R.id.addTask_icon);
        rv_taskList=findViewById(R.id.rv_taskList);
        database=new Database(this);
        setListeners();
        initRecycler();

    }
    void setListeners()
    {
        addLaptop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,AddTaskActivity.class);
                startActivity(intent);

            }
        });


    }

    void initRecycler()
    {
        database.getTasks(this);
        adapter=new TaskAdapter(list,this);
        rv_taskList.setAdapter(adapter);
        rv_taskList.setLayoutManager(new LinearLayoutManager(this));

    }


    public  void onTasksReceived(ArrayList<Task> taskArrayList)
    {
        this.list=taskArrayList;
        Log.d("DB","ALL TASKS WERE Recevied: "+taskArrayList.size());
        adapter.refresh(this.list);


    }


    @Override
    protected void onResume() {
        super.onResume();

    }



    public  void taskClicked(Task task)
    {
        Intent intent=new Intent(this, AddTaskActivity.class);
        intent.putExtra("intention","update");
        intent.putExtra("key",task.key);
        intent.putExtra("name",task.taskName);
        intent.putExtra("desc",task.taskDescription);
        startActivity(intent);
    }












}