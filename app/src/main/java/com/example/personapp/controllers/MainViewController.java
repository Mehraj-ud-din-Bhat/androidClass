package com.example.personapp.controllers;

import android.util.Log;

import com.example.personapp.api.IRetrofitService;
import com.example.personapp.api.RetrofitClient;
import com.example.personapp.models.NewsResponse;
import com.example.personapp.ui.MainView;

import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainViewController {
         MainView mainView;

    public MainViewController(MainView mainView) {
        this.mainView = mainView;



    }

 public    void getNews()
    {
        IRetrofitService service = RetrofitClient.getRetrofitInstance().create(IRetrofitService.class);
        Call<NewsResponse> call=service.getALlNews();

        // 200   OK
        // 404   NOT FOUND
        // 500   SERVER ERROR
        // 400   BAD REQUEST

        call.enqueue(new Callback<NewsResponse>() {
            @Override
            public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {

                if(response.code()==200)
                {
                    Log.d("STATS","RESPONSE: FIRST NEWS DES:  "+response.body().getArticles().get(0).getDescription());
                    mainView.onNewsReceived(response.body());
                }
                else {
                    Log.d("STATUS","RESPONSE: "+response.code());
                    mainView.onError(response.message());
                }

            }

            @Override
            public void onFailure(Call<NewsResponse> call, Throwable t) {

                mainView.onError(t.getMessage());

            }
        });

    }


    public  void getTopicNews(String topic)
    {
        IRetrofitService service = RetrofitClient.getRetrofitInstance().create(IRetrofitService.class);
        String url= "https://newsapi.org/v2/everything?q="+topic.toLowerCase(Locale.ROOT)+"&sortBy=popularity&apiKey=28a87b70255143128bea8d58d836fd07";
        Call<NewsResponse> call=service.getTopicNews(url);
        call.enqueue(new Callback<NewsResponse>() {
            @Override
            public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {

                if(response.code()==200)
                {
                    Log.d("STATS","RESPONSE: FIRST NEWS DES:  "+response.body().getArticles().get(0).getDescription());
                    mainView.onNewsReceived(response.body());
                }
                else {
                    Log.d("STATUS","RESPONSE: "+response.code());
                    mainView.onError(response.message());
                }

            }

            @Override
            public void onFailure(Call<NewsResponse> call, Throwable t) {

                mainView.onError(t.getMessage());

            }
        });



    }
}
