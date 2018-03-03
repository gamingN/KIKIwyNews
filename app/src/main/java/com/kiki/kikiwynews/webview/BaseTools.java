package com.kiki.kikiwynews.webview;

import android.app.ActivityManager;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import com.blankj.utilcode.utils.Utils;

import java.lang.reflect.Field;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

/**
 * 工具类
 * Created by KIKI on 2017/12/29.
 * 346409606@qq.com
 */

public class BaseTools {
    /**
     * 获取文件夹名称
     */
    public static String getDir(String path) {
        String subString = path.substring(0, path.lastIndexOf('/'));
        return subString.substring(subString.lastIndexOf('/') + 1, subString.length());
    }

    /**
     * 获取屏幕宽度
     */
    public static int getWindowWidth(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        int mScreenWidth = dm.widthPixels;
        return mScreenWidth;
    }

    /**
     * 获取屏幕高度
     */
    public static int getWindowHeigh(Context context) {
        WindowManager wm = (WindowManager) (context
                .getSystemService(Context.WINDOW_SERVICE));
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        int mScreenHeigh = dm.heightPixels;
        return mScreenHeigh;
    }

    /**
     * 获得状态栏，通知栏高度
     */
    public static int getStatusBarHeight(Context context) {
        Class<?> c = null;
        Object obj = null;
        Field field = null;

        int x = 0;
        int statusBarHeight = 0;

        try {
            c = Class.forName("com.android.internal.R$dimen");
            obj = c.newInstance();
            field = c.getField("status_bar_height");
            x = Integer.parseInt(field.get(obj).toString());
            statusBarHeight = context.getResources().getDimensionPixelSize(x);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return statusBarHeight;
    }

    /**
     * 以默认方式显示货币
     * 默认保留两位小数，四舍五入
     */
    public static String formatCurrency(double d) {
        String s = "";
        DecimalFormat nf = (DecimalFormat) NumberFormat.getCurrencyInstance(Locale.CHINA);
        s = nf.format(d);
        return s;
    }

    /**
     * 去掉无效的小数点".00"
     */
    public static String formatMoney(double d) {
        String tmp = formatCurrency(d);
        if (tmp.endsWith(".00")) {
            return tmp.substring(0, tmp.length() - 3);
        } else {
            return tmp;
        }
    }

    /**
     * 处于栈顶的Acitivity名
     */
    public String getTopAcitivityName(Context context) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List var2 = am.getRunningTasks(1);  //返回任务列表
        ActivityManager.RunningTaskInfo runningTaskInfo = (ActivityManager.RunningTaskInfo) var2.get(0);
        return runningTaskInfo.topActivity.getClassName();
    }

    /**
     * 获取当前应用版本号
     */
    public static String getVersionName(){
        PackageManager packageManager= Utils.getContext().getPackageManager();
        PackageInfo packageInfo=null;
        try {
            packageInfo=packageManager.getPackageInfo(Utils.getContext().getPackageName(),0);
            return packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "1.0";
        }
    }

    /**
     * 实现文本复制功能
     */
    public static void copy(String content){
        ClipboardManager cmb= (ClipboardManager) Utils.getContext().getSystemService(Context.CLIPBOARD_SERVICE);
        cmb.setText(content.trim());
    }

    /**
     * 使用浏览器打开连接
     */
    public static void openLink(Context context,String content){
        Uri issuesUrl=Uri.parse(content);
        Intent intent=new Intent(Intent.ACTION_VIEW,issuesUrl);
        context.startActivity(intent);
    }
}
