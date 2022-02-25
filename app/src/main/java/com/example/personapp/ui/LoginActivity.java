package com.example.personapp.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.personapp.R;
import com.example.personapp.models.User;
import com.example.personapp.utility.SharedPref;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {

    ArrayList<User> userArrayList=new ArrayList<>();
    Button loginButton;
    EditText userNameEt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userNameEt=findViewById(R.id.username);
        loginButton=findViewById(R.id.loginButton);


        User mehraj=new User("mehrajb33");
        User louis=new User("louis99");

        userArrayList.add(mehraj);
        userArrayList.add(louis);
        userArrayList.add(new User("demo44"));


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(userNameEt.getText().toString().isEmpty())
                {
                    userNameEt.setError("Please enter username");

                }else {

                     User user=null;

                    for(int i=0;i<userArrayList.size();i++)
                    {
                        if(userArrayList.get(i).getUserName().equals(userNameEt.getText().toString()))
                        {
                            user=userArrayList.get(i);
                            break;

                        }
                    }

                    if(user==null)
                    {
                        Toast.makeText(getApplicationContext(),"USER DOES NOT EXIST",Toast.LENGTH_LONG).show();
                        return;
                    }
                    SharedPref.storeUser(user.getUserName(),getBaseContext());
                    startActivity(new Intent(LoginActivity.this,MainActivity.class));
                    finish();


                }





            }
        });



    }
}