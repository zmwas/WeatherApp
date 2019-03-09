package com.newsapp.weatherapp.viewModel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.newsapp.weatherapp.model.BookMark;
import com.newsapp.weatherapp.repository.BookMarkRepository;

import java.util.List;

import javax.inject.Inject;

public class BookMarkViewModel extends ViewModel {
    BookMarkRepository repository;

    @Inject
    public BookMarkViewModel(BookMarkRepository repository) {
        this.repository = repository;
    }

    public void saveBookMark(BookMark bookMark) {
        repository.saveBookMark(bookMark);
    }

    public LiveData<List<BookMark>> fetchBookMarks() {
        return repository.fetchBookMarks();
    }
}
