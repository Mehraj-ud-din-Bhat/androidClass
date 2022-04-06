package com.example.personapp.api;

import com.example.personapp.models.NewsResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface IRetrofitService {

    @GET(Urls.GET_NEWS_ENDPOINT)
    Call<NewsResponse> getALlNews();


}
