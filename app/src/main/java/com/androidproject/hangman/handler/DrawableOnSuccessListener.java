package com.androidproject.hangman.handler;

import android.graphics.drawable.Drawable;

import com.androidproject.hangman.activitys.MainActivity;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;

import java.io.File;

/**
 * Created by Someone on 17.03.2018.
 */

public class DrawableOnSuccessListener implements OnSuccessListener<FileDownloadTask.TaskSnapshot> {

    private File localFile;
    private String imageUrl;
    private MainActivity main;
    private GetFromFireStorage fromFireStorageInstance = GetFromFireStorage.getInstance();
    private static int count;

    public DrawableOnSuccessListener(File localFile, String imageUrl, MainActivity main) {
        this.imageUrl = imageUrl;
        this.localFile = localFile;
        this.main = main;
    }

    @Override
    public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
        fromFireStorageInstance.newEntryProfilePicHashMap(imageUrl, Drawable.createFromPath(localFile.getAbsolutePath()));
        if (count == 10){
            //main.setTimer();
        }
        count++;
    }
}
