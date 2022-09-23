package com.example.demotest;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.graphics.Color;

public class WelcomeScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);

        // change action bar & status bar colors
        int blueInt = Color.parseColor("#e1f3fe");
        getWindow().setStatusBarColor(blueInt);

        // start button
        Button b = findViewById(R.id.startButton);
        b.setOnClickListener(l -> {
            Intent i = new Intent(this, ConfigurationScreen.class);
            startActivity(i);
            finish();
        });

        // quit button
        Button quitButton = findViewById(R.id.quitButton);
        quitButton.setOnClickListener(l -> {
            System.exit(0);
        });
    }
}
