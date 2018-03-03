package com.kiki.kikiwynews.injector;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by Administrator on 2017/12/24.
 */
@Scope
@Retention(RUNTIME)
public @interface FragmentScope {
}
