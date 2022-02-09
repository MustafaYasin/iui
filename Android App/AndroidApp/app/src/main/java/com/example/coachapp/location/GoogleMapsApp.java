package com.example.coachapp.location;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;

import androidx.lifecycle.LifecycleOwner;

import com.example.coachapp.view.ItemViewModel;

public class GoogleMapsApp {

    private ItemViewModel viewModel;
    private Activity activity;

    public GoogleMapsApp(Activity activity) {
        this.activity = activity;
    }

    public GoogleMapsApp(Activity activity, ItemViewModel viewModel) {
        this.activity = activity;
        this.viewModel = viewModel;
    }

    public void openMapsWithCurrentLocation() {
        viewModel.getCurrentLocation().observe((LifecycleOwner) activity, item -> {
            Uri currentLocation = Uri.parse("geo:" + item[0] + "," + item[1]);
            Intent mapIntent = new Intent(Intent.ACTION_VIEW, currentLocation);
            mapIntent.setPackage("com.google.android.apps.maps");
            activity.startActivity(mapIntent);
        });
    }

    public void searchNearby(String searchFor) {
        Uri gmmIntentUri = Uri.parse("geo:0,0?q=" + searchFor);
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        activity.startActivity(mapIntent);
    }
}
