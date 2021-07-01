package com.alibaba.android.arouter.demo;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;

public class App extends Application {

    private static App INSTANCE;

    public static Application getInstance() {
        return INSTANCE;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        INSTANCE = this;
        // ARouter.openDebug();
        ARouter.openLog();
        ARouter.printStackTrace();
        ARouter.init(this);
    }
}
