package com.example.coachapp.location;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Looper;
import android.provider.Settings;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;

import com.example.coachapp.view.ItemViewModel;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

public class GPSLocation {

    private FusedLocationProviderClient fusedLocationProviderClient;
    private final int PERMISSION_ID = 44;
    private Activity activity;
    private Double longitude;
    private Double latitude;
    private Double[] currentLocation;

    public GPSLocation(Activity activity) {
        this.activity = activity;
        if (fusedLocationProviderClient == null) {
            fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(activity);
        }
    }

    @SuppressLint("MissingPermission")
    public Double[] getLastLocation() {
        if (checkPermissions()) {
            if (isLocationEnabled()) {
//                getFusedLocationProviderClient().getLastLocation().addOnCompleteListener(task -> {
                fusedLocationProviderClient.getLastLocation().addOnSuccessListener(activity, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        if (location != null) {
                            latitude = location.getLatitude();
                            longitude = location.getLongitude();
                            currentLocation = new Double[]{latitude, longitude};
                        } else {
                            requestNewLocationData();
                        }
                    }
                });
//                    Location location = task.getResult();
//                    if (location == null) {
//                        requestNewLocationData();
//                    } else {
//                        latitude = location.getLatitude();
//                        longitude = location.getLongitude();
//                        currentLocation = new Double[]{latitude, longitude};
//                    }
//                });
            } else {
                Toast.makeText(activity, "Please turn on your GPS...", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                activity.startActivity(intent);
            }
        } else {
            requestPermissions();
        }
        return currentLocation;
    }

    @SuppressLint("MissingPermission")
    public void requestNewLocationData() {
        LocationRequest mLocationRequest = new LocationRequest();
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mLocationRequest.setInterval(5);
        mLocationRequest.setFastestInterval(0);
        mLocationRequest.setNumUpdates(1);
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(activity);
        fusedLocationProviderClient.requestLocationUpdates(mLocationRequest, mLocationCallback, Looper.myLooper());
    }

    private LocationCallback mLocationCallback = new LocationCallback() {

        @Override
        public void onLocationResult(LocationResult locationResult) {
            Location mLastLocation = locationResult.getLastLocation();
            latitude = mLastLocation.getLatitude();
            longitude = mLastLocation.getLongitude();
            currentLocation = new Double[]{latitude, longitude};
        }
    };

    public boolean checkPermissions() {
        return ActivityCompat.checkSelfPermission(activity,
                Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED;
    }

    public void requestPermissions() {
        ActivityCompat.requestPermissions(activity, new String[]{
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSION_ID);
    }

    public boolean isLocationEnabled() {
        LocationManager locationManager = (LocationManager) activity.getSystemService(Context.LOCATION_SERVICE);
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
    }

    public FusedLocationProviderClient getFusedLocationProviderClient() {
        return fusedLocationProviderClient;
    }

    public int getPERMISSION_ID() {
        return PERMISSION_ID;
    }

    public Double getLongitude() {
        return longitude;
    }

    public Double getLatitude() {
        return latitude;
    }


}
