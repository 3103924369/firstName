package com.example.homework.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class SimpleService extends Service {

    public static final String TAG = "SimpleService";
    private SimpleBinder mBinder;

    //当通过startService方法开启一个服务的时候，会执行Service的onCreate和onStartCommand方法。
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate");
        mBinder = new SimpleBinder();
    }
    //当一个Service被创建以后，再次调用startService方法，Service是不会被重新创建的，而是会重新执行onStartCommand方法。
    // 无论我们点击多少次start按钮，始终只会执行onStartCommand方法
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }
    //当通过stopService方法停止一个service时调用
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }
    //无论调用多少次bindService方法onBind只会执行一次绑定
    @Override
    public IBinder onBind(Intent intent) {
        if (mBinder != null) {
            return mBinder;
        }
        return null;
    }
    public class SimpleBinder extends Binder{
        int num = 0 ;
        public void setData(int num1 , int num2) {
            num = num1 + num2 ;
        }
        public int getNum(){
            return num ;
        }
    }
}