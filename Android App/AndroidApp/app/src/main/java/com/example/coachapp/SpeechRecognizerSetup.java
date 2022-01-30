package com.example.coachapp;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.Locale;

public class SpeechRecognizerSetup {

    public final Integer RecordAudioRequestCode = 1;
    public SpeechRecognizer speechRecognizer;
    public Intent speechRecognizerIntent;
    private final Activity activity;

    public SpeechRecognizerSetup(Activity activity) {
        this.activity = activity;
        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(activity);
        speechRecognizerIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        speechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        speechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
    }

    public void checkPermission() {
        if (ContextCompat.checkSelfPermission(activity, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.RECORD_AUDIO}, RecordAudioRequestCode);
            }
        }
    }
}
