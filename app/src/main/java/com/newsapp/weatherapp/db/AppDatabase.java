package com.newsapp.weatherapp.db;


import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.newsapp.weatherapp.model.BookMark;

@Database(entities = {BookMark.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract BookmarkDao bookmarkDao();
}
