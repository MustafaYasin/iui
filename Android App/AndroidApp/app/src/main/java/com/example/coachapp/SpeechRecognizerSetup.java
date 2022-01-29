package com.example.coachapp;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.support.v4.app.ActivityCompat;

import java.util.Locale;

public class SpeechRecognizerSetup {

    public static final Integer RecordAudioRequestCode = 1;
    public static SpeechRecognizer speechRecognizer;
    public static Intent speechRecognizerIntent;
    private static Context context;

    public SpeechRecognizerSetup(Context context) {
        this.context = context;
        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(context);
        speechRecognizerIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        speechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        speechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
    }

    public static void checkPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.RECORD_AUDIO}, RecordAudioRequestCode);
        }
    }
}
