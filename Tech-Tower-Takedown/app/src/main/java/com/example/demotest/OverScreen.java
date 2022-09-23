package com.example.demotest;

import static com.example.demotest.ConfigurationScreen.player;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.graphics.Color;
import android.widget.TextView;

import com.example.demotest.data.Player;

public class OverScreen extends AppCompatActivity {
    private int blueInt;
    private int whiteInt;
    private TextView time;
    private TextView profit;
    private TextView towersBought;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // navigate to win or over screen
        Intent i = getIntent();
        Player player = (Player) i.getSerializableExtra("player");
        if (player.getWon()) {
            setContentView(R.layout.activity_win_screen);
            whiteInt = Color.parseColor("#ffffff");
            getWindow().setStatusBarColor(whiteInt);
        } else {
            setContentView(R.layout.activity_over_screen);
            blueInt = Color.parseColor("#3b6ca1");
            getWindow().setStatusBarColor(blueInt);
        }

        // back to welcome screen
        Button backToStart = findViewById(R.id.backToStart);
        backToStart.setOnClickListener(l -> {
            Intent j = new Intent(this, WelcomeScreen.class);
            startActivity(j);
            finish();
        });

        // initialize everything
        init();
    }

    // initialize everything
    private void init() {
        // text view
        time = findViewById(R.id.time2);
        profit = findViewById(R.id.profit2);
        towersBought = findViewById(R.id.towersBought2);

        // set text view
        time.setText("" + player.getTime());
        profit.setText("$" + player.getProfit());
        towersBought.setText("" + player.getTowersBought());
    }
}
