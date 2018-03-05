package com.androidproject.hangman.fragments;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.androidproject.hangman.NavigationHandler;
import com.androidproject.hangman.R;
import com.google.firebase.auth.FirebaseAuth;

public class FragmentActivity extends AppCompatActivity {
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private TextView tvUsername, tvUserEmail;
    private  FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
        auth = FirebaseAuth.getInstance();

        mDrawerLayout = findViewById(R.id.loginDrawLayout);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);
        mDrawerLayout.addDrawerListener(mDrawerToggle);
        NavigationView nvDrawer = findViewById(R.id.navview);
        mDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setDrawerContent(nvDrawer);

        tvUsername = (TextView) nvDrawer.getHeaderView(0).findViewById(R.id.tvUsername);
        tvUserEmail = (TextView) nvDrawer.getHeaderView(0).findViewById(R.id.tvUserEmail);
        setHeader();
        FragmentManager fm = getFragmentManager();
        try {
            fm.beginTransaction().replace(R.id.testLinLayout, ChooseGameModeFragment.class.newInstance()).commit();
        }catch (java.lang.InstantiationException | java.lang.IllegalAccessException ex){

        }

    }

    public void setHeader(){
        tvUserEmail.setText(auth.getCurrentUser().getEmail());
        tvUsername.setText(auth.getCurrentUser().getDisplayName());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem){
        if(mDrawerToggle.onOptionsItemSelected(menuItem)){
            return true;
        }
        return super.onOptionsItemSelected(menuItem);
    }

    public void selectDrawerMenu(MenuItem menuItem){
        Fragment fragment = null;
        Class fragmentClass;
        switch (menuItem.getItemId()){
            case R.id.chooseGamemod:
                fragmentClass = ChooseGameModeFragment.class;
                break;
            case R.id.settings:
                fragmentClass = SettingsFragment.class;
                break;
            case R.id.logout:
                auth.signOut();
                NavigationHandler.changeToLoginActivity(this);
            default:
                fragmentClass = ChooseGameModeFragment.class;
        }
        try{
            fragment = (Fragment) fragmentClass.newInstance();
        }catch (java.lang.InstantiationException | java.lang.IllegalAccessException ex){
            ex.printStackTrace();
        }
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.testLinLayout, fragment).commit();
        menuItem.setChecked(true);
        mDrawerLayout.closeDrawers();
    }

    private void setDrawerContent(NavigationView navigationView){
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                selectDrawerMenu(item);
                return true;
            }
        });
    }
}
