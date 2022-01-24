package com.example.personapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText numberEt;

    Button generateTable,reset;
    TextView tvTable;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        numberEt=findViewById(R.id.number_et);
        generateTable=findViewById(R.id.generate_table_btn);
        reset=findViewById(R.id.reset_btn);
        tvTable=findViewById(R.id.tv_table);
        generateTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               if(numberEt.getText().toString().isEmpty())
               {
                   numberEt.setError("Enter Number");
                   return;
               }

               generateTable(Integer.parseInt(numberEt.getText().toString()));
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               reset();
            }
        });




    }






    public  void generateTable(int no)
    {

        numberEt.setVisibility(View.GONE);
        generateTable.setVisibility(View.GONE);
        reset.setVisibility(View.VISIBLE);
        int temp;
        for(int i=1;i<50;i++)
        {
            temp=no*i;
            tvTable.setText(tvTable.getText()+"\n  "+no+" X " + i +" = "+temp);
        }
    }


    public  void reset()
    {
        numberEt.setVisibility(View.VISIBLE);
        generateTable.setVisibility(View.VISIBLE);
        numberEt.setText("");
        tvTable.setText("");
        reset.setVisibility(View.GONE);
    }


}