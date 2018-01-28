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
        initLocalGameButton();
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
                NavigationHandler.changeToSoloGameActivity(getApplicationContext());
            }
        });
    }

    private void initLocalGameButton() {
        Button loginButton = (Button) findViewById(R.id.local_game_button);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavigationHandler.changeToLocalGameActivity(getApplicationContext());
            }
        });
    }

    private void initSettingsButton() {
        Button loginButton = (Button) findViewById(R.id.settings_button);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavigationHandler.changeToSettingsActivity(getApplicationContext());
            }
        });
    }

}
