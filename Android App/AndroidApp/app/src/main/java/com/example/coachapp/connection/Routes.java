package com.example.coachapp.connection;

import android.app.Activity;
import android.icu.text.StringPrepParseException;
import android.util.Log;
import android.widget.Toast;

import com.example.coachapp.model.Exercise;
import com.example.coachapp.model.ExerciseExplanation;
import com.example.coachapp.model.TrainingsDay;
import com.example.coachapp.model.TrainingsPlan;
import com.example.coachapp.model.TrainingsPlanSettings;
import com.example.coachapp.model.TrainingsSettings;
import com.example.coachapp.model.User;
import com.example.coachapp.speech.SpeechFromText;
import com.example.coachapp.speech.VoiceFlow;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Routes {

    private static final String TAG = "Routes";

    public static TrainingsPlan plan;

    private Activity activity;
    public static Routes instance;
    public ExerciseExplanation exerciseExplanation;
    public String explanation;
    private SpeechFromText speechFromText;

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

    public void getExerciseExplanation(String exercise, SpeechFromText speechFromText) {
        exerciseExplanation = new ExerciseExplanation();
        SpeechFromText speechFromText2 = speechFromText;
        if (exercise.contains("squads")) {
            exercise = "Squats";
        }
        System.out.println("exercise: " + exercise);

        Call<String> call = RetrofitInstance.retrofitInterface.getExerciseExplanation(exercise);

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                System.out.println("Response exersice2" + response);
                if (response.isSuccessful()) {
                   /* System.out.println("Response exersice" + response);
                    JSONObject json = new JSONObject();
                    explanation[0] = "hi";
                } else {
                    Log.e(TAG, "Response was not successful");
                }*/
                    String exerciseRaw = response.body();
                    try {
                        Log.i("Exercise", response.body());

                        JSONObject jsonPlan = new JSONObject(response.body());
                        //explanation[0] = jsonPlan.getString("exercise_execution");
                        //exerciseExplanation.setTitle(jsonPlan.getString("exercise_title"));
                        exerciseExplanation.setExecution(jsonPlan.getString("exercise_execution"));
                        //exerciseExplanation.setMuscleDescription(jsonPlan.getString("muscle_description"));
                        //exerciseExplanation.setMuscleGroup(jsonPlan.getString("muscle_group"));
                        //exerciseExplanation.setSubsetMuscles(jsonPlan.getString("subset_muscles"));
                        System.out.println("responde" + exerciseExplanation.getExecution());
                        speechFromText2.speakOutAndRecord(exerciseExplanation.getExecution(), false);

                        //trainingsPlanRaw = jsonPlan.getString("title");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    Log.i("UNSERERROR", exerciseRaw);
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.e(TAG, "Can't get exercise explanation");
            }
        });
    }

    public void loadTrainingsplan(User user) {
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
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()) {
                    String trainingsPlanRaw = response.body();
                    try {
                        Log.i("TRAINING", response.body());

                        JSONObject jsonPlan = new JSONObject(response.body());

                        //TrainingsPlan plan = new TrainingsPlan();
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

                        //trainingsPlanRaw = jsonPlan.getString("title");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    Log.i("UNSERERROR", trainingsPlanRaw);
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

    public static TrainingsPlan getMyTrainingsPlan() {
        return plan;
    }
}
