package com.androidproject.hangman;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class SoloGameActivity extends AppCompatActivity {
    
    int maxTry = 10;
    int failureCount = 1;
    String pressedLetter = "";
    String missingWord = "";
    String enteredCorrectWord = "";
    String alreadyCompletedWord = "";
    ArrayList<String> guessedLetters = new ArrayList<>();
    AppData appData = new AppData(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solo_game);
        if (this.getIntent().hasExtra(NavigationHandler.SINGLE_PLAYER_KEY)) {
            appData.storeData();
            initStoredData();
        } else {
            missingWord = getIntent().getStringExtra(NavigationHandler.EXPECTED_WORD_KEY);
        }
        Log.d("1","/nDas gesuchte Wort : " + missingWord);
        initImageView();
        initTextView();
    }

    private void initStoredData() {
        missingWord = appData.getStoredRandomWord();
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

    @Override
    public void onBackPressed() {
        NavigationHandler.changeToDrawerTest(getApplicationContext());
    }

    private void addLettersToTextView() {
        TextView textView = (TextView) findViewById(R.id.HangmanTextView);
        alreadyCompletedWord = "";
        textView.setText("");
        if (missingWord.toLowerCase().contains(pressedLetter)){
            guessedLetters.add(pressedLetter);
        }
        for (int i = 0; i < missingWord.length(); i++) {
            if (guessedLetters.contains(String.valueOf(missingWord.toLowerCase().charAt(i)))) {
                alreadyCompletedWord = alreadyCompletedWord.concat(String.valueOf(missingWord.toLowerCase().charAt(i)));
            } else {
                alreadyCompletedWord = alreadyCompletedWord.concat("_");
            }
        }
        textView.setText(this.alreadyCompletedWord);
    }

    private void checkIfTextViewContainsAllMissingWords() {
        TextView textView = (TextView) findViewById(R.id.HangmanTextView);
        textView.getText().toString().replace(" ","");
        if (textView.getText().toString().equals(missingWord.toLowerCase())) {
            AlertHandler.showWinningAlert(this, missingWord);
        }
    }

    private void comparePressedLetterWithWord(int pressedButton) {
        if (missingWord.toLowerCase().contains(pressedLetter)) {
            this.addLettersToTextView();
            this.checkIfTextViewContainsAllMissingWords();
        } else {
            switchImageView(failureCount++);
            if (failureCount == maxTry) {
                AlertHandler.showGameOverAlert(this, missingWord);
            }
        }
        Button btn = (Button)findViewById(pressedButton);
        btn.setEnabled(false);
    }

    public void letterBtnPressed(View view) {
        switch (view.getId()) {
            case R.id.letterButton_a:
                pressedLetter = "a";
                comparePressedLetterWithWord(R.id.letterButton_a);
                break;
            case R.id.letterButton_b:
                pressedLetter = "b";
                comparePressedLetterWithWord(R.id.letterButton_b);
                break;
            case R.id.letterButton_c:
                pressedLetter = "c";
                comparePressedLetterWithWord(R.id.letterButton_c);
                break;
            case R.id.letterButton_d:
                pressedLetter = "d";
                comparePressedLetterWithWord(R.id.letterButton_d);
                break;
            case R.id.letterButton_e:
                pressedLetter = "e";
                comparePressedLetterWithWord(R.id.letterButton_e);
                break;
            case R.id.letterButton_f:
                pressedLetter = "f";
                comparePressedLetterWithWord(R.id.letterButton_f);
                break;
            case R.id.letterButton_g:
                pressedLetter = "g";
                comparePressedLetterWithWord(R.id.letterButton_g);
                break;
            case R.id.letterButton_h:
                pressedLetter = "h";
                comparePressedLetterWithWord(R.id.letterButton_h);
                break;
            case R.id.letterButton_i:
                pressedLetter = "i";
                comparePressedLetterWithWord(R.id.letterButton_i);
                break;
            case R.id.letterButton_j:
                pressedLetter = "j";
                comparePressedLetterWithWord(R.id.letterButton_j);
                break;
            case R.id.letterButton_k:
                pressedLetter = "k";
                comparePressedLetterWithWord(R.id.letterButton_k);
                break;
            case R.id.letterButton_l:
                pressedLetter = "l";
                comparePressedLetterWithWord(R.id.letterButton_l);
                break;
            case R.id.letterButton_m:
                pressedLetter = "m";
                comparePressedLetterWithWord(R.id.letterButton_m);
                break;
            case R.id.letterButton_n:
                pressedLetter = "n";
                comparePressedLetterWithWord(R.id.letterButton_n);
                break;
            case R.id.letterButton_o:
                pressedLetter = "o";
                comparePressedLetterWithWord(R.id.letterButton_o);
                break;
            case R.id.letterButton_p:
                pressedLetter = "p";
                comparePressedLetterWithWord(R.id.letterButton_p);
                break;
            case R.id.letterButton_q:
                pressedLetter = "q";
                comparePressedLetterWithWord(R.id.letterButton_q);
                break;
            case R.id.letterButton_r:
                pressedLetter = "r";
                comparePressedLetterWithWord(R.id.letterButton_r);
                break;
            case R.id.letterButton_s:
                pressedLetter = "s";
                comparePressedLetterWithWord(R.id.letterButton_s);
                break;
            case R.id.letterButton_t:
                pressedLetter = "t";
                comparePressedLetterWithWord(R.id.letterButton_t);
                break;
            case R.id.letterButton_u:
                pressedLetter = "u";
                comparePressedLetterWithWord(R.id.letterButton_u);
                break;
            case R.id.letterButton_v:
                pressedLetter = "v";
                comparePressedLetterWithWord(R.id.letterButton_v);
                break;
            case R.id.letterButton_w:
                pressedLetter = "w";
                comparePressedLetterWithWord(R.id.letterButton_w);
                break;
            case R.id.letterButton_x:
                pressedLetter = "x";
                comparePressedLetterWithWord(R.id.letterButton_x);
                break;
            case R.id.letterButton_y:
                pressedLetter = "y";
                comparePressedLetterWithWord(R.id.letterButton_y);
                break;
            case R.id.letterButton_z:
                pressedLetter = "z";
                comparePressedLetterWithWord(R.id.letterButton_z);
                break;
            case R.id.letterButton_ä:
                pressedLetter = "ä";
                comparePressedLetterWithWord(R.id.letterButton_ä);
                break;
            case R.id.letterButton_ö:
                pressedLetter = "ö";
                comparePressedLetterWithWord(R.id.letterButton_ö);
                break;
            case R.id.letterButton_ü:
                pressedLetter = "ü";
                comparePressedLetterWithWord(R.id.letterButton_ü);
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
