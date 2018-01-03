package com.kiki.kikiwynews;

import rx.Subscription;

/**
 * 只有一个绑定subscription的方法bindSubscription
 * Created by Administrator on 2017/12/20.
 */

public interface LifeSubscription {
    void bindSubscription(Subscription subscription);
}
