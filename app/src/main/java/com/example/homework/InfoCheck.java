package com.example.homework;

import android.content.Context;

import com.example.homework.enitiy.account;

import java.util.HashMap;
import java.util.Map;

public class InfoCheck {
    public static void SaveInformation(Context context, String username, String password, String mail) {
        account acc = new account(username,password,mail);
        System.out.println("注册信息： "+"username"+username+"   password"+password+"   mail"+mail);



    }

    //验证输入的账号密码
    public static boolean doLogin(String username,String password) {

        Map<String, String> map = new HashMap<String, String>();
        map.put("1","123");
        map.put("2","456");

        if(map.get(username).equals(password))
        {
            return true;
        }else{
            return false;
        }


    }

}
