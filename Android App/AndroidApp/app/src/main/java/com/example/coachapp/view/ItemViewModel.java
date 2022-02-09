package com.example.coachapp.view;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ItemViewModel extends ViewModel {

    private final MutableLiveData<Double[]> currentLocation = new MutableLiveData<>();

    public void selectCurrentLocation(Double[] location) {
        currentLocation.setValue(location);
    }

    public LiveData<Double[]> getCurrentLocation() {
        return currentLocation;
    }

}
