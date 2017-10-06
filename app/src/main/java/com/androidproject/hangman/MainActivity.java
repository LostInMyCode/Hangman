package com.androidproject.hangman;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTimer();
    }

    private void setTimer() {
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                changeActivity();
            }
        };
        timer.schedule(timerTask, 5000);
    }

    private void changeActivity() {
        System.out.println("TEST");
        startActivity(new Intent(MainActivity.this, LoginActivity.class));
    }
}
