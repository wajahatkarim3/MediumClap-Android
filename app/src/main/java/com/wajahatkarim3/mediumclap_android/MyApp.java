package com.wajahatkarim3.mediumclap_android;

import android.app.Application;

import com.facebook.stetho.Stetho;

public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
    }
}
