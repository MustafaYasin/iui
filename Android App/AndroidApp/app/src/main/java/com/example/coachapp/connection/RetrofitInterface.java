package com.example.coachapp.connection;

import com.example.coachapp.gps.GPSLocation;
import com.example.coachapp.speech.SpeechToText;
import com.example.coachapp.view.User;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RetrofitInterface {

    @POST("/user")
    Call<User> sendUser(@Body HashMap<String, String> map);

    @POST("/spokenText")
    Call<String> sendSpokenText(@Body String body);

    @POST("/currentLocation")
    Call<GPSLocation> sendGPSLocation(@Body String map);
}
