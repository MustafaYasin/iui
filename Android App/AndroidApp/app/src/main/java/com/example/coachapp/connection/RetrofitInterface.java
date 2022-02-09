package com.example.coachapp.connection;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RetrofitInterface {

    @FormUrlEncoded
    @POST("/user")
    Call<Void> sendUser(
            @Field("name") String name,
            @Field("age") int age,
            @Field("gender") String gender,
            @Field("workouts") int workouts,
            @Field("experience") String experience,
            @Field("trainingsGoal") String goal,
            @Field("trainingsLocation") String trainingsLocation
    );

    @GET("/user")
    Call<String> loadUser(@Field("objectId") String objectId);

    @FormUrlEncoded
    @POST("/spokenText")
    Call<String> sendSpokenText(@Field("mySpokenText") String mySpokenText, @Field("userId") String userId);

    @POST("/trainingsSettings")
    Call<Void> sendTrainingsSettings(@Body HashMap<String, String> map);

    @POST("/trainingsPlanSettings")
    Call<Void> sendTrainingsPlanSettings(@Body HashMap<String, Integer> map);
}
