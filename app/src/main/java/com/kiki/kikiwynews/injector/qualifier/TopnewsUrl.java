package com.kiki.kikiwynews.injector.qualifier;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;
import javax.inject.Singleton;

/**
 * Created by KIKI on 2018/2/26.
 * 346409606@qq.com
 */
@Qualifier
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface TopnewsUrl {
}
