package com.newsapp.weatherapp.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.newsapp.weatherapp.db.BookmarkDao;
import com.newsapp.weatherapp.model.BookMark;

import java.util.List;
import java.util.concurrent.Executor;

import javax.inject.Inject;

public class BookMarkRepository {
    BookmarkDao bookmarkDao;
    Executor executor;

    @Inject
    public BookMarkRepository(BookmarkDao bookmarkDao, Executor executor) {
        this.bookmarkDao = bookmarkDao;
        this.executor = executor;
    }

    public void saveBookMark(BookMark bookMark) {
        executor.execute(() -> {
            bookmarkDao.insert(bookMark);
        });
    }

    public LiveData<List<BookMark>> fetchBookMarks() {
          return bookmarkDao.fetchBookMarks();
    }

    public void deleteBookMark(BookMark bookMark) {
        executor.execute(() -> {
            bookmarkDao.delete(bookMark);
        });
    }
}
