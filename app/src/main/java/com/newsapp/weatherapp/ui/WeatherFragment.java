package com.newsapp.weatherapp.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.newsapp.weatherapp.R;
import com.newsapp.weatherapp.WeatherViewModelFactory;
import com.newsapp.weatherapp.databinding.FragmentWeatherLayoutBinding;
import com.newsapp.weatherapp.model.BookMark;
import com.newsapp.weatherapp.model.ForeCastObject;
import com.newsapp.weatherapp.viewModel.FetchWeatherViewModel;

import java.util.List;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;

public class WeatherFragment extends Fragment {
    FragmentWeatherLayoutBinding binding;
    WeatherAdapter weatherAdapter;
    FetchWeatherViewModel viewModel;
    RecyclerView recyclerView;
    List<ForeCastObject> weatherList;
    @Inject
    WeatherViewModelFactory factory;
    BookMark bookMark;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_weather_layout, container, false);
        recyclerView = binding.weatherList;
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        return binding.getRoot();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidSupportInjection.inject(this);
        if (getArguments() != null) {
            bookMark = (BookMark) getArguments().getSerializable("bookmark");
        }
        String latitude = null;
        if (bookMark != null) {
            latitude = String.valueOf(bookMark.getLatitude());
        }
        String longitude = null;
        if (bookMark != null) {
            longitude = String.valueOf(bookMark.getLongitude());
        }
        viewModel = ViewModelProviders.of(this, factory).get(FetchWeatherViewModel.class);
        if (bookMark != null) {
            viewModel.fetchFiveDay(latitude, longitude).observe(this, weathers -> {
                weatherAdapter.setWeatherList(weathers);

            });
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        weatherAdapter = new WeatherAdapter(getContext());
        recyclerView.setAdapter(weatherAdapter);

    }
}
