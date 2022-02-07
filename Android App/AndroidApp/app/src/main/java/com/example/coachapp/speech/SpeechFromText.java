package com.example.coachapp.speech;

import android.app.Activity;
import android.os.Build;
import android.speech.tts.TextToSpeech;
import android.util.Log;

import java.util.Locale;

public class SpeechFromText {
    private final Activity activity;
    private TextToSpeech tts;

    public SpeechFromText(Activity activity) {
        this.activity = activity;
    }

    public void startTextToSpeech(String myTextToSpeech) {
        if (tts == null) {
            tts = new TextToSpeech(activity, status -> {
                if (status == TextToSpeech.SUCCESS) {
                    int result = tts.setLanguage(Locale.UK);
                    if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Log.e("error", "This Language is not supported");
                    } else {
                        //Todo: choose male voice
//                        Log.i("Voices", String.valueOf(tts.getVoices()));
//                        Voice[] voices = tts.getVoices().toArray(new Voice[0]);
//                        tts.setVoice(voices[11]);

                        if (tts.isSpeaking()) {
                            tts.shutdown();
                            tts = null;
                        } else {
                            speak(myTextToSpeech);
                        }
                    }
                } else {
                    Log.e("error", "Failed to Initialize");
                }
            });
        } else {
            tts.shutdown();
            tts = null;
        }
    }

    private void speak(String speechText) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            tts.speak(speechText, TextToSpeech.QUEUE_FLUSH, null, null);
        else
            tts.speak(speechText, TextToSpeech.QUEUE_FLUSH, null);
    }
}
