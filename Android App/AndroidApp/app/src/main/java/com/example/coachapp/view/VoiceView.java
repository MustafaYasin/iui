package com.example.coachapp.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.coachapp.R;
import com.example.coachapp.speech.SpeechFromText;
import com.example.coachapp.speech.TextFromSpeech;
import com.example.coachapp.speech.VoiceFlow;

public class VoiceView extends Fragment {
    //change IP adress in RetrofitInstance
    // start server with node app.js via terminal
    // Todo: send settings
    // Todo: check if first time app started

    private TextFromSpeech textFromSpeech;
    private SpeechFromText speechFromText;
    private VoiceFlow voiceFlow;

    private ImageButton voiceButton;

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
        voiceButton = view.findViewById(R.id.voiceButton);
        voiceFlow = VoiceFlow.getInstance();
        voiceFlow.setActivity(getActivity());
        //voiceFlow = new VoiceFlow(getActivity());
//        voiceFlow.initialSettings();

        voiceButton.setOnClickListener(view1 -> {
            voiceFlow.initialSettings();
        });
    }
}