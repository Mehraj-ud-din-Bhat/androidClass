package com.example.personapp.ui;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
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
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ChatActivity extends AppCompatActivity {

       Database database;
       EditText editTextMessage;
       ImageView sendicon;
       RecyclerView chatsRv;
       ChatsAdapter chatsAdapter;
       TextView isOnline,isTyping;
       List<Message> messageList=new ArrayList<>();
       String chatChannel;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextMessage=findViewById(R.id.chatMessage);
        sendicon=findViewById(R.id.sendIcon);
        chatsRv=findViewById(R.id.rv_chats);
        isTyping=findViewById(R.id.userTypingSattus);
        isOnline=findViewById(R.id.userOnlineStatus);
        isTyping.setVisibility(View.GONE);
        isOnline.setVisibility(View.GONE);
        chatsAdapter=new ChatsAdapter(messageList,this);
        chatsRv.setAdapter(chatsAdapter);
        chatsRv.setLayoutManager(new LinearLayoutManager(this));
        String chatReceiverPhone=getIntent().getStringExtra("phone");
        chatChannel=SharedPref.getCurrentuser(this)+"-"+chatReceiverPhone;
        database=new Database(this);
        database.setMeOnline();
        sendicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(editTextMessage.getText().toString().isEmpty())
                {
                    Toast.makeText(getBaseContext(),"ENTER MESSAGE TO SEND",Toast.LENGTH_LONG).show();
                    return;
                }

                Message message=new Message(editTextMessage.getText().toString(),Calendar.getInstance().getTime(),SharedPref.getCurrentuser(getBaseContext()));


                database.sendMessage(chatChannel,message);
                editTextMessage.getText().clear();

            }
        });

        editTextMessage.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                  database.updateTypingStatus(1);
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                database.updateTypingStatus(1);
            }

            @Override
            public void afterTextChanged(Editable editable) {
                final Handler handler = new Handler(Looper.getMainLooper());
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        database.updateTypingStatus(0);
                    }
                }, 100);

            }
        });

        setChatRefrence();
        bindOnlineStatus();
        bindTypingStatus();




    }


    void  bindOnlineStatus()
    {
        database.isOnline("louis99").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot!=null && snapshot.getValue(Integer.class)!=null) {
                    Integer value = snapshot.getValue(Integer.class);
                    if (value == 1) {
                        isOnline.setVisibility(View.VISIBLE);
                        isOnline.setText("Online");
                    } else {
                        isOnline.setVisibility(View.GONE);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    void  bindTypingStatus()
    {
        database.isTyping("louis99").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Log.d("TY","JJS");
                if(snapshot!=null && snapshot.getValue(Integer.class)!=null) {
                    Integer value = snapshot.getValue(Integer.class);
                    if (value == 1) {
                        isTyping.setVisibility(View.VISIBLE);
                        isTyping.setText("Typing...");
                    } else {
                        isTyping.setVisibility(View.GONE);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    void  setChatRefrence()
    {
        database.getChatRefrence().child(chatChannel).addChildEventListener(new ChildEventListener() {
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        database.setMeOffline();
    }
}