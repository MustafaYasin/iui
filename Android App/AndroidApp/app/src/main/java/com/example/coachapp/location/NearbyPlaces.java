package com.example.coachapp.location;

import android.util.Log;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Not used
 */
public class NearbyPlaces {

    private static final String TAG = NearbyPlaces.class.getSimpleName();

    private final String API_KEY = "AIzaSyApLLSXl_Qss0hyL8S3VwMe5P5pU3cWRz0";
    private final String nearbyTest = "https://maps.googleapis.com/maps/api/place/findplacefromtext/json?input=Museum%20of%20Contemporary%20Art%20Australia&inputtype=textquery&fields=formatted_address%2Cname%2Crating%2Copening_hours%2Cgeometry&key=";

    public NearbyPlaces() {
        try {
            OkHttpClient client = new OkHttpClient().newBuilder()
                    .build();
            Request request = new Request.Builder()
                    .url(nearbyTest + API_KEY)
                    .method("GET", null)
                    .build();
            Response response = client.newCall(request).execute();
            Log.e(TAG, String.valueOf(response));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
