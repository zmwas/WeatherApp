package com.newsapp.weatherapp.ui;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.newsapp.weatherapp.databinding.WeatherItemBinding;
import com.newsapp.weatherapp.model.WeatherResponse;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",Locale.ENGLISH);
            Date date = null;
            try {
                date = formatter.parse(weather.getDtTxt());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            String strDate = DateFormat.getLongDateFormat(context).format(date);
            String time = DateFormat.getTimeFormat(context).format(date);

            binding.dateText.setText(strDate);
            binding.timeText.setText(time);
            binding.weatherDescription.setText(weather.getWeather().get(0).getDescription());
            binding.temperature.setText(String.format("%s ℃", String.valueOf(weather.getMain().getTemp())));
        }
    }
}
