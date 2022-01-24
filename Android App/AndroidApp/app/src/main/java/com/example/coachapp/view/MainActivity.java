package com.example.coachapp.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.coachapp.R;
import com.example.coachapp.connection.RetrofitInterface;
import com.example.coachapp.connection.RetrofitRoutes;
import com.example.coachapp.gps.GPSLocation;
import com.example.coachapp.speech.SpeechToText;
import com.example.coachapp.speech.TextToSpeech;

import org.w3c.dom.Text;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private Retrofit retrofit;
    private RetrofitInterface retrofitInterface;
    private String BASE_URL = "http://10.0.2.2:3000"; // for emulator on port 3000
    private String location = "123456";
    private String spokenText = "hello again";

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        RetrofitRoutes retrofitRoutes = new RetrofitRoutes();

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        retrofitInterface = retrofit.create(RetrofitInterface.class);

        TextView speechText = findViewById(R.id.speechText);

        findViewById(R.id.coachButton).setOnClickListener(view -> {
            speechText.setVisibility(View.VISIBLE);
            System.out.println("PRessed");
//            HashMap<String, String> map = new HashMap<>();
//            map.put("spokenText", spokenText);
//            map.put("location", location);
            Call<SpeechToText> call = retrofitInterface.sendSpokenText("map");
            call.enqueue(new Callback<SpeechToText>() {
                @Override
                public void onResponse(Call<SpeechToText> call, Response<SpeechToText> response) {
                    if (response.code() == 200) {
                        System.out.println("REsponded ");
                        SpeechToText respondSpeech = response.body();
                        speechText.setText(respondSpeech.getSpokenText());
                        System.out.println("test " + respondSpeech.toString());
                    }

                }
                // Todo: error handling for status code 400

                @Override
                public void onFailure(Call<SpeechToText> call, Throwable t) {
                    Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();

                }
            });
//             Send spokenText after finished recognition
        });

        findViewById(R.id.sendBtn).setOnClickListener(view -> {
            System.out.println("PRessed location");
//            Call<GPSLocation> call = retrofitInterface.sendGPSLocation(location);
//            call.enqueue(new Callback<GPSLocation>() {
//                @Override
//                public void onResponse(Call<GPSLocation> call, Response<GPSLocation> response) {
//                    if (response.code() == 200) {
//                        System.out.println("REsponded ");
//                        GPSLocation respondLocation = response.body();
//                        speechText.setText(respondLocation.getLocation());
//                        System.out.println("test " + respondSpeech.toString());
//                    }
//
//                }
//                // Todo: error handling for status code 400
//
//                @Override
//                public void onFailure(Call<GPSLocation> call, Throwable t) {
//                    Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
//
//                }
//            });
//
        });
    }
}