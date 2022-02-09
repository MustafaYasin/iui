package com.example.coachapp.connection;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.coachapp.model.Exercise;
import com.example.coachapp.model.ExerciseExplanation;
import com.example.coachapp.model.TrainingsDay;
import com.example.coachapp.model.TrainingsPlan;
import com.example.coachapp.model.User;
import com.example.coachapp.speech.SpeechFromText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Routes {

    private static final String TAG = Routes.class.getSimpleName();

    public static TrainingsPlan plan;
    private Activity activity;
    public static Routes instance;
    public ExerciseExplanation exerciseExplanation;

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
                user.getId(),
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
            public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(activity, "Saved user data successfully", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<Void> call, @NonNull Throwable t) {
                Log.e(TAG, t.getMessage());
            }
        });
    }

    public void getExerciseExplanation(String exercise, SpeechFromText speechFromText) {
        exerciseExplanation = new ExerciseExplanation();
        if (exercise.contains("squads")) {
            exercise = "Squats";
        }
        Call<String> call = RetrofitInstance.retrofitInterface.getExerciseExplanation(exercise);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(@NonNull Call<String> call, @NonNull Response<String> response) {
                if (response.isSuccessful()) {
                    String exerciseRaw = response.body();
                    try {
                        Log.i(TAG, "exercise explanation: " + response.body());
                        JSONObject jsonPlan = new JSONObject(response.body());
                        exerciseExplanation.setTitle(jsonPlan.getString("exercise_title"));
                        exerciseExplanation.setExecution(jsonPlan.getString("exercise_execution"));
                        exerciseExplanation.setMuscleDescription(jsonPlan.getString("muscle_description"));
                        exerciseExplanation.setMuscleGroup(jsonPlan.getString("muscle_group"));
                        exerciseExplanation.setSubsetMuscles(jsonPlan.getString("subset_muscles"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Log.e(TAG, "Can't get JSONObject", e);
                    }
                    Log.i(TAG, exerciseRaw);
                }
            }

            @Override
            public void onFailure(@NonNull Call<String> call, @NonNull Throwable t) {
                Log.e(TAG, "Can't get exercise explanation", t);
            }
        });
    }

    public void loadTrainingsPlan(User user) {
        Call<String> call = RetrofitInstance.retrofitInterface.loadTrainingsPlan(
                user.getId(),
                user.getName(),
                user.getAge(),
                String.valueOf(user.getGender()),
                user.getWorkouts(),
                String.valueOf(user.getExperience()),
                String.valueOf(user.getTrainingsGoal()),
                String.valueOf(user.getTrainingsLocation())
        );
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(@NonNull Call<String> call, @NonNull Response<String> response) {
                if (response.isSuccessful()) {
                    String trainingsPlanRaw = response.body();
                    try {
                        Log.i(TAG, "trainings plan: " + response.body());
                        JSONObject jsonPlan = new JSONObject(response.body());
                        plan = new TrainingsPlan();
                        plan.setTitle(jsonPlan.getString("title"));
                        plan.setTrainings(jsonPlan.getInt("trainings"));
                        plan.setLevel(jsonPlan.getString("level"));
                        plan.setSplit(jsonPlan.getInt("split"));
                        plan.setSets(jsonPlan.getInt("sets"));

                        for (int i = 1; i < 8; i++) {
                            JSONObject jsonDay = jsonPlan.getJSONObject("day_" + i);
                            TrainingsDay day = new TrainingsDay();
                            day.setArea(jsonDay.getString("area"));
                            day.setExerciseCount(jsonDay.getInt("exercises"));
                            if (jsonDay.getInt("exercises") > 0) {
                                JSONArray arrayExercise = (JSONArray) jsonDay.get("exerciseDetails");
                                for (int j = 0; j < arrayExercise.length(); j++) {
                                    Exercise exercise = new Exercise();
                                    JSONObject obj = (JSONObject) arrayExercise.get(j);
                                    exercise.setTitle(obj.getString("exercise_title"));
                                    exercise.setExecution(obj.getString("exercise_execution"));
                                    exercise.setMuscleGroup(obj.getString("muscle_group"));
                                    exercise.setSubsetMuscles(obj.getString("subset_muscles"));
                                    exercise.setMuscleDescription(obj.getString("muscle_description"));
                                    day.addExercise(exercise);
                                }
                                day.setExerciseCount(day.getExercises().size());
                            }
                            plan.addTrainingDay(day);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Log.e(TAG, "Can't get exercise explanation", e);
                    }
                    Log.i(TAG, "trainings plan JSON: " + trainingsPlanRaw);
                } else {
                    Log.e(TAG, "Response was not successful");
                }
            }

            @Override
            public void onFailure(@NonNull Call<String> call, @NonNull Throwable t) {
                Log.e(TAG, t.getMessage());
            }
        });
    }

    public static TrainingsPlan getMyTrainingsPlan() {
        return plan;
    }
}
