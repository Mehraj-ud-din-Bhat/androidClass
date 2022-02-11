package com.example.personapp.utility;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.personapp.models.Task;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Database {
      Context context;
      FirebaseDatabase firebaseDatabase;
      DatabaseReference databaseReference;
    public Database(Context context) {
        this.context = context;
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference();
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


}
