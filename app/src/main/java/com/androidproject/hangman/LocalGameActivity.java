package com.androidproject.hangman;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LocalGameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_game);
        initEditTextView();
        initAcceptButton();
    }

    private void initEditTextView() {
        EditText editText = (EditText) findViewById(R.id.local_game_userinputview);
    }

    private void initAcceptButton() {
        Button loginButton = (Button) findViewById(R.id.local_game_acceptBtn);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
