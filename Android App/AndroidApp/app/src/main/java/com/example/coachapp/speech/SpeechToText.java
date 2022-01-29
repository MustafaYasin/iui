package com.example.coachapp.speech;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import java.util.ArrayList;
import java.util.Locale;

public class SpeechToText {

    public String getSpokenText() {
        return spokenText;
    }

    private String spokenText;
    private Activity activity;
    private SpeechRecognizer speechRecognizer;
    private Intent speechRecognizerIntent;
    public static final Integer RecordAudioRequestCode = 1;

//    public SpeechToText(){
//        super();
//
//    }
//    public SpeechToText(Activity activity) {
////        super();
//        this.activity = activity;
//    }

//    public void setUpSpeechListener(){
//        if (ContextCompat.checkSelfPermission(activity, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
//            checkPermission();
//        }
//        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(activity);
//        final Intent speechRecognizerIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
//        speechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
//        speechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());

//        speechRecognizer.setRecognitionListener(new RecognitionListener() {
//            @Override
//            public void onReadyForSpeech(Bundle bundle) {
//
//            }
//
//            @Override
//            public void onBeginningOfSpeech() {
//                speechText.setText("");
//                speechText.setHint("Listening...");
//            }
//
//            @Override
//            public void onRmsChanged(float v) {
//
//            }
//
//            @Override
//            public void onBufferReceived(byte[] bytes) {
//
//            }
//
//            @Override
//            public void onEndOfSpeech() {
//
//            }
//
//            @Override
//            public void onError(int i) {
//
//            }
//
//            @Override
//            public void onResults(Bundle bundle) {
//                ArrayList<String> data = bundle.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
//                speechText.setText(data.get(0));
//
//            }
//
//            @Override
//            public void onPartialResults(Bundle bundle) {
//
//            }
//
//            @Override
//            public void onEvent(int i, Bundle bundle) {
//
//            }
//        });
//    }

    private void checkPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.RECORD_AUDIO}, RecordAudioRequestCode);
        }
    }
}
