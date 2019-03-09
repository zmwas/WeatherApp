package com.newsapp.weatherapp.dependencyInjection;

import com.newsapp.weatherapp.ui.BookMarkFragment;
import com.newsapp.weatherapp.ui.MapFragment;
import com.newsapp.weatherapp.ui.WeatherFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class FragmentsModule {

    @ContributesAndroidInjector
    abstract WeatherFragment weatherFragment();

    @ContributesAndroidInjector
    abstract MapFragment mapFragment();

    @ContributesAndroidInjector
    abstract BookMarkFragment bookMarkFragment();
}
