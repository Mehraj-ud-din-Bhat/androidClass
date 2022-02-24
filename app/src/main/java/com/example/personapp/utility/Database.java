package com.example.personapp.utility;

import android.content.Context;

import com.example.personapp.models.Message;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Database   {
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private Context context;


    public  Database(Context mContext) {
      this.context=mContext;
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference().child("chats");
    }



    public  void sendMessage(Message message)
    {

        databaseReference.push().setValue(message);

    }






    public  DatabaseReference getChatRefrence()
    {

        return  this.databaseReference;
    }




}
