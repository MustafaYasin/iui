package com.example.coachapp.connection;

import com.example.coachapp.model.TrainingsSettings;
import com.example.coachapp.model.User;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RetrofitInterface {

    @POST("/user")
    Call<Void> sendUser(@Body HashMap<String, String> map);

    @POST("/trainingsSettings")
    Call<Void> sendTrainingsSettings(@Body HashMap<String, String> map);

    @POST("/trainingsPlanSettings")
    Call<Void> sendTrainingsPlanSettings(@Body HashMap<String, Integer> map);

    @POST("/spokenText")
    Call<String> sendSpokenText(@Body String body);
}
