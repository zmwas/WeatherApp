package com.newsapp.weatherapp.dependencyInjection;

import com.newsapp.weatherapp.BuildConfig;
import com.newsapp.weatherapp.api.ApiService;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ApiModule {

    @Provides
    @Singleton
    public OkHttpClient client() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        Interceptor interceptor = chain -> {
            Request request = chain.request();
            HttpUrl url = request.url();
            HttpUrl newUrl = url.newBuilder()
                    .addQueryParameter("APPID", BuildConfig.APPID)
                    .build();
            Request.Builder builder = request.newBuilder()
                    .url(newUrl);

            Request newRequest = builder.build();
            return chain.proceed(newRequest);

        };
        return new OkHttpClient.Builder().connectTimeout(6, TimeUnit.MINUTES)
                .readTimeout(6, TimeUnit.MINUTES)
                .writeTimeout(6, TimeUnit.MINUTES).addNetworkInterceptor(interceptor).addInterceptor(loggingInterceptor).retryOnConnectionFailure(true).build();
    }

    @Provides
    @Singleton
    public Retrofit retrofit(OkHttpClient client) {
        return new Retrofit.Builder().baseUrl(BuildConfig.BASE_URL).client(client).addConverterFactory(GsonConverterFactory.create()).build();
    }

    @Provides
    @Singleton
    public ApiService apiService(Retrofit retrofit) {
        return retrofit.create(ApiService.class);
    }
}
