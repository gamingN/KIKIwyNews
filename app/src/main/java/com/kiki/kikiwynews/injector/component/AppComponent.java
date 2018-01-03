package com.kiki.kikiwynews.injector.component;

import com.kiki.kikiwynews.app.App;
import com.kiki.kikiwynews.injector.module.AppModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by KIKI on 2018/1/2.
 * 346409606@qq.com
 */
@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {
    App getContext();  // 提供App的Context

}