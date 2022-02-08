package com.example.coachapp.view;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.provider.Settings;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.coachapp.R;
import com.example.coachapp.location.GPSLocation;
import com.example.coachapp.location.NewGetGPS;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    BottomNavigationView bottomNavigationView;
    VoiceView voiceView = new VoiceView();
    TrainingsplanView trainingsplanView = new TrainingsplanView();
    MapsFragment mapsFragment = new MapsFragment();

    private ItemViewModel viewModel;
    private NewGetGPS newGetGPS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.voiceNav);

        newGetGPS = new NewGetGPS(this);
        viewModel = new ViewModelProvider(this).get(ItemViewModel.class);
        newGetGPS.getLastLocation();
        viewModel.selectCurrentLocation(newGetGPS.getLastLocation());
    }


//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        if (requestCode == newGetGPS.getPERMISSION_ID()) {
//            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                viewModel.selectCurrentLocation(newGetGPS.getLastLocation());
//            }
//        }
//    }
//
//    @Override
//    public void onResume() {
//        super.onResume();
//        if (newGetGPS.checkPermissions()) {
//            viewModel.selectCurrentLocation(newGetGPS.getLastLocation());
//        }
//    }




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
                getSupportFragmentManager().beginTransaction().replace(R.id.mainActivity, mapsFragment).commit();
                return true;
        }
        return false;
    }
}