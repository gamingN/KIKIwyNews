package com.kiki.kikiwynews.injector.qualifier;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;

import javax.inject.Qualifier;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @Qualifier
 * Created by KIKI on 2017/12/26.
 * 346409606@qq.com
 */

@Qualifier
@Documented
@Retention(RUNTIME)
public @interface GankUrl {
}
