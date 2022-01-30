package com.example.coachapp.connection;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RetrofitInstance {

    public static RetrofitInterface retrofitInterface;
    private final String device_url = "http://192.168.178.33";
    // Real device: connect both to same internet connection, search on windows for IP via terminal >ipconfig
    private final String emulator_url = "http://10.0.2.2";
    private final String port = "3000";
    String BASE_URL = device_url + ":" + port;

    public RetrofitInstance() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        retrofitInterface = retrofit.create(RetrofitInterface.class);
    }
}