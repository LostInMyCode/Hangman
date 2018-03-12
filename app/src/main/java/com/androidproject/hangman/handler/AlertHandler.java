package com.androidproject.hangman.handler;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;

import com.androidproject.hangman.activitys.DrawerFragmentActivity;

/**
 * Created by aybarscavus on 17.01.18.
 */

public class AlertHandler {

    static Context appContext;

    public static void showStandardAlert(Context context, String title, String message) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.create();
        alertDialogBuilder.setTitle(title);
        alertDialogBuilder.setMessage(message);
        alertDialogBuilder.setCancelable(true);
        alertDialogBuilder.setNeutralButton("OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        alertDialogBuilder.show();
    }

    public static void showGameOverAlert(final Context context, String solutionWord) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.create();
        alertDialogBuilder.setTitle("Game Over");
        alertDialogBuilder.setMessage("Du hast verloren.\nDie richtige Lösung war " + solutionWord + " .");
        alertDialogBuilder.setCancelable(false);
        alertDialogBuilder.setNeutralButton("OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        Intent intent = new Intent(context, DrawerFragmentActivity.class);
                        context.startActivity(intent);
                    }
                });
        alertDialogBuilder.show();
    }

    public static void showWinningAlert(final Context context, String solutionWord) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.create();
        alertDialogBuilder.setTitle("Du hast gewonnen");
        alertDialogBuilder.setMessage("Glückwunsch du hast gewonnen. Du hast das Wort : " + solutionWord + " richtig erraten.\n");
        alertDialogBuilder.setCancelable(false);
        alertDialogBuilder.setNeutralButton("OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        Intent intent = new Intent(context, DrawerFragmentActivity.class);
                        context.startActivity(intent);
                    }
                });
        alertDialogBuilder.show();
    }

    public static void wantToLeave(final Context context) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.create();
        alertDialogBuilder.setMessage("Bist du dir sicher das du das Spiel verlassen willst?");
        alertDialogBuilder.setPositiveButton("Ja", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                NavigationHandler.changeToDrawerTest(context);
            }
        });
        alertDialogBuilder.setNegativeButton("Nein", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        alertDialogBuilder.show();
    }

}
