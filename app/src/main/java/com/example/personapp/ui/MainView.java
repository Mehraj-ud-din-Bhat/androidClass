package com.example.personapp.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.personapp.R;
import com.example.personapp.controllers.MainViewController;

public class MainView extends AppCompatActivity {

      MainViewController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_view);
        controller=new MainViewController(this);

        controller.getNews();


    }
}