package com.example.coachapp.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.coachapp.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView speechText = findViewById(R.id.speechText);
        ImageButton coachButton = findViewById(R.id.coachButton);
        coachButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                speechText.setVisibility(View.VISIBLE);
            }
        });
    }
}