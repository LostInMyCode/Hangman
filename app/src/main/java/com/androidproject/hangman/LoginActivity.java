package com.androidproject.hangman;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class LoginActivity extends AppCompatActivity {

    ChooseGameModeActivity gameMode = new ChooseGameModeActivity();
    Boolean loginStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_vc);
        initLoginBtn();
        /** TODO: Entfernen, wenn FireBase implementiert **/
        setLoginStatus(true);
    }

    @Override
    public void onBackPressed() {

    }

    private void initLoginBtn() {
        Button loginButton = (Button) findViewById(R.id.login_button);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getLoginStatus()) {
                    changeToChooseGameModeActivity();
                } else {
                    showAlert("Info","Du musst dich zuerst einloggen");
                }
            }
        });
    }

    private void changeToChooseGameModeActivity() {
        startActivity(new Intent(LoginActivity.this, ChooseGameModeActivity.class));
    }

    public void setLoginStatus(Boolean status) {
        this.loginStatus = status;
    }

    private Boolean getLoginStatus() {
        return this.loginStatus;
    }

    private void showAlert(String title, String message) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
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
}
