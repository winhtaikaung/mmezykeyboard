package com.example.android.lolikeyboard;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by jr on 2/26/15.
 */
public class MySharedPreferences {

    private static MySharedPreferences pref;
    protected SharedPreferences mSharedPreferences;
    protected SharedPreferences.Editor mEditor;
    protected Context mContext;

    private static final String VIBRATE = "vibrato";
    private static final boolean DEFAULT_VIBRATE = false;

    public MySharedPreferences(Context context) {
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        mEditor = mSharedPreferences.edit();

        this.mContext = context;
    }

    // Get an instance of a MySharedPreference
    public static synchronized MySharedPreferences getInstance(Context mContext) {
        if (pref == null) {
            pref = new MySharedPreferences(mContext);
        }
        return pref;
    }

    /**
     * Set VIbration passed via argument
     *
     * @param isvibrate location argument
     */
    public void setVibrate(boolean isvibrate) {
        mEditor.putBoolean(VIBRATE, isvibrate).apply();
    }
    /**
     *
     * get Vibrate True or False
     * **/
    public boolean getVibrate(){
        return mSharedPreferences.getBoolean(VIBRATE, DEFAULT_VIBRATE);
    }

}
