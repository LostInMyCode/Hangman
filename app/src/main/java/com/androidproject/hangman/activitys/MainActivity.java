package com.androidproject.hangman.activitys;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;

import com.androidproject.hangman.R;
import com.androidproject.hangman.handler.GetFromFireStorage;
import com.androidproject.hangman.handler.NavigationHandler;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends FragmentActivity {

    private FirebaseAuth.AuthStateListener authListener;
    private FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //get firebase auth instance
        auth = FirebaseAuth.getInstance();

        //get current user
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        authListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user == null) {
                    // user auth state is changed - user is null
                    // launch login activity
                    //startActivity(new Intent(MainActivity.this, LoginActivity.class));
                    finish();
                }
            }
        };
        GetFromFireStorage.getInstance().getProfilePics();
        setTimer();
    }

    @Override
    public void onBackPressed(){

    }

    private void setTimer() {
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                NavigationHandler.changeToLoginActivity(getApplicationContext());
                //startActivity(new Intent(MainActivity.this, LoginActivity.class));
            }
        };
        timer.schedule(timerTask, 5000);
    }

}
