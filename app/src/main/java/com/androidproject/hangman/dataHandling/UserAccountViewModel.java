package com.androidproject.hangman.dataHandling;

import android.arch.lifecycle.ViewModel;
import android.graphics.drawable.Drawable;

import com.androidproject.hangman.handler.GetFromFireStorage;

import java.util.Map;

/**
 * Created by Someone on 10.03.2018.
 */

public class UserAccountViewModel extends ViewModel {
    private String username;
    private String emailadress;
    private String password;
    private String repeatPassword;
    private String profilePicUrl;
    private Drawable profilePic;

    public UserAccountViewModel(){
        for (Map.Entry<Drawable,String> entry : GetFromFireStorage.getInstance().getProfilePicDrawableHashMap().entrySet()){
            profilePic = entry.getKey();
            profilePicUrl = entry.getValue();
        }
    }


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

    public String getProfilePicUrl() {
        return profilePicUrl;
    }

    public void setProfilePicUrl(String profilePicUrl) {
        this.profilePicUrl = profilePicUrl;
    }
}
