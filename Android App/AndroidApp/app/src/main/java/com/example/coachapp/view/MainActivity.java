package com.example.coachapp.view;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.coachapp.R;
import com.example.coachapp.location.GPSLocation;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    BottomNavigationView bottomNavigationView;
    VoiceView voiceView = new VoiceView();
    TrainingsplanView trainingsplanView = new TrainingsplanView();

    private GPSLocation gpsLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.voiceNav);

        gpsLocation = new GPSLocation(this);
        gpsLocation.getLastLocation();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == gpsLocation.getPERMISSION_ID()) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                gpsLocation.getLastLocation();
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (gpsLocation.checkPermissions()) {
            gpsLocation.getLastLocation();
        }
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
        }
        return false;
    }
}