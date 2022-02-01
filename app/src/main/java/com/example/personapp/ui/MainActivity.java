package com.example.personapp.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.personapp.R;
import com.example.personapp.models.Task;
import com.example.personapp.adapter.TaskAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


  public  static ArrayList<Task> list=new ArrayList<>();
   RecyclerView recyclerView;
   ImageView   addTask;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=findViewById(R.id.rv_taskList);
        addTask=findViewById(R.id.addTask_icon);
        handleIntent();
        initRecycler();
        setListeners();


    }

  void  handleIntent()
    {
        if(getIntent()!=null) {
            String title =null;
            title = getIntent().getStringExtra("title");
            String desc = getIntent().getStringExtra("desc");
            if(title==null)
            {
                return;
            }
            Task task = new Task(title, desc);
            addTask(task);
        }

    }


    public void  initRecycler()
    {

       TaskAdapter taskAdapter=new TaskAdapter(list);
        recyclerView.setAdapter(taskAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }


    void setListeners()
    {
        addTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,AddTaskActivity.class);
                startActivity(intent);

            }
        });


    }


    public static void addTask(Task task){
        list.add(task);
    }







}