package com.kiki.kikiwynews.injector.component;

import android.app.Activity;

import com.kiki.kikiwynews.injector.FragmentScope;
import com.kiki.kikiwynews.injector.module.FragmentModule;

import dagger.Component;

/**
 * Created by KIKI on 2018/1/3.
 * 346409606@qq.com
 */

@FragmentScope
@Component(dependencies = AppComponent.class, modules = FragmentModule.class)
public interface FragmentComponent {
    Activity getActivity();

}
