package com.arhamtechnolabs.karohamapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

public class SharedPreferenceHelper {

    Context context;

    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String DESC = "descKey";

    SharedPreferences sharedpreferences;
    SharedPreferences.Editor editor;

    public SharedPreferenceHelper(Context context) {
        this.context = context;
        sharedpreferences = context.getSharedPreferences(MyPREFERENCES, 0);
    }

    public String getDesc() {
        return sharedpreferences.getString(DESC, "");
    }

    public void setDesc(String x) {
        editor = sharedpreferences.edit();
        editor.putString(DESC, x);
        editor.commit();
        Log.i("SharedPreference", "Mobile Entered in pref");

    }

}
