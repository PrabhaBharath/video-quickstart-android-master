package com.twilio.video.quickstart.util;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.preference.PreferenceManager;
import android.util.Log;

import com.twilio.video.quickstart.activity.VideoActivity;

import java.io.File;
import java.io.IOException;

public class SessionManager {
    // Shared Preferences
    SharedPreferences pref;
    // Editor for Shared preferences
    SharedPreferences.Editor editor;
    // Context
    Context _context;
    // Shared pref mode
    int PRIVATE_MODE = 0;
    // Sharedpref file name
    private static final String PREF_NAME = "IcyChat";
    //vmartApp
    // All Shared Preferences Keys
    private static final String IS_LOGIN = "IsLoggedIn";
    public static final String KEY_USERID = "mobile";
    public static final String IS_ONLINE = "false";

    // Constructor
    public SessionManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    /**
     * Create login session
     */
    public void createLoginSession(String userid) {
        // Storing login value as TRUE
        editor.putBoolean(IS_LOGIN, true);
        ///storing the mobile value
        editor.putString(KEY_USERID, userid);
        // commit changes
        editor.commit();
    }

    public void logoutUser() {
        editor.putBoolean(IS_LOGIN, false);
        editor.clear();
        editor.commit();
        File sharedPreferenceFile = new File("/data/data/"+ _context.getPackageName()+ "/shared_prefs/");
        File[] listFiles = sharedPreferenceFile.listFiles();
        for (File file : listFiles) {
            file.delete();
        }
        //PreferenceManager.getDefaultSharedPreferences(_context).edit().clear().apply();
        Intent i = new Intent(_context, VideoActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        _context.startActivity(i);
    }

    /**
     * Quick checkky for login
     **/
    // Get Login State
    public boolean isLoggedIn() {
        String userid = pref.getString(SessionManager.KEY_USERID, "");
        Log.e("sessionMobile", " " + userid);
        return pref.getBoolean(IS_LOGIN, false);
    }
}