package com.example.personapp.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.personapp.R;
import com.example.personapp.adapter.UsersAdapter;
import com.example.personapp.models.User;
import com.example.personapp.utility.Database;
import com.example.personapp.utility.SharedPref;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class UsersActivity extends AppCompatActivity {

    RecyclerView rvUsers;
    Database database;
    ArrayList<User> userArrayList=new ArrayList<>();
    UsersAdapter usersAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);
        rvUsers=findViewById(R.id.rv_Users);
        database=new Database(this);
        usersAdapter=new UsersAdapter(userArrayList,this);
        rvUsers.setAdapter(usersAdapter);
        rvUsers.setLayoutManager(new LinearLayoutManager(this));
        getUsers();
    }


 void    getUsers()
    {
        database.getUsers().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                userArrayList.clear();

                for (DataSnapshot snapshot1: snapshot.getChildren())
                {


                    User user=snapshot1.getValue(User.class);
                    if(user.getPhoneNumber().equals(SharedPref.getCurrentuser(getApplicationContext())))
                    {
                       // continue;

                    }else {
                        userArrayList.add(user);
                    }

                }



                usersAdapter.refresh(userArrayList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


    public  void  onClickedOnUser(User user)
    {
        startActivity(new Intent(UsersActivity.this, ChatActivity.class).putExtra("phone",user.getPhoneNumber()));

    }
}