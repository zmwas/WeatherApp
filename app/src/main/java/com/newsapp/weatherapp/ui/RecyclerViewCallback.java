package com.newsapp.weatherapp.ui;

import android.view.View;

import com.newsapp.weatherapp.model.BookMark;

public interface RecyclerViewCallback {
    void onItemClick(int position, BookMark bookMark, View v);

}
