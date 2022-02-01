package com.example.personapp.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.personapp.R;
import com.example.personapp.models.Task;

public class AddTaskActivity extends AppCompatActivity {

    EditText etTaskTitle,etTaskDesc;
    Button btnSaveTask;
   ImageView imgCancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        etTaskTitle=findViewById(R.id.et_tasKName);
        etTaskDesc=findViewById(R.id.et_tasKDesc);
        btnSaveTask=findViewById(R.id.btn_saveTask);
        imgCancel=findViewById(R.id.img_cancel_task_icon);
        btnSaveTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(etTaskTitle.getText().toString().isEmpty())
                {
                    // IF TITLE IS MISSING RETURN
                    etTaskTitle.setError("Please enter task title");
                    return;

                }

                // PROCEED FURTHER IF USER
                 Task task=new Task(etTaskTitle.getText().toString(),etTaskDesc.getText().toString());
                Intent intent=new Intent(AddTaskActivity.this,MainActivity.class);
                intent.putExtra("title",task.taskName);
                intent.putExtra("desc",task.taskDescription);
                startActivity(intent);
                  finish();


            }
        });

        imgCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}