package com.androidproject.hangman;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import static android.text.TextUtils.isEmpty;

public class CreateAccountActivity extends AppCompatActivity {
    private FirebaseAuth auth;
    private EditText inputEmail, inputPassword, inputRepeatEmail;
    private Button btnSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        auth = FirebaseAuth.getInstance();

        inputEmail = (EditText) findViewById(R.id.etEmail);
        inputPassword = (EditText) findViewById(R.id.etPassword);
        inputRepeatEmail = (EditText) findViewById(R.id.etRepeatPassword);
        btnSignUp = (Button) findViewById(R.id.btnCreateAccount);
    }

    public void createAccount(View v) {

        String email = inputEmail.getText().toString().trim();
        String password = inputPassword.getText().toString().trim();
        String repeatPassword = inputRepeatEmail.getText().toString().trim();

        if (isEmpty(email)) {
            Toast.makeText(getApplicationContext(), "Bitte gibt eine E-Mailadresse ein!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (isEmpty(password) && isEmpty(repeatPassword)) {
            Toast.makeText(getApplicationContext(), "Gib ein Passwort ein", Toast.LENGTH_SHORT).show();
            return;
        }

        if (password.length() < 6) {
            Toast.makeText(getApplicationContext(), "Das Passwort muss mindestens 6 Zeichen lang sein", Toast.LENGTH_SHORT).show();
            return;
        }

        if (isEmpty(repeatPassword)) {
            Toast.makeText(getApplicationContext(), "Bitte wiederhole das Passwort", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!password.equals(repeatPassword)) {
            Toast.makeText(getApplicationContext(), "Die Passwörter stimmen nicht überein" + password + repeatPassword, Toast.LENGTH_SHORT).show();
            return;
        }

        //create user
        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(CreateAccountActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Toast.makeText(CreateAccountActivity.this, "Benutzer erfolgreich erstellt", Toast.LENGTH_SHORT).show();
                        if (!task.isSuccessful()) {
                            Toast.makeText(CreateAccountActivity.this, "Benutzer konnte nicht erstellt werden" + task.getException(),
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            startActivity(new Intent(CreateAccountActivity.this, LoginActivity.class));
                            finish();
                        }
                    }
                });

    }
}