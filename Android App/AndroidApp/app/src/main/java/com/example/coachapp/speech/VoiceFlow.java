package com.example.coachapp.speech;

import android.app.Activity;

import com.example.coachapp.connection.RetrofitInstance;
import com.example.coachapp.model.User;


public class VoiceFlow {

    private final Activity activity;
    private TextFromSpeech textFromSpeech;
    private SpeechFromText speechFromText;
    private VoiceTexts voiceTexts;
    private User user = new User();

    public VoiceFlow(Activity activity) {
        this.activity = activity;
        textFromSpeech = new TextFromSpeech(activity);
        speechFromText = new SpeechFromText(activity, textFromSpeech);
        voiceTexts = new VoiceTexts();
        new RetrofitInstance();
    }

    public void initialSettings() {
//        textFromSpeech.startVoiceRecognitionCycle();

//        try {
        speechFromText.speakOut(voiceTexts.getGreeting1());
//            Thread.sleep(5000);
//            while (!textFromSpeech.getFinishedResult()) {
//                Thread.sleep(100);
//            }
//            if (textFromSpeech.getSpokenText() != null) {
//                System.out.println("spoken " + textFromSpeech.getSpokenText());
//                if (textFromSpeech.getSpokenText().contains("my name")) {
//                    user.setName(textFromSpeech.getSpokenText());
//                }
//            System.out.println("name" + user.getName());
//            speechFromText.speakOut(voiceTexts.getGreeting2());
//            startVoice(voiceTexts.getTrainingsgoalQ());
//            if (textFromSpeech.getSpokenText().contains("my name")) {
//                user.setName(textFromSpeech.getSpokenText());
//            }
//        routes.sendSpokenText(data.get(0));
//        speechToTextView.setText(data.get(0));
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }


//    public void startVoice(String voiceTxt) {
//        try {
//            speechFromText.speakOut(voiceTxt);
//            Thread.sleep(5000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//            Log.e("VoiceFlow", "Can't sleep", e);
//        }
//    }

}
