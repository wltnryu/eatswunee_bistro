package com.example.eatswunee_bistro.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static RetrofitClient instance = null;
    private static ServiceApi serviceApi;
    private static String baseUrl = "http://43.201.201.163:8080";

    private RetrofitClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://43.201.201.163:8080")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        serviceApi = retrofit.create(ServiceApi.class);
    }

    public static RetrofitClient getInstance() {
        if (instance == null){
            instance = new RetrofitClient();
        }
        return instance;
    }

    public static ServiceApi getRetrofitInterface() {
        return serviceApi;
    }
}
