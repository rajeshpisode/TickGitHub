package com.piyush.tick.utils;

/**
 * Created by hp on 7/20/2015.
 */

import android.content.Context;
        import android.content.SharedPreferences;
        import android.content.SharedPreferences.Editor;
        import android.preference.PreferenceManager;
        import android.text.TextUtils;

        import com.google.gson.Gson;

public class TicTackToeSharedPrefrence {

    private SharedPreferences sharedPref;
    private Editor editor;

    public static final String KEY_USERNAME_LOGIN = "username";
    public static final String KEY_PASSWORD_LOGIN = "password";

    public static final String KEY_IS_REMEMBER_LOGIN_BOOEAN = "is_remember";

    public static final boolean DEFAULT_IS_REMEMBER_LOGIN_BOOEAN = false;

    public static final String KEY_DEVICE_WIDTH = "key_device_width";
    public static final String KEY_DEVICE_DENSITY = "key_device_density";
    public static final String DEVICE_RATE = "10 Rs/sec";
    public static final String CREDIT_TRANSFER_DONOT = "" + false;
    public static final String REGISTERED_DONOR = "unregistered";
    private Context mContext;
    private static TicTackToeSharedPrefrence mMSCSharedPrefUtils;

    private TicTackToeSharedPrefrence(Context context) {
        mContext = context;
        sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        editor = sharedPref.edit();
    }

    private TicTackToeSharedPrefrence() {

    }

    public static TicTackToeSharedPrefrence getInstance(Context context) {
        if (mMSCSharedPrefUtils == null) {
            mMSCSharedPrefUtils = new TicTackToeSharedPrefrence(context);
        }
        return mMSCSharedPrefUtils;

    }

    // for fetching data from shared pref.
    public String fetchStringPrefernce(String key, String defaultValue) {

        String value = sharedPref.getString(key, "");
//		return TextUtils.isEmpty(value) ? defaultValue : JioCypher.getInstace(
//				mContext).decrypt(
//				value,
//				((MSCApplicationBootstrap) mContext.getApplicationContext())
//						.getEncryptionKey());
        return TextUtils.isEmpty(value) ? defaultValue : value;
    }

    // for save data in to shared perf.
    public void saveStringPrefernce(String key, String value) {
//		value = JioCypher.getInstace(mContext).encrypt(
//				value,
//				((MSCApplicationBootstrap) mContext.getApplicationContext())
//						.getEncryptionKey());

        editor.putString(key, value);
        editor.commit();

    }

    // for fetching data from shared pref.
    public boolean fetchBooleanPrefernce(String key, Boolean defaultValue) {

        return sharedPref.getBoolean(key, defaultValue);
    }

    // for save data in to shared perf.
    public void saveBooleanPrefernce(String key, Boolean value) {

        editor.putBoolean(key, value);
        editor.commit();

    }

    // for save long data in to shared perf.
    public void saveLongPrefernce(String key, long value) {
        editor.putLong(key, value);
        editor.commit();

    }

    public long fetchLongPrefernce(String key, long defaultValue) {
        return sharedPref.getLong(key, defaultValue);
    }

    // for fetching data from shared pref.
    public int fetchIntegerPrefernce(String key, int defaultValue) {
        return sharedPref.getInt(key, defaultValue);
    }

    // for save data in to shared perf.
    public void saveIntegerPrefernce(String key, int value) {
        editor.putInt(key, value);
        editor.commit();
    }

    public void removeFromSharedPreference(String key) {
        editor.remove(key);
        editor.commit();
    }

    public void saveObjectPreference(String key, Object obj) {
        editor.putString(key, new Gson().toJson(obj));
        editor.commit();
    }

    public Object fetchObjectPreference(String key, Class<?> class1) {
        return new Gson().fromJson(sharedPref.getString(key, ""), class1);
    }

    public void clearAllData() {
        editor = sharedPref.edit().clear();
        editor.commit();
    }
}