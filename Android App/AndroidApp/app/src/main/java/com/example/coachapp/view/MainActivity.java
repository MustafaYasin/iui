package com.example.coachapp.view;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.SpeechRecognizer;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.coachapp.R;
import com.example.coachapp.SpeechRecognizerSetup;
import com.example.coachapp.connection.RetrofitInstance;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "RecognitionListener";

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
        new SpeechRecognizerSetup(this);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            SpeechRecognizerSetup.checkPermission();
        }
        recognizeSpeech();

        voiceButton.setOnClickListener(view -> {
            speechToTextView.setVisibility(View.VISIBLE);
            voiceButton.setImageResource(R.mipmap.voice_recorder);
            new RetrofitInstance();
            SpeechRecognizerSetup.speechRecognizer.startListening(SpeechRecognizerSetup.speechRecognizerIntent);
        });

        findViewById(R.id.sendBtn).setOnClickListener(view -> {
            System.out.println("PRessed location");
        });
    }

    private void recognizeSpeech() {
        SpeechRecognizerSetup.speechRecognizer.setRecognitionListener(new RecognitionListener() {
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

            @Override
            public void onPartialResults(Bundle bundle) {
            }

            @Override
            public void onEvent(int i, Bundle bundle) {
                Log.d(TAG, "onEndOfSpeech");
            }
        });
    }
}