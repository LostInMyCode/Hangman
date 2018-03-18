package com.androidproject.hangman.handler;

import android.content.Context;
import android.graphics.drawable.Drawable;

import com.androidproject.hangman.dataHandling.CurrentUser;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;

import java.io.File;

/**
 * Created by Someone on 18.03.2018.
 */

public class ProfilePicOnSuccessListener implements OnSuccessListener<FileDownloadTask.TaskSnapshot> {
    private File localFile;
    private Context activityContext;

    public ProfilePicOnSuccessListener(File localFile, Context activityContext){
        this.activityContext = activityContext;
        this.localFile = localFile;
    }

    @Override
    public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
        CurrentUser currentUser = CurrentUser.getInstance();
        currentUser.setProfilePic(Drawable.createFromPath(localFile.getAbsolutePath()));
        NavigationHandler.changeToDrawerTest(activityContext);
    }
}
