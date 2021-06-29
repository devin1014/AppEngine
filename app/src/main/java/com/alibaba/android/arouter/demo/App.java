package com.alibaba.android.arouter.demo;

import android.app.Application;

public class App extends Application {

    private static App INSTANCE;

    public static Application getInstance() {
        return INSTANCE;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        INSTANCE = this;
    }
}
