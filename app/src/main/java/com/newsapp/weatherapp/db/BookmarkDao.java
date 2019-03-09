package com.newsapp.weatherapp.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import com.newsapp.weatherapp.model.BookMark;

import java.util.List;

@Dao
public interface BookmarkDao extends BaseDao<BookMark> {

    @Query("SELECT * FROM bookmark")
    LiveData<List<BookMark>> fetchBookMarks();

}
