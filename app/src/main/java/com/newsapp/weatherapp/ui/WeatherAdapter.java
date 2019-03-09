package com.newsapp.weatherapp.ui;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.newsapp.weatherapp.databinding.WeatherItemBinding;
import com.newsapp.weatherapp.model.Weather;
import com.newsapp.weatherapp.model.WeatherResponse;

import java.util.ArrayList;
import java.util.List;

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder> {
    private List<WeatherResponse> weatherList;
    private Context context;

    public WeatherAdapter(Context context) {
        weatherList = new ArrayList<>();
        this.context = context;
    }

    @NonNull
    @Override
    public WeatherViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        WeatherItemBinding binding = WeatherItemBinding.inflate(inflater, viewGroup, false);
        return new WeatherViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull WeatherViewHolder weatherViewHolder, int i) {
        WeatherResponse weather = weatherList.get(i);
        weatherViewHolder.bind(weather);
    }

    @Override
    public int getItemCount() {
        return weatherList.size();
    }

    public void setWeatherList(List<WeatherResponse> weatherList) {
        this.weatherList = weatherList;
        notifyDataSetChanged();
    }

    class WeatherViewHolder extends RecyclerView.ViewHolder {
        WeatherItemBinding binding;

        WeatherViewHolder(@NonNull WeatherItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(WeatherResponse weather) {
            binding.weatherText.setText(weather.getDtTxt());
            binding.weatherDescription.setText(weather.getWeather().get(0).getDescription());
        }
    }

}
