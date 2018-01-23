package com.androidproject.hangman;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        initLogoutBtn();
    }

    private void changeToLoginActivity() {
        startActivity(new Intent(SettingsActivity.this, LoginActivity.class));
    }

    private void initLogoutBtn() {
        Button loginButton = (Button) findViewById(R.id.logout_button);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeToLoginActivity();
            }
        });
    }
}