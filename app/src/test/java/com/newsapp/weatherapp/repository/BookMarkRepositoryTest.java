package com.newsapp.weatherapp.repository;

import android.arch.lifecycle.LiveData;

import com.newsapp.weatherapp.db.BookmarkDao;
import com.newsapp.weatherapp.model.BookMark;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.concurrent.Executor;

import static com.newsapp.weatherapp.utils.ExecutorUtil.implementAsDirectExecutor;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class BookMarkRepositoryTest {
    @Mock
    BookmarkDao bookmarkDao;
    @Mock
    private Executor executor;

    BookMarkRepository bookMarkRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        bookMarkRepository = new BookMarkRepository(bookmarkDao,executor);
        implementAsDirectExecutor(executor);
    }

    @Test
    public void testSavingBookMark() {
        BookMark bookMark = new BookMark();
        when(bookmarkDao.insert(any())).thenReturn(anyLong());
        bookMarkRepository.saveBookMark(bookMark);
        verify(bookmarkDao).insert(bookMark);
    }

}