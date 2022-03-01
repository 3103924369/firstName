package com.example.homework;


import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.example.homework.service.SimpleService;

public class ServiceActivity extends AppCompatActivity {
    public static final String TAG = "serviceActivity";
    private Button startService;
    private Button stopService;
    private Button bindService;
    private Button unbindService;

    private SimpleService.SimpleBinder mBinder;

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            mBinder = (SimpleService.SimpleBinder) iBinder;
            mBinder.setData(1 , 2);
            mBinder.getNum() ;
            Log.e(TAG , mBinder.getNum()+"");
        }
        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    } ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);

        startService =(Button) findViewById(R.id.startService);
        stopService =(Button) findViewById(R.id.stopService);
        bindService =(Button) findViewById(R.id.bindService);
        unbindService =(Button) findViewById(R.id.unbindService);


        startService.setOnClickListener(new MyButton());
        stopService.setOnClickListener(new MyButton());
        bindService.setOnClickListener(new MyButton());
        unbindService.setOnClickListener(new MyButton());

    }

    public  class MyButton implements View.OnClickListener{
        @Override
        public void onClick(View view){

            switch (view.getId()) {
                //当点击登录按钮时
                case R.id.startService:
                    Intent startIntent = new Intent(ServiceActivity.this, SimpleService.class);
                    startService(startIntent);
                    break;
                case R.id.stopService:
                    Intent stopIntent = new Intent(ServiceActivity.this, SimpleService.class);
                    stopService(stopIntent);
                    break;
                case R.id.bindService:
                    Intent bindIntent = new Intent(ServiceActivity.this, SimpleService.class);
                    bindService(bindIntent, mConnection, BIND_AUTO_CREATE);
                    break;
                case R.id.unbindService:
                    unbindService(mConnection);
                    break;

            }
        }
    }


}
