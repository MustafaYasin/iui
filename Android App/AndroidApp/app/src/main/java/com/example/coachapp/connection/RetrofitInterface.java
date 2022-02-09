package com.example.coachapp.connection;

import java.util.UUID;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface RetrofitInterface {

    @FormUrlEncoded
    @POST("/user")
    Call<Void> sendUser(
            @Field("id") UUID id,
            @Field("name") String name,
            @Field("age") int age,
            @Field("gender") String gender,
            @Field("workouts") int workouts,
            @Field("experience") String experience,
            @Field("trainingsGoal") String goal,
            @Field("trainingsLocation") String trainingsLocation
    );

    @FormUrlEncoded
    @POST("/recommend")
    Call<String> loadTrainingsPlan(
            @Field("id") UUID id,
            @Field("name") String name,
            @Field("age") int age,
            @Field("gender") String gender,
            @Field("workouts") int workouts,
            @Field("experience") String experience,
            @Field("trainingsGoal") String goal,
            @Field("trainingsLocation") String trainingsLocation
    );

    @FormUrlEncoded
    @POST("/search_ex")
    Call<String> getExerciseExplanation(@Field("exercise") String ex);//@Body JSONObject exercise);
}
