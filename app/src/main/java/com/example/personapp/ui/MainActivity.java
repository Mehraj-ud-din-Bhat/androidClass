package com.example.personapp.ui;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
import com.example.personapp.adapter.ChatsAdapter;
import com.example.personapp.models.Message;
import com.example.personapp.models.User;
import com.example.personapp.utility.Database;
import com.example.personapp.utility.SharedPref;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

       Database database;
       EditText editTextMessage;
       ImageView sendicon;
       RecyclerView chatsRv;
       ChatsAdapter chatsAdapter;
       List<Message> messageList=new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        editTextMessage=findViewById(R.id.chatMessage);
        sendicon=findViewById(R.id.sendIcon);
        chatsRv=findViewById(R.id.rv_chats);

        chatsAdapter=new ChatsAdapter(messageList,this);
        chatsRv.setAdapter(chatsAdapter);
        chatsRv.setLayoutManager(new LinearLayoutManager(this));


        database=new Database(this);
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
                editTextMessage.getText().clear();






            }
        });

        setChatRefrence();



    }

    void  setChatRefrence()
    {
        database.getChatRefrence().addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                messageList.add(snapshot.getValue(Message.class));
               chatsAdapter.notifyDataSetChanged();
               chatsRv.scrollToPosition(messageList.size()-1);


            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }






}