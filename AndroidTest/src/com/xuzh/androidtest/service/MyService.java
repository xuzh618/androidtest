package com.xuzh.androidtest.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {
    private final static String TAG = MyService.class.getSimpleName();

    private MyBinder mBinder = new MyBinder();

    /*** def my binder ***/
    public class MyBinder extends Binder {
        public void onStartDownLoad() {
            //TODO
            Log.d(TAG, "myBinder.startDownLoad");
        }

        public void onOtherMethod() {
            //TODO
            Log.d(TAG, "myBinder.otherMethod");
        }
    }

    @Override
    public void onCreate() {
        // TODO Auto-generated method stub
        super.onCreate();
        Log.d(TAG, "onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // TODO Auto-generated method stub
        Log.d(TAG, "onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy");
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind intent");
        return mBinder;
    }

}
