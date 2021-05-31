package com.example.mepositry;


import android.app.Application;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;
import android.arch.lifecycle.ProcessLifecycleOwner;
import android.util.Log;

public class CourserApplication extends Application implements LifecycleObserver {

    private static CourserApplication mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;

        ProcessLifecycleOwner.get().getLifecycle().addObserver(this);
    }

    public static CourserApplication getContext() {
        return mContext;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    void onForeground() {
        Log.e("LifecycleObserver", "应用回到前台");
        isAppInBackground(true);

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    void onBackground() {
        Log.e("LifecycleObserver", "应用退到後台");
        isAppInBackground(false);
    }


    public interface ValueChangeListener {
        void onChanged(Boolean value);
    }

    private ValueChangeListener visibilityChangeListener;

    public void setOnVisibilityChangeListener(ValueChangeListener listener) {
        this.visibilityChangeListener = listener;
    }

    private void isAppInBackground(Boolean isBackground) {
        if (null != visibilityChangeListener) {
            visibilityChangeListener.onChanged(isBackground);
        }
    }

}
