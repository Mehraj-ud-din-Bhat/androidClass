package com.example.personapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText etFirstName,etLastName,etPhone;
    Button submit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etFirstName=findViewById(R.id.firstname);
        etLastName=findViewById(R.id.lastname);
        etPhone=findViewById(R.id.phone);
        submit=findViewById(R.id.submit);


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validate())
                {
                    Intent intent=new Intent(MainActivity.this,Summary.class);
                    intent.putExtra("fname",etFirstName.getText().toString());
                    intent.putExtra("lname",etLastName.getText().toString());
                    intent.putExtra("phone",etPhone.getText().toString());
                    startActivity(intent);
                }
            }
        });

      //  Toast.makeText(this,"ON CREATE WAS CALLED",Toast.LENGTH_LONG).show();
    }
    public  boolean validate()
    {
        if(etFirstName.getText().toString().isEmpty())
        {
            etFirstName.setError("First Name is required");
            return  false;
        }


        if(etLastName.getText().toString().isEmpty())
        {
            etLastName.setError("Last Name is required");
            return  false;
        }


        if(etPhone.getText().toString().isEmpty())
        {
            etPhone.setError("phone is required");
            return  false;
        }


        return  true;

    }

}