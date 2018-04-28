package com.dekinci.lksbstu;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.dekinci.lksbstu.communication.PolyExemple;
import com.dekinci.lksbstu.communication.structure.Login;

public class PolyApp extends Application {
    private static final String CREDENTIALS_KEY = "credentials_key";
    private static SharedPreferences prefs;


    @Override
    public void onCreate() {
        super.onCreate();
        PolyManager.get().setApi(new PolyExemple());
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
    }

    public static void persistCredentials(Login login) {
        prefs.edit()
                .putString(CREDENTIALS_KEY, login.toString()).apply();
    }

    public static Login restoreCredentials() {
        if (isLoggedIn())
            return Login.fromString(prefs.getString(CREDENTIALS_KEY, null));
        else
            return null;
    }

    public static boolean isLoggedIn() {
        return prefs.getString(CREDENTIALS_KEY, null) != null;
    }

    @SuppressLint("CommitPrefEdits")
    public static void deleteCredentials() {
        prefs.edit().remove(CREDENTIALS_KEY);
    }
}
