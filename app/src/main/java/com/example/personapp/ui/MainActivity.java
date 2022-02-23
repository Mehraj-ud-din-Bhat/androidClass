package com.example.personapp.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.personapp.R;
import com.example.personapp.models.Message;
import com.example.personapp.models.User;
import com.example.personapp.utility.Database;
import com.example.personapp.utility.SharedPref;

import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    ArrayList<User> users =new ArrayList<>();
     Database database;

     EditText editTextMessage;
     ImageView sendicon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextMessage=findViewById(R.id.chatMessage);
        sendicon=findViewById(R.id.sendIcon);
        users.add(new User("MEHRAJ"));
        users.add(new User("Louis"));
        database=new Database(this);
      SharedPref.storeUser("MEHRAJ",this);
        sendicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(editTextMessage.getText().toString().isEmpty())
                {
                    Toast.makeText(getBaseContext(),"ENTER MESSAGE TO SEND",Toast.LENGTH_LONG).show();
                    return;
                }
                Message message=new Message(editTextMessage.getText().toString(),Calendar.getInstance().getTime(),SharedPref.getCurrentuser(getBaseContext()));
                database.sendMessage(message);
            }
        });



    }






}