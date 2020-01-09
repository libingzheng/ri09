package com.example.myapplication.utils;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitUtil {
    private static RetrofitUtil retrofitUtil;
    private Retrofit retrofit;
    private RetrofitUtil(){
        OkHttpClient client=new OkHttpClient.Builder()
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build();
        retrofit=new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build();
    }

    public static RetrofitUtil getInstance() {
        if (retrofitUtil==null){
            synchronized (RetrofitUtil.class){
                if (retrofitUtil==null){
                    retrofitUtil=new RetrofitUtil();
                }
            }
        }
        return retrofitUtil;
    }
    public <T> T createservice(Class<T> tClass){
        return retrofit.create(tClass);
    }
}
