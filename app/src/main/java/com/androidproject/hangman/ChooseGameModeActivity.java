package com.androidproject.hangman;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ChooseGameModeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_game_mode);
        initSoloGameButton();
        initSettingsButton();
    }

    @Override
    public void onBackPressed() {

    }

    private void initSoloGameButton() {
        Button loginButton = (Button) findViewById(R.id.solo_game_button);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeToSoloGameActivity();
            }
        });
    }

    private void initSettingsButton() {
        Button loginButton = (Button) findViewById(R.id.settings_button);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeToSettingsActivity();
            }
        });
    }

    private void changeToSettingsActivity() {
        startActivity(new Intent(ChooseGameModeActivity.this, SettingsActivity.class));
    }

    private void changeToSoloGameActivity() {
        startActivity(new Intent(this, SoloGameActivity.class));
    }
}
