package com.example.coachapp.connection;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RetrofitInstance {

    public static RetrofitInterface retrofitInterface;
    private static Retrofit retrofit = null;
    private final String device_url = "http://192.168.178.21"; //58900
    //    private final String device_url = "http://192.168.0.196";
    // Real device: connect both to same internet connection, search on windows for IP via terminal >ipconfig
    private final String emulator_url = "http://10.0.2.2";
    private final String port = "6000";
    String BASE_URL = "http:192.168.178.21//:6000/";

    public RetrofitInstance() {
        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(6000, TimeUnit.SECONDS)
                .connectTimeout(6000, TimeUnit.SECONDS)
                .build();

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .build();
        }

        retrofitInterface = retrofit.create(RetrofitInterface.class);
    }
}
