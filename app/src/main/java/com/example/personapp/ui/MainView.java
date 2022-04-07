package com.example.personapp.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;

import com.example.personapp.R;
import com.example.personapp.adapter.viewHolders.NewsAdapter;
import com.example.personapp.controllers.MainViewController;
import com.example.personapp.databinding.ActivityMainViewBinding;
import com.example.personapp.models.NewsResponse;
import com.example.personapp.utility.ViewUtils;

public class MainView extends AppCompatActivity {

      MainViewController controller;
      ActivityMainViewBinding binding;
      NewsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainViewBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        controller=new MainViewController(this);
        binding.pb.setVisibility(View.VISIBLE);
        controller.getNews();

      initViews();
    }


   void  initViews()
   {
       binding.toolbarMain.toolBartitle.setText("Global News");
       binding.toolbarMain.back.setVisibility(View.GONE);


   }


    public  void  onNewsReceived(NewsResponse newsResponse)
    {

        adapter=new NewsAdapter(this,newsResponse.getArticles());
        binding.rvNews.setAdapter(adapter);
        binding.rvNews.setLayoutManager(new LinearLayoutManager(this));
        binding.pb.setVisibility(View.GONE);





    }


    public  void  onError()
    {

    }

    public  void  onError(String msg)
    {
        ViewUtils.showToast(this,msg);
    }




}