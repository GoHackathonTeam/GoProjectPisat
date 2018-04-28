package com.dekinci.lksbstu;

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

    }

    public static Login restoreCredentials() {
        return null;
    }

    public static boolean isLoggedIn() {
        return false;
    }
}
