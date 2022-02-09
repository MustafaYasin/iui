package com.example.coachapp.connection;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import com.example.coachapp.model.TrainingsPlanSettings;
import com.example.coachapp.model.TrainingsSettings;
import com.example.coachapp.model.User;
import com.example.coachapp.speech.SpeechFromText;
import com.example.coachapp.speech.TextFromSpeech;
import com.example.coachapp.speech.VoiceFlow;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Routes {

    private static final String TAG = "Routes";
    private Activity activity;
    private Routes routes;
    public static Routes instance;
    private TrainingsSettings trainingsSettings = new TrainingsSettings();
    private TrainingsPlanSettings trainingsPlanSettings = new TrainingsPlanSettings();

//    public Routes(Activity activity) {
//        this.activity = activity;
//    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public static Routes getInstance() {
        if (instance == null) {
            instance = new Routes();
        }
        return instance;
    }

    public void sendUser(User user) {
        Call<Void> call = RetrofitInstance.retrofitInterface.sendUser(
                user.getName(),
                user.getAge(),
                String.valueOf(user.getGender()),
                user.getWorkouts(),
                String.valueOf(user.getExperience()),
                String.valueOf(user.getTrainingsGoal()),
                String.valueOf(user.getTrainingsLocation())
        );

        System.out.println("User" + user.getName());
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

    public String getExerciseExplanation(String exercise) {
        if (exercise.contains("Squads")) {
            exercise = "Squats";
        }
        final String[] explanation = {""};
        JSONObject jsonEx = new JSONObject();
        try {
            jsonEx.put("exercise", exercise);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        System.out.println("exercise"+jsonEx);
        Call<JSONObject> call = RetrofitInstance.retrofitInterface.getExerciseExplanation(jsonEx);
        call.enqueue(new Callback<JSONObject>() {
            @Override
            public void onResponse(Call<JSONObject> call, Response<JSONObject> response) {
                if (response.isSuccessful()) {
                    System.out.println("Response exersice" + response.toString());
                    JSONObject json = new JSONObject();
                    explanation[0] = "hi";
                } else {
                    Log.e(TAG, "Response was not successful");
                }

            }

            @Override
            public void onFailure(Call<JSONObject> call, Throwable t) {
                Log.e(TAG, "Can't get exercise explanation");
            }
        });
        return explanation[0];
    }

//
//    public void sendSpokenText(String speechToText) {
//        Call<String> call = RetrofitInstance.retrofitInterface.sendSpokenText(speechToText, "1");
//        call.enqueue(new Callback<String>() {
//            @Override
//            public void onResponse(Call<String> call, Response<String> response) {
//                if (response.isSuccessful()) {
//                    try {
//                        JSONObject obj = new JSONObject(response.body());
//                        myTextToSpeech = obj.getString("key");
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                    Log.i("UNSERERROR",myTextToSpeech);
//                } else {
//                    Log.e(TAG, "Response was not successful");
//                }
//            }
//
//            @Override
//            public void onFailure(Call<String> call, Throwable t) {
//                Toast.makeText(activity, t.getMessage(), Toast.LENGTH_LONG).show();
//                Log.e(TAG, t.getMessage());
//            }
//        });
//    }

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
