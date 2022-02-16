package com.example.personapp.utility;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.personapp.models.Task;
import com.example.personapp.ui.MainActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Database {
      Context context;
      FirebaseDatabase firebaseDatabase;
      DatabaseReference databaseReference;
    public Database(Context context) {
        this.context = context;
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference().child("tasks");
    }

   public void addTask(Task task)
    {
        databaseReference.push().setValue(task).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(context,"TASK ADDED SUCCESSFULLY",Toast.LENGTH_LONG).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(context,"THERE WAS AN ISSUE WHILE ADDING THE TASK PLEASE CHECK LATER",Toast.LENGTH_LONG).show();
            }
        });

    }


    public void updateTask(Task task,String key)
    {
        databaseReference.child(key).setValue(task).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(context,"TASK UPDATED SUCCESSFULLY",Toast.LENGTH_LONG).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(context,"THERE WAS AN ISSUE WHILE ADDING THE TASK PLEASE CHECK LATER",Toast.LENGTH_LONG).show();
            }
        });

    }

     // GETS TAKS FROM FIREBA
    public  void   getTasks(MainActivity mainActivity)
    {
        ArrayList<Task> list=new ArrayList<>();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                  list.clear();
                  Task task;
                  for(DataSnapshot  dataSnapshot:snapshot.getChildren())
                  {
                      task=dataSnapshot.getValue(Task.class);
                      task.key=dataSnapshot.getKey();
                      list.add(task);

                  }
                  Log.d("DB","ALL TASKS WERE LOADED");
                  mainActivity.onTasksReceived(list);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }




}
