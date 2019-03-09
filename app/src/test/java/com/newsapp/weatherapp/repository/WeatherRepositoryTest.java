package com.newsapp.weatherapp.repository;

import android.arch.core.executor.testing.InstantTaskExecutorRule;

import com.newsapp.weatherapp.api.ApiService;
import com.newsapp.weatherapp.db.BookmarkDao;
import com.newsapp.weatherapp.model.BookMark;
import com.newsapp.weatherapp.model.OpenWeatherMapResponse;
import com.newsapp.weatherapp.utils.MockApiResponse;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.concurrent.Executor;

import retrofit2.Response;

import static com.newsapp.weatherapp.utils.ExecutorUtil.implementAsDirectExecutor;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class WeatherRepositoryTest {

    @Mock
    ApiService apiService;
    @Mock
    private Executor executor;

    @Rule
    public TestRule rule = new InstantTaskExecutorRule();
    WeatherRepository weatherRepository;
    OpenWeatherMapResponse response;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        weatherRepository = new WeatherRepository(apiService);
        response = new OpenWeatherMapResponse();
        implementAsDirectExecutor(executor);
    }

    @Test
    public void testFetchingOfWeather() {
        when(apiService.fetchFiveDayForeCast(anyString(),anyString())).thenReturn(new MockApiResponse<>(Response.success(response)));
        weatherRepository.fetchWeather("123","123");
        verify(apiService).fetchFiveDayForeCast("123","123");
    }

}