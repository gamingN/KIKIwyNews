package com.kiki.kikiwynews.http;

import com.blankj.utilcode.utils.NetworkUtils;
import com.blankj.utilcode.utils.ToastUtils;
import com.kiki.kikiwynews.LifeSubscription;
import com.kiki.kikiwynews.app.AppConstants;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/12/25.
 */

public class HttpUtils {
    /**
     * 将LifeSubscription设置到callback中,进行网络请求
     * 生成subscription，然后LifeSubscription绑定这个subscription
     */
    public static <T> void invoke(LifeSubscription lifecycle, Observable<T> observable, Callback<T> callback){
        Stateful target=null;
        if(lifecycle instanceof Stateful){    //同时实现了这两个接口
           target= (Stateful) lifecycle;
            callback.setTarget(target);
        }

        if(!NetworkUtils.isConnected()){
            ToastUtils.showShortToast("网络连接已断开");
            if(target!=null){
                target.setState(AppConstants.STATE_ERROR);
            }
            return;
        }

        Subscription subscription = observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(callback);
        lifecycle.bindSubscription(subscription);

    }



}
