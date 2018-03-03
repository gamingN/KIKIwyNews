package com.kiki.kikiwynews.presenter;

import com.kiki.kikiwynews.LifeSubscription;
import com.kiki.kikiwynews.app.AppConstants;
import com.kiki.kikiwynews.http.Callback;
import com.kiki.kikiwynews.http.HttpUtils;
import com.kiki.kikiwynews.http.Stateful;

import java.lang.ref.Reference;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.List;

import rx.Observable;

/**
 * presenter的基类(属于类，与presenter有点区别)
 * 封装attachView(LifeSubscription mLifeSubscription)，
 * invoke(Observable<T> observable,Callback<T> callback)，
 * checkState(List list)，detachView()
 * Created by Administrator on 2017/12/25.
 */

public class BasePresenter<T extends BaseView> {
    protected Reference<T> mReferenceView;//指的是界面

    protected T mView;
    private Callback callback;

    /**
     * 绑定对应的view层
     * @param mLifeSubscription
     */
    public void attachView(LifeSubscription mLifeSubscription){
        this.mReferenceView=new WeakReference<>((T) mLifeSubscription);
        mView=mReferenceView.get();
    }

    /**
     * 回调HttpUtils.invoke
     * @param observable
     * @param callback  是一个Subscriber
     * @param <T>
     */
    protected <T> void invoke(Observable<T> observable,Callback<T> callback){
        this.callback=callback;
        HttpUtils.invoke((LifeSubscription) mView,observable,callback);
    }

    /**
     * 给子类检查返回集合是否为空
     * 这样子做虽然耦合度高，但是接口都不是统一定的，我们没有什么更好的办法
     * @param list
     */
    public void checkState(List list) {
        if (list.size() == 0) {
            if (mView instanceof Stateful)
                ((Stateful) mView).setState(AppConstants.STATE_EMPTY);
            return;
        }
    }

    public void detachView(){
        if(mReferenceView!=null){
            mReferenceView.clear();
            mReferenceView=null;
        }
        if(mView!=null){
            mView=null;
        }
        if(callback!=null){
            callback.detachView();
        }
    }
}
