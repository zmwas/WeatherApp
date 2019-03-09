package com.newsapp.weatherapp.viewModel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.newsapp.weatherapp.repository.WeatherRepository;
import com.newsapp.weatherapp.model.WeatherResponse;

import java.util.List;

import javax.inject.Inject;

public class FetchWeatherViewModel extends ViewModel {

    private WeatherRepository weatherRepository;

    @Inject
    public FetchWeatherViewModel(WeatherRepository weatherRepository) {
        this.weatherRepository = weatherRepository;
    }

    public LiveData<List<WeatherResponse>> fetchFiveDay(String latitude, String longitude) {
        return weatherRepository.fetchWeather(latitude, longitude);
    }
}
