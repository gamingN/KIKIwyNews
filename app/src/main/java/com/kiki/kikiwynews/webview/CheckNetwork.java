package com.kiki.kikiwynews.webview;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * 用于判断是否联网状态
 * Created by KIKI on 2017/12/31.
 * 346409606@qq.com
 */

public class CheckNetwork {

    /**
     * 判断网络是否连通
     */
    public static boolean isNetworkConnected(Context context){
        if(context!=null){
            ConnectivityManager cm= (ConnectivityManager) context.getSystemService(context.CONNECTIVITY_SERVICE);
            NetworkInfo info = cm.getActiveNetworkInfo();
            return info!=null && info.isConnected();
        }else{
            /**
             * 如果context为空，就返回false，表示网络未连接
             */
            return false;
        }
    }

    /**
     * 判断是否是wifi
     * @param context
     * @return
     */
    public static boolean isWifiConnected(Context context){
        if(context!=null){
            ConnectivityManager cm= (ConnectivityManager) context.getSystemService(context.CONNECTIVITY_SERVICE);
            NetworkInfo info=cm.getActiveNetworkInfo();
            return  info!=null && (info.getType()==ConnectivityManager.TYPE_WIFI);
        }else {
            /**
             * 如果context为null就表示为未连接
             */
            return false;
        }
    }
}
