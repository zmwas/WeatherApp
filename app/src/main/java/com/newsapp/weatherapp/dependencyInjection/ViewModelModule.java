package com.newsapp.weatherapp.dependencyInjection;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.newsapp.weatherapp.viewModel.BookMarkViewModel;
import com.newsapp.weatherapp.viewModel.FetchWeatherViewModel;
import com.newsapp.weatherapp.WeatherViewModelFactory;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ViewModelModule {
    @Binds
    abstract ViewModelProvider.Factory factory(WeatherViewModelFactory factory);

    @Binds
    @IntoMap
    @ViewModelKey(FetchWeatherViewModel.class)
    abstract ViewModel bindFetchWeatherViewModel(FetchWeatherViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(BookMarkViewModel.class)
    abstract ViewModel bindBookMarkViewModel(BookMarkViewModel viewModel);

}
