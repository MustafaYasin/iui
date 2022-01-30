package com.example.coachapp.view;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.SpeechRecognizer;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.coachapp.R;
import com.example.coachapp.SpeechRecognizerSetup;
import com.example.coachapp.connection.RetrofitInstance;
import com.example.coachapp.gps.GPSLocation;
import com.example.coachapp.model.TrainingsPlanSettings;
import com.example.coachapp.model.TrainingsSettings;
import com.example.coachapp.model.User;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    //change IP adress in RetrofitInstance
    // start server with node app.js via terminal
    // Todo: send settings
    // Todo: check if first time app started

    private static final String TAG = "RecognitionListener";
    private GPSLocation gpsLocation;
    private SpeechRecognizerSetup speechRecognizerSetup;
    private User user = new User();
    private TrainingsSettings trainingsSettings = new TrainingsSettings();
    private TrainingsPlanSettings trainingsPlanSettings = new TrainingsPlanSettings();

    private ImageButton voiceButton;
    private TextView speechToTextView;
    private TextView textToSpeechView;

    private String speechToText;
    private String textToSpeech;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        speechToTextView = findViewById(R.id.speechToText);
        textToSpeechView = findViewById(R.id.textToSpeech);
        voiceButton = findViewById(R.id.voiceButton);
        speechRecognizerSetup = new SpeechRecognizerSetup(this);
        gpsLocation = new GPSLocation(this);
        gpsLocation.getLastLocation();
        recognizeSpeech();
        new RetrofitInstance();

        voiceButton.setOnClickListener(view -> {
            speechToText();
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
//        speechToText();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == gpsLocation.getPERMISSION_ID()) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                gpsLocation.getLastLocation();
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (gpsLocation.checkPermissions()) {
            gpsLocation.getLastLocation();
        }
    }

    private void recognizeSpeech() {
        speechRecognizerSetup.checkPermission();
        speechRecognizerSetup.speechRecognizer.setRecognitionListener(new RecognitionListener() {
            @Override
            public void onReadyForSpeech(Bundle bundle) {
                Log.d(TAG, "onReadyForSpeech");
            }

            @Override
            public void onBeginningOfSpeech() {
                Log.d(TAG, "onBeginningOfSpeech");
                speechToTextView.setText("");
                speechToTextView.setHint("Listening...");
            }

            @Override
            public void onRmsChanged(float v) {
            }

            @Override
            public void onBufferReceived(byte[] bytes) {
            }

            @Override
            public void onEndOfSpeech() {
            }

            @Override
            public void onError(int i) {
                Log.d(TAG, "SpeechRecognizer does not work " + i);
            }

            @Override
            public void onResults(Bundle bundle) {
                ArrayList<String> data = bundle.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
                speechToText = data.get(0);
                setSpeechToTextText();
                sendSpokenText();
            }

            @Override
            public void onPartialResults(Bundle bundle) {
            }

            @Override
            public void onEvent(int i, Bundle bundle) {
                Log.d(TAG, "onEndOfSpeech");
            }
        });
    }

    private void sendSpokenText() {
        Call<String> call = RetrofitInstance.retrofitInterface.sendSpokenText(speechToText);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()) {
                    textToSpeech = response.body();
                    setTexToSpeechText();
                    // Todo: start text to speech
                    //  at variable textToSpeech is response of server saved
                    // Todo: start listening again after text to speech is finished
//                    speechToText();
                } else {
                    Log.e(TAG, "Response was not successful");
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                Log.e(TAG, t.getMessage());
            }
        });
    }

    private void sendUser() {
        HashMap<String, String> map = new HashMap<>();
        map.put("name", user.getName());
        map.put("age", String.valueOf(user.getAge()));
        map.put("weight", String.valueOf(user.getWeight()));
        map.put("height", String.valueOf(user.getHeight()));
        map.put("experience", String.valueOf(user.getExperience()));
        map.put("trainingsGoal", user.getTrainingsGoal());

        Call<Void> call = RetrofitInstance.retrofitInterface.sendUser(map);
        call.enqueue(new Callback<Void>() {

            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(MainActivity.this, "Saved user data successfully", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
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
                    Toast.makeText(MainActivity.this, "Saved trainings plan settings successfully", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
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
                    Toast.makeText(MainActivity.this, "Saved trainings settings successfully", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                Log.e(TAG, t.getMessage());
            }
        });
    }

    private void speechToText() {
        speechToTextView.setText("");
        speechToTextView.setVisibility(View.VISIBLE);
        textToSpeechView.setVisibility(View.INVISIBLE);
        voiceButton.setImageResource(R.mipmap.voice_recorder);
        speechRecognizerSetup.speechRecognizer.startListening(speechRecognizerSetup.speechRecognizerIntent);
    }

    private void setTexToSpeechText() {
        speechToTextView.setVisibility(View.INVISIBLE);
        textToSpeechView.setText(textToSpeech);
        voiceButton.setImageResource(R.mipmap.microphone);
        textToSpeechView.setVisibility(View.VISIBLE);
        System.out.println("Response textToSpeech: " + textToSpeech);
    }

    private void setSpeechToTextText() {
        textToSpeechView.setVisibility(View.INVISIBLE);
        speechToTextView.setText(speechToText);
        speechToTextView.setVisibility(View.VISIBLE);
        System.out.println("Input speechToText: " + speechToText);
    }
}