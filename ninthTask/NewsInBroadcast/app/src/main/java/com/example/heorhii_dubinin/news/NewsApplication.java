package com.example.heorhii_dubinin.news;

import android.app.Application;

public class NewsApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ApplicationConfiguration.adjust(this);
    }
}
