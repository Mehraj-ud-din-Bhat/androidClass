package com.example.personapp.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.personapp.R;
import com.example.personapp.models.Task;
import com.example.personapp.utility.Database;
import com.example.personapp.utility.SharedPreferencesUtil;


public class AddTaskActivity extends AppCompatActivity {

    EditText etTaskTitle,etTaskDesc;
    Button btnSaveTask;
     ImageView imgCancel;
   SharedPreferencesUtil sharedPreferencesUtil;
   Database database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        etTaskTitle=findViewById(R.id.et_tasKName);
        etTaskDesc=findViewById(R.id.et_tasKDesc);
        btnSaveTask=findViewById(R.id.btn_saveTask);
        imgCancel=findViewById(R.id.img_cancel_task_icon);
        sharedPreferencesUtil=new SharedPreferencesUtil(this);
        database=new Database(this);
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
                   database.addTask(task);
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