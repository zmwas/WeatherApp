package com.newsapp.weatherapp.api;

import com.newsapp.weatherapp.model.OpenWeatherMapResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    @GET("/data/2.5/forecast")
    Call<OpenWeatherMapResponse> fetchFiveDayForeCast(@Query("lat")String lat, @Query("lon")String lon);
}
