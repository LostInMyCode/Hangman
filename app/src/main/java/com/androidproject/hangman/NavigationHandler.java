package com.androidproject.hangman;

import android.content.Context;
import android.content.Intent;

/**
 * Created by aybarscavus on 07.12.17.
 */

public class NavigationHandler {

    public static final String EXPECTED_WORD_KEY = "EXPECTED_WORD";
    public static final String SINGLE_PLAYER_KEY = "SINGLE_PLAYER";

    public static void changeToLoginActivity(Context context) {
        context.startActivity(new Intent(context, LoginActivity.class));
    }

    public static void changeToChooseGameModeActivity(Context context) {
        context.startActivity(new Intent(context, ChooseGameModeActivity.class));
    }

    public static void changeToSettingsActivity(Context context) {
        context.startActivity(new Intent(context, SettingsActivity.class));
    }

    public static void changeToSoloGameActivity(Context context) {
        Intent intent = new Intent(context, SoloGameActivity.class);
        intent.putExtra(SINGLE_PLAYER_KEY, "SINGLE_PLAYER");
        context.startActivity(intent);
    }

    public static void changeToSoloGameActivityWithExpectedWord(Context context, String expectedWord) {
        Intent intent = new Intent(context, SoloGameActivity.class);
        intent.putExtra(EXPECTED_WORD_KEY, expectedWord);
        context.startActivity(intent);
    }

    public static void changeToLocalGameActivity(Context context) {
        context.startActivity(new Intent(context, LocalGameActivity.class));
    }

}
