package com.kiki.kikiwynews;

import rx.Subscription;

/**
 * 只有一个绑定subscription的方法bindSubscription
 * 用于添加rx的监听的在onDestroy中记得关闭不然会内存泄漏，CompositeSubscription
 * Created by Administrator on 2017/12/20.
 */

public interface LifeSubscription {
    void bindSubscription(Subscription subscription);
}
