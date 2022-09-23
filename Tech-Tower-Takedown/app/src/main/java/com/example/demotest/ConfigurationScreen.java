package com.example.demotest;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.graphics.Color;
import com.example.demotest.data.Player;

public class ConfigurationScreen extends AppCompatActivity {
    private String difficulty;
    private int yellowInt = Color.parseColor("#ffdb61");
    private int whiteInt = Color.parseColor("#ffffff");
    public static Player player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuration_screen);

        // edit text
        EditText name = (EditText) findViewById(R.id.nameBox);

        //  all the buttons
        Button easyButton = findViewById(R.id.difficulty1);
        Button normalButton = findViewById(R.id.difficulty2);
        Button hardButton = findViewById(R.id.difficulty3);
        Button nextButton = findViewById(R.id.nextButton);
        Button backButton = findViewById(R.id.backButton);
        Button confirmButton = findViewById(R.id.confirmButton);

        // colors
        int blueInt = Color.parseColor("#e1f3fe");

        // change colors of action & status bars
        getWindow().setStatusBarColor(blueInt);

        // if easy difficulty has been selected
        easyButton.setOnClickListener(l -> {
            difficulty = "Easy";
            setTextColor(easyButton, normalButton, hardButton);
        });

        // if normal difficulty has been selected
        normalButton.setOnClickListener(l -> {
            difficulty = "Normal";
            setTextColor(normalButton, easyButton, hardButton);
        });

        // if hard difficulty has been selected
        hardButton.setOnClickListener(l -> {
            difficulty = "Hard";
            setTextColor(hardButton, easyButton, normalButton);
        });

        // remove keyboard after pressing button
        confirmButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                name.onEditorAction(EditorInfo.IME_ACTION_DONE);
            }
        });

        // backButton
        backButton.setOnClickListener(l -> {
            Intent i = new Intent(this, WelcomeScreen.class);
            startActivity(i);
            finish();
        });

        // move onto the game screen
        nextButton.setOnClickListener(l -> {

            // input username requirements
            String stringName = name.getText().toString();
            boolean correctInput = true;

            // if username requirements are not met
            if (stringName == null || stringName.trim().length() == 0) {
                correctInput = false;
            }

            // navigates to respective game levels
            if (correctInput && (difficulty != null)) {
                player = new Player(stringName, difficulty);
                Intent i = new Intent(this, GameScreen.class);
                startActivity(i);
                finish();
            }
        });
    }

    // changes the text color of the buttons
    public void setTextColor(Button fButton, Button sButton, Button tButton) {
        fButton.setTextColor(yellowInt);
        sButton.setTextColor(whiteInt);
        tButton.setTextColor(whiteInt);
    }
}
