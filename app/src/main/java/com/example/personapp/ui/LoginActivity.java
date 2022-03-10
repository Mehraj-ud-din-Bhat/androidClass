package com.example.personapp.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.personapp.R;
import com.example.personapp.models.User;
import com.example.personapp.utility.Database;
import com.example.personapp.utility.SharedPref;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {

    ArrayList<User> userArrayList=new ArrayList<>();
    Button loginButton;
    EditText userNameEt;
    Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        userNameEt=findViewById(R.id.phoneNumber);
        loginButton=findViewById(R.id.submit);
        database=new Database(this);
        if(SharedPref.getCurrentuser(this).length()>0)
        {
            startActivity(new Intent(LoginActivity.this, UsersActivity.class));
            finish();
        }
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(userNameEt.getText().toString().isEmpty())
                {
                    userNameEt.setError("Please enter username");

                }else {

                     User user=new User(userNameEt.getText().toString());
                     user.setIsLoggedIn(1);
                     database.doLogin(user);
                     SharedPref.storeUser(user.getPhoneNumber(),getBaseContext());
                     startActivity(new Intent(LoginActivity.this, UsersActivity.class));
                     finish();


                }





            }
        });



    }
}