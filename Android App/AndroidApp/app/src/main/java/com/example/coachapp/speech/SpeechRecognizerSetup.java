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
import android.util.Log;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.coachapp.R;
import com.example.coachapp.connection.Routes;

import java.util.ArrayList;

public class SpeechRecognizerSetup implements RecognitionListener {

    private static final String TAG = "SpeechRecognizerSetup";
    public final Integer RecordAudioRequestCode = 1;
    public SpeechRecognizer speechRecognizer;
    private final Activity activity;
    private TextView speechToTextView;
    private ImageButton voiceButton;
    private Routes routes;

    public SpeechRecognizerSetup(Activity activity) {
        this.activity = activity;
        checkPermission();
        routes = new Routes(activity);
        speechToTextView = activity.findViewById(R.id.speechToText);
        voiceButton = activity.findViewById(R.id.voiceButton);
    }

    private SpeechRecognizer getSpeechRecognizer() {
        if (speechRecognizer == null) {
            speechRecognizer = SpeechRecognizer.createSpeechRecognizer(activity);
            speechRecognizer.setRecognitionListener(this);
        }
        return speechRecognizer;
    }

    public void startVoiceRecognitionCycle() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        getSpeechRecognizer().startListening(intent);
    }

    @Override
    public void onReadyForSpeech(Bundle bundle) {

    }

    @Override
    public void onBeginningOfSpeech() {
        Log.d(TAG, "onBeginningOfSpeech");
        speechToTextView.setHint("Listening...");
        voiceButton.setImageResource(R.mipmap.voice_recorder);
    }

    @Override
    public void onRmsChanged(float v) {

    }

    @Override
    public void onBufferReceived(byte[] bytes) {

    }

    @Override
    public void onEndOfSpeech() {
        voiceButton.setImageResource(R.mipmap.microphone);
    }

    @Override
    public void onError(int error) {
        String message;
        boolean restart = true;
        switch (error) {
            case SpeechRecognizer.ERROR_AUDIO:
                message = "Audio recording error";
                break;
            case SpeechRecognizer.ERROR_CLIENT:
                message = "Client side error";
                restart = false;
                break;
            case SpeechRecognizer.ERROR_INSUFFICIENT_PERMISSIONS:
                message = "Insufficient permissions";
                restart = false;
                break;
            case SpeechRecognizer.ERROR_NETWORK:
                message = "Network error";
                break;
            case SpeechRecognizer.ERROR_NETWORK_TIMEOUT:
                message = "Network timeout";
                break;
            case SpeechRecognizer.ERROR_NO_MATCH:
                message = "No match";
                break;
            case SpeechRecognizer.ERROR_RECOGNIZER_BUSY:
                message = "RecognitionService busy";
                break;
            case SpeechRecognizer.ERROR_SERVER:
                message = "error from server";
                break;
            case SpeechRecognizer.ERROR_SPEECH_TIMEOUT:
                message = "No speech input";
                break;
            default:
                message = "Not recognised";
                break;
        }
        Log.d(TAG, "onError code:" + error + " message: " + message);

        if (restart) {
            activity.runOnUiThread(new Runnable() {
                public void run() {
                    getSpeechRecognizer().cancel();
                    startVoiceRecognitionCycle();
                }
            });
        }
    }

    @Override
    public void onResults(Bundle bundle) {
        ArrayList<String> data = bundle.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
        routes.sendSpokenText(data.get(0));
        speechToTextView.setText(data.get(0));
    }

    @Override
    public void onPartialResults(Bundle bundle) {

    }

    @Override
    public void onEvent(int i, Bundle bundle) {

    }

    public void checkPermission() {
        if (ContextCompat.checkSelfPermission(activity, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.RECORD_AUDIO}, RecordAudioRequestCode);
            }
        }
    }
}
