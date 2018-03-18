package com.androidproject.hangman.handler;

import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Someone on 14.03.2018.
 */

public class GetFromFireStorage {
    private static GetFromFireStorage instance;
    private FirebaseStorage storageInstance = FirebaseStorage.getInstance();
    private FirebaseDatabase databaseInstance = FirebaseDatabase.getInstance();
    private HashMap<String, String> profilePicStringHashMap = new HashMap<>();
    private HashMap<Drawable, String> profilePicDrawableHashMap = new HashMap<>();
    private File localFile;
    private Drawable t;


    private GetFromFireStorage() {

    }

    public static GetFromFireStorage getInstance() {
        if (instance == null) {
            instance = new GetFromFireStorage();
            return instance;
        } else {
            return instance;
        }
    }

    public void getProfilePics() {
        DatabaseReference picRef = databaseInstance.getReference("profilePictures");

        picRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    profilePicStringHashMap.put(ds.getKey().toString(), ds.getValue().toString());
                }
                generateDrawable();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


    private void generateDrawable() {
        StorageReference storageRef = storageInstance.getReference();

        for (final Map.Entry<String, String> entry : profilePicStringHashMap.entrySet()) {
            StorageReference image = storageInstance.getReferenceFromUrl(entry.getValue());

            try {
                localFile = File.createTempFile(entry.getKey(), ".png");
            } catch (IOException e) {
                e.printStackTrace();
            }

            image.getFile(localFile).addOnSuccessListener(new DrawableOnSuccessListener(localFile, entry.getValue())).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception exception) {
                }
            });
        }



    }

    public HashMap<Drawable,String> getProfilePicDrawableHashMap() {
        return profilePicDrawableHashMap;
    }

    public void newEntryProfilePicHashMap(String imageUrl, Drawable imageDrawable) {
        profilePicDrawableHashMap.put(imageDrawable,imageUrl);
    }
}


