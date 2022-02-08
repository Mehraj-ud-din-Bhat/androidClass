package com.example.personapp.utility;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.personapp.models.Task;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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


    public void addTask(Task mytask) {

        ArrayList<Task> lstArrayList=(ArrayList<Task>) getTasks();
        Gson gson = new Gson();
        lstArrayList.add(mytask);
        String jsonString=gson.toJson(lstArrayList);



     editor.putString("tasks",jsonString);
     editor.apply();





    }
    public List<Task> getTasks()
    {
        Gson gson = new Gson();
        String jsonText = sharedPreferences.getString("tasks", null);

        ArrayList<Task> lstArrayList = gson.fromJson(jsonText,
                new TypeToken<List<Task>>(){}.getType());
    if(lstArrayList==null)
     {
    lstArrayList=new ArrayList<>();
     }
        //Log.d("LIST",""+taskList.size());
        return  lstArrayList;


    }

}





