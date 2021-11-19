package com.shunli.bookingmeeting;

import android.app.Application;

public class App extends Application {


    static Application application = null;

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
    }

    public static Application getApplication() {
        return application;
    }


}
