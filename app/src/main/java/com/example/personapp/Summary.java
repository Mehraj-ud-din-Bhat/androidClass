package com.example.personapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class Summary extends AppCompatActivity {


    TextView displayTv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);
        displayTv=findViewById(R.id.displayTv);
        String fName,lname,phone;

        fName=getIntent().getStringExtra("fname");

        lname=getIntent().getStringExtra("lname");

        phone=getIntent().getStringExtra("phone");

        displayTv.setText("\nYOUR NAME IS "+fName+"\nYOUR LAST NAME IS "+lname+"\nYOUR PHONE NUMBER IS "+phone);

    }
}