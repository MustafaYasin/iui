package com.example.coachapp.view;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.coachapp.R;
import com.example.coachapp.connection.RetrofitInstance;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "RecognitionListener";

    public static final Integer RecordAudioRequestCode = 1;
    private SpeechRecognizer speechRecognizer;
    private Intent speechRecognizerIntent;
    private RetrofitInstance retrofitRoutes = new RetrofitInstance();

    private TextView speechText;
    private String spokenText;
    private String responseMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        speechText = findViewById(R.id.speechText);
        ImageButton voiceButton = findViewById(R.id.voiceButton);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            checkPermission();
        }
        setUpSpeechToText();

        voiceButton.setOnClickListener(view -> {
            speechText.setVisibility(View.VISIBLE);
            voiceButton.setImageResource(R.mipmap.voice_recorder);
            speechRecognizer.startListening(speechRecognizerIntent);
//            retrofitRoutes.sendSpokenText(spokenText, MainActivity.this);
//            speechText.setText(responseMessage);
            System.out.println("msg set text " + responseMessage);
        });

        findViewById(R.id.sendBtn).setOnClickListener(view -> {
            System.out.println("PRessed location");
//            Call<GPSLocation> call = retrofitInterface.sendGPSLocation(location);
//            call.enqueue(new Callback<GPSLocation>() {
//                @Override
//                public void onResponse(Call<GPSLocation> call, Response<GPSLocation> response) {
//                    if (response.code() == 200) {
//                        System.out.println("REsponded ");
//                        GPSLocation respondLocation = response.body();
//                        speechText.setText(respondLocation.getLocation());
//                        System.out.println("test " + respondSpeech.toString());
//                    }
//
//                }
//                // Todo: error handling for status code 400
//
//                @Override
//                public void onFailure(Call<GPSLocation> call, Throwable t) {
//                    Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
//
//                }
//            });
// 
        });
    }

    private void setUpSpeechToText() {
        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(this);
        speechRecognizerIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        speechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        speechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());

        speechRecognizer.setRecognitionListener(new RecognitionListener() {
            @Override
            public void onReadyForSpeech(Bundle bundle) {
                Log.d(TAG, "onReadyForSpeech");
            }

            @Override
            public void onBeginningOfSpeech() {
                Log.d(TAG, "onBeginningOfSpeech");
                speechText.setText("");
                speechText.setHint("Listening...");
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
                retrofitRoutes.sendSpokenText(spokenText, MainActivity.this);
                speechText.setText(spokenText);
            }

            @Override
            public void onPartialResults(Bundle bundle) {
            }

            @Override
            public void onEvent(int i, Bundle bundle) {
            }
        });
    }

    private void checkPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO}, RecordAudioRequestCode);
        }
    }
}