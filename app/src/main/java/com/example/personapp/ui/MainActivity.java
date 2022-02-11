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


    ImageView   addLaptop;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addLaptop=findViewById(R.id.addTask_icon);
        setListeners();

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


    @Override
    protected void onResume() {
        super.onResume();

    }












}