package com.example.heorhii_dubinin.news;

import android.app.Application;

import com.facebook.stetho.Stetho;

class ApplicationConfiguration {
    static void adjust(Application application) {
        Stetho.initializeWithDefaults(application);
    }
}
