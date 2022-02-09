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
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.coachapp.R;
import com.example.coachapp.connection.Routes;
import com.example.coachapp.model.User;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class TextFromSpeech implements RecognitionListener {

    private static final String TAG = "SpeechRecognizerSetup";
    public final Integer RecordAudioRequestCode = 1;
    public SpeechRecognizer speechRecognizer;
    private final Activity activity;
    private final ImageButton voiceButton;
    private final Routes routes;
    private String spokenText = "";
    //private Boolean finishedResult = false;
    private TextView voiceTV;

    public TextFromSpeech(Activity activity) {
        this.activity = activity;
        checkPermission();
        routes = new Routes(activity);
        voiceButton = activity.findViewById(R.id.voiceButton);
        voiceTV = activity.findViewById(R.id.voiceTV);
    }

    private SpeechRecognizer getSpeechRecognizer() {
        if (speechRecognizer == null) {
            //speechRecognizer.
            Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "he");
            intent.putExtra("android.speech.extra.EXTRA_ADDITIONAL_LANGUAGES", new String[]{"he"});
            speechRecognizer = SpeechRecognizer.createSpeechRecognizer(activity);
            speechRecognizer.setRecognitionListener(this);
            //intent.putExtra(RecognizerIntent.EXTRA_PROMPT, getString(R.string.speech_prompt));
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
        voiceButton.setImageResource(R.mipmap.voice_recorder_circle_green);
        voiceTV.setHint("Listening...");
        //finishedResult = false;
    }

    @Override
    public void onBeginningOfSpeech() {
        Log.d(TAG, "onBeginningOfSpeech");
        voiceTV.setHint("Listening...");
    }

    @Override
    public void onRmsChanged(float v) {

    }

    @Override
    public void onBufferReceived(byte[] bytes) {

    }

    @Override
    public void onEndOfSpeech() {
        voiceButton.setImageResource(R.mipmap.microphone_circle_green);
        Log.d(TAG, "onEndOfSpeech");
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
            case SpeechRecognizer.ERROR_LANGUAGE_NOT_SUPPORTED:
                message = "Language not supported";
                break;
            case SpeechRecognizer.ERROR_LANGUAGE_UNAVAILABLE:
                message = "Language unavailable";
                break;
            case SpeechRecognizer.ERROR_TOO_MANY_REQUESTS:
                message = "Too many requests";
                break;
            case SpeechRecognizer.ERROR_SERVER_DISCONNECTED:
                message = "Server disconnected";
                break;
            default:
                message = "Not recognised";
                break;
        }
        Log.d(TAG, "onError code:" + error + " message: " + message);
        Toast.makeText(activity, message, Toast.LENGTH_LONG).show();

        if (restart) {
            activity.runOnUiThread(() -> {
                getSpeechRecognizer().cancel();
                startVoiceRecognitionCycle();
            });
        }
    }

    @Override
    public void onResults(Bundle bundle) {
        ArrayList<String> data = bundle.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
        spokenText = data.get(0);
        VoiceFlow voiceFlow = VoiceFlow.getInstance();
        voiceFlow.parseSpokenText(spokenText);


        Log.d(TAG, "onResults " + spokenText);

        User user = voiceFlow.getUser();
        if (user.isCompleted()) {
            //finishedResult = true;
            speechRecognizer.cancel();
            speechRecognizer.stopListening();
            speechRecognizer.destroy();
            routes.sendUser(user);
            routes.loadTrainingsplan(user);
        }
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

    //public String getSpokenText() {
    //    return spokenText;
    //}
//
    //public Boolean getFinishedResult() {
    //    return finishedResult;
    //}
}
