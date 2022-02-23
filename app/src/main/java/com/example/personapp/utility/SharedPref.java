package com.example.personapp.utility;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.personapp.models.User;

import java.util.Calendar;

public class SharedPref {




    public static String getCurrentuser(Context context)
    {

        SharedPreferences prefs =context.getSharedPreferences("users", MODE_PRIVATE);
         String name = prefs.getString("name", "");//"No name defined" is the default value.
         return  name;
    }


    public  static  void storeUser(String userbame,Context context)
    {
        SharedPreferences.Editor editor = context.getSharedPreferences("users", MODE_PRIVATE).edit();
        editor.putString("name", userbame);
        editor.apply();

    }




}
