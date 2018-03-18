package com.androidproject.hangman.dataHandling;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * Created by aybarscavus on 09.11.17.
 */

public class AppData {

    private Context context;

    public AppData(Context context){
        this.context = context;
    }

    public void storeData() {
        String MyPREFERENCES = "MyPrefs" ;
        SharedPreferences sharedPref = context.getSharedPreferences(MyPREFERENCES,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        ArrayList arrayList = new ArrayList();
        arrayList.add("Zyklop");
        arrayList.add("Faupax");
        arrayList.add("Gymnastik");
        arrayList.add("Rhythmus");
        arrayList.add("Rhesusfaktor");
        arrayList.add("Scarabäus");
        arrayList.add("Physiognomie");
        arrayList.add("Hydroxylgruppe");
        arrayList.add("Quantitativ");
        arrayList.add("Developer");
        arrayList.add("AEP");
        arrayList.add("Intel");
        arrayList.add("AMD");
        arrayList.add("NVIDIA");
        arrayList.add("Python");
        arrayList.add("Swift");
        arrayList.add("Java");
        arrayList.add("Firebase");
        arrayList.add("Haus");
        arrayList.add("Boot");
        arrayList.add("Traktor");
        arrayList.add("JackDaniels");
        arrayList.add("Wasweißich");
        arrayList.add("Baum");
        arrayList.add("Schnee");
        arrayList.add("Winter");
        arrayList.add("Sommer");
        arrayList.add("Strand");
        arrayList.add("Italien");
        arrayList.add("Flugzeug");
        arrayList.add("SolidStateDrive");
        arrayList.add("RandomAccessMemory");
        arrayList.add("Kino");
        arrayList.add("Freizeitpark");
        arrayList.add("Game");
        arrayList.add("Android");
        Set<String> set = new HashSet<String>();
        set.addAll(arrayList);
        editor.putStringSet("words", set);
        editor.apply();
    }

    public String getStoredRandomWord() {
        String MyPREFERENCES = "MyPrefs" ;
        SharedPreferences sharedPref = context.getSharedPreferences(MyPREFERENCES,Context.MODE_PRIVATE);
        Set<String> set = sharedPref.getStringSet("words", null);
        List<String> stringsList = new ArrayList<>(set);
        String randomWord = stringsList.get(new Random().nextInt(stringsList.size()));
        return randomWord;
    }

}
