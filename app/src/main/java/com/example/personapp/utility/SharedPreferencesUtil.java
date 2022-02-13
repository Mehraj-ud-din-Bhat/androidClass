package com.example.personapp.utility;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesUtil {
    Context context;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    String MY_PREF = "my_pref";



    public SharedPreferencesUtil(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(MY_PREF, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }




}





