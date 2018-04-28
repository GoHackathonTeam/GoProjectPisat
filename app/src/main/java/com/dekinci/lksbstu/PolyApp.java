package com.dekinci.lksbstu;

import android.app.Application;

public class PolyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        PolyManager.get().setApi(null); //TODO
    }
}
