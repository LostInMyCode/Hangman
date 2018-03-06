package com.androidproject.hangman;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

public class ProfilePicActivity extends AppCompatActivity {

    ImageView profilPicIv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_pic);
        profilPicIv = findViewById(R.id.choosProfilePicIv);
    }

    public void imageSelected(View v){
        Drawable buttonImage = v.getBackground();
        profilPicIv.setImageDrawable(buttonImage);
    }
}
