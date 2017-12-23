package com.kiki.kikiwynews;

import rx.Subscription;

/**
 * Created by Administrator on 2017/12/20.
 */

public interface LifeSubscription {
    void bindSubscription(Subscription subscription);
}
