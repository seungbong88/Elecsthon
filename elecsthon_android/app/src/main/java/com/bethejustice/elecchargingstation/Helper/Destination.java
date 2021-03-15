package com.bethejustice.elecchargingstation.Helper;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

public class Destination {
    private static final String TAG = "Destination";

    private static Destination instance = null;
    private static Context context;

    private static SharedPreferences mSharedPreferences;
    private static SharedPreferences.Editor editor;

    private Destination(Context context){
        this.context = context;
    }

    public static Destination getInstance(Context context){
        return instance;
    }
}
