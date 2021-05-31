package com.example.mepositry;

import android.app.Application;

public class CourserApplication extends Application {
    private static CourserApplication mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;

    }

    public static CourserApplication getContext() {
        return mContext;
    }
}
