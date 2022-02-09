package com.example.coachapp.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.coachapp.R;
import com.example.coachapp.speech.VoiceFlow;

public class VoiceView extends Fragment {

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
        ItemViewModel viewModel = new ViewModelProvider(requireActivity()).get(ItemViewModel.class);

        voiceButton.setOnClickListener(view1 -> {
            if (!voiceFlow.getFinished()) {
                voiceFlow.initialSettings();
            } else {
                voiceFlow.greeting();
            }
        });
    }
}