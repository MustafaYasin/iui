package com.example.coachapp.speech;

import android.app.Activity;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.UtteranceProgressListener;
import android.util.Log;
import android.widget.Toast;

import com.example.coachapp.location.GoogleMapsApp;

import java.util.Locale;

public class SpeechFromText implements TextToSpeech.OnInitListener {

    private static final String TAG = "SpeechFromText";

    private final Activity activity;
    private TextToSpeech tts;
    private TextFromSpeech textFromSpeech;

    public SpeechFromText(Activity activity, TextFromSpeech textFromSpeech) {
        this.activity = activity;
        this.textFromSpeech = textFromSpeech;
        tts = new TextToSpeech(activity, this);
    }

    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {
            int result = tts.setLanguage(Locale.ENGLISH);
            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e(TAG, "This Language is not supported");
            } else {
                //Todo: choose male voice
//                        Log.i("Voices", String.valueOf(tts.getVoices()));
//                        Voice[] voices = tts.getVoices().toArray(new Voice[0]);
//                        tts.setVoice(voices[11]);
                /*if (tts.isSpeaking()) {
                    tts.shutdown();
                    tts = null;
                }*/
            }
        } else {
            Log.e(TAG, "Failed to Initialize");
        }
    }

    public void speakOutAndRecord(String myTextToSpeech, Boolean record) {
        tts.setOnUtteranceProgressListener(new UtteranceProgressListener() {
            @Override
            public void onStart(String s) {
                final String keyword = s;
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.d(TAG, "start running to speak");
                    }
                });
            }

            @Override
            public void onDone(String s) {
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (record) {
                            textFromSpeech.startVoiceRecognitionCycle();
                        }
                    }
                });
            }

            @Override
            public void onError(String s) {
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(activity, "Error ", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        Bundle params = new Bundle();
        params.putString(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, "");
        tts.speak(myTextToSpeech, TextToSpeech.QUEUE_FLUSH, params, "Dummy String");
    }
}
