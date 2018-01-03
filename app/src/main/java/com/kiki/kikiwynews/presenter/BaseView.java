package com.kiki.kikiwynews.presenter;

/**
 * 只有一个成功后回调方法refreshView
 * Created by Administrator on 2017/12/25.
 */

public interface BaseView<T> {
    /**
     *回去数据成功调用该方法
     */
    void refreshView(T mData);
}
