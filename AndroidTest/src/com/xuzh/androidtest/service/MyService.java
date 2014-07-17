package com.xuzh.androidtest.service;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.Process;
import android.os.RemoteException;
import android.renderscript.Program.TextureType;
import android.text.TextUtils;
import android.util.Log;

import com.xuzh.androidtest.R;

public class MyService extends Service {
    private final static String TAG = MyService.class.getSimpleName();

    private MyBinder mBinder = new MyBinder();

    /*** def my binder ***/
    public class MyBinder extends Binder {
        public void onStartDownLoad() {
            //TODO
            Log.d(TAG, "myBinder.startDownLoad");
            new Thread(new Runnable() {

                @Override
                public void run() {
                    // TODO Auto-generated method stub
                    //执行下载操作
                }
            });
        }

        public void onOtherMethod() {
            //TODO
            Log.d(TAG, "myBinder.otherMethod");
        }
    }

    /*** AIDL binder实现相关的接口 ***/
    MyAIDLService.Stub mBinderAIDL = new MyAIDLService.Stub() {

        @Override
        public String toUpperCase(String str) throws RemoteException {
            if (!TextUtils.isEmpty(str)) {
                Log.d(TAG, "AIDL toUpperCase:" + str.toUpperCase());
                return str.toUpperCase();
            }
            Log.d(TAG, "AIDL toUpperCase:" + str);
            return str;
        }

        @Override
        public int plus(int a, int b) throws RemoteException {
            Log.d(TAG, "AIDL plus: " + (a + b));
            return a + b;
        }
    };

    @Override
    public void onCreate() {
        // TODO Auto-generated method stub
        super.onCreate();
        onStartForeService();//启动前台服务
        Log.d(TAG, "onCreate");
        /***
         * 将 service改为android:process=":remote"属性的话服务线程就不是主线程
         * service和activity交互这时候用AIDL方式通信，改为 MyAIDLService.Stub mBinderAIDL,而非自定义的 MyBinder extends Binder
         * 否则activity和service非在一个线程中会产生问题
         ***/
        Log.d(TAG, "process id == " + Process.myPid());//线程id
    }

    /*** 开启前台服务程序 ****/
    private void onStartForeService() {
        Notification mNotification = new Notification();
        mNotification.icon = R.drawable.ic_launcher;
        mNotification.tickerText = "有通知来";
        mNotification.when = System.currentTimeMillis();
        mNotification.flags = Notification.FLAG_NO_CLEAR;//不消失点击

        Intent it = new Intent(this, ServiceActivity.class);//跳转到 ServiceActivity
        PendingIntent pit = PendingIntent.getActivity(this, 0, it, 0);
        mNotification.setLatestEventInfo(this, "通知标题", "通知内容", pit);
        startForeground(1, mNotification);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // TODO Auto-generated method stub
        Log.d(TAG, "onStartCommand");
        new Thread(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                //执行子线程的一些耗时操作
            }
        });
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
        //return mBinder;
        return mBinderAIDL;
    }

}
