package com.example.personapp.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.personapp.R;
import com.example.personapp.adapter.viewHolders.NewsAdapter;
import com.example.personapp.adapter.viewHolders.TopicsAdapter;
import com.example.personapp.controllers.MainViewController;
import com.example.personapp.databinding.ActivityMainViewBinding;
import com.example.personapp.models.Article;
import com.example.personapp.models.NewsResponse;
import com.example.personapp.models.Topic;
import com.example.personapp.utility.ViewUtils;

import java.util.ArrayList;

public class MainView extends AppCompatActivity {

      MainViewController controller;
      ActivityMainViewBinding binding;
      NewsAdapter adapter;
      ArrayList<Topic> topicArrayList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainViewBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        controller=new MainViewController(this);
        binding.pb.setVisibility(View.VISIBLE);
        topicArrayList.add(new Topic("Apple"));
        topicArrayList.add(new Topic("Tesla"));
        topicArrayList.add(new Topic("Coronavirus"));
        topicArrayList.add(new Topic("Science"));
        topicArrayList.add(new Topic("Google"));
        topicArrayList.add(new Topic("Amazon"));
        controller.getNews();
       // startActivity(new Intent(this,WebView.class));

        initViews();
        initTopics();
    }

    private void initTopics() {
        TopicsAdapter topicsAdapter=new TopicsAdapter(this,topicArrayList,this);
        binding.rvtopics.setAdapter(topicsAdapter);
        binding.rvtopics.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));

    }


    void  initViews()
   {
       binding.toolbarMain.toolBartitle.setText("Global News");
       binding.toolbarMain.back.setVisibility(View.GONE);


   }


    public  void  onNewsReceived(NewsResponse newsResponse)
    {

        adapter=new NewsAdapter(this,newsResponse.getArticles(),this);
        binding.rvNews.setAdapter(adapter);
        binding.rvNews.setLayoutManager(new LinearLayoutManager(this));
        binding.pb.setVisibility(View.GONE);





    }

    public void  onNewsItemClicked(Article article)
    {
        Intent intent=new Intent(this,WebView.class);
        intent.putExtra("title",article.getTitle());
        intent.putExtra("url",article.getUrl());
        startActivity(intent);

    }


    public  void  onError()
    {

    }

    public  void  onError(String msg)
    {
        ViewUtils.showToast(this,msg);
    }




    public  void onTopicClicked(String topicName)
    {

       controller.getTopicNews(topicName);
    }

}