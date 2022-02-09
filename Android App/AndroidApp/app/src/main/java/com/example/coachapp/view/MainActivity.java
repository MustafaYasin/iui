package com.example.coachapp.view;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.ViewModelProvider;

import com.example.coachapp.R;
import com.example.coachapp.location.GoogleMapsApp;
import com.example.coachapp.location.NearbyPlaces;
import com.example.coachapp.location.CurrentLocation;
import com.example.coachapp.model.User;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private BottomNavigationView bottomNavigationView;
    private VoiceView voiceView = new VoiceView();
    private TrainingsplanView trainingsplanView = new TrainingsplanView();
    private MapsFragment mapsFragment = new MapsFragment();
    private NearbyPlaces nearbyPlaces;
    private GoogleMapsApp googleMapsApp;

    private ItemViewModel viewModel;
    private CurrentLocation newGetGPS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.voiceNav);
        viewModel = new ViewModelProvider(this).get(ItemViewModel.class);

        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);
        } else {
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION}, 44);
        }
        newGetGPS = new CurrentLocation(this);
        viewModel.selectCurrentLocation(newGetGPS.getLastLocation());
        googleMapsApp = new GoogleMapsApp(this, viewModel);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.voiceNav:
                getSupportFragmentManager().beginTransaction().replace(R.id.mainActivity, voiceView).commit();
                return true;
            case R.id.trainingsplanNav:
                getSupportFragmentManager().beginTransaction().replace(R.id.mainActivity, trainingsplanView).commit();
                return true;
            case R.id.mapsNav:
                googleMapsApp.openMapsWithCurrentLocation();
//                getSupportFragmentManager().beginTransaction().replace(R.id.mainActivity, mapsFragment).commit();
                return true;
        }
        return false;
    }
}