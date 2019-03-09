package com.newsapp.weatherapp;

import android.app.Activity;
import android.app.Application;
import android.support.v4.app.Fragment;

import com.newsapp.weatherapp.dependencyInjection.DaggerAppComponent;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import dagger.android.support.HasSupportFragmentInjector;

public class WeatherApp extends Application implements HasActivityInjector, HasSupportFragmentInjector {
    @Inject
    DispatchingAndroidInjector<Activity> mActivityInjector;

    @Inject
    DispatchingAndroidInjector<Fragment> mFragmentInjector;
    @Override
    public void onCreate() {
        super.onCreate();
        DaggerAppComponent.builder().application(this).build().inject(this);

    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return mActivityInjector;
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return mFragmentInjector;
    }
}

