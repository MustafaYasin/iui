package com.example.coachapp.speech;

import android.app.Activity;
import android.os.Build;
import android.speech.tts.TextToSpeech;
import android.speech.tts.Voice;
import android.util.Log;

import java.util.Locale;
import java.util.Set;

public class SpeechFromText {
    private Activity activity;
    private TextToSpeech tts;

    public SpeechFromText(Activity activity) {
        this.activity = activity;
    }

    public void textToSpeech(String myTextToSpeech) {
        if (tts == null) {
            tts = new TextToSpeech(activity, status -> {
                if (status == TextToSpeech.SUCCESS) {
                    int result = tts.setLanguage(Locale.US);
                    if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Log.e("error", "This Language is not supported");
                    } else {
                        speak(myTextToSpeech);
                    }
                    /*
                    int result = tts.setLanguage(Locale.ENGLISH);
                    tts.setPitch(1.0f);
                    tts.setSpeechRate(1.0f);
                    //tts.setLanguage(new Locale("en_EN"));
*/

/*
                    Set<String> a=new HashSet<>();
                    a.add("male");
                    Voice voice = new Voice("en-us-x-sfg#male_1-local",new Locale("en","US"),400,200,true,a);
                    tts.setVoice(voice);
                    */


                    //choose male voice
                    Log.i("Voices", String.valueOf(tts.getVoices()));
//                    Set<Voice> list = tts.getVoices();
//                    Voice[] voices = tts.getVoices().toArray(new Voice[0]);
//                    tts.setVoice(voices[11]);

                    System.out.println("Speeeek: " + String.valueOf(tts.getVoices()));
                    speak(myTextToSpeech);

                    if (tts.isSpeaking()) {
                        tts.shutdown();
                        tts = null;
                    } else {
                        speak(myTextToSpeech);
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
