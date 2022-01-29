package com.example.coachapp.connection;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class ServiceGenerator {

    private static final String device_url = "http://192.168.178.33";
    // Real device: connect both to same internet connection, search on windows for IP via terminal >ipconfig
    private static final String emulator_url = "http://10.0.2.2";
    private static final String port = "3000";
    private static final String BASE_URL = device_url + ":" + port;

    private static Retrofit.Builder builder =
            new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addConverterFactory(ScalarsConverterFactory.create());

    private static Retrofit retrofit = builder.build();

    private static OkHttpClient.Builder httpClient =
            new OkHttpClient.Builder();

    public static <S> S createService(
            Class<S> serviceClass) {
        return retrofit.create(serviceClass);
    }
}
