package com.example.coachapp.connection;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.coachapp.view.MainActivity;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RetrofitInstance {

    private static final String TAG = "RetrofitInstance";

    //    private Retrofit retrofit;
    private RetrofitInterface retrofitInterface;
    private String device_url = "http://192.168.178.33";
    // Real device: connect both to same internet connection, search on windows for IP via terminal >ipconfig
    private String emulator_url = "http://10.0.2.2";
    private String port = "3000";
    String BASE_URL = device_url + ":" + port;

    public RetrofitInstance() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        retrofitInterface = retrofit.create(RetrofitInterface.class);
//        retrofitInterface = ServiceGenerator.createService(RetrofitInterface.class);
    }

    public String sendSpokenText(String spokenText, Context context) {
        System.out.println("Spoken text ");
        final String[] responseMessage = new String[1];
//        String responseMessage;
//            HashMap<String, String> map = new HashMap<>();
//            map.put("spokenText", spokenText);
//            map.put("location", location);
//        TaskService taskService = ServiceGenerator.createService(TaskService.class);

        Call<String> call = retrofitInterface.sendSpokenText(spokenText);
        System.out.println("sended");
//        try {
//            Response<String> response = call.execute();
//            responseMessage = response.body();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()) {
//                    System.out.println("REsponded ");
//                    try {
//                        response = call.execute();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                        Log.e(TAG, "nope");
//                    }
//
                    responseMessage[0] = response.body();

//                    responseMessage = msg;
//                    speechText.setText(respondSpeech.getSpokenText());
//                    System.out.println("test " + responseMessage);
                } else {
                    Log.e(TAG, "Response was not successful");
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_LONG).show();
                Log.e(TAG, t.getMessage());
            }
        });
        System.out.println("msg retro " + responseMessage[0]);
        return responseMessage[0];
    }


}
