package com.kiki.kikiwynews.injector.component;

import android.app.Activity;

import com.kiki.kikiwynews.injector.ActivityScope;
import com.kiki.kikiwynews.injector.module.ActivityModule;

import dagger.Component;

/**
 * Created by KIKI on 2018/1/2.
 * 346409606@qq.com
 */

@ActivityScope
@Component(dependencies = AppComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {
    Activity getActivity();
}
