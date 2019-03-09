package com.newsapp.weatherapp.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.newsapp.weatherapp.api.ApiService;
import com.newsapp.weatherapp.model.OpenWeatherMapResponse;
import com.newsapp.weatherapp.model.Weather;
import com.newsapp.weatherapp.model.WeatherResponse;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeatherRepository {
    ApiService apiService;
    MutableLiveData<List<WeatherResponse>> weatherForecastLiveData;

    @Inject
    public WeatherRepository(ApiService apiService
    ) {
        this.apiService = apiService;
    }


    public LiveData<List<WeatherResponse>> fetchWeather(String latitude, String longitude) {
        List<Weather> weatherList = new ArrayList<>();

        weatherForecastLiveData = new MutableLiveData<>();
        Call<OpenWeatherMapResponse> fetchWeather = apiService.fetchFiveDayForeCast(latitude, longitude);
        fetchWeather.enqueue(new Callback<OpenWeatherMapResponse>() {
            @Override
            public void onResponse(Call<OpenWeatherMapResponse> call, Response<OpenWeatherMapResponse> response) {
                List<WeatherResponse> responses = response.body().getList();

                weatherForecastLiveData.postValue(responses);
            }

            @Override
            public void onFailure(Call<OpenWeatherMapResponse> call, Throwable t) {
                Log.e("ERROR", t.getLocalizedMessage());
            }
        });
        return weatherForecastLiveData;
    }
}
