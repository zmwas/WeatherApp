package com.newsapp.weatherapp.dependencyInjection;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;

import com.newsapp.weatherapp.db.AppDatabase;
import com.newsapp.weatherapp.db.BookmarkDao;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RoomModule {
    @Provides
    public AppDatabase database(Application application) {
        RoomDatabase.Builder<AppDatabase> databaseBuilder = Room.databaseBuilder(application, AppDatabase.class, "bookmarks.db");
        return databaseBuilder.build();
    }

    @Singleton
    @Provides
    public BookmarkDao bookmarkDao(AppDatabase db) {
        return db.bookmarkDao();
    }
}
