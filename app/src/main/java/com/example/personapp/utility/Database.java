package com.example.personapp.utility;

import android.content.Context;

import androidx.annotation.NonNull;

import com.example.personapp.models.Message;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Database   {
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private DatabaseReference userRef;
    private Context context;


    public  Database(Context mContext) {
      this.context=mContext;
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference().child("chats");
        userRef=firebaseDatabase.getReference().child("users");
    }



    public  void sendMessage(Message message)
    {

        databaseReference.push().setValue(message);

    }






    public  DatabaseReference getChatRefrence()
    {

        return  this.databaseReference;
    }



    public  void setMeOnline()
    {

        userRef.child(SharedPref.getCurrentuser(context)).child("isOnline").setValue(1);

    }


    public  void setMeOffline()
    {

        userRef.child(SharedPref.getCurrentuser(context)).child("isOnline").setValue(0);

    }




    public DatabaseReference  isOnline(String user)
    {
      return   userRef.child(user).child("isOnline");

    }


    public  DatabaseReference isTyping(String user)
    {
      return  userRef.child(user).child("isTyping");

    }

    public  void  updateTypingStatus(int status)
    {

        userRef.child(SharedPref.getCurrentuser(context)).child("isTyping").setValue(status);
    }





}
