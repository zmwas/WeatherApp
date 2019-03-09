package com.newsapp.weatherapp.utils;

import java.io.IOException;

import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MockApiResponse<T> implements Call<T> {
    private Response<T> response;

    public MockApiResponse(Response<T> response) {
        this.response = response;
    }


    @Override
    public Response<T> execute() throws IOException {
        return response;
    }

    @Override
    public void enqueue(Callback<T> callback) {
        if(response.isSuccessful()) {
            callback.onResponse(this, response);
        } else {
            callback.onFailure(this, new Throwable());
        }
    }

    @Override
    public boolean isExecuted() {
        return false;
    }

    @Override
    public void cancel() {
    }

    @Override
    public boolean isCanceled() {
        return false;
    }

    @Override
    public Call<T> clone() {
        return null;
    }

    @Override
    public Request request() {
        return null;
    }
}
