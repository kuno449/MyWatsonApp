package com.example.yoshiki.mywatsonapp.api;


import com.example.yoshiki.mywatsonapp.model.Result;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

public interface WatsonInterface {
    @GET("/classify")
    public void classifyImage(@Query("api_key") String key, @Query("url") String url,
                              @Query("version") String version, Callback<Result> response);
}
