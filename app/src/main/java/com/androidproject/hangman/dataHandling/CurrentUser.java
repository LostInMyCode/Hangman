package com.androidproject.hangman.dataHandling;

import android.graphics.drawable.Drawable;

/**
 * Created by Someone on 17.03.2018.
 */

public class CurrentUser {
    private static CurrentUser instance;
    private Drawable profilePic;


    private CurrentUser(){

    }

    public static CurrentUser getInstance() {
        if (instance == null) {
            instance = new CurrentUser();
            return instance;
        } else {
            return instance;
        }
    }


    public Drawable getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(Drawable profilePic) {
        this.profilePic = profilePic;
    }
}
