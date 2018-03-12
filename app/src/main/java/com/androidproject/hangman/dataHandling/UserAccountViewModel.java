package com.androidproject.hangman.dataHandling;

import android.arch.lifecycle.ViewModel;
import android.graphics.drawable.Drawable;

/**
 * Created by Someone on 10.03.2018.
 */

public class UserAccountViewModel extends ViewModel {
    private String username;
    private String emailadress;
    private String password;
    private String repeatPassword;
    private Drawable profilePic;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmailadress() {
        return emailadress;
    }

    public void setEmailadress(String emailadress) {
        this.emailadress = emailadress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }

    public Drawable getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(Drawable profilePic) {
        this.profilePic = profilePic;
    }
}
