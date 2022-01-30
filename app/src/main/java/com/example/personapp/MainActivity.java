package com.example.personapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


   ArrayList<Task> list=new ArrayList<>();

  RecyclerView recyclerView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=findViewById(R.id.rv_taskList);
       initRecycler();


    }


    public void  initRecycler()
    {
        list.add(new Task("MORNING WALK","jsjsjjsjs"));
        list.add(new Task("TAKE BREAKFAST","jsjsjjsjs"));
        list.add(new Task("Go to Office","jsjsjjsjs"));
        TaskAdapter taskAdapter=new TaskAdapter(list);
        recyclerView.setAdapter(taskAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }









}