package com.androidproject.hangman;

import android.content.Context;
import android.content.Intent;

/**
 * Created by aybarscavus on 07.12.17.
 */

public class NavigationHandler {

    public static void changeToLoginActivity(Context context) {
        context.startActivity(new Intent(context, LoginActivity.class));
    }

    public static void changeToSettingsActivity(Context context) {
        context.startActivity(new Intent(context, SettingsActivity.class));
    }

    public static void changeToSoloGameActivity(Context context) {
        context.startActivity(new Intent(context, SoloGameActivity.class));
    }

    public static void changeToLocalGameActivity(Context context) {
        context.startActivity(new Intent(context, LocalGameActivity.class));
    }

}
