package com.androidproject.hangman.activitys;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.androidproject.hangman.R;
import com.androidproject.hangman.dataHandling.UserAccountViewModel;
import com.androidproject.hangman.fragments.LoginFragment;

public class UserAccountActivity extends FragmentActivity {
    private UserAccountViewModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_account);
        android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
        try {
            fm.beginTransaction().replace(R.id.userAccountFrameLayout, LoginFragment.class.newInstance()).commit();
        }catch (java.lang.InstantiationException | java.lang.IllegalAccessException ex){

        }
        model = ViewModelProviders.of(this).get(UserAccountViewModel.class);


    }

    @Override
    public void onBackPressed(){

    }
}
