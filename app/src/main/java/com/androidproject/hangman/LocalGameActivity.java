package com.androidproject.hangman;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LocalGameActivity extends AppCompatActivity {

    String expectedWord = "";
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_game);
        initEditTextView();
        initAcceptButton();
    }

    @Override
    public void onBackPressed(){
        AlertHandler.wantToLeave(this);
    }

    private void initEditTextView() {
        this.editText = (EditText) findViewById(R.id.local_game_userinputview);
    }

    private void initAcceptButton() {
        Button loginButton = (Button) findViewById(R.id.local_game_acceptBtn);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!editText.getText().toString().isEmpty()) {
                    setExpectedWord(editText.getText().toString());
                    NavigationHandler.changeToSoloGameActivityWithExpectedWord(getApplicationContext(), getExpectedWord());
                }
            }
        });
    }

    private void setExpectedWord(String expectedWord) {
        this.expectedWord = expectedWord;
    }

    private String getExpectedWord() {
        return expectedWord;
    }
}
