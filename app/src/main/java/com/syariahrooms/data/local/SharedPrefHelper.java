package com.syariahrooms.data.local;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefHelper {

    public static final String ACCES_TOKEN = "token";
    public static final String USER_NAME = "name";
    public static final String USER_EMAIL = "email";
    public static final String USER_ID = "userid";
    public static final String USER_ADDR = "addr";
    public static final String USER_GENDER = "gender";
    private final String MLIB_PREF = "mlibuin";
    private SharedPreferences mPreferences;

    public SharedPrefHelper(Context context) {
        mPreferences = context.getSharedPreferences(MLIB_PREF, Context.MODE_PRIVATE);
    }

    public String getString(String key) {
        return mPreferences.getString(key, null);
    }

    public int getInt(String key) {
        return mPreferences.getInt(key, 0);
    }

    public void putString(String key, String value) {
        mPreferences.edit().putString(key, value).apply();
    }

    public void putInt(String key, int value) {
        mPreferences.edit().putInt(key, value).apply();
    }

    public void clear() {
        mPreferences.edit().clear().apply();
    }

}
