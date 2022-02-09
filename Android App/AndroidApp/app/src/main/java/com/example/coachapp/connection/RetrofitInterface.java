package com.example.coachapp.connection;

import com.example.coachapp.model.TrainingsPlan;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.UUID;

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

    @GET("/user")
    Call<String> loadUser(@Field("uuId") String uuId);

    @FormUrlEncoded
    @POST("/search_ex")//("/search_ex?exercise='Squats'")
    Call<String> getExerciseExplanation(@Field("exercise") String ex);//@Body JSONObject exercise);

    @FormUrlEncoded
    @POST("/spokenText")
    Call<String> sendSpokenText(@Field("mySpokenText") String mySpokenText, @Field("userId") String userId);

    @POST("/trainingsSettings")
    Call<Void> sendTrainingsSettings(@Body HashMap<String, String> map);
}
