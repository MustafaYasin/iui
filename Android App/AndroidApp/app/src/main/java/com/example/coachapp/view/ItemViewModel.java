package com.example.coachapp.view;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.coachapp.speech.VoiceFlow;

public class ItemViewModel extends ViewModel {

    private final MutableLiveData<Double[]> currentLocation = new MutableLiveData<>();
    private final MutableLiveData<Boolean> userSettingsFinished = new MutableLiveData<>();

    public void selectCurrentLocation(Double[] location) {
        currentLocation.setValue(location);
    }

    public LiveData<Double[]> getCurrentLocation() {
        return currentLocation;
    }

    public void selectUserSettingsFinished(Boolean finished) {
        userSettingsFinished.setValue(finished);
    }

    public LiveData<Boolean> getUserSettingsFinished() {
        return userSettingsFinished;
    }
}
