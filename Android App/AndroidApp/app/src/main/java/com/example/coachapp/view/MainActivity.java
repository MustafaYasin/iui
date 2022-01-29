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

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "RecognitionListener";
    private GPSLocation gpsLocation;
    private SpeechRecognizerSetup speechRecognizerSetup;

    private ImageButton voiceButton;
    private TextView speechToTextView;
    private TextView textToSpeechView;

    private String spokenText;

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
            speechToTextView.setVisibility(View.VISIBLE);
            textToSpeechView.setVisibility(View.INVISIBLE);
            voiceButton.setImageResource(R.mipmap.voice_recorder);
            speechRecognizerSetup.speechRecognizer.startListening(speechRecognizerSetup.speechRecognizerIntent);
        });
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
                spokenText = data.get(0);
                speechToTextView.setText(spokenText);
                sendSpokenText(spokenText);
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

    private void sendSpokenText(String spokenText) {
        speechToTextView.setText(spokenText);
        System.out.println("Response message: " + spokenText);
        Call<String> call = RetrofitInstance.retrofitInterface.sendSpokenText(spokenText);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()) {
                    speechToTextView.setVisibility(View.INVISIBLE);
                    String responseMsg = response.body();
                    textToSpeechView.setVisibility(View.VISIBLE);
                    textToSpeechView.setText(responseMsg);
                    voiceButton.setImageResource(R.mipmap.microphone);
                    System.out.println("Response message: " + responseMsg);
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
}