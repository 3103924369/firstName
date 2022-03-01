package com.example.homework;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private EditText et_username;
    private EditText et_password;
    private Button btn_login;
    private Button btn_register;
    private Button bt_pwd_eye;
    private CheckBox checkbox;
    boolean pwdFlag = false; //显示密码

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        et_username =(EditText) findViewById(R.id.et_username);
        et_password =(EditText) findViewById(R.id.et_password);
        checkbox = (CheckBox) findViewById(R.id.checkBox);
        btn_login =(Button) findViewById(R.id.button_login);
        btn_register =(Button) findViewById(R.id.button_register);
        bt_pwd_eye =(Button) findViewById(R.id.bt_pwd_eye);
        btn_login.setOnClickListener(new MyButton());
        btn_register.setOnClickListener(new MyButton());
        bt_pwd_eye.setOnClickListener(new MyButton());

        sharedPreferences= PreferenceManager.getDefaultSharedPreferences(this);
        boolean pang=sharedPreferences.getBoolean("remember_password",false);
        if(pang)
        {
            et_username.setText(sharedPreferences.getString("Name",""));
            et_password.setText(sharedPreferences.getString("Password",""));
            checkbox.setChecked(true);
        }





    }

    public  class MyButton implements View.OnClickListener{
        @Override
        public void onClick(View view){
            String username =et_username.getText().toString().trim();
            String password =et_password.getText().toString().trim();
            editor=sharedPreferences.edit();
            switch (view.getId()) {
                //当点击登录按钮时
                case R.id.button_login:
                    if(TextUtils.isEmpty(username) || TextUtils.isEmpty(password)){
                        Toast.makeText(MainActivity.this,"密码或账号不能为空",Toast.LENGTH_SHORT).show();
                    } else {
                        //验证账号密码--此次未用到
                        //if(InfoCheck.doLogin(username,password))
                        if(true)
                        {
                            if(checkbox.isChecked())
                            {
                                editor.putString("Name",username);
                                editor.putString("Password",password);
                                editor.putBoolean("remember_password",true);
                            }
                            else {
                                editor.clear();
                            }
                            editor.apply();


                            Toast.makeText(MainActivity.this,"登录成功",Toast.LENGTH_SHORT).show();


                            //登录成功进入界面
                            Intent intent = new Intent(MainActivity.this, ServiceActivity.class);
                            startActivity(intent);



                        }else{
                            AlertDialog textTips = new AlertDialog.Builder(MainActivity.this)
                                    .setTitle("提示")
                                    .setMessage("账号密码错误")
                                    .create();
                            textTips.show();
                        }
                    }
                    break;

                //当点击注册按钮事件时
                case R.id.button_register:
                    Intent intent = new Intent(MainActivity.this,RegisterActivity.class);
                    startActivity(intent);
                    break;

                //显示密码
                case R.id.bt_pwd_eye:
                    if(pwdFlag){
                        pwdFlag = false;
                        et_password.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD | InputType.TYPE_CLASS_TEXT );//设置密码不可见
                    }else{
                        pwdFlag = true;
                        et_password.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);//设置密码可见
                    }
                    break;
            }
        }
    }

    //Activity创建或者从后台重新回到前台时被调用
    @Override
    protected void onStart() {
        super.onStart();
        Log.e(TAG, "MainActivity -- onStart");
    }

    //Activity从后台获取其他页面重新回到前台时被调用 , 在start之前调用
    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e(TAG, "MainActivity -- onRestart");
    }
    //Activity创建或者从被覆盖、后台重新回到前台时被调用/自此，Activity进入了运行状态
    @Override
    protected void onResume() {
        super.onResume();
        Log.e(TAG, "MainActivity -- onResume");
    }
    //Activity被覆盖到下面或者锁屏时被调用
    @Override
    protected void onPause() {
        super.onPause();
        Log.e(TAG, "MainActivity -- onPause");
    }
    //退出当前Activity或者跳转到新Activity时被调用
    @Override
    protected void onStop() {
        super.onStop();
        Log.e(TAG, "MainActivity -- onPause");
    }

    //退出当前Activity时被调用,调用之后Activity就结束了
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "MainActivity -- onDestroy");
    }
}
