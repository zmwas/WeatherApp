package com.newsapp.weatherapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class OpenWeatherMapResponse {

    @SerializedName("cod")
    @Expose
    private String cod;
    @SerializedName("message")
    @Expose
    private double message;
    @SerializedName("cnt")
    @Expose
    private int cnt;
    @SerializedName("list")
    @Expose
    private List<WeatherResponse>list = null;
    @SerializedName("city")
    @Expose
    private City city;

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public OpenWeatherMapResponse withCod(String cod) {
        this.cod = cod;
        return this;
    }

    public double getMessage() {
        return message;
    }

    public void setMessage(double message) {
        this.message = message;
    }

    public OpenWeatherMapResponse withMessage(double message) {
        this.message = message;
        return this;
    }

    public int getCnt() {
        return cnt;
    }

    public void setCnt(int cnt) {
        this.cnt = cnt;
    }

    public OpenWeatherMapResponse withCnt(int cnt) {
        this.cnt = cnt;
        return this;
    }

    public List<WeatherResponse> getList() {
        return list;
    }

    public void setList(List<WeatherResponse> list) {
        this.list = list;
    }

    public OpenWeatherMapResponse withList(List<WeatherResponse> list) {
        this.list = list;
        return this;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public OpenWeatherMapResponse withCity(City city) {
        this.city = city;
        return this;
    }

}
