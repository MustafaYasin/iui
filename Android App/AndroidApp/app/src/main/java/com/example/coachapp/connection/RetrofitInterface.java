package com.example.coachapp.connection;

import com.example.coachapp.model.TrainingsSettings;
import com.example.coachapp.model.User;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RetrofitInterface {

    @POST("/user")
    Call<Void> sendUser(@Body HashMap<String, String> map);

    @POST("/trainingsSettings")
    Call<Void> sendTrainingsSettings(@Body HashMap<String, String> map);

    @POST("/trainingsPlanSettings")
    Call<Void> sendTrainingsPlanSettings(@Body HashMap<String, Integer> map);

    @FormUrlEncoded
    @POST("/spokenText")
    Call<String> sendSpokenText(@Field("mySpokenText") String mySpokenText, @Field("userId") String userId);

/*
    @POST("/spokenText")
    Call<String> sendSpokenText(@Body String post);

 */

    @POST("/myText")
    Call<String> sendMyText(@Body String post);

}
