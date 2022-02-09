package com.example.coachapp.location;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

public class CurrentLocation {

    private FusedLocationProviderClient fusedLocationProviderClient;
    private Context context;

    public CurrentLocation(Context context) {
        this.context = context;
        if (fusedLocationProviderClient == null) {
            fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context);
        }
    }

    @SuppressLint("MissingPermission")
    public Double[] getLastLocation() {
        Double[] currentLocation = new Double[2];
        fusedLocationProviderClient.getLastLocation().addOnSuccessListener((Activity) context, location -> {
            if (location != null) {
                currentLocation[0] = location.getLatitude();
                currentLocation[1] = location.getLongitude();
            } else {
                LocationRequest mLocationRequest = new LocationRequest();
                mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
                mLocationRequest.setInterval(5);
                mLocationRequest.setFastestInterval(0);
                mLocationRequest.setNumUpdates(1);
                fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context);
                getLastLocation();
            }
        });
        return currentLocation;
    }
}
