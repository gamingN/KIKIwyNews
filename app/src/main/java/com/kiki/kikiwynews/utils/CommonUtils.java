package com.kiki.kikiwynews.utils;

import android.content.Context;
import android.content.res.Resources;

import com.blankj.utilcode.utils.Utils;


/**
 * Created by KIKI on 2017/12/27.
 * 346409606@qq.com
 */

public class CommonUtils {

    public static float getDimens(int resId){
        return getResour().getDimension(resId);
    }

    public static Resources getResour(){
        return Utils.getContext().getResources();
    }

    public static int getColor(int resid){
        return getResour().getColor(resid);
    }
}
