package com.imrainbow.popularmovies.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Bin Li on 2015/8/10.
 */
public class SharedPreferenceHelper {

    private static final String APP_NAME = "com.imrainbow.popularmovies";
    public static SharedPreferenceHelper instance;
    protected Context mContext;
    protected SharedPreferences sharedPreferences;

    public SharedPreferenceHelper(Context context){
        mContext = context;
        sharedPreferences = mContext.getSharedPreferences(APP_NAME, 0);
    }

    public static synchronized SharedPreferenceHelper getInstance(Context context){
        if (null == instance)
            instance = new SharedPreferenceHelper(context.getApplicationContext());
        return instance;
    }

    public void setValue(String key, String value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public String getValue(String key) {
        return sharedPreferences.getString(key, "");
    }

    public void remove(String key) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(key);
        editor.apply();
    }

    public void clear() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }
}
