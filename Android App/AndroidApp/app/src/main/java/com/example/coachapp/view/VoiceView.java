package com.example.coachapp.view;

import android.os.Build;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.speech.tts.TextToSpeech;
import android.speech.tts.Voice;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.coachapp.R;
import com.example.coachapp.speech.SpeechFromText;
import com.example.coachapp.speech.SpeechRecognizerSetup;
import com.example.coachapp.connection.RetrofitInstance;

import java.util.Set;

public class VoiceView extends Fragment {
    //change IP adress in RetrofitInstance
    // start server with node app.js via terminal
    // Todo: send settings
    // Todo: check if first time app started


    private SpeechRecognizerSetup speechRecognizerSetup;
    private SpeechFromText speechFromText;

    private ImageButton voiceButton;
    private TextView speechToTextView;
    private TextView textToSpeechView;

    private TextToSpeech tts;

    private String voice1 = "Hi i am Coach Sam. What is your name?";
    private String voice2 = "Hi nice to meet you. Thanks for using me as your coach. For your first use I have some questions for you.";
    private String voice3 = "What is your trainingsgoal?";
    private String voice4 = "Are you a beginner, advanced or expert at sport in general";
    private String voice5 = "Do you want to train at the gym, at home or outdoor?";

    public VoiceView() {
        super(R.layout.fragment_voice_view);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_voice_view, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        speechToTextView = view.findViewById(R.id.speechToText);
        textToSpeechView = view.findViewById(R.id.textToSpeech);
        voiceButton = view.findViewById(R.id.voiceButton);
        speechRecognizerSetup = new SpeechRecognizerSetup(getActivity());
        speechFromText = new SpeechFromText(getActivity());

        new RetrofitInstance();

        voiceButton.setOnClickListener(view1 -> {
//            speechFromText.textToSpeech(voice1);
            speechToTextView.setText("");
            speechToTextView.setVisibility(View.VISIBLE);
            textToSpeechView.setVisibility(View.INVISIBLE);
            speechRecognizerSetup.startVoiceRecognitionCycle();
        });

    }

    private void changeButtonAndText(Boolean recording) {
        if (recording) {
            speechToTextView.setVisibility(View.VISIBLE);
            textToSpeechView.setVisibility(View.INVISIBLE);
            voiceButton.setImageResource(R.mipmap.voice_recorder);
        } else {
            speechToTextView.setVisibility(View.INVISIBLE);
            textToSpeechView.setVisibility(View.VISIBLE);
            voiceButton.setImageResource(R.mipmap.microphone);
        }
    }
}