package com.androidproject.hangman;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    private FirebaseAuth auth;
    private EditText inputEmail, inputPassword;
    private Button btnSignUp, btnLogIn;

    ChooseGameModeActivity gameMode = new ChooseGameModeActivity();
    Boolean loginStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_vc);
        initLoginBtn();
        setLoginStatus(true);
        auth = FirebaseAuth.getInstance();

        inputEmail = (EditText) findViewById(R.id.etUsername);
        inputPassword = (EditText) findViewById(R.id.etPassword);
        btnSignUp = (Button) findViewById(R.id.btnCreateAccount);
        btnLogIn = (Button) findViewById(R.id.btnLogin);
    }

    public void test(View v) {
        Intent intent = new Intent(LoginActivity.this, CreateAccountActivity.class);
        startActivity(intent);
    }

    public void login(View v) {
        String email = inputEmail.getText().toString();
        final String password = inputPassword.getText().toString();

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
            return;
        }

        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()) {
                            if (password.length() < 6) {
                                Toast.makeText(getApplicationContext(), "Das Passwort muss mindestens 6 Zeichen lang sein", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getApplicationContext(), "Authentifizierung fehlgeschlagen", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(getApplicationContext(), "Authentifizierung erfolgreich "+auth.getCurrentUser().getEmail(), Toast.LENGTH_LONG).show();

                            finish();
                        }
                    }
                });

    }

    @Override
    public void onBackPressed() {

    }

    private void initLoginBtn() {
        Button loginButton = (Button) findViewById(R.id.btnLogin);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getLoginStatus()) {
                    NavigationHandler.changeToChooseGameModeActivity(getApplicationContext());
                } else {
                    AlertHandler.showStandardAlert(getApplicationContext(),"Info", "Du musst dich zuerst einloggen");
                    showAlert("Info","Du musst dich zuerst einloggen");
                }
            }
        });
    }

    public void generateUser(String username, String password)
    {

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
