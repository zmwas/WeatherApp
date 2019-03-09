package com.newsapp.weatherapp.dependencyInjection;

import com.newsapp.weatherapp.ui.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityModule {

    @ContributesAndroidInjector(modules = FragmentsModule.class)
    abstract MainActivity mainActivity();
}
