package com.example.heorhii_dubinin.news;

import android.app.Application;

import com.facebook.stetho.Stetho;

public class ApplicationConfiguration {
    public static void adjust(Application application) {
        Stetho.initializeWithDefaults(application);
    }
}
