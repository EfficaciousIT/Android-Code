package com.mobi.efficacious.demoproject;

import android.util.Log;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

public class MainActivityObserver implements LifecycleObserver {
    public String TAG=this.getClass().getSimpleName();

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    public void onCreateEvent(){
        Log.e(TAG,"Observer ON_CREATE");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void onStartEvent(){
        Log.e(TAG,"Observer ON_START");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void onPauseEvent(){
        Log.e(TAG,"Observer ON_PAUSE");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void onResumeEvent(){
        Log.e(TAG,"Observer ON_RESUME");
    }


    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void onDestroyEvent(){
        Log.e(TAG,"Observer ON_DESTROY");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void onStopEvent(){
        Log.e(TAG,"Observer ON_STOP");
    }

}
