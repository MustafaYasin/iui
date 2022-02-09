package com.example.coachapp.connection;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RetrofitInstance {

    public static RetrofitInterface retrofitInterface;
    private static Retrofit retrofit = null;
    private final String device_url = "http://10.181.144.26";  // <------ put here your local IPV4 adress (windows terminal: ipconfig)
    private final String port = "3000";
    private final String BASE_URL = device_url + ":" + port;

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
