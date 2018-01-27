package com.androidproject.hangman;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class SoloGameActivity extends AppCompatActivity {
    
    int maxTry = 10;
    int failureCount = 0;
    String pressedLetter = "";
    String missingWord = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solo_game);
        initStoredData();
        initImageView();
        initTextView();
        AppData appData = new AppData(this);
        Log.d("1","/nDas gesuchte Wort : " + appData.getStoredData(1));
    }

    private void initStoredData() {
        AppData appData = new AppData(this);
        missingWord = appData.getStoredData(0);
    }

    private void initImageView() {
        ImageView imageView = (ImageView) findViewById(R.id.HangmanImageView);
        imageView.setImageResource(R.drawable.versuch_0);
    }

    private void initTextView() {
        TextView textView = (TextView) findViewById(R.id.HangmanTextView);
        textView.setText("");
        textView.setKeyListener(null);
    }

    private void addLettersToTextView() {
        TextView textView = (TextView) findViewById(R.id.HangmanTextView);
        for (int i = 0; i < missingWord.length(); i++) {
            if (missingWord.toLowerCase().charAt(i) == pressedLetter.charAt(0)) {
                textView.append(pressedLetter.toString());
            }
        }
    }

    private void checkIfTextViewContainsAllMissingWords() {
        TextView textView = (TextView) findViewById(R.id.HangmanTextView);
        textView.getText().toString().replace(" ","");
        if (textView.getText().toString().length() == missingWord.length()) {
            AlertHandler.showWinningAlert(this,missingWord);
        }
    }

    private void comparePressedLetterWithWord() {
        if (missingWord.toLowerCase().contains(pressedLetter)) {
            this.addLettersToTextView();
            this.checkIfTextViewContainsAllMissingWords();
        } else {
            switchImageView(failureCount++);
            if (failureCount == maxTry) {
                AlertHandler.showGameOverAlert(this);
            }
        }
        Log.d("1",missingWord);
    }

    public void letterBtnPressed(View view) {
        switch (view.getId()) {
            case R.id.letterButton_a:
                pressedLetter = "a";
                comparePressedLetterWithWord();
                break;
            case R.id.letterButton_b:
                pressedLetter = "b";
                comparePressedLetterWithWord();
                break;
            case R.id.letterButton_c:
                pressedLetter = "c";
                comparePressedLetterWithWord();
                break;
            case R.id.letterButton_d:
                pressedLetter = "d";
                comparePressedLetterWithWord();
                break;
            case R.id.letterButton_e:
                pressedLetter = "e";
                comparePressedLetterWithWord();
                break;
            case R.id.letterButton_f:
                pressedLetter = "f";
                comparePressedLetterWithWord();
                break;
            case R.id.letterButton_g:
                pressedLetter = "g";
                comparePressedLetterWithWord();
                break;
            case R.id.letterButton_h:
                pressedLetter = "h";
                comparePressedLetterWithWord();
                break;
            case R.id.letterButton_i:
                pressedLetter = "i";
                comparePressedLetterWithWord();
                break;
            case R.id.letterButton_j:
                pressedLetter = "j";
                comparePressedLetterWithWord();
                break;
            case R.id.letterButton_k:
                pressedLetter = "k";
                comparePressedLetterWithWord();
                break;
            case R.id.letterButton_l:
                pressedLetter = "l";
                comparePressedLetterWithWord();
                break;
            case R.id.letterButton_m:
                pressedLetter = "m";
                comparePressedLetterWithWord();
                break;
            case R.id.letterButton_n:
                pressedLetter = "n";
                comparePressedLetterWithWord();
                break;
            case R.id.letterButton_o:
                pressedLetter = "o";
                comparePressedLetterWithWord();
                break;
            case R.id.letterButton_p:
                pressedLetter = "p";
                comparePressedLetterWithWord();
                break;
            case R.id.letterButton_q:
                pressedLetter = "q";
                comparePressedLetterWithWord();
                break;
            case R.id.letterButton_r:
                pressedLetter = "r";
                comparePressedLetterWithWord();
                break;
            case R.id.letterButton_s:
                pressedLetter = "s";
                comparePressedLetterWithWord();
                break;
            case R.id.letterButton_t:
                pressedLetter = "t";
                comparePressedLetterWithWord();
                break;
            case R.id.letterButton_u:
                pressedLetter = "u";
                comparePressedLetterWithWord();
                break;
            case R.id.letterButton_v:
                pressedLetter = "v";
                comparePressedLetterWithWord();
                break;
            case R.id.letterButton_w:
                pressedLetter = "w";
                comparePressedLetterWithWord();
                break;
            case R.id.letterButton_x:
                pressedLetter = "x";
                comparePressedLetterWithWord();
                break;
            case R.id.letterButton_y:
                pressedLetter = "y";
                comparePressedLetterWithWord();
                break;
            case R.id.letterButton_z:
                pressedLetter = "z";
                comparePressedLetterWithWord();
                break;
        }
    }

    private void switchImageView(int currentTry) {
        ImageView imageView = (ImageView) findViewById(R.id.HangmanImageView);
        switch (currentTry) {
            case 1 :
                imageView.setImageResource(R.drawable.versuch_1);
                break;
            case 2:
                imageView.setImageResource(R.drawable.versuch_2);
                break;
            case 3:
                imageView.setImageResource(R.drawable.versuch_3);
                break;
            case 4:
                imageView.setImageResource(R.drawable.versuch_4);
                break;
            case 5:
                imageView.setImageResource(R.drawable.versuch_5);
                break;
            case 6:
                imageView.setImageResource(R.drawable.versuch_6);
                break;
            case 7:
                imageView.setImageResource(R.drawable.versuch_7);
                break;
            case 8:
                imageView.setImageResource(R.drawable.versuch_8);
                break;
            case 9:
                imageView.setImageResource(R.drawable.versuch_9);
                break;
            default:
                break;
        }
    }

}
