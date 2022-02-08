package com.example.coachapp.connection;

import android.app.Activity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.coachapp.R;
import com.example.coachapp.model.TrainingsPlanSettings;
import com.example.coachapp.model.TrainingsSettings;
import com.example.coachapp.model.User;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Routes {

    private static final String TAG = "Routes";
    private final Activity activity;
    //private User user = new User();
    private TrainingsSettings trainingsSettings = new TrainingsSettings();
    private TrainingsPlanSettings trainingsPlanSettings = new TrainingsPlanSettings();
    private String myTextToSpeech;

    public Routes(Activity activity) {
        this.activity = activity;
    }

    public void sendSpokenText(String speechToText) {
        Call<String> call = RetrofitInstance.retrofitInterface.sendSpokenText(speechToText, "1");
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()) {
//                    myTextToSpeech = response.body();
                    try {
                        JSONObject obj = new JSONObject(response.body());
                        myTextToSpeech = obj.getString("key");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    Log.i("UNSERERROR",myTextToSpeech);
                } else {
                    Log.e(TAG, "Response was not successful");
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(activity, t.getMessage(), Toast.LENGTH_LONG).show();
                Log.e(TAG, t.getMessage());
            }
        });
    }

    //test
    private void sendMyText() {
        String post = "Das habe ich gesagt";
        Call<String> call = RetrofitInstance.retrofitInterface.sendMyText(post);

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String postResponse = response.body();
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }

    public void sendUser(User user) {
        // HashMap<String, String> map = new HashMap<>();
        // map.put("name", user.getName());
        // map.put("age", String.valueOf(user.getAge()));
        // map.put("gender", String.valueOf(user.getGender()));
        // map.put("workouts", String.valueOf(user.getWorkouts()));
        // //map.put("weight", String.valueOf(user.getWeight()));
        // //map.put("height", String.valueOf(user.getHeight()));
        // map.put("experience", String.valueOf(user.getExperience()));
        // map.put("trainingsGoal", String.valueOf(user.getTrainingsGoal()));
        // map.put("trainingsLocation", String.valueOf(user.getTrainingsLocation()));

        Call<Void> call = RetrofitInstance.retrofitInterface.sendUser(
                user.getName(),
                user.getAge(),
                String.valueOf(user.getGender()),
                user.getWorkouts(),
                String.valueOf(user.getExperience()),
                String.valueOf(user.getTrainingsGoal()),
                String.valueOf(user.getTrainingsLocation())
        );
        call.enqueue(new Callback<Void>() {

            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(activity, "Saved user data successfully", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(activity, t.getMessage(), Toast.LENGTH_LONG).show();
                Log.e(TAG, t.getMessage());
            }
        });
    }

    private void sendTrainingsPlanSettings() {
        HashMap<String, Integer> map = new HashMap<>();
        map.put("weekLength", trainingsPlanSettings.getWeekLength());
        map.put("daysFrequency", trainingsPlanSettings.getDaysFrequency());
        map.put("maxTrainingsTime", trainingsPlanSettings.getMaxTrainingsTime());
        map.put("cardio", trainingsPlanSettings.getCardio());
        map.put("weightTraining", trainingsPlanSettings.getWeightTraining());

        Call<Void> call = RetrofitInstance.retrofitInterface.sendTrainingsPlanSettings(map);
        call.enqueue(new Callback<Void>() {

            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(activity, "Saved trainings plan settings successfully", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(activity, t.getMessage(), Toast.LENGTH_LONG).show();
                Log.e(TAG, t.getMessage());
            }
        });
    }

    private void sendTrainingsSettings() {
        HashMap<String, String> map = new HashMap<>();
        map.put("preferredTrainingsLocation", String.valueOf(trainingsSettings.getPreferredTrainingsLocation()));
        map.put("trainingsPreferenceGym", String.valueOf(trainingsSettings.getTrainingsPreferenceGym()));
        map.put("trainingsEquipment", String.valueOf(trainingsSettings.getTrainingsEquipment()));
        map.put("weatherPreference", String.valueOf(trainingsSettings.getWeatherPreference()));
        map.put("cardioPreference", String.valueOf(trainingsSettings.getCardioPreference()));

        Call<Void> call = RetrofitInstance.retrofitInterface.sendTrainingsSettings(map);
        call.enqueue(new Callback<Void>() {

            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(activity, "Saved trainings settings successfully", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(activity, t.getMessage(), Toast.LENGTH_LONG).show();
                Log.e(TAG, t.getMessage());
            }
        });
    }
}
