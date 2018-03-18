package com.androidproject.hangman.handler;

import android.graphics.drawable.Drawable;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;

import java.io.File;

/**
 * Created by Someone on 17.03.2018.
 */

public class DrawableOnSuccessListener implements OnSuccessListener<FileDownloadTask.TaskSnapshot> {

    private File localFile;
    private String imageUrl;
    private GetFromFireStorage fromFireStorageInstance = GetFromFireStorage.getInstance();

    public DrawableOnSuccessListener(File localFile, String imageUrl) {
        this.imageUrl = imageUrl;
        this.localFile = localFile;
    }

    @Override
    public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
        fromFireStorageInstance.newEntryProfilePicHashMap(imageUrl, Drawable.createFromPath(localFile.getAbsolutePath()));
    }
}
