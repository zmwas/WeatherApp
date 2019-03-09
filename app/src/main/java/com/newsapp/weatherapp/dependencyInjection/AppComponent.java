package com.newsapp.weatherapp.dependencyInjection;

import android.app.Application;

import com.newsapp.weatherapp.WeatherApp;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {
        AndroidInjectionModule.class,
        AndroidSupportInjectionModule.class,
        ApiModule.class,
        RoomModule.class,
        ExecutorModule.class,
        ViewModelModule.class,
        ActivityModule.class,
        FragmentsModule.class
})
public interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder application(Application app);

        AppComponent build();

    }

    void inject(WeatherApp app);
}
