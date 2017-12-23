package com.kiki.kikiwynews;

import android.app.Activity;

/**
 * Created by Administrator on 2017/12/22.
 */

public class LoginUtils {
    public static boolean isLogin=false;

    public interface Ilogin{
        void onlogin();
    }

    static Ilogin ilogin;
    static Activity activity;
    public static void setIlogin(Ilogin ilogin,Activity activity){
        LoginUtils.ilogin=ilogin;
        LoginUtils.activity=activity;
        isLogin();
    }

    private static void isLogin() {
        if(isLogin){
            if(ilogin!=null){
                LoginUtils.ilogin.onlogin();
            }
        }else{
            //去登录界面
        }
    }

    public static void clear(){
        if(ilogin!=null){
            ilogin=null;
        }
        if(activity!=null){
            activity=null;
        }
    }

}
