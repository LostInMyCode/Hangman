package com.androidproject.hangman;

import android.content.SharedPreferences;
import android.content.Context;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.Random;
import java.util.Set;
import java.util.HashSet;
import java.util.List;

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
        arrayList.add("LSD");
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
