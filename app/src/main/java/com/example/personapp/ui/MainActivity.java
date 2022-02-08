package com.example.personapp.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.personapp.R;
import com.example.personapp.models.Task;
import com.example.personapp.adapter.TaskAdapter;
import com.example.personapp.utility.SharedPreferencesUtil;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


  public  static List<Task> list=new ArrayList<>();
   RecyclerView recyclerView;
   ImageView   addTask;

   SharedPreferencesUtil sharedPreferencesUtil;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=findViewById(R.id.rv_taskList);
        addTask=findViewById(R.id.addTask_icon);
        sharedPreferencesUtil=new SharedPreferencesUtil(this);

        initRecycler();
        setListeners();


    }



    @Override
    protected void onResume() {
        super.onResume();
        initRecycler();
    }

    public void  initRecycler()
    {
        // RETRIVE TASKS FROM SP
         list=sharedPreferencesUtil.getTasks();


         // INIT ADAPTER
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